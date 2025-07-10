package com.hms.repository;
import com.hms.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    @Query("SELECT mr FROM MedicalRecord mr JOIN FETCH mr.patient p JOIN FETCH mr.doctor d")
    List<MedicalRecord> findAllWithJoin();
}