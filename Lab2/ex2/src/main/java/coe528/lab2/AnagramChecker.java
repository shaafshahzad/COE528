package coe528.lab2;

import java.util.Arrays;

/**
 *
 * @author Shaaf Shahzad, #501172227
 */
public class AnagramChecker {

    // Requires: Requires two strings, str1 and str2.
    // Modifies: The two given strings are not modified, but they are assigned
    // to temporary character arrays to be modified for sorting and
    // comparing the two inputted strings.
    // Effects: On execution, determines if the two strings are anagrams of each
    // other, ignoring case sensitivity and non letter character, by
    // comparing the sorted versions.
    public static boolean areAnagrams(String str1, String str2) {

        if (str1 == null || str2 == null) {
            return false;
        }

        str1 = str1.toLowerCase().replaceAll("[^a-z]", "");
        str2 = str2.toLowerCase().replaceAll("[^a-z]", "");

        if (str1.length() != str2.length()) {
            return false;
        }

        char[] str1Array = str1.toCharArray();
        char[] str2Array = str2.toCharArray();

        Arrays.sort(str1Array);
        Arrays.sort(str2Array);

        return Arrays.equals(str1Array, str2Array);

    }

    public static void main(String[] args) {
        if (args.length == 1) {
            if (args[0].equals("1"))
                System.out.println(areAnagrams("listen", "silent"));
            else if (args[0].equals("2"))
                System.out.println(areAnagrams("Hello", "World"));
            else if (args[0].equals("3"))
                System.out.println(areAnagrams("Dormitory", "Dirty room"));
        }
    }

}
