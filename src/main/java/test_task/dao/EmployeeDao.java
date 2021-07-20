package test_task.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test_task.model.Employee;

import java.util.List;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Long> {

    //TODO Get a list of employees receiving a salary greater than that of the boss  WHERE boss IS NOT NULL AND emp.salary > boss.salary
    @Query(
            value = "SELECT * FROM Employee emp LEFT JOIN Employee boss on boss.id = emp.boss_id WHERE emp.salary > boss.salary",
            nativeQuery = true)
    List<Employee> findAllWhereSalaryGreaterThatBoss();

    //TODO Get a list of employees receiving the maximum salary in their department
    @Query(value = "SELECT * FROM Employee WHERE (department_id, salary) in (SELECT department_id, MAX(salary) FROM Employee GROUP BY department_id)",
            nativeQuery = true)
    List<Employee> findAllByMaxSalary();

    //TODO Get a list of employees who do not have boss in the same department
    @Query(
            value = "SELECT * FROM Employee emp LEFT JOIN Employee boss on boss.id = emp.boss_id WHERE boss IS NULL",
            nativeQuery = true)
    List<Employee> findAllWithoutBoss();
}
