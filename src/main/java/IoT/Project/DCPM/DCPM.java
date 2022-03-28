package IoT.Project.DCPM;

import IoT.Project.DCPM.Models.ExtractionDescriptor;
import IoT.Project.DCPM.Resources.*;
import org.eclipse.californium.core.CoapResource;
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
        ET.setObservable(true);
        ET.getAttributes().setObservable();
        this.add(ET);
        TransportResource TR = new TransportResource("Transport");
        TR.setObservable(true);
        TR.getAttributes().setObservable();
        this.add(TR);
        ProcessingResource PR = new ProcessingResource("Processing");
        TR.setObservable(true);
        TR.getAttributes().setObservable();
        this.add(PR);
        AssemblyResource AR = new AssemblyResource("Assembly");
        TR.setObservable(true);
        TR.getAttributes().setObservable();
        this.add(AR);
        BatteryResource BR = new BatteryResource("Battery");
        TR.setObservable(true);
        TR.getAttributes().setObservable();
        this.add(BR);
    }


    public static void main(String[] args) {

        DCPM coapServer = new DCPM();
        coapServer.start();

        coapServer.getRoot().getChildren().forEach(resource -> {
            System.out.printf("Resource %s -> URI: %s (Observable: %b)%n", resource.getName(), resource.getURI(), resource.isObservable());
        });
    }

}