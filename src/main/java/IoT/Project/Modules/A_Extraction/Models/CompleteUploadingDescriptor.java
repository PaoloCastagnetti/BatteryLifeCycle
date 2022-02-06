package IoT.Project.Modules.A_Extraction.Models;

import java.util.Random;

public class CompleteUploadingDescriptor {

    //Attributes
    private boolean ready_to_load = false;
    private int value;
    private final String unit = "%";
    private boolean ready_to_go = false;
    private long timestamp;

    private static int QUANTITY_START_VALUE = 0;
    private static final int QUANTITY_VALUE_BOUND = 10;

    //Utils
    private transient Random random;

    //Getter & Setter
    public boolean isReady_to_load() {
        return ready_to_load;
    }
    public void setReady_to_load(boolean ready_to_load) {
        this.ready_to_load = ready_to_load;
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
    public boolean isReady_to_go() {
        return ready_to_go;
    }
    public void setReady_to_go(boolean ready_to_go) {
        this.ready_to_go = ready_to_go;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    //Constructor
    public CompleteUploadingDescriptor(Random random) {
        this.random = random;
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
        this.timestamp = System.currentTimeMillis();
    }
}
