package com.nasmas.workouts.model.local;

import com.nasmas.workouts.model.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UsersLocalList {

    private List<UsersLocal> usersLocalList;

    public UsersLocalList(List<Users> users) {
        usersLocalList = new ArrayList<>();
        for (Users user : users) {
            usersLocalList.add(new UsersLocal(user));
        }
    }
}
