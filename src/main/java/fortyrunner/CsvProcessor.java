package fortyrunner;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.OptionalDouble;


public class CsvProcessor implements HousePriceProcessor {

  private static final Logger LOGGER = LoggerFactory.getLogger(CsvProcessor.class);

  @Override
  public void process(final Exchange exchange) throws Exception {

    List<HouseInfo> housePrices = HousePriceProcessor.getPrices(exchange);

    OptionalDouble average = housePrices.stream().mapToDouble(HouseInfo::getPrice).average();

    LOGGER.info("CSV file contains {} lines, average price={}", housePrices.size(), average.getAsDouble());

    // Save the average on the message header.. avoids creation of pojo to hold a tuple

    exchange.getIn().setHeader("average-price", average);

  }
}
