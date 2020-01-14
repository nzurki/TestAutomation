package com.sjiintegration.utility;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.sjindustries.structure.ObjectRepository;
import com.sjindustries.structure.ccbObjectRepository;
import com.sjindustries.structure.maximoObjectRepository;

public class TestCasesLib extends GeneralFunctionLib {

	ccbObjectRepository ccbOr = new ccbObjectRepository();
	maximoObjectRepository maximoOr = new maximoObjectRepository();
	ObjectRepository or = new ObjectRepository();

	private static final Logger logger = Logger.getLogger(TestCasesLib.class.getName());
	public XL_Utility TestCase = new XL_Utility(System.getProperty("user.dir") + "//inputSheet//inputSheet.xlsx");
	public XL_Utility SuiteData = new XL_Utility(System.getProperty("user.dir") + "//inputSheet//inputSheet.xlsx");
	
	/***
	 * THIS METHOD IS TO CREATE FA IN CCB
	 * NEMET ZURKI
	 */
	public void ccb_create_fieldActivityNoAppointment() {
		String Browser = TestCase.getCellData("CCB", "Browser", getRowNum());
		String AccountID = TestCase.getCellData("CCB", "AccountID", getRowNum());
		String UpdateType = TestCase.getCellData("CCB", "UpdateType", getRowNum());
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + AccountID + "_" + UpdateType);
		try {
			setUp(Browser, prop.getProperty("ccbTestURL"));
			enterTextField(ccbOr.ccbLoginPageusernameInput, prop.getProperty("ccbUID"));
			enterTextField(ccbOr.ccbLoginPagePasswordInput, prop.getProperty("ccbPWD"));
			clickButton(ccbOr.ccbLoginPageloginButton);
			switchtoIframe(ccbOr.frame_mainFrame);
			switchtoIframe(ccbOr.frame_tabPage);
			clickLink(ccbOr.ccbWelcomePage_SearchBy);
			clickLink(ccbOr.ccbWelcomePage_searchBy_AccountID);
			enterTextField(ccbOr.controlCentral_MainTab_AccountID_InputField, AccountID);
			clickButton(ccbOr.controlCentral_MainTab_SearchButton);
			switchToDefault();
			Wait(3);
			switchtoIframe(ccbOr.frame_mainFrame);
			switchtoIframe(ccbOr.frame_tabPage);
			switchtoIframe(ccbOr.frame_zoneMapFrame);
			clickLink(ccbOr.controlCentral_AccInfoTab_PremiseDropdown);
			switchToDefault();
			Wait(2);
			switchtoIframe(ccbOr.frame_mainFrame);
			clickLink(ccbOr.gotoFieldActivityLink);
			clickLink(ccbOr.goToFieldActivity_AddButton);
			switchtoIframe(ccbOr.frame_tabPage);
			sendKeys(ccbOr.fieldActivity_MainTab_FATypeInput, UpdateType);
			enterTextField(ccbOr.fieldActivity_MainTab_ScheduleDateInput, "");
			sendKeys(ccbOr.fieldActivity_MainTab_ScheduledTimeInput, "08:00AM");
			enterTextField(ccbOr.fieldActivity_MainTab_InstructionInput, getCurrentTime());
			switchToDefault();
			switchtoIframe(ccbOr.frame_mainFrame);
			clickLink(ccbOr.fieldActivity_MainTab_SaveButton);
			driver.switchTo().alert().accept();
			Wait(2);
			switchtoIframe(ccbOr.frame_tabPage);
			String FAID = driver.findElement(ccbOr.fieldActivity_MainTab_FieldActivityID).getAttribute("value");
			Wait(2);
			SuiteData.setCellData("CCB", "FA_ID", getRowNum(), FAID);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "field activity ID === " + FAID);
			logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "*****Execution completed****");
		} catch (Exception e) {
			log.log(Status.FAIL, e);
		}
	}

	/***
	 * THIS METHOD IS TO UPDATE/ADD CUSTOMER INFO IN CCB
	 * NEMET ZURKI
	 */
	public void ccb_setCustomerInformation() {
		String Browser = TestCase.getCellData("CCB", "Browser", getRowNum());
		String UpdateType = TestCase.getCellData("CCB", "UpdateType", getRowNum());
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + UpdateType);
		try {
			setUp(Browser, prop.getProperty("ccbTestURL"));
			enterTextField(ccbOr.ccbLoginPageusernameInput, prop.getProperty("ccbUID"));
			enterTextField(ccbOr.ccbLoginPagePasswordInput, prop.getProperty("ccbPWD"));
			clickButton(ccbOr.ccbLoginPageloginButton);
			switchtoIframe(ccbOr.frame_mainFrame);
			switchtoIframe(ccbOr.frame_tabPage);
			clickLink(ccbOr.ccbWelcomePage_SearchBy);
			clickLink(ccbOr.ccbWelcomePage_searchBy_AccountID);
			String AccountID = TestCase.getCellData("CCB", "AccountID", getRowNum());
			enterTextField(ccbOr.controlCentral_MainTab_AccountID_InputField, AccountID);
			clickButton(ccbOr.controlCentral_MainTab_SearchButton);
			switchToDefault();
			Wait(3);
			switchtoIframe(ccbOr.frame_mainFrame);
			switchtoIframe(ccbOr.frame_tabPage);
			switchtoIframe(ccbOr.frame_zoneMapFrame);
			clickLink(ccbOr.controlCentral_AccInfoTab_PersonDropdown);
			switchToDefault();
			Wait(2);
			switchtoIframe(ccbOr.frame_mainFrame);
			clickLink(ccbOr.gotoPerson);
			Wait(2);
			if (UpdateType.equalsIgnoreCase("Mobile Number")) {
				String MobPhoneNm = String.valueOf(generateRandomPhoneNumber());
				switchtoIframe(ccbOr.frame_tabPage);
				switchtoIframe(ccbOr.frame_personContact);
				clickButton(ccbOr.person_PersonContacts_addContactInfo_Button);
				selectDropdownValue(ccbOr.person_personContacts_selectContactType_Dropdown, UpdateType);
				enterTextField(ccbOr.person_personContacts_AddValueInTheField, MobPhoneNm);
				selectCheckBox(ccbOr.person_personContacts_checkPrimaryBox, true);
				log.info("Phone number generated in runtime == " + MobPhoneNm);
				logger.info("Phone number generated in runtime == " + MobPhoneNm);
				SuiteData.setCellData("CCB", "PhoneNumber", getRowNum(), MobPhoneNm);
				Wait(3);
				switchToDefault();
				switchtoIframe(ccbOr.frame_mainFrame);
				clickLink(ccbOr.fieldActivity_MainTab_SaveButton);
				Wait(5);
				logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "*****Execution completed****");
			} else if (UpdateType.equalsIgnoreCase("Secondary Contact Name")) {
				switchtoIframe(ccbOr.frame_tabPage);
				switchtoIframe(ccbOr.frame_personPersonName);
				clickButton(ccbOr.person_PersonName_Add_Button);
				selectDropdownValue(ccbOr.person_PersonName_SelectNameType_Dropdown, UpdateType);
				enterTextField(ccbOr.person_PersonName_SelectValue_InputBox, "Nemet, Zurki");
				log.info("Secondary Contact Name added as: " + "Nemet, Zurki");
				logger.info("Secondary Contact Name added as: " + "Nemet, Zurki");
				Wait(3);
				switchToDefault();
				switchtoIframe(ccbOr.frame_mainFrame);
				clickLink(ccbOr.fieldActivity_MainTab_SaveButton);
				Wait(5);
				logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "*****Execution completed****");
			} else if (UpdateType.equalsIgnoreCase("Driver's License")) {
				String DriverLicenseNum = String.valueOf(generateRandomPhoneNumber());
				switchtoIframe(ccbOr.frame_tabPage);
				switchtoIframe(ccbOr.frame_personPersonIdentifier);
				clickButton(ccbOr.Person_PersonID_Add_Button);
				selectDropdownValue(ccbOr.Person_PersonID_SelectIDType_Dropdown, UpdateType);
				enterTextField(ccbOr.Person_PersonID_SelectIDType_InputBox, DriverLicenseNum);
				log.info("Driver Lisence Number Created during the Runtime is: " + DriverLicenseNum);
				logger.info("Driver Lisence Number Created during the Runtime is: " + DriverLicenseNum);
				SuiteData.setCellData("CCB", "DriverLincenseNumber", getRowNum(), DriverLicenseNum);
				Wait(3);
				switchToDefault();
				switchtoIframe(ccbOr.frame_mainFrame);
				clickLink(ccbOr.fieldActivity_MainTab_SaveButton);
				Wait(5);
				logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "*****Execution completed****");
			} else {
				logger.info(
						Thread.currentThread().getStackTrace()[1].getMethodName() + " -- No Selection Option Provided");
			}
		} catch (Exception e) {
			log.log(Status.FAIL, e);
		}
	}

	/***
	 * THIS METHOD IS TO CREATE WORK ORDER IN MAXIMO
	 * @throws IOException
	 * NEMET ZURKI
	 */
	public void Maximo_Create_new_WorkOrder() throws IOException {

		String Browser = TestCase.getCellData("MAXIMO", "Browser", getRowNum());
		String JobCode = TestCase.getCellData("MAXIMO", "JobCode", getRowNum());
		String Location = TestCase.getCellData("MAXIMO", "Location", getRowNum());
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "_"+ Location+ "JobCode - " + JobCode);
		try {
			setUp(Browser, prop.getProperty("maximoTestURL"));
			enterTextField(maximoOr.maximo_LoginPage_UsernameInput, prop.getProperty("maximoUID"));
			enterTextField(maximoOr.maximo_LoginPage_PasswordInput, prop.getProperty("maximoPWD"));
			clickButton(maximoOr.maximo_LoginPage_SignInButton);
			waitUntilObjVisible(maximoOr.maximo_WelcomePage_Title);
			Assert.assertTrue(driver.getTitle().contains("Start Center"));
			clickLink(maximoOr.welcomePage_AddOn_WorkOrderTrackingTD);
			clickButton(maximoOr.workOrderTracking_CreateNewWorkOrder);
			sendKeys(maximoOr.workOrderTracking_LocationInputField, Location);
			clickButton(maximoOr.workOrderTracking_PlannedJobCode_SearchButton);
			sendKeys(maximoOr.selectValue_searchByJobCode_InputField, JobCode);
			Wait(2);
			clickButton(maximoOr.workOrderTracking_JobCodeLookUp_FirstRow);
			Wait(4);
			clickButton(maximoOr.workOrderTracking_SaveWorkOrder_Button);
			Wait(5);
			clickLink(maximoOr.approveWorkOrder_Link);
			Wait(3);
			clickButton(maximoOr.approveWorkOrder_OKButton);
			Wait(5);
			String WONumber = driver.findElement(maximoOr.workOrderTracking_WorkOrderNumberField).getAttribute("value");
			String WOstatus = driver.findElement(maximoOr.workOrderTracking_StatusField).getAttribute("value");
			SuiteData.setCellData("MAXIMO", "WONumber", getRowNum(), WONumber);
			SuiteData.setCellData("MAXIMO", "WOStatus", getRowNum(), WOstatus);
			logger.info("Work Order Number = " + WONumber + "; Work Order Status = " + WOstatus);
			clickLink(maximoOr.maximo_WelcomePage_SignOutButton);
			log.info("Work Order Number = " + WONumber + "; Work Order Status = " + WOstatus);
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "****Execution Completed****");
		} catch (Exception e) {
			log.log(Status.FAIL, e);
		}
	}

	/***
	 * THIS METHOD IS TO RETIRE A NON RETIRED METER
	 */
	public void maximo_RetireMeterInventory() {
		
		String Browser = TestCase.getCellData("MAXIMO", "Browser", getRowNum());
		String AssetID = TestCase.getCellData("MAXIMO", "AssetID", getRowNum());
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + AssetID);
		try {
			setUp(Browser, prop.getProperty("maximoTestURL"));
			enterTextField(maximoOr.maximo_LoginPage_UsernameInput, prop.getProperty("maximoUID"));
			enterTextField(maximoOr.maximo_LoginPage_PasswordInput, prop.getProperty("maximoPWD"));
			clickButton(maximoOr.maximo_LoginPage_SignInButton);
			waitUntilObjVisible(maximoOr.maximo_WelcomePage_Title);
			Assert.assertTrue(driver.getTitle().contains("Start Center"));
			clickLink(maximoOr.maximo_WelcomePage_MeterAssetTD_Link);
			sendKeys(maximoOr.assets_SearchByAssetID_InputBox, AssetID);
			clickLink(maximoOr.assets_SearchByAssetIF_FirstRow);
			String meterStatus = driver.findElement(maximoOr.assets_MeterAsset_Status_InputBox).getAttribute("value");
			if (!meterStatus.equalsIgnoreCase("retired")) {
				clickLink(maximoOr.assets_ChangeStatus_Button);
				clickButton(maximoOr.assets_ChangeStatusWindow_NewStatusSelectIcon);
				Wait(1);
				clickLink(maximoOr.assets_ChangeStatusWindow_NewStatusOption_Retired);
				Wait(1);
				clickButton(maximoOr.assets_changeStatusWindow_Ok_Button);
				Wait(2);
				enterTextField(maximoOr.assets_RetireDate_InputBox, "1/7/2020");
				Wait(2);
				enterTextField(maximoOr.assets_RetireReasonCode_InputBox, "9");
				Wait(2);
				clickButton(maximoOr.assets_SaveBotton);
				Wait(3);
				String meterStatusChanged = driver.findElement(maximoOr.assets_MeterAsset_Status_InputBox).getAttribute("value");
				assertEquals(meterStatusChanged, "RETIRED");
				logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " === Current Meter Status is: " + meterStatusChanged);
				log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " === Current Meter Status is: " + meterStatusChanged);
			} else {
				log.log(Status.FAIL, Thread.currentThread().getStackTrace()[1].getMethodName() + " === The Meter is already " + meterStatus);
				logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " === The Meter is already " + meterStatus);
			}
			log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "****Execution Completed****");
		} catch (Exception e) {
			log.log(Status.FAIL, e);
		}
	}


	

}