package test.java.exercise2;

import main.java.exercise2.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PairTest {

  /**
   * Test for creating a Pair of Integers
   */
  @Test
  public void testPairIntegerInteger() {
    // Pair pair = new Pair(1, 2);
    Pair<Integer, Integer> pair = new Pair<>(1, 2);

    // Check the object values are 1 and 2
    assertEquals(1, pair.getFirst());
    assertEquals(2, pair.getSecond());
  }

  /**
   * Test for creating a Pair of Doubles
   */
  @Test
  public void testPairDoubleDouble() {
    // Pair pair = new Pair(1, 2);
    Pair<Double, Double> pair = new Pair<>(3.0, 4.0);

    // Check the object values are 1 and 2
    assertEquals(3.0, pair.getFirst(), 0.00001);
    assertEquals(4.0, pair.getSecond(), 0.00001);
  }

  /**
   * Test for creating a Pair of String/Integer
   */
  @Test
  public void testPairStringInteger() {
    // Pair pair = new Pair(1, 2);
    Pair<String, Integer> pair = new Pair<>("Hello", 29);

    // Check the object values are 1 and 2
    assertEquals("Hello", pair.getFirst());
    assertEquals(29, pair.getSecond());
  }
  
  /**
   * Test for Pair of Integer toString()
   */
  @Test
  public void testPairIntegersToString() {
    Pair<Integer, Integer> pair = new Pair<>(1, 2);
    
    // Do NOT change this check. Fix your Pair to match this format.
    assertEquals("<1, 2>", pair.toString());
  }

  /**
   * Test for a composite parameterized Pair
   */
  @Test
  public void testPairHelperIntegerToString() {
    // Create a Pair with a parameterized type and check toString()
    Pair<Integer, Integer> sng = new Pair<>(5, 6);
    Pair<Pair<Integer, Integer>,Integer> pair = new Pair<>(sng, 10);

    // Do NOT change this check. Fix your Pair to match this format.
    assertEquals("<<5, 6>, 10>", pair.toString());
  }

}