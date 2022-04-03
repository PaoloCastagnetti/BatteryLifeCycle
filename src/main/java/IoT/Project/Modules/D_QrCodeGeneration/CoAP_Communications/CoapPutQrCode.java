package IoT.Project.Modules.D_QrCodeGeneration.CoAP_Communications;

import IoT.Project.DCPM.Models.QrCodeDescriptor;
import com.google.gson.Gson;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.elements.exception.ConnectorException;

import java.io.IOException;

public class CoapPutQrCode {
    private static final String COAP_ENDPOINT = "coap://127.0.0.1:5683/QrCode";
    private static Gson gson = new Gson();
    public static void putQrCode(QrCodeDescriptor qrCodeDescriptor){
        CoapClient coapClient = new CoapClient(COAP_ENDPOINT);

        //PUT
        Request request = new Request(CoAP.Code.PUT);
        request.setConfirmable(true);
        String payload = gson.toJson(qrCodeDescriptor);
        request.setPayload(payload.getBytes());

        System.out.printf("Request Pretty Print: \n%s%n", Utils.prettyPrint(request));
        try {
            CoapResponse coapResp = coapClient.advanced(request);
            System.out.printf("Response Pretty Print: \n%s%n", Utils.prettyPrint(coapResp));
        } catch (ConnectorException | IOException e) {
            e.printStackTrace();
        }

        //GET
        Request req = new Request(CoAP.Code.GET);
        req.setConfirmable(true);

        System.out.printf("Request Pretty Print: \n%s%n", Utils.prettyPrint(req));
        try{
            CoapResponse resp = coapClient.advanced(req);
            System.out.printf("Response Pretty Print: \n%s%n", Utils.prettyPrint(resp));
        }catch(ConnectorException | IOException e){
            e.printStackTrace();
        }
    }
}
