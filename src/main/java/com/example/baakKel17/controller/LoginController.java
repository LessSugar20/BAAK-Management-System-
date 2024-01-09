package com.example.baakKel17.controller;

import java.util.Objects;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.baakKel17.model.User;
import com.example.baakKel17.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    private LoginService userService;

    @GetMapping("/login")

    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new User());
        return mav;
    }


    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, HttpSession session) {
        User oauthUser = userService.login(user.getUsername(), user.getPassword());
        if (Objects.nonNull(oauthUser)) {
            session.setAttribute("userId", oauthUser.getId()); // Menyimpan ID pengguna dalam sesi
            session.setAttribute("username", oauthUser.getUsername());
            if (oauthUser.getRoles().equals("admin")) {
                return "redirect:/index2";
            } else if (oauthUser.getRoles().equals("mahasiswa")) {
                return "redirect:/produkuser";
            } else {
                return "redirect:/";
            }
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = { "/logout" }, method = RequestMethod.POST)
    public String logoutDo(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/login";
    }

}