package com.hoola.coding.challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.hoolah.coding.challenge.TransactionAnalyzer;
import com.hoolah.coding.challenge.TransactionOutput;
import org.junit.Before;
import org.junit.Test;

/** @author thirumaldhanasekaran */
public class TransactionAnalyzerTest {

  public static final String CSV_RESOURCE_FILE_LOCATION = "src/test/resources/testdata.csv";

  @Before
  public void setUp() {
    System.setProperty("csvFileName", CSV_RESOURCE_FILE_LOCATION);
  }

  @Test
  public void testTransactionAnalyzerWithInvalidDataExpectNull() {
    System.setProperty("merchantName", "MacLaren");
    System.setProperty("fromTime", "20/08/2018");
    System.setProperty("toTime", "20/08/2018 13:00:00");

    TransactionOutput output = TransactionAnalyzer.runAnalyzer(new String[] {});
    assertNull(output);
  }

  @Test
  public void testTransactionAnalyzerWithReversalExpectZeroTransactions() {
    System.setProperty("merchantName", "Kwik-E-Mart");
    System.setProperty("fromTime", "20/08/2018 12:46:00");
    System.setProperty("toTime", "20/08/2018 12:47:00");

    TransactionOutput output = TransactionAnalyzer.runAnalyzer(new String[] {});
    assertEquals(Integer.valueOf(0), output.getTotalTransactions());
    assertEquals(Double.valueOf(0), output.getAverageTransactionValue());
  }

  @Test
  public void testTransactionAnalyzerWithoutReversalExpectTwoTransactions() {
    System.setProperty("merchantName", "MacLaren");
    System.setProperty("fromTime", "20/08/2018 12:45:00");
    System.setProperty("toTime", "20/08/2018 14:08:00");

    TransactionOutput output = TransactionAnalyzer.runAnalyzer(new String[] {});
    assertEquals(Integer.valueOf(2), output.getTotalTransactions());
    assertEquals(Double.valueOf(52.25), output.getAverageTransactionValue());
  }

  @Test
  public void testTransactionAnalyzer() {
    System.setProperty("merchantName", "Kwik-E-Mart");
    System.setProperty("fromTime", "20/08/2018 12:00:00");
    System.setProperty("toTime", "20/08/2018 13:00:00");

    TransactionOutput output = TransactionAnalyzer.runAnalyzer(new String[] {});

    assertEquals(Integer.valueOf(1), output.getTotalTransactions());
    assertEquals(Double.valueOf(59.99), output.getAverageTransactionValue());
  }
}
