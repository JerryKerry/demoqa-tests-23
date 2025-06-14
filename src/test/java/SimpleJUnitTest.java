import org.junit.jupiter.api.*;


public class SimpleJUnitTest {

    int result;

    @BeforeAll
    static void setUpBeforeClass() {
        System.out.println("Before All");
    }

    @AfterAll
    static void tearDownAfterClass() {
        System.out.println("After All");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Inside setUp method");
        result = getResult();
    }

    @AfterEach
    void tearDown() {
        System.out.println("Inside tearDown method");
        result = 0;
    }

    @Test
    void firstTest() {
        System.out.println("First Test");
        Assertions.assertTrue(result>2);
          }

    @Test
    void secondTest() {
        System.out.println("Second Test");
        Assertions.assertTrue(result>2);
    }

    @Test
    void thirdTest() {
        System.out.println("Third Test");
        Assertions.assertTrue(result>2);
    }

    private int getResult() {
        return 3;
    }
}

