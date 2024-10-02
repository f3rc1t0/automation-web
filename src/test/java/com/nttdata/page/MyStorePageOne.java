package com.nttdata.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyStorePageOne {
    WebDriver driver;
    WebDriverWait wait;
    public MyStorePageOne(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navegarEnElMenu(){
        //Localizar el elemento "Clothes" usando XPath relativo
        WebElement clothesMenu = driver.findElement(By.cssSelector("#category-3 > a"));

        //Instancia de Actions para el hover
        Actions actions = new Actions(driver);

        //Se mueve el ratón sobre el elemento Clothes
        actions.moveToElement(clothesMenu).perform();

        //Se espera hasta que el menu Men esté visible
        WebElement menSubMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#category-4 > a")));

        menSubMenu.click();
    }

    public void validarProductoExiste(){
        WebElement productoExiste = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#js-product-list-top > div:first-child > p")));

        //Validamos que el texto dentro del elemento esté visible

        if(productoExiste.isDisplayed()){
            System.out.println("El texto está visible: " + productoExiste.getText());
        }else{
            System.out.println("El texno no está visible");
        }
    }

    public void clickEnProducto(){
        WebElement productoImagen = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='js-product-list']/div[1]/div/article/div/div[1]/a/picture/img")));
        productoImagen.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main\"]/div[1]/div[2]/h1")));

    }

}
