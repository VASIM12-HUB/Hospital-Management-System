package com.hms.entity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)  // Add this annotation
public class Staff extends Person {
    private String role;
    private String department;
}