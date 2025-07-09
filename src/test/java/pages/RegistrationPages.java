package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.RegistrationResultsModal;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPages {
    CalendarComponent calendarComponent = new CalendarComponent();
    RegistrationResultsModal registrationResultsModal = new RegistrationResultsModal();
    private SelenideElement
            firstNameInput = $("#userName-wrapper #firstName"),
            lastNameInput = $("#userName-wrapper #lastName"),
            emailInput = $("#userEmail-wrapper #userEmail"),
            genderInput = $("#genterWrapper"),
            numberInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesInput = $("#hobbiesWrapper"),
            pictureInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            submitTab = $("#submit");


    public RegistrationPages openPage() {
        open("/automation-practice-form");
        //Убираем рекламу
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");

        return this;
    }

    public RegistrationPages setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPages setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPages setEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

    public RegistrationPages setGender(String value) {
        genderInput.$(byText(value)).click();

        return this;
    }

    public RegistrationPages setNumber(String value) {
        numberInput.setValue(value);

        return this;
    }

    public RegistrationPages setBirthDate(String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationPages setSubjects(String value) {
        subjectsInput.setValue(value).pressEnter();

        return this;
    }

    public RegistrationPages setHobbies(String value) {
        hobbiesInput.$(byText(value)).click();

        return this;
    }

    public RegistrationPages uploadPicture(String value) {
        pictureInput.uploadFromClasspath(value);

        return this;
    }

    public RegistrationPages setAddress(String value) {
        addressInput.setValue(value);

        return this;
    }

    public RegistrationPages selectStateAndCity(String state, String city) {
        stateInput.click();
        $(byText(state)).click();
        cityInput.click();
        $(byText(city)).click();

        return this;

    }

    public RegistrationPages submitForm() {
        submitTab.click();

        return this;
    }


    public RegistrationPages verifyResultsModalAppears() {
        registrationResultsModal.verifyModalAppears();

        return this;
    }

    public RegistrationPages verifyResult(String key, String value) {
        registrationResultsModal.verifyResult(key, value);

        return this;
    }

    public RegistrationPages verifyResultsModalAppearsNeg() {
        registrationResultsModal.verifyModalAppearsNegativ();

        return this;
    }
}