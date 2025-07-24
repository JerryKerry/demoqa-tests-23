package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.CalendarDate;
import pages.components.CalendarComponent;
import pages.components.RegistrationResultsModal;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPages {

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

    public RegistrationPages selectDateOfBirth(CalendarDate date) {
        CalendarComponent calendar = new CalendarComponent(dateOfBirthInput);
        calendar.click();
        calendar.setDate(date);

        return this;
    }

    public RegistrationPages selectSubjects(String... subjects) {
        for (String subject : subjects) {
            subjectsInput.setValue(subject).pressEnter();
        }
        return this;
    }

    public RegistrationPages selectHobbies(String... hobbies) {
        for (String hobby : hobbies) {
            hobbiesInput.$(byText(hobby)).click();
        }
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

    public RegistrationPages setMobile(String mobile) {
        numberInput.setValue(mobile);
        return this;
    }

    public RegistrationPages selectState(String state) {
        stateInput.click();
        stateInput.$$("div")
                .filterBy(Condition.exactText(state))
                .first()
                .click();
        return this;
    }

    public RegistrationPages selectCity(String city) {
        cityInput.click();
        cityInput.$$("div")
                .filterBy(Condition.exactText(city))
                .first()
                .click();
        return this;
    }

    public void submitForm() {
        submitTab.click();
    }

    public static class ResultModal {
        private static final String STUDENT_NAME_LABEL = "Student Name";
        private static final String STUDENT_EMAIL_LABEL = "Student Email";
        private static final String GENDER_LABEL = "Gender";
        private static final String MOBILE_LABEL = "Mobile";
        private static final String DATE_OF_BIRTH_LABEL = "Date of Birth";
        private static final String SUBJECTS_LABEL = "Subjects";
        private static final String HOBBIES_LABEL = "Hobbies";
        private static final String PICTURE_LABEL = "Picture";
        private static final String ADDRESS_LABEL = "Address";
        private static final String STATE_AND_CITY_LABEL = "State and City";

        RegistrationResultsModal result = new RegistrationResultsModal();

        public ResultModal shouldHaveStudentName(String firstNae, String lastNae){
            result.verifyResult(STUDENT_NAME_LABEL, firstNae + " " + lastNae);
            return this;
        };

        public ResultModal shouldHaveStudentEmail(String email) {
            result.verifyResult(STUDENT_EMAIL_LABEL, email);
            return this;
        }

        public ResultModal shouldHaveGender(String gender) {
            result.verifyResult(GENDER_LABEL, gender);
            return this;
        }

        public ResultModal shouldHaveMobile(String mobile) {
            result.verifyResult(MOBILE_LABEL, mobile);
            return this;
        }

        public ResultModal shouldHaveDateOfBirth(CalendarDate date) {
            String formattedDate = String.format("%02d %s,%s", date.day(), date.month(), date.year());
            result.verifyResult(DATE_OF_BIRTH_LABEL, formattedDate);
            return this;
        }

        public ResultModal shouldHaveSubjects(String... subjects) {
            result.verifyResult(SUBJECTS_LABEL, String.join(", ", subjects));
            return this;
        }

        public ResultModal shouldHaveHobbies(String... hobbies) {
            result.verifyResult(HOBBIES_LABEL, String.join(", ", hobbies));
            return this;
        }

        public ResultModal shouldHavePicture(String filename) {
            result.verifyResult(PICTURE_LABEL, filename);
            return this;
        }

        public ResultModal shouldHaveAddress(String address) {
            result.verifyResult(ADDRESS_LABEL, address);
            return this;
        }

        public ResultModal shouldHaveStateAndCity(String state, String city) {
            result.verifyResult(STATE_AND_CITY_LABEL, state + " " + city);
            return this;
        }
        public void shouldNotBeVisible() {
            result.shouldNotBeVisible();
        }
    }
}