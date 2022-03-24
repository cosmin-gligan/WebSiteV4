package siit.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class CustomValidators {

    public static void phoneNoValidator(String phoneNo) {
        if (phoneNo == null || phoneNo.trim().length() == 0) {
            throw new IllegalArgumentException("Phone number cannot be blank!");
        }

        char[] phoneNoDigits = phoneNo.toCharArray();
        for (int i = 0; i < phoneNoDigits.length; i++) {
            if (i == 0) {
                if (String.valueOf(phoneNoDigits[i]).equals("+"))
                    continue; // primul caracter e plus, trecem mai departe
            }
            NumberUtils.validateDigit(String.valueOf(phoneNoDigits[i]));
        }
    }

    public static void nameValidator(String name){
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("Name cannot be blank!");
        }
    }

    public static void emailValidator(String email){
        if (email == null || email.trim().length() == 0) {
            throw new IllegalArgumentException("E-mail cannot be blank!");
        }
        String emailFormate ="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\."
                + "[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

        Pattern p = Pattern.compile(emailFormate);

        if (!p.matcher(email).matches())
            throw new IllegalArgumentException("Email is not valid!");
    }

    public static void dateValidator(String date){
        if (date == null || date.trim().length() == 0)
            throw new IllegalArgumentException("Date cannot be blank!");
        try{
            LocalDate localDate = LocalDate.parse(date);
        }catch (DateTimeParseException e){
            throw new IllegalArgumentException("Date is not valid!");
        }
    }

    public static void dateValidator(LocalDate date){
        if (date == null)
            throw new IllegalArgumentException("Date cannot be blank!");
    }

}
