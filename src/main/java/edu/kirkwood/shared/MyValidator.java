package edu.kirkwood.shared;

import java.util.regex.Pattern;

public class MyValidator {
    
    public static Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$");
    
    // requires min 8 characters, 3 of 4 (uppercase, lowercase, number, or character)
    public static Pattern passwordPattern = Pattern.compile("^(?:(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[^A-Za-z0-9])(?=.*[a-z])|(?=.*[^A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))(?!.*(.)\\1{2,})[A-Za-z0-9~`!\\@#\\$%\\^&*()_\\-+={}\\[\\]\\|\\\\:;\"'<>,.?\\/]{8,128}$");

    public static Pattern languagePattern = Pattern.compile("^(en-US|fr-FR|es-MX)$");

    public static Pattern phonePattern =  Pattern.compile("^\\D?(\\d{3})\\D?\\D?(\\d{3})\\D?(\\d{4})$");
    public static Pattern birthdayPattern = Pattern.compile("^(?:(?:(?:0?[13578]|1[02])(\\/|-|\\.)31)\\1|(?:(?:0?[1,3-9]|1[0-2])(\\/|-|\\.)(?:29|30)\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:0?2(\\/|-|\\.)29\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:(?:0?[1-9])|(?:1[0-2]))(\\/|-|\\.)(?:0?[1-9]|1\\d|2[0-8])\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
}
