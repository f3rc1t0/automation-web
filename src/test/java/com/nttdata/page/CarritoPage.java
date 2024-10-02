package com.nttdata.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CarritoPage {
    WebDriver driver;
    WebDriverWait wait;

    public CarritoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void validarTitulo(String tituloEsperado) {
        WebElement tituloElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[1]/h1")));
        String tituloActual = tituloElemento.getText();
        if (!tituloActual.equals(tituloEsperado)) {
            throw new AssertionError("Error: El título es incorrecto. Se esperaba: " + tituloEsperado + ", pero se encontró: " + tituloActual);
        }
    }

    public void validarPrecio(String precioEsperado) {
        WebElement precioElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[2]/div[2]/div[2]/span")));

        // Limpiar el texto para eliminar el símbolo y los espacios adicionales
        String precioActual = precioElemento.getText().replace("S/ ", "").trim();

        if (!precioActual.equals(precioEsperado)) {
            throw new AssertionError("Error: El precio es incorrecto. Se esperaba: " + precioEsperado + ", pero se encontró: " + precioActual);
        }
    }

    public void validarCantidad(int cantidadEsperada) {
        WebElement cantidadElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[3]/div/div[2]/div/div[1]/div/input")));
        String cantidadActual = cantidadElemento.getAttribute("value");
        if (!cantidadActual.equals(String.valueOf(cantidadEsperada))) {
            throw new AssertionError("Error: La cantidad es incorrecta. Se esperaba: " + cantidadEsperada + ", pero se encontró: " + cantidadActual);
        }
    }

    public void validarTotal(String totalEsperado) {
        WebElement totalElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[1]/div[2]/div[2]/span[2]")));

        // Limpiar el texto para eliminar el símbolo y los espacios adicionales
        String totalActual = totalElemento.getText().replace("S/ ", "").trim();

        if (!totalActual.equals(totalEsperado)) {
            throw new AssertionError("Error: El total es incorrecto. Se esperaba: " + totalEsperado + ", pero se encontró: " + totalActual);
        }
    }

}
