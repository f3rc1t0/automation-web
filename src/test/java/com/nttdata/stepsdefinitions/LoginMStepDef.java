package com.nttdata.stepsdefinitions;

import com.nttdata.page.CarritoPage;
import com.nttdata.page.MyStorePageOne;
import com.nttdata.page.MyStorePageTwo;
import com.nttdata.steps.LoginMSteps;
import com.nttdata.steps.LoginSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.nttdata.core.DriverManager.screenShot;

public class LoginMStepDef {

    WebDriver driver;
    @Given("estoy en la página de la tienda")
    public void estoyEnLaPáginaDeLaTienda() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qalab.bensg.com/store/pe/iniciar-sesion");
    }

    @And("me logueo con mi usuario {string} y clave {string}")
    public void meLogueoConMiUsuarioYClave(String usuario, String clave) {
        LoginMSteps loginMSteps = new LoginMSteps(driver);
        loginMSteps.typeUser(usuario);
        loginMSteps.typeClave(clave);
        loginMSteps.iniciarSesion();
        screenShot();

        //Validar que la URL después del login es la esperada
        String expectedURL = "https://qalab.bensg.com/store/pe/";
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals("Error: No se redirigió a la URL esperado", expectedURL, currentURL);

    }

    @When("navego a la categoria {string} y subcategoria {string}")
    public void navegoALaCategoriaYSubcategoria(String categoria, String subcategoria) {
        MyStorePageOne myStorePageOne= new MyStorePageOne(driver);
        if(categoria.equals("Clothes") && subcategoria.equals("Men")) {
            myStorePageOne.navegarEnElMenu();
        }

        myStorePageOne.clickEnProducto();
    }

    @And("agrego {int} unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito(int cantidad) throws InterruptedException {
        MyStorePageTwo myStorePageTwo = new MyStorePageTwo(driver);

        myStorePageTwo.anadirCarrito(cantidad);
    }

    @Then("valido en el popup la confirmación del producto agregado")
    public void validoEnElPopupLaConfirmaciónDelProductoAgregado() {
        MyStorePageTwo myStorePageTwo = new MyStorePageTwo(driver);
        myStorePageTwo.validarPopupConfirmation("Hummingbird printed t-shirt", "19.12", 2);
    }

    @And("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
    }

    @When("finalizo la compra")
    public void finalizoLaCompra() {
        MyStorePageTwo myStorePageTwo = new MyStorePageTwo(driver);

        WebElement finalizarCompraBtn = myStorePageTwo.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a")));
        finalizarCompraBtn.click();

    }

    @Then("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        CarritoPage carritoPage = new CarritoPage(driver);

        carritoPage.validarTitulo("CARRITO");
    }

    @And("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {
        CarritoPage carritoPage = new CarritoPage(driver);
        carritoPage.validarPrecio("19.12");
        carritoPage.validarCantidad(2);
        carritoPage.validarTotal("38.24");
    }
}
