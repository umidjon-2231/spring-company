package pdp.springboot.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pdp.springboot.dto.Constants;
import pdp.springboot.dto.DepartmentDTO;
import pdp.springboot.entity.Department;
import pdp.springboot.repository.CompanyRepository;
import pdp.springboot.repository.DepartmentRepository;
import pdp.springboot.services.DepartmentService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/department")

public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;

    @GetMapping
    public String getPage(Model model){
        Gson gson=new Gson();
        model.addAttribute("current", Constants.entities.get(1));
        model.addAttribute("entities", Constants.entities);
        List<Map<String, String>> list= gson.fromJson(
                gson.toJson(departmentRepository.findAll()),
                new TypeToken<List<Map<String, Object>>>() {}.getType());
        model.addAttribute("list", list);
        return "entities_page";
    }

    @GetMapping("/add")
    public String getSaveDepartment(Model model) {
        model.addAttribute("companyList", companyRepository.findAll());
        model.addAttribute("edit", false);
        return "department-default";
    }

    @PostMapping("/add")
    public String saveDepartment(Model model, @ModelAttribute DepartmentDTO dto) {
        departmentService.add(dto);
        return "redirect:/department";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        departmentRepository.deleteById(id);
        return "redirect:/department";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model){
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isEmpty()) {
            return "redirect:/department";
        }
        model.addAttribute("department", department.get());
        model.addAttribute("edit", true);
        model.addAttribute("companyList", companyRepository.findAll());
        return "department-default";
    }
    @PostMapping("/edit/{id}")
    public String editEmployee(@PathVariable Integer id, @ModelAttribute DepartmentDTO departmentDTO){
        departmentService.edit(id, departmentDTO);
        return "redirect:/department";
    }
}
