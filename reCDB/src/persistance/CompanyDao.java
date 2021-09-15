package persistance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

import model.CompanyModel;
import model.ComputerModel;
import service.CompanyService;
import service.ComputerService;

public class CompanyDao {
	private static CompanyDao instance = new CompanyDao(); 
	private static MysqlDataSource dataSource = null;
	private static Connection connection;
	
	private CompanyDao() {
		
	}
	
	public static CompanyDao getInstance() {
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
	
	public String getUsername() {
		return dataSource.getUser();
	}
	
	public String getPassword() {
		return dataSource.getPassword();
	}
	
	public String getUrl() {
		return dataSource.getUrl();
	}
	
	public List<CompanyModel> getCompaniesList(){
		List<CompanyModel> companiesList = new ArrayList<>();
		try(PreparedStatement pSttmnt = connection.prepareStatement("Select * From company");
				ResultSet resultSet = pSttmnt.executeQuery(); 
				){
			
			while(resultSet.next()) {
				CompanyModel company = new CompanyModel(resultSet.getInt("id"),
					 resultSet.getString("name"));
				companiesList.add(company);
			}
			
		}catch(SQLException sE) {
			
		}
		return companiesList;
	}
	
	public void closeConnection() throws SQLException {
		connection.close();
	}
	
	public int countCompanies() {
		try(PreparedStatement pSttmnt = connection.prepareStatement("Select count(*) From company");
				ResultSet resultSet = pSttmnt.executeQuery(); ){
			
			while(resultSet.next()) {
				return resultSet.getInt(1);
			}
			
		}catch(SQLException sE) {
			sE.printStackTrace();
		}
		return -1;
	}
	

}
