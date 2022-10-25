package com.example.backend.controller;

import com.example.backend.entity.Userlog;
import com.example.backend.entity.Users;
import com.example.backend.service.UserLogService;
import com.example.backend.service.UserService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserLogService userLogService;

    @PostMapping(path="/signup",consumes = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<?> addNewUser (@RequestBody Users users) {
        System.out.println(users.getEmail());
        System.out.println(users.getFirstname());
        Users existingUser = userService.getUserDetails(users.getEmail());
        if(existingUser!=null){
            return new ResponseEntity(null,HttpStatus.UNAUTHORIZED);
        }
        Users user = userService.addUser(users);
        if(user==null){
            return new ResponseEntity(null,HttpStatus.UNAUTHORIZED);
        }
        File file = new File(System.getProperty("user.dir")+"/public/uploads/"+users.getEmail().split("\\.")[0]);
        System.out.println(file);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }

        System.out.println("Saved");
        return new ResponseEntity(null,HttpStatus.CREATED);
    }

    @GetMapping(path="/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(path="/login",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody Users user, HttpSession session) throws JSONException {

        session.setAttribute("email",user.getEmail());
        List<Users> users = userService.login(user.getEmail(), user.getPassword());
        if(users.size()>0) {
            userService.updateLastLogin(user.getEmail());
            return new ResponseEntity(null, HttpStatus.OK);
        }
        return new ResponseEntity(null,HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(path="/updateuser",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUserDetails(@RequestBody Users user, HttpSession session) throws JSONException {

        int count = userService.updateUserDetails(user);

        if(count>0)
            return new ResponseEntity(null, HttpStatus.OK);
        return new ResponseEntity(null,HttpStatus.UNAUTHORIZED);
    }


    @GetMapping(path="/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> getUserDetails(HttpSession session){

        String email = (String)session.getAttribute("email");
        Users user = userService.getUserDetails(email);
        if(user!=null)
            return new ResponseEntity(user, HttpStatus.OK);
        return new ResponseEntity(null,HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(path="/userlogs",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Userlog>> getUserLogs(HttpSession session){

        String email = (String)session.getAttribute("email");

        List<Userlog> userlogs = userLogService.getUserlogByEmail(email);

        return new ResponseEntity(userlogs,HttpStatus.OK);
    }

    @PostMapping(value = "/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> logout(HttpSession session) {
        System.out.println(session.getAttribute("email"));
        session.invalidate();
        return  new ResponseEntity(HttpStatus.OK);
    }
}