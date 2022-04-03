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
        ExtractionResource ET = new ExtractionResource("Extraction");
        TransportResource TR = new TransportResource("Transport");
        ProcessingResource PR = new ProcessingResource("Processing");
        QrCodeResource QRR = new QrCodeResource("QrCode");

        this.add(ET);
        this.add(TR);
        this.add(PR);
        this.add(QRR);
    }


    public static void main(String[] args) {

        DCPM coapServer = new DCPM();
        coapServer.start();

        coapServer.getRoot().getChildren().stream().forEach(resource -> {
            System.out.printf("Resource %s -> URI: %s (Observable: %b)%n",resource.getName(), resource.getURI(), resource.isObservable());
            if(!resource.getURI().equals("/.well-known")){
                resource.getChildren().stream().forEach(childResource -> {
                    System.out.printf("Resource %s -> URI: %s (Observable: %b)%n", childResource.getName(), childResource.getURI(), childResource.isObservable());
                });
            }
        });
    }

}