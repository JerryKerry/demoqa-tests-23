import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

public class TextBoxTest extends TestBase {
    TextBoxPage textBoxPage = new TextBoxPage();
    @Test
    void fillFormTest() {
        textBoxPage.openPage()
                .setUserName("Artem")
                .setUserEmail("l25dscx@mail.ru")
                .setUserCurrentAddress("SPB")
                .setUserPermanentAddress("SPB")
                .submitForm()
                .checkResult("name", "Artem")
                .checkResult("email", "l25dscx@mail.ru")
                .checkResult("currentAddress", "SPB")
                .checkResult("permanentAddress", "SPB");
    }
}