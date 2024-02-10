package com.oualid.springsecurity.controllers;

import com.oualid.springsecurity.models.Otp;
import com.oualid.springsecurity.models.User;
import com.oualid.springsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/add")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @PostMapping("/user/auth")
    public ResponseEntity<String> auth(@RequestBody User user){
        if(userService.auth(user)){
            return new ResponseEntity<>("NEED OTP", HttpStatus.OK);
        }
        throw new BadCredentialsException("Bad credentials");
    }

    @PostMapping("/otp/check")
    public void check(@RequestBody Otp otp, HttpServletResponse response){
        if (userService.check(otp)){
            response.setStatus(HttpServletResponse.SC_OK);
        }else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
