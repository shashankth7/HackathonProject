package BaseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Pages.MainPage;
import Utilities.DateUtil;
import Utilities.ExtentReportManager;

public class BaseUi {
	public WebDriver driver;
	public Properties prop;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;

	public void invokeBrowser(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\Resources\\Drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\Resources\\Drivers\\geckodriver.exe");
				driver = new FirefoxDriver();

			} else {
				System.setProperty("webdriver.edge.driver",
						System.getProperty("user.dir") + "\\Resources\\Drivers\\msedgedriver.exe");
				driver = new EdgeDriver();
			}
			reportPass("Browser invoked is : "+browserName);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream file = new FileInputStream(
						System.getProperty("user.dir") + "\\Resources\\Repositiries\\Config.properties");
				prop.load(file);
			} catch (Exception e) {
				 reportFail(e.getMessage());
			}
		}
	}

	public MainPage openApplication(String url) {
		try {
			driver.get(url);
			reportPass("website opened with url : "+ url);
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}
		
		return PageFactory.initElements(driver, MainPage.class);

	}

	
	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	public void takeScreenShot() {
		TakesScreenshot ss = (TakesScreenshot) driver;
		File src = ss.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + DateUtil.getTimeStamp() + ".png");

		try {
			FileUtils.copyFile(src, dest);
			logger.addScreenCaptureFromPath(System.getProperty("user.dir") + DateUtil.getTimeStamp() + ".png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShot();
		Assert.fail();
	}
	
	public String[][] getExcelData(String fileName, String sheetName,int totalNoOfRows,int totalNoOfCols) {
		String[][] arrayExcelData = null;
		try {
			FileInputStream fs = new FileInputStream(fileName);
			@SuppressWarnings("resource")
			XSSFWorkbook wb = new XSSFWorkbook(fs);
			XSSFSheet sh = wb.getSheet(sheetName);

			
			arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols-1];
			
			for (int i= 1 ; i < totalNoOfRows; i++) {

				for (int j=1; j < totalNoOfCols; j++) {
					arrayExcelData[i-1][j-1] =String.valueOf(sh.getRow(i).getCell(j));;
				}

			}
			fs.close();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			e.printStackTrace();
		} 
		
		return arrayExcelData;
	}
	

	public void ExplicitWait(String LocatorValue) {
		WebDriverWait wait=new WebDriverWait(driver, 20);
			if(LocatorValue.endsWith("_Id")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorValue)));
			}
			else if(LocatorValue.endsWith("_XPath")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));
			}
			else if(LocatorValue.endsWith("_ClassName")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(LocatorValue)));
			}
			else if(LocatorValue.endsWith("_TagName")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(LocatorValue)));
			}
			else if(LocatorValue.endsWith("_LinkText")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(LocatorValue)));
			}

		
	}
	
	public void clickElement(String LocatorValue) {
		
			if(LocatorValue.endsWith("_Id")) {
				driver.findElement(By.id(LocatorValue)).click();
			}
			else if(LocatorValue.endsWith("_XPath")) {
				driver.findElement(By.xpath(LocatorValue)).click();
			}
			else if(LocatorValue.endsWith("_ClassName")) {
				driver.findElement(By.className(LocatorValue)).click();
			}
			else if(LocatorValue.endsWith("_TagName")) {
				driver.findElement(By.tagName(LocatorValue)).click();
			}
			else if(LocatorValue.endsWith("_LinkText")) {
				driver.findElement(By.linkText(LocatorValue)).click();
			}
		
	}
	
	public void clearField(String LocatorValue) {
		WebElement element=driver.findElement(By.xpath(LocatorValue));
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.DELETE);
		
	}
	
	public void addValues(String LocatorValue, String value) {
		try {
			driver.findElement(By.xpath(LocatorValue)).sendKeys(value);
			driver.findElement(By.xpath(LocatorValue)).sendKeys(Keys.ENTER);
			
			reportPass("Value entered is : " + value) ;
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	public void LoanEMI(String LocatorValue) {
		try {
			WebElement element = driver.findElement(By.xpath(LocatorValue));
			String EMI=element.getText();
			System.out.println("EMI amount is : " + EMI);
			reportPass("Loan EMI is : " + EMI);
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public void Interest(String LocatorValue) {
		try {
			WebElement element = driver.findElement(By.xpath(LocatorValue));
			String Interest=element.getText();
			System.out.println("Interest payable is : " + Interest);
			reportPass("Interest amount is : " + Interest);
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public String TotalPayment(String LocatorValue) {
		String Payment = null;
		try {
			WebElement element = driver.findElement(By.xpath(LocatorValue));
			Payment=element.getText();
			System.out.println("Total amount is : " + Payment);
			reportPass("Total amount will be : " + Payment);
			
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}
		return Payment;

	}
	
	@AfterMethod
	public void flushReports() {
		report.flush();
		driver.close();
	}



}
