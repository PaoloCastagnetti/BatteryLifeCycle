package IoT.Project.Modules.A_Extraction.Process;

import IoT.Project.Modules.A_Extraction.MQTTConfigurationParameters;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import java.util.UUID;
/**
 * @author Paolo Castagnetti, 267731@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 23/02/2022 - 12:13
 */
public class UploadingActuatorConsumer {
    public static void main(String [ ] args) {

        System.out.println("MQTT Auth Consumer Tester Started ...");

        try{

            //Generate a random MQTT client ID using the UUID class
            String clientId = UUID.randomUUID().toString();

            //Represents a persistent data store, used to store outbound and inbound messages while they
            //are in flight, enabling delivery to the QoS specified. In that case use a memory persistence.
            //When the application stops all the temporary data will be deleted.
            MqttClientPersistence persistence = new MemoryPersistence();

            //The the persistence is not passed to the constructor the default file persistence is used.
            //In case of a file-based storage the same MQTT client UUID should be used
            IMqttClient client = new MqttClient(
                    String.format("tcp://%s:%d", MQTTConfigurationParameters.BROKER_ADDRESS, MQTTConfigurationParameters.BROKER_PORT),
                    clientId,
                    persistence);

            //Define MQTT Connection Options such as reconnection, persistent/clean session and connection timeout
            //Authentication option can be added -> See AuthProducer example
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(MQTTConfigurationParameters.MQTT_USERNAME);
            options.setPassword((MQTTConfigurationParameters.MQTT_PASSWORD).toCharArray());
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);

            //Connect to the target broker
            client.connect(options);

            System.out.println("Connected !");


            //Subscribe to Vehicle Telemetry Data
            //Topic Structure: /iot/user/<user_id>/vehicle/<vehicle_id>/telemetry
            String sensorTelemetryTopic = String.format("%s/%s/+/%s/%s",
                    MQTTConfigurationParameters.MQTT_BASIC_TOPIC,
                    MQTTConfigurationParameters.SENSOR_TOPIC,
                    MQTTConfigurationParameters.QUANTITY_VALUE_TOPIC,
                    MQTTConfigurationParameters.EXTRACTED_TOPIC);

            //Subscribe to Vehicle Telemetry Data
            client.subscribe(sensorTelemetryTopic, new IMqttMessageListener() {
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    byte[] payload = message.getPayload();
                    System.out.println("Message Received ("+topic+") Message Received: " + new String(payload));
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
