# DNATree
This is a 5-way branching tree structure utilizing flyweights, used to store and query DNA sequences (some combination of the letters: 'A' 'C' 'G' 'T'). All sequences are stored in the leaves and the tree holds no duplicates. Internal nodes contain only references to their 5 children: A, C, G, T, and D, The empty leaves all point to a single flyweight, that holds no data, and most tree function is handled recursively. Input is a text file with a single command per line, formatted as a command followed by one sequence, if neccessary (ex. "insert ATCG"), and output is to the console.  
  
The commands for insert and remove are: "insert [seq]" and "remove [seq]". The tree has various search and print functions.  
Searches:  
  
exact match search - command "search [seq]$" (the $ is to indicate exact instead of region) - output is the number of nodes visited in the search followed by the found sequence "# of nodes visited x \n sequence: [seq]" 
  
region search - command "search [seq]" - output is formatted the same as exact match, but this search returns all the sequences with the input seq as a prefix, Ex. "search AT" on a tree with AT and ATG in it would print     
#of nodes visited x  
sequence: AT  
sequence: ATG 
  
Print functions:  
  
command "print" - output will be a string representation of the tree with "I" for internal nodes, "E" for empty leaves, and the sequences displayed. Each level of the tree will have its own vertical line. 
  
command "print stats" - output is the same as print but next to each sequence is the percent of each letter right next to it (ex. ACT A:33.33 C:33.33 G:0.00 T:33.33).  
  
command "print lengths" - output is similar to stats but the length of the sequence is shown (ex. ACT 3)  
