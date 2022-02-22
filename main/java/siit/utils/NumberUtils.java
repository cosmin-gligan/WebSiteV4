package siit.utils;

public class NumberUtils {

    public static void validateDigit(String sDigit){
        try {
            int digit = Integer.parseInt(sDigit);
        } catch (NumberFormatException e){
            throw new NumberFormatException(sDigit + " is NOT a valid digit");
        }
        return;
    }

}
