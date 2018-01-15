package by.radomskaya.project.entity;

public class Reader extends Entity {
    private int numberTicket;
    private String surname;
    private String name;
    private String middleName;
    private int age;
    private String phoneNumber;
    private String mail;
    private String login;
    private String password;
    private String profilePhoto;

    public Reader() {}

    public Reader(int numberTicket, String surname, String name, String middleName, int age, String phoneNumber, String mail, String login, String password, String profilePhoto) {
        this.numberTicket = numberTicket;
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.login = login;
        this.password = password;
        this.profilePhoto = profilePhoto;
    }

    public int getNumberTicket() {
        return numberTicket;
    }

    public void setNumberTicket(int numberTicket) {
        this.numberTicket = numberTicket;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reader reader = (Reader) o;

        if (numberTicket != reader.numberTicket) return false;
        if (age != reader.age) return false;
        if (!surname.equals(reader.surname)) return false;
        if (!name.equals(reader.name)) return false;
        if (!middleName.equals(reader.middleName)) return false;
        if (!phoneNumber.equals(reader.phoneNumber)) return false;
        if (!mail.equals(reader.mail)) return false;
        if (!login.equals(reader.login)) return false;
        if (!password.equals(reader.password)) return false;
        return profilePhoto.equals(reader.profilePhoto);
    }

    @Override
    public int hashCode() {
        int result = numberTicket;
        result = 31 * result + surname.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + middleName.hashCode();
        result = 31 * result + age;
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + mail.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + profilePhoto.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "numberTicket=" + numberTicket +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mail='" + mail + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                '}';
    }
}
