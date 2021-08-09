### Constant

### Logarithmic O(log(n))
- Logarithmic is opposite of exponent.
- Exponent (to the power) is repeated multiplication.
```2^5 = 32```
- Logarithmic of a base is repeated division by the base.
```
    4^3 = 64
    log4(64) = 3
    4 to the 3rd power is 64

    2^5 = 32
    log2(32) = 5 [16/2 > 8/2 > 4/2 > 2/2 > 1 ]
    2 the 5th power is 32
```
- Logarithmic does not grow fast
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

### Linear - O(n)
We have to evaluate n function calls

### Linear Logarithmic / LogLinear - O(n*log(n))

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

```
        n* log(n)
        _
       |               abcdefgh                      4
log(n) |         abcd            efgh                4+4
       |     ab     cd       ef      gh             2+2+2+2
       |_   a  b   c  d     e  f    g   h           1+1+1+1+1+1+1+1
```

### Polynomial - O(n^c)
O(n^c) where, c is a constant
e.g.  n^2, n^3
### Exponential O(c^n)
O(c^n)      where c is a constant and n is the size of the input]
e.g. 2^n or 3^n

```2^n``` A single call to foo makes 2 recursive call here
```
    void foo(int n){
        if (n==1) return;
        foo(n-1);
        foo(n-1);
    }
    foo(4);
```

The tree view of the recursive function for ```2^n```
```
                  4                                     1
                3   3                                   2
            2   2   2   2                               4
        1   1   1   1   1   1   1                       8

        2^n = 2*2*2*2* ... * 2
```

- ```3^n``` A single call to foo makes 3 recursive calls here
```
    void foo(int n){
        if (n==1) return;
        foo(n-1);
        foo(n-1);
    }
    foo(4);
```
```2^n/2 = 2^n/2 ``` A single call to foo makes 2 recursive calls and the height of the tree is going to be half as tall.
- Branching factor of 2
- Height is half of n

```
    void foo(int n){
        if (n == 1) return;
        foo(n - 2);
        foo(n - 2);
    }
    foo(4);
```

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
### Factorial (n!)
- The key characteristic of this function is within each call to foo, we make n further recursive calls from within the for loop.
- In an exponential function, we branch out a constant number of times.
- However, in a factorial we branch out n number of times.

```
n! = n * (n-1) * (n-2) ... (2) * (1)
4! = 4 * 3 * 2 * 1
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

```

                                    4                                                   4

          3                     3               3               3                       3

  2       2       2                                                                     2

1   1   1   1   1   1                                                                   1

```


### Analyzing Multi-Argument Function - O(m+n)
Here we have loops after each other, hence ```O(m+n)```
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

### O(n) where n is the length of the longer string


### Analyzing Stack Space









### REFERENCES
https://youtu.be/zo7YFqw5hNw
https://vimeo.com/157480836#
https://vimeo.com/158532188
https://medium.com/outco/time-complexity-from-bad-to-worst-2537361fb5f3
https://medium.com/outco/how-to-solve-power-set-c8ef7d1382ee
https://adrianmejia.com/how-to-find-time-complexity-of-an-algorithm-code-big-o-notation/
https://adrianmejia.com/most-popular-algorithms-time-complexity-every-programmer-should-know-free-online-tutorial-course/