package pdp.springboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pdp.springboot.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
