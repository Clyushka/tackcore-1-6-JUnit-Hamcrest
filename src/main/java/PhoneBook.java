import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private Map<String, List<Contact>> phoneBook;

    public PhoneBook() {
        this.phoneBook = new HashMap<>() {{
            put(null, new ArrayList<>());//contacts without groups
        }};
    }

    public boolean addGroup(String groupName) {
        if (groupName != null && !containsGroup(groupName)) {
            phoneBook.put(groupName, new ArrayList<>());
            return true;
        }
        return false;
    }

    public boolean addGroup(String groupName, Contact... contacts) {
        if (groupName != null && !containsGroup(groupName)) {
            phoneBook.put(groupName, new ArrayList<>());
            for (Contact c : contacts) {
                if (hasContactInGroup(null, c)) {
                    phoneBook.get(null).remove(c);
                }
                phoneBook.get(groupName).add(c);
            }
            return true;
        }
        return false;
    }

    //if groupName is null so contact has no group
    public boolean hasContactInGroup(String groupName, Contact c) {
        if (c != null && phoneBook.get(groupName).contains(c)) {
            return true;
        }
        return false;
    }

    public boolean addContact(Contact c, String... groupNames) {
        boolean result = true;
        for (String name : groupNames) {
            if (containsGroup(name) && !hasContactInGroup(name, c)) {
                if (hasContactInGroup(null, c)) {
                    phoneBook.get(null).remove(c);
                }
                phoneBook.get(name).add(c);
            } else {
                result = false;
            }
        }

        return result;
    }

    public boolean addContact(Contact c) {
        if (!hasContactInGroup(null, c)) {
            phoneBook.get(null).add(c);
            return true;
        }
        return false;
    }

    public boolean containsGroup(String groupName) {
        return phoneBook.containsKey(groupName);
    }

    public List<Contact> getGroupContacts(String groupName) {
        return phoneBook.get(groupName);
    }

    public List<Contact> getGrouplessContacts() {
        return getGroupContacts(null);
    }

    public Contact getContactByPhone(PhoneNumber phoneNumber) {
        for (List<Contact> groupContacts : phoneBook.values()) {
            for (Contact c : groupContacts) {
                if (c.equalsPhone(phoneNumber)) {
                    return c;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Телефонная книга:\n\n");
        for (String group : phoneBook.keySet()) {
            result.append(((group == null) ? "Без группы" : group) + ":\n");
            for (Contact c : phoneBook.get(group)) {
                result.append(c + "\n");
            }
            result.append("\n");
        }
        return result.toString();
    }
}