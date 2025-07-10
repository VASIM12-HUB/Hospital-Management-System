package dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AppointmentViewDTO {
    private Long id;
    private String patientName;
    private String email;
    private String phone;
    private String disease;
    private String doctorName;
    private LocalDateTime appointmentTime;
}
