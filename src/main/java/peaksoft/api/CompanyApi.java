package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import peaksoft.entity.Company;
import peaksoft.service.CompanyService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyApi {

    private final CompanyService companyService;


    @GetMapping
    public String getAllCompanies(Model model) {
        model.addAttribute("getAllCompany", companyService.getAllCompany());
        return "myCompany/mainPage";
    }


    @GetMapping("/get")
    public String searchAgency(
            @RequestParam String parameter,
            @RequestParam String type,
            Model model
    ) {
        List<Company> agency = companyService.getCompanyByParameter(parameter, type);
        model.addAttribute("getCompanyById", agency);
        return "myCompany/getProfile";
    }


    @GetMapping("/{getById}/get")
    public String companyProfile(@PathVariable("getById") Long companyID, Model model) {
        model.addAttribute("getCompanyById1", companyService.getCompanyById(companyID));
        return "myCompany/getById";
    }


    @GetMapping("/create")
    public String createCompany(Model model) {
        model.addAttribute("newCompany", new Company());
        return "myCompany/createCompany";
    }

    @PostMapping("/save")
    public String saveCompany(@ModelAttribute("newCompany") Company company) {
        companyService.SaveCompany(company);
        return "redirect:/company";
    }

    @DeleteMapping("{companyId}/delete")
    public String deleteCompany(@PathVariable("companyId") Long id) {
        companyService.deleteCompany(id);
        return "redirect:/company";
    }


    @GetMapping("/{myId}/update")
    public String update(@PathVariable("myId") Long id, Model model) {
        try {
            Company companyToUpdate = companyService.getCompanyById(id);
            model.addAttribute("companyToUpdate", companyToUpdate);
        } catch (HttpClientErrorException.NotFound e) {
            return e.getMessage();
        }
        return "myCompany/update-company";
    }


    @PutMapping("{idToUpdate}/merge")
    public String mergeLesson(
            @PathVariable("idToUpdate") Long id,
            @ModelAttribute("companyToUpdate") Company company) {
        try {
            companyService.updateCompany(id, company);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/company";
    }

}