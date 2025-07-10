package com.hms.controller;

import com.hms.entity.Role;
import com.hms.entity.User;
import com.hms.repository.UserRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private final AuthenticationManager authenticationManager;

    public UserController(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JavaMailSender mailSender,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam String phone,
            @RequestParam String role,
            Model model,
            HttpServletRequest request) {

        // Validate password match
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "register";
        }

        // Check if username exists
        if (userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }

        // Check if email exists
        if (userRepository.findByEmail(email).isPresent()) {
            model.addAttribute("error", "Email already registered");
            return "register";
        }

        // Convert role to proper enum value
        Role userRole;
        try {
            String roleName = role.replaceFirst("ROLE_", "");
            userRole = Role.valueOf(roleName);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Invalid role selected");
            return "register";
        }

        // Create and save user
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhone(phone);
        user.setRole(userRole);

        userRepository.save(user);

        // Auto-login the user after registration
        try {
            authenticateUserAndSetSession(username, password, request);
            return "redirect:/dashboard";
        } catch (Exception e) {
            // If auto-login fails, redirect to login page with success message
            return "redirect:/login?registered";
        }
    }

    private void authenticateUserAndSetSession(String username, String password, HttpServletRequest request) {
        try {
            // Use the raw password for authentication
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            throw new RuntimeException("Auto-login failed after registration", e);
        }
    }

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password-form";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam String email, Model model) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            model.addAttribute("error", "No account found with that email.");
            return "forgot-password-form";
        }

        // Generate OTP (6 digits)
        String otp = String.format("%06d", new Random().nextInt(999999));
        user.setResetToken(otp);
        userRepository.save(user);

        // Send email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset OTP");
        message.setText("Your OTP for password reset is: " + otp);
        mailSender.send(message);

        model.addAttribute("email", email);
        return "reset-password-form";
    }

    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam(required = false) String email, Model model) {
        model.addAttribute("email", email);
        return "reset-password-form";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String email,
            @RequestParam String otp,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            Model model) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null || !otp.equals(user.getResetToken())) {
            model.addAttribute("error", "Invalid OTP or email.");
            return "reset-password-form";
        }

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            return "reset-password-form";
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        userRepository.save(user);

        return "redirect:/login?reset";
    }

    @GetMapping("/check-auth")
    @ResponseBody
    public String checkAuth(Authentication auth) {
        return auth != null ? "Current roles: " + auth.getAuthorities() : "Not authenticated";
    }
}