import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AlphaCardTest {

    @BeforeEach
    public void eachTest() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1800x1000";
        open("http://localhost:9999");
    }

    @Test
    void debitCardApplicationSuccessfully() {

        $(By.cssSelector("[data-test-id='name'] input")).setValue("Двойная Фамилия Имя");
        $(By.cssSelector("[data-test-id='phone'] input")).setValue("+01234567890");
        $(By.cssSelector("[data-test-id='agreement']")).click();
        $(By.cssSelector("span.button__text")).click();
        $(By.cssSelector("[data-test-id='order-success']")).shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void debitCardApplicationInvalidName() {

        $(By.cssSelector("[data-test-id='name'] input")).setValue("122121212121");
        $(By.cssSelector("[data-test-id='phone'] input")).setValue("+01234567890");
        $(By.cssSelector("[data-test-id='agreement']")).click();
        $(By.cssSelector("span.button__text")).click();
        $(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void debitCardApplicationInvalidPhone() {

        $(By.cssSelector("[data-test-id='name'] input")).setValue("Фамилия Имя");
        $(By.cssSelector("[data-test-id='phone'] input")).setValue("%");
        $(By.cssSelector("[data-test-id='agreement']")).click();
        $(By.cssSelector("span.button__text")).click();
        $(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void debitCardApplicationCheckBoxFalse() {

        $(By.cssSelector("[data-test-id='name'] input")).setValue("Двойная-Фамилия Имя");
        $(By.cssSelector("[data-test-id='phone'] input")).setValue("+01234567890");
        $(By.cssSelector("span.button__text")).click();
        $(By.cssSelector("[data-test-id='agreement'].input_invalid")).shouldBe(Condition.visible);
    }

    @Test
    void debitCardApplicationEmptyName() {

        $(By.cssSelector("[data-test-id='phone'] input")).setValue("+01234567890");
        $(By.cssSelector("[data-test-id='agreement']")).click();
        $(By.cssSelector("span.button__text")).click();
        $(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void debitCardApplicationEmptyPhone() {

        $(By.cssSelector("[data-test-id='name'] input")).setValue("Фамилия Имя");
        $(By.cssSelector("[data-test-id='agreement']")).click();
        $(By.cssSelector("span.button__text")).click();
        $(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).shouldHave(exactText("Поле обязательно для заполнения"));
    }

}
