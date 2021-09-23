package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String viewDiceRoll(Model model) {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String diceRoll(@PathVariable int n, Model model) {
        Random random = new Random();
        int roll = random.nextInt(6) + 1;

        model.addAttribute("roll", roll);
        model.addAttribute("guess", n);
        model.addAttribute("isCorrect", n == roll);

        return "roll-dice";
    }
}
