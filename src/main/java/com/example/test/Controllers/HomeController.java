package com.example.test.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {
    public static String exception = "null";

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("name", "Главная страница:):):):):):):)");
        return "home";
    }

    @PostMapping("/resultPost")
    public String PostResult(
            @RequestParam(
                    value = "variable1",
                    required = true,
                    defaultValue = "0") double a,
            @RequestParam(
                    value = "variable2",
                    required = true,
                    defaultValue = "0") double b,
            @RequestParam(
                    value = "operation",
                    required = true,
                    defaultValue = "0") String select,
            Model model) {
        model.addAttribute("res", Calc(a, b, select));
        model.addAttribute("exception", exception);
        model.addAttribute("title", "Post   Mapping");
        return "result";
    }

    @GetMapping("/resultGet")
    public String GetResult(@RequestParam(
            value = "variable1",
            required = true,
            defaultValue = "0") double a,
                            @RequestParam(
                                    value = "variable2",
                                    required = true,
                                    defaultValue = "0") double b,
                            @RequestParam(
                                    value = "operation",
                                    required = true,
                                    defaultValue = "0") String select,
                            Model model) {
        model.addAttribute("res", Calc(a, b, select));
        model.addAttribute("exception", exception);
        model.addAttribute("title", "GetMapping");
        return "result";
    }

    public double Calc(double a, double b, String select) {
        double c = 0;
        try {
            switch (select) {
                case "+":
                    c = a + b;
                    break;
                case "-":
                    c = a - b;
                    break;
                case "*":
                    c = a * b;
                    break;
                case "/":
                    if (b == 0) {
                        exception = "делить на ноль нельзя";
                    } else {
                        c = a / b;
                    }
                    break;
            }
        } catch (Exception e) {
            exception = e.toString();
        }
        return c;
    }
}