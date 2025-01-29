package com.ganeshsiripuram.springbootweb.springbootweb.repositories;

import com.ganeshsiripuram.springbootweb.springbootweb.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {



}
