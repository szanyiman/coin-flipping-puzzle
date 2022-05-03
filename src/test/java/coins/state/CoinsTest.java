package coins.state;

import org.apache.commons.math3.util.CombinatoricsUtils;
import org.junit.jupiter.api.Test;

import java.util.BitSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoinsTest {

    private Coins state1 = new Coins(7, 3); // the original initial state

    private Coins state2; // the goal state
    {
        BitSet bs = new BitSet(7);
        bs.set(0, 7);
        state2 = new Coins(7, 3, bs);
    }

    //Need to test the following functions: isGoal, canFlip, flip, generateFlips, getFlips

    @Test
    void isGoal(){
        assertTrue(state2.isGoal());
        assertFalse(state1.isGoal());
    }

    @Test
    void canFlip(){
        BitSet bitSet = new BitSet(5);
        bitSet.set(0,7);
        assertFalse(state1.canFlip(bitSet));
        assertFalse(state2.canFlip(bitSet));
        bitSet = new BitSet(10);
        bitSet.set(1,4);
        assertTrue(state1.canFlip(bitSet));
        assertTrue(state2.canFlip(bitSet));
        bitSet = new BitSet(3);
        bitSet.set(0,8);
        assertFalse(state1.canFlip(bitSet));
        assertFalse(state2.canFlip(bitSet));
        bitSet = new BitSet(15);
        bitSet.set(2,5);
        assertTrue(state1.canFlip(bitSet));
        assertTrue(state2.canFlip(bitSet));
    }

    @Test
    void flip(){
        BitSet bitSet = new BitSet(3);
        bitSet.set(3,7);
        state1 = new Coins(8, 3);
        state1.flip(bitSet);
        assertEquals(bitSet, state1.getCoins());
        bitSet = new BitSet(5);
        bitSet.set(0, 7);
        state1.flip(bitSet);
        assertEquals(bitSet, state2.getCoins());
    }

    @Test
    void generateFlips(){
        long number = CombinatoricsUtils.binomialCoefficient(12, 7);
        List<BitSet> flips = Coins.generateFlips(12, 7);
        assertEquals((int) number, flips.size());
        number = CombinatoricsUtils.binomialCoefficient(3, 1);
        flips = Coins.generateFlips(3, 1);
        assertEquals((int) number, flips.size());
    }

    @Test
    void getFlips(){
        List<BitSet> flips = Coins.generateFlips(35, 1);
        assertEquals(flips.size(), state2.getFlips().size());
    }

    @Test
    void generateFlipsThrowingException() {
        assertThrows(IllegalArgumentException.class, () -> Coins.generateFlips(0, 7));
        assertThrows(IllegalArgumentException.class, () -> Coins.generateFlips(7, 0));
        assertThrows(IllegalArgumentException.class, () -> Coins.generateFlips(7, 10));
    }

}