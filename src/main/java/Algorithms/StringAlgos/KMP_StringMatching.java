package Algorithms.StringAlgos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sumit on 27/01/17.
 */
public class KMP_StringMatching {
    final String pattern;
    final int[] patternTraversalArr;


    public KMP_StringMatching(String pattern) {
        this.pattern = pattern;
        this.patternTraversalArr = generateTraversalArrayForString(this.pattern);
    }


    public int getFirstOccurenceOfPatternInText (final String text) {
        List<Integer> firstOccurenceList = occurencesOfPatternInText(text, true);
        if (firstOccurenceList.size() == 0) return -1;
        else return firstOccurenceList.get(0);
    }


    public List<Integer> getAllOccurencesOfPatternInText (final String text) {
        return occurencesOfPatternInText(text, false);
    }


    private List<Integer> occurencesOfPatternInText (final String text, boolean getFirstOccurenceOnly) {
        List<Integer> occurencesIndexList = new ArrayList<>();

        for (int j = 0, i = 0; i < text.length();) {
            if (text.charAt(i) == this.pattern.charAt(j)) {
                i++; j++;
                if (j == this.pattern.length()) {
                    occurencesIndexList.add(i - j);
                    if (getFirstOccurenceOnly) {
                        return occurencesIndexList;
                    }
                    i = i - j + 1;
                    j = 0;
                }
            } else {
                if (j != 0) {
                    j = this.patternTraversalArr[j - 1];
                } else {
                    i++;
                }
            }
        }

        return occurencesIndexList;
    }

    private int[] generateTraversalArrayForString (String pattern) {
        int[] arr = new int[pattern.length()];
        if (arr.length < 2) return arr;
        int i = 1;
        int j = 0;
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                arr[i] = j + 1;
                i++;
                j++;
            } else if (j != 0){
                j = arr[j - 1];
            } else {
                i++;
            }
        }
        return arr;
    }


    public static void main(String[] args) {
        String text = "abxabcabcabyabcaby";
        String pattern = "abcaby";

//        String text = "aaaaaa";
//        String pattern = "aa";

        KMP_StringMatching kmp_stringMatching = new KMP_StringMatching(pattern);
//        int[] arr = kmp_stringMatching.generateTraversalArrayForString("aabaabaaa");

//        int[] arr = kmp_stringMatching.generateTraversalArrayForString("abcdea");

        List<Integer> occurencesIndexList = kmp_stringMatching.getAllOccurencesOfPatternInText(text);

        for (Integer i : occurencesIndexList) {
            System.out.print(i + " ");
        }
        System.out.println();

    }
}
