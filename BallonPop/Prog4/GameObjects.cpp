#include "GameObjects.h"
#include <iostream>
using namespace std;

// Gets a random value for the X-Corrdinate of an object
int RandomizedX()
{
   int X = rand() % 400 + 100;
   return X;
}

// Moves an object the current speed and checks if the moved object is off the panel
void RedBalloon::Move()
{
   // Moving the red balloon
   y -= RedBalloonSpeed;

   // Checking if the red balloon is off the panel
   if (y <= 0)
   {
      // If off, resets it at the bottom
      y = 600;
      x = RandomizedX();
   }
}

// Returns the score of a  red balloon object
int RedBalloon::popScore()
{
   return this->RedBalloonScore;
}

// Returns red balloons type, indicated by a 1
int RedBalloon::typeOfObject()
{
   // Red balloons types are stored with the value 1
   return this->objectType;
}

// Moves an object the current speed and checks if the moved object is off the panel
void YellowBalloon::Move()
{
   // Moving the red balloon
   y -= YellowBalloonSpeed;

   // Checking if the yellow balloon is off the panel
   if (y <= 0)
   {
      // If off, resets it at the bottom
      y = 600;
      x = RandomizedX();
   }
}

// Returns the score of a  yellow balloon object
int YellowBalloon::popScore()
{
   return this->YellowBalloonScore;
}

// Returns a yellow balloons type, indicated by a 2
int YellowBalloon::typeOfObject()
{
   // Yellow balloons types are stored with the value 2
   return this->objectType;
}


// Moves an object the current speed and checks if the moved object is off the panel
void Bomb::Move()
{
   // Moving the bomb
   x += BombSpeed;

   // Checking if the bomb is off the panel
   if (x >= 612)
   {
      // If off, resets it on the left
      y = 50;
      x = 50;
   }
}

// Returns the score of a bomb object
int Bomb::popScore()
{
   return this->BombScore;
}

// Reutns the type of a bomb, indicated by a 0
int Bomb::typeOfObject()
{
   // Bomb types are stored with the value 0
   return this->objectType;
}
