package dev.vjammi.ds.v1.hashing.hashset;

import java.util.LinkedList;

class DesignHashSet {
    private final Bucket[] bucketArray;
    private final int keyRange;

    public DesignHashSet() {
        this.keyRange = 769;
        this.bucketArray = new Bucket[this.keyRange];
        for (int i = 0; i < this.keyRange; ++i)
            this.bucketArray[i] = new Bucket();
    }

    protected int _hash(int key) {
        return (key % this.keyRange);
    }

    public void add(int key) {
        int bucketIndex = this._hash(key);
        this.bucketArray[bucketIndex].insert(key);
    }

    public void remove(int key) {
        int bucketIndex = this._hash(key);
        this.bucketArray[bucketIndex].delete(key);
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        int bucketIndex = this._hash(key);
        return this.bucketArray[bucketIndex].exists(key);
    }

    public static void main(String[] args) {
        // Your MyHashSet object will be instantiated and called as such:
        DesignHashSet obj = new DesignHashSet();
        obj.add(1);
        obj.remove(1);
        boolean param_3 = obj.contains(1);
    }
}


class Bucket {
    private final LinkedList<Integer> container;

    public Bucket() {
        container = new LinkedList<Integer>();
    }

    public void insert(Integer key) {
        int index = this.container.indexOf(key);
        if (index == -1) {
            this.container.addFirst(key);
        }
    }

    public void delete(Integer key) {
        this.container.remove(key);
    }

    public boolean exists(Integer key) {
        int index = this.container.indexOf(key);
        return (index != -1);
    }
}

