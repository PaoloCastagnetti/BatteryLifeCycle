package IoT.Project.Modules.C_Processing.Resource;

import IoT.Project.DCPM.Models.ExtractionDescriptor;
import IoT.Project.DCPM.Models.TransportDescriptor;
import IoT.Project.Modules.C_Processing.Sensors.TransformingSensor;
import com.google.gson.Gson;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransformingResource extends CoapResource {

    private TransformingSensor transformingSensor;
    private static final String OBJECT_TITLE = "TransformingSensor";
    static Gson gson;

    public TransformingResource(String name){
        super(name);
        init();
    }

    private void init(){
        getAttributes().setTitle(OBJECT_TITLE);
        //getAttributes().setObservable();
        this.gson = new Gson();
        this.transformingSensor = new TransformingSensor();
    }

    //GET DA GUARDARE
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
    public void handlePUT(CoapExchange exchange){
        try{

            //If the request body is available
            if(exchange.getRequestPayload() != null){
                byte[] payload = exchange.getRequestPayload();
                String final_payload = new String(payload);
                transformingSensor= gson.fromJson(final_payload, TransformingSensor.class);
                exchange.respond(CoAP.ResponseCode.CHANGED);
            }
            else
                exchange.respond(CoAP.ResponseCode.BAD_REQUEST);

        }catch (Exception e){
            exchange.respond(CoAP.ResponseCode.INTERNAL_SERVER_ERROR);
        }

    }


}
