package com.hoolah.coding.challenge;

/** @author thirumaldhanasekaran */
public class TransactionException extends RuntimeException {

  /**
   * Create a {@link TransactionException} with the specific message. Constructor that accepts a
   * message
   *
   * @param message the message
   */
  public TransactionException(String message) {
    super(message);
  }
}
