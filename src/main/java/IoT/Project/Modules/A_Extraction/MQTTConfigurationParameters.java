package IoT.Project.Modules.A_Extraction;

public class MQTTConfigurationParameters {
    //Do not touch
    public static String BROKER_ADDRESS = "155.185.228.20";
    public static int BROKER_PORT = 7883;
    public static final String MQTT_USERNAME="267731@studenti.unimore.it";
    public static final String MQTT_PASSWORD="gzfwajdvhoyjtytm";
    public static final String MQTT_BASIC_TOPIC= String.format("/iot/user/%s", MQTT_USERNAME);
    //Editable
    public static final String VEHICLE_TOPIC="vehicle";
    public static final String VEHICLE_TELEMETRY_TOPIC="telemetry";
    public static final String VEHICLE_INFO_TOPIC="info";
}
