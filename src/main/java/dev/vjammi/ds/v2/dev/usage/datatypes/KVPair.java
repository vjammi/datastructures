package dev.vjammi.ds.v2.dev.usage.datatypes;

public class KVPair {

    class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey(){ return key; }
        public V getValue() { return value; }
        public void setKey(K key)     { this.key = key; }
        public void setValue(V value) { this.value = value; }
    }

    public static void main(String[] args) {
        new KVPair().usage();
    }

    private void usage(){
        Pair<Integer, String> pair = new Pair(Integer.valueOf(1), "One");
        System.out.println(pair.key +" " + pair.value);
    }

}
