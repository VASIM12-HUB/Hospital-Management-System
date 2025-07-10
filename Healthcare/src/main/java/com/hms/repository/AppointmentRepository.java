package com.hms.repository;
import com.hms.entity.Appointment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientId(Long patientId, Sort sort);
    List<Appointment> findByDoctorId(Long doctorId, Sort sort);
    List<Appointment> findByStatus(String status, Sort sort);
    
    // Additional query methods if needed
    List<Appointment> findByPatientIdAndStatus(Long patientId, String status, Sort sort);
    List<Appointment> findByDoctorIdAndStatus(Long doctorId, String status, Sort sort);
}