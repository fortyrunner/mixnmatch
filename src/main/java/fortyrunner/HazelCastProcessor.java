package fortyrunner;

import com.hazelcast.core.HazelcastInstance;
import org.apache.camel.Exchange;

import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

/**
 * Simple use of Hazel cast, save the list in the cache
 */
public class HazelCastProcessor implements org.apache.camel.Processor {

  private HazelcastInstance hazelCast;

  public HazelCastProcessor(final HazelcastInstance hazelCast){
    this.hazelCast = hazelCast;
  }


  @Override
  public void process(final Exchange exchange) throws Exception {

    Map<String, HouseInfo> mapCustomers = this.hazelCast.getMap("customers");
    List<HouseInfo> list = (List<HouseInfo>) exchange.getIn().getBody();

    OptionalDouble average = (OptionalDouble) exchange.getIn().getHeader("average-price");

    System.out.println(String.format("Remember the average.. it was %.2f", average.getAsDouble()));

    for (HouseInfo houseInfo : list) {
      mapCustomers.put(houseInfo.getKey(), houseInfo);
    }

  }
}
