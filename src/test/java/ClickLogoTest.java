import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import pageobjects.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClickLogoTest {
    private WebDriver driver;
    Actions actions;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // Создание драйвера перед каждым тестом
        actions = new Actions(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void testClickScooterLogo() {
        driver.get("https://qa-scooter.praktikum-services.ru/order");
        MainPage mainPage = new MainPage(driver, actions);
        mainPage.clickScooterLogo();
        assertEquals("https://qa-scooter.praktikum-services.ru/", driver.getCurrentUrl());
    }

    @Test
    public void testClickYandexLogo() {
        MainPage mainPage = new MainPage(driver, actions);
        mainPage.clickYandexLogo();
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        assertEquals("https://yandex.ru", driver.getCurrentUrl());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Закрытие браузера
        }
    }
}
