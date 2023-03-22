package com.InarAcademy.utilities;

import com.InarAcademy.pages.LoginPage;
import com.InarAcademy.pages.OrderPage;
import com.InarAcademy.pages.WebOrdersPage;

public class Pages {
    private WebOrdersPage webOrdersPage;
    private LoginPage loginPage;
    private OrderPage orderpage;

    public Pages() {
        this.loginPage = new LoginPage();
        this.webOrdersPage = new WebOrdersPage();
        this.orderpage = new OrderPage();
    }

    public WebOrdersPage webOrdersPage() {
        return webOrdersPage;
    }

    public LoginPage loginPage() {
        return loginPage;
    }
    public OrderPage orderPage() {
        return orderpage;
    }


}
