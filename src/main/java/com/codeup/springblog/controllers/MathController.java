package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @GetMapping("/add/{addendOne}/and/{addendTwo}")
    @ResponseBody
    public int add(@PathVariable int addendOne, @PathVariable int addendTwo) {
        return addendOne + addendTwo;
    }

    @GetMapping("/subtract/{operandOne}/from/{operandTwo}")
    @ResponseBody
    public int subtract(@PathVariable int operandOne, @PathVariable int operandTwo) {
        return operandTwo - operandOne;
    }

    @GetMapping("/multiply/{factorOne}/and/{factorTwo}")
    @ResponseBody
    public int multiply(@PathVariable int factorOne, @PathVariable int factorTwo) {
        return factorOne * factorTwo;
    }

    @GetMapping("/divide/{dividend}/by/{divisor}")
    @ResponseBody
    public int divide(@PathVariable int dividend, @PathVariable int divisor) {
        return dividend / divisor;
    }
}
