import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import pageobjects.MainPage;
import pageobjects.OrderPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NegativeTest {
    WebDriver driver;
    Actions actions;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // Создание драйвера перед каждым тестом
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    // Проверка текста ошибки при вводе некорректного имени
    @Test
    public void testIncorrectName() {
        MainPage mainPage = new MainPage(driver, actions);
        mainPage.clickUpperBookButton();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.inputName("Jack");
        orderPage.inputSurname("");
        assertEquals("Введите корректное имя", orderPage.getErrorMessage());
    }

    // Проверка текста ошибки при вводе некорректной фамилии
    @Test
    public void testIncorrectSurname() {
        MainPage mainPage = new MainPage(driver, actions);
        mainPage.clickUpperBookButton();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.inputSurname("Johnson");
        orderPage.inputName("");
        assertEquals("Введите корректную фамилию", orderPage.getErrorMessage());
    }

    // Проверка текста ошибки при вводе некорректного адреса
    @Test
    public void testIncorrectAddress() {
        MainPage mainPage = new MainPage(driver, actions);
        mainPage.clickUpperBookButton();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.inputAddress("second star to the right and straight on till morning");
        orderPage.inputName("");
        assertEquals("Введите корректный адрес", orderPage.getErrorMessage());
    }

    // Проверка текста ошибки при вводе некорректного телефона
    @Test
    public void testIncorrectPhoneNumber() {
        MainPage mainPage = new MainPage(driver, actions);
        mainPage.clickUpperBookButton();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.inputPhoneNumber("123");
        orderPage.inputName("");
        assertEquals("Введите корректный номер", orderPage.getErrorMessage());
    }

    // Проверка текста ошибки при вводе несуществующего номера заказа
    @Test
    public void testIncorrectOrder() throws InterruptedException {
        MainPage mainPage = new MainPage(driver, actions);
        mainPage.clickCookieButton();
        mainPage.clickStatusButton();
        mainPage.inputOrderNumber("777777");
        mainPage.clickGoButton();
        assertTrue(driver.findElement(By.xpath(".//img[@src='/assets/not-found.png']")).isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Закрытие браузера
        }
    }
}
