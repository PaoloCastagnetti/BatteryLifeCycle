package IoT.Project.DCPM.Resources;

import IoT.Project.DCPM.Models.ExtractionDescriptor;
import IoT.Project.DCPM.Models.ProcessingDescriptor;
import IoT.Project.Modules.A_Extraction.Models.UploadingActuatorDescriptor;
import com.google.gson.Gson;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.server.resources.CoapExchange;

/**
 * @author Francesco Lasalvia, 271719@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 03/03/2022 - 10:09
 */
public class ProcessingResource extends CoapResource {
    private static final String OBJECT_TITLE = "ProcessingStage";
    private Gson gson;
    private ProcessingDescriptor PD;

    public ProcessingResource(String name) {
        super(name);
        init();
    }

    private void init(){
        getAttributes().setTitle(OBJECT_TITLE);
        this.gson = new Gson();
        this.PD = new ProcessingDescriptor();
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        try{
            String responseBody = this.gson.toJson(this.PD);
            exchange.respond(CoAP.ResponseCode.CONTENT, responseBody, MediaTypeRegistry.APPLICATION_JSON);
        }catch (Exception e){
            exchange.respond(CoAP.ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        try{
          this.PD.setProcessing_state(0);
          this.PD.setTimestamp_end_processing(0);
          this.PD.setTimestamp_start_processing(0);
          this.PD.setTimestamp_start_assembling_within_processing(0);
          this.PD.setTimestamp_end_assembling_within_processing(0);
            exchange.respond(CoAP.ResponseCode.CHANGED);
            changed();
        }catch (Exception e){
            exchange.respond(CoAP.ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void handlePUT(CoapExchange exchange) {
        try{
            String receivedPayload = new String(exchange.getRequestPayload());
            UploadingActuatorDescriptor UAD = this.gson.fromJson(receivedPayload, UploadingActuatorDescriptor.class);

            if(UAD != null && UAD.getL_timestamp() > 0 && UAD.getE_timestamp() > 0 && UAD.getValue()==100) {
               //  @Override
                //    public void handlePUT(CoapExchange exchange) {
                //        try{
                //            String receivedPayload = new String(exchange.getRequestPayload());
                //            UploadingActuatorDescriptor UAD = this.gson.fromJson(receivedPayload, UploadingActuatorDescriptor.class);
                //
                //            if(UAD != null && UAD.getL_timestamp() > 0 && UAD.getE_timestamp() > 0 && UAD.getValue()==100) {
                //                this.ED.setTimestamp_end_extraction(UAD.getE_timestamp());
                //                this.ED.setTimestamp_end_loading(UAD.getL_timestamp());
                //                this.ED.setLocation(UAD.getLocation());
                //                this.ED.setLoad_code(UAD.getLoad_code());
                //                this.ED.setMineral_quantity(UAD.getMineral_quantity());
                //                exchange.respond(CoAP.ResponseCode.CHANGED);
                //                changed();
                //            }
                //            else{
                //                exchange.respond(CoAP.ResponseCode.BAD_REQUEST);
                //            }
                //        }catch(Exception e){
                //            exchange.respond(CoAP.ResponseCode.INTERNAL_SERVER_ERROR);
                //        }
                //    }
                exchange.respond(CoAP.ResponseCode.CHANGED);
                changed();
            }
            else{
                exchange.respond(CoAP.ResponseCode.BAD_REQUEST);
            }
        }catch(Exception e){
            exchange.respond(CoAP.ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

}
