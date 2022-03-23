package IoT.Project.Modules.C_Processing.Server;

import IoT.Project.Modules.C_Processing.Resource.TransformingResource;
import IoT.Project.Modules.C_Processing.Sensors.AssemblingSensor;
import IoT.Project.Modules.C_Processing.Sensors.TransformingSensor;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class TransformingCoapProcess extends CoapServer {

    private final static Logger logger = LoggerFactory.getLogger(TransformingCoapProcess.class);

    public TransformingCoapProcess(){
        super();
        String deviceId = String.format("modules:processing:%s", UUID.randomUUID().toString());
        this.add(createTransformingResource(deviceId));
    }

    public CoapResource createTransformingResource(String deviceId){
        CoapResource transformingRootResource = new CoapResource("transformer");

        //sensors
        TransformingSensor transformingSensor=new TransformingSensor();
        //resource
        TransformingResource transformingResource=new TransformingResource("transform",transformingSensor,deviceId);

        transformingRootResource.add(transformingResource);
        return transformingRootResource;

    }

    public static void main(String[] args) {

        TransformingCoapProcess server =new TransformingCoapProcess();
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
