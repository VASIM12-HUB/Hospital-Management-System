package com.hms.controller;
import com.hms.entity.Patient;
import com.hms.repository.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {
    private final PatientRepository patientRepository;

    @GetMapping
    public String listPatients(Model model,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir) {

        Sort sort = Sort.by(
                sortDir != null && sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
                sortBy != null ? sortBy : "id");

        List<Patient> patients;
        if (name != null && !name.isEmpty()) {
            patients = patientRepository.findByNameContainingIgnoreCase(name, sort);
        } else if (phone != null && !phone.isEmpty()) {
            patients = patientRepository.findByPhoneContaining(phone, sort);
        } else {
            patients = patientRepository.findAll(sort);
        }

        model.addAttribute("patients", patients);
        model.addAttribute("patient", new Patient());
        return "patients";
    }

    @PostMapping
    public String addPatient(@ModelAttribute Patient patient) {
        patientRepository.save(patient);
        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String editPatientForm(@PathVariable Long id, Model model) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
        model.addAttribute("patient", patient);
        return "edit-patient";
    }

    @PostMapping("/edit/{id}")
    public String updatePatient(@PathVariable Long id,
            @Valid @ModelAttribute("patient") Patient patient,
            BindingResult result) {
        if (result.hasErrors()) {
            return "edit-patient";
        }

        // Get existing patient with appointments
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));

        // Copy non-collection fields
        existingPatient.setName(patient.getName());
        existingPatient.setEmail(patient.getEmail());
        existingPatient.setPhone(patient.getPhone());

        // Save the updated patient
        patientRepository.save(existingPatient);

        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientRepository.deleteById(id);
        return "redirect:/patients";
    }
}