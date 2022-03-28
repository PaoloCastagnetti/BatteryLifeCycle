package IoT.Project.Modules.C_Processing.Sensors;

import IoT.Project.Modules.A_Extraction.Models.Cities;

import java.util.Random;
import java.util.UUID;

public class AssemblingSensor {

    private int value;
    private long i_Timestamp_assembling;
    private long f_Timestamp_assembling;
    private long i_timestamp_transforming;
    private long f_timestap_transforming;
    private String unit = "%";
    private String deviceId;
    private final static int VALUE_BOND = 10;
    private String location;
    private String code;
    private Cities city;
    private Random random;


    public AssemblingSensor(){
        super();
        init();
    }

    private void init(){

        try{
            this.i_Timestamp_assembling =0;
            this.deviceId= UUID.randomUUID().toString();
            this.value=10;
            update_assemble();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void update_assemble() {
        System.out.println(String.format("Starting periodic Update Task with on {%s}", deviceId));
        this.i_Timestamp_assembling =System.currentTimeMillis();

        while (value < 100) {
            try {
                Thread.sleep(1500);
                value += VALUE_BOND;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        f_Timestamp_assembling = System.currentTimeMillis();
        this.value = 100;
        setLocation(city.getCITY(random.nextInt(5)));
        System.out.println(String.format("The %s is full assembled!", deviceId));
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
