package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class RentPage {
    private final WebDriver driver;

    // Поле "Когда привезти самокат"
    private final By startDateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    // Поле "Срок аренды"
    private final By rentDuration = By.xpath(".//div[@class='Dropdown-placeholder']");

    // Поле "Комментарий для курьера"
    private final By commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    // Кнопка "Заказать"
    private final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public RentPage(WebDriver driver){
        this.driver = driver;
    }

    // Кнопка "Да"
    private final By yesButton = By.xpath(".//button[(@class='Button_Button__ra12g Button_Middle__1CSJM') and (text()='Да')]");

    // Модальное окно заказа
    private final By successOrderText = By.xpath(".//div[@class='Order_Text__2broi']");

    // Выбрать дату, когда привезти самокат
    public void inputDate(String date) {
        driver.findElement(startDateInput).sendKeys(date, Keys.ENTER);
    }

    // Выбрать срок аренды
    public void inputDuration(String duration) {
        driver.findElement(rentDuration).click();
        driver.findElement(By.xpath(".//div[(@class='Dropdown-option') and text()='" + duration + "']")).click();
    }

    // Выбрать цвет
    public void setColor(Colors color) {
        switch (color) {
            case BLACK:
                driver.findElement(By.id("black")).click();
            case GREY:
                driver.findElement(By.id("grey")).click();
        }

    }

    // Ввести комментарий для курьера
    public void inputComment(String comment) {
        driver.findElement(commentInput).sendKeys(comment);
    }

    // Нажать кнопку "Заказать"
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    // Нажать кнопку "Да"
    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }

    // Метод возвращает текст из модального окна о заказе самоката
    public String getResultText() {
        return driver.findElement(successOrderText).getText();
    }

    // Метод заполняет поля формы
    public void fulfillRentForm(String date, String duration, Colors color, String comment) {
        inputDate(date);
        inputDuration(duration);
        setColor(color);
        inputComment(comment);
    }
}
