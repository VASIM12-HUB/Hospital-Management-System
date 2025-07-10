package com.hms.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Patient extends Person {
    private String disease;  // Add this field
    @OneToMany(mappedBy = "patient", 
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();
     @OneToMany(mappedBy = "patient",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<MedicalRecord> medicalRecords = new ArrayList<>();

    // Helper method to maintain bidirectional relationship
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.setPatient(this);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
        appointment.setPatient(null);
    }
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecords.add(medicalRecord);
        medicalRecord.setPatient(this);
    }
    
    public void removeMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecords.remove(medicalRecord);
        medicalRecord.setPatient(null);
    }
}