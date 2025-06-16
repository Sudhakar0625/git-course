package com.healthycoderapp;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BMICalculatorTest {
	
	private String env="dev";
	@BeforeAll
	static void beforeAll()
	{
		System.out.println("Berfore all unit test case");
	}
	
	@AfterAll
	static void afterAll()
	{
		System.out.println("After all unit test case");
	}
	@Nested
	class IsDietRecommendedTests
	{
		
		@ParameterizedTest(name="weight={0},height={1}")
		//@CsvSource(value= {"89.0,1.72" ,"95.0,1.75","110.0,1.78"})
		@CsvFileSource(resources="/diet-recommended-input-data.csv",numLinesToSkip=1)
		void should_ReturnTrue_When_DietRecommended(double codeWeight,double codeHeight) {
			//given
			double wieght=codeWeight;
			double height=codeHeight;
			
			//when
			boolean recommended=BMICalculator.isDietRecommended(wieght,height);
			
			//then
			
			assertTrue(recommended);
		}
		
		@Test
		void should_ReturnFalse_When_DietNotRecommended() {
			//given
			double wieght=50.0;
			double hieght=1.92;
			
			//when
			boolean recommended=BMICalculator.isDietRecommended(wieght,hieght);
			
			//then
			
			assertFalse(recommended);
		}
		
		@Test
		void should_ThrowArithemeticException_When_HieghtZero() {
			//given
			double wieght=50.0;
			double hieght=0.0;
			
			//when
			 Executable executable=()->BMICalculator.isDietRecommended(wieght,hieght);
			
			//then
			
			assertThrows(ArithmeticException.class,executable);
		}
		
	}
	
	@Nested
	@DisplayName("Sample Inner class")
	class FindCoderWithWorstBMITests
	{
		@Test
		void should_ReturnCoderWithWrostBMI_When_CoderListNotEmpty()
		{
			//given
			List<Coder> coders=new ArrayList<>();
			coders.add(new Coder(1.80, 60.0));
			coders.add(new Coder(1.82, 98.0));
			coders.add(new Coder(1.82, 64.7));
			
			//When
			Coder coderWorstBMI=BMICalculator.findCoderWithWorstBMI(coders);
			
			//Then
			assertAll(
			()->assertEquals(1.82, coderWorstBMI.getHeight()),
			()->assertEquals(98.0, coderWorstBMI.getWeight())
		);
		}
		

		@Test
		void should_ReturnCoderWithWrostBMIIn1Ms_When_CoderListHas10000Elements()
		{
			assumeTrue(BMICalculatorTest.this.env.equals("dev"));
			//given
			List<Coder> coders=new ArrayList<>();
			for(int i=0;i <10000;i++)
			{
				coders.add(new Coder(1.0+i, 10+i));
			}
			
			//when
			
			Executable executable=()->BMICalculator.findCoderWithWorstBMI(coders);
			
			//then
			assertTimeout(Duration.ofMillis(500),executable);
		}
		
		@Test
		void should_ReturnNullWithWrostBMI_When_CoderListNotEmpty()
		{
			//given
			List<Coder> coders=new ArrayList<>();

			//When
			Coder coderWorstBMI=BMICalculator.findCoderWithWorstBMI(coders);
			
			//Then
			assertNull(coderWorstBMI);
		
		}
		
		
		
	}
	
	@Nested
	class GetBMIScoresTests
	{
		@Test
		//@Disabled
		@DisabledOnOs(OS.WINDOWS)
		void should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty()
		{
			//given
			List<Coder> coders=new ArrayList<>();
			coders.add(new Coder(1.80, 60.0));
			coders.add(new Coder(1.82, 98.0));
			coders.add(new Coder(1.82, 64.7));
			double[] expected= {18.52,29.59,19.53};

			//When
			double[] bmiScores =BMICalculator.getBMIScores(coders);
			
			//Then
			assertArrayEquals(expected,bmiScores);
		
		}


		
	}
	
	
	
	
	
}
