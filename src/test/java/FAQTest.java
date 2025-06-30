import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import pageobjects.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FAQTest {
    private WebDriver driver;
    Actions actions;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options); // Создание драйвера перед каждым тестом
        actions = new Actions(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @ParameterizedTest
    @CsvSource({
            "0, Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "1, 'Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.'",
            "2, 'Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.'",
            "3, Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "4, Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "5, Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "6, 'Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.'",
            "7, 'Да, обязательно. Всем самокатов! И Москве, и Московской области.'"
    })
    public void testAccordionText(String number, String text) throws InterruptedException {
        MainPage mainPage = new MainPage(driver, actions);
        mainPage.clickCookieButton();
        mainPage.scrollToFAQSection();
        driver.findElement(mainPage.getAccordionHeader(number)).click();
        synchronized (driver) {
            driver.wait(1000);
        }
        assertEquals(text, driver.findElement(mainPage.getAccordionValue(number)).getText());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Закрытие браузера
        }
    }
}
