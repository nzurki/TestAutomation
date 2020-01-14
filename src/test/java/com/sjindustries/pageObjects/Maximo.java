package com.sjindustries.pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sjiintegration.utility.GeneralFunctionLib;

public class Maximo {
	
	GeneralFunctionLib myLib;
	WebDriver driver;

	public Maximo(WebDriver driver) {
		this.driver = driver;
		myLib = new GeneralFunctionLib();
	}

	By usernameInput = By.id("userId");
	By passwordInput = By.id("j_password");
	By loginButton = By.id("loginbutto");

	public void usernameInput(String UID) throws IOException {
		myLib.enterTextField(usernameInput, UID);
	}
}
