package com.example.pertemuan6.controller;

import com.example.pertemuan6.model.User;
import com.example.pertemuan6.repository.UserRepository; // Pastikan kamu sudah membuat interface UserRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private final String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/uploads/";

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
        model.addAttribute("listMhs", userRepository.findAll());
        return "home";
    }

    @GetMapping("/form")
    public String formPage(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/save")
    public String simpanData(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/home";
    }

    @PostMapping("/upload-home")
    public String uploadDariHome(@RequestParam("id") Long id,
                                 @RequestParam("deskripsi") String deskripsi,
                                 @RequestParam("file") MultipartFile file) throws IOException {

        User user = userRepository.findById(id).orElse(null);

        if (user != null && !file.isEmpty()) {
            File uploadPath = new File(UPLOAD_DIR);
            if (!uploadPath.exists()) uploadPath.mkdirs();

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.write(path, file.getBytes());

            user.setFotoPath(fileName);
            user.setDeskripsi(deskripsi);
            userRepository.save(user);
        }
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}