package IoT.Project.Modules.C_Processing.Sensors;


import IoT.Project.Modules.A_Extraction.Models.Cities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.UUID;


public class TransformingSensor {

    private int value;
    private long i_Timestamp;
    private long f_Timestamp;
    private String unit = "%";
    private String deviceId;
    private final static int VALUE_BOND = 10;
    private String location;
    private String code;
    private Cities city;
    private Random random;

    public TransformingSensor() {
        super();
        init();
    }

    private void init() {

        try {
            this.i_Timestamp = 0;
            this.deviceId = UUID.randomUUID().toString();
            this.value = 0;
            update_transform();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update_transform() throws InterruptedException {

        System.out.println(String.format("Starting periodic Update Task with on {%s}", deviceId));
        i_Timestamp = System.currentTimeMillis();
        while (value < 100) {
            Thread.sleep(1500);
            value += VALUE_BOND;
        }
        f_Timestamp = System.currentTimeMillis();
        this.value = 100;
        setLocation(city.getCITY(random.nextInt(5)));
        System.out.println(String.format("The %s is full assembled!", deviceId));
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
