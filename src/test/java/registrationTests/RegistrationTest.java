package registrationTests;

import com.codeborne.selenide.Selenide;
import data.DataGeneration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pages.registration.RegistrationPage;
import pages.components.RegistrationResultsModal;

import java.util.Date;

import static data.DataMap.uploadFile;

public class RegistrationTest extends TestBase {

    private DataGeneration data = new DataGeneration();
    private final RegistrationPage registrationPage = new RegistrationPage();
    private final RegistrationResultsModal registrationResultsModal = new RegistrationResultsModal();

    Date birthday = data.getBirthday();
    String dayOfBirth = data.getDayOfBirth(birthday);
    String monthOfBirth = data.getMonthOfBirth(birthday);
    String yearOfBirth = data.getYearOfBirth(birthday);
    String state = data.getState();
    String city = data.getCity(state);
    String firstName = data.getFirstName();
    String lastName = data.getLastName();
    String gender = data.getGender();
    String mobile = data.getMobile();
    String email = data.getEmail();
    String subject = data.getSubject();
    String hobby = data.getHobby();
    String address = data.getAddress();

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }

    @Test
    void successfulFillFormTest() {
        registrationPage.openPage()
                .removeAds()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setMobile(mobile)
                .setBirthDate(dayOfBirth,monthOfBirth,yearOfBirth)
                .setSubjects(subject)
                .setHobbies(hobby)
                .uploadPicture(uploadFile)
                .setAddress(address)
                .setStateAndCity(state, city)
                .submitForm();

        registrationPage
                .verifyResult("Student Name", firstName + " " + lastName)
                .verifyResult("Student Email", email)
                .verifyResult("Gender", gender)
                .verifyResult("Mobile", mobile)
                .verifyResult("Date of Birth", dayOfBirth + " " + monthOfBirth+ "," + yearOfBirth)
                .verifyResult("Subjects", subject)
                .verifyResult("Hobbies", hobby)
                .verifyResult("Picture", uploadFile)
                .verifyResult("Address", address)
                .verifyResult("State and City", state + " " + city);
    }

    @Test
    void successfulMinFormTest() {
        registrationPage.openPage()
                .removeAds()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setMobile(mobile)
                .submitForm();

        registrationPage.verifyResultsModalAppears()
                .verifyResult("Student Name", firstName + " " + lastName)
                .verifyResult("Gender", gender)
                .verifyResult("Mobile", mobile);
    }

    @Test
    void negativeMinFormTest() {
        registrationPage.openPage()
                .submitForm();
        registrationResultsModal.verifyModalAppearsNegativ();
    }
}
