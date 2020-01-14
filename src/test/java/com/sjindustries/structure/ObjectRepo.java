package com.sjindustries.structure;

public class ObjectRepo {

	// Maximo Log in Page
	public static String searchGoole = "//*[@id='q']";
	public static String sUsername_id = "j_username";
	public static String sPassword_id = "j_password";
	public static String sLogin_id = "loginbutton";
	public static String sSignOut_uiXP = "//*[text()='ign Out']";
	public static String sAppSignOutMessage_uiXpath = "//*[text()='You have successfully signed out.']";
	// Maximo Welcome page - field name
	public static String sAsset_uiXpath = "//*[contains(@title, 'Assets (T&D)')]";
	public static String sTitle_uiXpath = "//*[contains(@title, 'Locations')]";
	public static String sVSI_uiXpath = "//*[contains(@title, 'Vendor Services Invoicing')]";
	public static String sJobPlan_uiXpath = "//*[contains(@title, 'Job Plans (T&D)')]";
	public static String sLocation_uiXpath = "//*[contains(@title, 'Locations')]";
	public static String sMeterAssets_uiXpath = "//*[contains(@title, 'Meter Assets (T&D)')]";
	public static String sLaborRepo_uiXpath = "//*[contains(@title, 'Labor Reporting')]";
	public static String sPrevMaintenance_uiXpath = "//*[contains(@title, 'Preventive Maintenance')]";
	public static String sServiceReq_uiXpath = "//*[contains(@title, 'Service Requests')]";
	public static String sWorkOrderTruck_uiXpath = "//*[contains(@title, 'Work Order Tracking (T&D)')]";
	public static String sPayrollExcep_uiXpath = "//*[contains(@title, 'Payroll Exception')]";
	// Create WorkOrder
	public static String sNewWOButton_uiXpth = "//*[contains(@alt, 'New Work Order    CTRL+ALT+I')]";
	public static String sAssertDetailMenu_id = "md9a4a7af-img";
	public static String sAssertinType_uiID = "lookup_page1_tfrow_[C:4]_txt-tb";
	public static String sAssertSelectValue_xpath = "//*[text()='Select Value']";
	// public String sAssertField_id = "lookup_page1_tfrow_[C:4]_txt-tb";
	public static String sAssertsearchLink_id = "lookup_page1_tfrow_[C:4]_txt-img";
	public static String sAssertsearchDes_id = "lookup_page2_tfrow_[C:2]_txt-tb";
	// public String sAssertClickOk_id="lookup_page2_tfrow_[C:2]_txt-tb";
	public static String sAssertClickOk_uiXpath = "//*[text()='OK']";
	public static String sSelectAssert_id = "lookup_page1_tdrow_[C:0]_ttxt-lb[R:3]";
	public static String sAssetPlannedJobCodeLink_id = "maec60940-img";
	public static String sSelectPlanCode_id = "lookup_page1_tfrow_[C:0]_txt-tb";
	public static String sNameofAsset_id = "ma49bc9f9-tb2";
	public static String sNewWOSave_uiXpth = "//*[contains(@alt, 'Save Work Order    CTRL+ALT+S')]";
	public static String sclickJobCodeNu_id = "lookup_page1_tdrow_[C:0]_ttxt-lb[R:0]";
	public static String sJobCodeTurnOn_uiXpath = "//*[text()='Turn on.']";
	public static String sSystemMsg_uiXpath = "//*[text()='System Message']";
	public static String sSystemMsgAccept_id = "m88dbf6ce-pb";

	// CCB Locators
	public static String cLoginBut_id = "loginButton";
	public static String sHomeImage_title = "//*[text()='Customer Information']";
	public static String sAccountinfo_uiXpath = "//*[contains(@title, 'Account Information')]";
	
	/// WorkDay Locators 
	
