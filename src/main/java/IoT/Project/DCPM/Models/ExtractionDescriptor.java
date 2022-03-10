package IoT.Project.DCPM.Models;
/**
 * @author Paolo Castagnetti, 267731@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 03/03/2022 - 10:09
 */
public class ExtractionDescriptor {
    private static final int MINERAL_QUANTITY = 150;
    private static final String UNIT = "Kg";

    private long timestamp_end_extraction;
    private long timestamp_end_loading;
    private String ExtractionLocation;

    public String getLocation() {
        return ExtractionLocation;
    }
    public void setLocation(String location) {
        ExtractionLocation = location;
    }
    public long getTimestamp_end_extraction() {
        return timestamp_end_extraction;
    }
    public void setTimestamp_end_extraction(long timestamp_end_extraction) {
        this.timestamp_end_extraction = timestamp_end_extraction;
    }
    public long getTimestamp_end_loading() {
        return timestamp_end_loading;
    }
    public void setTimestamp_end_loading(long timestamp_end_loading) {
        this.timestamp_end_loading = timestamp_end_loading;
    }

    @Override
    public String toString() {
        return "ExtractionDescriptor{" +
                "timestamp_end_extraction=" + timestamp_end_extraction +
                ", timestamp_end_loading=" + timestamp_end_loading +
                '}';
    }

}