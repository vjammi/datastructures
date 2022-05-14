https://youtu.be/pQldnua_hZ4?list=PLujIAthk_iiO7r03Rl4pUnjFpdHjdjDwy
https://leetcode.com/discuss/general-discussion/458695/dynamic-programming-patterns
https://www.geeksforgeeks.org/tabulation-vs-memoization/

To start dp is an optimization technique.
The idea is that you take a problem and break it down into smaller sub problems of the same nature
In dp the assumption is that if you can solve the sub problems then you should be able to solve the main problem this
is called having an optimal substructure 
the issue is that when you break a problem down into sub-problems some of those sub problems reoccur many times 
or in other words they overlap what dp does is that it saves the solution to a sub problem you've already solved so that you don't need to compute
it again which saves a lot of time understanding recursive dfs and backtracking are essential to understanding dp and you'll recognize this tree structure 
if you've been thru dfs from all the solution diagrams we drew one way to think of dp
is just as dfs with memoization or more generally recursion with caching where the goal is that you don't check down paths
that you've already been down before of course you can implement all dp problems iteratively but i think that it's easier
to start thinking about them recursively. 

Now I want to go over the two big phases in solving a dp problem which i derived from this great article written by
Nikola Otasevic [https://dev.to/nikolaotasevic/dynamic-programming--7-steps-to-solve-any-dp-interview-problem-3870]
we are going to talk about all these steps while showing  how to apply them to a simple example - the problem climbing stairs. 

In climbing stairs you have a staircase with n stairs and you can take step sizes of one or two to get there
you want to figure out how many different ways you can get to the nth stair.

As an example if you wanted to get to the third stair you could take 
    one step one step one step 
or you could take 
    one step two steps 
or you could take 
    two steps and one step
So there are three ways to get to stair number three.
 
1. The first phase is to define the recurrence relation or how to break a problem down into sub problems 
At this stage you can basically treat the problem as a regular recursive dfs problem don't even think about memoization yet 
and The first thing you should do here is just count how many parameters there are in the problem in climbing stairs we're trying to compute
the number of ways we can get to the nth stair so our only parameter is n in this case the step sizes are fixed to one or two so these are not parameters 

if you're trying to find the longest common subsequence between two strings then you have two parameters which are the two strings 
another way to think about this step is that you're finding the dimensionality of the problem you can categorize all dp problems into 
different structural forms 1d 2d or even 3d climbing stairs has one parameter the number of stairs 
so it's one dimensional i'll admit that this concept doesn't matter a ton but i think the idea of dimensionality really helps when you're trying to draw
a diagram for your iterative solution which i'll show in a second once you know what your parameters are 
you want to try to express the recurrence relation and this is where i would say 70 of the brain work in a dp
problem is happening basically you want to ask yourself can the solution to this problem be expressed as a function of solutions to smaller similar problems 

In climbing stairs we're trying to find a number of ways to stare n from the bottom 
Let's say n equals five since we can only take steps of size one or two to get to stair five we know we had to have come from stair
three or stair four in other words the solution at stair five can be expressed as a function of the solutions at stair three
and therefore which are the sub problems likewise the solution at stair three can be built from the solutions at stair one
and stair two obviously if we know how many ways there are to get to stair three and stair four then we can use that information to calculate how many ways
there are to get to stair five 

one way to represent this recurrence relation is to write it out as a function in climbing stairs the solution at stair n depends on the solutions at the two
stairs below it so you could write it out in a format like this where the nth staircase depends on the nth minus one and n
minus two stairs you can also draw it out as a diagram and 
i want to show you two ways to do it one is as a tree which is straightforward for this problem and it's similar to our dfs diagrams 

To find stair five we need to know the solutions at stair three and stair four 
To find stair four we need to know the solution at stair two and stair three 
So in this way we build up our tree of subproblems and we figure out how we can use them to build our top level problem 
As you can probably see this illustration lends itself to recursive implementation and it's also a great way to see overlapping subproblems 
which you can see are happening here and 
Here the other way to draw this is as an array or a matrix this is where the problem's dimensionality comes into play 
since this is a 1d problem where the only parameter is n we can show it as a 1d array the array will go from stair 0 to star n 
which is 5 in this example and what you want to do is draw forward directed edges 

For example to get to stair 2 you know you had to have come from stair 0 or stair 1. 
Likewise to get to stair 3 you know you have to come from stair 1 or 2. 
The idea is that if we know how many ways it takes to get to stair 0 and how many ways there are to get to stair 1 
then we can easily use that information to figure out how many ways there are to get to stair 2 and 
how many ways there are to get to stair three and so on

once you draw this out you can see that it really lends itself to an iterative implementation where you go through and
construct future values from past values a formal way to think about this is that any dp problem is a dag or a directed acyclic graphTraversal 
of sub-problems that go from easy to hard any of these representations is fine at this point and 

I would also say that at this point don't think too much about how you're eventually going to implement it yet recursively or iteratively 
All you need to focus on at this point is understanding the recurrence relationship don't even worry about identifying overlapping subproblems or
memoization yet regardless of how you're going to implement it later to finish defining a recurrence relation you need to identify the base case
in this case we know that there's only one way to get to stair zero or even stair one and that's good enough for our base case

once you've figured out the recurrence relation you can move on to the next step which is implementing it recursively or iteratively
and adding memoization or tabulation that was really a mouthful to be clear you can implement any dp problem iteratively or
    recursively iterative or bottom-up implementations use tabulation 
    while recursive or top-down implementations use memoization

At the end of the day they're both just forms of caching 
we don't want to recompute the solutions to sub problems that we've already figured out so what we do is we save the solutions to some problems we've already 
encountered so that we can just look it up rather than recomputing the whole thing i'll show you the difference between these two types of implementations in a second when i
implement the solution to climbing stairs but there's also this great geeksforgeeks article that talks about it and i especially like the english example that they give at the beginning
https://www.geeksforgeeks.org/tabulation-vs-memoization/
 
the one difference i will point out is that i think it's often easier to think of dp
problems in terms of the recursive solution but and i can't emphasize this enough if you have the recurrence relation then
it should be easy to build either solution 

1. So the first type of implementation 
the recursive or top-down method uses memoization and it closely follows the tree diagram that we saw earlier  

you can basically start this implementation by writing out the solution with regular recursion without memoization 
since we're starting at the top and working our way down to simpler solutions this approach is called top down 
then to turn it into a dp solution we just need to add some data structure that caches our solutions 
in this case we're going to use an array where the index of the array represents a stair and 
the value represents how many ways it takes to get to that stair 
we can get rid of the base case from our recursive method we'll replace it with something else and instead we're going to put it up here 
so that we initialize certain values of the array to start 

in our case we know that to get to the zeroth staircase there's only one way and to get to the first staircase
there's only one way now in our recursive method what we need to do is pass in this memoization array
like so and then what we can do is check if the current stairs value has already been computed and if it has it'll just be not zero if it's already been computed then we
simply return it and we don't have to bother with any more recursive calls otherwise what we do is we have to
compute the ways to the current stair in the same way we did before except this time we need to pass it the memoization array once we compute it we need to make sure to
store it in our memorization array so that we can use it later if we need it and then at the end we just return it
and this is basically how you go from a simple recursive solution to implementing memorization
the other option is an iterative or bottom-up implementation which uses tabulation and this closely follows the
1d array diagram that we saw earlier our algorithm will just iterate through the array and build the solution at each
stair because it solves the problems from small to large this approach is called
bottom up you usually have some array or matrix holding your solutions and all you have to do is figure out how
to fill it up to do that you need to understand how to build the current element
out of the previous elements which is what the recurrence relation is for the relation between the current one and
the previous ones will vary from problem to problem but you can simply refer back to the
diagrams you drew earlier and look at the edges in this case the solution at the current position is the sum of the solutions at
the previous two positions and at the end we just returned the solution at the nth stair
this is a classic how many waves problem and the structure for this problem can usually be found by asking yourself
looking at my current position where could i have come from to get here once you get that recurrence relation the
rest should fall into place right so now that i've showed you how to solve a basic dp problem i want to show
you how you can apply this framework to other dp problems that i think are very essential
hopefully by showing you the similarities between them you'll start to get a feel for this type of pattern
coin change is a classic dp problem where you have some set of coin values in our case let's say we have
a one cent coin a two cent coin and a five cent coin and you're trying to make some amount of sense
and cents with the minimum number of coins for example if we were trying to make six cents we could use six pennies
like so but a better way to do it with fewer coins is to use one nickel and one penny and that would be our
solution two coins let's start from the beginning just like before this is a 1d problem where the
only parameter is the amount of money that you're trying to make change for now for the
recurrence relation if i'm trying to find the best way to make say a hundred cents one way to do it is
to figure out the best way to make 99 cents and simply add a one cent coin or we could take the best way to make 98
cents and add a two cent coin or the third option would be to take the best way to make 95 cents and add a five cent coin the big insight
with this problem is that it's the exact same as climbing stairs except that the size of your steps are different
instead of a step size of one or two the step to get from a previous state to a current state is the different coin
values one two or five as this is another one-dimensional problem we can draw it with a 1d array where the directed edges
reflect our step sizes or the coin values in this case to get the solution at n equals 5 when
we're trying to figure out the best way to make 5 cents we could take our solution at 0 cents and add a single nickel
or we could find the best way to make three cents and add one two cent coin or we could take the best way to make four cents and add a
penny these are all possible candidates for
the best way to make five cents
likewise when we're figuring out the
best way to make six cents we can take
the solution at one
and add a nickel or the solution at four
and add old two cent coin
or the solution at five and add a one
cent coin this
becomes our dag which is similar to the
previous problem our base case then
is that when we're trying to make zero
coins we obviously can't so there's no
ways to do that
and from there we can build out the rest
of the array we can follow our 1d array
diagram and code it bottom up
the top down example will basically look
the same as that of climbing stairs
in this example i'm actually going to
deviate from the problem a little and
fix the coins as one two and five
just to make it simpler first we need to
make an array up to
the amount of sense we're trying to
compute and we create an extra spot to
hold our base case
which is that at zero cents we can make
it using zero coins
then we iterate through the rest of the
array and try to populate it
at each step we consider all our
different coin denominations
option one for building our current
sense amount is to look back at the
solution
to i minus 1 how many coins did it take
to build
i minus 1 cents and add a penny that
should let us build our current amount
we see if it would be better to instead
use the solution at i minus 2
and add a coin of value 2. or if it'd be
better to use the solution at i minus 5
and add a nickel this math.min lets us
keep the best solution out of all of
them and these if statements simply make
sure that we don't check
things when they're out of bounds for
example when we're trying to build three
cents there's no way we can use a nickel
so we'll skip this
we basically keep the best option move
through the rest of the array and at the
end
we're able to return the element in the
last index of our array
so there's two key differences between
this problem and climbing stairs one is
the nature of the recurrence relation
in this problem rather than summing up
the subproblems you're trying to find
the minimum
between the sub problems what is the
minimum amount of coins you can use the
other key difference with this problem
is what i'll call the step
size which affects which sub problems
you need to look at in this case
we have to look at not only n minus 1
and n minus 2 but also
n minus 5 and these are the three cases
we consider at every point the problem
longest increasing subsequence takes
this idea a step farther and introduces
the concept of
inconsistent step sizes is how i'll call
it but i'll show you that beneath that
it's basically the same idea this
problem asks you to look at an array of
integers
and find the longest chain of increasing
numbers but they don't have to be next
to each other so in this example
the longest increasing subsequence would
be 2 4
6 9 for length of 4. there's only one
parameter in this case still which is
the single input array all the examples
today are actually 1d
but my next video will show some
two-dimensional examples
now for the recurrence relation the main
thing we care about is which previous
values are less than the value at the
current position as an example
in the array 3 12 4 11 the longest
increasing subsequence or lis
at the first position is obviously just
one because there's no values before it
so three is in its own sequence
at the second position we consider
everything before the 12.
we see that 12 is greater than 3 so we
can add 12
to the subsequence ending at 3 which
gives us a longest increasing
subsequence of length
two at the third position we consider
everything before the four
four is greater than three so if you put
them in the same increasing subsequence
you have a length of two but now you
have to consider the twelve
four is not greater than twelve so they
can't be part of the same increasing
subsequence
so you just ignore it and at the fourth
position we again consider
everything before it eleven is greater
than three so they can be part of the
same increasing subsequence
which would form a length of 2. 11 is
not greater than 12 so we can't do
anything there
but we see that 11 is also greater than
4. if you add them to the same
increasing subsequence you actually get
an even longer sequence of length
3. this basically becomes our 1d array
diagram
our dag at each position we consider all
the values before it and we draw a
directed edge between them
if the current value is greater than the
previous value and in this way we're
able to build up our array
and figure out how to compute each value
to draw this with our tree
structure to find the lis at some
position i we need to consider the lis
at all the positions before it then we
have to ignore those positions
where the value in that position is
greater than the value at our current
position out of the remaining
subproblems
we see which one has the biggest lis our
tree then looks a little odd because at
any given position we don't know how
many of the previous positions
can be used to make an increasing
subsequence
in coin change our steps were the coin
values one two or five
so each problem had three sub-problems
in this problem the number of
sub-problems we need to consider will
vary depending on the value in the array
at that position and whether it's
greater than or less than the previous
values
but just as before you can see that
there are overlapping subproblems
our base case is that at the first
position the longest increasing
subsequence will have a length of one
because there's no elements before it
and with that we can implement it
recursively using memoization
or iteratively using tabulation let's go
ahead and do this iteratively so we can
compare it to coin change
it might be a little tough to follow so
i've commented it out so you can pause
and read if you need
first we set up our dp array and we set
up the base case
which just says that the longest
increasing subsequence the lis
at the first element is one since
there's no elements before it
this outer for loop is us trying to
compute the lis for each element of the
array
from left to right bottom up at each
given point in the array we want to
first compute the lis
that ends on our current element this
inner for loop is what's going to let us
do that it's for considering all the
elements before
our current element and just as we saw
with the 1d array diagram with directed
edges
we only care about elements that are
smaller than our current element when we
find such an element we check the
longest increasing subsequence that
ended at that point and we see if that's
better than the best option we have so
far once we find the longest one
ending at our current position we know
that our longest increasing subsequence
is actually going to be that value plus
one
to include the current element and we
save that back to the dp array so that
we can use it later this is the same
idea as in coin change except instead of
taking the minimum number we're taking
the maximum because we want the longest
increasing subsequence the biggest
difference is that in this problem we're
not exactly sure
how many of the previous sub problems we
can use so we can't hard code it like we
did in coin change with n
minus 1 n minus 2 and n minus 5. we need
to check all of them
just in case hence the step sizes are
inconsistent and will vary depending on
where we are
another difference is this variable max
answer
in this problem the longest increasing
subsequence might not necessarily end
on the last element in the array so we
can't just return
the element at the very end instead we
need to keep
track of the running maximum whereas in
coin change we knew for sure that the
answer would be in the last position
of our dp array so the final problem i
want to discuss today is word break
which i actually got in one of my
full-time interviews for a fan company
this past year just like with the lis
problem in this problem the step to get
from a previous state to the current
state is going to vary so you can't hard
code it i think with this problem it's a
little harder to understand the
iterative intuition behind it but it's
actually not much different from the
previous problem the problem is that
you're given a list of valid words and
you want to figure out if some string
can be broken up into
some combination of valid words or not
in this example
our string is dog food and the word dog
and
food are both valid words so this would
return true
because the string can be broken up into
valid words
again we have a one parameter problem
because the string is really the only
thing we're working with
this one's a little easier to visualize
recursively i think but i'll show both
so again we need to think how can we
break this down into sub problems well
first let's look at the beginning of the
stream if there's a valid word at the
beginning
then all we need is to figure out if the
rest of the word
can be broken down into valid words as
well there's our sub problem
however we could have multiple valid
words that all start at the beginning
which gives us multiple sub problems
that we need to consider
you don't exactly know how many
subproblems you'll have as it depends
what words you can find at the beginning
of the string so just like in longest
increasing subsequences you have to
consider
all of them and just ignore the ones
that aren't going to work
you'll also see that different paths
might reach the same sub problem
such as in this scenario
where you both end up at this sub
problem so this is a great case for us
to use memoization so that we don't have
to recompute them
in the interview that i got this problem
i actually just implemented it
recursively and it was fine but today i
want to show you the iteratives version
2 because it looks just like longest
increasing subsequence so here's what
we're going to work with we're going to
set up a 1d
array that keeps track of whether the
string up to a certain point
can be broken up into valid words or not
at any point of the string
say we're currently at the end of the
string we have to consider all the
indices before it and the substrings
that they formed so we'd consider
the substrings like food or oo or od
if any of those are valid words then we
have a directed edge so in this case
food is a valid word what i mean by this
is that if we already know whether the
part of the string
up to that point is a valid word and now
we know that the rest of the string up
to our current point
is a valid word as well then we know
that this string up to this point
can in fact be broken up into valid
words
so at this point we would say that yes
the string up to this point can be
broken up into valid words
just like in the longest increasing
subsequence problem we have to consider
all the different indices before it
because we don't know which ones are
going to form valid words or not
our base case in this problem is that
when the input string is empty
we should return true to implement it
iteratively we set it up the same way as
we did before
first we set up our 1d dp array which
just holds booleans in this case
in this case we want an extra spot in
the array to represent when the string
is empty because that's our base case
and it says that when the string is
empty we consider that it can be broken
up into valid words
then we go through the rest of the dp
array and build it up at each position
of the array
we consider all the possible previous
indices
if the substring from a previous index
to this current index
is a valid word which is represented by
this statement right
here and if we already know that
everything up to that point
can be broken up into valid words then
we know that the string
up to this point can also be broken up
into valid words
unlike in longest increasing subsequence
once we've determined that there's a
single way to achieve our goal
we don't need to go looking for more we
don't need to find a better way it's
just true or false
and also unlike in longest increasing
subsequences we know that the solution
will be whatever's at the end of the
array
since we want to know whether the entire
string can be broken up into valid words

At the end of all of this i have a few key takeaways 
1. First is the importance of coming up with 
    the recurrence relation and 
    just thinking about the problem with regular recursion 
2. next is how you can 
    draw the recurrence relation as a tree or as a table, 
    which corresponds to the recursive and iterative implementations respectively. 

I hope you have some appreciation of how you can translate these diagrams into their corresponding implementations
and above all i wanted to emphasize this idea of Stepping from a problem into its sub-problems 
1. sometimes in problems like climbing stairs those steps are quite literal 
2. sometimes in problems like coin change those steps are disguised as something else like coin values and 
3. sometimes in problems like lis or word break those steps aren't fixed and will vary based on certain conditions
 
Keeping all of that in mind the best way to get better at dp problems is to expose yourself to more and as you do so i encourage you to keep these patterns in mind and try to draw these diagrams
by hand so you get a feel for it personally i think a challenging part of dp is that there are so many problems
that appear to look very different from one another i actually came across a great leap code discuss article
that tries to categorize some of these different patterns i also have two more dp videos planned in this series
along with all the other structures like graphs and trees that i'll talk about later as well 

I want to use one of those dp videos to talk about popular problems and string dp problems like edit distance and then the other one i want
to tackle knapsack and independent set decision problems these specific types of videos take an annoyingly
long time to make so please let me know if they were helpful and i'll get to work on making more
i've also made other videos with some more high level advice on how to study for your interviews what to say during
an interview and i'm also thinking of making some videos on my personal interview experiences
