package IoT.Project.Modules.C_Processing.Resource;

import IoT.Project.Modules.C_Processing.Sensors.TransformingSensor;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransformingResource extends CoapResource {

    private final static Logger logger = LoggerFactory.getLogger(TransformingResource.class);
    private String deviceId;
    private TransformingSensor transformingSensor;
    private static final String OBJECT_TITLE = "TransformingSensor";
    private Boolean isOn=true;

    public TransformingResource(String name,TransformingSensor transformingSensor,String deviceId){
        super(name);
        if(transformingSensor!= null && deviceId !=null){
            this.deviceId=deviceId;
            this.transformingSensor=transformingSensor;
            getAttributes().setTitle(OBJECT_TITLE);
            getAttributes().setObservable();
            getAttributes().addAttribute("rt",transformingSensor.getDeviceId());
        }
        else {
            logger.error("Error -> NULL Raw Reference !");

        }
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.accept();

        CoapClient client = new CoapClient("localhost:5683/sensor/transform");
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
                this.transformingSensor.setOn(isOn);

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
                this.transformingSensor.setOn(this.isOn);

                exchange.respond(CoAP.ResponseCode.CHANGED);
            }
            else
                exchange.respond(CoAP.ResponseCode.BAD_REQUEST);

        }catch (Exception e){
            exchange.respond(CoAP.ResponseCode.INTERNAL_SERVER_ERROR);
        }

    }


}
