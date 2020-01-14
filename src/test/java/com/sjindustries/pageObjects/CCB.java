package com.sjindustries.pageObjects;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sjiintegration.utility.GeneralFunctionLib;

public class CCB {

	private static final Logger logger = Logger.getLogger(CCB.class.getName());
	WebDriver driver;
	GeneralFunctionLib myLib;

	public CCB(WebDriver driver) {
		this.driver = driver;
		myLib = new GeneralFunctionLib();
	}	
	
	private By frame_mainFrame = By.name("main");
	private By frame_tabPage = By.id("tabPage");

	private By ccbLoginPageusernameInput = By.id("userId");
	private By ccbLoginPagePasswordInput = By.id("password");
	private By loginButton = By.id("loginButton");

	private By ccbWelcomePage_SearchBy = By.id("multiQueryZoneFilters1");
	private By ccbWelcomePage_searchBy_NameAndAddress = By.cssSelector("#multiQueryZoneFilters1 > option:nth-child(1)");
	private By ccbWelcomePage_searchBy_AccountID = By.cssSelector("#multiQueryZoneFilters1 > option:nth-child(2)");
	private By ccbWelcomePage_searchBy_PersonContact = By.cssSelector("#multiQueryZoneFilters1 > option:nth-child(3)");
	private By ccbWelcomePage_searchBy_PersonIDType = By.cssSelector("#multiQueryZoneFilters1 > option:nth-child(4)");
	private By ccbWelcomePage_searchBy_GeoType = By.cssSelector("#multiQueryZoneFilters1 > option:nth-child(5)");

	public void getCCBLoginPage_UsernameInput(String UID) {
		try {
			myLib.enterTextField(ccbLoginPageusernameInput, UID);
			logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getCCBLoginPage_PasswordInput(String PWD) {
		try {
			myLib.enterTextField(ccbLoginPagePasswordInput, PWD);
			logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getCCBLoginPage_SubmitButton()  {
		try {
			myLib.clickButton(loginButton);
			logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
