

import org.junit.jupiter.api.Test;

public class RegistrationTest extends TestBase {

    @Test
    void successfulFillFormTest() {

        registrationPages.openPage()
                .setFirstName("Artem")
                .setLastName("Pupkin")
                .setEmail("l25dscx@mail.ru")
                .setGender("Male")
                .setNumber("9333494066")
                .setBirthDate("28", "June", "1992")
                .setSubjects("Maths")
                .setHobbies("Sports")
                .uploadPicture("1.jpg")
                .setAddress("Russia SPB")
                .selectStateAndCity("NCR","Delhi" )
                .submitForm();

        registrationPages.verifyResultsModalAppears()
                .verifyResult("Student Name", "Artem" + " Pupkin")
                .verifyResult("Student Email", "l25dscx@mail.ru")
                .verifyResult("Gender", "Male")
                .verifyResult("Mobile", "9333494066")
                .verifyResult("Date of Birth", "28 June,1992")
                .verifyResult("Subjects", "Maths")
                .verifyResult("Hobbies", "Sports")
                .verifyResult("Picture", "1.jpg")
                .verifyResult("Address", "Russia SPB")
                .verifyResult("State and City", "NCR Delhi");
    }

    @Test
    void successfulMinFormTest() {

        registrationPages.openPage()
                .setFirstName("Artem")
                .setLastName("Pupkin")
                .setGender("Male")
                .setNumber("9333494066")
                .submitForm();

        registrationPages.verifyResultsModalAppears()
                .verifyResult("Student Name", "Artem" + " Pupkin")
                .verifyResult("Gender", "Male")
                .verifyResult("Mobile", "9333494066");
    }

    @Test
    void negativeMinFormTest() {

        registrationPages.openPage()
                .setFirstName("iVAN")
                .setLastName("Ivanov")
                .setGender("Male")
                .setNumber("")
                .submitForm();

        registrationPages.verifyResultsModalAppearsNeg();
    }
}
