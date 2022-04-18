public class Contact {
    private String name;
    private PhoneNumber phoneNumber;

    public Contact(String name, String phoneNumber) throws NullPointerException, IllegalArgumentException {
        //check null
        if (name == null || phoneNumber == null) {
            throw new NullPointerException();
        }

        //set
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name + ": " + this.phoneNumber;
    }

    @Override
    public boolean equals(Object object) {
        //musthave checks
        if (this == object) {
            return true;
        }
        if (object == null || !object.getClass().equals(Contact.class)) {
            return false;
        }

        //field check
        Contact contact = (Contact) object;
        if (this.name.equals(contact.name) && this.phoneNumber.equals(contact.phoneNumber)) {
            return true;
        }
        return false;
    }

    public boolean equalsPhone(PhoneNumber phone) {
        return this.phoneNumber.equals(phone);
    }

    public PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getName() {
        return this.name;
    }
}