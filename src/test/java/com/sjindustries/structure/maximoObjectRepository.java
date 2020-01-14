package com.sjindustries.structure;

import org.openqa.selenium.By;

import com.sjiintegration.utility.GeneralFunctionLib;

public class maximoObjectRepository {

	GeneralFunctionLib myLib = new GeneralFunctionLib();
	public By maximo_LoginPage_UsernameInput = By.id("j_username");
	public By maximo_LoginPage_PasswordInput = By.id("j_password");
	public By maximo_LoginPage_SignInButton = By.id("loginbutton");
	public By maximo_WelcomePage_Title = By.id("titlebar-tb_appname");
	public By maximo_WelcomePage_SignOutButton = By.id("titlebar_hyperlink_8-lbsignout");
	public By maximo_WelcomePage_SignOutButton_SubmitBtn = By.cssSelector("#titlebar_hyperlink_8-lbsignout > img");
	public By welcomePage_AddOn_WorkOrderTrackingTD = By.id("FavoriteApp_PLUSDWOTRK");
	public By workOrderTracking_CreateNewWorkOrder = By.id("toolactions_INSERT-tbb_image");
	public By workOrderTracking_LocationInputField = By.id("m3a3dd144-tb");
	public By Location_shevronSymbal = By.id("m3a3dd144-img");
	public By location_shevronSymbal_selectValue = By.id("");
	public By workOrderTracking_PlannedJobCodeInputField = By.id("maec60940-tb");
	public By workOrderTracking_PlannedJobCode_SearchButton = By.id("maec60940-img");
	public By selectValue_searchByJobCode_InputField = By.xpath("//input[@id='lookup_page1_tfrow_[C:0]_txt-tb']");
	public By workOrderTracking_SaveWorkOrder_Button = By.id("toolactions_SAVE-tbb_image");
	public By workOrderTracking_JobCodeLookUp_Window = By.id("lookup_SJGJOBCODE-lb");
	public By workOrderTracking_JobCodeLookUp_FirstRow = By.xpath("//td[@id='lookup_page1_tdrow_[C:0]-c[R:0]']/span");
	public By approveWorkOrder_Link	= By.xpath("//span[text()='Approve Work Order']");
	public By approveWorkOrder_OKButton = By.id("m60bd6d91-pb");
	public By workOrderTracking_StatusField = By.id("md3801d08-tb");
	public By workOrderTracking_WorkOrderNumberField = By.id("ma49bc9f9-tb");	
	public By maximo_WelcomePage_MeterAssetTD_Link = By.id("FavoriteApp_PLUSDASSET");
	public By assets_SearchByAssetID_InputBox = By.xpath("//input[@id='m6a7dfd2f_tfrow_[C:1]_txt-tb']");
	public By assets_SearchByAssetIF_FirstRow = By.xpath("//td[@id='m6a7dfd2f_tdrow_[C:1]-c[R:0]']/span");
	public By assets_MeterAsset_Status_InputBox = By.xpath("//input[@id='m7755dc58-tb']");
	public By assets_ChangeStatus_Button = By.xpath("//span[@id='md86fe08f_ns_menu_STATUS_OPTION_a_tnode']");
	public By assets_ChangeStatusWindow_NewStatusSelectIcon = By.xpath("//img[@id='mc927149a-img']");
	public By assets_ChangeStatusWindow_NewStatusOption_Retired = By.xpath("//span[@id='menu0_RETIRED_OPTION_a_tnode']");
	public By assets_changeStatusWindow_Ok_Button = By.xpath("//button[@id='m60bd6d91-pb']");	
	public By assets_RetireDate_InputBox = By.xpath("//input[@id='md5c1a47a-tb']");
	public By assets_RetireReasonCode_InputBox = By.xpath("//input[@id='m637e5375-tb']");
	public By assets_SaveBotton = By.xpath("//img[@id='toolactions_SAVE-tbb_image']");	
}
