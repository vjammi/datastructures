package ds.patterns.usage.datatypes;

public class MyStringImpl {

    public static void main(String[] args) {
        String str = new String("ABC");
        //String str2 = new String(new char[]{ 'a', 'b', 'c'});

        int len = str.length();
        String strLower = str.toLowerCase();
        System.out.println(len + " " +strLower);

        Character[] charArr = new Character[]{'a', 'b', 'c'};

        StringBuilder sb = new StringBuilder("ABC");

        byte[] bytes =  new byte[]{'a', 'b', 'c'};
        byte[] strBytes = "abc".getBytes();
        System.out.println(bytes.toString() +" "+ strBytes.toString());


    }
}
