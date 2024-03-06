package edu.kirkwood.shared;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Helpers {
    public static long ageInYears(String birthDay) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.US);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US);
        LocalDate birthDate = null;
        try {
            birthDate = LocalDate.parse(birthDay, formatter1);
        } catch(DateTimeParseException e) {
            try {
                birthDate = LocalDate.parse(birthDay, formatter2);
            } catch(DateTimeParseException e2) {
                return 0;
            }
        }
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
