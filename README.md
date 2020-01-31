# CSCI 5448 : Object Oriented Analysis and Design - Project 1
**Team  Members :** 
1. Amatullah Sethji
2. Keerthika Rajvel
3. Swarnalatha Natarajan

**Levels of Inheritance in Animals**
![Test Image 1](Animals.jpeg)
The Animal class is the super class with wakeUp() and sleep() methdods. Classes Herbivore, Carnivore and Omnivore extend/inherit from class Animal. These 3 classes contain a feed() method. The last level of inheritance contains classes for each of the 3 aforementioned classes. Class Herbivore is inherited by Giraffe and Goat classes; class carnivore is inherited by Crocodile and Lion classes; class Omnivore is inherited by Bear and Pig classes. Each class in the last level of inheritance has makeNoise() and roam() methods.

The randomize response feature has been incorporated in the Giraffe class where a random number is generated which picks the Giraffe's response. For instance, when the Zookeeper feeds the Giraffe, the Giraffe might randomly sleep, make noise, eat or roam.

In addition to this, there exists a class Zookeeper whose functions are to perform each of the 5 activities by calling the appropriate classes and methods associated with each animal.

