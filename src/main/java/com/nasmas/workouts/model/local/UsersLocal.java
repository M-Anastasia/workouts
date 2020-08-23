package com.nasmas.workouts.model.local;

import com.nasmas.workouts.model.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersLocal {

    private String name;
    private String password;

    public UsersLocal(Users user) {
        setName(user.getName());
        setPassword(user.getPassword());
    }
}
