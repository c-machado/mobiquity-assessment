package com.mobiquity.assessment.steps;

import com.mobiquity.assessment.consts.Constants;
import com.mobiquity.assessment.pages.LoginPage;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class ManageEmployeeData {

    private WebDriver driver;
    private LoginPage loginpPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Given("^I'm logged in the Townsend application$")
    public void iMLoggedInTheTownsendApplication() {
        driver.get(Constants.LOGIN_URL);
        loginpPage = new LoginPage(driver);
        loginpPage.enterUsername(Constants.USERNAME);
        loginpPage.enterPassword(Constants.PASSWORD);
        loginpPage.clickLoginButton();

    }

//    @Given("^I'm at the main interface view$")
//    public void iMAtTheMainInterfaceView() {
//    }
//
//    @When("^I click on the Create CTA$")
//    public void iClickOnTheCreateCTA() {
//    }
//
//    @And("^I submit the new employee's information$")
//    public void iSubmitTheNewEmployeeSInformation() {
//    }
//
//    @Then("^I should see the employee data at the main interface view$")
//    public void iShouldSeeTheEmployeeDataAtTheMainInterfaceView() {
//    }



}
