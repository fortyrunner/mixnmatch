package fortyrunner;


import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.model.dataformat.CsvDataFormat;

/**
 * Setup routes to
 * <ol>
 * <li>watch for files in src/data</li>
 * <li>process CSV and XML on separate SEDA routes</li>
 * <li>Recombine output of CSV and XML parsing onto a new queue</li>
 * <li>Implement (non-transactional) persistence by multi-casting the output to (dummy) end points </li>
 * </ol>
 *
 */
public class DataFlow extends RouteBuilder {
  /**
   * Let's configure the Camel routing rules using Java code...
   */
  public void configure() {

    CsvDataFormat csv = new CsvDataFormat();
    csv.setSkipFirstLine(true);


    // here is a sample which processes the input files
    // (leaving them in place - see the 'noop' flag)
    from("file:src/data?noop=true").id("A. File Loader and Router").
      log("Loaded ${file:name}").
      choice().
      when(header("CamelFileName").
        endsWith("csv")).to("seda:csv").
      when(header("CamelFileName").
        endsWith("xml")).to("seda:xml");

    from("seda:csv").id("B. CSV Parser").log("Parsing CSV").
      unmarshal().
      bindy(BindyType.Csv, HouseInfo.class).
      process(new CsvProcessor()).
      to("seda:persist");

    from("seda:xml").id("C. XML Parser").log("Parsing XML").
      split().tokenizeXML("partyId").streaming().
      process(new XmlProcessor()).
      to("seda:persist");


    from("seda:persist").id("D. Replicator").
      log("Replicate to Database, cache and backup cache").
      multicast().
      to("mock:database").
      to("mock:cache").
      to("mock:backup-cache");

  }
}
