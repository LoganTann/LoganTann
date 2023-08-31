package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import appli.Stack;

/**
 * StackTest.java
 * Classe permettant les tests unitaires des Stacks.
 * @author Antoine <antoine@jiveoff.fr> & Logan Tann
 * @project Projet-BPO
 */
public class StackTest {

	private Stack AscValues_Stack, DescValues_Stack;
	
    @Before
    public void setUp() {
		AscValues_Stack = new Stack(Stack.TypeStack.ASC);
		DescValues_Stack = new Stack(Stack.TypeStack.DESC);
		
		// (values reset for every tests except firstConstructor)
		AscValues_Stack.addCard(1);  
		DescValues_Stack.addCard(60);
    }
	

	/*
	 * METHODS
	 */
	@Test
	public void firstConstructor() {
		AscValues_Stack = new Stack(Stack.TypeStack.ASC);
		DescValues_Stack = new Stack(Stack.TypeStack.DESC);
		/** -1 is the fallback value when no cards have been added */
		assertEquals(-1, AscValues_Stack.getCardOnTop());
		assertEquals(-1, DescValues_Stack.getCardOnTop());
	}
	
	@Test
	public void getterAndSetter() {
		/**  the first time we assign a value */
		AscValues_Stack.addCard(1);
		DescValues_Stack.addCard(60);
		assertEquals(1, AscValues_Stack.getCardOnTop());
		assertEquals(60, DescValues_Stack.getCardOnTop());
		
		/**  second time we change the value */
		AscValues_Stack.addCard(58);
		DescValues_Stack.addCard(8);
		assertEquals(58, AscValues_Stack.getCardOnTop());
		assertEquals(8, DescValues_Stack.getCardOnTop());
	}

	@Test
	public void toStringMethod() {
		/** by exact values */
		assertEquals("^[01]", AscValues_Stack.toString());
		assertEquals("v[60]", DescValues_Stack.toString());
		
		AscValues_Stack.addCard(58);
		DescValues_Stack.addCard(8);
		assertEquals("^[58]", AscValues_Stack.toString());
		assertEquals("v[08]", DescValues_Stack.toString());

		/** length should be constant if it follows the correct rules of the game*/
		assertEquals(5, AscValues_Stack.toString().length());
		assertEquals(5, DescValues_Stack.toString().length());
	}

	@Test
	public void CloneConstructor() {
		/** creating and checking clone */
		Stack AscClone = new Stack(this.AscValues_Stack);
		Stack DescClone = new Stack(this.DescValues_Stack);
		assertEquals(1, AscClone.getCardOnTop());   // (checking clone values)
		assertEquals(60, DescClone.getCardOnTop());
		assertEquals("^[01]", AscClone.toString());
		assertEquals("v[60]", DescClone.toString());
		
		/** editing the original stack shouldn't edit the cloned stack*/
		AscValues_Stack.addCard(58);
		DescValues_Stack.addCard(8);
		assertEquals(1, AscClone.getCardOnTop());
		assertEquals(60, DescClone.getCardOnTop());
	}
	
	
	
	/*
	 * NOT METHODS
	 */
	
	private void savestate_example_dummyValues() {
		DescValues_Stack.addCard(44);
		DescValues_Stack.addCard(54);
		AscValues_Stack.addCard(20);
		AscValues_Stack.addCard(23);
		AscValues_Stack.addCard(10);
	}
	
	
	@Test
	public void savestate_exampleByValue() {
		AscValues_Stack.addCard(3);
		DescValues_Stack.addCard(57);
		
		/**  create savestate :*/
		Stack save_StackASC = new Stack(AscValues_Stack);
		Stack save_StackDESC = new Stack(DescValues_Stack);
		
		savestate_example_dummyValues();
		
		/**  load savestate by value :*/
		AscValues_Stack.addCard(save_StackASC.getCardOnTop());
		DescValues_Stack.addCard(save_StackDESC.getCardOnTop());
		
		assertEquals(3, AscValues_Stack.getCardOnTop());  // (checking if it's success)
		assertEquals(57, DescValues_Stack.getCardOnTop());
	}

	/**
	 * Same as savestate_exampleByValue, but with a different load technique
	 */
	@Test
	public void savestate_exampleByReference() {
		AscValues_Stack.addCard(3);
		DescValues_Stack.addCard(57);
		Stack save_StackASC = new Stack(AscValues_Stack);
		Stack save_StackDESC = new Stack(DescValues_Stack);
		savestate_example_dummyValues();
		
		/**  load savestate by reference :*/
			AscValues_Stack = save_StackASC;
			DescValues_Stack = save_StackDESC;
		
		assertEquals(3, AscValues_Stack.getCardOnTop());
		assertEquals(57, DescValues_Stack.getCardOnTop());
	}
}
