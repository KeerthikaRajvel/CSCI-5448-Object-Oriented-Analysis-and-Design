# CSCI 5448 : Object Oriented Analysis and Design - Project 1
**Team  Members :** 
1. Amatullah Sethjiwala
2. Keerthika Rajvel
3. Swarnalatha Natarajan

**Question 1(b)**
For this question, we implement the startegy pattern. Our previous project had  the following class structure : 
![Test Image 1](Animals.jpeg)
Since the method 'roam()' varies among different kinds of animals, it is taken outside the class and encapsulated. We create a RoamBehaviour Interface that has a set of classes representing it's behaviour : WalkBehaviour, SwimBehaviour and RunBehaviour. The various subclasses then choose from these classes of behaviours. 
The class diagram and sequence diagram for this is in the .doc file submitted. 
The code is : AnimalStrategy.java and ZooKeeperStrategy.java and dayatthezoo_usingStrategyPattern.out.
