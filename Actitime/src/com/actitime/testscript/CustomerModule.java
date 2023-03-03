package com.actitime.testscript;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.actitime.generic.BaseClass;
import com.actitime.generic.FileLib;
import com.actitime.pom.HomePage;
import com.actitime.pom.TaskListPage;

@Listeners(com.actitime.generic.ListenerImplementation.class)
public class CustomerModule extends BaseClass{
	
	@Test 
	public void testCreateCustomer() throws InterruptedException, EncryptedDocumentException, IOException {
		Reporter.log("CreateCustomer",true);
		/*FileInputStream fis=new FileInputStream("./data/testscript.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String expCustName = wb.getSheet("CreateCustomer").getRow(1).getCell(2).getStringCellValue();
		String custDesp = wb.getSheet("CreateCustomer").getRow(1).getCell(3).getStringCellValue();
		*/
		FileLib f=new FileLib();
		String expCustName = f.getExcelData("CreateCustomer", 1, 2);
		String custDesp = f.getExcelData("CreateCustomer", 1, 3);
		HomePage h=new HomePage(driver);
		h.setTaskTab();
		TaskListPage t=new TaskListPage(driver);
		t.getAddNewBtn().click();
		t.getNewCustOption().click();
		t.getEnterCustNameTbx().sendKeys(expCustName);
		t.getEnterCustDespTbx().sendKeys(custDesp);
		t.getSelectCustDD().click();
		t.getOurCompanyTxt().click();
		t.getCreateCustBtn().click();
		Thread.sleep(4000);
		String actualCustName = t.getActualCustCreated().getText();
		Assert.assertEquals(actualCustName, expCustName);
		
	}
}
