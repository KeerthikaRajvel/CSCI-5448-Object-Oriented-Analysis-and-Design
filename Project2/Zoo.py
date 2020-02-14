#------STRATEGY PATTERN IMPLEMENTATION-------#

import abc
import random

class RoamBehaviour(abc.ABC): #Creating an interface of common behaviour "Roam"
    @abc.abstractmethod
    def roam(self,name):
        pass

#-----Set of Classes for the Roam Behaviour------#

class WalkBehaviour(RoamBehaviour):
    def roam(self,name):
        print(name, " is walking.")

class RunBehaviour(RoamBehaviour):
    def roam(self,name):
        print(name, " is running.")

class SwimBehaviour(RoamBehaviour):
    def roam(self,name):
        print(name, " is swimming.")


#------OBSERVER PATTERN IMPLEMENTATION-------#

class Observable(abc.ABC): #Creating an interface for the Publisher i.e. Zoo Keeper
    @abc.abstractmethod
    def register_observer(self, observer):
        pass
    def remove_observer(self, observer):
        pass
    def notify_observers(self):
        pass

class Observer(abc.ABC): #Creating an interface for the Subscriber i.e. Zoo Announcer
    @abc.abstractmethod
    def update(self, task):
        pass

class ZooKeeper(Observable): #Zoo Keeper implements the Observable interface
    def __init__(self,name):
        '''function called automatically whenever a new objected is created to initialise the name of the ZooKeeper'''
        self.name = name
        self.observers=[] #list of observers
        self.current_task=None #keeps track of the zoo keeper's current task
    def register_observer(self, observer): #registers a new observer
        self.observers.append(observer)
    def remove_observer(self, observer): #removes an observer
        self.observers.remove(observer)
    def notify_observers(self): #notifies all the observers when the zoo keeper's changes the task
        for observer in self.observers:
            observer.update(self.current_task)
    def task_changed(self):
        self.notify_observers()
    def set_task(self,task):
        self.current_task = task
        self.task_changed()

    #ZooKeeper Tasks
    def wakeUp(self,animals):
        self.set_task("wake")
        for i in range(len(animals)):
            print("Zookeeper : Waking up ", animals[i].name, " the ", animals[i].__class__.__name__,".")
            animals[i].wakeUp();
    def rollCall(self,animals):
        self.set_task("roll call")
        for i in range(len(animals)):
            print("Zookeeper : Calling ", animals[i].name, " the ", animals[i].__class__.__name__,".")
            animals[i].makeNoise();
    def feed(self,animals):
        self.set_task("feed")
        for i in range(len(animals)):
            print("Zookeeper : Feeding ", animals[i].name, " the ", animals[i].__class__.__name__,".")
            animals[i].feed();
    def exercise(self,animals):
        self.set_task("exercise")
        for i in range(len(animals)):
            print("Zookeeper : Making ", animals[i].name, " the ", animals[i].__class__.__name__," exercise.")
            animals[i].roam();
    def shutDown(self,animals):
        self.set_task("shutDown")
        for i in range(len(animals)):
            print("Zookeeper : Putting ", animals[i].name, " the ", animals[i].__class__.__name__," to sleep.")
            animals[i].wakeUp();
        #Remove all observers
        for observer in self.observers:
            self.remove_observer(observer)
            print("Zookeeper : "+observer.name+" can no longer observe me!")
        print(" ******** Zoo shut down ******** ")

class ZooAnnouncer(Observer):#Zoo Announcer implements the Observer interface
    def __init__(self,name):
        self.name=name
    def update(self,task):
        print("Hi, this is the Zoo Announcer.",end=" ")
        if task=="exercise":
            print("The Zookeeper is about to make the animals roam!")
            return
        if task=="shutDown":
            print("The Zookeeper is about to close the zoo!")
            return
        else:
            print("The Zookeeper is about to "+task+" the animals!")

class Animal:
    #Base class

    def __init__(self,name):
        #function called automatically whenever a new objected is created to initialise the name of the Animal
        self.name = name
    def wakeUp(self):
        print(self.name," is waking up")
    def sleep(self):
        print(self.name," is going to sleep Zzzz")
    def makeNoise(self):
        pass
    def feed(self):
        pass
    ##---- Using the STRATEGY PATTERN-----
    def setRoamBehaviour(self,rb):
        self.roamBehaviour = rb
    def performRoam(self, name):
        self.roamBehaviour.roam(name)

