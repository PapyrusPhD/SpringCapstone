package com.vertere.CapstoneSpring.controllers;
import com.vertere.CapstoneSpring.models.Enrollments;
import com.vertere.CapstoneSpring.services.EnrollmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EnrollmentsController {

    @Autowired
    private EnrollmentsService enrollmentsService;

    @PostMapping("/enrollment/add")
    public ResponseEntity<Object> addEnrollment(@RequestHeader(value = "Authorization") String stringToken,
                                                @RequestBody Enrollments enrollments){
        enrollmentsService.addEnrolments(stringToken, enrollments);
        return new ResponseEntity<>("Successfully added a enrollment", HttpStatus.ACCEPTED);
    }

    @PutMapping("/enrollment/update/{id}")
    public ResponseEntity<Object> updateEnrollment(@RequestHeader(value = "Authorization") String stringToken,
                                                   @PathVariable int id, @RequestBody Enrollments enrollments){
        return enrollmentsService.updateEnrolments(id, stringToken, enrollments);
    }

    @DeleteMapping("/enrollment/delete/{id}")
    public ResponseEntity<Object> deleteEnrollment(@PathVariable int id, @RequestHeader(value = "Authorization") String stringToken){
        return enrollmentsService.deleteEnrolments(id, stringToken);
    }

    @GetMapping("/enrollment/all")
    public ResponseEntity<Object> getAllEnrollment(@RequestHeader(value = "Authorization") String stringToken){
        return new ResponseEntity<>(enrollmentsService.getEnrolments(stringToken), HttpStatus.OK);
    }

    @GetMapping("/enrollment")
    public ResponseEntity<Object> getSpecificEnrollment(@RequestHeader(value = "Authorization") String stringToken){
        return new ResponseEntity<>(enrollmentsService.getSpecificEnrolments(stringToken), HttpStatus.OK);
    }
}
