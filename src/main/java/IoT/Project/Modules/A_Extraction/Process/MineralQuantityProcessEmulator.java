package IoT.Project.Modules.A_Extraction.Process;

import com.google.gson.Gson;
import IoT.Project.Modules.A_Extraction.MQTTConfigurationParameters;
import IoT.Project.Modules.A_Extraction.Models.MineralQuantitySensorDescriptor;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
/**
 * @author Paolo Castagnetti, 267731@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 23/02/2022 - 10:33
 */
public class MineralQuantityProcessEmulator {
    private static final int MESSAGE_COUNT = 1000;
    public static void main(String[] args) {

        System.out.println("MineralQuantityEmulator started ...");

        try{

            String SensorId = String.format("sensor-%s", MQTTConfigurationParameters.MQTT_USERNAME);

            MqttClientPersistence persistence = new MemoryPersistence();

            IMqttClient mqttClient = new MqttClient(
                    String.format("tcp://%s:%d", MQTTConfigurationParameters.BROKER_ADDRESS, MQTTConfigurationParameters.BROKER_PORT),
                    SensorId,
                    persistence);

            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(MQTTConfigurationParameters.MQTT_USERNAME);
            options.setPassword((MQTTConfigurationParameters.MQTT_PASSWORD).toCharArray());
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);

            //Connect to the target broker
            mqttClient.connect(options);

            System.out.println("Connected !");

            //Create Sensor Reference
            MineralQuantitySensorDescriptor MQSDescriptor = new MineralQuantitySensorDescriptor();


            //Publish Vehicle Information
            //publishDeviceInfo(mqttClient, MQSDescriptor);

            //Start to publish MESSAGE_COUNT messages
            for(int i = 0; i < MESSAGE_COUNT; i++) {

                //Measure new values
                MQSDescriptor.measureQuantityValue();

                publishTelemetryData(mqttClient, MQSDescriptor);

                //Sleep for 1 Second
                Thread.sleep(3000);
            }

            //Disconnect from the broker and close the connection
            mqttClient.disconnect();
            mqttClient.close();

            System.out.println("Disconnected !");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void publishTelemetryData(IMqttClient mqttClient, MineralQuantitySensorDescriptor telemetryData) {
        try{
            Gson gson = new Gson();

            //Topic Structure: /iot/user/<user_id>/sensor/quantity/extracted
            String topic = String.format("%s/%s/%s/%s",
                    MQTTConfigurationParameters.MQTT_BASIC_TOPIC,
                    MQTTConfigurationParameters.SENSOR_TOPIC,
                    MQTTConfigurationParameters.QUANTITY_VALUE_TOPIC,
                    MQTTConfigurationParameters.EXTRACTED_TOPIC);

            String payloadString = gson.toJson(telemetryData);

            System.out.println("Publishing to Topic: " + topic + " Data: " + payloadString);

            if (mqttClient.isConnected() && payloadString != null && topic != null) {

                MqttMessage msg = new MqttMessage(payloadString.getBytes());
                msg.setQos(0);
                msg.setRetained(false);
                mqttClient.publish(topic,msg);
                System.out.println("Data Correctly Published !");
            }
            else{
                System.err.println("Error: Topic or Msg = Null or MQTT Client is not Connected !");
            }

        }catch (Exception e){
            System.err.println("Error Publishing Telemetry Information ! Error: " + e.getLocalizedMessage());
        }

    }
}

