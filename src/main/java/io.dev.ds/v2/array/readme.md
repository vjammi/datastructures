## Arrays

### 1 Two Sum
```
    // Time - O(n^2)
    // Space - O(1)
    public int[] twoSum_bruteForce(int[] nums, int target) {

        for (int i=0; i < nums.length-1; i++){
            for (int j=i+1; j < nums.length; j++){
                if (nums[i] + nums[j] == target){
                    return new int[]{i, j};
                }
            }
        }
        return new  int[]{};
    }
```

```
    // Time  - O(n)
    // Space - O(n)
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i=0; i<nums.length; i++){
            int remainder = target - nums[i];
            if (map.containsKey(remainder)){
                return new int[]{map.get(remainder), i};
            }
            map.put(nums[i], i); // 3:0, 2:1, 4:2
        }

        return new int[]{};
    }
```

