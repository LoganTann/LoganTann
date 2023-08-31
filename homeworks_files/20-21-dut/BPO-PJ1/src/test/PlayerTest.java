package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import appli.Player;
import appli.Stack;

/**
 * PlayerTest.java
 * Classe permettant les tests unitaires des Players.
 * @author Antoine <antoine@jiveoff.fr> & Logan Tann
 * @project Projet-BPO
 */
public class PlayerTest {

	private Player nord, sud;
	ArrayList<Integer> hand6, hand2;
	
	@Before
	public void setUp() throws Exception {
		nord = new Player("NORD");
		sud = new Player("SUD");

		hand6 = new ArrayList<>(); 
		hand6.add(4); hand6.add(2); hand6.add(5);
		hand6.add(3); hand6.add(60); hand6.add(1);

		hand2 = new ArrayList<>(); 
		hand2.add(11); hand2.add(5); 
	}

	@Test
	public void testPlayer() {
		assertEquals(6, nord.getHand().size());

		assertEquals("NORD", nord.getName());
		assertEquals("SUD", sud.getName());

		Stack asc = nord.getStack(Stack.TypeStack.ASC);
		Stack desc = nord.getStack(Stack.TypeStack.DESC);
		assertEquals(1, asc.getCardOnTop());
		assertEquals(60, desc.getCardOnTop());
	}

	@Test
	public void testToString() {
		String pattern = "(NORD|SUD ) \\^\\[\\d\\d\\] v\\[\\d\\d\\] \\(m\\dp\\d{1,2}+\\)";

		assertTrue(nord.toString().matches(pattern));		
		assertTrue(sud.toString().matches(pattern));
	}

	@Test
	public void testHand_toString() {
		String pattern = "cartes \\w+ \\{ (\\d\\d ){2,6}+\\}";
		assertTrue(nord.hand_toString().matches(pattern));
		assertTrue(sud.hand_toString().matches(pattern));
	}
	
	@Test
	public void testSortHand() {
		// (init)
		nord.setHand(hand6);
		assertEquals("[4, 2, 5, 3, 60, 1]", nord.getHand().toString());
		
		// test sorted
		nord.sortHand();
		assertEquals("[1, 2, 3, 4, 5, 60]", nord.getHand().toString());
	}
	
	@Test
	public void testRemoveCardFromHand() {
		// (init)
		nord.setHand(hand6);
		assertEquals("[4, 2, 5, 3, 60, 1]", nord.getHand().toString());
		
		nord.removeCardFromHand(3);
		assertEquals("[4, 2, 5, 60, 1]", nord.getHand().toString());
	}

	@Test
	public void testCanRemoveFromHand() {
		// (init)
		nord.setHand(hand6);
		assertEquals("[4, 2, 5, 3, 60, 1]", nord.getHand().toString());
		
		// test method
		assertTrue(nord.canRemoveFromHand(1));
		assertTrue(nord.canRemoveFromHand(60));
		assertFalse(nord.canRemoveFromHand(9));
		assertFalse(nord.canRemoveFromHand(11));
	}

	@Test
	public void testPickCards() {
		// (init)
		setPack(nord, new int[] {7, 8, 9});
		nord.setHand(hand2);
		assertEquals("[7, 8, 9]", nord.getPack().getPack().toString());
		assertEquals("[11, 5]", nord.getHand().toString());
		
		assertEquals(2, nord.pickCards(2));
		assertEquals("[9]", nord.getPack().getPack().toString());
		assertEquals("[11, 5, 7, 8]", nord.getHand().toString());

		assertEquals(1, nord.pickCards(2));
		assertEquals("[]", nord.getPack().getPack().toString());
		assertEquals("[11, 5, 7, 8, 9]", nord.getHand().toString());
	}

	@Test
	public void testHadNoMoreCards() {
		// un joueur n'a pas de paquet vide une fois créé
		assertFalse(nord.hadNoMoreCards());
		
		// vérif une fois tt vide
		nord.getPack().getPack().clear();
		nord.getHand().clear();
		assertTrue(nord.hadNoMoreCards());
	}
	
	@Test
	public void testFillCardsComplete() {
		setPack(nord, new int[] {7, 8, 9, 10, 15});
		nord.setHand(hand2);
		assertEquals("[7, 8, 9, 10, 15]", nord.getPack().getPack().toString());
		assertEquals("[11, 5]", nord.getHand().toString());
		
		// testing usual case
		assertEquals(4, nord.fillCards(6));
		assertEquals("[15]", nord.getPack().getPack().toString());
		assertEquals("[11, 5, 7, 8, 9, 10]", nord.getHand().toString());
		assertEquals(0, nord.fillCards(6));
	}

	@Test
	public void testFillCardsIncomplete() {
		// init
		setPack(nord, new int[] {7, 8, 9});
		nord.setHand(hand2);
		assertEquals("[7, 8, 9]", nord.getPack().getPack().toString());
		assertEquals("[11, 5]", nord.getHand().toString());
		
		// testing the case where there is 1 card is missing in both hand and pack
		assertEquals(3, nord.fillCards(6));
		assertEquals("[]", nord.getPack().getPack().toString());
		assertEquals("[11, 5, 7, 8, 9]", nord.getHand().toString());
		assertEquals(0, nord.fillCards(6));
	}
	
	// HORS TEST
	private void setPack(Player p, int[] values) {
		nord.getPack().getPack().clear();
		for (int i : values) {
			p.getPack().getPack().add(i);
		}
	}
}
