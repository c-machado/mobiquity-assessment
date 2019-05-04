package com.mobiquity.assessment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateEmployeePage {

    private WebDriver driver;

    @FindBy(name = "employeeForm")
    private WebElement employeeForm;

    @FindBy(css = "[ng-model='selectedEmployee.firstName']")
    private WebElement firstNameField;

    @FindBy(css = "[ng-model='selectedEmployee.lastName']")
    private WebElement lastNameField;

    @FindBy(css = "[ng-model='selectedEmployee.startDate']")
    private WebElement startDateField;

    @FindBy(css = "[ng-model='selectedEmployee.email']")
    private WebElement emailField;

    @FindBy(css = "button[type='submit'][ng-show='isCreateForm']")
    private WebElement addEmployeeButton;

    @FindBy(css = "button[type='submit'][ng-hide='isCreateForm']")
    private WebElement updateEmployeeButton;

    public CreateEmployeePage(WebDriver _driver) {
        this.driver = _driver;
        PageFactory.initElements(_driver,this);
    }

    public void enterFirstName(String _firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(_firstName);
    }

    public void enterLastName(String _lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(_lastName);
    }

    public void enterStartDate(String _startDate) {
        startDateField.clear();
        startDateField.sendKeys(_startDate);
    }

    public void enterEmail(String _email) {
        emailField.clear();
        emailField.sendKeys(_email);
    }

    public void clickSubmitButton() {
        addEmployeeButton.click();
    }

    public void clickUpdateButton() {
        updateEmployeeButton.click();
    }

    public boolean isFormInvalid() {
        return employeeForm.getAttribute("class").contains("ng-invalid");
    }

    public String getEmployeesEmail() {
        return emailField.getAttribute("value");
    }
}