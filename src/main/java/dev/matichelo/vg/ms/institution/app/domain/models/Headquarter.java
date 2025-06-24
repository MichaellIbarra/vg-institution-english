package dev.matichelo.vg.ms.institution.app.domain.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "headquarters")
public class Headquarter {
    @Id
    private String id;

    @NotEmpty(message = "El nombre de la sede es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String headquartersName;

    @NotEmpty(message = "El código de la sede es obligatorio")
    @Size(min = 2, max = 15, message = "El código debe tener entre 2 y 15 caracteres")
    private String headquartersCode;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String address;

    @NotBlank(message = "El nombre de la persona de contacto es obligatorio")
    private String contactPerson;

    @NotEmpty(message = "El correo de contacto es obligatorio")
    @Email(message = "Formato de correo inválido")
    private String contactEmail;

    @Pattern(regexp = "^[0-9]{9,12}$", message = "El teléfono debe contener entre 9 y 12 dígitos")
    private String contactPhone;

    @Pattern(regexp = "^[AI]$", message = "El estado debe ser 'A' (activo) o 'I' (inactivo)")
    private String status;
}