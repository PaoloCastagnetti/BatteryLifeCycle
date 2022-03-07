package IoT.Project.Modules.A_Extraction.Models;

import java.util.Random;
/**
 * @author Paolo Castagnetti, 267731@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 06/02/2022 - 12:00
 */
public class UploadingActuatorDescriptor {

    //Attributes
    private int value;
    private final String unit = "%";
    private boolean rtl = false; //ready to load
    private boolean rtg = false; //ready to go
    private long l_timestamp;
    private long e_timestamp;

    private static int QUANTITY_START_VALUE = 0;
    private static final int QUANTITY_VALUE_BOUND = 10;

    //Utils
    private transient Random random;

    //Getter & Setter
    public boolean isRtl() {
        return rtl;
    }
    public void setRtl(boolean rtl) {
        this.rtl = rtl;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public String getUnit() {
        return unit;
    }
    public boolean isRtg() {
        return rtg;
    }
    public void setRtg(boolean rtg) {
        this.rtg = rtg;
    }
    public long getL_timestamp() {
        return l_timestamp;
    }
    public void setL_timestamp(long l_timestamp) {
        this.l_timestamp = l_timestamp;
    }
    public long getE_timestamp() {
        return e_timestamp;
    }
    public void setE_timestamp(long e_timestamp) {
        this.e_timestamp = e_timestamp;
    }

    //Constructor
    public UploadingActuatorDescriptor() {
        this.random = new Random();
    }

    //Methods

    //This method simulates the quantity of the extracted materials loaded on the camions
    public void measureLoadingMaterial(){
        QUANTITY_START_VALUE += this.random.nextInt(1, QUANTITY_VALUE_BOUND);
        this.value = QUANTITY_START_VALUE;
        if (this.value>100){
            int tmp = this.value - 100;
            this.value-=tmp;
        }
        this.l_timestamp = System.currentTimeMillis();
    }
}
