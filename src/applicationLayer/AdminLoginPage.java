package applicationLayer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLoginPage {
	@FindBy(name="txtUsername")
	WebElement ObjUser;
	@FindBy(xpath ="//input[@id='txtPassword']")
	WebElement ObjPass;
	@FindBy(id="btnLogin")
	WebElement ObjLogin;
	//write method for Login
	public void verifyLogin(String userName, String Password) {
		ObjUser.sendKeys(userName);
		ObjPass.sendKeys(Password);
		ObjLogin.click();
	}
	

}
