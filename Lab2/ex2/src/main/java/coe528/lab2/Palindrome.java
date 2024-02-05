package coe528.lab2;

public class Palindrome {

    // Requires: <Write the Requires clause here>
    // Modifies: <Write the Modifies clause here>
    // Effects: <Write the Effects clause here>
    public static boolean isPalindrome(String a) {

        if (a == null) {
            return true;
        }

        int n = a.length();
        for (int i = 0; i < (n / 2); ++i) {
            if (a.charAt(i) != a.charAt(n - i - 1)) {
                return false;
            }
        }
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
