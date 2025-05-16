/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tubes_pbo;

/**
 *
 * @author user
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

class GuestUser extends User {
    GuestUser(int idUser, String role) {
        super(idUser, role);
    }                                   
    
    public void loginWithUsername(String username, String password) {
        try {
            if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                throw new IllegalArgumentException("Username and password must not be empty.");
            }
            // Simulate authentication logic here
            // If authentication succeeds, upgrade to RegisteredUser
            if ("guest".equals(username) && "password".equals(password)) {
                // Example: create a new RegisteredUser and update role
                // In real code, fetch user data from DB
                RegisteredUser registeredUser = new RegisteredUser(
                    this.getIdUser(), "registered user", username, "guest@example.com", password
                );
                System.out.println("Login successful. Upgraded to RegisteredUser.");
                // You may want to return or store registeredUser somewhere
                this.setRole("registered user");
            } else {
                throw new Exception("Invalid username or password.");
            }
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
            return;
        }
    }

    public void loginWithEmail(String email, String password) {
        try {
            if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
                throw new IllegalArgumentException("Email and password must not be empty.");
            }
            // Simulate authentication logic here
            if ("guest@example.com".equals(email) && "password".equals(password)) {
                RegisteredUser registeredUser = new RegisteredUser(
                    this.getIdUser(), "registered user", "guest", email, password
                );
                System.out.println("Login successful. Upgraded to RegisteredUser.");
                this.setRole("registered user");
            } else {
                throw new Exception("Invalid email or password.");
            }
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
            return;
        }
    }

    public void register(String username, String email, String password) {
        if (username == null || username.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Username, email, and password must not be empty.");
        }
        try {
            if ("guest".equalsIgnoreCase(username)) {
                throw new Exception("Registration failed: Username 'guest' is not allowed.");
            }
            if ("useduser".equalsIgnoreCase(username)) {
                throw new Exception("Registration failed: Username already used.");
            }
            if ("guest@example.com".equalsIgnoreCase(email)) {
                throw new Exception("Registration failed: Email 'guest@example.com' is not allowed.");
            }
            if ("usedemail@example.com".equalsIgnoreCase(email)) {
                throw new Exception("Registration failed: Email already used.");
            }
            if (password.length() < 6) {
                throw new Exception("Registration failed: Password must be at least 6 characters.");
            }
            // Registration successful, upgrade to RegisteredUser
            RegisteredUser registeredUser = new RegisteredUser(
                this.getIdUser(), "registered user", username, email, password
            );
            System.out.println("Registration successful. Upgraded to RegisteredUser.");
            this.setRole("registered user");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}
