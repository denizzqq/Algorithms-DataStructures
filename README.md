# Algorithms-DataStructures
This repo covers some popular algorithms in computer science. The aim here is to implement these algorithms by initializing data structures in Java.
In the following every single dir and its content are superficially explained.

**Content:**
- 1-OOP Basics: The network implementation of neurons that simplistically models the processing of colors in the brain. The processing of light waves occurs in photoreceptors, which convert light into a neural signal and transmit it to interneurons. These interneurons, in turn, pass the signal to cortical neurons. At this point, the neural signal can be interpreted as a color. The transmission always occurs through synapses, which can be visualized as connectors between neurons. The neuron system here is actually an OOP-Model. Through the classes of this model the OOP-Basics (e.g Class,Constructor,Method) can be easily observed.

- 2-Inheritance through Queue&Stack: This project actually consists of 2 different tasks: Dec2Bin and Bettelmann. In "Dec2Bin" the task is to convert a decimal number into a binary number, which is implemented by using Stack as Data Structure. However "Bettelmann" is actually a card game, which is an appropriate model to show inheritance by using Queue.

- 3-JUnit tests for Backtracking: The goal of this task is to develop JUnit tests. The class to be tested utilizes backtracking to determine all fixed-point-free permutations (or derangements) of a sequence of numbers. JUnit 5 is used for this purpose.
Two classes are provided, both inheriting from the class "PermutationVariation." These classes are intended to determine the set of all fixed-point-free permutations. While one of the two classes functions correctly, the other does not. Your task is to write the JUnit test class, "PermutationTest," which tests the correctness of the two given classes, along with additional test cases that you do not have, for derangements of lengths greater than 1 and less than 10.

- 4-Alpha-beta pruning: A program is to be written that evaluates any position in the Tic-Tac-Toe game. In the case of winning positions, it should take into account how many moves it takes to achieve victory (with an optimally playing opponent). The same principle applies to losing positions as well. All possible game scenarios are calculated through Alpha-Beta pruning from Minimax Algorithms.

- 5-Dynamic Programming: Two individuals are playing the following game. There is an (usually even) number of bowls, each containing marbles. The number of marbles in each bowl is known from the start. Alternatingly, the players select one of the two outermost bowls and take the marbles within them. At the end, each player receives the difference between the number of their own marbles and the marbles of the opponent. The objective of this task is to determine an optimal strategy. The strategies are recursively calculated by using dynamic programming and then the best strategy is served from the program.

- 6-Depth First Search: In this task, the DFS algorithm creates and solves a randomized maze. The maze is represented by a graph with V = \(N^2\) nodes, arranged in a square grid, along with the connecting edges in between. In this context, the nodes represent intersections, and the edges represent direct connections between these intersections. Therefore, the edges correspond to the pathways within the maze. This project is the beginning of graph-based algorithms in this repo.

- 7-Clustering: In this task, you will solve a clustering problem using the Prim's Minimum Spanning Tree (MST) algorithm. A cluster is a group of objects that share similar properties. This similarity or dissimilarity can be expressed, for example, using a norm. In this task, we consider points in \(R^n\) with the Euclidean norm.
Cluster analysis is applied in various fields such as image processing or the classification of genetic data. As an application example, in addition to the n-dimensional graphs we create, you will also explore the IRIS dataset from the UCI Machine Learning Repository.

- 8-Flow Algorithms: In this task, you will use the Edmonds-Karp algorithm to solve a general graph problem. The objective is to find as many edge-disjoint paths as possible in a given undirected graph between two nodes, s and t. A set of paths is considered edge-disjoint if none of the edges are shared between different paths. However, nodes can be used by different paths.
The focus is on sets of edge-disjoint paths with a maximum number of paths. There can be various such maximal sets. In Task, the goal is to determine the maximum number of edge-disjoint paths between nodes s and t. Additionally, the aim is to return a maximum set of such paths. Here the Edmonds-Karp algorithm is applied with the help of Ford-Fulkerson algorithm and its class.





