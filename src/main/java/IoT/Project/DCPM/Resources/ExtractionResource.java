package IoT.Project.DCPM.Resources;

import IoT.Project.DCPM.Models.ExtractionDescriptor;
import com.google.gson.Gson;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class ExtractionResource extends CoapResource {

    private static final String OBJECT_TITLE = "ExtractionStage";
    private Gson gson;
    private ExtractionDescriptor ED;

    public ExtractionResource(String name) {
        super(name);
        init();
    }

    private void init(){
        getAttributes().setTitle(OBJECT_TITLE);
        this.gson = new Gson();
        this.ED = new ExtractionDescriptor();
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        try{

        }catch (Exception e){
            exchange.respond(CoAP.ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }
}
