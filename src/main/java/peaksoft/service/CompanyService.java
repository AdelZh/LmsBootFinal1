package peaksoft.service;

import peaksoft.entity.Company;

import java.util.List;

public interface CompanyService {

    void SaveCompany(Company company);
    Company getCompanyById(Long id);
    void deleteCompany(Long id);
    void updateCompany(Long id, Company newCompany);
    List<Company> getAllCompany();
    List<Company> getCompanyByCountry(String locatedCountry);
    List<Company> getCompanyByName(String companyName);

    Company getCompanyById1(Long id);
    List<Company> getCompanyByParameter(String parameter, String type);

}
