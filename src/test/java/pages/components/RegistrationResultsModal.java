package pages.components;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationResultsModal {
    private SelenideElement
            modalContent = $(".modal-content");

    public void verifyResult(String label, String expectedValue) {
        SelenideElement valueElement = modalContent.$$("table tr")
                .findBy(text(label))
                .$$("td")
                .get(1);

        if (expectedValue.trim().isEmpty()) {
            valueElement.shouldBe(empty);
        } else {
            valueElement.shouldHave(exactText(expectedValue));
        }
    }

    public void shouldNotBeVisible() {
        modalContent.shouldNot(visible);
    }
}