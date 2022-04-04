package IoT.Project.Modules.C_Processing.Client;

import IoT.Project.Modules.C_Processing.Sensors.TransformingSensor;
import com.google.gson.Gson;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.elements.exception.ConnectorException;
import java.io.IOException;

/**
 * @author Francesco Lasalvia, 271719@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 03/03/2022 - 10:09
 */

public class CoapPutTransform {
    private static final String COAP_ENDPOINT_TRANSFORM = "coap://127.0.0.1:5684/Transform";

    static Gson gson=new Gson();


    public static void coapPutTransformCall(TransformingSensor transformingSensor){
        CoapClient coapClient = new CoapClient(COAP_ENDPOINT_TRANSFORM);
        //PUT
        System.out.println("Trying PUT on Transform stage..\n");
        Request request = new Request(CoAP.Code.PUT);
        request.setConfirmable(true);
        String payload = gson.toJson(transformingSensor);
        request.setPayload(payload.getBytes());

        System.out.printf("Request Pretty Print: \n%s%n", Utils.prettyPrint(request));
        try {
            CoapResponse coapResp = coapClient.advanced(request);
            System.out.printf("Response Pretty Print: \n%s%n", Utils.prettyPrint(coapResp));
            System.out.println(String.format("State of PUT on TransformStage: completed successfully,current timestamp is: %d\n",System.currentTimeMillis()));
        } catch (ConnectorException | IOException e) {
            System.out.println("Trasformg information weren't correct, something's wrong!!\n");
            e.printStackTrace();
        }
    }

}
