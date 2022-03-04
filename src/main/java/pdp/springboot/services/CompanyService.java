package pdp.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.springboot.dto.ApiResponse;
import pdp.springboot.entity.Company;
import pdp.springboot.repository.CompanyRepository;

@Service
public class CompanyService{
    @Autowired
    CompanyRepository companyRepository;

    public ApiResponse add(Company company){
        companyRepository.save(company);
        return new ApiResponse("Success add!", true);
    }
}
