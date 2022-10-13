package com.vertere.CapstoneSpring.services;
import com.vertere.CapstoneSpring.models.Enrollees;

import java.util.ArrayList;
import java.util.Optional;

public interface EnrolleesService {
    void addEnrollee(Enrollees enrollees);
    void updateEnrollee(int id, Enrollees enrollees);
    void deleteEnrollee(int id);
    Optional<Enrollees> findByUsername(String enrollee);
    ArrayList<String> loginEnrollee(String username);
}
