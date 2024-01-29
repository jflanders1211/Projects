// 
// MyObject is a abstract base class for balloons and bombs.
// 
// All CPP files need this as the first line after comment block.
#include "MyObject.h"

using namespace System;
using namespace System::ComponentModel;
using namespace System::Collections;
using namespace System::Windows::Forms;
using namespace System::Data;
using namespace System::Drawing;

void MyObject::Show()
{
   // **** Can add some code here to make your images better (transparent, etc.)
   // **** maybe stop flickering :)

   // create a graphics object for the panel
   Graphics^ g = panel->CreateGraphics();

   // draw the image to the graphics object
   g->DrawImageUnscaled(image, x, y);
}

// Don't rewrite or override this one! 
bool MyObject::CollidedWith(MyObject* b) const
{
   // make sure the passed in object is valid
   if (b == nullptr)
      return false;

   // if it is, then see if we have overlapping image regions
   return ((x + image->Width) >= b->x && (b->x + b->image->Width) >= x && (y +
      image->Height) >= b->y && (b->y + b->image->Height) >= y);
}

// This function sets the Scores for the balloons and bombs
void MyObject::setScores(MyObject* b)
{
   // need to update the score:
   // grab the current score from the textbox on your main form
   // convenient enough place to store the overall score :)
   int value = Convert::ToInt32(score->Text);

   // ballon vs. bomb
   if (b->typeOfObject() == 0 && this->typeOfObject() != 0)
   {
      // add the points for the balloon
      value += this->popScore();

      // now that these objects have collided, set their scores to 0
      b->scores = 0;
      this->scores = 0;
   }
   // bomb vs balloon
   else if (this->typeOfObject() == 0 && b->typeOfObject() != 0)
   {
      // add the points for the balloon
      value += b->popScore();

      // now that these objects have collided, set their scores to 0
      b->scores = 0;
      this->scores = 0;
   }
   // bomb vs. bomb
   else if (b->typeOfObject() == 0 && this->typeOfObject() == 0)
   {
      // subtract 5 points for a bomb to bomb collision
      value -= 5;  // could probably use popScore() as well with bombs having a -5, but oh well ...

      // now that these objects have collided, set their scores to 0
      // to indicate they should be removed from the MyObjectList
      b->scores = 0;
      this->scores = 0;
   }

   // once we're done, update the overall score
   // set the textbox with the new score
   score->Text = value.ToString();
}