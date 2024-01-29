#pragma once
#include "MyObject.h"
#include <vector>
#include <cstddef>
using namespace std;

// This class contains a private vector that stores pointers of MyObjects.
// This class also contains methods to add objects, move the objects, check the
// objects for collisions and to delete them when needed. 
class MyObjectList
{
public:
   // Function to move all the objects
   void MoveObjects();

   // Function to add an object
   void Add(MyObject* name);

   // This function checks for object collissions
   void objCollisions();

   // Deletes appropriate objects in the vector
   void deleteObjs();

   // This function gets a random value for an objects X-Coordinate
   int RandomizedX();

private:
   vector<MyObject*> objects;
};