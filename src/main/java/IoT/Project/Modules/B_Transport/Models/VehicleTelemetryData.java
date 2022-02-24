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
    private int speed;
    private double enginetemp;
    private long timestamp;

    private transient Random random;


    //Constructor
    public VehicleTelemetryData(){
    }
    public VehicleTelemetryData(GPSTrackerDescriptor gpsTrackerDescriptor, double batterylevel, int speed, double enginetemp, long timestamp) {
        this.gpsTrackerDescriptor = gpsTrackerDescriptor;
        this.batterylevel = batterylevel;
        this.speed = speed;
        this.enginetemp = enginetemp;
        this.timestamp = timestamp;
    }

    public void updateMeasurments(){
        if(this.random == null){
            this.random = new Random(System.currentTimeMillis());
        }
        double randomlatitude = 40.85 + this.random.nextDouble() * 10.0;
        double randomlongitude = 15.27 + this.random.nextDouble() * 10.0;

        this.gpsTrackerDescriptor = new GPSTrackerDescriptor(randomlatitude, randomlongitude, 1232.0);

    }
}
