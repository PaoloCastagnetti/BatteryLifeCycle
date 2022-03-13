package IoT.Project.Modules.C_Processing.Models;

import IoT.Project.Modules.A_Extraction.Models.Cities;

import java.util.Random;

public class TransformingSensorDescriptor {
    //COSA MISURA?
    private int value;
    private final String unit="%";
    private long start_timestamp;
    private long end_timestamp;
    private String location;

    private transient Random random;
    private transient Cities city;

    //getter and setter


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public long getStart_timestamp() {
        return start_timestamp;
    }

    public void setStart_timestamp(long start_timestamp) {
        this.start_timestamp = start_timestamp;
    }

    public long getEnd_timestamp() {
        return end_timestamp;
    }

    public void setEnd_timestamp(long end_timestamp) {
        this.end_timestamp = end_timestamp;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    public TransformingSensorDescriptor(){
        this.random=new Random();
        this.city=new Cities();
    }
    //This method simulates the provenience of the extracted materials
    public void simulateOriginOfMaterial(){
        int rnd = this.random.nextInt(0, this.city.getCITIES().length);
        this.setLocation(this.city.getCITY(rnd));
        String code = String.format("%s.%d",this.getLocation(),this.getStart_timestamp());
    }


}
