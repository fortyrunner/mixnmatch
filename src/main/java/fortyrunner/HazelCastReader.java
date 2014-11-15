package fortyrunner;

import com.hazelcast.core.HazelcastInstance;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Map;

/**
 * Read the results from the cache and print how many
 */
public class HazelCastReader implements Processor {
  private final HazelcastInstance instance;

  public HazelCastReader(final HazelcastInstance instance) {
    this.instance = instance;
  }

  @Override
  public void process(final Exchange exchange) throws Exception {

    Map<String, HouseInfo> map = instance.getMap("customers");

    long count = map.values().stream().filter(price -> price.getPrice() > 100000).count();
    System.out.println("Cache elements with price > 100,000 = " + count);


  }
}
