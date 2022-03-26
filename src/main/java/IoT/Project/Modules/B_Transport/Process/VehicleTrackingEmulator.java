package IoT.Project.Modules.B_Transport.Process;

import IoT.Project.DCPM.Models.ExtractionDescriptor;
import IoT.Project.Modules.B_Transport.CoAP_Communication.ValidatingSecondStage;
import IoT.Project.Modules.B_Transport.MQTTConfigurationParameters;
import IoT.Project.Modules.B_Transport.Models.VehicleDesctiptor;
import IoT.Project.Modules.B_Transport.Models.VehicleTelemetryData;
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

/**
 * @author Marco Savarese - 271055@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 08/03/2022 11:04
 */
public class VehicleTrackingEmulator {
    static Gson gson = new Gson();
    static ExtractionDescriptor STC;
    public static void main(String[] args) {
        //GET
        CoapClient coapClientGet = new CoapClient(ValidatingSecondStage.COAP_PREVIOUS_ENDPOINT);
        Request req = new Request(CoAP.Code.GET);
        req.setConfirmable(true);
        try{
            CoapResponse resp = coapClientGet.advanced(req);
            byte[] payload = resp.getPayload();
            String peppe = new String(payload);
            STC = gson.fromJson(peppe, ExtractionDescriptor.class);
            System.out.printf("Response Pretty Print: \n%s%n", Utils.prettyPrint(resp));
        }catch(ConnectorException | IOException e){
            e.printStackTrace();
        }
        IMqttClient mqttClient;
        try {
            String ID = String.format("Truck-%s", STC.getLoad_code());

            MqttClientPersistence persistence = new MemoryPersistence();

            mqttClient = new MqttClient(
                    String.format(
                            "tcp://%s:%d",
                            MQTTConfigurationParameters.BROKER_ADDRESS,
                            MQTTConfigurationParameters.BROKER_PORT
                    ),
                    ID,
                    persistence
            );

            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(MQTTConfigurationParameters.MQTT_USERNAME);
            options.setPassword((MQTTConfigurationParameters.MQTT_PASSWORD).toCharArray());
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);

            //Connection to the broker
            mqttClient.connect(options);
            System.out.println("Connected");

            //Vehicle
            VehicleDesctiptor vehicleDesctiptor = new VehicleDesctiptor();
            vehicleDesctiptor.setID(ID);
            vehicleDesctiptor.setDriverId(MQTTConfigurationParameters.MQTT_NUMBER);
            vehicleDesctiptor.setBrand("Ford");
            vehicleDesctiptor.setModel("E-Transit");

            //TelemetryData
            VehicleTelemetryData vehicleTelemetryData = new VehicleTelemetryData(ID);
            publishVehicleData(mqttClient, vehicleDesctiptor);
            String SC = STC.getExtraction_location();
            while(true){
                vehicleTelemetryData.updateMeasurments(SC);
                publishTelemetryData(mqttClient, vehicleDesctiptor.getID(), vehicleTelemetryData);
                if(vehicleTelemetryData.getBatteryLevel() == 0){
                    System.out.println("Destination reached");
                    break;
                }
                Thread.sleep(1000);
            }

            mqttClient.disconnect();
            mqttClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends message on topic /iot/user/<userid>/vehicle/<vehicleID>/info
     * @param mqttClient
     * @param vehicleDescriptor
     */
    public static void publishVehicleData(IMqttClient mqttClient, VehicleDesctiptor vehicleDescriptor){
        try{
            Gson gsonData = new Gson();

            if(mqttClient.isConnected()){

                String topic = String.format("%s/%s/%s/%s",
                        MQTTConfigurationParameters.MQTT_BASIC_TOPIC,
                        MQTTConfigurationParameters.VEHICLE_TOPIC,
                        vehicleDescriptor.getID(),
                        MQTTConfigurationParameters.VEHICLE_INFO_TOPIC
                );

                String payload = gsonData.toJson(vehicleDescriptor);
                //String payload = "";

                MqttMessage msg = new MqttMessage(payload.getBytes());
                msg.setQos(0);
                msg.setRetained(false);
                mqttClient.publish(topic, msg);

                System.out.println("Device Data Correctly Published! Topic" + topic + "Payload: " + payload);
            }else{
                System.err.println("Error: Topic or Msg = Null or MQTT Client is not Connected!");
            }
        }catch (Exception e){
            System.err.println("Error Publishing Vehicle Information: " + e.getLocalizedMessage());
        }
    }

    /**
     * Sends message on topic /iot/user/<userid>/vehicle/<vehicleID>/sensor/telemetry
     * @param mqttClient
     * @param vehicleID
     * @param telemetryData
     */
    public static void publishTelemetryData(IMqttClient mqttClient, String vehicleID, VehicleTelemetryData telemetryData){
        try{
            Gson gsonTel = new Gson();

            String topic = String.format("%s/%s/%s/%s/%s",
                    MQTTConfigurationParameters.MQTT_BASIC_TOPIC,
                    MQTTConfigurationParameters.VEHICLE_TOPIC,
                    vehicleID,
                    MQTTConfigurationParameters.SENSOR_TOPIC,
                    MQTTConfigurationParameters.VEHICLE_TELEMETRY_TOPIC
            );

            String payload = gsonTel.toJson(telemetryData);
            if(mqttClient.isConnected() && payload != null && topic != null){
                MqttMessage msg = new MqttMessage(payload.getBytes());
                msg.setQos(0);
                msg.setRetained(false);
                mqttClient.publish(topic, msg);
                System.out.println("Telemetry Data Correctly Published! Topic" + topic + "Payload: " + payload);


            }
        }catch (Exception e){
            System.err.println("Error Publishing Telemetry Data: " + e.getLocalizedMessage());
        }
    }
}

