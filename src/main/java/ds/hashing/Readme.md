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

#### What does & 0x7fffffff mean?
The constant 0x7FFFFFFF is a 32-bit integer in hexadecimal with all but the highest bit set.
Despite the name, this method isn't getting the hashCode, rather looking for which bucket the key should appear in
for a getBucketIndex set or map.
When you use % on negative value, you get a negative value. There are no negative buckets so to avoid this
you can remove the sign bit (the highest bit) and

1. One way of doing this is to use a mask
   e.g.  x & 0x7FFFFFFF which keeps all the bits except the top one.

2. Another way to do this is to shift the output
   x >>> 1 however this is slower.

3. A slightly better approach is to use take the modulus and apply Math.abs.
   This uses all the bits of the hashCode which might be better.
```
    public int hashCode(char[] a){
        int hash = 17; // Start off with a prime number
        for (int i = 0; i < a.length; i++) {
            hash =  (31 * hash) + (int) a[i]; // Use the ascii value of the character at teh ith position of the array (a[])
        }
        return hash;
    }
```

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
    
#### Hash [hash(key)] To compute the Hash Value we will
  1. Use the system hashCode to compute the hash of the object
       key.hashCode()

  2. We will then make it positive by
       Ending on the sign bit [ key.hashCode() & 0x7fffffff ]  or
       Taking the absolute of the integer [ Math.abs( key.hashCode() )  ]

  3. Take the Modulus of the above value by M to get a number between 0 and M-1
       (key.hashCode() & 0x7fffffff) % M
       Math.abs( key.hashCode() ) % M
  
#### Runtime
A hashtable is O(1). The problem is if two keys are not equal, however they result in the same hash.
For example, if two strings string a and string b both resulted in a hash value 12345.
When the first string is inserted, it's put in bucket 12345. 
When the second string is inserted, it would see that a value already exists for bucket 12345. 
It would then compare the new value to the existing value, and see they are not equal. 
In this case, linked list is created for that key. 
In the worst case, if too many elements were hashed into the same key, retrieving this key may take O(n) time.
For this reason, when using a hash table, it's important to use a key with a good hash function that's both fast and doesn't often result in duplicate values for different objects.

Now, as an optimization instead of using a linked list, a balanced tree can be used to achieve lg(n) retrieval. 

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

#### Design HashSet using Separate Chaining
There are two key questions that one should address, in order to implement the HashSet data structure, namely hash function and collision handling.
- hash function: the goal of the hash function is to assign an address to store a given value. Ideally, each unique value should have a unique hash value.
- collision handling: since the nature of a hash function is to map a value from a space A into a corresponding value in a smaller space B, 
  it could happen that multiple values from space A might be mapped to the same value in space B. This is what we call collision. 
  Therefore, it is indispensable for us to have a strategy to handle the collision.

- Overall, there are several strategies to resolve the collisions:
  - Separate Chaining: for values with the same hash key, we keep them in a bucket, and each bucket is independent of each other.
  - Open Addressing: whenever there is a collision, we keep on probing on the main space with certain strategy until a free slot is found.
  - 2-Choice Hashing: we use two hash functions rather than one, and we pick the generated address with fewer collision.

We will use the strategy of separate chaining. Here is how it works overall.
- Essentially, the primary storage underneath a HashSet is a continuous memory as Array. Each element in this array corresponds to a bucket that stores the actual values.
- Given a value, first we generate a key for the value via the hash function. The generated key serves as the index to locate the bucket.
- Once the bucket is located, we then perform the desired operations on the bucket, such as add, remove and contains.

Approach 1: LinkedList as Bucket
Intuition
The common choice of hash function is the modulo operator, i.e. hash=valuemod  base\text{hash} = \text{value} \mod \text{base}hash=valuemodbase. Here, the base\text{base}base of modulo operation would determine the number of buckets that we would have at the end in the HashSet.
Theoretically, the more buckets we have (hence the larger the space would be), the less likely that we would have collisions. The choice of base\text{base}base is a tradeoff between the space and the collision.
In addition, it is generally advisable to use a prime number as the base of modulo, e.g. 769769769, in order to reduce the potential collisions.

As to the design of bucket, again there are several options. One could simply use another Array as bucket to store all the values. However, one drawback with the Array data structure is that it would take O(N)\mathcal{O}(N)O(N) time complexity to remove or insert an element, rather than the desired O(1)\mathcal{O}(1)O(1).
Since for any update operation, we would need to scan the entire bucket first to avoid any duplicate, a better choice for the implementation of bucket would be the LinkedList, which has a constant time complexity for the insertion as well as deletion, once we locate the position to update.

Algorithm
As we discussed in the above section, here we adopt the LinkedList to implement our bucket within the HashSet.
Essentially, we are implementing a LinkedList that does not contain any duplicate.
For each of the functions of add, remove and contains, we first generate the bucket index with the hash function. Then, we simply pass down the operation to the underlying bucket.

For a value that was never seen before, we insert it to the head of the bucket, though we could also append it to the tail. It is a choice that we made, which could fit better the scenario where redundant values are operated in nearby time windows, since it is more likely that we spot the value at the head of the bucket rather than walking through the entire bucket.

##### Complexity Analysis
Time Complexity
O(N/K) where N is the number of all possible values and K is the number of predefined buckets, which is 769.
Assuming that the values are evenly distributed, thus we could consider that the average size of bucket is N/K.
Since for each operation, in the worst case, we would need to scan the entire bucket, hence the time complexity is O(N/K).

Space Complexity: 
O(K+M) where K is the number of predefined buckets, and M is the number of unique values that have been inserted into the HashSet.

REFERENCES
https://algs4.cs.princeton.edu/34hash/
https://www.cpp.edu/~ftang/courses/CS240/lectures/hashing.htm
https://stackoverflow.com/questions/12684175/what-would-a-compress-method-do-in-a-hash-table