	public static String wUname_uiXP = "//*[contains(@aria-label, 'Username')]";
	public static String wPawd_uiXP = "//*[contains(@aria-label, 'Password')]";
	public static String wSignIn_uiXP = "//*[text()= 'Sign In']";
	public static String wLogout_uiXP = "//*[contains(@data-automation-id, 'Current_User')]";
	public static String wLoutButton_uiXP = "//button[contains(text(), 'Sign Out')]";
	//Create Adhoc Payment
	public static String wSearchBox_uiXP = "//*[contains(@aria-label, 'search all of workday')]";
	public static String wCreateAdHocPayment_uiXP = "//*[contains(@aria-label, 'Create Ad Hoc Payment')]";
	public static String wCompanies_uiXP = "//*[text()= 'Companies']";
	public static String wSelectCompanyIcon_uiXP = "//*[text()='Bank Account']//preceding::div[1]";
	public static String wBankAcc_uiXP = "//*[text()= 'Global Modern Services, Inc. (USA)']";
	public static String wAccount_uiXpath = "//*[@id='56$12374--uid9']/span/div";
	public static String wSelectAcc_uiXP = "//*[text()= 'Bank Account for Ad Hoc Transactions']";
	public static String wSelectAccLink_uiXP = "//*[text()= 'AIB - Checking']";
	public static String wCurrency_uiXP = "//*[@id='56$36253--uid13']/span/div";
	public static String wPayeeIcon_uiXP = "//*[text()='Payee']//following::span[3]/div[1]";
	public static String wAdHocPayee_uiXP = "//*[text()= 'Ad Hoc Payee']";
	public static String wSelectPayee_uiXP = "//*[text()= 'PP FAS Demo Adhoc Payee 1']";
   	public static String wNewAdHocRadioButt_uiXP = "//*[text()='Payee']//preceding::input[@type='radio']";
	public static String wSingleusePayeeBox_uiXP = "//*[@id='56$506987--uid103']/div";
	public static String wPaymentType_uiXP = "//*[text()='Ship-To Address']//preceding::span[1]/div[1]";
	public static String wPayeeType_uiXP = "//*[text()= 'Direct Deposit']";
	public static String wSubmit_uiXP = "//*[text()= 'Submit']";
	public static String wErrorMessage_uiXP = "//*[contains(text(),'Errors and Alerts Found')]";

	//Create Settlement for Adhoc Payment
	public static String wCreateSett_uiXP = "//*[contains(@aria-label, 'Settlement Work Area')]";
	public static String wCreate_uiXP = "//*[text()= 'Create']";
	public static String wCompany_uiXP = "//*[text()='Company']//following::span[3]/div[1]";
	public static String wCurrencyRadioButt_uiXP = "//*[text()='Currencies']//preceding::input[@type='radio']";
	public static String wCurrencyIcon_uiXP = "//*[text()='Currencies']//following::span[3]/div[1]";
	public static String wPreferredCurrency_uiXP = "//*[text()='Preferred Currencies']";
	public static String wSelectUSD_uiXP = "//*[text()='SGD']//following::div[4]";
	public static String wSettRunName_uiXP = "//*[text()='Settlement Run Name']//following::input[1] ";
	public static String wAutoSelectFilters_uiXP = "//*[text()='Auto Select Filters']//following::span[3]/div[1]";
	public static String wSystemFilters_uiXP = "//*[text()='System Filters']";
	public static String wAdhocPayment_uiXP = "//*[text()='All Available Ad hoc Payments']";
	public static String wAdhocPay_uixp = "//div[@title='All Available Ad hoc Payments']";
	public static String wOK_uiXP = "//span[@title='OK']";
	public static String settlementPage_uiXP = "//*[text()='View Settlement Run']";
	//Create Ad Hoc Payment Template
	
	public static String wCreateAdhocTemplate_uiXP = "//*[text()='Tasks and Reports']//following::div[text()='Create Ad Hoc Payment Template']";
	//public static String wTableItemCom_uiXp = "//*[text()='Ad Hoc Payment Line Defaults']//following::table/thead/tr[1]/th[3]//following::div[10]";
	public static String wNameField_uiXP = "//*[text()='Name']//following::input[1]";
	public static String wBankAccounticon_uiXP = "//*[text()='Bank Account']//following::span[3]/div[1]";
	public static String wCTPaymentType_uiXP = "//*[text()='Payment Type']//following::span[3]/div[1]";
	public static String wCTPayeeType_uiXP = "//*[text()= 'Cash']";
	public static String wTableItemCom_uiXp = "//*[text()='Ad Hoc Payment Line Defaults']//following::table/thead/tr[1]/th[3]//following::div[10]";
	public static String wColItem_uiXP = "//*[text()='Ad Hoc Payment Line Defaults']//following::table/thead/tr[1]/th[3]//following::input[2]";
	public static String wItemColList_uiXP = "//*[text()='All Items']";
	public static String wItemName_uiXP="//*[text()='Advil Packets']";
	public static String wCostCenterCol_uiXP = "//*[text()='Ad Hoc Payment Line Defaults']//following::table/thead/tr[1]/th[3]//following::input[6]";
	public static String wSelectCostCenter_uiXP = "//*[text()='Active Cost Centers']";
	public static String wSeelctCostCenterItem_uiXP = "//*[text()='10000 Office of CEO']";
	public static String wAdditionalWorkTagCol_uiXP = "//*[text()='Ad Hoc Payment Line Defaults']//following::table/thead/tr[1]/th[3]//following::input[7]";
	public static String wSelectAddWorkTag_uiXP = "//*[text()='My Favorites']";
	public static String wSelectAddWorkItem_uiXP = "//*[text()='Location: Boston']";
	public static String wSubmitButton_uiXP = "//*[text()='Submit']";
	public static String wSuccessfullCreateAdhocTem_uiXP="//*[text()='Process Successfully Completed']";
	

}
