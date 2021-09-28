package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
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
    @ResponseBody
    public String viewPostForm() {
        return "view create post form";
    }

    @PostMapping("posts/create")
    @ResponseBody
    public String createPost() {
        return "create a new post";
    }

    @GetMapping("/posts/edit/{id}")
    public String viewEditForm(@PathVariable long id, Model model) {
        model.addAttribute("id", postDao.getById(id).getId());
        return "/posts/edit";
    }

    @PostMapping("/posts/edit/{id}")
    public String editPost(
            @PathVariable long id,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "body") String body
    ) {
        postDao.save(new Post(id, title, body));

        return "redirect:/posts";
    }

    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id) {
        postDao.delete(postDao.getById(id));

        return "redirect:/posts";
    }
}
