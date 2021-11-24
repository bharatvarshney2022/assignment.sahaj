/**
 * 
 */
package com.sahaj.ai.farecalculator.service;

import java.util.List;

import com.sahaj.ai.farecalculator.exception.CalculatorException;
import com.sahaj.ai.farecalculator.model.Commute;


public interface CalculationService
{
	public double calculate(List<Commute> inputs) throws CalculatorException;
}
