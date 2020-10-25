package TestCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseClass.BaseUi;
import Pages.CarLoanPage;
import Pages.HomeLoanPage;
import Pages.LoanCalculatorPage;
import Pages.MainPage;

public class Test1 extends BaseUi{
	
	CarLoanPage carLoan;
	HomeLoanPage homeLoan;
	LoanCalculatorPage loanCalculator;
	MainPage mainPage;
	
	
	//**********************Scenario 1*********************************************************************************************************************
		@Test(dataProvider="carloan",priority=0)
		public void VerifycarloanData(String loan_amount, String interest_rate,String tenure,String loan_tenure,String expected_result) throws IOException {
			logger=report.createTest("Car Loan Testing");
			invokeBrowser("chrome");
			
			//BaseUi base=new BaseUi();
			
			mainPage= openApplication(prop.getProperty("URL"));
			
			carLoan=mainPage.clickCarLoan();
			
			
			clearField(prop.getProperty("LoanAmount_XPath"));	
			addValues(prop.getProperty("LoanAmount_XPath"), loan_amount);
			
			clearField(prop.getProperty("Rate_XPath"));		
			addValues(prop.getProperty("Rate_XPath"), interest_rate);
			
			if(tenure.equals("M"))
			{
				driver.findElement(By.xpath("/html/body/div[1]/div/main/article/div[3]/div/div[1]/div[1]/div[2]/form/div/div[7]/div/div/div/div/div/label[2]")).click();
				
			}
			
			clearField(prop.getProperty("Tenure_XPath"));	
			addValues(prop.getProperty("Tenure_XPath"), loan_tenure);
			
			String actual_result = TotalPayment(prop.getProperty("TotalPayment"));
			if(actual_result.equals("0"))
			{actual_result="0.0";}
			Assert.assertEquals(expected_result, actual_result);
			
			writeExcelData(System.getProperty("user.dir") +"\\Resources\\Repositiries\\carloan.xlsx",prop.getProperty("Label_Xpath1"),prop.getProperty("Row_Xpath1"),prop.getProperty("Col_Xpath1"));
			
		}
		
		@DataProvider(name="carloan")
		public Object[][] carloanData() {
			Object[][] arrayObject = getExcelData(System.getProperty("user.dir") +"\\Resources\\Repositiries\\Testing.xlsx","scenario_1",9,6);
			return arrayObject;
		}
	//*******************************Scenario 2*************************************************************************************************************************************

		@Test(dataProvider="homeloanemi",priority=1)
		public void VerifyhomeloanemiData(String home_value, String down_payment,String loan_insurance,String interest_rate,String loan_tenure,String one_time_expenses,String property_taxes,String home_insurance,String maintenance_expenses ,String expected_result) throws IOException {
			logger=report.createTest("Home Loan EMI testing");
			invokeBrowser("chrome");
			
			mainPage= openApplication(prop.getProperty("URL"));
			
			homeLoan=mainPage.clickHomeLoan();

			
			clearField(prop.getProperty("Home_Value"));	
			addValues(prop.getProperty("Home_Value"), home_value);
			clearField(prop.getProperty("Down_Payment"));	
			addValues(prop.getProperty("Down_Payment"), down_payment);
			clearField(prop.getProperty("Loan_Insurance"));	
			addValues(prop.getProperty("Loan_Insurance"), loan_insurance);
			clearField(prop.getProperty("Interest_Rate"));	
			addValues(prop.getProperty("Interest_Rate"), interest_rate);
			clearField(prop.getProperty("Loan_Tenure"));	
			addValues(prop.getProperty("Loan_Tenure"), loan_tenure);
			clearField(prop.getProperty("One_Time_Expenses"));	
			addValues(prop.getProperty("One_Time_Expenses"), one_time_expenses);
			clearField(prop.getProperty("Property_Taxes"));	
			addValues(prop.getProperty("Property_Taxes"), property_taxes);
			clearField(prop.getProperty("Home_Insurance"));	
			addValues(prop.getProperty("Home_Insurance"), home_insurance);
			clearField(prop.getProperty("Maintenance_Expenses"));	
			addValues(prop.getProperty("Maintenance_Expenses"), maintenance_expenses);
			
			
			String actual_result = TotalPayment(prop.getProperty("TotalPayment_2"));
			Assert.assertEquals(expected_result, actual_result);
			
			writeExcelData(System.getProperty("user.dir") +"\\Resources\\Repositiries\\homeloanemi.xlsx",prop.getProperty("Label_Xpath2"),prop.getProperty("Row_Xpath2"),prop.getProperty("Col_Xpath2"));
		}
		
		@DataProvider(name="homeloanemi")
		public Object[][] homeloanemiData() {
			Object[][] arrayObject = getExcelData(System.getProperty("user.dir") +"\\Resources\\Repositiries\\Testing.xlsx","scenario_2",11,11);
			return arrayObject;
		}

	//***********************Scenario 3*************************************************************************************************************************************************

		@Test(dataProvider="loancalculator",priority=2)
		public void VerifyloancalculatorData(String loan_amount,String interest_rate,String loan_tenure,String fees_charges,String expected_result) throws IOException {
			logger=report.createTest("Loan Calculator testing");
			invokeBrowser("chrome");
		
			mainPage= openApplication(prop.getProperty("URL"));
			
			loanCalculator=mainPage.LoanCal();
			
			
			clearField(prop.getProperty("Loan_Amount_3"));	
			addValues(prop.getProperty("Loan_Amount_3"), loan_amount);
			clearField(prop.getProperty("Interest_Rate_3"));	
			addValues(prop.getProperty("Interest_Rate_3"), interest_rate);
			clearField(prop.getProperty("Loan_Tenure_3"));	
			addValues(prop.getProperty("Loan_Tenure_3"), loan_tenure);
			clearField(prop.getProperty("Fees_Charges"));	
			addValues(prop.getProperty("Fees_Charges"), fees_charges);
			
			
			String actual_result = TotalPayment(prop.getProperty("TotalPayment_3"));
			Assert.assertEquals(expected_result, actual_result);
			
			writeExcelData(System.getProperty("user.dir") +"\\Resources\\Repositiries\\loancalculator.xlsx",prop.getProperty("Label_Xpath3"),prop.getProperty("Row_Xpath3"),prop.getProperty("Col_Xpath3"));
			
		}
		
		@DataProvider(name="loancalculator")
		public Object[][] loancalculatorData() {
			Object[][] arrayObject = getExcelData(System.getProperty("user.dir") +"\\Resources\\Repositiries\\Testing.xlsx","scenario_3",4,6);
			return arrayObject;
		}

}
