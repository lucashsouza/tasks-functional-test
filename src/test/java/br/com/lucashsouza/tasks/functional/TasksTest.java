package br.com.lucashsouza.tasks.functional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TasksTest {

    @Test
    public void deveSalvarTarefaComSucesso() throws MalformedURLException {

        WebDriver driver = acessarAplicacao();

        try {
            // Click em "Add todo"
            driver.findElement(By.id("addTodo")).click();

            // Escrever descricao
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            // Escrever data
            driver.findElement(By.id("dueDate")).sendKeys("01/01/2030");

            // Click em "salvar"
            driver.findElement(By.id("saveButton")).click();

            // Validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", message);
        } finally {
            // Fechar o browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {

        WebDriver driver = acessarAplicacao();

        try {
            // Click em "Add todo"
            driver.findElement(By.id("addTodo")).click();

            // Escrever data
            driver.findElement(By.id("dueDate")).sendKeys("01/01/2030");

            // Click em "salvar"
            driver.findElement(By.id("saveButton")).click();

            // Validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the task description", message);
        } finally {
            // Fechar o browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemData() throws MalformedURLException {

        WebDriver driver = acessarAplicacao();

        try {
            // Click em "Add todo"
            driver.findElement(By.id("addTodo")).click();

            // Escrever descricao
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            // Click em "salvar"
            driver.findElement(By.id("saveButton")).click();

            // Validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the due date", message);
        } finally {
            // Fechar o browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {

        WebDriver driver = acessarAplicacao();

        try {
            // Click em "Add todo"
            driver.findElement(By.id("addTodo")).click();

            // Escrever descricao
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            // Escrever data
            driver.findElement(By.id("dueDate")).sendKeys("01/01/2010");

            // Click em "salvar"
            driver.findElement(By.id("saveButton")).click();

            // Validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Due date must not be in past", message);
        } finally {
            // Fechar o browser
            driver.quit();
        }
    }

    private WebDriver acessarAplicacao() throws MalformedURLException {
        //WebDriver driver = new ChromeDriver();
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        WebDriver driver = new RemoteWebDriver(new URL("http://172.20.0.1:4444/wd/hub"), cap);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("http://192.168.1.200:8090/tasks/");

        return driver;
    }
}
