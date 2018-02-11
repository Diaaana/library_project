package by.radomskaya.project.constant;

public class RegularExpressions {
    public final static String NAME_PATTERN = "[A-ZА-Я][a-zа-я\\-]+";
    public final static String SURNAME_AND_MIDDLE_NAME_PATTERN = "[A-ZА-Я][a-zа-я]+";
    public final static String INTEGER_PATTERN = "[\\d]+";
    public final static String PHONE_NUMBER_PATTERN = "\\+|\\d[\\d\\(\\)\\ -]{4,14}\\d";
    public final static String MAIL_PATTERN = "[\\w-\\.]+@[\\w-]+\\.[a-z]{2,3}";
    public final static String LOGIN_PATTERN = "[A-Za-z][A-Za-z0-9_]+";
    public final static String PASSWORD_PATTERN = "(?=.*[a-z])(?=.*[A-Z]).{4,}";
    public final static String PLACE_PATTERN = "[A-ZА-Я][a-zа-я\\-\\s]+";
    public final static String TITTLE_PATTERN = "[A-ZА-Я0-9][a-zа-я0-9\\-\\№\\(\\)\\s]+";
    public final static String DATE_PATTERN = "(\\d{4})\\-(0\\d|1[012])\\-([0-2]\\d|3[01])";

}
