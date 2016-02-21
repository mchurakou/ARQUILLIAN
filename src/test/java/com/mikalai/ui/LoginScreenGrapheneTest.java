package com.mikalai.ui;

import com.mikalai.ui.fragment.LoginForm;
import com.mikalai.ui.page.HomePage;
import com.mikalai.ui.page.LoginPage;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.findby.FindByJQuery;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.URL;


@RunWith(Arquillian.class)
public class LoginScreenGrapheneTest {


    private static final String USER_NAME = "demo";
    private static final String USER_PASSWORD = "demo";

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return Deployments.getWebArchive();

    }




    @Drone
    private WebDriver browser;

    @ArquillianResource
    private URL deploymentUrl;

    @FindBy(id = "loginForm:userName")
    private WebElement userName;

    @FindBy(id = "loginForm:password")
    private WebElement password;

    @FindBy(id = "loginForm:login")
    private WebElement loginButton;


    @FindBy(tagName = "li")
    private WebElement facesMessage;

    @FindByJQuery("p:visible")
    private WebElement signedAs;

    @FindBy(css = "input[type=submit]")
    private WebElement whoAmI;


    @Test
    public void should_login_successfully() throws InterruptedException {

        browser.get(deploymentUrl.toExternalForm() + "login.jsf"); // first page load doesn't have to be synchronized

        userName.sendKeys("demo");
        password.sendKeys("demo");


        Graphene.guardHttp(loginButton).click();                            // 1. synchronize full-page request
        Assert.assertEquals("Welcome", facesMessage.getText().trim());

        Graphene.guardAjax(whoAmI).click();                                 // 2. synchronize AJAX request
        Assert.assertTrue(signedAs.getText().contains("demo"));
    }

    @Page
    private HomePage homePage;


    @FindBy
    private LoginForm loginForm;

    @Test
    public void should_login_successfully2(@InitialPage LoginPage loginPage ) {

        loginPage.login(USER_NAME, USER_PASSWORD);
        homePage.assertOnHomePage();

        Assert.assertEquals(homePage.getUserName(), "You are signed in as demo.");
    }
}
