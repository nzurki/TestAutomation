package com.sjindustries.structure;

public class ObjectRepository {
	// Maximo Log in Page
	public String sUsername_id = "j_username";
	public String sPassword_id = "j_password";
	public String sLogin_id = "loginbutton";
	public String sSignOut_CSS ="#titlebar_hyperlink_8-lbsignout";
	public String sAppSignOutMessage_uiXpath = "//*[text()='You have successfully signed out.']";
	
	// Maximo Welcome page - field name
	public String sAsset_uiXpath = "//*[contains(@title, 'Assets (T&D)')]";
	public String sTitle_uiXpath = "//*[contains(@title, 'Locations')]";
	public String sVSI_uiXpath = "//*[contains(@title, 'Vendor Services Invoicing')]";
	public String sJobPlan_uiXpath = "//*[contains(@title, 'Job Plans (T&D)')]";
	public String sLocation_uiXpath = "//*[contains(@title, 'Locations')]";
	public String sMeterAssets_uiXpath = "//*[contains(@title, 'Meter Assets (T&D)')]";
	public String sLaborRepo_uiXpath = "//*[contains(@title, 'Labor Reporting')]";
	public String sPrevMaintenance_uiXpath = "//*[contains(@title, 'Preventive Maintenance')]";
	public String sServiceReq_uiXpath = "//*[contains(@title, 'Service Requests')]";
	public String sWorkOrderTruck_uiXpath = "//*[contains(@title, 'Work Order Tracking (T&D)')]";
	public String sPayrollExcep_uiXpath = "//*[contains(@title, 'Payroll Exception')]";

	// Create WorkOrder
	public String sNewWOButton_uiCSS = "#toolactions_INSERT-tbb_image";
	public String sAssertDetailMenu_idCSS = "#md9a4a7af-img";
	public String sAssertinType_uiID = "lookup_page1_tfrow_[C:4]_txt-tb";
	public String sAssertSelectValue_xpath = "//*[text()='Select Value']";
	public String sAssertField_id = "lookup_page1_tfrow_[C:4]_txt-tb";
	public String sAssertsearchLink_id = "lookup_page1_tfrow_[C:4]_txt-img";
	public String sAssertsearchDes_id = "lookup_page2_tfrow_[C:2]_txt-tb";
	public String sAssertClickOk_id="lookup_page2_tfrow_[C:2]_txt-tb";
	public String sAssertClickOk_uiXpath = "//*[text()='OK']";
	public String sSelectAssert_id = "lookup_page1_tdrow_[C:0]_ttxt-lb[R:3]";
	public String sAssetPlannedJobCodeLink_id = "maec60940-img";
	public String sSelectPlanCode_id = "lookup_page1_tfrow_[C:0]_txt-tb";
	public String sNameofAsset_id = "ma49bc9f9-tb2";
//	public String sNewWOSave_uiXpth = "//*[contains(@alt, 'Save Work Order    CTRL+ALT+S')]";
	public String sclickJobCodeNu_id = "lookup_page1_tdrow_[C:0]_ttxt-lb[R:0]";
//	public String sJobCodeTurnOn_uiXpath = "//*[text()='Turn on.']";
//	public String sSystemMsg_uiXpath = "//*[text()='System Message']";
//	public String sSystemMsgAccept_id = "m88dbf6ce-pb";
}
