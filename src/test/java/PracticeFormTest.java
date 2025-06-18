import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {

    @BeforeAll
    static void basicBrowserSettings() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fullFormTest() {
        open("/automation-practice-form");

        $("#firstName").setValue("Artem");
        $("#lastName").setValue("Pupkin");

        $("#userEmail").setValue("l25dscx@mail.ru");

        $(byText("Female")).click();

        $("#userNumber").setValue("9333494066");

        $("#dateOfBirthInput").clear();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("2025");
        $$(".react-datepicker__day").findBy(text("18")).click();

        $("#subjectsInput").setValue("Math");
        $$(".subjects-auto-complete__option").findBy(text("Maths")).click();
        $("#subjectsInput").setValue("Phy");
        $$(".subjects-auto-complete__option").findBy(text("Physics")).click();
        $("#subjectsInput").setValue("Eng");
        $$(".subjects-auto-complete__option").findBy(text("English")).click();

        $("label[for='hobbies-checkbox-1']").click();
        $("label[for='hobbies-checkbox-3']").click();

        $("#uploadPicture").uploadFromClasspath("1.jpg");

        $("#currentAddress")
                .scrollTo()
                .shouldBe(visible, enabled)
                .setValue("Russia, SPB");

        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Delhi").pressEnter();
        $("#submit").click();

        $(".table-responsive").
                shouldHave(
                        text("Имя Фамилия"),
                        text("mailfortest@test.test"),
                        text("Male"),
                        text("8999999999"),
                        text("01 March,1995"),
                        text("Maths, Physics, Computer Science"),
                        text("Sports, Music"),
                        text("picture.png"),
                        text("Москва, Театральный проезд, 5с1"),
                        text("NCR Gurgaon")
                );
    }
}