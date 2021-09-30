package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String showPosts(Model model) {
        List<Post> allPosts = postDao.findAll();

        model.addAttribute("posts", allPosts);

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable long id, Model model) {
        Post post = postDao.getById(id);

        model.addAttribute("postID", id);
        model.addAttribute("post", post);

        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String viewPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("posts/create")
    public String createPost(@ModelAttribute Post postToAdd) {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postToAdd.setOwner(authenticatedUser);

        postDao.save(postToAdd);

        emailService.prepareAndSend(
                postToAdd,
                "New Post Created",
                "You created a new post: " + postToAdd.getTitle()
        );

        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String viewEditForm(@PathVariable long id, Model model) {
        model.addAttribute("postToUpdate", postDao.getById(id));
        return "/posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, @ModelAttribute Post postToUpdate) {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        postToUpdate.setId(id);
        postToUpdate.setOwner(authenticatedUser);

        postDao.save(postToUpdate);

        return "redirect:/posts";
    }

    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id) {
        postDao.delete(postDao.getById(id));

        return "redirect:/posts";
    }
}
