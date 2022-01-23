package br.com.lucashsouza.tasks.functional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TasksTest {

    @Test
    public void deveSalvarTarefaComSucesso() {

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
    public void naoDeveSalvarTarefaSemDescricao() {

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
    public void naoDeveSalvarTarefaSemData() {

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
    public void naoDeveSalvarTarefaComDataPassada() {

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

    private WebDriver acessarAplicacao() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("http://localhost:8090/tasks/");

        return driver;
    }
}
