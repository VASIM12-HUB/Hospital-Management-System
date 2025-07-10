package com.hms.controller;

import com.hms.entity.Doctor;
import com.hms.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorRepository doctorRepository;

    @GetMapping
    public String listDoctors(Model model,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String specialization,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir) {

        // Sorting
        Sort sort = Sort.by(
                sortDir != null && sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
                sortBy != null ? sortBy : "id");

        // Filtering
        List<Doctor> doctors;
        if (name != null && !name.isEmpty()) {
            doctors = doctorRepository.findByNameContainingIgnoreCase(name, sort);
        } else if (specialization != null && !specialization.isEmpty()) {
            doctors = doctorRepository.findBySpecializationContainingIgnoreCase(specialization, sort);
        } else {
            doctors = doctorRepository.findAll(sort);
        }

        model.addAttribute("doctors", doctors);
        model.addAttribute("doctor", new Doctor());
        return "doctors";
    }

    

    @PostMapping
    public String addDoctor(@ModelAttribute Doctor doctor) {
        doctorRepository.save(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorRepository.deleteById(id);
        return "redirect:/doctors";
    }
}
