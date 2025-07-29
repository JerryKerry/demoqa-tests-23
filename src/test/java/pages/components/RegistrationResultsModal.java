package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationResultsModal {

    private SelenideElement
            modalContent = $(".modal-content"),
            table = $(".table-responsive");

    public void verifyModalAppears() {
        modalContent.shouldHave(text("Thanks for submitting the form"));
    }

    public void verifyResult(String label, String expectedValue) {
        table.$(byText(label))
                .parent().shouldHave(text(expectedValue));
    }
        public void verifyModalAppearsNegativ () {
            modalContent.shouldNot();
        }

}