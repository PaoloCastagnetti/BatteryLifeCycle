package IoT.Project.Modules.A_Extraction.CoAP_Communications;

import IoT.Project.Modules.A_Extraction.Models.UploadingActuatorDescriptor;
import com.google.gson.Gson;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.elements.exception.ConnectorException;

import java.io.IOException;

public class ValidatingFirstStage {
    private static final String COAP_ENDPOINT = "coap://127.0.0.1:5683/Extraction";
    private static Gson gson = new Gson();

    public static void sendResource(UploadingActuatorDescriptor UAD){
        CoapClient coapClient = new CoapClient(COAP_ENDPOINT);

        //Request Class is a generic CoAP message: in this case we want a GET.
        //"Message ID", "Token" and other header's fields can be set
        Request request = new Request(CoAP.Code.PUT);

        //Set Request as Confirmable
        request.setConfirmable(true);
        String payload = gson.toJson(UAD);
        request.setPayload(payload.getBytes());

        System.out.printf("Request Pretty Print: \n%s%n", Utils.prettyPrint(request));

        try {
            CoapResponse coapResp = coapClient.advanced(request);
            System.out.printf("Response Pretty Print: \n%s%n", Utils.prettyPrint(coapResp));
        } catch (ConnectorException | IOException e) {
            e.printStackTrace();
        }
        Request req = new Request(CoAP.Code.GET);
        req.setConfirmable(true);

        try{
            CoapResponse resp = coapClient.advanced(req);
            System.out.printf("Response Pretty Print: \n%s%n", Utils.prettyPrint(resp));
        }catch(ConnectorException | IOException e){
            e.printStackTrace();
        }
    }
}
