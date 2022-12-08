import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AlphaCardTest {

    @Test
    void debitCardApplicationSuccessfully() {

        Configuration.holdBrowserOpen=true;
        Configuration.browserSize = "1800x1000";
        open("http://localhost:9999");

        $(By.cssSelector("#root > div > form > div:nth-child(1) > span > span > span.input__box > input")).setValue("Двойная Фамилия Имя");
        $(By.cssSelector("#root > div > form > div:nth-child(2) > span > span > span.input__box > input")).setValue("+01234567890");
        $(By.cssSelector("#root > div > form > div:nth-child(3) > label > span.checkbox__text")).click();
        $(By.cssSelector("#root > div > form > div:nth-child(4) > button > span > span")).click();
        $(By.cssSelector("#root > div > div > p")).shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void debitCardApplicationInvalidName() {

        Configuration.holdBrowserOpen=true;
        Configuration.browserSize = "1800x1000";
        open("http://localhost:9999");

        $(By.cssSelector("#root > div > form > div:nth-child(1) > span > span > span.input__box > input")).setValue("122121212121");
        $(By.cssSelector("#root > div > form > div:nth-child(2) > span > span > span.input__box > input")).setValue("+01234567890");
        $(By.cssSelector("#root > div > form > div:nth-child(3) > label > span.checkbox__text")).click();
        $(By.cssSelector("#root > div > form > div:nth-child(4) > button > span > span")).click();
        $(By.cssSelector("#root > div > form > div:nth-child(1) > span > span > span.input__sub")).shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void debitCardApplicationInvalidPhone() {

        Configuration.holdBrowserOpen=true;
        Configuration.browserSize = "1800x1000";
        open("http://localhost:9999");

        $(By.cssSelector("#root > div > form > div:nth-child(1) > span > span > span.input__box > input")).setValue("Фамилия Имя");
        $(By.cssSelector("#root > div > form > div:nth-child(2) > span > span > span.input__box > input")).setValue("%");
        $(By.cssSelector("#root > div > form > div:nth-child(3) > label > span.checkbox__text")).click();
        $(By.cssSelector("#root > div > form > div:nth-child(4) > button > span > span")).click();
        $(By.cssSelector("#root > div > form > div:nth-child(2) > span > span > span.input__sub")).shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

}

