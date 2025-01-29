package com.ganeshsiripuram.springbootweb.springbootweb.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.ganeshsiripuram.springbootweb.springbootweb.annotations.EmployeeRoleValidation;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long employeeId;
    @NotNull(message="required field name")
    @NotEmpty(message="it should not be empty")
    @NotBlank(message="it should not be blank")
    @Size(min=3,max=10,message="range btw 3 to 10")
    private String name;
    @Max(value=80)
    @Min(value=18,message="min should be 18")
    private Integer age;
    @Email(message="should be a valid email")
    private String email;
    @NotBlank(message="role of employee cannnot be blank")
    //@Pattern(regexp = " ^(ADMIN|USER)$",message="role of employee can be user or admin")
    @EmployeeRoleValidation
    private String role;
    @NotNull(message="salary should not be null") @Positive(message="salary must be positive")
    @Digits(integer=6,fraction=2,message="should be in xxxxxx.yy")
    @DecimalMax(value="100000.99")
    @DecimalMin(value="100.50")
    private Double salary;
    @Past(message="date of joining should be past only")
    private LocalDate dateOfJoining;
    @AssertTrue(message="employee should be active")
    @JsonProperty("isActive")
    private Boolean isActive;
}
