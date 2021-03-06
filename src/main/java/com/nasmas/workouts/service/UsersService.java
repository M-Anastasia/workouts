package com.nasmas.workouts.service;

import com.nasmas.workouts.model.Role;
import com.nasmas.workouts.model.Users;
import com.nasmas.workouts.repository.RoleRepository;
import com.nasmas.workouts.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class UsersService implements UserDetailsService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MessageDigestPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public Users findUserById(Long userId) {
        Optional<Users> userFromDb = usersRepository.findById(userId);
        return userFromDb.orElse(new Users());
    }

    public Users findByName(String name) {
        return (Users) entityManager.createQuery("SELECT u FROM Users u WHERE u.name = '" + name + "'").getSingleResult();
    }

    public List<Users> allUsers() {
        return usersRepository.findAll();
    }

    public boolean saveUser(Users user) {
        Users userFromDB = usersRepository.findByName(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        user.setUuid(UUID.randomUUID());
        user.setRoles(Collections.singleton(new Role(3L, UUID.randomUUID(), "ROLE_COACH")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
        return true;
    }

    public String getSaltString() {
        String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) {
            int index = (int) (rnd.nextFloat() * saltChars.length());
            salt.append(saltChars.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
