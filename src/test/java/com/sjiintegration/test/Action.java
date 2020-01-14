package com.sjiintegration.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.sjiintegration.utility.TestCasesLib;

public class Action extends TestCasesLib {

	public int failedTest;
	public int passedTest;

	@Test(enabled = false)
	public void TestCaseController() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		for (int Conf = 2; Conf <= SuiteData.getRowCount("Config"); Conf++) {
			String Flag = SuiteData.getCellData("Config", "Flag", Conf);
			if (Flag.equalsIgnoreCase("y")) {
				String Apps = SuiteData.getCellData("Config", "Applications", Conf);
				failedTest = 0;
				passedTest = 0;
				for (int TC = 2; TC <= TestCase.getRowCount(Apps); TC++) {
					String Run = TestCase.getCellData(Apps, "Run", TC);
					setRowNum(TC);
					if (Run.equalsIgnoreCase("y")) {
						String executionTime = this.now("dd.MMMM.yyyy hh.mm.ss aaa");
						String keyword = TestCase.getCellData(Apps, "TestCases", TC);
						log = extent.createTest(keyword);
						System.out.println("Executing: " + keyword);
						Method method = getClass().getMethod(keyword);
						method.invoke(this);
						TestCase.setCellData(Apps, "ExectionTime", TC, executionTime);
						TestCase.setCellData(Apps, "Status", TC, log.getStatus().toString());
						if (log.getStatus().toString().contains("fail")) {
							failedTest = failedTest + 1;
						} else if (log.getStatus().toString().contains("pass")) {
							passedTest = passedTest + 1;
						}
						tearDown();
					}
				}
				SuiteData.setCellData("Config", "TotalExecutionTime", Conf, log.getModel().getRunDuration().toString());
				SuiteData.setCellData("Config", "ExecutionStatus", Conf,
						"Pass[" + passedTest + "]" + " ; " + "Fail[" + failedTest + "]");
			}
		}

	}

}
