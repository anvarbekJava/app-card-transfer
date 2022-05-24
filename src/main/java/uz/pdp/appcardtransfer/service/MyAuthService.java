package uz.pdp.appcardtransfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appcardtransfer.payload.ApiResponce;
import uz.pdp.appcardtransfer.payload.UserDto;
import uz.pdp.appcardtransfer.security.JwtProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MyAuthService implements UserDetailsService {
    @Lazy
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> list = new ArrayList<>(Arrays.asList(
                new User("Anvarbek",passwordEncoder.encode("123"), new ArrayList<>()),
                new User("Boburbek",passwordEncoder.encode("123"), new ArrayList<>()),
                new User("Asrorbek",passwordEncoder.encode("123"), new ArrayList<>())
        ));
        for (User user : list) {
            if (user.getUsername().equals(username))
                return user;
        }
        throw new UsernameNotFoundException("Username not found");
    }
    public HttpEntity<?> loginToSystem(UserDto userDto){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userDto.getUsername(), userDto.getPassword()));
            String token = jwtProvider.generateToken(userDto.getUsername());
            return ResponseEntity.ok(token);
        }catch (BadCredentialsException exception){
            return ResponseEntity.status(401).body("Login yoki parol hato");
        }
    }
}

