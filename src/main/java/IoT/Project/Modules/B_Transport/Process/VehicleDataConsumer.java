package IoT.Project.Modules.B_Transport.Process;

import IoT.Project.Modules.B_Transport.MQTTConfigurationParameters;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.UUID;

/**
 * @author Marco Savarese - 271055@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 26/02/2022 14:46
 */

public class VehicleDataConsumer {
    public static void main(String[] args) {
        try{

            String clientID = UUID.randomUUID().toString();
            MqttClientPersistence persistence = new MemoryPersistence();
            IMqttClient client = new MqttClient(
                    String.format("tcp://%s:%d",
                            MQTTConfigurationParameters.BROKER_ADDRESS,
                            MQTTConfigurationParameters.BROKER_PORT),
                    clientID,
                    persistence
                    );

            //Options
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(MQTTConfigurationParameters.MQTT_USERNAME);
            options.setPassword((MQTTConfigurationParameters.MQTT_PASSWORD).toCharArray());
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);

            //Connection
            client.connect(options);

            //Subscription to the topic that gives the vehicle's information
            String vehicleInfoTopic = String.format("%s/%s/+/%s",
                    MQTTConfigurationParameters.MQTT_BASIC_TOPIC,
                    MQTTConfigurationParameters.VEHICLE_TOPIC,
                    MQTTConfigurationParameters.VEHICLE_INFO_TOPIC
                    );
            client.subscribe(vehicleInfoTopic, new IMqttMessageListener() {
                @Override
                public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                    byte[] payload = mqttMessage.getPayload();
                    System.out.println("Message Received (" + topic + ") Message Received" + new String(payload));
                }
            });

            //Subscription to the topic that gives telemetry data
            String vehicleTelemetryTopic = String.format("%s/%s/+/%s",
                    MQTTConfigurationParameters.MQTT_BASIC_TOPIC,
                    MQTTConfigurationParameters.VEHICLE_TOPIC,
                    MQTTConfigurationParameters.VEHICLE_TELEMETRY_TOPIC
            );
            client.subscribe(vehicleTelemetryTopic, new IMqttMessageListener() {
                @Override
                public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                    byte[] payload = mqttMessage.getPayload();
                    System.out.println("Message Received (" + topic + ") Message Received" + new String(payload));
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
