package dev.vjammi.ds.v1.arrays;

public class OneAway {

    public boolean testOneAway(){
        checkOneCharAway("PALE","PLE");
        checkOneCharAway("PALL","PLE");
        return false;
    }

    private boolean checkOneCharAway(String a, String b) {
        if (a.length() == b.length()){
            return replaceCharFromAorB(a,b);
        }else if(a.length() + 1 == b.length()){
            return insertCharIntoB(a,b);
        }else if(a.length() - 1 == b.length()){
            return removeCharFromA(a,b);
        }else{
            return false;
        }
    }

    private boolean replaceCharFromAorB(String a, String b) {
        return false;
    }

    private boolean insertCharIntoB(String a, String b) {
        return false;
    }

    private boolean removeCharFromA(String a, String b) {
        int i=0, j=0;
        int distance = 0;
        char charToRemove = '\u0000';
        int charPosition = 0;
        while(i < a.length() && j < b.length()){
            if (a.charAt(i) == b.charAt(j)){
                i++; j++;
            }else {
                if (distance == 1){
                    System.out.println("First char diff: " +a.charAt(charPosition) + " Second Char diff:  " +a.charAt(i));
                    System.out.println("Returning " +false + " Original String A " +a +" String B: " + b);
                    return false;
                }
                charPosition = i; charToRemove = a.charAt(i);
                System.out.println("charPosition: " +charPosition +" charToRemove: " + charToRemove);
                i++;
                distance++;
            }
        }
        System.out.println("Returning " +true + "Original String A " +a +" String B: " + b);
        System.out.println("Returning " +true +"Modified String A " + removeChar(a, charPosition) +" String B: " + b);
        return true;
    }

    private String removeChar(String a, int charPosition) {
        char[] charArr = new char[a.length()];
        for (int i = 0; i< a.length(); i++){
            char c = a.charAt(i);
            if (i != charPosition) charArr[i] = c;
        }
        return new String(charArr);

    }

    public static void main (String[] agrs){
        OneAway oneAway = new OneAway();
        oneAway.testOneAway();

    }
}
