package registrationTests;

import data.DataGeneration;
import static com.codeborne.selenide.Selenide.sleep;

import org.junit.jupiter.api.Test;
import pages.registration.RegistrationPage;
import pages.components.RegistrationResultsModal;

import java.util.Date;

import static data.DataMap.uploadFile;

public class RegistrationTest extends TestBase {



    private DataGeneration data = new DataGeneration();
    private final RegistrationPage registrationPage = new RegistrationPage();
    private final RegistrationResultsModal registrationResultsModal = new RegistrationResultsModal();


    @Test
    void successfulFillFormTest() {

        Date birthday = data.getBirthday();
        String dayOfBirth = data.getDayOfBirth(birthday);
        String monthOfBirth = data.getMonthOfBirth(birthday);
        String yearOfBirth = data.getYearOfBirth(birthday);
        String state = data.getState();
        String city = data.getCity(state);

        registrationPage.openPage()
                .removeAds()
                .setFirstName(data.getFirstName())
                .setLastName(data.getLastName())
                .setEmail(data.getEmail())
                .setGender(data.getGender())
                .setMobile(data.getMobile())
                .setBirthDate(dayOfBirth,monthOfBirth,yearOfBirth)
                .setSubjects(data.getSubject())
                .setHobbies(data.getHobby())
                .uploadPicture(uploadFile)
                .setAddress(data.getAddress())
                .setStateAndCity(data.getState(), city)
                .submitForm();

        registrationPage
                .verifyResult("Student Name", data.getFirstName() + " " + data.getLastName())
                .verifyResult("Student Email", data.getEmail())
                .verifyResult("Gender", data.getGender())
                .verifyResult("Mobile", data.getMobile())
                .verifyResult("Date of Birth", dayOfBirth + " " + monthOfBirth+ "," + yearOfBirth)
                .verifyResult("Subjects", data.getSubject())
                .verifyResult("Hobbies", data.getHobby())
                .verifyResult("Picture", uploadFile)
                .verifyResult("Address", data.getAddress())
                .verifyResult("State and City", data.getState() + " " + city);
    }

    @Test
    void successfulMinFormTest() {
        registrationPage.openPage()
                .removeAds()
                .setFirstName(data.getFirstName())
                .setLastName(data.getLastName())
                .setGender(data.getGender())
                .setMobile(data.getMobile())
                .submitForm();
        registrationPage.verifyResultsModalAppears()
//                .verifyResult("Student Name", data.getFirstName() + " " + data.getLastName())
                .verifyResult("Gender", data.getGender())
                .verifyResult("Mobile", data.getMobile());
    }

    @Test
    void negativeMinFormTest() {
        registrationPage.openPage()
                .submitForm();
        registrationResultsModal.verifyModalAppearsNegativ();

    }
}
