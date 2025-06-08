package com.seyedin.excelimport.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Id Form is mandatory")
    @Column(name = "id_form", nullable = false, unique = true)
    private Integer idForm;

    @NotBlank(message = "First name is mandatory")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Column(nullable = false)
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull(message = "Age is mandatory")
    @Min(value = 0, message = "Age must be positive")
    @Column(nullable = false)
    private Integer age;

    @NotBlank(message = "Department is mandatory")
    @Column(nullable = false)
    private String department;
}
