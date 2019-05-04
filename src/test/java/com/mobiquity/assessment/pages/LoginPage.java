package com.mobiquity.assessment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    @FindBy(css = "[ng-model='user.name']")
    private WebElement usernameField;

    @FindBy(css = "[ng-model='user.password']")
    private WebElement passwordField;

    @FindBy(css = ".main-button[type='submit']")
    private WebElement loginCta;

    public LoginPage(WebDriver _driver) {
        this.driver = _driver;
        PageFactory.initElements(_driver,this);
    }

    public void enterUsername(String _username) {
        usernameField.sendKeys(_username);
    }

    public void enterPassword(String _password) {
        passwordField.sendKeys(_password);
    }

    public void clickLoginButton() {
        loginCta.click();
    }
}
