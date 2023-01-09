package tests;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import pages.TaskPage;
import pages.UserLogin;

public class DataEntryTest extends TestBase{
	
	public DataEntryTest (String url) {
		super();
		this.url = "internal";
	}
	UserLogin userlogin;
	TaskPage taskpage;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	@Test 
	public void LoginSuccessfullydataentry() throws InterruptedException
	{
		userlogin = new UserLogin(driver);	
		userlogin.userlogin("LCE2@ads.infath.sa", "Infath@1234");
	}
	@Test (dependsOnMethods = {"LoginSuccessfullydataentry"} )
	public void DataEntry() throws InterruptedException, AWTException
	{
		taskpage = new TaskPage(driver);
		taskpage.opentask();
 		taskpage.TaskDetails("AutomatedAssetsTasks","711","127","0512345678","SA0410000079451000000207","1","2","2");
 		for(int i=1;i<=10;i++)
		{
			if(i>=9)
			{
				for(int j=1;j<=2;j++) {
				taskpage.nextpage();
		 		taskpage.addassetsentry("(//div[@class='display-flex align-items-flex-start']/div[text()='Asset Number'])["+j+"]","Schema", "10", "90", "10", "20", "20", "10", "100", "90", "Mai Owner", "1234567801", "Mai", "06-30-2000", "100");
				}
				break;
			}
			else {
	 		taskpage.addassetsentry("(//div[@class='display-flex align-items-flex-start']/div[text()='Asset Number'])["+i+"]","Schema", "10", "90", "10", "20", "20", "10", "100", "90", "Mai Owner", "1234567801", "Mai", "06-30-2000", "100");
			}
		}
		taskpage.sendforinitialapproval();

	}
	@Test (dependsOnMethods = {"DataEntry"})
	public void logout() throws InterruptedException
	{
		WebElement logoutbutton = driver.findElement(By.id("b2-b3-Icon3"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", logoutbutton);
		System.out.println(driver.getCurrentUrl());
	}
	
}