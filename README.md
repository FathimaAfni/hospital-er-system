# Mini Hospital Emergency Management System

CIT300 – Data Structures and Algorithms – Individual Mid Assignment
Author: Fathima afni

## Overview

A console-based Java application simulating a hospital emergency unit. It demonstrates
four core data structures, each implemented from scratch (no built-in Java collections
used for the required structures):

|Requirement|Data Structure|File|
|-|-|-|
|Patient Records|Binary Search Tree|`PatientBST.java`|
|Emergency Patient Queue|Queue (FIFO)|`EmergencyQueue.java`|
|Treatment History|Stack (LIFO)|`TreatmentStack.java`|
|Patient Visit History|Singly Linked List|`VisitHistory.java`|

Supporting model classes: `Patient.java`, `Visit.java`, `TreatmentRecord.java`.
Entry point: `Main.java` (console menu).

## How to Run

1. Install JDK 17+.
2. Clone this repository.
3. Compile: `javac src/\*.java -d out`
4. Run: `java -cp out Main`

Or open the folder in IntelliJ IDEA and run `Main.java` directly.

## Data Structure Design Notes

**Binary Search Tree (Patient Records)** — Keyed on `patientId`. Supports insert,
search, delete (handles 0, 1, and 2-child deletion cases using the in-order successor),
and in-order traversal for ascending-ID display.

**Emergency Queue** — Custom singly-linked FIFO queue with `front`/`rear` pointers.
Enqueue adds at the rear, dequeue removes from the front, giving O(1) for both.

**Treatment Stack** — Custom singly-linked LIFO stack with a `top` pointer. Push/pop
are both O(1).

**Visit History (Linked List)** — Each `Patient` owns one singly linked list of
`Visit` records: add, remove-by-ID, search-by-ID, and display.

## Sample Usage

Register a patient -> add them to the emergency queue -> "treat" them (this dequeues
them and pushes a treatment record onto the stack) -> add a visit record to their
history for future reference.

## Development Process

See commit history for incremental development: project structure, BST, queue, stack,
linked list, testing, and documentation were committed separately.

