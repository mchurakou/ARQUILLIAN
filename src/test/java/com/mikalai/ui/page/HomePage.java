package com.mikalai.ui.page;

import junit.framework.Assert;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.findby.FindByJQuery;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.jboss.arquillian.graphene.Graphene.guardAjax;

public class HomePage {

    @FindBy(tagName = "li")
    private WebElement facesMessage;

    @FindByJQuery("p:visible")
    private WebElement signedAs;

    @FindBy(css = "input[type=submit]")
    private GrapheneElement whoAmI;

    public void assertOnHomePage() {
        Assert.assertEquals("We should be on home page", "Welcome", getMessage());
    }

    public String getMessage() {
        return facesMessage.getText().trim();
    }

    public String getUserName() {
        whoAmI();
        return signedAs.getText();
    }

    private void whoAmI() {
        guardAjax(whoAmI).click();
    }
}