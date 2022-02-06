package IoT.Project.Modules.A_Extraction.Models;

import java.util.Random;

public class MineralQuantitySensorDescriptor {

    //Attributes
    private int value;
    private String unit = "%";
    private long timestamp;

    private static int QUANTITY_START_VALUE = 0;
    private static final int QUANTITY_VALUE_BOUND = 10;

    //Utils
    private transient Random random;

    //Getter & Setter
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    //Constructor
    public MineralQuantitySensorDescriptor() {
        this.random = new Random();
    }

    //Methods

    //This method simulates the loading of the extracted materials
    public void measureQuantityValue(){
        QUANTITY_START_VALUE += this.random.nextInt(1, QUANTITY_VALUE_BOUND);
        this.value = QUANTITY_START_VALUE;
        if (this.value>100){
            int tmp = this.value - 100;
            this.value-=tmp;
        }
        this.timestamp = System.currentTimeMillis();
    }
}
