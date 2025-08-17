import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest{

    @BeforeAll
    static void basicBrowserSettings() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }

    @Test
    void fullFormTest() {
        open("/automation-practice-form");

        $("#firstName").setValue("Artem");
        $("#lastName").setValue("Pupkin");

        $("#userEmail").setValue("l25dscx@mail.ru");

        $("label[for='gender-radio-1']").click();

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

        $("#hobbiesWrapper").$(byText("Sports")).click();

        $("#hobbiesWrapper").$(byText("Music")).click();

        $("#uploadPicture").uploadFromClasspath("1.jpg");

        $("#currentAddress")
                .scrollTo()
                .shouldBe(visible, enabled)
                .setValue("Russia, SPB");

        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Delhi").pressEnter();
        $("#submit").pressEnter();

        $(".table-responsive").shouldHave(text("Artem Pupkin"));
        $(".table-responsive").shouldHave(text("l25dscx@mail.ru"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("9333494066"));
        $(".table-responsive").shouldHave(text("18 July,2025"));
        $(".table-responsive").shouldHave(text("Maths, Physics, English"));
        $(".table-responsive").shouldHave(text("Sports, Music"));
        $(".table-responsive").shouldHave(text("1.jpg"));
        $(".table-responsive").shouldHave(text("Russia, SPB"));
        $(".table-responsive").shouldHave(text("NCR Delhi"));
    }
}