class Herbivore(Animal):
    #Inherits from class Animal
    def __init__(self,name):
        Animal.__init__(self,name)
    def feed(self):
        print(self.name," is eating grass")

class Carnivore(Animal):
    #Inherits from class Animal
    def __init__(self,name):
        Animal.__init__(self,name)
    def feed(self):
        print(self.name," is eating meat")

class Omnivore(Animal):
    #Inherits from class Animal
    def __init__(self,name):
        Animal.__init__(self,name)
    def feed(self):
        print(self.name," is eating greens and meat")

class Giraffe(Herbivore):
    #Inherits from class Herbivore
    def __init__(self,name):
        Animal.__init__(self,name)
        self.roamBehaviour = WalkBehaviour(); #Setting roam behaviour in the sub class
    def makeNoise(self):
        r = random.randint(1,5)
        switch(r):
            case 1:
                self.makeNoise(r)
                break;
            case 2:
                print("*** Misbehavior ***"")
                self.feed()
                break;
            case 3:
                print("*** Misbehavior ***"")
                self.sleep()
                break;
            case 4:
                print("*** Misbehavior ***"")
                self.roam()
                break;
        print(self.name," : Giraffeeee")
    def makeNoise(self,r):
        print(self.name+"I am a Giraffeeeee.")
    def feed(self)


class Goat(Herbivore):
    #Inherits from class Herbivore
    def __init__(self,name):
        Animal.__init__(self,name)
        self.roamBehaviour = WalkBehaviour(); #Setting roam behaviour in the sub class
    def makeNoise(self):
        print(self.name," : Baaaah")


class Crocodile(Carnivore):
    #Inherits from class Carnivore
    def __init__(self,name):
        Animal.__init__(self,name)
        self.roamBehaviour = SwimBehaviour(); #Setting roam behaviour in the sub class
    def makeNoise(self):
        print(self.name," : Crocodileeeee")

class Lion(Carnivore):
    #Inherits from class Carnivore
    def __init__(self,name):
        Animal.__init__(self,name)
        self.roamBehaviour = RunBehaviour(); #Setting roam behaviour in the sub class
    def makenoise(self):
        print(self.name," : Raaaawrrr")

class Bear(Omnivore):
    #Inherits from class Omnivore
    def __init__(self,name):
        Animal.__init__(self,name)
        self.roamBehaviour = RunBehaviour(); #Setting roam behaviour in the sub class
    def makeNoise(self):
        print(self.name," : Brrrrrr")

class Pig(Omnivore):
    #Inherits from class Omnivore
    def __init__(self,name):
        Animal.__init__(self,name)
        self.roamBehaviour = WalkBehaviour(); #Setting roam behaviour in the sub class
    def makeNoise(self):
        print(self.name," : Oink oink")

animals = []
names = ["Gabi","Geno","Gal","Gag","Carl","Cassey","Lily","Lee","Ben","Boo","Pepa","Pooh"]
for i in range(0,12,2):
    if(i < 2):
        animals.append(Giraffe(names[i]))
        animals.append(Giraffe(names[i+1]))
        continue
    if(i < 4):
        animals.append(Goat(names[i]))
        animals.append(Goat(names[i+1]))
        continue
    if(i < 6):
        animals.append(Crocodile(names[i]))
        animals.append(Crocodile(names[i+1]))
        continue
    if(i < 8):
        animals.append(Lion(names[i]))
        animals.append(Lion(names[i+1]))
        continue
    if(i < 10):
        animals.append(Bear(names[i]))
        animals.append(Bear(names[i+1]))
        continue
    if(i < 12):
        animals.append(Pig(names[i]))
        animals.append(Pig(names[i+1]))
        continue

zooKeeper = ZooKeeper("Me")
zooAnnouncer = ZooAnnouncer("Zoo Announcer")
zooKeeper.register_observer(zooAnnouncer)
with open('dayatthezoo.out', 'w') as f:
    print('', file=f)
zooKeeper.wakeUp(animals)
print("--------------------------------------------------------")
zooKeeper.rollCall(animals)
print("--------------------------------------------------------")
zooKeeper.feed(animals)
print("--------------------------------------------------------")
zooKeeper.exercise(animals)
print("--------------------------------------------------------")
zooKeeper.shutDown(animals)
