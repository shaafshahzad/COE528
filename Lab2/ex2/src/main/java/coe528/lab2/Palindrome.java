package coe528.lab2;

/**
 *
 * @author Shaaf Shahzad, #501172227
 */
public class Palindrome {

    // Requires: Requires a string 'a'.
    // Modifies: No modifications in this class.
    // Effects: On execution, returns false if string 'a' is null or empty.
    // If not null or empty, compare the start of the string to the end.
    // If they do not match, then return false. Otherwise return true.
    public static boolean isPalindrome(String a) {

        // if there are no letters or null, return false
        if (a == null || a.length() == 0) {
            return false;
        }

        // compare half of the string with the other half
        // if they do not match, then return false
        for (int i = 0; i < (a.length() / 2); i++) {
            if (a.charAt(i) != a.charAt(a.length() - 1 - i)) {
                return false;
            }
        }

        // otherwise return true
        return true;

    }

    public static void main(String[] args) {
        if (args.length == 1) {
            if (args[0].equals("1"))
                System.out.println(isPalindrome(null));
            else if (args[0].equals("2"))
                System.out.println(isPalindrome(""));
            else if (args[0].equals("3"))
                System.out.println(isPalindrome("deed"));
            else if (args[0].equals("4"))
                System.out.println(isPalindrome("abcd"));
        }
    }
}
