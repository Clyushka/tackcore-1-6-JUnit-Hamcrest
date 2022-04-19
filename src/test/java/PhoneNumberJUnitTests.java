import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PhoneNumberJUnitTests {
    private PhoneNumber systemUnderTest;

    @BeforeEach
    public void init() {
        systemUnderTest = new PhoneNumber();
        System.out.println("test is started");
    }

    @AfterEach
    public void afterTest() {
        System.out.println("test is finished");
    }

    @Test
    public void parametryzedConstructorIllegalStringTest() {
        //arrange
        String testString = "sdfghjkl";
        var expected = IllegalArgumentException.class;
        //act
        Executable action = () -> new PhoneNumber(testString);
        //assert
        assertThrows(expected, action);
    }

    @Test
    public void getPhoneNumberTest() {
        //arrange
        String expected = "+7 999-999-99-99";
        //act
        String result = systemUnderTest.getPhoneNumber();
        //assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("setParamsSourceFactory")
    public void setPhoneNumberTest(String phoneNumber, boolean expected) {
        //act
        boolean result = systemUnderTest.setPhoneNumber(phoneNumber);
        //assert
        assertEquals(expected, result);
    }

    public static Stream<Arguments> setParamsSourceFactory() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of("sdfghj", false),
                Arguments.of("9096784563", true),
                Arguments.of("+7 906 567 4567", true),
                Arguments.of("7 906 567 4567", false),
                Arguments.of("8-870-456-76-78", true),
                Arguments.of("+8-870-456-76-78", false),
                Arguments.of("909 567 43-78", true),
                Arguments.of("+79867653209", true),
                Arguments.of("8 876 6789054", true),
                Arguments.of("89876541234", true)
        );
    }
}
