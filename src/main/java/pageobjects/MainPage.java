package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class MainPage {
    private final WebDriver driver;
    private final Actions actions;

    // Верхняя кнопка "Заказать"
    private final By upperBookButton = By.className("Button_Button__ra12g");

    // Кнопка "Статус заказа"
    private final By statusButton = By.className("Header_Link__1TAG7");

    // Поле "Введите номер заказа"
    private final By orderNumberInput = By.xpath(".//input[@class='Input_Input__1iN_Z Header_Input__xIoUq']");

    // Кнопка "Go"
    private final By goButton = By.xpath(".//button[@class='Button_Button__ra12g Header_Button__28dPO']");

    // Нижняя кнопка "Заказать"
    private final By lowerBookButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Кнопка "Да все привыкли"
    private final By okCookie = By.xpath(".//button[@class='App_CookieButton__3cvqF']");

    // Эмблема самоката
    private final By scooterLogo = By.xpath(".//img[@alt='Scooter']");

    // Эмблема яндекса
    private final By yandexLogo = By.xpath(".//img[@alt='Yandex']");

    // Секция вопросов
    private final By faqSection = By.xpath(".//div[@id='accordion__heading-0']");

    public MainPage(WebDriver driver, Actions actions){
        this.driver = driver;
        this.actions = actions;
    }

    // Метод кликает по верхней кнопке "Заказать"
    public void clickUpperBookButton() {
        driver.findElement(upperBookButton).click();
    }

    // Метод кликает по нижней кнопке "Заказать"
    public void clickLowerBookButton() {
        actions.scrollToElement(driver.findElement(lowerBookButton)).perform();
        driver.findElement(lowerBookButton).click();
    }

    // Метод кликает по кнопке "Статус заказа"

    public void clickStatusButton() throws InterruptedException {
        driver.findElement(statusButton).click();
        synchronized (driver) {
            driver.wait(1000);
        }
    }

    // Метод заполняет поле "Номер заказа"
    public void inputOrderNumber(String orderNumber) {
        driver.findElement(orderNumberInput).sendKeys(orderNumber);
    }

    // Метод кликает по кнопке "Go"
    public void clickGoButton() throws InterruptedException {
        driver.findElement(goButton).click();
        synchronized (driver) {
            driver.wait(1000);
        }
    }

    // Метод кликает по кнопке "Да все привыкли"
    public void clickCookieButton() {
        driver.findElement(okCookie).click();
    }

    // Метод скроллит до FAQ секции
    public void scrollToFAQSection() {
        actions.scrollToElement(driver.findElement(faqSection)).pause(1000).perform();
    }

    // Метод кликает по лого самоката
    public void clickScooterLogo() {
        driver.findElement(scooterLogo).click();
    }

    // Метод кликает по лого яндекса
    public void clickYandexLogo() {
        driver.findElement(yandexLogo).click();
    }

    // Метод возвращает элемент аккордеона с вопросом
    public By getAccordionHeader(String number) {
        return By.xpath(".//div[@id='accordion__heading-" + number + "']");
    }

    // Метод возвращает элемент аккордеона с ответом
    public By getAccordionValue(String number) {
        return By.xpath(".//div[@id='accordion__panel-" + number + "']");
    }
}