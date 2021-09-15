package service;

import java.util.List;

import persistance.CompanyDao;
import model.CompanyModel;

public class CompanyService {
	
	private static CompanyService instance = new CompanyService(); 
	CompanyDao companyDao = CompanyDao.getInstance();
	
	private CompanyService() {
		
	}
	
	public static CompanyService getInstance() {
		return instance;
	}
	
	public List<CompanyModel> getCompaniesList() {
		return companyDao.getCompaniesList();
	}
	
	public int countCompanies() {
		return companyDao.countCompanies();
	}
}
