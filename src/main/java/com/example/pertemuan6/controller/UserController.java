package com.example.pertemuan6.controller;

import com.example.pertemuan6.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private static List<User> daftarMahasiswa = new ArrayList<>();

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String prosesLogin(@RequestParam String username,
                              @RequestParam String password,
                              Model model) {
        if ("admin".equals(username) && "20230140062".equals(password)) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Username atau Password Salah!");
            return "login";
        }
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("listMhs", daftarMahasiswa);
        return "home";
    }

    @GetMapping("/form")
    public String formPage(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/save")
    public String simpanData(@ModelAttribute User user) {
        daftarMahasiswa.add(user);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
