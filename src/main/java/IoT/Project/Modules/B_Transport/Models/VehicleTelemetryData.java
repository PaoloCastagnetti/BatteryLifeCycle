package IoT.Project.Modules.B_Transport.Models;

/**
 * @author Marco Savarese - 271055@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 24/02/2022 - 18:12
 */
import java.util.Random;

public class VehicleTelemetryData {
    private GPSTrackerDescriptor gpsTrackerDescriptor;
    private double batterylevel = 100.0;
    private double enginetemp;
    private long timestamp;

    private transient Random random;

    //Constructor
    public VehicleTelemetryData(){
    }
    public VehicleTelemetryData(GPSTrackerDescriptor gpsTrackerDescriptor, double batterylevel, double enginetemp, long timestamp) {
        this.gpsTrackerDescriptor = gpsTrackerDescriptor;
        this.batterylevel = batterylevel;
        this.enginetemp = enginetemp;
        this.timestamp = timestamp;
    }

    //Measurments updater via random numbers
    public void updateMeasurments(){
        if(this.random == null){
            this.random = new Random(System.currentTimeMillis());
        }
        double randomlatitude = 40.85 + this.random.nextDouble() * 10.0;
        double randomlongitude = 15.27 + this.random.nextDouble() * 10.0;

        this.gpsTrackerDescriptor = new GPSTrackerDescriptor(randomlatitude, randomlongitude, 1232.0);
        this.enginetemp = 80 + this.random.nextDouble() * 10.0;
        this.batterylevel = this.batterylevel - (this.random.nextDouble() * 10.0);
        this.timestamp = System.currentTimeMillis();
    }

    //Getters and Setters
    public GPSTrackerDescriptor getGpsTrackerDescriptor() {
        return gpsTrackerDescriptor;
    }
    public void setGpsTrackerDescriptor(GPSTrackerDescriptor gpsTrackerDescriptor) {
        this.gpsTrackerDescriptor = gpsTrackerDescriptor;
    }
    public double getBatterylevel() {
        return batterylevel;
    }
    public void setBatterylevel(double batterylevel) {
        this.batterylevel = batterylevel;
    }
    public double getEnginetemp() {
        return enginetemp;
    }
    public void setEnginetemp(double enginetemp) {
        this.enginetemp = enginetemp;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    //toString Method
    @Override
    public String toString(){
        StringBuffer VehicleTelemetry = new StringBuffer("VehicleTelemetry{");
        VehicleTelemetry.append("Geolocation: ").append(gpsTrackerDescriptor);
        VehicleTelemetry.append(" Battery Level: ").append(batterylevel).append('%');
        VehicleTelemetry.append(" Engine Temperature: ").append(enginetemp).append('°');
        VehicleTelemetry.append(" Timestamp: ").append(timestamp);
        VehicleTelemetry.append('}');
        return VehicleTelemetry.toString();
    }
}
