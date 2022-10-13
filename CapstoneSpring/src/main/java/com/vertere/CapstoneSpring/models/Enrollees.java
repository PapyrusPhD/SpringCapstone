package com.vertere.CapstoneSpring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Enrollees")
public class Enrollees {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String username;

    @Column
    private String password;

    @OneToMany(mappedBy = "enrollees")
    @JsonIgnore
    private Set<Enrollments> enrollments;

    public Enrollees(){}

    public Enrollees(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public Set<Enrollments> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollments> enrollments) {
        this.enrollments = enrollments;
    }
}
