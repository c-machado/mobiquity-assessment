package com.mobiquity.assessment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class MainViewPage {

    private WebDriver driver;

    @FindBy(css = "[ng-click='createEmployee()']")
    private WebElement createCTA;

    @FindBy(id = "employee-list")
    private WebElement employeeList;

    @FindBy(css = "#employee-list li")
    private List<WebElement> employeesListDetail;

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
        try{
            WebElement employeeToGet = driver.findElement(By.xpath("//*[@id='employee-list']/li[not(contains(@class, 'ng-leave'))]" +
                    "[contains(text(),'" + _userName + "')]"));
            return employeeToGet;
        }
        catch (NoSuchElementException e){
            return null;
        }
    }

    public boolean isUserInList(String _userName) {
        WebDriverWait waitUntilUsersAreAvailable = new WebDriverWait(driver, 3);
        waitUntilUsersAreAvailable.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("#employee-list li"), 1));
        WebElement employedToFind = getUserByDisplayName(_userName);
        if(employedToFind == null){
            return false;
        }
        return employedToFind.isDisplayed();
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

    public void doubleClickEmployeeName() {
        Actions actions = new Actions(driver);
        actions.doubleClick(getRandomEmployee()).build().perform();
    }

    public WebElement getRandomEmployee() {
        Random rand = new Random();
        int randomEmployee = rand.nextInt(employeesListDetail.size());
        return employeesListDetail.get(randomEmployee);
    }
}