package pdp.springboot.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pdp.springboot.dto.Constants;
import pdp.springboot.dto.EmployeeDTO;
import pdp.springboot.entity.Department;
import pdp.springboot.entity.Employee;
import pdp.springboot.repository.DepartmentRepository;
import pdp.springboot.repository.EmployeeRepository;
import pdp.springboot.services.EmployeeService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/employee")

public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping
    public String getPage(Model model){
        Gson gson=new Gson();
        model.addAttribute("current", Constants.entities.get(2));
        model.addAttribute("entities", Constants.entities);
        List<Map<String, String>> list= gson.fromJson(
                gson.toJson(employeeRepository.findAll()),
                new TypeToken<List<Map<String, Object>>>() {}.getType());
        model.addAttribute("list", list);
        return "entities_page";
    }
    @GetMapping("/add")
    public String getSaveDepartment(Model model) {
        model.addAttribute("departmentList", departmentRepository.findAll());
        model.addAttribute("edit", false);
        return "employee-default";
    }

    @PostMapping("/add")
    public String saveDepartment(Model model, @ModelAttribute EmployeeDTO dto) {
        employeeService.add(dto);
        return "redirect:/employee";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        employeeRepository.deleteById(id);
        return "redirect:/employee";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model){
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            return "redirect:/employee";
        }
        model.addAttribute("employee", employee.get());
        model.addAttribute("edit", true);
        model.addAttribute("departmentList", departmentRepository.findAll());
        return "employee-default";
    }
    @PostMapping("/edit/{id}")
    public String editEmployee(@PathVariable Integer id, @ModelAttribute EmployeeDTO employeeDTO){
        Employee employee=new Employee();
        Optional<Department> department = departmentRepository.findById(employeeDTO.getDepartmentId());
        if(department.isEmpty()){
            return "redirect:/employee";
        }
        employee.setId(id);
        employee.setDepartment(department.get());
        employee.setFull_name(employeeDTO.getFull_name());
        employeeRepository.save(employee);
        return "redirect:/employee";
    }
}
