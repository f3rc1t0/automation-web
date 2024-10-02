package com.nttdata.steps;

import com.nttdata.page.LoginMyStore;
import com.nttdata.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginMSteps {
    private WebDriver driver;

    //Constructor
    public LoginMSteps(WebDriver driver) {
        this.driver = driver;
    }

    //Se ingresa el usuario
    public void typeUser (String user) {
        WebElement userInputElement = driver.findElement(LoginMyStore.userInput);
        userInputElement.sendKeys(user);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(444));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginMyStore.iniciarButton));
    }

    //Se ingresa la clave
    public void typeClave(String clave){
        this.driver.findElement(LoginMyStore.passInput).sendKeys(clave);
    }

    public void iniciarSesion(){
        this.driver.findElement(LoginMyStore.iniciarButton).click();
    }
}
