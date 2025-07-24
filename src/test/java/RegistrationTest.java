import data.CalendarDate;
import data.DataGeneration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class RegistrationTest extends TestBase {

    private DataGeneration data;
    private final RegistrationPage.ResultModal resultModal = new RegistrationPage.ResultModal();
    private final RegistrationPage registrationPage = new RegistrationPage();

    @BeforeEach
    public void initTestData() {
        data = new DataGeneration();
    }

    @Test
    void successfulFillFormTest() {

        registrationPage.openPage()
                .setFirstName(data.getFirstName())
                .setLastName(data.getLastName())
                .setEmail(data.getEmail())
                .setGender(data.getGender())
                .setMobile(data.getMobile())
                .selectDateOfBirth(data.getDateOfBirth())
                .selectSubjects(data.getSubjects())
                .selectHobbies(data.getHobbies())
                .uploadPicture(data.getFilename())
                .setAddress(data.getCurrentAddress())
                .selectState(data.getState())
                .selectCity(data.getCity())
                .submitForm();

        resultModal.shouldHaveStudentName(data.getFirstName(), data.getLastName())
                .shouldHaveStudentName(data.getFirstName(), data.getLastName())
                .shouldHaveStudentEmail(data.getEmail())
                .shouldHaveGender(data.getGender())
                .shouldHaveMobile(data.getMobile())
                .shouldHaveDateOfBirth(data.getDateOfBirth())
                .shouldHaveSubjects(data.getSubjects())
                .shouldHaveHobbies(data.getHobbies())
                .shouldHavePicture(data.getFilename())
                .shouldHaveAddress(data.getCurrentAddress())
                .shouldHaveStateAndCity(data.getState(), data.getCity());
    }

    @Test
    void successfulMinFormTest() {

        registrationPage.openPage()
                .setFirstName(data.getFirstName())
                .setLastName(data.getLastName())
                .setGender(data.getGender())
                .setMobile(data.getMobile())
                .submitForm();

        resultModal.shouldHaveStudentName(data.getFirstName(), data.getLastName())
                .shouldHaveGender(data.getGender())
                .shouldHaveMobile(data.getMobile())
                .shouldHaveDateOfBirth(CalendarDate.now())
                .shouldHaveStudentEmail("")
                .shouldHaveSubjects("")
                .shouldHaveHobbies("")
                .shouldHavePicture("")
                .shouldHaveAddress("")
                .shouldHaveStateAndCity("", "");
    }

    @Test
    void negativeMinFormTest() {

        registrationPage.openPage()
                .submitForm();
      resultModal.shouldNotBeVisible();

    }
}
