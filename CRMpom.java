package Library.CRM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CRMpom 
{
	public WebDriver driver;
	public WebDriverWait wait;

	public CRMpom(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait (driver,5);
	}

	///////////////////////Login///////////////////////////////
	
	@FindBy(id="user_name")
		private WebElement userName;
	
	@FindBy(id="username_password")
		private WebElement password;
	
	@FindBy(id="bigbutton")
		private WebElement loginBtn; 
	
	@FindBy (xpath="//*[@id='tab0']")
		private WebElement crmDashboard;
	
	//enter user ID
	public void sendUserName(String uName) {
		this.userName.clear();
		this.userName.sendKeys(uName);
	}
	
	//enter password
	public void sendPassword(String pwd) {
		this.password.clear(); 
		this.password.sendKeys(pwd); 
	}
	
	//click login button
	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}
	
	//check if login was successful
	public String confirmLogIn() {
		boolean loggedIn = this.crmDashboard.isDisplayed();
		if (loggedIn == true) {
			return "Login successful";
		}
		else {
			return "login failed";
		}
		
	}
	
	///////////////////////MenuBar////////////////////////////
	
	@FindBy (xpath="//div[@class='container-fluid']")
			private WebElement navigMenu;
	
	//check navigation menu bar color
	public String navigMenuColor() {
		String color = navigMenu.getCssValue("color");
		return color;
	}
	
	///////////////////////Activities MenuBar////////////////////////////
	
	@FindBy (id="grouptab_3")
		private WebElement activitiesMenu;

	//check if activities menu is clickable
	public boolean actMenu() {
		Assert.assertTrue(activitiesMenu.isDisplayed());
		try {
			wait.until(ExpectedConditions.elementToBeClickable(activitiesMenu));
			return true;
		}
		catch (Exception e){
			return false;
		}
	}
	
	///////////////////////Sales MenuBar////////////////////////////
	
	@FindBy (id="grouptab_0")
		private WebElement salesMenu;
	
	@FindBy (className="module-title-text")
		private WebElement pageTitle;
	
	///////////////////////Sales MenuBar -> Leads////////////////////////////
	
	@FindBy (id="moduleTab_9_Leads")
		private WebElement leadsSubMenu;
	
	@FindBy (xpath="/html[1]/body[1]/div[4]/div[1]/div[3]/form[2]/div[3]/table[1]/tbody[1]/tr[1]/td[10]/span[1]/span[1]")
		private WebElement additionalDetails;
	
	@FindBy (css="span[class=\'phone\']")
		private WebElement phoneNumber;

	//goto sales -> leads menu bar
	public void accessLeadsSubMenu(){
		driver.get("https://alchemy.hguy.co/crm/index.php?action=ajaxui#ajaxUILoc=index.php%3Fmodule%3DLeads%26action%3Dindex%26parentTab%3DSales");
//		Actions act = new Actions(driver);
//		act.moveToElement(salesMenu)
//			.click()
//			.build()
//			.perform();
//		act.moveToElement(leadsSubMenu)
//			.click()
//			.build()
//			.perform();
	}
	
	//get and print the phone number from leads sub menu
	public void checkPhoneNumber() {
		wait.until(ExpectedConditions.visibilityOf(pageTitle));
		additionalDetails.click();
		
		wait.until(ExpectedConditions.visibilityOf(phoneNumber));
		System.out.println(phoneNumber.getText());
	}
	
	//get and print the names & user names from leads sub menu
	public void getNamesUsers() {
		wait.until(ExpectedConditions.visibilityOf(pageTitle));
		System.out.println("Names and UserNames from first 10 rows are:");
		for (int i=1; i<=10; i++) {
			System.out.print("Row "+ i + ": Name is ");
			System.out.print(driver.findElement(By.xpath("//*[@id='MassUpdate']/div[3]/table/tbody/tr["+i+"]/td[3]/b/a")).getText());
			System.out.print(", Username is ");
			System.out.print(driver.findElement(By.xpath("//*[@id='MassUpdate']/div[3]/table/tbody/tr["+i+"]/td[8]/a")).getText());
			System.out.println();
		}
	}
	
	///////////////////////Sales MenuBar -> Accounts////////////////////////////
	
	@FindBy (id="moduleTab_9_Accounts")
		private WebElement accountsSubMenu;
	
	//goto sales -> accounts menu bar
	public void accessAccountsSubMenu(){
		//driver.get("https://alchemy.hguy.co/crm/index.php?action=ajaxui#ajaxUILoc=index.php%3Fmodule%3DAccounts%26action%3Dindex%26parentTab%3DSales");
		Actions act = new Actions(driver);
		act.moveToElement(salesMenu)
			.click()
			.build()
			.perform();
		act.moveToElement(accountsSubMenu)
			.click()
			.build()
			.perform();
	}
	
	//get and print the names from accounts sub menu
	public void getNames() {
		wait.until(ExpectedConditions.visibilityOf(pageTitle));
		System.out.println("Names from first five odd numbered rows are:");
		for (int i=1; i<10; i++) {
			if (i%2 !=0) {
				System.out.println(driver.findElement(By.xpath("//tr["+i+"]//td[3]//b[1]//a[1]")).getText());
			}
		}
	}
	
}
