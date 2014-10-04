package fortyrunner;

import com.google.common.base.Preconditions;
import io.hawt.embedded.Main;

import java.net.URL;

/**
 * Find the hawtio utils in teh class path and start it up.
 * Uses default port 8080 unless overridden
 */
public class HawtioUtils {


  private boolean enableAuthentication;

  private int port = 8080;

  public HawtioUtils(final boolean enableAuthentication) {
    this.enableAuthentication = enableAuthentication;
  }

  public void setPort(final int value){
    Preconditions.checkArgument(value >= 8080);
    this.port = value;
  }

  public void startHawtio() throws Exception {

    URL location = this.getClass().getResource("/hawtio.war");
    String path = location.getPath();

    System.out.println("Start Hawtio");
    if (!this.enableAuthentication) {
      System.setProperty("hawtio.authenticationEnabled", "false");

    }
    Main hawtio = new Main();
    hawtio.setPort(this.port);
    hawtio.setWar(path);
    hawtio.run();
  }
}
