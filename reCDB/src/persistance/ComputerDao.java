package persistance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

import model.ComputerModel;


public class ComputerDao {
	private static ComputerDao instance = new ComputerDao(); 
	private static MysqlDataSource dataSource = null;
	private static Connection connection;
	
	public static ComputerDao getInstance() {
		if(dataSource==null) {
			try {
				dataSource = new MysqlDataSource();
				FileInputStream fis = new FileInputStream("resources/db.properties");
				Properties prop = new Properties();
				prop.load(fis);
				dataSource.setUser(prop.getProperty("jdbc.username"));
				dataSource.setPassword(prop.getProperty("jdbc.password"));
				dataSource.setUrl(prop.getProperty("jdbc.url"));
				connection = dataSource.getConnection();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	public List<ComputerModel> getComputersList(){
		List<ComputerModel> computersList = new ArrayList<>();
		try(PreparedStatement pSttmnt = connection.prepareStatement("Select * From computer");
				ResultSet resultSet = pSttmnt.executeQuery(); ){
			
			while(resultSet.next()) {
				ComputerModel company = new ComputerModel(resultSet.getInt("id"),
					 resultSet.getString("name"), resultSet.getTimestamp("introduced"), 
					 resultSet.getTimestamp("discontinued"), resultSet.getInt("company_id"));
				computersList.add(company);
			}
			
		}catch(SQLException sE) {
			
		}
		return computersList;
	}
	
	public int addComputer(String name, Timestamp introduced, Timestamp discontinued,
			int company_id) {
		int rowNumber=0;
		try(PreparedStatement pSttmnt = 
				connection.prepareStatement("INSERT INTO "
						+ "computer(name, introduced, discontinued, company_id) VALUES( ?, ?, ?, ? )");
				){
			pSttmnt.setString(1,name);
			pSttmnt.setTimestamp(2, introduced);
			pSttmnt.setTimestamp(3, discontinued);
			pSttmnt.setInt(4, company_id);
			rowNumber = pSttmnt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
		return rowNumber;
	}
	
	public int updateComputer(int id, String name, Timestamp introduced,
			Timestamp discontinued, int company_id) {
		int rowNumber=0;
		try(PreparedStatement pSttmnt = 
				connection.prepareStatement("UPDATE computer"
						+ " SET name=?, introduced=?, discontinued=?, company_id=? where id=?");
				){
			pSttmnt.setString(1,name);
			pSttmnt.setTimestamp(2, introduced);
			pSttmnt.setTimestamp(3, discontinued);
			pSttmnt.setInt(4, company_id);
			pSttmnt.setInt(5, id);
			rowNumber = pSttmnt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
		return rowNumber;
	}
	
	public int deleteComputer(int id) {
		int rowNumber=0;
		try(PreparedStatement pSttmnt = 
				connection.prepareStatement("DELETE from computer"
						+ " where id=?");
				){
			pSttmnt.setInt(1, id);
			rowNumber = pSttmnt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		
		return rowNumber;
	}
	
	public int countComputers() {
		try(PreparedStatement pSttmnt = connection.prepareStatement("Select count(*) From computer");
				ResultSet resultSet = pSttmnt.executeQuery(); ){
			
			while(resultSet.next()) {
				return resultSet.getInt(1);
			}
			
		}catch(SQLException sE) {
			sE.printStackTrace();
		}
		return -1;
	}
	
	public List<ComputerModel> getComputersListPaged(int Page, int PageCapacity){
		List<ComputerModel> computersList = new ArrayList<>();
		try(PreparedStatement pSttmnt = connection.prepareStatement("Select * From computer LIMIT ?,?");
				){
			
			pSttmnt.setInt(1, (Page-1)*PageCapacity);
			pSttmnt.setInt(2, PageCapacity);
			ResultSet resultSet = pSttmnt.executeQuery(); 
			
			while(resultSet.next()) {
				ComputerModel company = new ComputerModel(resultSet.getInt("id"),
					 resultSet.getString("name"), resultSet.getTimestamp("introduced"), 
					 resultSet.getTimestamp("discontinued"), resultSet.getInt("company_id"));
				computersList.add(company);
			}
			
		}catch(SQLException sE) {
			
		}
		return computersList;
	}
	
	public List<ComputerModel> getComputersListOfLastPage(int pageCapacity){
		List<ComputerModel> computersList = null;
		try(PreparedStatement pSttmnt = connection.prepareStatement("Select * From computer LIMIT ?,?");
				){
			
			int rowsNumber = countComputers();
			int pageNumbers = 0;
			if(rowsNumber>pageCapacity) {
				pageNumbers = rowsNumber/pageCapacity;
				if(rowsNumber%pageCapacity>0);
					pageNumbers+=1;
				pSttmnt.setInt(1, (pageNumbers-1)*pageCapacity);
			}
			else
				pSttmnt.setInt(1, 0);
			pSttmnt.setInt(2, pageCapacity);
			ResultSet resultSet = pSttmnt.executeQuery(); 
			computersList = resultsetOfComputersToAList(resultSet);	
		}catch(SQLException sE) {
			
		}
		return computersList;
	}
	
	List<ComputerModel> resultsetOfComputersToAList(ResultSet resultSet){
		List<ComputerModel> computersList = new ArrayList<>();
		try {
			while(resultSet.next()) {
				ComputerModel company = new ComputerModel(resultSet.getInt("id"),
					 resultSet.getString("name"), resultSet.getTimestamp("introduced"), 
					 resultSet.getTimestamp("discontinued"), resultSet.getInt("company_id"));
				computersList.add(company);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computersList;
	}
	

}
