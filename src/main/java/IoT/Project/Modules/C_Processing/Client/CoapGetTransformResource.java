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

public class CoapGetTransformResource {
    private static final String COAP_ENDPOINT_GET = "coap://127.0.0.1:5683/Transform";
    static TransformingSensor transformingSensor;
    static Gson gson;

    public static TransformingSensor getTransformComponent(){
        //Initialize coapClient
        CoapClient coapClient = new CoapClient(COAP_ENDPOINT_GET);
        long elements[]=new long[2];
        String string[]=new String[2];
        TransformingSensor sensor=new TransformingSensor();
        


        //Request Class is a generic CoAP message: in this case we want a GET.
        //"Message ID", "Token" and other header's fields can be set
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

            System.out.printf("Response Pretty Print: \n%s%n", Utils.prettyPrint(resp));
        }catch(ConnectorException | IOException e){
            System.out.println("The information of transform stage are wrong!");
            e.printStackTrace();
        }
        return sensor;
    }
}
