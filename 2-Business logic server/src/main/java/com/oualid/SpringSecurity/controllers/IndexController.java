package com.oualid.SpringSecurity.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/business_logic",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
public class IndexController {


    @PostMapping("/home")
    public String WelcomeHom(@RequestBody String message){
        return "This is your message : "+message;
    }
}
