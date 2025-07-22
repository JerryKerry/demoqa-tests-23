package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxPage {
    private SelenideElement header = $("h1"),
            userNameInput = $("#userName"),
            userEmailInput = $("#userEmail"),
            userCurrentAddressInput = $("#currentAddress"),
            userPermanentAddressInput = $("#permanentAddress"),
            submitButton = $("#submit"),
            outputField = $("#output");


    public TextBoxPage openPage() {
        open("/text-box");
        header.shouldHave(text("Text Box"));
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");
        return this;
    }

    public TextBoxPage setUserName(String value) {
        userNameInput.setValue(value);
        return this;
    }


    public TextBoxPage setUserEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    public TextBoxPage setUserCurrentAddress(String value) {
        userCurrentAddressInput.setValue(value);
        return this;
    }

    public TextBoxPage setUserPermanentAddress(String value) {
        userPermanentAddressInput.setValue(value);
        return this;
    }

    public TextBoxPage submitForm() {
        submitButton.click();
        return this;
    }

    public TextBoxPage checkResult(String key, String value) {
        outputField.$(By.id(key)).shouldHave(text(value));
        return this;
    }
}