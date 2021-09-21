package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.rmi.MarshalledObject;
import java.util.Random;

@Controller
public class HelloController {
    @GetMapping("/helloFromSpring")
    @ResponseBody
    public String helloFromSpring() {
        return "Hello from Spring Boot.";
    }

    @GetMapping("/helloWorld/{username}")
    @ResponseBody
    public String helloWorld(@PathVariable String username) {
        return "Hello, " + username + "!";
    }

    @GetMapping("/happy_birthday/{username}/{age}")
    @ResponseBody
    public String happyBirthday(
            @PathVariable String username,
            @PathVariable int age
    ) {
        return "Happy Birthday, " + username + "! You are " + age + " years old.";
    }

    @GetMapping("/random/number")
    @ResponseBody
    public int randomNumber() {
        Random r = new Random();
        int low = 1;
        int high = 100;
        return r.nextInt(high - low + 1) + low;
    }

    @GetMapping("/weather")
    public String viewWeather(Model model) {
        model.addAttribute("temp", "93F");
        return "weather";
    }
}
