package dev.vjammi.ds.v2.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatching {
    private void matchPattern() {
        Pattern pattern = Pattern.compile("^[a][a]$||^[b][b]$", Pattern.CASE_INSENSITIVE); //"^[a][a]$||[^[b][b]$]"
        Matcher matcher = pattern.matcher("a");
        boolean matchFound = matcher.find();
        if (matchFound) {
            System.out.println("Match found");
        } else {
            System.out.println("Match not found");
        }
    }

    public static void main(String[] args) {
        new PatternMatching().matchPattern();
    }
}
