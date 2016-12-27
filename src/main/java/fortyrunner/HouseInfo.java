package fortyrunner;


import com.google.common.base.MoreObjects;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import java.io.Serializable;

/**
 * Annotated class that matches the CSV format of our House Prices file
 * Date,Region_Name,Average_Price,Monthly_Change,Annual_Change,Average_Price_SA
 *
 */
@CsvRecord(separator = ",", crlf = "UNIX", skipFirstLine = true)
public class HouseInfo implements Serializable {

  @DataField(pos = 1)
  private String date;

  @DataField(pos = 2)
  private String name;

  @DataField(name = "Average_Price", pos = 3)
  private double price;

  @DataField(name = "Monthly_Change", pos = 4)
  private double monthlyChange;

  @DataField(name = "Annual_Change", pos = 5)
  private double annualChange;

  // Seasonally adjusted
  @DataField(name = "Average_Price_SA", pos = 6)
  private double averagePriceSAChange;


  public double getPrice() {
    return price;
  }

  public String getName() {
    return name;
  }

  public String getDate() {
    return date;
  }

  public double getMonthlyChange() {
    return monthlyChange;
  }

  public double getAnnualChange() {
    return annualChange;
  }

  public double getAveragePriceSAChange() {
    return averagePriceSAChange;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .add("date", date)
      .add("name", name)
      .add("price", price)
      .add("monthlyChange", monthlyChange)
      .add("annualChange", annualChange)
      .add("averagePriceSAChange", averagePriceSAChange)
      .toString();
  }

  public String toCSV() {
    return this.name + "," + this.date + "," + this.price;
  }


  public String getKey() {
    return toCSV();
  }
}
