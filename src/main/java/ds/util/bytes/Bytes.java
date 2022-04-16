package ds.usage;

import java.nio.charset.StandardCharsets;

public class Bytes {

    public static void main(String[] args) {
        byte[] byteArray1 = "1234567890".getBytes(StandardCharsets.UTF_8);
        System.out.println(byteArray1.length);

        byte[] byteArray2 = "ABCDEFGHIJ".getBytes(StandardCharsets.UTF_8);
        System.out.println(byteArray2.length);

        byte[] byteArray3 = new byte[4096];

        for (int i=0; i< byteArray3.length; i++) {
            System.out.print(" '" +byteArray3[i] +"' ");
        }
        System.out.println();
        byteArray2 = "abcdefghijklmnopqrstuvwxyz".getBytes();
        for (int i=0; i< byteArray2.length; i++) {
            System.out.print(" '" +byteArray3[i] +"'");
        }

    }
}
