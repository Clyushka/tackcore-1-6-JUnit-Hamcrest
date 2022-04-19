import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MissedCallsJUnitTests {
    MissedCalls systemUnderTest;

    @BeforeEach
    public void init() {
        systemUnderTest = new MissedCalls();
        System.out.println("test is started");
    }

    @AfterEach
    public void afterTest() {
        System.out.println("test is finished");
    }

    @Test
    public void addMissedCallTest() {
        boolean isBothAdded = systemUnderTest.addMissedCall(
                LocalDateTime.of(2021, 1, 13, 22, 1, 45, 0),
                new PhoneNumber("83451238345"));
        isBothAdded &= systemUnderTest.addMissedCall(new PhoneNumber("83451238345"));
        String expected = "Пропущенные звонки:\n" +
                "2021-01-13 22:01:45 : +7 345-123-83-45\n" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " : +7 345-123-83-45\n";

        assertEquals(
                systemUnderTest.toString(),
                expected
        );
        assertTrue(isBothAdded);
    }

    @Test
    public void toStringTest() {
        String expected = "Пропущенные звонки:\n";

        assertEquals(
                systemUnderTest.toString(),
                expected
        );
    }

    @ParameterizedTest
    @MethodSource("phoneBookSourceFactory")
    public void setPhoneNumberTest(PhoneBook phoneBook) {
        //arrange
        String expected = "Пропущенные звонки:\n" +
                "2021-01-13 22:01:45 : Коллега\n" +
                "2022-02-01 10:50:30 : Рома\n" +
                "2022-02-01 10:50:30 : Парикмахер\n" +
                "2022-02-04 16:59:59 : Брат\n" +
                "2022-02-19 08:08:08 : Жена\n" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " : +7 987-456-87-45\n";

        boolean isAllAdded = systemUnderTest.addMissedCall(
                LocalDateTime.of(2022, 2, 1, 10, 50, 30, 0),
                new PhoneNumber("+79091237634"));
        isAllAdded &= systemUnderTest.addMissedCall(
                LocalDateTime.of(2022, 2, 1, 10, 50, 30, 404),
                new PhoneNumber("+7 919-547-76-60"));
        isAllAdded &= systemUnderTest.addMissedCall(
                LocalDateTime.of(2021, 1, 13, 22, 1, 45, 0),
                new PhoneNumber("83451238345"));
        isAllAdded &= systemUnderTest.addMissedCall(
                LocalDateTime.of(2022, 2, 4, 16, 59, 59, 999),
                new PhoneNumber("8919-600-76-00"));
        isAllAdded &= systemUnderTest.addMissedCall(
                LocalDateTime.of(2022, 2, 19, 8, 8, 8, 888),
                new PhoneNumber("9058760000"));
        isAllAdded &= systemUnderTest.addMissedCall(new PhoneNumber("9874568745"));
        //act
        String result = systemUnderTest.toString(phoneBook);
        //assert
        assertEquals(expected, result);
        assertTrue(isAllAdded);
    }

    public static Stream<Arguments> phoneBookSourceFactory() {
        List<Contact> contacts = new ArrayList<>() {{
            add(new Contact("Рома", "+79091237634"));
            add(new Contact("Катя", "+7 909 567 76 38"));
            add(new Contact("Парикмахер", "+7 919-547-76-60"));
            add(new Contact("Брат", "8919-600-76-00"));
            add(new Contact("Папа", "9068762498"));
            add(new Contact("Жена", "9058760000"));
            add(new Contact("Начальник", "+79873450987"));
            add(new Contact("Коллега", "83451238345"));
        }};

        PhoneBook phb = new PhoneBook();
        phb.addGroup("Друзья");
        phb.addGroup("Семья", contacts.get(3), contacts.get(4));
        phb.addGroup("Работа", contacts.get(6), contacts.get(7));
        phb.addContact(contacts.get(2));
        phb.addContact(contacts.get(5));
        phb.addContact(contacts.get(0), "Друзья");
        phb.addContact(contacts.get(1), "Друзья");
        phb.addContact(contacts.get(5), "Семья", "Работа");

        return Stream.of(Arguments.of(phb));
    }
}
