package com.mobiquity.assessment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainViewPage {

    private WebDriver driver;

    @FindBy(css = "[ng-click='createEmployee()']")
    private WebElement createCTA;

    @FindBy(id = "employee-list")
    private WebElement employeeList;

    @FindBy(id = "bEdit")
    private WebElement editButton;

    @FindBy(id = "bDelete")
    private WebElement deleteButton;

    public MainViewPage(WebDriver _driver) {
        this.driver = _driver;
        PageFactory.initElements(_driver,this);
    }

    public void clickCreateButton() {
        createCTA.click();
    }

    public WebElement getUserByDisplayName(String _userName) {
        return driver.findElement(By.xpath("//*[@id='employee-list']/li[contains(@class, 'ng-enter-active')]" +
                "[contains(text(),'" + _userName + "')]"));
    }

    public boolean isUserInList(String _userName) {
        WebDriverWait waitUntilUsersAreAvailable = new WebDriverWait(driver, 3);
        waitUntilUsersAreAvailable.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("#employee-list li"), 1));
        WebElement newEmployee = getUserByDisplayName(_userName);
        System.out.println("new Employeeeeeeee "+ newEmployee);
        return newEmployee.isDisplayed();
    }

    public void clickEditCTA() {
        editButton.click();
    }

    public void clickDeleteCTA() {
        deleteButton.click();
    }

    public int getListElementsCount() {
        return driver.findElements(By.cssSelector("#employee-list li:not(.ng-leave)")).size();
    }
}