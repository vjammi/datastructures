# Runtime Complexity Analysis

## Common Classifications for Time and Space Complexity

### 1. Constant
```
// Code example here...
```

### 2. Logarithmic O(log(n))
- Logarithmic is opposite of exponent ```log4(32) = 5```
- Exponent (to the power) is repeated multiplication ```2^5 = 32```
- Logarithmic of a base is repeated division by the base.
```
    4^3 = 64
    log4(64) = 3
    4 to the 3rd power is 64

    2^5 = 32
    log2(32) = 5 [16/2 > 8/2 > 4/2 > 2/2 > 1 ]
    2 the 5th power is 32
```
- Logarithmic does not grow very fast
```
    n           log2(n)         n*log2(n)       n*n
    ------------------------------------------------
    1                           0               1
    2           1               2               4
    4           1               8               16
    8           3               24              64
    16          4               64              256
    32          5               128
    ...         ...             ...             ...
    1024        10              10240           1048576
```

### 3. Linear - O(n)
We have to evaluate n function calls
```
// Code example here...
```


### 4. Linear Logarithmic / LogLinear - O(n*log(n))
```
        n* log(n)
        _
       |               abcdefgh                     4
log(n) |         abcd            efgh               4+4
       |     ab     cd       ef      gh             2+2+2+2
       |_   a  b   c  d     e  f    g   h           1+1+1+1+1+1+1+1
```
```
public void foo(String[] arr){
    String str = "";
    for (int i=0; i<str.length(); i++){
        str = str + array[i];
    }

    if (arr.length <= 1)
        return;

    int midIndex = arr.length / 2;
    int left    = arr.subString(0, mid);
    foo(left);
    int right   = arr.subString(mid, arr.length()-1);
    foo(right);
}
```

### 5. Polynomial - O(n^c)
- O(n^c) where, c is a constant. For e.g. ```n^2, n^3```
- An example of nested for loop with ```O(n*n)``` runtime complexity
```
    void foo(int n){
        for (int i=0; i<n; i++){
            for (int i=0; i<n; i++){
                // print...
            }
        }
    }
```

### 6. Exponential O(c^n)
- O(c^n) where c is a constant and n is the size of the input. For e.g. ```2^n or 3^n```
- ```2^n``` A single call to foo, makes 2 further recursive calls
```
    void foo(int n){
        if (n==1) return;
        foo(n-1);
        foo(n-1);
    }
    foo(4);
```
The visual for the above recursive function is the tree below and has an exponential runtime.
In particular its ``` O(2^n) ``` because from one level to the next we double the number of nodes.
For n = 4321, the recursive call stack will be the height of the tree which will be n.

The space required on the call stack will be the maximum stack depth.
Though we need to evaluate 2^n  recursive calls in total but at any point of time there will be a max of only n items on the call stack.
For n = 4321, the recursive call stack will be the height of the tree which will be n.
Time Complexity = 2^n, which is the total number of function calls
Space Complexity = n, which is the number of digits on the stack.
```
                        4                                    h = 1
                3               3                            h = 2
            2       2       2        2                       h = 3
          1   1   1   1   1   1   1     1                    h = 4

        2^n function calls = 2*2 ... *2
        2^4 function calls  = 2*2*2*2 = 16
```

- ```3^n``` A single call to foo makes 3 further recursive calls
```
    void foo(int n){
        if (n==1) return;
        foo(n-1);
        foo(n-1);
        foo(n-1);
    }
    foo(4);
```

- ```2^n/2 = 2^n/2 ``` A single call to foo makes 2 further recursive calls, but the height of the tree is going to be half as tall asd the earlier.
```Branching factor of 2```
```Height is half of n```
```
    void foo(int n){
        if (n == 1) return;
        foo(n - 2);
        foo(n - 2);
    }
    foo(4);
```

### 7. Factorial (n!)
- The key characteristic of this function is within each call to foo, we make n further recursive calls from within the for loop.
- In an exponential function, we branch out a constant number of times.
- However, in a factorial we branch out n number of times.
```
n! = n * (n-1) * (n-2) ... (2) * (1)
4! = 4 * 3 * 2 * 1
```
```
                                 4                                      4
                  3         3         3         3                       3
          2       2       2                                             2
        1   1   1   1   1   1                                           1
```


```
    void foo(int n){
        if (n == 1) return;
        for (int i=0; i<n; i++){        // Within the for loop we iterate n times.
            foo(n - 1);                 // within the fir loop we make multiple recurssive calls
        }
    }
    foo(4);
```



## Comparisons
```
n           log2(n)         n*log2(n)       n*n         n*n*n       2^n
-----------------------------------------------------------------------
1                           0               1           1           2
2           1               2               4           8           4
3                                           9                       8
4           1               8               16          64          16
5                                           25          125         32
6                                           36                      64
7                                           49
8           3               24              64
16          4               64              256
32          5               128
...         ...             ...             ...         ...         ...
1024        10              10240           1048576
```


