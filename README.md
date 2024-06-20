# Divine-Ingenuity-Dijkstra
Practice of Dijkstra algorithm.

2023 Fall CSC3100 Assignment 4 

# Description

If you have ever played Genshin Impact, you must remember “Divine Ingenuity: Collector’s Chapter” event. In this event, players can create custom domains by arranging components, including props and traps, between the given starting point and exit point. 
Paimon does not want to design a difficult domain; she pursues the ultimate “automatic map”. In the given domain with a size of m × n, she only placed **Airflow** and **Spikes**. Specifically, **Spikes** will eliminate the player (represented by ‘x’), while the **Airflow** will blow the player to the next position according to the wind direction (up, left, down, right represented by ‘w’, ‘a’, ‘s’, ‘d’, respectively). 
The **starting point** and **exit point** are denoted by ‘i’ and ‘j’, respectively. Ideally, in Paimon’s domain, the player selects a direction and advances one position initially; afterward, the **Airflow** propels the player to the endpoint without falling into the **Spikes**. The player will achieve automatic clearance in such a domain. 
Please assist Paimon by making the minimum adjustments to her design to achieve automatic clearance. 
Given that the positions of the starting point and exit point are fixed, you can only adjust components at other locations. You have the option to remove existing component at any position; then, place a new direction of **Airflow**, or position a **Spikes**.

# Input and Output
The first line of input contains two integers m and n, representing the size of the domain. m lines follow, each containing n characters. The characters must be one of ‘w’, ‘a’, ‘s’, ‘d’, ‘x’, ‘i’ and ‘j’. It is guaranteed that there is only one ‘i’ and ‘j’ on the map, and they are not adjacent.
Output a single integer, representing the minimum number of changes needed.


Example:
Sample input 1
```
3 3 
dsi 
ssd 
jdd
```
Sample output 1
```
1
```

Sample input 2
```
4 4 
jxsx 
xdxa 
dxax 
xwxi
```
Sample output 2
```
4
```

# Solution Details
Used a **modified Dijkstra's algorithm**. 
1. Data Structures and Initialization: 
- The domain is represented as a 2D character array `char[][] domain`, where each character signifies the nature of the cell (airflow directions, spikes, start, and end points). 
- A 2D integer array `int[][] distance` is used to keep track of the minimum distancefrom the starting point to every other point in the domain. Initially, all distances are set to `Integer.MAX_VALUE` to represent infinity. 
- A `PriorityQueue` is used to efficiently determine the next node to process. Nodes with smaller distances are given higher priority. 
- An inner class `Node` is defined to encapsulate the coordinates (x, y) and the current distance `dist` of each node. The class override `Comparable` to facilitate priority queue operations. 
- A HashMap`<Character, int[]> directions` maps airflow characters (‘w’, ‘a’, ‘s’, ‘d’) to their corresponding directional movements.
2. Algorithm Process:

- The algorithm identifies the starting (‘i’) and ending (‘j’) points in the domain and initializes distance of starting point to 0.  The start node is added to the priority queue.
- The algorithm repeatedly polls nodes from the priority queue, beginning with the start node.  For each polled node, the algorithm explores its adjacent nodes based on the possible movements (including the effects of airflow and spikes) and calculates the distance from the start node to these nodes, using the polled node’s distance. If the current polled node is a spike (‘x’), the distance to adjacent nodes is incremented by 1. If the current node is an airflow direction, the distance to adjacent nodes is calculate either incremented by 1 or 0, based on the direction.  If this new distance is less than the previously recorded distance in `distance[nx][ny]`, the algorithm updates the distance and adds the adjacent node to the priority queue.
- The loop terminates when the end node is polled from the priority queue, ensuring the shortest path is found.

