package com.hoolah.coding.challenge;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

/**
 * Custom csv reader to read the csv data.
 *
 * @author thirumaldhanasekaran
 */
public class CustomCSVReader extends CSVReader {

  /**
   * Instantiates a new Custom csv reader.
   *
   * @param reader the reader
   */
  public CustomCSVReader(final Reader reader) {
    super(reader);
  }

  /**
   * This method is used to read every column in the csv file and trim the spaces in the column
   *
   * @return array of values which contains the column data
   * @throws IOException
   */
  @Override
  public String[] readNext() throws IOException {
    final String[] data = super.readNext();
    return data == null
        ? data
        : Arrays.asList(data).stream().map(value -> value.trim()).toArray(String[]::new);
  }
}
