package ds.bitmanipulations;

public class BitManipulations {

    public static void main(String[] args) {
        BitManipulations obj = new BitManipulations();
        //obj.givenBoolArray_whenMemoryLayout_thenConsumeMoreThanOneBit();
        obj.givenBitSet_whenMemoryLayout_thenConsumeOneBitPerFlag();
    }
    public void givenBoolArray_whenMemoryLayout_thenConsumeMoreThanOneBit() {
        boolean[] bits = new boolean[1024 * 1024];
        for (int i=0;i<bits.length; i++)
            System.out.print(bits[i] +" ");
    }

    public void givenBitSet_whenMemoryLayout_thenConsumeOneBitPerFlag() {
        java.util.BitSet bitSet = new java.util.BitSet(1024 * 1024);
        for (int i=0;i<bitSet.size(); i++)
            System.out.print(bitSet.get(i) +" ");
    }

    public void givenBitSet_whenSetting_thenShouldBeTrue() {
        java.util.BitSet bitSet = new java.util.BitSet();

        bitSet.set(10);
        System.out.println(bitSet.get(10));

        bitSet.set(20, 30);
        for (int i = 20; i <= 29; i++) {
            System.out.println(bitSet.get(i));
        }
        System.out.println(bitSet.get(30));

        bitSet.set(10, false);
        System.out.println(bitSet.get(10));//.isFalse();

        bitSet.set(20, 30, false);
        for (int i = 20; i <= 30; i++) {
            System.out.println(bitSet.get(i));//.isFalse();
        }
    }

    public void givenBitSet_whenClearing_thenShouldBeFalse() {
        java.util.BitSet bitSet = new java.util.BitSet();
        bitSet.set(42);
        System.out.println(bitSet.get(42));//.isTrue();

        bitSet.clear(42);
        System.out.println(bitSet.get(42));//.isFalse();

        bitSet.set(10, 20);
        for (int i = 10; i < 20; i++) {
            System.out.println(bitSet.get(i));//.isTrue();
        }

        bitSet.clear(10, 20);
        for (int i = 10; i < 20; i++) {
            System.out.println(bitSet.get(i));//.isFalse();
        }

        bitSet.set(10, 20);
        bitSet.clear();
        for (int i = 0; i < 100; i++) {
            System.out.println(bitSet.get(i));//.isFalse();
        }
    }

    public void givenBitSet_whenGettingElements_thenShouldReturnRequestedBits() {
        java.util.BitSet bitSet = new java.util.BitSet();
        bitSet.set(42);

        System.out.println(bitSet.get(42));//.isTrue();
        System.out.println(bitSet.get(43));//.isFalse();

        bitSet.set(10, 20);
        java.util.BitSet newBitSet = bitSet.get(10, 20);
        for (int i = 0; i < 10; i++) {
            System.out.println(newBitSet.get(i));//.isTrue();
        }
    }

    public void givenBitSet_whenFlip_thenTogglesTrueToFalseAndViceVersa() {
        java.util.BitSet bitSet = new java.util.BitSet();
        bitSet.set(42);
        bitSet.flip(42);
        System.out.println(bitSet.get(42));//.isFalse();

        bitSet.flip(12);
        System.out.println(bitSet.get(12));//.isTrue();

        bitSet.flip(30, 40);
        for (int i = 30; i < 40; i++) {
            System.out.println(bitSet.get(i));//.isTrue();
        }
    }

    public void givenBitSet_whenGettingTheSize_thenReturnsTheSize() {
        java.util.BitSet defaultBitSet = new java.util.BitSet();
        System.out.println(defaultBitSet.size());//.isEqualTo(64);

        java.util.BitSet bitSet = new java.util.BitSet(1024);
        System.out.println(bitSet.size());//.isEqualTo(1024);

        System.out.println(bitSet.cardinality());//.isEqualTo(0);
        bitSet.set(10, 30);
        System.out.println(bitSet.cardinality());//.isEqualTo(30 - 10);

        System.out.println(bitSet.length());//.isEqualTo(30);
        bitSet.set(100);
        System.out.println(bitSet.length());//.isEqualTo(101);

        System.out.println(bitSet.isEmpty());//.isFalse();
        bitSet.clear();
        System.out.println(bitSet.isEmpty());//.isTrue();
    }

    public void givenBitSet_whenSetOperations_thenShouldReturnAnotherBitSet() {
        java.util.BitSet first = new java.util.BitSet();
        first.set(5, 10);

        java.util.BitSet second = new java.util.BitSet();
        second.set(7, 15);

        System.out.println(first.intersects(second));//.isTrue();

        first.and(second);
        System.out.println(first.get(7));//.isTrue();
        System.out.println(first.get(8));//.isTrue();
        System.out.println(first.get(9));//.isTrue();
        System.out.println(first.get(10));//.isFalse();

        first.clear();
        first.set(5, 10);

        first.xor(second);
        for (int i = 5; i < 7; i++) {
            System.out.println(first.get(i));//.isTrue();
        }
        for (int i = 10; i < 15; i++) {
            System.out.println(first.get(i));//.isTrue();
        }
    }

    public void givenBitSet_whenStream_thenStreamsAllSetBits() {
        java.util.BitSet bitSet = new java.util.BitSet();
        bitSet.set(15, 25);

        bitSet.stream().forEach(System.out::println);
        System.out.println(bitSet.stream().count());//.isEqualTo(10);
    }

    public void givenBitSet_whenNextOrPrev_thenReturnsTheNextOrPrevClearOrSetBit() {
        java.util.BitSet bitSet = new java.util.BitSet();
        bitSet.set(15, 25);

        System.out.println(bitSet.nextSetBit(13));//.isEqualTo(15);
        System.out.println(bitSet.nextSetBit(25));//.isEqualTo(-1);

        System.out.println(bitSet.nextClearBit(23));//.isEqualTo(25);

        System.out.println(bitSet.previousClearBit(24));//.isEqualTo(14);
        System.out.println(bitSet.previousSetBit(29));//.isEqualTo(24);
        System.out.println(bitSet.previousSetBit(14));//.isEqualTo(-1);
    }
}
