package IoT.Project.Modules.C_Processing.Sensors;

import IoT.Project.Modules.A_Extraction.Models.Cities;
import java.util.Random;
import java.util.UUID;


public class TransformingSensor {

    private int value;
    private long i_Timestamp;
    private long f_Timestamp;
    private String unit = "%";
    private String deviceId;
    private final static int VALUE_BOND = 20;
    private String location;
    private String code;
    private transient Cities city;
    private transient Random random;

    public TransformingSensor() {
        this.i_Timestamp = 0;
        this.deviceId = UUID.randomUUID().toString();
        this.value = 0;
        this.random=new Random();
        this.city=new Cities();
    }


    public void update_transform() throws InterruptedException {

        System.out.printf("Starting periodic Update Task with on {%s}%n", deviceId);
        i_Timestamp = System.currentTimeMillis();
        while (value < 100) {
            value += VALUE_BOND;
            System.out.println("Working on it...");
            Thread.sleep(3000);
            System.out.printf("Trasforming percentage increased to:  %d, the current timestamp is %d, continue...%n",value,System.currentTimeMillis());
            Thread.sleep(1500);
        }
        f_Timestamp = System.currentTimeMillis();
        this.value = 100;
        setLocation(city.getCITY(random.nextInt(5)));
        System.out.printf("The device number: %s is full transformed! The current timestamp is %s%n", deviceId,f_Timestamp);
        System.out.printf("Current location is %s%n",location);
    }

    @Override
    public String toString() {
        return "TransformingSensor{" +
                "value=" + value +
                ", i_Timestamp=" + i_Timestamp +
                ", f_Timestamp=" + f_Timestamp +
                ", unit='" + unit + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", location='" + location + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public long getI_Timestamp() {
        return i_Timestamp;
    }

    public void setI_Timestamp(long i_Timestamp) {
        this.i_Timestamp = i_Timestamp;
    }

    public long getF_Timestamp() {
        return f_Timestamp;
    }

    public void setF_Timestamp(long f_Timestamp) {
        this.f_Timestamp = f_Timestamp;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
