package pdp.springboot.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.springboot.dto.ApiResponse;
import pdp.springboot.dto.DepartmentDTO;
import pdp.springboot.entity.Company;
import pdp.springboot.entity.Department;
import pdp.springboot.repository.CompanyRepository;
import pdp.springboot.repository.DepartmentRepository;

import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;


    public ApiResponse add(DepartmentDTO departmentDTO) {
        Optional<Company> optionalCompany = companyRepository.findById(departmentDTO.getCompanyId());
        if (optionalCompany.isEmpty()) return new ApiResponse("Bunday id yoq", false);
        Company company = optionalCompany.get();

        Department department = new Department();
        department.setName(departmentDTO.getName());
        department.setCompany(company);

        Department save = departmentRepository.save(department);
        return new ApiResponse("Saved", true, save);
    }

    public ApiResponse edit(Integer id, DepartmentDTO departmentDTO){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if(optionalDepartment.isEmpty())return new ApiResponse("department not found",false);
        Department department=optionalDepartment.get();
        Optional<Company> company = companyRepository.findById(departmentDTO.getCompanyId());
        if(company.isEmpty()){
            return new ApiResponse("company not found", false);
        }
        department.setCompany(company.get());
        department.setName(departmentDTO.getName());
        departmentRepository.save(department);
        return new ApiResponse("Department saved!", true);
    }
}
