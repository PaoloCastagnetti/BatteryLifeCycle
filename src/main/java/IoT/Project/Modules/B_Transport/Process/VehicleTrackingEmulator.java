package IoT.Project.Modules.B_Transport.Process;

import IoT.Project.Modules.B_Transport.MQTTConfigurationParameters;
import IoT.Project.Modules.B_Transport.Models.VehicleDesctiptor;
import IoT.Project.Modules.B_Transport.Models.VehicleTelemetryData;
import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @author Marco Savarese - 271055@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 08/03/2022 11:13
 */
public class VehicleTrackingEmulator {
    private static final int MESSAGE_LIMIT = 100;

    public static void main(String[] args) {

        IMqttClient mqttClient;
        try {
            String ID = String.format("Vehicle-%s", MQTTConfigurationParameters.MQTT_USERNAME);

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
            VehicleTelemetryData vehicleTelemetryData = new VehicleTelemetryData();
            publishVehicleData(mqttClient, vehicleDesctiptor);

            for (int i = 0; i < MESSAGE_LIMIT; ++i) {
                vehicleTelemetryData.updateMeasurments();

                publishTelemetryData(mqttClient, vehicleDesctiptor.getID(), vehicleTelemetryData);

                Thread.sleep(3000);
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
            Gson gson = new Gson();

            if(mqttClient.isConnected()){

                String topic = String.format("%s/%s/%s/%s",
                        MQTTConfigurationParameters.MQTT_BASIC_TOPIC,
                        MQTTConfigurationParameters.VEHICLE_TOPIC,
                        vehicleDescriptor.getID(),
                        MQTTConfigurationParameters.VEHICLE_INFO_TOPIC
                );

                String payload = gson.toJson(vehicleDescriptor);
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
            Gson gson = new Gson();

            String topic = String.format("%s/%s/%s/%s/%s",
                    MQTTConfigurationParameters.MQTT_BASIC_TOPIC,
                    MQTTConfigurationParameters.VEHICLE_TOPIC,
                    vehicleID,
                    MQTTConfigurationParameters.SENSOR_TOPIC,
                    MQTTConfigurationParameters.VEHICLE_TELEMETRY_TOPIC
            );

            String payload = gson.toJson(telemetryData);
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

