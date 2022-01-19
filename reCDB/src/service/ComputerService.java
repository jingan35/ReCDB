package service;
import java.sql.Timestamp;
import java.util.List;

import model.ComputerModel;
import persistance.ComputerDao;

public class ComputerService {
	
	private static ComputerService instance = new ComputerService(); 
	private ComputerDao computerDao = ComputerDao.getInstance();
	
	private ComputerService() {
		
	}
	
	public static ComputerService getInstance() {
		return instance;
	}
	
	public List<ComputerModel> getComputersList(){
		return computerDao.getComputersList();
	}
	
	public int addComputer(String name, Timestamp introduced,
			Timestamp discontinued, int company_id) {
		return computerDao.addComputer(name, introduced, 
				discontinued, company_id);
	}
	
	public int updateComputer(int id, String name, Timestamp introduced, 
			Timestamp discontinued, int company_id) {
		return computerDao.updateComputer(id, name, introduced, discontinued,
				company_id);
	}
	
	public int deleteComputer(int id) {
		return computerDao.deleteComputer(id);
	}
	
	public int countComputers() {
		return computerDao.countComputers();
	}
	
	public List<ComputerModel> getComputersListPaged(int Page, int PageCapacity){
		return computerDao.getComputersListPaged(Page, PageCapacity);
	}
	
	public List<ComputerModel> getComputersListOfLastPage(int PageCapacity){
		return computerDao.getComputersListOfLastPage(PageCapacity);
	} 

}
