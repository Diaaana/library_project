package by.radomskaya.project.entity;

public class Librarian extends Entity {
    private String surname;
    private String name;
    private String middleName;
    private int shift;
    private String login;
    private String password;

    public Librarian() {}

    public Librarian(String surname, String name, String middleName, int shift, String login, String password) {
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.shift = shift;
        this.login = login;
        this.password = password;
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

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Librarian librarian = (Librarian) o;

        if (shift != librarian.shift) return false;
        if (!surname.equals(librarian.surname)) return false;
        if (!name.equals(librarian.name)) return false;
        if (!middleName.equals(librarian.middleName)) return false;
        if (!login.equals(librarian.login)) return false;
        return password.equals(librarian.password);
    }

    @Override
    public int hashCode() {
        int result = surname.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + middleName.hashCode();
        result = 31 * result + shift;
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", shift=" + shift +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
