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

public class CoapGetTransformResource {
    private static final String COAP_ENDPOINT_GET = "coap://127.0.0.1:5683/Transform";

    static Gson gson;

    public static TransformingSensor getTransformComponent(){
        //Initialize coapClient
        CoapClient coapClient = new CoapClient(COAP_ENDPOINT_GET);
        TransformingSensor sensor=new TransformingSensor();

        //Request Class is a generic CoAP message: in this case we want a GET.
        //"Message ID", "Token" and other header's fields can be set
        System.out.println("Asking information to Transform resource..\n");
        Request req = new Request(CoAP.Code.GET);

        //Set Request as Confirmable
        req.setConfirmable(true);

        //Synchronously send the GET message (blocking call)
        CoapResponse coapResp = null;

        System.out.printf("Request Pretty Print: \n%s%n", Utils.prettyPrint(req));
        try{
            CoapResponse resp = coapClient.advanced(req);
            byte[] payload = resp.getPayload();
            String final_payload = new String(payload);
            sensor= gson.fromJson(final_payload, TransformingSensor.class);
            System.out.println("Transform's information acquired succesfully:\n"+String.format("The current timestamp is:%d\n",System.currentTimeMillis()));
            System.out.printf("Response Pretty Print: \n%s%n", Utils.prettyPrint(resp));
            System.out.println("Ending Get on Transform Resource...\n");
        }catch(ConnectorException | IOException e){
            System.out.println("The information of transform stage are wrong!\n");
            e.printStackTrace();
        }
        return sensor;
    }
}
