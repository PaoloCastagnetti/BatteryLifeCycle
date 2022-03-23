package IoT.Project.Modules.C_Processing.Sensors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class AssemblingSensor {

    private static Logger logger = LoggerFactory.getLogger(AssemblingSensor.class);

    private int value;
    private long i_Timestamp;
    private long f_Timestamp;
    private String unit="%";
    private String deviceId;
    private final static int VALUE_BOND=10;
    private Boolean isOn;

    public AssemblingSensor(){
        super();
        init();
    }

    private void init(){

        try{
            this.i_Timestamp=System.currentTimeMillis();
            this.deviceId= UUID.randomUUID().toString();
            this.value=10;
            this.isOn=true;
            update();

        }catch (Exception e){
            logger.error("Error initializing the IoT Resource ! Msg: {}", e.getLocalizedMessage());
        }

    }

    private void update(){
        logger.info("Starting periodic Update Task with on {}",deviceId);

        while(value<100){
            value+=VALUE_BOND;
        }
        f_Timestamp=System.currentTimeMillis();
        this.value=0;
        logger.info("{} has reached 100% assembling",deviceId);    }

    @Override
    public String toString() {
        return "AssemblingSensor{" +
                "value=" + value +
                ", i_Timestamp=" + i_Timestamp +
                ", f_Timestamp=" + f_Timestamp +
                ", unit='" + unit + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", isOn=" + isOn +
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

    public Boolean getOn() {
        return isOn;
    }

    public void setOn(Boolean on) {
        isOn = on;
    }
}
