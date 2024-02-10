package com.oualid.SpringSecurity.config;

import com.oualid.SpringSecurity.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationServerProxy {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${auth.server.base.url}")
    private String baseUrl;

    public void sendAuth(String username,String password){
        String url = baseUrl + "/user/auth";

        var body = new User();
        body.setUsername(username);
        body.setPassword(password);

        var request = new HttpEntity<>(body);
        restTemplate.postForEntity(url,request,Void.class);
    }

    public boolean sendOtp(String username,String code){
        String url = baseUrl + "/otp/check";

        var body = new User();
        body.setUsername(username);
        body.setCode(code);

        var request = new HttpEntity<>(body);
        var response = restTemplate.postForEntity(url,request,Void.class);

        return response.getStatusCode().equals(HttpStatus.OK);
    }

    public void AddUser(String username,String password){
        String url = baseUrl + "/user/add";

        var body = new User();
        body.setUsername(username);
        body.setPassword(password);

        var request = new HttpEntity<>(body);
        restTemplate.postForEntity(url,request,Void.class);

    }
}
