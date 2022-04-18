import java.util.ArrayList;
import java.util.List;

public class PhoneNumber {

    private String phoneNumber; //хранение: NNNNNNNNNN, вывод +7 NNN-NNN-NN-NN
    private static final List<String> PATTERNS = new ArrayList<>() {{
        add("[+]7[ -]?[0-9]{3}[ -]?[0-9]{3}[ -]?[0-9]{2}[ -]?[0-9]{2}");
        add("8[ -]?[0-9]{3}[ -]?[0-9]{3}[ -]?[0-9]{2}[ -]?[0-9]{2}");
        add("[0-9]{3}[ -]?[0-9]{3}[ -]?[0-9]{2}[ -]?[0-9]{2}");
    }};

    public PhoneNumber() {
        phoneNumber = "9999999999";
    }

    public PhoneNumber(String phoneNumber) {
        if (!this.setPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException();
        }
    }

    //for constructor and setter
    public static boolean checkPhone(String phoneNumber) {
        for (String pattern : PATTERNS) {
            if (phoneNumber.matches(pattern)) {
                return true;
            }
        }
        return false;
    }

    //use if checkPhone returned true in constructor and setter
    private static String formatPhoneToSet(String phoneNumber) {
        phoneNumber = phoneNumber.replace("+7", "");
        phoneNumber = phoneNumber.replace(" ", "");
        phoneNumber = phoneNumber.replace("-", "");
        if (phoneNumber.charAt(0) == '8') {
            return phoneNumber.substring(1);
        }
        return phoneNumber;
    }

    public String getPhoneNumber() {
        return this.toString();
    }

    public boolean setPhoneNumber(String phoneNumber) {
        if (checkPhone(phoneNumber)) {
            this.phoneNumber = formatPhoneToSet(phoneNumber);
            return true;
        } else {
            return false;
        }
    }

    //use for getter
    @Override
    public String toString() {
        return "+7 " + phoneNumber.substring(0, 3) +
                "-" + phoneNumber.substring(3, 6) +
                "-" + phoneNumber.substring(6, 8) +
                "-" + phoneNumber.substring(8);
        //+7 999-999-99-99
    }

    @Override
    public boolean equals(Object object) {
        //musthave checks
        if (this == object) {
            return true;
        }
        if (object == null || !object.getClass().equals(PhoneNumber.class)) {
            return false;
        }

        //field check
        PhoneNumber phoneNumber = (PhoneNumber) object;
        if (checkPhone(phoneNumber.phoneNumber) && this.phoneNumber.equals(phoneNumber.phoneNumber)) {
            return true;
        }
        return false;
    }
}
