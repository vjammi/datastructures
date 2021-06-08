Clearing Up Exponential Complexity
• Let’s discuss a common mistake: O(s^8) vs O(8^s).
• Recall that in the non-linear word search, from any position there are a maximum of 8 choices, so any recursive call can lead to up to 8 more! (One for each direction)
• Remember the board is w wide, h high, and we are searching for a word with length s.
• For s=1 and an initial position, there’s no recursion. Either we found the correct letter, or we didn’t.
• For s=2, and an initial position (i, j), there are 8 calls: (i − 1, j − 1)(i − 1, j)(i − 1, j + 1)(i, j + 1),(i + 1, j + 1),(i + 1, j)(i + 1, j − 1),(i, j − 1). This is 81 = 8 calls.
• Now consider s=3. For each of the 8 positions from s=2, we can try 8 more positions. So that’s 8×8 = 82 = 64 total calls.
• To determine s=i, we could repeat this approach recursively by looking at the previous step, s=i−1. 
  Each time we add a step, we are multiplying by another 8, because every position from s=i − 1 can try 8 more positions.
• In general, our solution looks like 8^(s−1) = 8^s ∗ 8^−1. Since 8^−1 is just a constant, we can say O(8^s).

• This isn’t the whole picture though. Let’s consider a few cases:
    – w×h = 50, 000, s = 2? s = 4? s = 50,000?
    – w×h = 4, s = 2? s = 4? s = 50,000?
• How we would write a recursion to be O(s8)?
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
Final Note
We’ve said that recursion is sometimes the most natural way to begin thinking about designing and implementing
many algorithms. It’s ok if this feels downright uncomfortable right now. Practice, practice, practice!
