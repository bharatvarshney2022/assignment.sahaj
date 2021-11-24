/**
 * 
 */
package com.sahaj.ai.farecalculator;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sahaj.ai.farecalculator.exception.CalculatorException;
import com.sahaj.ai.farecalculator.processor.FareProcessor;
import com.sahaj.ai.farecalculator.service.impl.CalculationServiceImpl;
import com.sahaj.ai.farecalculator.strategy.DailyCappedFareStrategy;
import com.sahaj.ai.farecalculator.strategy.TimeBasedFareStrategy;
import com.sahaj.ai.farecalculator.strategy.WeeklyCappedFareStrategy;
import com.sahaj.ai.farecalculator.util.Util;


public class Application
{
	private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * @param args
	 * @throws CalculatorException
	 */
	public static void main(String[] args) throws CalculatorException
	{
		logger.setLevel(Level.SEVERE);
		var weekStrategy = new WeeklyCappedFareStrategy();
		var dailyStrategy = new DailyCappedFareStrategy();
		var timeStrategy = new TimeBasedFareStrategy();
		//create strategy chain
		dailyStrategy.setNextFareStrategy(weekStrategy);
		timeStrategy.setNextFareStrategy(dailyStrategy);

		FareProcessor processor = new FareProcessor(new CalculationServiceImpl(timeStrategy));
		logger.log(Level.SEVERE, "-------------------------------------------------------------------");
		logger.log(Level.SEVERE, "------------------<< FARE CALCULATION ENGINE >>--------------------");
		logger.log(Level.SEVERE, "-------------------------------------------------------------------");
		File inputFile = new File(args[0]);
		try
		{
			logger.log(Level.SEVERE, "-------------------------------------------------------------------");
			logger.log(Level.SEVERE, "Output : " + processor.execute(Util.readFile(inputFile)));
			logger.log(Level.SEVERE, "-------------------------------------------------------------------");
		}
		catch (Exception e)
		{
			throw new CalculatorException(CalculatorException.INVALID_FILE, e);
		}
	}
}
