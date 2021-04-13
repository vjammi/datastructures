package ds.arrays;

/*
    Input: "Mr John Smith ", 13
    Output: "Mr%20John%20Smith"

    char[] a = {'M','r',' ',        'J','o','h','n',' '        ,'S','m','i','t','h',' '}
               {'M','r','%','2','0','J','o','h','n','%','2','0','S','m','i','t','h','%','2','0',}
 */

/*
    Approach #1: While copying elements an auxilary array replace the empty spaces with %20 -
    Approach #2: Read the elements of array from the back. When a space is encoutered, shift the elements to its right. O(n)
    Approach #3: Read the number of spaces in the array during the first/forward pass.
                 In the second/backward pass replace the element with the spaces and shoft the elements to the right.
 */
public class URLify {

    public static void main(String[] args){
        char[] a = {'M','r',' ','J','o','h','n',' ','S','m','i','t','h','\0','\0','\0','\0','\0','\0','\0','\0','\0','\0'};
        URLify urLify = new URLify();
        urLify.testReplaceAllSpaces(a);
    }

    private void testReplaceAllSpaces(char[] a) {
        //approachN(a);
        //approach3(a);
        replaceSpaces(a, a.length);
    }

    private void approach3(char[] a) {

        int l=0;
        int numOfSpaces=0;
        char previousChar = '$';
        char nextChar = '$';

        for (int i = 0; i < a.length-1 ; i++){
            if (i!=0) previousChar = a[i-1];
            nextChar = a[i+1];
            if( previousChar != ' ' && a[i] == ' ' && nextChar != ' ') {
                numOfSpaces++;
            }else if ( previousChar != ' ' && a[i] == ' ' && nextChar == ' ') {
                l = i;
            }
            print("'"+a[i]+"'");
        }
        print("l: "+l + " numOfSpaces: "+numOfSpaces);
        int newSize = ( (l - numOfSpaces) / numOfSpaces * 3);

        for (int i = newSize; i <=0 ; i--){
            char c = a[i];

            print("'"+a[i]+"'");
        }

    }

    void replaceSpaces(char[] a, int origLength) {
        int spaceCount = 0, newLastInx = 0, i = 0, lastInx = 0;
        for (i = 0; i < origLength; i++) {
            if (a[i] == ' ') {
                spaceCount++;
            }
            if (a[i] == '\0') {
                lastInx = i;
                break;
            }
        }

        print("spaceCount: " +spaceCount +" origLength " +origLength);
        newLastInx = (lastInx - spaceCount) + (spaceCount * 3);
        if (origLength < a.length) a[origLength] = '\0'; //End Array
        print("newLastInx: " +newLastInx +" origLength " +origLength);

        for (i = lastInx - 1; i >= 0; i-- ) {
            if (a[i] == ' ') {
                a[newLastInx - 1] = '0';
                a[newLastInx - 2] = '2';
                a[newLastInx - 3] = '%';
                newLastInx = newLastInx - 3;
            } else {
                a[newLastInx-1]=a[i];
                newLastInx--;
            }
        }
        Print.printCharArray(a);
        }

    private void approachN(char[] a) {
        char l = ' ';
        char m = ' ';
        char n = ' ';
        char o = ' ';

        for(int i = 0; i<a.length; i++){
            l = a[i];
            m = a[i+1];
            n = a[i+2];
            o = a[i+3];

            /*char lTemp = ' '; char mTemp = ' '; char nTemp = ' '; */
            print(a[i] + a[i+1] +a[i+2]+"");
            if (a[i] == 0){
                a[i+2] = a[i+1];
                a[i+1] = a[i];
                a[i] = '%';
            }
        }
    }

    private void print(String s) {
        System.out.println(" " +s +" ");
    }

}

