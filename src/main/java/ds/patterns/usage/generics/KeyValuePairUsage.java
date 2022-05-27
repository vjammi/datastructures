package ds.patterns.usage.generics;

public class KeyValuePairUsage {

    class MyPair<K, V> {
        private K key;
        private V value;

        public MyPair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey(){ return key; }
        public V getValue() { return value; }
        public void setKey(K key)     { this.key = key; }
        public void setValue(V value) { this.value = value; }
    }

    private void usage(){
        MyPair<Integer, String> pair = new MyPair(Integer.valueOf(1), "One");
        System.out.println(pair.key +" " + pair.value);
    }

    public static void main(String[] args) {
        new KeyValuePairUsage().usage();
    }

}
