package test;
import appli.Pack;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.LinkedList;

/**
 * PackTest.java
 * Classe permettant les tests unitaires des Packs.
 * @author Antoine <antoine@jiveoff.fr> & Logan Tann
 * @project Projet-BPO
 */
public class PackTest {

	private Pack packDefault;
	
    @Before
    public void setUp() throws Exception {
        packDefault = new Pack();
    }

    @Test
    public void testConstants() {
        assertEquals(1, Pack.DEFAULT_FIRST_CARD);
        assertEquals(60, Pack.DEFAULT_LAST_CARD);
    }

    /**
     * @see testConstants() pour justif valeurs 1 et 60
     */
    @Test
    public void constructors() {
        Pack packLitterals = new Pack(1, 60);
        
        // arrays defined by the constructor ?
        assertNotNull(packDefault.getPack());
        assertNotNull(packLitterals.getPack());
        
        // array sorted ?
        LinkedList<Integer> packContent = packLitterals.getPack();
        for (int i = 0; i < 60; i++) {
        	assertTrue(packContent.get(i).equals(i + 1));
        }

        // default constructor should create a Pack(1, 60);
        assertArrayEquals(
            packLitterals.getPack().toArray(new Integer[0]),
            packDefault.getPack().toArray(new Integer[0])
        );
    }
    
    /**
     * @see constructors() "array sorted ?"
     */
    @Test
    public void pickCardMethods_and_getPackLength() {
    	// 60 cards by default
    	assertEquals(60, packDefault.getPackLength());
    	
    	// when a card is picked, it returns its value
    	assertEquals(3, packDefault.pickCard(2)); 
    	assertEquals(4, packDefault.pickCard(2));
    	// picked -> the value returned is also removed in the pack.
    	assertEquals(60 - 2, packDefault.getPackLength());
    	
    	// pickFirstCard = pickCard(0)
    	assertEquals(1, packDefault.pickFirstCard());
    	
    	// pickLastCard = pickCard( this.getPackLength() - 1)
    	assertEquals(60, packDefault.pickLastCard());
    	
    	// current pack state : [2 5 6 7 ... 58 59]
    }

    @Test
    public void cloneConstructor() {
        Pack packClone = new Pack(packDefault);
        assertEquals(packDefault.getPack(), packClone.getPack());
        
        packDefault.pickCard(0);
        packDefault.pickCard(5);
        packDefault.pickCard(0);
        assertNotEquals(packDefault.getPack(), packClone.getPack());
        
        // can be used for savestate system
        packDefault = packClone;
        assertEquals(packDefault.getPack(), packClone.getPack());
    }
    
    /**
     * @see cloneConstructor() 
     */
    @Test
    public void shuffle_and_exists() {
        Pack sorted = new Pack();
    	Pack toShuffle = new Pack();
        assertEquals(sorted.getPack(), toShuffle.getPack());
        
        toShuffle.shuffle();

        assertNotEquals(sorted.getPack(), toShuffle.getPack());
        assertEquals(sorted.getPackLength(), toShuffle.getPackLength());
        for (int card: sorted.getPack()) {
        	assertTrue(toShuffle.exists(card));
        }
    }

    @Test
    public void isEmpty() {
    	Pack verySmallPack = new Pack(1, 2); // content : [1 2]
    	verySmallPack.pickFirstCard(); // clears
    	verySmallPack.pickLastCard();
    	
    	assertTrue(verySmallPack.isEmpty());
    	assertEquals(-1, verySmallPack.pickLastCard());
    }
}