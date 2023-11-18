package kg.mega.rentserviceproject.configuration;

import java.util.regex.Pattern;

public class PatternConfig {
    public static final Pattern USERNAME_PATTERN = Pattern.compile("^[A-Za-z, 0-9]{4,16}$");

    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z0-9])(?=.*[^A-Za-z0-9]).{8,32}$");

    public static final Pattern PHONE_PATTERN = Pattern.compile("\\d{9,10}");

    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
}