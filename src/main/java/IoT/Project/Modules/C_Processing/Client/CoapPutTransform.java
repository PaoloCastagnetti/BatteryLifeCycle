package IoT.Project.Modules.C_Processing.Client;

import IoT.Project.Modules.C_Processing.Sensors.AssemblingSensor;
import IoT.Project.Modules.C_Processing.Sensors.TransformingSensor;
import com.google.gson.Gson;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.elements.exception.ConnectorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CoapPutTransform {
    private static final String COAP_ENDPOINT_TRANSFORM = "coap://127.0.0.1:5684/Transform";
    static Gson gson=new Gson();


    public static void CoapPutTransform(TransformingSensor transformingSensor){
        CoapClient coapClient = new CoapClient(COAP_ENDPOINT_TRANSFORM);
        //PUT
        Request request = new Request(CoAP.Code.PUT);
        request.setConfirmable(true);
        String payload = gson.toJson(transformingSensor);
        request.setPayload(payload.getBytes());

        System.out.printf("Request Pretty Print: \n%s%n", Utils.prettyPrint(request));
        try {
            CoapResponse coapResp = coapClient.advanced(request);
            System.out.printf("Response Pretty Print: \n%s%n", Utils.prettyPrint(coapResp));
        } catch (ConnectorException | IOException e) {
            System.out.println("Trasformg information weren't correct, something's wrong!!");
            e.printStackTrace();
        }
    }

}
