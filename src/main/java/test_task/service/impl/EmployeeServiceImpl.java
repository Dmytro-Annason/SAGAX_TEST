package test_task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test_task.dao.EmployeeDao;
import test_task.model.Employee;
import test_task.service.EmployeeService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public List<Employee> findAll() {
        return (List<Employee>) employeeDao.findAll();
    }

    @Override
    public List<Employee> findAllBySalaryGreaterThatBoss() {
        return employeeDao.findAllWhereSalaryGreaterThatBoss();
    }

    @Override
    public List<Employee> findAllByMaxSalary() {
        return employeeDao.findAllByMaxSalary();
    }

    @Override
    public List<Employee> findAllWithoutBoss() {
        return employeeDao.findAllWithoutBoss();
    }

    @Override
    public Long fireEmployee(String name) {
        Iterable<Employee> employees = employeeDao.findAll();

        Long result = -1L;
        for(Employee employee:employees){
            if(employee.getName().equals(name)){
                result=employee.getId();
                employeeDao.delete(employee);
                break;
            }
        }
        employeeDao.saveAll(employees);
        return result;
    }

    @Override
    public Long changeSalary(String name) {
        Iterable<Employee> employees = employeeDao.findAll();

        Long result = -1L;
        for(Employee employee:employees){
            if(employee.getName().equals(name)){
                result=employee.getId();
                employee.setSalary(new BigDecimal(12345));
                break;
            }
        }

        employeeDao.saveAll(employees);
        return result;
    }

    @Override
    public Long hireEmployee(Employee employee) {
        employeeDao.save(employee);
        return employee.getId();
    }
}
