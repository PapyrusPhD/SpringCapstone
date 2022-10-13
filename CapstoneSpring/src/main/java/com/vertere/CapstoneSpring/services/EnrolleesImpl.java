package com.vertere.CapstoneSpring.services;

import com.vertere.CapstoneSpring.models.Enrollees;
import com.vertere.CapstoneSpring.repositories.EnrolleesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Service
public class EnrolleesImpl implements EnrolleesService {
    @Autowired
    private EnrolleesRepository enrolleesRepository;


    public void addEnrollee(Enrollees enrollees) {
        enrolleesRepository.save(enrollees);
    }

    public void updateEnrollee(int id, Enrollees enrollees) {
        Enrollees findID = enrolleesRepository.findById(id).get();

        findID.setUsername(enrollees.getUsername());
        findID.setPassword(enrollees.getPassword());

        enrolleesRepository.save(findID);
    }

    public void deleteEnrollee(int id) {
        enrolleesRepository.deleteById(id);
    }

    public Optional<Enrollees> findByUsername(String username) {
        return Optional.ofNullable(enrolleesRepository.findByUsername(username));
    }

    public ArrayList<String> loginEnrollee(String username) {
        Enrollees matchEnrollee = enrolleesRepository.findByUsername(username);
        String password = matchEnrollee.getPassword();
        return new ArrayList(Arrays.asList(username, password));
    }


}