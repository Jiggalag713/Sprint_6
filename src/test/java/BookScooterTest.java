import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import pageobjects.MainPage;
import pageobjects.OrderPage;
import pageobjects.RentPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookScooterTest {
    private WebDriver driver;
    Actions actions;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // Создание драйвера перед каждым тестом
        actions = new Actions(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @ParameterizedTest
    @CsvSource({
            "clickUpperBookButton, Владимир, Владимиров, Москва, Черкизовская, +77777777777, 29.06.25, сутки, серый, ''",
            "clickUpperBookButton, Петр, Ким, Владивосток, Домодедово, +71234567890, 01.01.25, семеро суток, черный, привезите поскорее",
            "clickLowerBookButton, Владимир, Владимиров, Москва, Черкизовская, +77777777777, 29.06.25, сутки, серый, ''",
            "clickLowerBookButton, Петр, Ким, Владивосток, Домодедово, +71234567890, 01.01.25, семеро суток, черный, привезите поскорее"
    })
    public void testBookScooterUpperButton(String method, String name, String surname, String address, String metroStation,
                                           String phoneNumber, String date, String duration, String color,
                                           String comment) {
        MainPage mainPage = new MainPage(driver, actions);
        mainPage.clickCookieButton();
        if (method.equals("clickUpperBookButton")) {
            mainPage.clickUpperBookButton();
        } else if (method.equals("clickLowerBookButton")) {
            mainPage.clickLowerBookButton();
        }
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fulfillOrderForm(name, surname, address, metroStation, phoneNumber);
        RentPage rentPage = new RentPage(driver);
        rentPage.fulfillRentForm(date, duration, color, comment);
        rentPage.clickOrderButton();
        rentPage.clickYesButton();
        assertTrue(rentPage.getResultText().contains("Номер заказа:"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Закрытие браузера
        }
    }
}
