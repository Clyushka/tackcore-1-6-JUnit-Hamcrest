import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
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

        System.out.println(phb);
        System.out.println();

        MissedCalls mc = new MissedCalls();
        mc.addMissedCall(
                LocalDateTime.of(2022, 2, 1, 10, 50, 30, 0),
                contacts.get(0).getPhoneNumber());
        mc.addMissedCall(
                LocalDateTime.of(2022, 2, 1, 10, 50, 30, 404),
                contacts.get(2).getPhoneNumber());
        mc.addMissedCall(
                LocalDateTime.of(2021, 1, 13, 22, 1, 45, 0),
                contacts.get(7).getPhoneNumber());
        mc.addMissedCall(
                LocalDateTime.of(2022, 2, 4, 16, 59, 59, 999),
                contacts.get(3).getPhoneNumber());
        mc.addMissedCall(
                LocalDateTime.of(2022, 2, 19, 8, 8, 8, 888),
                contacts.get(5).getPhoneNumber());
        mc.addMissedCall(contacts.get(5).getPhoneNumber());
        Thread.sleep(1000);
        mc.addMissedCall(contacts.get(3).getPhoneNumber());
        Thread.sleep(1000);
        mc.addMissedCall(contacts.get(7).getPhoneNumber());
        Thread.sleep(1000);
        mc.addMissedCall(new PhoneNumber("9874568745"));

        System.out.println(mc.toString(phb));
    }
}
