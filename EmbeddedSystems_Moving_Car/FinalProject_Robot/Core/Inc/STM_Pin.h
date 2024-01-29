/*
 * STM_Pin.h
 *
 *  Created on: Dec 10, 2023
 *      Author: oopic
 */

#include "stm32f4xx_hal.h"

#ifndef SRC_PIN_H_
#define SRC_PIN_H_

class STM_Pin {
public:
	STM_Pin(GPIO_TypeDef* GPIOx, uint16_t GPIO_Pin);
	virtual ~STM_Pin();

	void SetLow();
	void SetHigh();
	void Toggle();

	GPIO_PinState GetState();
	bool isHigh();
	bool isLow();

private:
	GPIO_TypeDef* Port;
	uint16_t Pin;
	GPIO_PinState PinState = GPIO_PIN_SET;
};

#endif /* SRC_STM_PIN_H_ */
