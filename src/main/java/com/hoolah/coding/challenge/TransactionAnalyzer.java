package com.hoolah.coding.challenge;

import com.hoolah.coding.challenge.library.DateTimeUtils;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * TransactionAnalyzer is used to analyse the transactions based on the input in csv file and
 * displays the transactions related to the merchant.
 *
 * @author thirumaldhanasekaran
 */
public class TransactionAnalyzer {

  private static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm:ss";

  private static List<Transaction> transactionList;

  /**
   * Read csv file list.
   *
   * @param fileName the file name
   * @return the list
   * @throws IOException the io exception
   */
  public static List<Transaction> readCSVFile(String fileName) throws IOException {

    try (Reader reader = Files.newBufferedReader(Paths.get(fileName));
        CSVReader csvReader = new CustomCSVReader(reader); ) {
      final HeaderColumnNameMappingStrategy<Transaction> strategy =
          new HeaderColumnNameMappingStrategy<>();
      strategy.setType(Transaction.class);

      final CsvToBean<Transaction> transactionReader = new CsvToBean<Transaction>();
      transactionReader.setCsvReader(csvReader);
      transactionReader.setMappingStrategy(strategy);

      return transactionReader.parse();
    }
  }

  /**
   * Initialize data from csv.
   *
   * @param fileName the file name
   * @throws IOException the io exception
   */
  public static void initializeDataFromCSV(String fileName) throws IOException {

    transactionList = readCSVFile(fileName);
  }

  /**
   * Print transaction details.
   *
   * @param transactionOutput the transaction output
   */
  public void printTransactionDetails(TransactionOutput transactionOutput) {
    System.out.println("Number of transactions = " + transactionOutput.getTotalTransactions());
    System.out.println(
        "Average Transaction Value = " + transactionOutput.getAverageTransactionValue());
  }

  /**
   * Gets transaction details.
   *
   * @param merchantName the merchant name
   * @param fromTime the from time
   * @param endTime the end time
   * @return the transaction details
   */
  public TransactionOutput getTransactionDetails(
      String merchantName, LocalDateTime fromTime, LocalDateTime endTime) {

    // get the list of payment transactions
    List<Transaction> paymentTransactions =
        getTransactionByType(Transaction.TransactionType.PAYMENT);

    // get the list of reversal transaction Id's
    List<String> reversalTransactions =
        getTransactionByType(Transaction.TransactionType.REVERSAL)
            .stream()
            .map(transaction -> transaction.getRelatedTransaction())
            .collect(Collectors.toList());

    /**
     * get the transactions based on three conditions below and calculate total transactions and
     * average 1. transaction between from time and to time 2. Transaction related to merchant name
     * 2. If transaction is not reversed
     */
    List<Transaction> transactionDataSet =
        paymentTransactions
            .parallelStream()
            .filter(
                transaction -> {
                  LocalDateTime transactionTime =
                      DateTimeUtils.convertDateToLocalDate(transaction.getDateTime());
                  return transaction.getMerchant().equals(merchantName)
                      && fromTime.isBefore(transactionTime)
                      && endTime.isAfter(transactionTime)
                      && !reversalTransactions.contains(transaction.getId());
                })
            .collect(Collectors.toList());

    return new TransactionOutput(
        transactionDataSet.size(),
        transactionDataSet
            .stream()
            .collect(Collectors.averagingDouble(transaction -> transaction.getAmount())));
  }

  /**
   * Gets transaction by type.
   *
   * @param transactionType the transaction type
   * @return the transaction by type
   */
  public List<Transaction> getTransactionByType(Transaction.TransactionType transactionType) {
    return transactionList
        .parallelStream()
        .filter(transaction -> transaction.getTransactionType().equals(transactionType))
        .collect(Collectors.toList());
  }

  /**
   * Run analyzer transaction output.
   *
   * @param args the command line arguments
   * @return the transaction output
   * @throws Exception the exception
   */
  public static TransactionOutput runAnalyzer(String[] args) {

    TransactionAnalyzer analyzer = new TransactionAnalyzer();

    try {
      // load input parameters either from command line arguments or System Property
      TransactionInputData inputData = loadInputData(args);
      analyzer.validateTransactionInput(inputData);

      // Load CSV file
      initializeDataFromCSV(inputData.getFileName());

      // Get  Transaction output from analyzer
      TransactionOutput transactionOutput =
          analyzer.getTransactionDetails(
              inputData.getMerchantName(), inputData.getFromDate(), inputData.getToDate());

      analyzer.printTransactionDetails(transactionOutput);
      return transactionOutput;
    } catch (Exception e) {
      System.out.println("Error in running analyzer:" + ExceptionUtils.getStackTrace(e));
      return null;
    }
  }

  public static TransactionInputData loadInputData(String[] data) {
    if (data.length != 4 && data.length != 0) {
      throw new TransactionException(
          "Invalid number of parameters. Please enter all the arguments in the order --> <filename>  <merchantname> <fromDate>  <toDate> ");
    }
    if (data.length == 4) {
      return new TransactionInputData(
          data[0],
          data[1],
          DateTimeUtils.convertStringToLocalDate(data[2], DATE_TIME_PATTERN),
          DateTimeUtils.convertStringToLocalDate(data[3], DATE_TIME_PATTERN));
    } else {
      String csvFileName = System.getProperty("csvFileName");
      String merchantName = System.getProperty("merchantName");
      String fromTime = System.getProperty("fromDate");
      String toTime = System.getProperty("toDate");
      return new TransactionInputData(
          csvFileName,
          merchantName,
          DateTimeUtils.convertStringToLocalDate(fromTime, DATE_TIME_PATTERN),
          DateTimeUtils.convertStringToLocalDate(toTime, DATE_TIME_PATTERN));
    }
  }

  /**
   * Validate transaction input.
   *
   * @param inputData the input data
   */
  public void validateTransactionInput(TransactionInputData inputData) {
    if (Objects.isNull(inputData.getMerchantName())) {
      throw new TransactionException("Merchant Name cannot be empty");
    }
    if (Objects.isNull(inputData.getFromDate())) {
      throw new TransactionException("From Date cannot be empty");
    }
    if (Objects.isNull(inputData.getToDate())) {
      throw new TransactionException("To Date cannot be empty");
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   * @throws Exception the exception
   */
  public static void main(String[] args) throws Exception {
    runAnalyzer(args);
  }
}
