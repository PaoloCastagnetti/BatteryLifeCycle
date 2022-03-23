package IoT.Project.Modules.C_Processing.Resource;

import IoT.Project.Modules.C_Processing.Sensors.AssemblingSensor;
import IoT.Project.Modules.C_Processing.Sensors.TransformingSensor;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssemblingResource extends CoapResource {

    private final static Logger logger = LoggerFactory.getLogger(AssemblingResource.class);
    private String deviceId;
    private AssemblingSensor assemblingSensor;
    private static final String OBJECT_TITLE = "AssemblingSensor";
    private Boolean isOn=true;

    public AssemblingResource(String name, AssemblingSensor assemblingSensor, String deviceId){
        super(name);
        if(assemblingSensor!= null && deviceId !=null){
            this.deviceId=deviceId;
            this.assemblingSensor=assemblingSensor;
            getAttributes().setTitle(OBJECT_TITLE);
            getAttributes().setObservable();
            getAttributes().addAttribute("rt",assemblingSensor.getDeviceId());
        }
        else {
            logger.error("Error -> NULL Raw Reference !");
        }
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.accept();

        CoapClient client = new CoapClient("localhost:5683/sensor/assemble");
        client.get(new CoapHandler() {
            @Override
            public void onLoad(CoapResponse response) {
                exchange.respond(response.getCode(), response.getPayload());
            }

            @Override
            public void onError() {
                exchange.respond(CoAP.ResponseCode.BAD_GATEWAY);
            }
        });

        // exchange has not been responded yet??
    }

    @Override
    public void handlePOST(CoapExchange exchange){
        exchange.accept();

        try{
            //Empty request
            if(exchange.getRequestPayload() == null){

                //Update internal status
                this.isOn = !isOn;
                this.assemblingSensor.setOn(isOn);

                exchange.respond(CoAP.ResponseCode.CHANGED);
            }
            else
                exchange.respond(CoAP.ResponseCode.BAD_REQUEST);

        }catch (Exception e){

            exchange.respond(CoAP.ResponseCode.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public void handlePUT(CoapExchange exchange){
        try{

            //If the request body is available
            if(exchange.getRequestPayload() != null){

                boolean submittedValue = Boolean.parseBoolean(new String(exchange.getRequestPayload()));

                //Update internal status
                this.isOn = submittedValue;
                this.assemblingSensor.setOn(this.isOn);

                exchange.respond(CoAP.ResponseCode.CHANGED);
            }
            else
                exchange.respond(CoAP.ResponseCode.BAD_REQUEST);

        }catch (Exception e){
            exchange.respond(CoAP.ResponseCode.INTERNAL_SERVER_ERROR);
        }

    }
}
