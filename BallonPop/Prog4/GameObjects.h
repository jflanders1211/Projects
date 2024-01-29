#pragma once
#include <iostream>
#include "MyObject.h"
using namespace std;

// This function gets a random value for an objects X-Coordinate
int RandomizedX();

// This is a child class of the virtual abstract class MyObject
class RedBalloon : public MyObject
{
public:
   // Constuctor
   RedBalloon(int startX, int startY, Panel^ drawingPanel, TextBox^ inScore) : MyObject(startX, startY, drawingPanel, inScore)
   {   
      // Create an image of red balloon
      image = gcnew Drawing::Bitmap("redBalloon.bmp");   
      objectType = 1;
      image->MakeTransparent();
   }

   // Virtual function for movement speed of red balloon
   void Move();
   
   // virtual function for score of red balloon 
   int popScore();

   // virtual function to specify the type of object 
   int typeOfObject();

private:
   static const int RedBalloonSpeed = 13;
   int objectType;
   int RedBalloonScore = 20;
};

// This is a child class of the virtual abstract class MyObject
class YellowBalloon : public MyObject
{
public:
   // Constuctor
   YellowBalloon(int startX, int startY, Panel^ drawingPanel, TextBox^ inScore) : MyObject(startX, startY, drawingPanel, inScore)
   {
      // Create an image of red balloon
      image = gcnew Drawing::Bitmap("yellowBalloon.bmp");
      objectType = 2;
      image->MakeTransparent();
   }

   // Virtual function for movement speed of red balloon
   void Move();

   // virtual function for score of red balloon 
   int popScore();

   // virtual function to specify the type of object 
   int typeOfObject();

private:
   static const int YellowBalloonSpeed = 10;
   int objectType;
   int YellowBalloonScore = 20;
};

// This is a child class of the virtual abstract class MyObject
class Bomb : public MyObject
{
public:
   // Constuctor
   Bomb(int startX, int startY, Panel^ drawingPanel, TextBox^ inScore) : MyObject(startX, startY, drawingPanel, inScore)
   {
      // Create an image of red balloon
      image = gcnew Drawing::Bitmap("bomb.bmp");
      objectType = 0;
      image->MakeTransparent();
   }

   // Virtual function for movement speed of red balloon
   void Move();

   // virtual function for score of red balloon 
   int popScore();

   // virtual function to specify the type of object 
   int typeOfObject();

private:
   static const int BombSpeed = 3;
   int objectType;
   int BombScore = -5;
};
