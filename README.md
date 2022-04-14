# Programming Assignment 3

## Instructions for running the program
 - Download all the files in this repository and put them in a folder
 - Open your command prompt and change the directory to the folder you created
 - To run the code for problem one type "javac Presents.java" in the command prompt
 - The output will be displayed in the terminal

## Problem 1: The Birthday Presents Party
I implemented my own version of a lazy linked list for this problem. The reason why the servants in the problem were not able to have the same number of presents and thank you notes is because they did not use locks. Unfortunatly, I did not have enough time to create a full functioning program so the program so I did not gather any execition data. I was able to implement the insertion and the contains method correctly. The insertion method gets the present tag from the unordered bag and adds it into the linked list in its numerical place. The previous node and the current node is locked during this process. After the node is succesfully added the nodes are unlocked. The contains method does not contain any locks and works the same way as a traditional linked list. 

The part I was not able to implement fully was the delete method. The delete method is meant to work in a similar way as the insert method. The only difference is instead of adding a node, we first logicall mark the node as removed from the linked list, then connect the previous node to the next of the current node to physically remove the node. Since which node has to be removed was not specified in the problem definition, my idea was to always remove the tail of the linked list when a servant is assigned to the removal task. 

## Problem 2: Atmospheric Temperature Reading Module
I did not have time to implement this problem. 
