package com.hcl.wallet.dao.impl;

import com.hcl.wallet.dao.EmployeeDao;
import com.hcl.wallet.exception.EmployeeNotFound;
import com.hcl.wallet.model.Employee;
import com.hcl.wallet.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveData(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getDataById(int empId) {
        return employeeRepository.findById(empId).orElseThrow(() -> new EmployeeNotFound("Employee Not Found !!!!"));
    }

    @Override
    public List<Employee> getAllData() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteEmployeeById(int empId) {
        employeeRepository.deleteById(empId);
    }
}