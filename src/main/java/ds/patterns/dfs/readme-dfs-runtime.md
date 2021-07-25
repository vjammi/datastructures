Clearing Up Exponential Complexity

## Difference between ```O(s^8) vs O(8^s).```

- In the non-linear word search, from any position there are a maximum of 8 choices, so any recursive call can lead to up to 8 more! (One for each direction)
- Consider a board that of w width, h height, if we are searching for a word with length s.
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

So to determine s=i, we could repeat this approach recursively by looking at the previous step, ```s=i−1```. 
Each time we add a step, we are multiplying by another 8, because every position from s=i−1 can try 8 more positions.

In general, our solution looks like ```8^(s−1) = 8^s ∗ 8^−1```. Since ```8^−1 is just a constant```, so we can say ```O(8^s)```.

- This isn’t the whole picture though. Let’s consider a few cases:
```w×h = 50, 000, s = 2? s = 4? s = 50,000?```
```w×h = 4, s = 2? s = 4? s = 50,000?```
- How we would write a recursion to be O(s^8)?        
```
    int func(int s, int layer){
        if(layer==0){ return 1; }
        int ret = 0;
        //Make s calls
        for(int i=0; i<s; i++){
            ret += func(s,layer-1);
        }
        return ret;
    }        

    func(1,8); => 1
    func(2,8); => 256
    func(3,8); => 6561
    func(4,8); => 65536
```