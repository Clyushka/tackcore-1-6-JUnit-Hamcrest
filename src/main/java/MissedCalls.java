import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

public class MissedCalls {
    Map<LocalDateTime, PhoneNumber> missedCalls;
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public MissedCalls() {
        this.missedCalls = new TreeMap<>();
    }

    public boolean addMissedCall(PhoneNumber phoneNumber) {
        if (phoneNumber != null) {
            this.missedCalls.put(LocalDateTime.now(), phoneNumber);
            return true;
        }
        return false;
    }

    public boolean addMissedCall(LocalDateTime dt, PhoneNumber phoneNumber) {
        if (dt != null && phoneNumber != null) {
            this.missedCalls.put(dt, phoneNumber);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Пропущенные звонки:\n");
        for (LocalDateTime dt : this.missedCalls.keySet()) {
            result.append(dt.format(DATE_TIME_FORMATTER) + " : " + missedCalls.get(dt).toString() + "\n");
        }
        return result.toString();
    }

    public String toString(PhoneBook phb) {
        StringBuilder result = new StringBuilder("Пропущенные звонки:\n");
        Contact tempContact;
        for (LocalDateTime dt : this.missedCalls.keySet()) {
            tempContact = phb.getContactByPhone(missedCalls.get(dt));
            result.append(dt.format(DATE_TIME_FORMATTER) + " : " + ((tempContact == null) ? missedCalls.get(dt).toString() : tempContact.getName()) + "\n");
        }
        return result.toString();
    }
}
