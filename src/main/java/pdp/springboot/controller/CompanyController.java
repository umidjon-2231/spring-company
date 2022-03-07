package pdp.springboot.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pdp.springboot.dto.Constants;
import pdp.springboot.entity.Company;
import pdp.springboot.repository.CompanyRepository;
import pdp.springboot.services.CompanyService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping( "/company")
public class CompanyController {
    @Autowired
    CompanyService service;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    Gson gson;

    @GetMapping
    public String getPage(Model model){
        model.addAttribute("current", Constants.entities.get(0));
        model.addAttribute("entities", Constants.entities);
        List<Map<String, String>> list= gson.fromJson(
                gson.toJson(companyRepository.findAll()),
                new TypeToken<List<Map<String, Object>>>() {}.getType());
        model.addAttribute("list", list);
        return "entities_page";
    }

    @GetMapping(path = "/add")
    public String addPage(Model model){
        model.addAttribute("title", "Add");
        model.addAttribute("edit", false);
        return "company-default";
    }

    @PostMapping(path = "/add")
    public String addCompany(@ModelAttribute Company company){
        service.add(company);
        return "redirect:/company";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        companyRepository.deleteById(id);
        return "redirect:/company";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Integer id, Model model){
        Company company = companyRepository.getById(id);
        model.addAttribute("company", company);
        model.addAttribute("edit", true);
        return "company-default";
    }
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Integer id,@ModelAttribute Company company){
        company.setId(id);
        companyRepository.save(company);
        return "redirect:/company";
    }

}
