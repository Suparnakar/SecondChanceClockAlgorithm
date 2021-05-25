# Second-Chance-Clock-algorithm

Consider a scenario where you have N number of boxes and M number of slots (M<N).
Each box is having their identification number (more than one box can have the same
identification number). Each slot is having two parts, one is for accommodating the box
and another is for reference bits (this is for referring to the boxes).
You need to accommodate the boxes into the slots exactly once.

Condition:

● Initially all slots will be empty and the reference bit field will be 0.

● if reference bit is 0, replace

● if 1, give box a 2nd chance and move onto next FIFO box; reset reference bit to 0 and
reset arrival time to current time

a box given a second chance will not be replaced until all other boxes have been
replaced (FIFO) or given second chances

● if a box is used often enough to keep its reference bit set, it will never be replaced.

Implementation: the clock algorithm using a circular queue

○ a pointer ( clock) indicates which box is to be replaced next

○ when a box is needed, the pointer advances until it finds a box with a 0 reference
bit

○ as it advances, it clears the reference bits

○ once a victim box is found, the box is replaced, and the new box is inserted in the
circular queue in that position

○ degenerates to FIFO replacement if all bits are set

Input:

● Number of blocks

● Number of slots

Output:

● Number of replacements.

● Vary number of slots and show the number of replacements

● List of blocks, which got replaced




Here input is number of slots(size of circular queue) and the reference string separated by comma.
There are 3 files.
Cacheimplentation.java, Box.java, CircularQueue.java all have to be in same folder same package called cache.
main program resides in Cacheimplentation.java.
This program can be run in Eclipse, IntelliJ Idea, or running from command line.

For command line compilation:
javac *.java
cd..


For command line run the code:
java.cache.Cacheimplentation




