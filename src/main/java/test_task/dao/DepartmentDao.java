package test_task.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test_task.model.Department;

import java.util.List;

@Repository
public interface DepartmentDao extends CrudRepository<Department, Long> {
    //TODO Get a list of department IDS where the number of employees doesn't exceed 3 people
    @Query(value = "SELECT Department.id FROM Employee INNER JOIN Department ON Department.id = Employee.department_id GROUP BY Department.id HAVING COUNT(*) > 3",
            nativeQuery = true)
    List<Long> findAllWhereDepartmentDoesntExceedThreePeople();

    //TODO Get a list of departments IDs with the maximum total salary of employees
    @Query(value = "SELECT Department.id FROM Employee INNER JOIN Department ON Department.id = Employee.department_id GROUP BY Department.id ORDER BY SUM(Employee.salary) DESC LIMIT 1",
            nativeQuery = true)
    List<Long> findAllByMaxTotalSalary();
}
