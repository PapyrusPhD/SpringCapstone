package com.vertere.CapstoneSpring.models;

import javax.persistence.*;

@Entity
@Table(name = "enrollments")
public class Enrollments {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private double price;

    @ManyToOne
    @JoinColumn(name="enrollees_id", nullable = false)
    private Enrollees enrollees;

    public void Enrollments(){}
    public void Enrollments(String name, String description, double price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public void setEnrollees(Enrollees enrollees) { this.enrollees = enrollees; }

    public Enrollees getEnrollees() { return enrollees; }

    public void setDescription(String description) { this.description = description; }

    public String getDescription() { return description; }


}
