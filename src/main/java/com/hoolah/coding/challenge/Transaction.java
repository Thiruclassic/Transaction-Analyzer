package com.hoolah.coding.challenge;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import java.util.Date;

/**
 * Containing the details of Transaction
 *
 * @author thirumaldhanasekaran
 */
public class Transaction {

  /** The Id. */
  @CsvBindByName(column = "ID")
  String id;
  /** The Date time. */
  @CsvBindByName(column = "Date")
  @CsvDate("dd/MM/yyyy HH:mm:ss")
  Date date;
  /** The Amount. */
  @CsvBindByName(column = "Amount")
  double amount;
  /** The Merchant. */
  @CsvBindByName(column = "Merchant")
  String merchant;
  /** The Type. */
  @CsvBindByName(column = "Type")
  String type;

  /** The Reference id. */
  @CsvBindByName(column = "Related Transaction")
  String relatedTransaction;

  /**
   * Gets id.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Gets date time.
   *
   * @return the date time
   */
  public Date getDateTime() {
    return date;
  }

  /**
   * Sets date time.
   *
   * @param dateTime the date time
   */
  public void setDateTime(Date dateTime) {
    this.date = date;
  }

  /**
   * Gets amount.
   *
   * @return the amount
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Sets amount.
   *
   * @param amount the amount
   */
  public void setAmount(double amount) {
    this.amount = amount;
  }

  /**
   * Gets merchant.
   *
   * @return the merchant
   */
  public String getMerchant() {
    return merchant;
  }

  /**
   * Sets merchant.
   *
   * @param merchant the merchant
   */
  public void setMerchant(String merchant) {
    this.merchant = merchant;
  }

  /**
   * Gets type.
   *
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * Gets transaction type.
   *
   * @return the transaction type
   */
  public TransactionType getTransactionType() {
    return TransactionType.valueOf(type);
  }

  /**
   * Sets type.
   *
   * @param type the type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Gets related transaction.
   *
   * @return the related transaction
   */
  public String getRelatedTransaction() {
    return relatedTransaction;
  }

  /**
   * Sets related transaction.
   *
   * @param relatedTransaction the related transaction
   */
  public void setRelatedTransaction(String relatedTransaction) {
    this.relatedTransaction = relatedTransaction;
  }

  /** The enum Transaction type. */
  enum TransactionType {
    /** Payment transaction type. */
    PAYMENT,
    /** Reversal transaction type. */
    REVERSAL
  }
}
