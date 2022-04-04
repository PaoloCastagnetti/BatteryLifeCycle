package IoT.Project.Modules.C_Processing.Resource;

import IoT.Project.Modules.C_Processing.Sensors.AssemblingSensor;
import com.google.gson.Gson;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.server.resources.CoapExchange;

/**
 * @author Francesco Lasalvia, 271719@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 03/03/2022 - 10:09
 */

public class AssemblingResource extends CoapResource {
    private static final String OBJECT_TITLE = "ModCAssemble";
    private static final long UPDATE_TIME_MS = 10000;

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
        exchange.setMaxAge(UPDATE_TIME_MS/1000);
        try{
            String responseBody = this.gson.toJson(this.assemblingSensor);
            exchange.respond(CoAP.ResponseCode.CONTENT, responseBody, MediaTypeRegistry.APPLICATION_JSON);
        }catch (Exception e){
            exchange.respond(CoAP.ResponseCode.INTERNAL_SERVER_ERROR);
        }
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
