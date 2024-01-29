#include "MyObjectList.h"
#include "MyObject.h"
#include <cstddef>
#include <cstdlib>

// This function moves all of the objects in the vector, checks for any collisions, and displays the objects
void MyObjectList::MoveObjects()
{
   // Moves all of the objects in the vector
   for (int i = 0; i < objects.size(); i++)
      objects[i]->Move();

   // Shows all the objects in the vector
   for (int i = 0; i < objects.size(); i++)
      objects[i]->Show();
}

// This function adds an object to the back of the vector
void MyObjectList::Add(MyObject* name)
{
   objects.push_back(name);
}

// This function checks every object for collisions
void MyObjectList::objCollisions()
{
   // Loop for object your checking
   for (int i = 0; i < objects.size(); i++)
   {
      // Loop for other objects in vector you want to check
      for (int j = 0; j < objects.size(); j++)
      {
         // Making sure object is not checking for collision with itself
         if (i != j)
         {
            // Checking if object is collided with another
            if (objects[i]->CollidedWith(objects[j]))
            {
               // Calling the setScores method if object is collided
               objects[i]->setScores(objects[j]);
            }
         }
      }
   }   
   // Calls delete objects method
   deleteObjs();
}

// This method deletes objects that are collided, indicated with a score of 0
void MyObjectList::deleteObjs()
{
   for (int i = objects.size() - 1; i >= 0; i--)
   {
      if (objects[i]->IsPopped())
         objects.erase(objects.begin() + i);
   }
}

// This function gets a random number for the X-Coordinate of an object
int MyObjectList::RandomizedX()
{
   int X = rand() % 400 + 100;
   return X;
}

