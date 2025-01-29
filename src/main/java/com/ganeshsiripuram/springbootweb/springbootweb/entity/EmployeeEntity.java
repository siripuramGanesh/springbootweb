package com.ganeshsiripuram.springbootweb.springbootweb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("Id")
    private Long employeeId;
    private String name;
    private Integer age;
    private String email;
    private String role;
    private Double salary;
    private LocalDate dateOfJoining;
    private Boolean isActive;

}
