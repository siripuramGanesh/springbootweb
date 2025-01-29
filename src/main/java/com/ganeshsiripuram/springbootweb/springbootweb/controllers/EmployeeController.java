package com.ganeshsiripuram.springbootweb.springbootweb.controllers;

import com.ganeshsiripuram.springbootweb.springbootweb.dto.EmployeeDto;
import com.ganeshsiripuram.springbootweb.springbootweb.entity.EmployeeEntity;
import com.ganeshsiripuram.springbootweb.springbootweb.exceptions.ResourceNotFoundException;
import com.ganeshsiripuram.springbootweb.springbootweb.repositories.EmployeeRepository;
import com.ganeshsiripuram.springbootweb.springbootweb.services.EmployeeService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<String> handleEmployeeNotFound(ResourceNotFoundException exception){
//        return new ResponseEntity<>("Employee not found",HttpStatus.NOT_FOUND);
//    }


    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name= "employeeId") Long Id){
        Optional<EmployeeDto> employeeDto=employeeService.getEmployeeById(Id);
        return employeeDto
                .map(employeeDto1->ResponseEntity.ok(employeeDto1))
                .orElseThrow(()-> new ResourceNotFoundException("Employee Not Found with Id"+Id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(@RequestParam(required = false,name="inputAge") Long age){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody @Valid EmployeeDto newEmployee){
        EmployeeDto savedEmployee=employeeService.createEmployee(newEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);

    }

    @PutMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@RequestBody EmployeeDto employeeDto,@PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId,employeeDto));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
       boolean gotDeleted=employeeService.deleteEmployeeById(employeeId);
       if(gotDeleted) return ResponseEntity.ok(true);
       return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updatePartialEmployeeById(@RequestBody Map<String,Object> updates,@PathVariable Long employeeId){
        EmployeeDto employeeDto=employeeService.updatePartialEmployeeById(employeeId,updates);
        if(employeeDto==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDto);
    }


}
