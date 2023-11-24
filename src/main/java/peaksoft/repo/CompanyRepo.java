package peaksoft.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Company;


import java.util.List;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {


    List<Company> findByLocatedCountry(String locatedCountry);
    List<Company> findByCompanyName(String companyName);
}
