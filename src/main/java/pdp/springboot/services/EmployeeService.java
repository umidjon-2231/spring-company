package pdp.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.springboot.dto.ApiResponse;
import pdp.springboot.dto.EmployeeDTO;
import pdp.springboot.entity.Department;
import pdp.springboot.entity.Employee;
import pdp.springboot.repository.DepartmentRepository;
import pdp.springboot.repository.EmployeeRepository;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public ApiResponse add(EmployeeDTO dto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(dto.getDepartmentId());
        if (optionalDepartment.isEmpty()) return new ApiResponse("Bunday department yoq", false);
        Department department = optionalDepartment.get();

        Employee employee = new Employee();
        employee.setFull_name(dto.getFull_name());
        employee.setDepartment(department);

        Employee save = employeeRepository.save(employee);
        return new ApiResponse("Saved", true, save);
    }
}
