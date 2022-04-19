import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.Callable;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PhoneNumberHamcrestTests {
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
    public void parametrizedConstructorIllegalStringTest() {
        String testString = "sdfghjkl";
        assertThat(exceptionOf(() -> new PhoneNumber(testString)),
                instanceOf(IllegalArgumentException.class));
    }

    @Test
    public void getPhoneNumberTest() {
        assertThat(
                systemUnderTest.getPhoneNumber(),
                equalTo("+7 999-999-99-99")
        );
    }

    @ParameterizedTest
    @MethodSource("setParamsSourceFactory")
    public void setPhoneNumberTest(String phoneNumber, boolean expected) {
        assertThat(
                systemUnderTest.setPhoneNumber(phoneNumber),
                equalTo(expected)
        );
    }

    @Test
    public void setPhoneNumberTest() {
        assertThat(
                systemUnderTest.setPhoneNumber("+7 906 567 4567"),
                is(true)
        );
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

    public static Throwable exceptionOf(Callable<?> callable) {
        try {
            callable.call();
            return null;
        } catch (Throwable t) {
            return t;
        }
    }
}
