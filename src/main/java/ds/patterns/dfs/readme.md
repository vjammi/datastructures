Depth First Search

DFS search is a way to search thru all the nodes ofg a tree or a graph by searching all the way down one path before coming back to search other paths.

1. LC 17 Letter Combinations of a Phone Number
    Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. 
    Return the answer in any order. A mapping of digit to letters (just like on the telephone buttons) is given below. 
    Note that 1 does not map to any letters.

    Input: digits = "23"
                2 = abc
                3 = def 
    Output: ["ad","ae","af",
             "bd","be","bf",
             "cd","ce","cf"]
    Tree Representation of digits to their letters
         ""                  ""                    
         2          a         b         c
         3       d  e  f   d  e  f    d  e  f
               
               2       3
          -------------- 
                  -  d
               a  -  e
            /     -  f
                  -  d
         "" -  b  -  e
                  -  f
            \     -  d
               c  -  e
                  -  f
    Steps          
        - Starting with the first digit, we list all the possible letters it can represent. 
        - Then for each of letters for the first digit, we list out all possible letters of the next digit  
        - We continue until we have considered all the digits in out input. 
        - Each of the possible paths is a possible combination we will want in our solution set
        - Now if we start an empty string on the top, note that our solution space now looks like a tree.
        - We want whatever search algorithm we implement, we need to get to all the endpoints at the bottom  

    Other Problems    
        22 Generate Parentheses
        
        46 Permutations    
        
        39	Combination Sum
        40	Combination Sum II
        216	Combination Sum III
        377	Combination Sum IV
        
        78	Subsets
        90  Subsets II        

2. Number of Islands
    
    Number of Enclaves
    Supported Regions
    Work Search 
    Smallest Rectangle
    Enclosing Black Pixels
 
 
3. N-Queens
    Valid Sudoku
    