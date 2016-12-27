package fortyrunner;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

import java.util.List;

public interface HousePriceProcessor extends Processor {

  static List<HouseInfo> getPrices(Exchange exchange){

    Message message = exchange.getIn();

    return (List<HouseInfo>) message.getBody();
  }

}
