package IoT.Project.Modules.C_Processing.Client;

import IoT.Project.DCPM.Models.TransportDescriptor;
import com.google.gson.Gson;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.elements.exception.ConnectorException;

import java.io.IOException;

public class CoapGetTransportResource {

    private static final String COAP_ENDPOINT_GET = "coap://127.0.0.1:5683/Transport";
    static TransportDescriptor transportDescriptor;
    static Gson gson;

    public static String[] getTransport(){
        String elements[]=new String[2];

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
            transportDescriptor= gson.fromJson(final_payload, TransportDescriptor.class);
            //pos 0 -> vehicle id & pos 1->ending location
            elements[0]=transportDescriptor.getVID();
            elements[1]=transportDescriptor.getENL();
            System.out.printf("Response Pretty Print: \n%s%n", Utils.prettyPrint(resp));
        }catch(ConnectorException | IOException e){
            System.out.println("Transport information are wrong!");
            e.printStackTrace();
        }
        return elements;
    }

    //Questa funzione va utilizzata per una risorsa coap observable
    public static String[] getTransportObs(){
        String elements[]=new String[2];
        CoapClient coapClient = new CoapClient(COAP_ENDPOINT_GET);
        Request req = new Request(CoAP.Code.GET).setURI(COAP_ENDPOINT_GET);
        req.setConfirmable(true);
        req.setObserve();

        System.out.printf("Request Pretty Print: \n%s%n", Utils.prettyPrint(req));
        coapClient.observe(req,new CoapHandler() {

            @Override
            public void onLoad(CoapResponse response) {
                byte[] payload = response.getPayload();
                String final_payload = new String(payload);
                transportDescriptor= gson.fromJson(final_payload, TransportDescriptor.class);
                //pos 0 -> vehicle id & pos 1->ending location
                elements[0]=transportDescriptor.getVID();
                elements[1]=transportDescriptor.getENL();
                System.out.printf("Response Pretty Print: \n%s%n", Utils.prettyPrint(response));
            }
            @Override
            public void onError() {
                System.err.println("Error on loading resource");
            }
        });

        return elements;
    }

}