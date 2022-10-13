package com.vertere.CapstoneSpring.services;

import com.vertere.CapstoneSpring.models.Enrollees;
import com.vertere.CapstoneSpring.models.Enrollments;
import org.springframework.http.ResponseEntity;

public interface EnrollmentsService {
    void addEnrolments(String stringToken, Enrollments enrollments);
    ResponseEntity<Object> updateEnrolments(int id, String stringToken, Enrollments enrollments);
    ResponseEntity<Object> deleteEnrolments(int id, String stringToken);
    Iterable<Enrollments> getEnrolments(String stringToken);
    Iterable<Enrollments> getSpecificEnrolments(String stringToken);
}
