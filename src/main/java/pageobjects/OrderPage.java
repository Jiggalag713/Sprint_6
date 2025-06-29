package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private final WebDriver driver;

    // Поле ввода "Имя"
    private final By nameInput = By.xpath(".//input[@placeholder='* Имя']");

    // Поле ввода "Фамилия"
    private final By surnameInput = By.xpath(".//input[@placeholder='* Фамилия']");

    // Поле ввода "Адрес"
    private final By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    // Поле ввода "Станция метро"
    private final By metroStationDropdown = By.className("select-search__input");

    // Поле ввода "Телефон"
    private final By phoneNumberInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Кнопка "Далее"
    private final By proceedButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Поле с ошибкой
    private final By errorString = By.xpath(".//div[@class='Input_ErrorMessage__3HvIb Input_Visible___syz6']");

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    // Заполнить поле "Имя"
    public void inputName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    // Заполнить поле "Фамилия"
    public void inputSurname(String surname) {
        driver.findElement(surnameInput).sendKeys(surname);
    }

    // Заполнить поле "Адрес"
    public void inputAddress(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }

    // Заполнить поле "Станция метро"
    public void inputMetroStation(String station) {
        driver.findElement(metroStationDropdown).sendKeys(station, Keys.ARROW_DOWN, Keys.ENTER);
    }

    // Заполнить поле "Телефон"
    public void inputPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }

    // Нажать кнопку "Далее"
    public void clickProceedButton() {
        driver.findElement(proceedButton).click();
    }

    // Получить текст ошибки
    public String getErrorMessage() {
        return driver.findElement(errorString).getText();
    }

    // Заполнить форму заказа
    public void fulfillOrderForm(String name, String surname, String address, String metroStation, String phoneNumber) {
        inputName(name);
        inputSurname(surname);
        inputAddress(address);
        inputMetroStation(metroStation);
        inputPhoneNumber(phoneNumber);
        clickProceedButton();
    }
}
