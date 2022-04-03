package IoT.Project.Modules.D_QrCodeGeneration.CoAP_Communications;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.elements.exception.ConnectorException;

import java.io.IOException;

public class CoAPGetQrCode {
    private static final String COAP_ENDPOINT = "coap://127.0.0.1:5683/QrCode";

    public static String getQrCode(){
        String final_payload = null;
        //Initialize coapClient
        CoapClient coapClient = new CoapClient(COAP_ENDPOINT);

        //Request Class is a generic CoAP message: in this case we want a GET.
        //"Message ID", "Token" and other header's fields can be set
        Request getRequest = new Request(CoAP.Code.GET);

        //Set Request as Confirmable
        getRequest.setConfirmable(true);

        System.out.printf("Request Pretty Print: \n%s%n", Utils.prettyPrint(getRequest));
        try{
            CoapResponse resp = coapClient.advanced(getRequest);
            byte[] payload = resp.getPayload();
            final_payload = new String(payload);

        }catch(ConnectorException | IOException e){
            System.out.println("Extraction information are wrong!");
            e.printStackTrace();
        }
        return final_payload;
    }
}
