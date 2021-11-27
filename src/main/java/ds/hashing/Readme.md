Hash Table is a data structure which organizes data using hash functions in order to support quick insertion and search. In this article, we will take a look at the principle of the hash table.

### The Principle of Hash Table

The key idea of Hash Table is to use a hash function to map keys to buckets. To be more specific,
When we insert a new key, the hash function will decide which bucket the key should be assigned and the key will be stored in the corresponding bucket;
When we want to search for a key, the hash table will use the same hash function to find the corresponding bucket and search only in the specific bucket.

#### An Example
In the example, we use y = x % 5 as our hash function. Let's go through the insertion and search strategies using this example:
Insertion: we parse the keys through the hash function to map them into the corresponding bucket.
e.g. 1987 is assigned to bucket 2 while 24 is assigned to bucket 4.
Search: we parse the keys through the same hash function and search only in the specific bucket.
e.g. if we search for 1987, we will use the same hash function to map 1987 to 2. So we search in bucket 2 and we successfully find out 1987 in that bucket.
e.g. if we search for 23, will map 23 to 3 and search in bucket 3. And We find out that 23 is not in bucket 3 which means 23 is not in the hash table.


### Keys to Design a Hash Table
There are two essential factors that you should pay attention to when you are going to design a hash table.

### 1. Hash Function
The hash function is the most important component of a hash table which is used to map the key to a specific bucket. In the example in previous article, we use y = x % 5 as a hash function, where x is the key value and y is the index of the assigned bucket.
The hash function will depend on the range of key values and the number of buckets.

Converting a hashCode() to an array index. Since our goal is an array index, not a 32-bit integer, we combine hashCode() with modular hashing in our implementations to produce integers between 0 and M-1 as follows:

    private int hash(Key key) {
       return (key.hashCode() & 0x7fffffff) % M;
    }

The code masks off the sign bit (to turn the 32-bit integer into a 31-bit nonnegative integer) and then computing the remainder when dividing by M, as in modular hashing. 

Note:
It is an open problem to design a hash function. The idea is to try to assign the key to the bucket as uniform as you can. 
Ideally, a perfect hash function will be a one-one mapping between the key and the bucket. 
However, in most cases a hash function is not perfect and it is a tradeoff between the amount of buckets and the capacity of a bucket.

#### Compressing the hash code

    int hash = key.hashCode();      // get the hashcode of the key
    int index = compress(hash);     // compress it to an index

The hash code can be any integer between -2^31 and and 2^31 - 1. That's ~4 billion different possible hash codes. 
If you have, say, 40 hash table buckets, you need to "compress" those 4 billion integers down to the range 0-39.
A common way to do this is with the modulus operator %.  
a % b returns the remainder after dividing a by b. For example, 7 % 3 == 1.

    int compress(int hash) {
        return hash % numBuckets;
    }

### 2. Collision Resolution
Ideally, if our hash function is a perfect one-one mapping, we will not need to handle collisions. Unfortunately, in most cases, collisions are almost inevitable. For instance, in our previous hash function (y = x % 5), both 1987 and 2 are assigned to bucket 2. That is a collision.
A collision resolution algorithm should solve the following questions:

- How to organize the values in the same bucket?
- What if too many values are assigned to the same bucket?
- How to search a target value in a specific bucket?

These questions are related to the capacity of the bucket and the number of keys which might be mapped into the same bucket according to our hash function.
Let's assume that the bucket, which holds the maximum number of keys, has N keys.
Typically, if N is constant and small, we can simply use an array to store keys in the same bucket. If N is variable or large, we might need to use height-balanced binary search tree instead.

### Complexity Analysis
If there are M keys in total, we can achieve the space complexity of O(M) easily when using a hash table.
However, you might have noticed that the time complexity of hash table has a strong relationship with the design.
Most of us might have used an array in each bucket to store values in the same bucket. Ideally, the bucket size is small enough to be regarded as a constant. The time complexity of both insertion and search will be O(1).
But in the worst case, the maximum bucket size will be N. And the time complexity will be O(1) for insertion but O(N) for search.

#### The Principle of Built-in Hash Table
The typical design of built-in hash table is:

The key value can be any hashable type. And a value which belongs to a hashable type will have a hashcode. This code will be used in the mapping function to get the bucket index.
Each bucket contains an array to store all the values in the same bucket initially.
If there are too many values in the same bucket, these values will be maintained in a height-balanced binary search tree instead.
The average time complexity of both insertion and search is still O(1). And the time complexity in the worst case is O(logN) for both insertion and search by using height-balanced BST. It is a trade-off between insertion and search.



REFERENCES
https://algs4.cs.princeton.edu/34hash/
https://www.cpp.edu/~ftang/courses/CS240/lectures/hashing.htm
https://stackoverflow.com/questions/12684175/what-would-a-compress-method-do-in-a-hash-table