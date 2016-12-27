package fortyrunner;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * At the moment... doesn't do any XML processing.. but it could do
 */
public class XmlProcessor implements Processor {

  private static final Logger LOGGER = LoggerFactory.getLogger(XmlProcessor.class);

  @Override
  public void process(final Exchange exchange) throws Exception {
    Message message = exchange.getIn();
    Object body = message.getBody();
    LOGGER.info(body.toString());
  }
}
