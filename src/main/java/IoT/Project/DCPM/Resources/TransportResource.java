package IoT.Project.DCPM.Resources;
import IoT.Project.DCPM.Models.TransportDescriptor;
import IoT.Project.Modules.B_Transport.Models.TrackingActuatorDescriptor;
import com.google.gson.Gson;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.server.resources.CoapExchange;

import java.io.IOException;

/**
 * @author Marco Savarese, 271055@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 25/03/2022 10:37
 */

public class TransportResource extends CoapResource {
    private static final String OBJECT_TITLE = "TransportStage";
    private Gson gson;
    private TransportDescriptor TD;

    public TransportResource(String name) {
        super(name);
        init();
    }

    private void init(){
        getAttributes().setTitle(OBJECT_TITLE);
        this.gson = new Gson();
        this.TD = new TransportDescriptor();
    }


    @Override
    public void handleGET(CoapExchange exchange) {
        try{
            String responseBody = this.gson.toJson(this.TD);
            exchange.respond(CoAP.ResponseCode.CONTENT, responseBody, MediaTypeRegistry.APPLICATION_JSON);
        }catch (Exception e){
            exchange.respond(CoAP.ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void handlePUT(CoapExchange exchange) {
        try{
            String receivedPayload = new String(exchange.getRequestPayload());
            TrackingActuatorDescriptor TAD = this.gson.fromJson(receivedPayload, TrackingActuatorDescriptor.class);

            if(TAD != null && TAD.getBatterylevel() == 0){

                this.TD.setTTSS(TAD.getStimestamp());
                this.TD.setTTSE(TAD.getEtimestamp());
                this.TD.setVID(TAD.getDID());
                this.TD.setSTL(TAD.getStartLocation());
                this.TD.setENL(TAD.getEndLocation());
                exchange.respond(CoAP.ResponseCode.CHANGED);
                changed();
            }else{
                exchange.respond(CoAP.ResponseCode.BAD_REQUEST);
            }
        }catch(Exception e){
            exchange.respond(CoAP.ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }
}
