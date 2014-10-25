package fortyrunner;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

import java.util.List;
import java.util.OptionalDouble;


public class CsvProcessor implements Processor {

  @Override
  public void process(final Exchange exchange) throws Exception {
    Message message = exchange.getIn();

    List<HouseInfo> body = (List<HouseInfo>)message.getBody();

    OptionalDouble average = body.stream().mapToDouble(HouseInfo::getPrice).average();

    System.out.println(String.format("CSV file contains %d lines, average price=%.2f", body.size(), average.getAsDouble()));

    // Save the average on the message header.. avoids creation of pojo to hold a tuple
    message.setHeader("average-price", average);

  }
}
