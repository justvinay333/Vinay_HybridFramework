package applicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class EmpPage {
	WebDriver driver;
	//constructor to override the webdriver methods
	public EmpPage(WebDriver driver) 
	{
	this.driver=driver;
	}
	@FindBy(linkText = "PIM")
	WebElement clickPim;
	@FindBy(name = "btnAdd")
	WebElement clickAdd;
	@FindBy(name="firstName")
	WebElement Fname;
	@FindBy(name="middleName")
	WebElement Mname;
	@FindBy(name="lastName")
	WebElement Lname;
	@FindBy(name="employeeId")
	WebElement BeforeEid;
	@FindBy(id="btnSave")
	WebElement clickSave;
	@FindBy(name="personal[txtEmployeeId]")
	WebElement AfterEid;
	public boolean verifyAddEmp(String Fname, String Mname, String Lname) {
		Actions ac= new Actions(driver);
		ac.moveToElement(this.clickPim).click().perform();
		ac.moveToElement(this.clickAdd).click().perform();
		this.Fname.sendKeys(Fname);
		this.Fname.sendKeys(Lname);
		this.Fname.sendKeys(Mname);
		String expectedID = this.BeforeEid.getAttribute("value");
		ac.moveToElement(this.clickSave).click().perform();
		String actualEid = this.AfterEid.getAttribute("value");
		if(expectedID.equals(actualEid)) {
			Reporter.log("Emp created"+ expectedID +"   "+actualEid,true);
			return true;
			
		}
		else {
		Reporter.log("Emp creation failed"+expectedID+"  "+actualEid,true);
		return false;
		
	}
	
	
	
	}

}
