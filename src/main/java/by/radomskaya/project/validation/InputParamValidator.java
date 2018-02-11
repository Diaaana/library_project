package by.radomskaya.project.validation;

import by.radomskaya.project.constant.RegularExpressions;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParamValidator {

    private static boolean isCheckStringData(String data, String expression) {
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }

    public static boolean isValidateUserData(String surname, String name, String middleName, int age, String phone, String mail, String login, String password) {
        return isCheckStringData(surname, RegularExpressions.SURNAME_AND_MIDDLE_NAME_PATTERN) && isCheckStringData(name, RegularExpressions.NAME_PATTERN)
                && isCheckStringData(middleName, RegularExpressions.SURNAME_AND_MIDDLE_NAME_PATTERN) && isCheckStringData(phone, RegularExpressions.PHONE_NUMBER_PATTERN)
                && isCheckStringData(mail, RegularExpressions.MAIL_PATTERN) && isCheckStringData(login, RegularExpressions.LOGIN_PATTERN)
                && isCheckStringData(password, RegularExpressions.PASSWORD_PATTERN) && age != 0;
    }

    public static boolean isValidateAccountData(String surname, String name, String middleName, int age, String phone, String mail, String login) {
        return isCheckStringData(surname, RegularExpressions.SURNAME_AND_MIDDLE_NAME_PATTERN) && isCheckStringData(name, RegularExpressions.NAME_PATTERN)
                && isCheckStringData(middleName, RegularExpressions.SURNAME_AND_MIDDLE_NAME_PATTERN) && isCheckStringData(phone, RegularExpressions.PHONE_NUMBER_PATTERN)
                && isCheckStringData(mail, RegularExpressions.MAIL_PATTERN) && isCheckStringData(login, RegularExpressions.LOGIN_PATTERN)
                && age != 0;
    }

    public static boolean isValidateBookData(String isbn, String tittle, Date dateEdition, String placeEdition, String publisher, int numberCopies) {
        return isCheckStringData(isbn, RegularExpressions.INTEGER_PATTERN) && isCheckStringData(tittle, RegularExpressions.TITTLE_PATTERN)
                && isCheckStringData(String.valueOf(dateEdition), RegularExpressions.DATE_PATTERN) && isCheckStringData(placeEdition, RegularExpressions.PLACE_PATTERN)
                && isCheckStringData(publisher, RegularExpressions.PLACE_PATTERN) && numberCopies != 0;
    }

    public static boolean isValidateAuthorData(String surname, String name, String country) {
        return isCheckStringData(surname, RegularExpressions.SURNAME_AND_MIDDLE_NAME_PATTERN) && isCheckStringData(name, RegularExpressions.NAME_PATTERN)
                && isCheckStringData(country, RegularExpressions.PLACE_PATTERN);
    }

    public static boolean isValidateMiddleName(String middleName) {
        return isCheckStringData(middleName, RegularExpressions.SURNAME_AND_MIDDLE_NAME_PATTERN);
    }

    public static boolean isValidateLibrarianData(String surname, String name, String middleName, String login) {
        return isCheckStringData(surname, RegularExpressions.SURNAME_AND_MIDDLE_NAME_PATTERN) && isCheckStringData(name, RegularExpressions.NAME_PATTERN)
                && isCheckStringData(middleName, RegularExpressions.SURNAME_AND_MIDDLE_NAME_PATTERN) && isCheckStringData(login, RegularExpressions.LOGIN_PATTERN);
    }

    public static boolean isValidateOrderData(Date dateBorrow, Date dateReturn, String methodBorrow) {
        return isCheckStringData(String.valueOf(dateBorrow), RegularExpressions.DATE_PATTERN)
                && isCheckStringData(String.valueOf(dateReturn), RegularExpressions.DATE_PATTERN)
                && methodBorrow != null;
    }

}
