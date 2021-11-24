/**
 * 
 */
package com.sahaj.ai.farecalculator.service.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sahaj.ai.farecalculator.exception.CalculatorException;
import com.sahaj.ai.farecalculator.model.Commute;
import com.sahaj.ai.farecalculator.service.CalculationService;
import com.sahaj.ai.farecalculator.strategy.FareStrategy;


public class CalculationServiceImpl implements CalculationService
{
	private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private FareStrategy<Double> strategyChain = null;

	public CalculationServiceImpl(FareStrategy<Double> strategyChain)
	{
		this.strategyChain = strategyChain;
	}

	@Override
	public double calculate(List<Commute> inputs) throws CalculatorException
	{
		try
		{
			return strategyChain.calculate(inputs, null);
		}
		catch (Exception e)
		{
			logger.log(Level.SEVERE, "Exception Msg: " + e.getMessage(), e);
			throw new CalculatorException(CalculatorException.INVALID_FILE, e);
		}
	}
}
