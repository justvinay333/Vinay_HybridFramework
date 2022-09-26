package driverFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import applicationLayer.AdminLoginPage;
import applicationLayer.AdminLogoutPage;
import applicationLayer.EmpPage;
import utilities.ExcelFileUtil;

public class DriverScriptPOM {
	WebDriver driver;
	String inputpath= "D:\\SeleniumPrograms\\DDT_Framework\\TestInput\\Employee.xlsx";
	String outputpath = "D:\\SeleniumPrograms\\DDT_Framework\\TestOutput\\Empresults.xlsx";
	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("http://orangehrm.qedgetech.com/symfony/web/index.php/auth/login");
		AdminLoginPage login = PageFactory.initElements(driver, AdminLoginPage.class);
		login.verifyLogin("Admin", "Qedge123!@#");
	}
	@Test
	public void startTest() throws Throwable {
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int rc = xl.rowCount("EmpData");
		for (int i = 1; i<=rc; i++) {
			String para1=xl.getCellData("EmpData", i, 0);
			String para2 =xl.getCellData("EmpData", i, 1);
			String para3 =xl.getCellData("EmpData", i, 2);
			EmpPage emp = PageFactory.initElements(driver,EmpPage.class);
			boolean res = emp.verifyAddEmp(para1, para2, para3);
			if(res) {
				xl.setCelldata("EmpData", i, 3, "Pass", "outputpath");	
			}
			else {
				xl.setCelldata("EmpData", i, 3,"Fail","outputpath");
			}

	}
	}
	
	@AfterTest
	public void tearDown() {
		AdminLogoutPage logout = PageFactory.initElements(driver, AdminLogoutPage.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logout.verifyLogout();
		driver.close();
	}

			
			
			
	
		
	}
	

