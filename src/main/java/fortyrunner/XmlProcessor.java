package fortyrunner;

import org.apache.camel.Exchange;
import org.apache.camel.Message;

/**
 * At the moment... doesn't do any XML processing.. but it could do
 */
public class XmlProcessor implements org.apache.camel.Processor {
  @Override
  public void process(final Exchange exchange) throws Exception {
    Message message = exchange.getIn();
    Object body = message.getBody();
    System.out.println(body);
  }
}
