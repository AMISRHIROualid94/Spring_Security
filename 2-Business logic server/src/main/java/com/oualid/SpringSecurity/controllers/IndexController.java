package com.oualid.SpringSecurity.controllers;

import com.oualid.SpringSecurity.config.AuthenticationServerProxy;
import com.oualid.SpringSecurity.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/business_logic",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
public class IndexController {

    @Autowired
    private AuthenticationServerProxy proxy;
    @PostMapping("/home")
    public String WelcomeHom(@RequestBody String message){
        return "This is your message : "+message;
    }

    @PostMapping(value = "/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody User user){
        proxy.AddUser(user.getUsername(),user.getPassword());
    }
}
