# Semester 2 Project - LinkedTree

by Spencer Johnson & Oriol Segura i Niño & Adrian Luerssen Medina
on Sunday 22th May 2022

The following explanation is in conjunction with PAED Project Semester 2

## System requirements

* [IntelliJ IDEA 11.0.13](https://www.jetbrains.com/idea/download) 
* Java 11 with openJDK-17

It was tested using IntelliJ IDEA Ultimate Edition v2021.3 

## How to execute the code

By opening the project in IntelliJ the user will be able to compile the code using the platform. On Mac the shortcut is Control+R.

## Considerations

When utilizing the Canvas Menu (R-trees) the fifth functionality (E), Special Search - Circle, Rectangle.java line 346, the variable tolerance can be modified depending on how strict we desire the search and how many circles we want to show. This will also hold true for the variable toleranceColor declared on line 373. 

Within the code and the different classes there will exist methods that are not used. The non-used methods will have their names in grey and are left inside the code to show the codes improvements and the path followed showcasing the design decision at each step. 

## Algorithms Utilized

Throughout the class we have been presented with a few different algorithms. The algorithms each have their own unique advantage that allows them to be used for the different functionalities depending on the functionalities requirements. 

* Algorithms
    * Depth First Search (DFS)
    * Breadth First Search (BFS)
    * Dijkstra's Shortest Path
    * Topological Sort

## Available Datasets

By editing line 29 in Business/UIControllerFollower to give the path the name of the desired dataset to be run. A list of the different available datasets is provided below. This are identical the ones provided in the estudy in conjuction with the projects rules and regulations 

The user can enter any of the four UIControllers and edit the path, exactly the same as described above. 

For the graphs contextualise drama use the dag dataset.

* LinkedTree/data:
    * graphL.paed
    * graphM.paed
    * graphS.paed
    * graphXS.paed
    * graphXL.paed
    * graphXXL.paed
    * dagXS.paed
    * dagS.paed
    * dagM.paed
    * dagL.paed
    * dagXL.paed
    * dagXXL.paed
    * treeL.paed
    * treeS.paed
    * treeXS.paed
    * treeXL.paed
    * treeXXS.paed
    * treeXXL.paed
    * rtreeL.paed
    * rtreeS.paed
    * rtreeXS.paed
    * rtreeXL.paed
    * rtreeXXL.paed
    * tablesL.paed
    * tablesS.paed
    * tablesXS.paed
    * tablesXL.paed
    * tablesXXL.paed


## About The Architecure

* Persistence Package:
    * AlgorithmCSVDAO
    * BusinessCSVDAO
    * CircleCSVDAO
    * FollowerCSVDAO

* Business Package: 
    * UIControllerMain
    * UIControllerFollower
    * UIControllerAdvertising
    * UIControllerCanvas
    * UIControllerFeed

* Business/Graph Package:
    * AdjacencyList
    * Edge
    * User
    * Graph

* Business/Table Package:
    * Business
    * HashNode
    * Table

* Business/Tree/AVL Package:
    * Algorithm
    * LinkedTree
    * Push

* Business/Tree/RTree Package:
    * Circle
    * R_Tree
    * Rectangle
    * Shape

* Presentation Package:
    * FollowersView
    * MainView
    * MenuOptions
    * Messages
    * AdvertisingView
    * CanvasView
    * FeedView

* Presentation/Visualisation/HashTables Package:
    * HashHistogram
    * HashJFrame

* Presentation/Visualisation/RTrees Package:
    * RTreeJFrame
    * RtreeVisualiser

## Contact

Spencer Johnson - spencerjames.johnson@students.salle.url.edu - [@papasj19](https://www.instagram.com/papasj19/) - [papasj#8305](https://discordapp.com/users/376777722686341121)

Adrian Luerssen - adrian.luerssen@salle.url.edu - [@adrilumedina](https://www.instagram.com/adrilumedina/)

Oriol Segura - oriol.segura@salle.url.edu - [@0rikik0](https://www.instagram.com/0rikik0/) - [オリキコ君#2728](https://discordapp.com/users/376777722686341121)
