package com.abhijeetpadhy.SocialHub.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int errorCode = 0;
        String errorMeaning = "";
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                errorCode = 404;
                errorMeaning = "Not Found";
            } else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                errorCode = 500;
                errorMeaning = "Internal Server Error";
            }
        }
        model.addAttribute("errorCode", errorCode);
        model.addAttribute("errorMeaning", errorMeaning);
        return "error";
    }
}
