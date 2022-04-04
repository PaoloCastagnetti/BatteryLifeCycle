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
    private static final String COAP_ENDPOINT = "coap://127.0.0.1:5683/ModCTransform";

    static Gson gson=new Gson();


    public static void coapPutTransformCall(TransformingSensor transformingSensor){
        CoapClient coapClient = new CoapClient(COAP_ENDPOINT);
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
            System.out.printf("State of PUT on TransformStage: completed successfully,current timestamp is: %d\n%n",System.currentTimeMillis());
        } catch (ConnectorException | IOException e) {
            System.out.println("Trasformg information weren't correct, something's wrong!!\n");
            e.printStackTrace();
        }

        //GET
        Request getRequest = new Request(CoAP.Code.GET);
        getRequest.setConfirmable(true);
        System.out.println("GET on the resource.\n");
        System.out.printf("Request Pretty Print: \n%s%n\n", Utils.prettyPrint(getRequest));
        try{
            CoapResponse resp = coapClient.advanced(getRequest);
            System.out.println("Response DCPM: \n");
            System.out.printf("Response Pretty Print: \n%s%n\n", Utils.prettyPrint(resp));
        }catch(ConnectorException | IOException e){
            e.printStackTrace();
        }
    }

}
