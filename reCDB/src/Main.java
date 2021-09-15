import java.util.List;

import model.CompanyModel;
import model.ComputerModel;
import service.CompanyService;
import service.ComputerService;

public class Main {

	public static void main(String[] args) {
		
		Page page = Page.getInstance();
		
		/*System.out.println("user: " + cd.getUsername()+ " url: " + cd.getUrl() +
		" password: "+ cd.getPassword());*/
		CompanyService companyService = CompanyService.getInstance();
		/*List<CompanyModel> companiesList = companyService.getCompaniesList();
		for(CompanyModel cm: companiesList) {
			System.out.println(cm.getId()+" "+cm.getName());
		}*/
		
		/*ComputerService computerService = ComputerService.getInstance();
		List<ComputerModel> computersList = computerService.getComputersList();
		for(ComputerModel cm: computersList) {
			System.out.println(cm.getId()+" "+cm.getName());
		}*/
		
		//computerService.addComputer("testadd", null, null, 1);
		//computerService.updateComputer(577, "testUp", null, null, 2);
		//computerService.deleteComputer(577);
		System.out.print(companyService.countCompanies());

	}

}
