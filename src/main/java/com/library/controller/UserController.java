package com.library.controller;

import com.library.dto.UserDTO;
import com.library.model.User;
import com.library.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("api/register")
    public ResponseEntity<User> registerUser(@RequestBody @Valid UserDTO registrationDTO) {
        User user = userService.registerUser(registrationDTO);
        log.info("request for User controller. registerUser: " + registrationDTO.getPassword() + " " + registrationDTO.getUsername());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(path = "api/login", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> login(HttpServletResponse response,
                      HttpSession session,
                      @RequestBody @Valid UserDTO loginDTO) throws IOException {
        log.info("request for User controller. login: " + loginDTO.getPassword() + " " + loginDTO.getUsername());
        String token= userService.authenticateUser(loginDTO.getUsername(), loginDTO.getPassword());
        log.info("token: " + token);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("api/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
