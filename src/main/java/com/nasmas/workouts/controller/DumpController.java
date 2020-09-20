package com.nasmas.workouts.controller;

import com.nasmas.workouts.model.Users;
import com.nasmas.workouts.model.local.UsersLocalList;
import com.nasmas.workouts.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DumpController {

    @Autowired
    private UsersService usersService;

    @ResponseBody
    @RequestMapping(value = "/api/replica", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<UsersLocalList> getUsersDump() {
        return new ResponseEntity(new UsersLocalList(usersService.allUsers()), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/api/replica/getUser", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity<Users> getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = null;
        if (!auth.getPrincipal().equals("anonymousUser")) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            user = usersService.findByName(userDetails.getUsername());
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }
}
