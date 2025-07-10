package com.hms.repository;
import com.hms.entity.Patient;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByNameContainingIgnoreCase(String name, Sort sort);
    List<Patient> findByPhoneContaining(String phone, Sort sort);
    
    // Additional query methods if needed
    List<Patient> findByNameContainingOrPhoneContainingAllIgnoreCase(
        String name, String phone, Sort sort);
}