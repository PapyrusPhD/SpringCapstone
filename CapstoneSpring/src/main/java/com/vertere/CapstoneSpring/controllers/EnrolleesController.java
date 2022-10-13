package com.vertere.CapstoneSpring.controllers;

import com.vertere.CapstoneSpring.config.JwtToken;
import com.vertere.CapstoneSpring.exceptions.EnrolleesException;
import com.vertere.CapstoneSpring.models.Enrollees;
import com.vertere.CapstoneSpring.models.JwtResponse;
import com.vertere.CapstoneSpring.services.EnrolleesService;
import com.vertere.CapstoneSpring.services.JwtEnrolleeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@CrossOrigin
public class EnrolleesController {

    @Autowired
    EnrolleesService enrolleesService;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private JwtEnrolleeDetailService jwtenrolleeDetailsService;

    private AuthController authController;
    private Enrollees enrollees;

    @PostMapping("/enrollee/register")
    public ResponseEntity<Object> addEnrollee(@RequestBody Map<String, String> body) throws Exception {
        String username = body.get("username");

        if(enrolleesService.findByUsername(username).isPresent()){
            throw new EnrolleesException("Username already exists.");
        }
        else if (username.isEmpty()){
            throw new EnrolleesException("Username is empty");
        }
        else {
            String password = body.get("password");
            String encodedPassword = new BCryptPasswordEncoder().encode(password);
            Enrollees newEnrollee = new Enrollees(username, encodedPassword);
            enrolleesService.addEnrollee(newEnrollee);
            return new ResponseEntity<>("A new Enrollee has been registered", HttpStatus.CREATED);
        }
    }

    @PostMapping("/enrollee/login")
    public ResponseEntity<Object> login(@RequestBody Map<String, String> body){
        ArrayList<String> expected = enrolleesService.loginEnrollee(body.get("username"));
        String username = expected.get(0);
        String password = expected.get(1);
        String givenUsername = body.get("username");
        String givenPassword = body.get("password");
        if (username.equals(givenUsername) && password.equals(givenPassword)){
            final UserDetails userDetails = jwtenrolleeDetailsService.loadUserByUsername(givenUsername);
            final String token = jwtToken.generateToken(userDetails);
            ResponseEntity.ok(new JwtResponse(token));
            return new ResponseEntity<>("Welcome! " + givenUsername + "\n"+
                    "Your token: " + token, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("No account plss create", HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/account/update/{id}")
    public ResponseEntity<Object> updateEnrollee(@PathVariable int id, @RequestBody Enrollees enrollees){
        enrolleesService.updateEnrollee(id, enrollees);
        return new ResponseEntity<>("Your account has been updated", HttpStatus.OK);
    }

    @DeleteMapping("/account/delete/{id}")
    public ResponseEntity<Object> deleteEnrollee(@PathVariable int id){
        enrolleesService.deleteEnrollee(id);
        return new ResponseEntity<>("Your account has been deleted", HttpStatus.OK);
    }
}
