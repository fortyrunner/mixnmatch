package fortyrunner;

import org.apache.camel.CamelContext;
import org.apache.camel.component.metrics.routepolicy.MetricsRoutePolicyFactory;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * A simple Camel Application to demonstrate Hawtio and CSV/XML parsing
 * CSV file of House prices taken from UK Govt statistics
 * XML files are FpNL samples from http://www.fpml.org/spec/fpml-5-0-8-rec-1/html/confirmation/fpml-5-0-examples-frame.html
 */
public class MainApp {

  /**
   * A main() so we can easily run these routing rules in our IDE
   */
  public static void main(String... args) throws Exception {
    CamelContext context = new DefaultCamelContext();

    context.addRoutePolicyFactory(new MetricsRoutePolicyFactory());
    context.addRoutes(new DataFlow());
    context.startAllRoutes();
    context.start();

    HawtioUtils utils = new HawtioUtils(false);
    utils.startHawtio();
  }


}

