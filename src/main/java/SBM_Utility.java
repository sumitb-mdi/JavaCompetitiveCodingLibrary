import java.io.PrintWriter;
import java.util.List;

/**
 * Created by sumit on 12/01/17.
 */
public class SBM_Utility {
    private static PrintWriter printWriter = new PrintWriter(System.out);
    public static <T> void printArrayWithSeparator (final T[] arr, final String separator) {
        if (arr.length > 0) {
            printWriter.print(arr[0]);
            for (int i = 0; i < arr.length; i++) {
                printWriter.print(separator + arr[i]);
            }
            printWriter.println();
        }
        printWriter.flush();
    }

    public static <T> void printArraySpaceSeparated (T[] arr) {
        printArrayWithSeparator(arr, " ");
    }


    public static <T> void printListWithSeparator (final List<T> list, final String separator) {
        if (list.size() > 0) {
            printWriter.print(list.get(0));
            for (int i = 1; i < list.size(); i++) {
                printWriter.print(separator + list.get(i));
            }
            printWriter.println();
        }
        printWriter.flush();
    }


    public static int[] getFrequencyOfCharactersInString (String str, int startingChar, int totalUniqueChars) {
        int[] frequency = new int[totalUniqueChars];
        for (int i = 0; i < str.length(); i++) {
            frequency[str.charAt(i) - startingChar] += 1;
        }
        return frequency;
    }

    public static int[] getFrequencyOfSmallAlphabetsInString (final String str) {
        return getFrequencyOfCharactersInString(str, (int)'a', 26);
    }

    public static int[] getFrequencyOfCapitalAlphabetsInString (final String str) {
        return getFrequencyOfCharactersInString(str, (int)'A', 26);
    }
}
