package com.vertere.CapstoneSpring.repositories;


import com.vertere.CapstoneSpring.models.Enrollees;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolleesRepository extends CrudRepository<Enrollees, Object> {
    Enrollees findByUsername(String username);
}
