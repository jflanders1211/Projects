/*
 * Stepper.cpp
 *
 *  Created on: Dec 10, 2023
 *      Author: oopic
 */

#include "Stepper.h"
#include "main.h"
#include <cmath>


// HAL_GPIO_WritePin(IN1_GPIO_Port, IN1_Pin, GPIO_PIN_SET);   // IN1
// HAL_GPIO_WritePin(IN2_GPIO_Port, IN2_Pin, GPIO_PIN_RESET); // IN2

/*
 *   constructor for four-pin version
 *   Sets which wires should control the motor.
 */
Stepper::Stepper(int number_of_steps, STM_Pin* IN1, STM_Pin* IN2, STM_Pin* IN3, STM_Pin* IN4)
{
  this->step_number = 0;    // which step the motor is on
  this->direction = 0;      // motor direction
  this->last_step_time = 0; // time stamp in us of the last step taken
  this->number_of_steps = number_of_steps; // total number of steps for this motor

  // Arduino pins for the motor control connection:
  this->IN1 = IN1;
  this->IN2 = IN2;
  this->IN3 = IN3;
  this->IN4 = IN4;

  // setup the pins on the microcontroller:
  // pinMode(this->motor_pin_1, OUTPUT);
  // pinMode(this->motor_pin_2, OUTPUT);
  // pinMode(this->motor_pin_3, OUTPUT);
  // pinMode(this->motor_pin_4, OUTPUT);

  // pin_count is used by the stepMotor() method:
  this->pin_count = 4;
}

/*
 * Sets the speed in revs per minute
 */
void Stepper::setSpeed(long whatSpeed)
{
  this->step_delay = 60L * 1000L * 1000L / this->number_of_steps / whatSpeed;
}

/*
 * Moves the motor steps_to_move steps.  If the number is negative,
 * the motor moves in the reverse direction.
 */
void Stepper::step(int steps_to_move)
{
  int steps_left = abs(steps_to_move);  // how many steps to take

  // determine direction based on whether steps_to_mode is + or -:
  if (steps_to_move > 0) { this->direction = 1; }
  if (steps_to_move < 0) { this->direction = 0; }


  // decrement the number of steps, moving one step each time:
  while (steps_left > 0)
  {
    
    // move only if the appropriate delay has passed:
    
    microDelay(this->step_delay);

    
    // increment or decrement the step number,
    // depending on direction:
    if (this->direction == 1)
    {
      this->step_number++;
      if (this->step_number == this->number_of_steps) {
        this->step_number = 0;
      }
    }
    else
    {
      if (this->step_number == 0) {
        this->step_number = this->number_of_steps;
      }
      this->step_number--;
    }
    // decrement the steps left:
    steps_left--;
    // step the motor to step number 0, 1, ..., {3 or 10}

    stepMotor(this->step_number % 4);
  }
}

/*
 * Moves the motor forward or backwards.
 */
void Stepper::stepMotor(int thisStep)
{

    switch (thisStep) {
      case 0:  // 1010
        IN1->SetHigh();
        IN2->SetLow();
        IN3->SetHigh();
        IN4->SetLow();
        
      break;
      case 1:  // 0110
        IN1->SetLow();
        IN2->SetHigh();
        IN3->SetHigh();
        IN4->SetLow();
      break;
      case 2:  //0101
        IN1->SetLow();
        IN2->SetHigh();
        IN3->SetLow();
        IN4->SetHigh();
      break;
      case 3:  //1001
        IN1->SetHigh();
        IN2->SetLow();
        IN3->SetLow();
        IN4->SetHigh();
      break;
    }

}
