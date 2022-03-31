package IoT.Project.Modules.C_Processing.Resource;

import IoT.Project.Modules.C_Processing.Sensors.AssemblingSensor;
import com.google.gson.Gson;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

/**
 * @author Francesco Lasalvia, 271719@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 03/03/2022 - 10:09
 */

public class AssemblingResource extends CoapResource {
    private static final String OBJECT_TITLE = "AssemblingSensor";

    private AssemblingSensor assemblingSensor;
    private Gson gson;

    public AssemblingResource(String name){
        super(name);
        init();
    }

    private void init(){
        getAttributes().setTitle(OBJECT_TITLE);
        this.gson = new Gson();
        this.assemblingSensor = new AssemblingSensor();
    }

    //Guarda la get
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
    public void handlePUT(CoapExchange exchange){
        try{

            //If the request body is available
            System.out.println("Changing Assembling Resource... ");
            if(exchange.getRequestPayload() != null){
                byte[] payload = exchange.getRequestPayload();
                String final_payload = new String(payload);
                assemblingSensor= gson.fromJson(final_payload, AssemblingSensor.class);
                exchange.respond(CoAP.ResponseCode.CHANGED);
                System.out.println("Assembling resource changed successfully, current timestamp is:"+String.format("%s",System.currentTimeMillis()));
            }
            else{
                System.out.println("Couldn't change assembling resource...");
                exchange.respond(CoAP.ResponseCode.BAD_REQUEST);
            }

        }catch (Exception e){
            System.out.println("Error in coap exchange in assembing resource handlePut!!");
            exchange.respond(CoAP.ResponseCode.INTERNAL_SERVER_ERROR);
        }

    }
}
