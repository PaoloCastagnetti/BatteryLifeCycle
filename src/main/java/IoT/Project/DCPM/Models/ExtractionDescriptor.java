package IoT.Project.DCPM.Models;

public class ExtractionDescriptor {
    private long timestamp_start_extraction;
    private long timestamp_end_extraction;
    private long timestamp_start_loading;
    private long timestamp_end_loading;
    private float mineral_quantity_loaded;

    public long getTimestamp_start_extraction() {
        return timestamp_start_extraction;
    }
    public void setTimestamp_start_extraction(long timestamp_start_extraction) {
        this.timestamp_start_extraction = timestamp_start_extraction;
    }
    public long getTimestamp_end_extraction() {
        return timestamp_end_extraction;
    }
    public void setTimestamp_end_extraction(long timestamp_end_extraction) {
        this.timestamp_end_extraction = timestamp_end_extraction;
    }
    public long getTimestamp_start_loading() {
        return timestamp_start_loading;
    }
    public void setTimestamp_start_loading(long timestamp_start_loading) {
        this.timestamp_start_loading = timestamp_start_loading;
    }
    public long getTimestamp_end_loading() {
        return timestamp_end_loading;
    }
    public void setTimestamp_end_loading(long timestamp_end_loading) {
        this.timestamp_end_loading = timestamp_end_loading;
    }
    public float getMineral_quantity_loaded() {
        return mineral_quantity_loaded;
    }
    public void setMineral_quantity_loaded(float mineral_quantity_loaded) {
        this.mineral_quantity_loaded = mineral_quantity_loaded;
    }

    @Override
    public String toString() {
        return "ExtractionDescriptor{" +
                "timestamp_start_extraction=" + timestamp_start_extraction +
                ", timestamp_end_extraction=" + timestamp_end_extraction +
                ", timestamp_start_loading=" + timestamp_start_loading +
                ", timestamp_end_loading=" + timestamp_end_loading +
                ", mineral_quantity_loaded=" + mineral_quantity_loaded +
                '}';
    }
}
