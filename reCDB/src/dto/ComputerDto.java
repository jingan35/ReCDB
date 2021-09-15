package dto;

import java.sql.Timestamp;

public class ComputerDto {
	private int id;
	private String name;
	private Timestamp indroduced;
	private Timestamp discontinued;
	private int companyId;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getIndroduced() {
		return indroduced;
	}
	public void setIndroduced(Timestamp indroduced) {
		this.indroduced = indroduced;
	}
	public Timestamp getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(Timestamp discontinued) {
		this.discontinued = discontinued;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	
	

	
}