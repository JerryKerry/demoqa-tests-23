import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class PracticeFormTest {

    @BeforeAll
    static void basicBrowserSettings() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("version", "128.0");
        Configuration.browserSize = System.getProperty("resolution","1920x1080");
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.addVideo();
        Attach.browserConsoleLogs();
    }

    @Tag("demoqa")
    @Test
    void fullFormTest() {
        step("Open form", () -> {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()");
        });
        step("Fill form", () -> {

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
        });

        step("Verify results", () -> {
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
        });
    }
}

