package com.nasmas.workouts.controller;

import com.nasmas.workouts.service.UtilService;
import com.sun.corba.se.impl.logging.UtilSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class RouteController {

    @Autowired
    private UtilService utilService;

    @GetMapping("/about")
    public String getAbout(ModelMap model, @RequestParam("page") Optional<Integer> page, @RequestParam("size")
            Optional<Integer> size) {
        model = utilService.getBaseModel(model, page, size);
        model.addAttribute("about", "Hi! This is Workouts - Web site then you can watch workouts video.\n" +
                " Also you can create your own couch account and share with people all around the World your personal workouts! \n" +
                "And just in case, this site can be the place there you get money. Real money. People will pray for your private workouts! \n" +
                "So, have fun!");
        return "about";
    }
}
