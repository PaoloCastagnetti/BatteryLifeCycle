package IoT.Project.Modules.C_Processing.Server;

import IoT.Project.Modules.C_Processing.Resource.AssemblingResource;
import IoT.Project.Modules.C_Processing.Sensors.AssemblingSensor;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class AssemblingCoapProcess extends CoapServer {
    private final static Logger logger = LoggerFactory.getLogger(AssemblingCoapProcess.class);

    public AssemblingCoapProcess(){
        super();
        String deviceId = String.format("modules:assembling:%s", UUID.randomUUID().toString());
        this.add(createAssemblingResource(deviceId));
    }

    public CoapResource createAssemblingResource(String deviceId){
        CoapResource transformingRootResource = new CoapResource("Assembler");

        //sensors
        AssemblingSensor assemblingSensor=new AssemblingSensor();
        //resource
        AssemblingResource assemblingResource=new AssemblingResource("assemble",assemblingSensor,deviceId);

        transformingRootResource.add(assemblingResource);
        return transformingRootResource;

    }

    public static void main(String[] args) {

        AssemblingCoapProcess server =new AssemblingCoapProcess();
        server.start();
        logger.info("Coap Server Started ! Available resources: ");

        server.getRoot().getChildren().stream().forEach(resource -> {
            logger.info("Resource {} -> URI: {} (Observable: {})", resource.getName(), resource.getURI(), resource.isObservable());
            if(!resource.getURI().equals("/.well-known")){
                resource.getChildren().stream().forEach(childResource -> {
                    logger.info("\t Resource {} -> URI: {} (Observable: {})", childResource.getName(), childResource.getURI(), childResource.isObservable());
                });
            }
        });

    }

}
