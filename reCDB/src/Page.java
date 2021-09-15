
public class Page {
	
	private static Page instance = new Page();
	
	private int actualPage=1;
	private int pageCapacity=10;
	private int lastPage;
	
	private Page() {
		
	}
	
	public static Page getInstance() {
		return instance;
	}

	public int getActualPage() {
		return actualPage;
	}

	public void setActualPage(int actualPage) {
		this.actualPage = actualPage;
	}

	public int getPageCapcity() {
		return pageCapacity;
	}

	public void setPageCapcity(int pageCapcity) {
		this.pageCapacity = pageCapcity;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int rowsNumber) {
		this.lastPage = rowsNumber/pageCapacity;
	}

}
