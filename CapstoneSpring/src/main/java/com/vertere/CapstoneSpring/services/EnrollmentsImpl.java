package com.vertere.CapstoneSpring.services;

import com.vertere.CapstoneSpring.config.JwtToken;
import com.vertere.CapstoneSpring.exceptions.EnrolleesException;
import com.vertere.CapstoneSpring.models.Enrollees;
import com.vertere.CapstoneSpring.models.Enrollments;
import com.vertere.CapstoneSpring.repositories.EnrolleesRepository;
import com.vertere.CapstoneSpring.repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class EnrollmentsImpl implements EnrollmentsService{
    @Autowired
    private EnrolleesRepository enrolleesRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private EnrolleesService enrolleesService;

    @Autowired
    JwtToken jwtToken;

    public void addEnrolments(String stringToken, Enrollments enrollments) {
        Enrollees author = enrolleesRepository.findByUsername(jwtToken.getUsernameFromToken(stringToken));
        Enrollments newEnrollments = new Enrollments();
        newEnrollments.setName(enrollments.getName());
        newEnrollments.setDescription(enrollments.getDescription());
        newEnrollments.setPrice(enrollments.getPrice());
        newEnrollments.setEnrollees(author);
        enrollmentRepository.save(newEnrollments);
    }

    public ResponseEntity<Object> updateEnrolments(int id, String stringToken, Enrollments enrollments) {
        Enrollments enrollmentForUpdate = enrollmentRepository.findById(id).get();
        String user = enrollmentForUpdate.getEnrollees().getUsername();
        String authenticatedUserName = jwtToken.getUsernameFromToken(stringToken);

        if(authenticatedUserName.equals(user)){
            enrollmentForUpdate.setName(enrollments.getName());
            enrollmentForUpdate.setDescription(enrollments.getDescription());
            enrollmentForUpdate.setPrice(enrollments.getPrice());
            enrollmentRepository.save(enrollmentForUpdate);
            return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Dumbass!!!! you are trying to update without adding the specific id", HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<Object> deleteEnrolments(int id, String stringToken) {
        Enrollments enrollmentForDelete = enrollmentRepository.findById(id).get();
        String user = enrollmentForDelete.getEnrollees().getUsername();
        String authenticatedUserName = jwtToken.getUsernameFromToken(stringToken);

        if (authenticatedUserName.equals(user)){
            enrollmentRepository.deleteById(id);
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Dumbass!!!! you are trying to delete without adding the specific id", HttpStatus.UNAUTHORIZED);
        }
    }

    public Iterable<Enrollments> getEnrolments(String stringToken){
        Enrollees author = enrolleesRepository.findByUsername(jwtToken.getUsernameFromToken(stringToken));
        return author.getEnrollments();
    }

    public Iterable<Enrollments> getSpecificEnrolments(String stringToken){
        return enrollmentRepository.findAll();
    }
}
