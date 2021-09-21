package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String getPosts() {
        return "posts index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String viewPost(@PathVariable long id) {
        return "this is post " + id;
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
}
