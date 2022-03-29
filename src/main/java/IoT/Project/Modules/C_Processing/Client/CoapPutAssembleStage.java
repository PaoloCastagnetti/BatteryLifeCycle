package IoT.Project.Modules.C_Processing.Client;

import IoT.Project.Modules.C_Processing.Sensors.AssemblingSensor;
import com.google.gson.Gson;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.elements.exception.ConnectorException;

import java.io.IOException;

public class CoapPutAssembleStage {
    private static final String COAP_ENDPOINT_TRANSFORM = "coap://127.0.0.1:5684/Assemble";
    static Gson gson;

    public static void CoapPutAssemble(AssemblingSensor assemblingSensor){
        CoapClient coapClient = new CoapClient(COAP_ENDPOINT_TRANSFORM);
        //PUT
        Request request = new Request(CoAP.Code.PUT);
        request.setConfirmable(true);
        String payload = gson.toJson(assemblingSensor);
        request.setPayload(payload.getBytes());

        System.out.printf("Request Pretty Print: \n%s%n", Utils.prettyPrint(request));
        try {
            CoapResponse coapResp = coapClient.advanced(request);
            System.out.printf("Response Pretty Print: \n%s%n", Utils.prettyPrint(coapResp));
        } catch (ConnectorException | IOException e) {
            e.printStackTrace();
        }
    }

}
