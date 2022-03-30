package IoT.Project.QrCodeGeneration.GetDCPM;

import IoT.Project.DCPM.Models.AssemblyDescriptor;
import IoT.Project.DCPM.Models.ExtractionDescriptor;
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

public class CoapGetAssembly {
    private static final String COAP_ENDPOINT_GET = "coap://127.0.0.1:5683/Assembly";
    static AssemblyDescriptor assemblyDescriptor;
    static Gson gson;

    public static Gson getExtractionGson(){
        //Initialize coapClient
        CoapClient coapClient = new CoapClient(COAP_ENDPOINT_GET);

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
            assemblyDescriptor = gson.fromJson(final_payload, AssemblyDescriptor.class);
            System.out.printf("Response Pretty Print: \n%s%n", Utils.prettyPrint(resp));
        }catch(ConnectorException | IOException e){
            System.out.println("Assembly information are wrong!");
            e.printStackTrace();
        }
        return gson;
    }
}
