package peaksoft.service.impl;

import jakarta.persistence.EntityNotFoundException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;

import peaksoft.entity.Company;
import peaksoft.repo.CompanyRepo;
import peaksoft.service.CompanyService;

import java.util.Collections;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepo companyRepo;


    @Override
    public void SaveCompany(Company company) {
        companyRepo.save(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepo.deleteById(id);
    }

    @Override
    public void updateCompany(Long id, Company newCompany) {
        Company company = companyRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        company.setCompanyName(newCompany.getCompanyName());
        company.setLocatedCountry(newCompany.getLocatedCountry());
        companyRepo.save(company);
    }

    @Override
    public List<Company> getAllCompany() {
        return companyRepo.findAll();
    }


    @Override
    public List<Company> getCompanyByCountry(String locatedCountry) {
        return companyRepo.findByLocatedCountry(locatedCountry);
    }

    @Override
    public List<Company> getCompanyByName(String companyName) {
        return companyRepo.findByCompanyName(companyName);
    }

    @Override
    public Company getCompanyById1(Long id) {
        List<Company> company = getCompanyByParameter(String.valueOf(id), "id");

        if (!company.isEmpty()) {
            return company.get(0);
        } else {
            throw new EntityNotFoundException("Agency page not found");
        }
    }

    @Override
    public List<Company> getCompanyByParameter(String parameter, String type) {
        return switch (type) {
            case "companyName" -> companyRepo.findByCompanyName(parameter);
            case "locatedCountry" -> companyRepo.findByLocatedCountry(parameter);
            case "id" -> Collections.singletonList(companyRepo.findById(Long.parseLong(parameter))
                    .orElseThrow(() -> new EntityNotFoundException("Agency page not found")));
            default -> throw new IllegalArgumentException("Invalid search type: " + type);
        };
    }
}