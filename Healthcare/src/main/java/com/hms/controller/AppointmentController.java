package com.hms.controller;

import com.hms.entity.Appointment;
import com.hms.repository.AppointmentRepository;
import com.hms.repository.DoctorRepository;
import com.hms.repository.PatientRepository;
//import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;
import java.util.List;


@Controller
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @GetMapping
    public String listAppointments(Model model,
            @RequestParam(required = false) Long patientId,
            @RequestParam(required = false) Long doctorId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir) {

        // Sorting
        Sort sort = Sort.by(
                sortDir != null && sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
                sortBy != null ? sortBy : "id");

        // Filtering
        List<Appointment> appointments;
        if (patientId != null) {
            appointments = appointmentRepository.findByPatientId(patientId, sort);
        } else if (doctorId != null) {
            appointments = appointmentRepository.findByDoctorId(doctorId, sort);
        } else if (status != null && !status.isEmpty()) {
            appointments = appointmentRepository.findByStatus(status, sort);
        } else {
            appointments = appointmentRepository.findAll(sort);
        }

        model.addAttribute("appointments", appointments);
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("doctors", doctorRepository.findAll());
        model.addAttribute("appointment", new Appointment());
        return "appointments";
    }

    
    // ✅ Only one POST mapping here
    @PostMapping
    public String addAppointment(@ModelAttribute Appointment appointment) {
        appointmentRepository.save(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentRepository.deleteById(id);
        return "redirect:/appointments";
    }
}
