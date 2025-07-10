package com.hms.repository;
import com.hms.entity.Doctor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByNameContainingIgnoreCase(String name, Sort sort);
    List<Doctor> findBySpecializationContainingIgnoreCase(String specialization, Sort sort);
    
    // Additional query methods if needed
    List<Doctor> findByNameContainingOrSpecializationContainingAllIgnoreCase(
        String name, String specialization, Sort sort);
}