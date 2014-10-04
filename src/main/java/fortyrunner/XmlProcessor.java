package fortyrunner;

import org.apache.camel.Exchange;
import org.apache.camel.Message;

/**
 * Created by john on 04/10/2014.
 */
public class XmlProcessor implements org.apache.camel.Processor {
  @Override
  public void process(final Exchange exchange) throws Exception {
    Message message = exchange.getIn();
    Object body = message.getBody();
    System.out.println(body);
  }
}
