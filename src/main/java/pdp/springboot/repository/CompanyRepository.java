package pdp.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.springboot.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
