package com.vertere.CapstoneSpring.repositories;

import com.vertere.CapstoneSpring.models.Enrollments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends CrudRepository<Enrollments, Object> {
}
