package com.scb.mockito;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MainControllerTest {

	//@InjectMocks annotation is used to create and inject the mock object
	
	   @InjectMocks 
	   MathController controller;

	   //@Mock annotation is used to create the mock object to be injected
	   @Mock
	   CalculatorService calcService;

	   @BeforeEach
		public void setup() {
			MockitoAnnotations.openMocks(this);
		}

	   @Test
	   public void testAdd(){
	      //add the behavior of calc service to add two numbers
	      when(calcService.add(10.0,20.0)).thenReturn(30.00);
			
	      //test the add functionality
	      assertEquals(controller.add(10.0, 20.0),30.0,0);
	   }
}
