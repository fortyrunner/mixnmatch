package fortyrunner;

import com.netflix.hollow.core.write.HollowBlobWriter;
import com.netflix.hollow.core.write.HollowWriteStateEngine;
import com.netflix.hollow.core.write.objectmapper.HollowObjectMapper;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class HollowProcessor implements HousePriceProcessor {

  private static final Logger LOGGER = LoggerFactory.getLogger(HollowProcessor.class);

  public static final String HOLLOW_FILE_NAME = "target/hollow.bin";

  private HollowWriteStateEngine writeEngine = new HollowWriteStateEngine();

  private HollowObjectMapper mapper = new HollowObjectMapper(writeEngine);

  public HollowProcessor() {
  }

  @Override
  public void process(Exchange exchange) {

    List<HouseInfo> housePrices = HousePriceProcessor.getPrices(exchange);
    housePrices.forEach(h -> mapper.addObject(h));


    try (OutputStream os = new FileOutputStream(HOLLOW_FILE_NAME)) {

      HollowBlobWriter writer = new HollowBlobWriter(writeEngine);
      writer.writeSnapshot(os);

      LOGGER.info("Hollow file written to {}", HOLLOW_FILE_NAME);

    } catch (Exception ex) {

    }
  }


}
