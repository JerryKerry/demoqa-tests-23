package properties.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {

    @Test
    @Tag("demoqa")
    void systemProperties1Test() {
        System.setProperty("browser", "chrome");
        System.setProperty("version", "127.0");
        String browser = System.getProperty("browser");

        System.out.println(browser);
    }

    @Test
    @Tag("demoqa")
    void systemProperties4Test() {
        String browser = System.getProperty("browser", "firefox");
        System.out.println(browser);
    }

}