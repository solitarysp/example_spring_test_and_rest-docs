package com.lethanh98.controller;

import com.lethanh98.entity.User;
import com.lethanh98.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @GetMapping()
    public List<User> get() {
        List<User> users = (List<User>) userRepo.findAll();
        return users;
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") String id) {
        List<User> users = (List<User>) userRepo.findAll();
        return users.get(0);
    }

    @PostMapping()
    @Transactional
    public User post(String firstName, String lastName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return userRepo.save(user);
    }

    @PostMapping("/new")
    @Transactional
    public User postNew(@RequestBody User user) {
        return userRepo.save(user);
    }
}
