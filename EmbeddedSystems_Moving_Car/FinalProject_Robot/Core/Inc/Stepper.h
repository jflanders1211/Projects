/*
 * Stepper.h
 *
 *  Created on: Dec 10, 2023
 *      Author: oopic
 */

#ifndef SRC_STEPPER_H_
#define SRC_STEPPER_H_

#include "STM_Pin.h"

class Stepper {
  
  public:
    // constructors:
    Stepper(int number_of_steps, STM_Pin* IN1, STM_Pin* IN2, STM_Pin* IN3, STM_Pin* IN4);


    // speed setter method:
    void setSpeed(long whatSpeed);

    // mover method:
    void step(int number_of_steps);

    int version(void);

  private:
    void stepMotor(int this_step);

    int direction;            // Direction of rotation
    unsigned long step_delay; // delay between steps, in ms, based on speed
    int number_of_steps;      // total number of steps this motor can take
    int pin_count;            // how many pins are in use.
    int step_number;          // which step the motor is on

    // motor pin numbers:
    STM_Pin* IN1;
    STM_Pin* IN2;
    STM_Pin* IN3;
    STM_Pin* IN4;

    unsigned long last_step_time; // time stamp in us of when the last step was taken
};

#endif /* SRC_STEPPER_H_ */
