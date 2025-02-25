package com.library.service;

import com.library.dto.UserDTO;
import com.library.exception.UserBlockedException;
import com.library.model.Role;
import com.library.model.User;
import com.library.repository.RoleRepository;
import com.library.repository.UserRepository;
import com.library.security.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.util.List;
import java.util.Optional;


@Slf4j
@AllArgsConstructor
@Service
public class UserService  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final RoleRepository roleRepo;


    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean isAuthenticated() {
        log.info("Checking if user is authenticated");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.getName() + " " + authentication.isAuthenticated());
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            log.info("User is not authenticated");
            return false;
        }
        return authentication.isAuthenticated();
    }

    public User registerUser(UserDTO registrationDTO) {
        User user = userRepository.findByUsername(registrationDTO.getFirstName() + "_" + registrationDTO.getLastName());
        if (user != null && user.getUsername() != registrationDTO.getFirstName() + "_" + registrationDTO.getLastName() ) {
            user.setUsername(registrationDTO.getFirstName() + "_" + registrationDTO.getLastName() + "_" + user.getUserID());
            return userRepository.save(user);
        }
        User userNew = new User();
        String passwordSalt = null;
        if (registrationDTO.getUsername() == null && registrationDTO.getPassword() == null){
            userNew.setFirstName("");
            userNew.setLastName("");
            userNew.setUsername(registrationDTO.getUsername());
            userNew.setActive(Boolean.parseBoolean(registrationDTO.getIsActive()));
            passwordSalt = registrationDTO.getPassword() + generatePassayPassword(10);
            userNew.setPassword(passwordEncoder.encode(passwordSalt));
            userNew.setSalt(passwordSalt);
            return userRepository.save(userNew);
        }

        if (registrationDTO.getUsername() != null && registrationDTO.getPassword() != null){
            if (registrationDTO.getFirstName() == null && registrationDTO.getLastName() == null){
                userNew.setFirstName("");
                userNew.setLastName("");
                userNew.setEmail(registrationDTO.getEmail());
                userNew.setUsername(registrationDTO.getUsername());
                userNew.setActive(Boolean.parseBoolean(registrationDTO.getIsActive()));
                passwordSalt = registrationDTO.getPassword();
                userNew.setPassword(passwordEncoder.encode(passwordSalt));
                userNew.setSalt(passwordSalt);
            } else {
                userNew.setFirstName(registrationDTO.getFirstName());
                userNew.setLastName(registrationDTO.getLastName());
                userNew.setEmail(registrationDTO.getEmail());
                userNew.setUsername(registrationDTO.getUsername());
                userNew.setActive(Boolean.parseBoolean(registrationDTO.getIsActive()));
                passwordSalt = registrationDTO.getPassword();
                userNew.setPassword(passwordEncoder.encode(passwordSalt));
                userNew.setSalt(passwordSalt);
            }
        }
        log.info(userNew.toString() + " " + userNew.getUsername() + " " + userNew.getPassword());
        return userRepository.save(userNew);
    }

    public Role saveRole(Role role) {
        log.info("Saving new user to the database", role.getName());
        return roleRepo.save(role);
    }

    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    public String generatePassayPassword(int length) {
        PasswordGenerator gen = new PasswordGenerator();
        EnglishCharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        EnglishCharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        EnglishCharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);


        return gen.generatePassword(length, lowerCaseRule,
                upperCaseRule, digitRule);
    }

    public boolean isPasswordValid(User user, String oldPassword) {
        User userSearch = userRepository.findByUsername(user.getUsername());
        if (userSearch == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    public void changePassword(User user, String newPassword) {
        User userSearch = userRepository.findByUsername(user.getUsername());
        if (userSearch == null) {
            throw new UsernameNotFoundException("User not found");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setSalt(newPassword);
        userRepository.save(user);
    }

    public boolean deactivateUser(String username){
        User userSearch = userRepository.findByUsername(username);
        if (userSearch == null) {
            throw new UsernameNotFoundException("User not found");
        }
        userSearch.setActive(false);
        User userSave = userRepository.save(userSearch);
        if (userSave != null){
            return true;
        }
        return false;
    }

    public boolean activateUser(String username){
        User userSearch = userRepository.findByUsername(username);
        if (userSearch == null) {
            throw new UsernameNotFoundException("User not found");
        }
        userSearch.setActive(true);
        User userSave = userRepository.save(userSearch);
        if (userSave != null){
            return true;
        }
        return false;
    }


    public String authenticateUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtUtil.generateToken(userDetails.getUsername());
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            throw new UsernameNotFoundException("User not authenticated");
        }
        String username = authentication.getName();
        return userRepository.findByUsername(username);
    }
}
