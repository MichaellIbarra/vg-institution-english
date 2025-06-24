package dev.matichelo.vg.ms.institution.app.domain.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "institutions")
public class Institution {
    @Id
    private String id;

    @NotEmpty(message = "El nombre de la institución es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Indexed(unique = true)
    private String institutionName;

    @NotEmpty(message = "El código de la institución es obligatorio")
    @Size(min = 2, max = 10, message = "El código debe tener entre 2 y 10 caracteres")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "El código solo puede contener letras mayúsculas y números")
    private String codeName;

    private String institutionLogo;

    @NotEmpty(message = "El código modular es obligatorio")
    @Size(min = 5, max = 10, message = "El código modular debe tener entre 5 y 10 caracteres")
    @Indexed(unique = true)
    private String modularCode;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String address;

    @NotEmpty(message = "El correo de contacto es obligatorio")
    @Email(message = "Formato de correo inválido")
    private String contactEmail;

    @Pattern(regexp = "^[0-9]{9,12}$", message = "El teléfono debe contener entre 9 y 12 dígitos")
    private String contactPhone;

    @Pattern(regexp = "^[AI]$", message = "El estado debe ser 'A' (activo) o 'I' (inactivo)")
    private String status;

    @NotBlank(message = "El ID del usuario es obligatorio")
    private String userId;

    private Map<String, Object> uiSettings;

    private Map<String, Object> evaluationSystem;

    private Map<String, Object> scheduleSettings;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @Valid
    private List<Headquarter> headquarters = new ArrayList<>();
}