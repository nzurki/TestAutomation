package com.sjiintegration.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.spec.KeySpec;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class GeneralFunctionLib {

	private static final Logger logger = Logger.getLogger(GeneralFunctionLib.class.getName());
	File file = new File(
			"C:\\Automation\\SeleniumAutomation\\TSA_TestAutomation\\src\\test\\resources\\config\\testEnv.properties");
	FileInputStream fileInput;
	Properties prop = new Properties();

//	public Logger logger = Logger.getLogger(getResult());
	public WebDriver driver;
	public String screenShotPath = System.getProperty("user.dir") + "\\screenshot\\";
	public String testResult;
	public int rowNum;
	public ExtentHtmlReporter reporter;
	public ExtentReports extent;
	public ExtentTest log;
	public DesiredCapabilities capabilities;
	public String UNICODE_FORMAT = "UTF8", myEncryptionKey, myEncryptionScheme;
	public String DESEDE_ENCRYPTION_SCHEME = "DESede";
	public KeySpec ks;
	public SecretKeyFactory skf;
	public Cipher cipher;
	public byte[] arrayBytes;
	SecretKey key;

	public boolean isDemoMode = false;

	public GeneralFunctionLib() {
		super();
		try {
			fileInput = new FileInputStream(file);
			prop.load(fileInput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@BeforeTest
	public void loadlod4j() {
		String log4jConPath = System.getProperty("user.dir") + "\\log\\log4j.properties";
		PropertyConfigurator.configure(log4jConPath);
		reporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\ExtentReport\\sjiReporter.html");
		reporter.config().setReportName("SJI System Integrations: Automated Regression Test Report");
		extent = new ExtentReports();
		extent.setSystemInfo("Host Name:", "Turnberry Solutions QA Team");
		extent.setSystemInfo("Test Environment:", "Test");
		extent.setSystemInfo("QA Resource:", "Nemet_Z");
		extent.attachReporter(reporter);
	}

	public void setResult(String testResult) {
		this.testResult = testResult;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getRowNum() {
		return rowNum;
	}

	public String getResult() {
		return testResult;
	}

	/***
	 * Author: Nemet_Z This method selects check box
	 * 
	 * @param by
	 * @param isCheck
	 */
	public void selectCheckBox(By by, boolean isCheck) {
		try {
			WebElement checkBoxElem = driver.findElement(by);
			boolean checkBoxState = checkBoxElem.isSelected();
			if (isCheck == true) {
				if (checkBoxState == true) {
					/* do nothing */
				} else {
					highlightElement(checkBoxElem);
					checkBoxElem.click();
				}
			} else {
				if (checkBoxState == true) {
					highlightElement(checkBoxElem);
					checkBoxElem.click();
				} else {
					/* do nothing */}
			}
			log.log(Status.PASS, Thread.currentThread().getStackTrace()[1].getMethodName() + "---" + by);
		} catch (Exception e) {
			log.log(Status.FAIL, Thread.currentThread().getStackTrace()[1].getMethodName() + "---" + e.getMessage());
		}
	}

	public void selectDropdownValue(By by, String byText) {
		try {
			Select drpCountry = new Select(driver.findElement(by));
			drpCountry.selectByVisibleText(byText);
			logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "---" + by + "---" + byText );
			log.log(Status.PASS, Thread.currentThread().getStackTrace()[1].getMethodName() + "---" + by + "---" + byText );
		} catch (Exception e) {
			log.log(Status.FAIL, Thread.currentThread().getStackTrace()[1].getMethodName() + "---" + by + "---" + byText );
		}
	}
	/***
	 * Author: Nemet_Z This method initiates browser
	 * 
	 * @param browserType
	 * @return driver
	 */
	public WebDriver startBrowser(String browserType) {
		if (browserType.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserType.contains("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserType.contains("ie")) {
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\IEDriverServer.exe");
			InternetExplorerOptions options = new InternetExplorerOptions(capabilities).requireWindowFocus();
			driver = new InternetExplorerDriver(options);
		} else {
			log.log(Status.FAIL,
					Thread.currentThread().getStackTrace()[1].getMethodName() + "===" + "Unsupported Browser Type.");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "---" + driver);
		log.log(Status.PASS, Thread.currentThread().getStackTrace()[1].getMethodName() + "---" + driver);
		return driver;
	}

	/***
	 * Author:Nemet_Z This method Navigates to App URL
	 * 
	 * @param URL
	 */
	public void NavigateTo(String URL) {
		try {
			driver.navigate().to(URL);
			logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "===" + URL);
			log.log(Status.PASS, Thread.currentThread().getStackTrace()[1].getMethodName() + "===" + URL);
		} catch (Exception e) {
			log.log(Status.FAIL, Thread.currentThread().getStackTrace()[1].getMethodName() + "===" + e.getMessage());
		}
	}

	/***
	 * This method enters text to the field
	 * 
	 * @param by
	 * @param inputString
	 * @throws IOException
	 */
	public void enterTextField(By by, String inputString) throws IOException {
		try {
//			waitUntilObjVisible(by);
			WebElement textFieldElem = driver.findElement(by);
			moveMouseToElement(textFieldElem);
			highlightElement(textFieldElem);
			textFieldElem.clear();
			textFieldElem.sendKeys(inputString);
			logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " === " + by);
			log.log(Status.PASS, Thread.currentThread().getStackTrace()[1].getMethodName() + " === " + by);
		} catch (Exception e) {
			String temp = captureScreenShot("enterTextField", screenShotPath);
			log.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + "\\" + e.getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	public void enterTextField(WebElement textFieldElem, String inputString) throws IOException {
		try {
			highlightElement(textFieldElem);
			textFieldElem.clear();
			textFieldElem.sendKeys(inputString);
			logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "===" + textFieldElem);
			log.log(Status.PASS, Thread.currentThread().getStackTrace()[1].getMethodName() + "===" + textFieldElem
					+ "===" + inputString);
		} catch (Exception e) {
			String temp = captureScreenShot("enterTextField", screenShotPath);
			log.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + "\\" + e.getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	public void clickButton(By by) throws IOException {
		try {
//			waitUntilObjVisible(by);
			WebElement btnElem = driver.findElement(by);
			moveMouseToElement(btnElem);
			highlightElement(btnElem);
			btnElem.click();
			logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + "===" + by);
			log.log(Status.PASS, Thread.currentThread().getStackTrace()[1].getMethodName() + "===" + by);
		} catch (Exception e) {
			String temp = captureScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName(), screenShotPath);
			log.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	public void clickButton(WebElement btnElem) throws IOException {
		try {
			highlightElement(btnElem);
			btnElem.click();
//			log.log(Status.PASS, Thread.currentThread().getStackTrace()[1].getMethodName() + "===" + btnElem);
		} catch (Exception e) {
			String temp = captureScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName(), screenShotPath);
			log.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	/***
	 * Author: Nemet_Z This method wait for specific time
	 */
	public void Wait(int inSeconds) {
		try {
			Thread.sleep(inSeconds * 1000);
//			log.log(Status.PASS, Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + inSeconds);
		} catch (Exception e) {
			log.log(Status.FAIL, Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + inSeconds);
		}
	}

	/***
	 * Author: Nemet_Z
	 * 
	 * @param screenshotFileName
	 * @param filePathtoSave
	 * @return
	 */
	public String captureScreenShot(String screenshotFileName, String filePathtoSave) {
		String finalImage = null;
		try {
			String tempName = filePathtoSave + screenshotFileName + "_" + getCurrentTime() + ".png";
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(srcFile, new File(tempName));
			finalImage = tempName;
//			log.log(Status.PASS, Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + tempName);
		} catch (Exception e) {
			log.log(Status.FAIL, Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + finalImage);
		}
		return finalImage;
	}

	/***
	 * This method returns current time
	 * 
	 * @return
	 */
	public String getCurrentTime() {
		String finalTimeStamp = null;
		try {
			Date date = new Date();
			String tempTime = new Timestamp(date.getTime()).toString();
			finalTimeStamp = tempTime.replaceAll("-", "").replaceAll(" ", "_").replaceAll(":", "_").replace(".", "_");
//			log.log(Status.PASS, Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + finalTimeStamp);
		} catch (Exception e) {
			log.log(Status.FAIL, Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + finalTimeStamp);
		}
		return finalTimeStamp;
	}

	/**
	 * Author: Nemet_Z
	 * 
	 * @param by
	 */
	public void highlightElement(By by) {
		if (isDemoMode == true) {
			try {
				WebElement ele = driver.findElement(by);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				Thread.sleep(450);
				js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
						ele);
				Thread.sleep(450);
				js.executeScript("arguments[0].setAttribute('style', '');", ele);
				Thread.sleep(450);
				js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
						ele);
				Thread.sleep(450);
				js.executeScript("arguments[0].setAttribute('style', '');", ele);
				Thread.sleep(450);
//				log.log(Status.PASS, Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + by);
			} catch (Exception e) {
				log.log(Status.FAIL, Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + by);

			}
		}
	}

	public void TestHighlight(By by) {
		try {
			WebElement ele = driver.findElement(by);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Thread.sleep(450);
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", ele);
			Thread.sleep(450);
			js.executeScript("arguments[0].setAttribute('style', '');", ele);
			Thread.sleep(450);
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", ele);
			Thread.sleep(450);
			js.executeScript("arguments[0].setAttribute('style', '');", ele);
			Thread.sleep(450);
//				log.log(Status.PASS, Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + by);
		} catch (Exception e) {
			log.log(Status.FAIL, Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + by);

		}
	}

	/***
	 * This method highlights object
	 * 
	 * @param ele
	 */
	public void highlightElement(WebElement ele) {
		if (isDemoMode == true) {
			try {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
						ele);
				Thread.sleep(450);
				js.executeScript("arguments[0].setAttribute('style', '');", ele);
				Thread.sleep(450);
				js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
						ele);
				Thread.sleep(450);
				js.executeScript("arguments[0].setAttribute('style', '');", ele);
				Thread.sleep(450);
//				log.log(Status.PASS, Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + ele);
			} catch (Exception e) {
				log.log(Status.FAIL, Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + ele);
			}
		}
	}

	/***
	 * This is a dynamic wait. User can use it when switch browser windows. When
	 * web-page synchronization happens.
	 * 
	 * @param by
	 * @return
	 */
//	public WebElement fluentWait(final By by) {
//		WebElement targetElem = null;
//		try {
//			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60,TimeUnit.SECONDS)
//					.pollingEvery(3, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
//
//			targetElem = wait.until(new Function<WebDriver, WebElement>() {
//				public WebElement apply(WebDriver driver) {
//
//					return driver.findElement(by);
//				}
//			});
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// highlightElement(targetElem);
//		return targetElem;
//	}

	/***
	 * This method switch to iframe element
	 * 
	 * @param by
	 */
	public void switchtoIframe(By by) {
		try {
			WebElement iframe = driver.findElement(by);
			driver.switchTo().frame(iframe);
//			log.log(Status.PASS, Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + by);
		} catch (Exception e) {
			log.log(Status.FAIL, Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + by);
		}
	}

	/***
	 * This method switch to default content from iframe content
	 * 
	 * @return
	 * @return
	 */
	public void switchToDefault() {
		try {
			driver.switchTo().defaultContent();
//			log.log(Status.PASS, Thread.currentThread().getStackTrace()[1].getMethodName());
		} catch (Exception e) {
			log.log(Status.FAIL, Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}

	/***
	 * Nemet Z This method hover-over to the element
	 * 
	 * @param toElement
	 */
	public void moveMouseToElement(WebElement toElement) {
//		if (isDemoMode == true) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(toElement).build().perform();
//			log.log(Status.PASS, Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + toElement);
		} catch (Exception e) {
			log.log(Status.FAIL, Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + toElement);
		}
//		}
	}

	/***
	 * This method moves mouse pointer to the first element to the second
	 * 
	 * @param firstElem
	 * @param secondElem
	 * @throws IOException
	 */
	public void moveMouseToElement(WebElement firstElem, WebElement secondElem) throws IOException {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(firstElem).build().perform();
			Wait(2);
			action.moveToElement(secondElem).build().perform();
			logger.info(
					Thread.currentThread().getStackTrace()[1].getMethodName() + ": " + firstElem + "-->" + secondElem);
		} catch (Exception e) {
			log.log(Status.PASS,
					Thread.currentThread().getStackTrace()[1].getMethodName() + ": " + firstElem + "-->" + secondElem);
		}
	}

	/***
	 * This method clicks the link
	 * 
	 * @param by
	 * @throws IOException
	 */
	public void clickLink(By by) throws IOException {
		try {
			waitUntilObjVisible(by);
			WebElement link = driver.findElement(by);
			highlightElement(link);
//			moveMouseToElement(link);
			link.click();
			logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " === " + by);
			log.log(Status.PASS, Thread.currentThread().getStackTrace()[1].getMethodName() + " === " + by);
		} catch (Exception e) {
			String temp = captureScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName(), screenShotPath);
			log.fail(Thread.currentThread().getStackTrace()[1].getMethodName() + "==" + by,
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	/***
	 * This method verifies page title
	 * 
	 * @return
	 * @throws IOException
	 */
	public String verifyPageTitle() throws IOException {
		String pageTitle = null;
		try {
			pageTitle = driver.getTitle();
			log.log(Status.PASS, Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + pageTitle);
		} catch (Exception e) {
			String temp = captureScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName(), screenShotPath);
			log.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
		return pageTitle;

	}

	/***
	 * This method clicks on hidden element by locator
	 * 
	 * @param by
	 * @throws IOException
	 */
	public void clickOnHiddenElement(By by) throws IOException {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", driver.findElement(by));
			log.log(Status.PASS, Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + by);
		} catch (Exception e) {
			String temp = captureScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName(), screenShotPath);
			log.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	/***
	 * This method clicks on Hidden element by element
	 * 
	 * @param element
	 * @throws IOException
	 */
	public void clickOnHiddenElement(WebElement element) throws IOException {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
			log.log(Status.PASS, Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + element);
		} catch (Exception e) {
			String temp = captureScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName(), screenShotPath);
			log.fail(Thread.currentThread().getStackTrace()[1].getMethodName(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	/***
	 * Wait until object available
	 * 
	 * @param by
	 * @return
	 */
	public WebElement explicitWaitForConditionExpectedConditions(By by) {
		WebElement elem = null;
		WebElement myDynamicElement = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(by));
		elem = myDynamicElement;
		return elem;
	}

	/***
	 * Explicit wait
	 * 
	 * @param by
	 */
	public void explicitWaitForElementIsClickAble(By by) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
//				elementToBeClickable(by));
	}

	/***
	 * This method move mouse to the object
	 * 
	 * @param HoverElement
	 */
	public void mouseHoverJScript(WebElement HoverElement) {
		try {
			if (isElementPresent(HoverElement)) {
				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				((JavascriptExecutor) driver).executeScript(mouseOverScript, HoverElement);
			} else {
				System.out.println("Element was not visible to hover " + "\n");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println(
					"Element with " + HoverElement + "is not attached to the page document" + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + HoverElement + " was not found in DOM" + e.getStackTrace());
		} catch (Exception e) {
//			setResult("Fail");
//			System.out.println("Error occurred while hovering" + e.getStackTrace());
		}
	}

	/***
	 * This method checks the element presence
	 * 
	 * @param element
	 * @return
	 */
	public boolean isElementPresent(WebElement element) {
		boolean flag = false;
		try {
			if (element.isDisplayed() || element.isEnabled())
				flag = true;
		} catch (NoSuchElementException e) {
			flag = false;
//			setResult("Fail");
		} catch (StaleElementReferenceException e) {
			flag = false;
//			setResult("Fail");
		}
		return flag;
	}

	/***
	 * This method returns current time
	 * 
	 * @param format
	 * @return
	 */
	public String now(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat fm = new SimpleDateFormat();
		return fm.format(cal.getTime());
	}

	public Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	/***
	 * This method SendKeys and press Enter key
	 * 
	 * @param by
	 */
	public void sendKeys(By by, String value) {
		try {
			waitUntilObjVisible(by);
			WebElement textbox = driver.findElement(by);
			moveMouseToElement(textbox);
			highlightElement(textbox);
			textbox.sendKeys(value);
			Wait(1);
			textbox.sendKeys(Keys.ENTER);
			logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " === " + by + " -> " + value);
			log.log(Status.PASS,Thread.currentThread().getStackTrace()[1].getMethodName() + " === " + by + " -> " + value);
		} catch (Exception e) {
			log.log(Status.FAIL, Thread.currentThread().getStackTrace()[1].getMethodName() + " : " + by);
		}
	}

	public void sendKeys(By by) {
		try {
			WebElement textbox = driver.findElement(by);
			textbox.sendKeys(Keys.ENTER);
			logger.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " === " + by);
			log.log(Status.PASS, Thread.currentThread().getStackTrace()[1].getMethodName() + " === " + by);
		} catch (Exception e) {
			log.log(Status.FAIL, Thread.currentThread().getStackTrace()[1].getMethodName() + " === " + by);
		}
	}

	/***
	 * Author: Fatema_Y Scrool down to the page
	 */
	public void scrollDown(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
	}

	/*
	 * Method name - explicitWait Purpose : Wait until expected condition is meet
	 * Author: Fatema_Y This method close the Application
	 */

	public void explicitWait(By by, int time) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			log.log(Status.PASS, "Validate Object is visible :" + by);
		} catch (Exception e) {
			log.log(Status.FAIL, "Validate Object is visible" + by);
			System.setProperty("overAllStatus", "Fail");
		}
	}

	public void waitUntilObjVisible(By by) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	/***
	 * NemetZ : This method verifies text
	 * 
	 * @param by
	 * @param verifyText
	 * @return
	 */
	public boolean verifyText(By by, String verifyText) {
		WebElement elem = driver.findElement(by);
		moveMouseToElement(elem);
		highlightElement(elem);
		if (elem.getText().contains(verifyText)) {
			highlightElement(by);
			return true;
		} else {
			return false;
		}
	}

	public void textVerify(By by, String verifyText) throws IOException {
		try {
			WebElement elem = driver.findElement(by);
			moveMouseToElement(elem);
			highlightElement(elem);
			elem.getText().contains(verifyText);
			log.log(Status.PASS, "Verified the Text -->" + by);
		} catch (Exception e) {
//			log.log(Status.FAIL, "Verify Text Failed -->" + by);
			String temp = captureScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName(), screenShotPath);
			log.fail(e, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
	}

	/*
	 * Author Fatema_Y Method name - encrypt method to encrypt the data
	 */

	public String encrypt(String unencryptedString) {
		String encryptedString = null;
		try {
			myEncryptionKey = "ThisIsSpartaThisIsSparta";
			myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
			arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
			ks = new DESedeKeySpec(arrayBytes);
			skf = SecretKeyFactory.getInstance(myEncryptionScheme);
			cipher = Cipher.getInstance(myEncryptionScheme);
			key = skf.generateSecret(ks);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
			byte[] encryptedText = cipher.doFinal(plainText);
			encryptedString = new String(Base64.encodeBase64(encryptedText));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedString;
	}

	/*
	 * Author Fatema_Y Method name - decrypt method to decrypt the data
	 */

	public String decrypt(String encrypted) {
		this.encrypt("Me");
		String decryptedText = null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] encryptedText = Base64.decodeBase64(encrypted);
			byte[] plainText = cipher.doFinal(encryptedText);
			decryptedText = new String(plainText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptedText;
	}

	public void setUp(String Browser, String URL) {
		startBrowser(Browser);
		NavigateTo(URL);
	}

	public void tearDown() {
		driver.close();
		driver.quit();
		extent.flush();
	}
	
	public long generateRandomPhoneNumber() { 
	    Random rnd = new Random();
	    char [] digits = new char[10];
	    digits[0] = (char) (rnd.nextInt(9) + '1');
	    for(int i=1; i<digits.length; i++) {
	        digits[i] = (char) (rnd.nextInt(9) + '0');
	    }
	    return Long.parseLong(new String(digits));
	}
}
