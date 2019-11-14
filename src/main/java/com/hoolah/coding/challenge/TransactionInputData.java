package com.hoolah.coding.challenge;

import java.time.LocalDateTime;

/**
 * The TransactionInputData contains the input details of the analyzer
 *
 * @author thirumaldhanasekaran
 */
public class TransactionInputData {

  /** The File name. */
  String fileName;
  /** The Merchant name. */
  String merchantName;
  /** The From date. */
  LocalDateTime fromDate;
  /** The To date. */
  LocalDateTime toDate;

  /**
   * Instantiates a new Transaction input data.
   *
   * @param fileName the file name
   * @param merchantName the merchant name
   * @param fromDate the from date
   * @param toDate the to date
   */
  public TransactionInputData(
      String fileName, String merchantName, LocalDateTime fromDate, LocalDateTime toDate) {
    this.fileName = fileName;
    this.merchantName = merchantName;
    this.fromDate = fromDate;
    this.toDate = toDate;
  }

  /**
   * Gets merchant name.
   *
   * @return the merchant name
   */
  public String getMerchantName() {
    return merchantName;
  }

  /**
   * Gets from date.
   *
   * @return the from date
   */
  public LocalDateTime getFromDate() {
    return fromDate;
  }

  /**
   * Gets to date.
   *
   * @return the to date
   */
  public LocalDateTime getToDate() {
    return toDate;
  }

  /**
   * Gets file name.
   *
   * @return the file name
   */
  public String getFileName() {
    return fileName;
  }
}
