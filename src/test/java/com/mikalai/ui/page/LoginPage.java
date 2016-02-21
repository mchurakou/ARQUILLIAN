package com.mikalai.ui.page;

import com.mikalai.ui.fragment.LoginForm;
import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.support.FindBy;

@Location("login.jsf")
public class LoginPage {


    @FindBy
    private LoginForm loginForm;        // locates the root of a page fragment on a particular page

    public LoginForm getLoginForm() {   // we can either manipulate with the login form or just expose it
        return loginForm;
    }

    public void login(String userName, String password) {
        loginForm.login(userName, password);
    }
}
