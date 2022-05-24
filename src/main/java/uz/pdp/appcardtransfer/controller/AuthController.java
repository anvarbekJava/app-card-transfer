package uz.pdp.appcardtransfer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appcardtransfer.payload.UserDto;
import uz.pdp.appcardtransfer.service.MyAuthService;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
    @Autowired
    MyAuthService myAuthService;

    @PostMapping(value = "/login")
    public HttpEntity<?> login(@RequestBody UserDto userDto){
        HttpEntity<?> httpEntity = myAuthService.loginToSystem(userDto);
        return httpEntity;
    }
}
