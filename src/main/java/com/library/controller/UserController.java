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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "api/register")
    public ResponseEntity<User> registerUser(@RequestBody @Valid UserDTO registrationDTO) {
        log.info("request for User controller before. registerUser: " + registrationDTO.getPassword() + " " + registrationDTO.getUsername());
        System.out.println(registrationDTO.toString());
        User user = userService.registerUser(registrationDTO);

        log.info("request for User controller. registerUser: " + registrationDTO.getPassword() + " " + registrationDTO.getUsername());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(path = "api/login", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> login(HttpServletResponse response,
                                                     HttpSession session,
                                                     @RequestBody @Valid UserDTO loginDTO) throws IOException {

        User user = userService.getUserByEmail(loginDTO.getEmail());
        log.info("request for User controller. login: " + loginDTO.getPassword() + " " + loginDTO.getUsername());
        String token = userService.authenticateUser(Optional.ofNullable(user.getUsername()).orElse(loginDTO.getUsername()), loginDTO.getPassword());
        log.info("token: " + token);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("userID", String.valueOf(user.getUserID()));
        return new ResponseEntity<>(tokenMap, HttpStatus.OK);
    }

    @PreAuthorize("userService.isAuthenticated()")
    @GetMapping("api/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("userService.isAuthenticated()")
    @GetMapping("api/users/me")
    public ResponseEntity<User> getAuthenticatedUser() {
        User user = userService.getAuthenticatedUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("userService.isAuthenticated()")
    @GetMapping("api/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
