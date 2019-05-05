package com.mobiquity.assessment.steps;

import com.mobiquity.assessment.consts.Constants;
import com.mobiquity.assessment.pages.CreateEmployeePage;
import com.mobiquity.assessment.pages.LoginPage;
import com.mobiquity.assessment.pages.MainViewPage;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.After;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ManageEmployeeData {

    private WebDriver driver;
    private LoginPage loginPage;
    private MainViewPage mainViewPage;
    private CreateEmployeePage createEmployeePage;
    private String existingEmailRegistry;
    private Date date = new Date();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd-HH-mm-ss");
    private String updatedLastName;
    private String deleteEmployeeName;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT_TIME_FRAME, TimeUnit.SECONDS);
        /** initializing the page elements */
        loginPage = new LoginPage(driver);
        mainViewPage = new MainViewPage(driver);
        createEmployeePage = new CreateEmployeePage(driver);
    }

    @Given("^I'm logged in the Townsend application$")
    public void iMLoggedInTheTownsendApplication() {
        driver.get(Constants.LOGIN_URL);
        loginPage.enterUsername(Constants.USERNAME);
        loginPage.enterPassword(Constants.PASSWORD);
        loginPage.clickLoginButton();
    }

    @Given("^I'm at the main interface view$")
    public void iMAtTheMainInterfaceView() {
        WebDriverWait waitUntilViewIsLoaded = new WebDriverWait(driver, Constants.EXPLICIT_WAIT_TIME_FRAME);
        waitUntilViewIsLoaded.until(ExpectedConditions.urlContains("employees"));
        Assert.assertTrue(driver.getCurrentUrl().contains("employees"));
    }

    @When("^I click on the Create CTA$")
    public void iClickOnTheCreateCTA() {
        mainViewPage.clickCreateButton();
        Assert.assertTrue(driver.getCurrentUrl().contains("new"));
    }


    @Then("^I should see the employee listed at the main interface view$")
    public void iShouldSeeTheEmployeeListedAtTheMainInterfaceView() {
        Assert.assertTrue(mainViewPage.isUserInList(Constants.NEW_USER_FIRST_NAME + " " +  Constants.NEW_USER_LAST_NAME));
    }

    @And("^I fill out the new employee's information$")
    public void iFillOutTheNewEmployeeSInformation() {
        createEmployeePage.enterFirstName(Constants.NEW_USER_FIRST_NAME);
        createEmployeePage.enterLastName(Constants.NEW_USER_LAST_NAME);
        createEmployeePage.enterStartDate(Constants.NEW_USER_START_DATE);
        createEmployeePage.enterEmail(Constants.NEW_USER_EMAIL + "+" + dateFormat.format(date) + "@address.com");
    }

    @And("^I fill out the employee's \"([^\"]*)\" firstName$")
    public void iFillOutTheEmployeeSFirstName(String _firstName) {
        createEmployeePage.enterFirstName(_firstName);
    }

    @And("^I fill out the employee's \"([^\"]*)\" lastName$")
    public void iFillOutTheEmployeeSLastName(String _lastName) {
        createEmployeePage.enterLastName(_lastName);
    }

    @And("^I fill out the employee's \"([^\"]*)\" startDate$")
    public void iFillOutTheEmployeeSStartDate(String _startDate) {
        createEmployeePage.enterStartDate(_startDate);
    }

    @And("^I fill out the employee's \"([^\"]*)\" email address$")
    public void iFillOutTheEmployeeSEmailAddress(String _email) {
        createEmployeePage.enterEmail(_email);
    }

    @Then("^the create employees form should be invalid$")
    public void theCreateEmployeesFormShouldBeInvalid() {
        Assert.assertTrue(createEmployeePage.isFormInvalid());
    }

    @And("^I submit the new employee's information$")
    public void iSubmitTheNewEmployeeSInformation() {
        createEmployeePage.clickSubmitButton();
    }

    @When("^I select the employee to edit$")
    public void iSelectTheEmployeeToEdit() {
        mainViewPage.getRandomEmployee().click();
    }

    @And("^I click on the Edit CTA$")
    public void iClickOnTheEditCTA() {
        mainViewPage.clickEditCTA();
    }

    @And("^I update the employee's data$")
    public void iUpdateTheEmployeeSData() {
        updatedLastName = dateFormat.format(date);
        createEmployeePage.enterLastName(updatedLastName);
        createEmployeePage.enterEmail(Constants.UPDATED_EMAIL);
        createEmployeePage.clickUpdateButton();
    }

    @Then("^I should view the employee's data updated$")
    public void iShouldViewTheEmployeeSDataUpdated() {
        Assert.assertTrue(mainViewPage.isUserInList(updatedLastName));
        mainViewPage.clickEditCTA();
        Assert.assertTrue(createEmployeePage.getEmployeesEmail().equals(Constants.UPDATED_EMAIL));
    }

    @When("^I select the employee to delete$")
    public void iSelectTheEmployeeToDelete() {
        mainViewPage.getRandomEmployee().click();
        mainViewPage.clickEditCTA();
        deleteEmployeeName = createEmployeePage.getEmployeeName();
        createEmployeePage.clickBackButton();
        mainViewPage.getUserByDisplayName(deleteEmployeeName).click();
    }

    @And("^I click on the Delete CTA$")
    public void iClickOnTheDeleteCTA() {
        mainViewPage.clickDeleteCTA();
    }

    @And("^I confirm that I want to delete the employee's information$")
    public void iConfirmThatIWantToDeleteTheEmployeeSInformation() {
        driver.switchTo().alert().accept();
    }

    @Then("^I should not be able to find the employee$")
    public void iShouldNotBeAbleToFindTheEmployee() {
        int listElementsCount = mainViewPage.getListElementsCount();
        WebDriverWait waitUntilListIsRefreshed = new WebDriverWait(driver, Constants.EXPLICIT_WAIT_TIME_FRAME);
        waitUntilListIsRefreshed.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector("#employee-list li:not(.ng-leave)"),listElementsCount));

        Assert.assertFalse(mainViewPage.isUserInList(deleteEmployeeName));
    }

    @When("^I double click over an employee's name$")
    public void iDoubleClickOverAnEmployeeSName() {
        mainViewPage.doubleClickEmployeeName();
    }

    @Then("^I should see the employee's detailed information$")
    public void iShouldSeeTheEmployeeDetailedInformation() {
        Assert.assertTrue(driver.getCurrentUrl().contains("edit"));
    }

    @Then("^the create employees form should fail$")
    public void theCreateEmployeesFormShouldFail() {
        Assert.assertTrue(createEmployeePage.isFormInvalid());
    }

    @And("^I fill out the employee's mail with an already existing email address$")
    public void iFillOutTheEmployeeSMailWithAnAlreadyExistingEmailAddress() {
        createEmployeePage.enterEmail(existingEmailRegistry);
    }

    @Given("^I'm at the employees list interface$")
    public void iMAtTheEmployeesListInterface() {
        WebDriverWait waitUntilViewIsLoaded = new WebDriverWait(driver, Constants.EXPLICIT_WAIT_TIME_FRAME);
        waitUntilViewIsLoaded.until(ExpectedConditions.urlContains("employees"));
        existingEmailRegistry = mainViewPage.getEmail();
        createEmployeePage.clickBackButton();
    }

    @When("^I choose an employee to delete$")
    public void iChooseAnEmployeeToDelete() {
        mainViewPage.getRandomEmployee().click();
        mainViewPage.clickEditCTA();
        deleteEmployeeName = createEmployeePage.getEmployeeName();
    }

    @And("^I click on the Delete CTA on the Edit interface$")
    public void iClickOnTheDeleteCTAOnTheEditInterface() {
        createEmployeePage.clickDeleteButton();
    }

    @Then("^I should not be able to find the deleted employee$")
    public void iShouldNotBeAbleToFindTheDeletedEmployee() {
        Assert.assertFalse(mainViewPage.isUserInList(deleteEmployeeName));
    }

    @After
    public void close(){
        driver.close();
    }
}
