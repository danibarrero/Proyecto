package org.iesvdm.proyecto.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar en blanco")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción no puede estar en blanco")
    @Size(max = 250, message = "La descripción no puede tener más de 250 caracteres")
    private String descripcion;

    @NotBlank(message = "El país no puede estar en blanco")
    private String pais;

    @NotNull(message = "La ciudad no puede ser nula")
    @Size(max = 100, message = "La ciudad no puede tener más de 100 caracteres")
    private String ciudad;

    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que cero")
    private Double precio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @OneToMany(mappedBy = "actividad", fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Comentario> comentarios = new HashSet<>();

    @OneToMany(mappedBy = "actividad")
    @JsonIgnore
    @Builder.Default
    private Set<Inscripcion> inscripciones = new HashSet<>();
}