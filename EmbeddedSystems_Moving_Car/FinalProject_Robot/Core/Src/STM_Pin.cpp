/*
 * STM_Pin.cpp
 *
 *  Created on: Dec 10, 2023
 *      Author: oopic
 */

#include "STM_Pin.h"

STM_Pin::STM_Pin(GPIO_TypeDef* GPIOx, uint16_t GPIO_Pin) {
	this->Port = GPIOx;
	this->Pin = GPIO_Pin;
}

STM_Pin::~STM_Pin() {
	this->Port = nullptr;
}


void STM_Pin::SetLow(){
	this->PinState = GPIO_PIN_RESET;
	HAL_GPIO_WritePin(Port, Pin, GPIO_PIN_RESET);
}
void STM_Pin::SetHigh(){
	this->PinState = GPIO_PIN_SET;
	HAL_GPIO_WritePin(Port, Pin, GPIO_PIN_SET);
}
void STM_Pin::Toggle(){
	if(this->isHigh()) SetLow();
	else SetHigh();
}

GPIO_PinState STM_Pin::GetState(){
	return PinState;
}

bool STM_Pin::isHigh(){
	return PinState == GPIO_PIN_SET;
}

bool STM_Pin::isLow(){
	return PinState == GPIO_PIN_RESET;
}
