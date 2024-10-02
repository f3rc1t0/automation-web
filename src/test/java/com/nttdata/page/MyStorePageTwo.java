package com.nttdata.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyStorePageTwo {
    WebDriver driver;
    public WebDriverWait wait;

    public MyStorePageTwo(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void establecerCantidad(int cantidad) throws InterruptedException {
        WebElement campoCantidad = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"quantity_wanted\"]")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", campoCantidad);

        //campoCantidad.clear();

        wait.until(ExpectedConditions.attributeToBe(campoCantidad, "value",""));

        campoCantidad.sendKeys(String.valueOf(cantidad));

        //Validar que el campo muestra el valor esperado

        String valorActual = campoCantidad.getAttribute("value");
        if(!valorActual.equals(String.valueOf(cantidad))){
            throw new AssertionError("Error: La cantidad no se estableció correctamente. Se esperaba" + cantidad + "pero se encontró: " + valorActual);
        }

        //WebElement aumentarBoton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[1]/div/span[3]/button[1]/i")));
        //WebElement disminuirBoton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[1]/div/span[3]/button[2]/i")));

        /*if(cantidad > 1) {
            for(int i = 1; i<cantidad; i++){
                aumentarBoton.click();
            }
        }*/
    }

    public void anadirCarrito(int cantidad) throws InterruptedException {
        establecerCantidad(cantidad);

        WebElement addCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button")));
        addCartButton.click();

    }

    public void validarPopupConfirmation(String nombreEsperado, String precioEsperado, int cantidadEsperada){

        //Popup visible
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"blockcart-modal\"]/div")));

        //Validar el nombre del producto
        WebElement nombreProducto = popup.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[1]/div/div[2]/h6"));
        String nombreActual = nombreProducto.getText();
        if(!nombreActual.equals(nombreEsperado)){
            throw new AssertionError("Error: El nombre del producto es incorrecto. Se esperaba: " +nombreEsperado + "pero se encontró: " + nombreActual);
        }

        //Validar el precio

        WebElement precioElemento = popup.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[1]/div/div[2]/p"));
        System.out.println("Elemento de precio: " + precioElemento);
        String precioTexto = precioElemento.getText().replace("S/&nbsp", "").replace("S/ ", "").trim();
        double precioActual = Double.parseDouble(precioTexto);
        System.out.println("Precio actual: " + precioActual);

        //Calcular el total esperado
        double totalEsperado = precioActual * cantidadEsperada;
        System.out.println("Total esperado: " + totalEsperado);

        double precioEsperadoNumerico = Double.parseDouble(precioEsperado);
        if(precioActual != precioEsperadoNumerico) {
            throw new AssertionError("Error: El precio es incorrecto. Se esperaba: " + precioEsperado + ", pero se encontró: " + precioActual);
        }

        //Validar la cantidad

        WebElement cantidadElemento = popup.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[1]/div/div[2]/span[3]/strong"));
        String cantidadActualTexto = cantidadElemento.getText();
        int cantidadActual = Integer.parseInt(cantidadActualTexto);
        System.out.println("Cantidad actual: " + cantidadActual);
        if (!cantidadActualTexto.equals(String.valueOf(cantidadEsperada))) {
            throw new AssertionError("Error: La cantidad es incorrecta. Se esperaba: " + cantidadEsperada + ", pero se encontró: " + cantidadActual);
        }

        //Validar el total
        WebElement totalElemento = popup.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/p[4]/span[2]"));
        String totalTexto = totalElemento.getText().replace("S/", "").trim();
        double totalActual = Double.parseDouble(totalTexto);
        System.out.println("Total actual: " + totalActual);

        if(totalActual != totalEsperado) {
            throw new AssertionError("Error: El total es incorrecto. Se esperaba: " + totalEsperado + ", pero se encontró: " + totalActual);
        }

    }


}
