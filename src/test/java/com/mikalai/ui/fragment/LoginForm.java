package com.mikalai.ui.fragment;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by mikalai on 22.02.2016.
 */
public class LoginForm {
    @Root
    private WebElement form;

    @FindBy(css = "input[type=text]")
    private WebElement userName;

    @FindBy(css = "input[type=password]")
    private WebElement password;

    @FindBy(css = "input[type=submit]")
    private WebElement loginButton;

    public void login(String userName, String password) {
        this.userName.sendKeys(userName);
        this.password.sendKeys(password);
        Graphene.guardHttp(loginButton).click();
    }
}
