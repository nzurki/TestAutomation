package com.sjindustries.structure;

import org.openqa.selenium.By;
import com.sjiintegration.utility.GeneralFunctionLib;

public class ccbObjectRepository {
	
	GeneralFunctionLib myLib = new GeneralFunctionLib();

	public By frame_mainFrame = By.name("main");
	public By frame_tabPage = By.id("tabPage");
	public By frame_zoneMapFrame = By.id("zoneMapFrame_1");
	public By frame_personContact = By.id("PER_CONT_DETL");
	public By frame_personPersonName = By.id("PER_NAME");
	public By frame_personPersonIdentifier = By.id("PER_IDENTIFIER");	
	public By ccbLoginPageusernameInput = By.id("userId");
	public By ccbLoginPagePasswordInput = By.id("password");
	public By ccbLoginPageloginButton = By.id("loginButton");
	public By ccbWelcomePage_SearchBy = By.id("multiQueryZoneFilters1");
	public By ccbWelcomePage_searchBy_NameAndAddress = By.cssSelector("#multiQueryZoneFilters1 > option:nth-child(1)");
	public By ccbWelcomePage_searchBy_AccountID = By.cssSelector("#multiQueryZoneFilters1 > option:nth-child(2)");
	public By ccbWelcomePage_searchBy_PersonContact = By.cssSelector("#multiQueryZoneFilters1 > option:nth-child(3)");
	public By ccbWelcomePage_searchBy_PersonIDType = By.cssSelector("#multiQueryZoneFilters1 > option:nth-child(4)");
	public By ccbWelcomePage_searchBy_GeoType = By.cssSelector("#multiQueryZoneFilters1 > option:nth-child(5)");
	public By controlCentral_MainTab_AccountID_InputField = By.id("accountId1");
	public By controlCentral_MainTab_SearchButton = By.id("anTLZ1Refresh");
	public By controlCentral_AccInfoTab_PremiseDropdown = By.xpath("//img[@oramdlabel='F1_SHOW_PREMISE_CTX']");
//	public By controlCentral_AccInfoTab_PremiseDropdown = By.xpath("//td[@id='context.5.1']/following-sibling::td[1]");
	public By controlCentral_AccInfoTab_PersonDropdown = By.xpath("//img[@oramdlabel='F1_SHOW_PERSON_CTX']"); //td[@id='context.2.2']/img	
	public By gotoFieldActivityLink = By.id("CI_CONTEXTPREMISE_subMenuItem1x7");
	public By gotoPerson = By.id("CI_CONTEXTPERSON_subMenuItem0x0");
	public By goToFieldActivity_AddButton = By.xpath("//span[text()='Add']");
	public By fieldActivity_MainTab_FATypeInput = By.id("FA_TYPE_CD");
	public By fieldActivity_MainTab_ScheduleDateInput = By.id("SCHED_DTTM_FWDTTM_P1");
	public By fieldActivity_MainTab_ScheduledTimeInput = By.id("SCHED_DTTM_FWDTTM_P2");
	public By fieldActivity_MainTab_InstructionInput = By.id("INSTRUCTIONS");
	public By fieldActivity_MainTab_SaveButton = By.id("IM_SAVE");
	public By fieldActivity_MainTab_FieldActivityID = By.id("FA_ID");
	public By person_PersonContacts_addContactInfo_Button = By.xpath("//img[@id='IM_PER_CONT_DETL:0$pContactDetail_ADD_BUTTON']");
	public By person_personContacts_selectContactType_Dropdown = By.xpath("//select[@id='PER_CONT_DETL:1$COMM_RTE_TYPE_CD']");
	public By person_personContacts_AddValueInTheField = By.xpath("//input[@id='PER_CONT_DETL:1$CONTACT_VALUE']");
	public By person_personContacts_checkPrimaryBox = By.xpath("//input[@id='PER_CONT_DETL:1$CND_PRIMARY_SW']");
	public By person_PersonName_Add_Button = By.xpath("//img[@id='IM_PER_NAME:0$pNames_ADD_BUTTON']");
	public By person_PersonName_SelectNameType_Dropdown = By.xpath("//select[@id='PER_NAME:1$NAME_TYPE_FLG']");
	public By person_PersonName_SelectValue_InputBox = By.xpath("//input[@id='PER_NAME:1$ENTITY_NAME']");
	public By Person_PersonID_Add_Button = By.xpath("//img[@id='IM_PER_IDENTIFIER:0$pIdents_Add']");
	public By Person_PersonID_SelectIDType_Dropdown = By.xpath("//select[@id='PER_IDENTIFIER:1$ID_TYPE_CD']");
	public By Person_PersonID_SelectIDType_InputBox = By.xpath("//input[@id='PER_IDENTIFIER:1$PER_ID_NBR']");
}
