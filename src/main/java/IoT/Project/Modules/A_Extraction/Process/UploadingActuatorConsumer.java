package IoT.Project.Modules.A_Extraction.Process;

import IoT.Project.Modules.A_Extraction.MQTTConfigurationParameters;
import IoT.Project.Modules.A_Extraction.Models.MineralQuantitySensorDescriptor;
import IoT.Project.Modules.A_Extraction.Models.UploadingActuatorDescriptor;
import com.google.gson.Gson;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.Utils;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.elements.exception.ConnectorException;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.IOException;
import java.util.UUID;
/**
 * @author Paolo Castagnetti, 267731@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 23/02/2022 - 12:13
 */
public class UploadingActuatorConsumer {
    static UploadingActuatorDescriptor UAD = new UploadingActuatorDescriptor();
    static Gson gson = new Gson();
    private static final String COAP_ENDPOINT = "coap://127.0.0.1:5683/mineral";

    static void StartLoading() throws InterruptedException {
        System.out.println("Starting to load minerals into the camions");
        while(true){
            UAD.measureLoadingMaterial();
            String loading = gson.toJson(UAD);
            System.out.println("Loading: "+ loading);
            if(UAD.getValue()==100){
                UAD.setReady_to_go(true);
                SendMaterials();
                break;
            }
            Thread.sleep(1000);
        }
    }

    static void SendMaterials(){
        System.out.println("Sending Materials!");
        /*CoapClient coapClient = new CoapClient(COAP_ENDPOINT);

        //Request Class is a generic CoAP message: in this case we want a GET.
        //"Message ID", "Token" and other header's fields can be set
        Request request = new Request(CoAP.Code.POST);

        //Set Request as Confirmable
        request.setConfirmable(true);

        System.out.println(String.format("Request Pretty Print: \n%s", Utils.prettyPrint(request)));

        try {
            CoapResponse coapResp = coapClient.advanced(request);
            System.out.println(String.format("Response Pretty Print: \n%s", Utils.prettyPrint(coapResp)));
        } catch (ConnectorException | IOException e) {
            e.printStackTrace();
        }*/
    }

    public static void main(String [ ] args) {

        System.out.println("MQTT Auth Consumer Tester Started ...");

        try{

            String clientId = UUID.randomUUID().toString();

            MqttClientPersistence persistence = new MemoryPersistence();

            IMqttClient client = new MqttClient(
                    String.format("tcp://%s:%d", MQTTConfigurationParameters.BROKER_ADDRESS, MQTTConfigurationParameters.BROKER_PORT),
                    clientId,
                    persistence);

            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(MQTTConfigurationParameters.MQTT_USERNAME);
            options.setPassword((MQTTConfigurationParameters.MQTT_PASSWORD).toCharArray());
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);

            client.connect(options);
            System.out.println("Connected !");

            //Subscribe to Telemetry Data
            //Topic Structure: /iot/user/<user_id>/vehicle/<vehicle_id>/telemetry
            String sensorTelemetryTopic = String.format("%s/%s/+/%s/%s",
                    MQTTConfigurationParameters.MQTT_BASIC_TOPIC,
                    MQTTConfigurationParameters.SENSOR_TOPIC,
                    MQTTConfigurationParameters.QUANTITY_VALUE_TOPIC,
                    MQTTConfigurationParameters.EXTRACTED_TOPIC);

            client.subscribe(sensorTelemetryTopic, new IMqttMessageListener() {
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    byte[] payload = message.getPayload();
                    String msg = new String(payload);
                    System.out.println("Message Received ("+topic+") Message Received: " + msg);
                    MineralQuantitySensorDescriptor MQS = gson.fromJson(msg, MineralQuantitySensorDescriptor.class);
                    if(MQS.getValue()==100){
                        UAD.setReady_to_load(true);
                        StartLoading();
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
