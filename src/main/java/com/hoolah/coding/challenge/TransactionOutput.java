package com.hoolah.coding.challenge;

/**
 * The TransactionOutput contains the output details of the analyzer.
 *
 * @author thirumaldhanasekaran
 */
public class TransactionOutput {
  /** The Total transactions. */
  Integer totalTransactions;

  /** The Average transaction value. */
  Double averageTransactionValue;

  /**
   * Instantiates a new Transaction output.
   *
   * @param totalTransactions the total transactions
   * @param averageTransactionValue the average transaction value
   */
  public TransactionOutput(Integer totalTransactions, Double averageTransactionValue) {
    this.totalTransactions = totalTransactions;
    this.averageTransactionValue = averageTransactionValue;
  }

  /**
   * Gets total transactions.
   *
   * @return the total transactions
   */
  public Integer getTotalTransactions() {
    return totalTransactions;
  }

  /**
   * Sets total transactions.
   *
   * @param totalTransactions the total transactions
   */
  public void setTotalTransactions(Integer totalTransactions) {
    this.totalTransactions = totalTransactions;
  }

  /**
   * Gets average transaction value.
   *
   * @return the average transaction value
   */
  public Double getAverageTransactionValue() {
    return averageTransactionValue;
  }

  /**
   * Sets average transaction value.
   *
   * @param averageTransactionValue the average transaction value
   */
  public void setAverageTransactionValue(Double averageTransactionValue) {
    this.averageTransactionValue = averageTransactionValue;
  }
}
