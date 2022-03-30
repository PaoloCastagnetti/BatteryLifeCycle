package IoT.Project.DCPM.Resources;

import IoT.Project.DCPM.Models.AssemblyDescriptor;
import com.google.gson.Gson;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.server.resources.CoapExchange;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Francesco Lasalvia, 271719@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 03/03/2022 - 10:09
 */
public class AssemblyResource extends CoapResource {

    private static final String OBJECT_TITLE = "Assembly";
    private Gson gson;
    private AssemblyDescriptor AD;
    private static final long UPDATE_TIME_MS = 10000;

    public AssemblyResource(String name) {
        super(name);
        init();
        setObservable(true);
        setObserveType(CoAP.Type.CON);
        getAttributes().setObservable();

        Timer timer = new Timer();
        timer.schedule(new AssemblyResource.UpdateTask(),0,UPDATE_TIME_MS);
    }

    private void init(){
        getAttributes().setTitle(OBJECT_TITLE);
        this.gson = new Gson();
        this.AD = new AssemblyDescriptor();
    }

    private class UpdateTask extends TimerTask {
        @Override
        public void run() {
            changed();
        }
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.setMaxAge(UPDATE_TIME_MS/1000);
        try{
            String responseBody = this.gson.toJson(this.AD);
            exchange.respond(CoAP.ResponseCode.CONTENT, responseBody, MediaTypeRegistry.APPLICATION_JSON);
        }catch (Exception e){
            exchange.respond(CoAP.ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }
}
