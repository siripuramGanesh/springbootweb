package com.ganeshsiripuram.springbootweb.springbootweb.services;

import com.ganeshsiripuram.springbootweb.springbootweb.entity.EmployeeEntity;
import com.ganeshsiripuram.springbootweb.springbootweb.dto.EmployeeDto;

import com.ganeshsiripuram.springbootweb.springbootweb.exceptions.ResourceNotFoundException;
import com.ganeshsiripuram.springbootweb.springbootweb.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDto> getEmployeeById(Long id){
        return employeeRepository.findById(id)
                .map(employeeEntity->modelMapper.map(employeeEntity,EmployeeDto.class));
    }

    public List<EmployeeDto> getAllEmployees(){
        List<EmployeeEntity> employeeEntities= employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity-> modelMapper.map(employeeEntity,EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto createEmployee(EmployeeDto newEmployee){
        EmployeeEntity toSaveEntity=modelMapper.map(newEmployee,EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity=employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployeeEntity,EmployeeDto.class);
    }

    public void isExistsById(Long employeeId){
        Boolean isExists= employeeRepository.existsById(employeeId);
        if(!isExists) throw new ResourceNotFoundException("Employee not found"+employeeId);
            }

    public EmployeeDto updateEmployeeById(Long employeeId,EmployeeDto employeeDto){
        isExistsById(employeeId);
        EmployeeEntity employeeEntity=modelMapper.map(employeeDto,EmployeeEntity.class);
        employeeEntity.setEmployeeId(employeeId);
        EmployeeEntity savedEmployeeEntity= employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity,EmployeeDto.class);
    }



    public boolean deleteEmployeeById(Long employeeId){
        isExistsById(employeeId);
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDto updatePartialEmployeeById(Long employeeId, Map<String,Object> updates){
        isExistsById(employeeId);
        EmployeeEntity employeeEntity=employeeRepository.findById(employeeId).get();
        updates.forEach((field,value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDto.class);
    }

}
