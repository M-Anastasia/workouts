package com.nasmas.workouts.controller;

import com.nasmas.workouts.model.local.UsersLocalList;
import com.nasmas.workouts.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/replica")
public class DumpController {

    @Autowired
    private UsersService usersService;

    @ResponseBody
    @GetMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<UsersLocalList> getUsersDump() {
        return new ResponseEntity(new UsersLocalList(usersService.allUsers()), HttpStatus.OK);
    }
}
