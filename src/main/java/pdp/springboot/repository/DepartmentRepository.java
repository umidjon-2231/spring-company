package pdp.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.springboot.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
