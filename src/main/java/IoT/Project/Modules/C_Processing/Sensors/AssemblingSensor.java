package IoT.Project.Modules.C_Processing.Sensors;

import IoT.Project.Modules.A_Extraction.Models.Cities;

import java.util.Random;
import java.util.UUID;

/**
 * @author Francesco Lasalvia, 271719@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 03/03/2022 - 10:09
 */

public class AssemblingSensor {
    private final static int VALUE_BOND = 20;

    private int value;
    private long i_Timestamp_assembling;
    private long f_Timestamp_assembling;
    private long i_timestamp_transforming;
    private long f_timestap_transforming;
    private String unit = "%";
    private String deviceId;
    private String location;
    private String code;
    private transient Cities city;
    private transient Random random;


    public AssemblingSensor(){
        this.i_Timestamp_assembling =0;
        this.deviceId= UUID.randomUUID().toString();
        this.value=20;
        this.random=new Random();
        this.city=new Cities();
    }


    public void update_assemble() {
        System.out.println(String.format("Starting periodic Assemblation with on {%s}", deviceId));
        this.i_Timestamp_assembling =System.currentTimeMillis();

        while (value < 100) {
            try {
                value += VALUE_BOND;
                System.out.println("Working on it...");
                Thread.sleep(3000);
                System.out.println(String.format("Assemplation percentage increased to:  %i, the current timestamp is %l, continue...",value,System.currentTimeMillis()));
                Thread.sleep(1500);

            } catch (InterruptedException e) {
                System.out.println("Assemble failed!!");
                e.printStackTrace();
            }
        }
        f_Timestamp_assembling = System.currentTimeMillis();
        this.value = 100;
        setLocation(city.getCITY(random.nextInt(5)));
        System.out.println(String.format("The device number: %s is full assembled!,the current timestamp is %s", deviceId,f_Timestamp_assembling));
        System.out.println(String.format("Current location is %s",location));
    }

    @Override
    public String toString() {
        return "AssemblingSensor{" +
                "value=" + value +
                ", i_Timestamp_assembling=" + i_Timestamp_assembling +
                ", f_Timestamp_assembling=" + f_Timestamp_assembling +
                ", i_timestamp_transforming=" + i_timestamp_transforming +
                ", f_timestap_transforming=" + f_timestap_transforming +
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

    public long getI_Timestamp_assembling() {
        return i_Timestamp_assembling;
    }

    public void setI_Timestamp_assembling(long i_Timestamp_assembling) {
        this.i_Timestamp_assembling = i_Timestamp_assembling;
    }

    public long getF_Timestamp_assembling() {
        return f_Timestamp_assembling;
    }

    public void setF_Timestamp_assembling(long f_Timestamp_assembling) {
        this.f_Timestamp_assembling = f_Timestamp_assembling;
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

    public long getI_timestamp_transforming() {
        return i_timestamp_transforming;
    }

    public void setI_timestamp_transforming(long i_timestamp_transforming) {
        this.i_timestamp_transforming = i_timestamp_transforming;
    }

    public long getF_timestap_transforming() {
        return f_timestap_transforming;
    }

    public void setF_timestap_transforming(long f_timestap_transforming) {
        this.f_timestap_transforming = f_timestap_transforming;
    }
}
