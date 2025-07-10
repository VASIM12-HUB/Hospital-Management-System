package com.hms.controller;

import dto.AppointmentViewDTO;
import com.hms.entity.Appointment;
import com.hms.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final AppointmentRepository appointmentRepository;

    @GetMapping
    public String viewRecords(Model model) {
        List<Appointment> appointments = appointmentRepository.findAll();

        List<AppointmentViewDTO> records = appointments.stream().map(app ->
                new AppointmentViewDTO(
                        app.getId(),
                        app.getPatient().getName(),
                        app.getPatient().getEmail(),
                        app.getPatient().getPhone(),
                        app.getPatient().getDisease(),
                        app.getDoctor().getName(),
                        app.getAppointmentTime()
                )
        ).collect(Collectors.toList());

        model.addAttribute("records", records);
        return "records";
    }
}
