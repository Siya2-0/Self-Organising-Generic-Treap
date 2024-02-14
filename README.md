# Self-Organising-Generic-Treap
This repository contains the implementation of a Self-Organizing Generic Treap designed for indexing columns in a simplified database.

## Introduction
The primary aim of this project is to implement a self-organizing treap data structure, which is a combination of a binary search tree and a heap. This treap is used to index columns in a simplified single-table database. The implementation includes functionalities such as insertion, deletion, searching, updating, and indexing.

## Background
### Self-Organizing Data Structures
A self-organizing data structure is designed to optimize search operations by dynamically rearranging its elements based on access patterns. This assignment focuses on self-organizing treaps, which maintain both binary search tree properties and heap properties.

### Treaps
Treaps are randomized binary search trees where each node has both a data element and a priority value. They maintain the max heap property, ensuring that the priority of a node is greater than or equal to its children. Additionally, treaps assign priorities randomly to achieve balanced structures on average.

### Database
The simplified database in this project consists of a single table with structured columns. Operations such as insert, update, delete, and search are supported. To optimize search efficiency, columns are indexed using self-organizing treaps instead of traditional B+ or B* trees.

## Implementation
### Generic Self-Organizing Treap
The Treap class represents the self-organizing treap data structure. It includes functions for insertion, removal, access, and rotation to maintain the heap property.

### Database Management System
The Database class serves as the database management system. It supports various operations including inserting, updating, deleting, and searching records. The database also handles indexing using treaps for improved search performance.

## Getting Started
To use the provided code, follow these steps:

Clone this repository to your local machine.
Open the project in your preferred Java development environment.
Explore the Treap.java and Database.java files for implementation details.
Modify the code or integrate it into your own projects as needed.