## Analyzing Multi-Argument Function - O(m+n)
- Here we have loops after each other, hence ```O(m+n)```
```
    void foo(int m, int n){
        for (int i=0; i<m; i++){
            // print....
        }
        for (int i=0; i<n; i++){
            // print...
        }
    }
```
Here we have nested for loops, hence ```O(m*n)```
```
    void foo(int m, int n){
        for (int i=0; i<m; i++){
            for (int i=0; i<n; i++){
                // print...
            }
        }
    }
```

### Analyzing Complexity when iterating over two strings of different lengths
- O(n) where n is the length of the longer string
```
// Code example here....
```


## Analyzing Stack Space of recursive functions
The space required on the call stack, and that will be the maximum stack depth.
Though we need to evaluate 2^n  recursive calls in total but at any point of time there will be a max of only n items on my call stack.
For n = 4321, the recursive call stack will be the height of the tree which will be n.
Time Complexity = 2^n, which is the total number of function calls
Space Complexity = n, which is the number of digits on the stack.
```
                        4                                    h = 1
                3               3                            h = 2
            2       2       2        2                       h = 3
          1   1   1   1   1   1   1     1                    h = 4

        2^n function calls = 2*2 ... *2
        2^4 function calls  = 2*2*2*2 = 16
```

## Clearing up Exponential and Factorial Runtime Complexities ```O(s^8) vs O(8^s)```

### Exponential - An example of a recursion with O(8^s)
In the non-linear word search, from any position there are a maximum of 8 choices, so any recursive call can lead to up to 8 more! (One for each direction)
Consider a board that of w width, h height, if we are searching for a word with length s.
1. For ```s=1``` and an ```initial position```, there is no recursion. Either we found the correct letter, or we didn’t.
2. For ```s=2```, and an ```initial position (i, j)```, there are 8 calls. This is 8^1 = 8 calls.
```
     (i−1, j−1)  (i−1, j)  (i−1, j+1)
                \    |    /
                  \  |  /
     (i, j−1) - -  (i,j)  - - (i, j+1)
                /    |    \
               /     |      \
    (i+1, j−1)    (i+1, j)   (i+1, j+1)
```
```
        Direction   (row, col)
        ----------------------
        northwest   (i−1, j−1),
        north       (i−1, j),
        northeast   (i−1, j+1),
        east        (i, j+1),
        southeast   (i+1, j+1),
        south       (i+1, j),
        southwest   (i+1, j−1),
        west        (i, j−1)
```
3. Now consider s=3. For each of the 8 positions from s=2, we can try 8 more positions. So that is ```8×8 = 8^2 = 64 total calls```.

So to determine ```s=i```, we could repeat this approach recursively by looking at the previous step, ```s=i−1```.
Each time we add a step, we are multiplying by another ```8```, because every position from ```s=i−1``` can try ```8 more positions```.

In general, our solution looks like ```8^(s−1) = 8^s ∗ 8^−1```. Since ```8^−1 is just a constant```, so we can say ```O(8^s)```.

- This isn’t the whole picture though. Let’s consider a few cases:
```w×h = 50, 000, s = 2? s = 4? s = 50,000?```
```w×h = 4, s = 2? s = 4? s = 50,000?```

### Factorial - Writing a recursive function of O(s^8)
```
    int func(int s, int layer){
        if(layer==0) return 1;

        int ret = 0;
        for(int i=0; i<s; i++){
            ret += func(s,layer-1); // For each call to func, there are s number of recursive calls O(s^8)
        }
        return ret;
    }
```
1. func(1,8); ```// 1^8 = 1```
2. func(2,8); ```// 2^8 = 256```
3. func(3,8); ```// 3^8 = 6,561```
4. func(4,8); ```// 4^8 = 65,536```

# Complexity Analysis of Commonly used Algorithms

### Binary Tree - DFS
Time complexity  : O(N)
Space complexity : O(d) = O(log N) to keep the recursion stack, where d is a tree depth.


### Binary Tree - BFS
Let N be the total number of nodes in the input tree.
Time Complexity: O(N)
We visit each node once and only once. And at each visit, it takes a constant time to process.
Space Complexity: O(N)
We used a queue to maintain the nodes along with its indices, which is the main memory consumption of the algorithm.
Due to the nature of BFS, at any given moment, the queue holds no more than two levels of nodes. In the worst case,
a level in a full binary tree contains at most half of the total nodes (i.e. N/2​), i.e. this is also the
level where the leaf nodes reside. Hence, the overall space complexity of the algorithm is O(N).



## REFERENCES
https://youtu.be/zo7YFqw5hNw
https://vimeo.com/157480836#
https://vimeo.com/158532188
https://medium.com/outco/time-complexity-from-bad-to-worst-2537361fb5f3
https://medium.com/outco/how-to-solve-power-set-c8ef7d1382ee
https://adrianmejia.com/how-to-find-time-complexity-of-an-algorithm-code-big-o-notation/
https://adrianmejia.com/most-popular-algorithms-time-complexity-every-programmer-should-know-free-online-tutorial-course/