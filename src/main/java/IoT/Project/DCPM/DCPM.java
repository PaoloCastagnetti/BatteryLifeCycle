package IoT.Project.DCPM;

import IoT.Project.DCPM.Resources.*;
import org.eclipse.californium.core.CoapServer;

/**
 * @author Paolo Castagnetti, 267731@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 25/02/2022 - 11:12
 */
public class DCPM extends CoapServer {
    public DCPM(){
        super();

        this.add(new ExtractionResource("Extraction"));
        this.add(new TransportResource("Transport"));
        this.add(new ProcessingResource("Processing"));
        this.add(new AssemblyResource("Assembly"));
        this.add(new BatteryResource("Battery"));
    }
    public static void main(String[] args) {

        DCPM coapServer = new DCPM();
        coapServer.start();

        coapServer.getRoot().getChildren().forEach(resource -> {
            System.out.printf("Resource %s -> URI: %s (Observable: %b)%n", resource.getName(), resource.getURI(), resource.isObservable());
        });
    }
}
