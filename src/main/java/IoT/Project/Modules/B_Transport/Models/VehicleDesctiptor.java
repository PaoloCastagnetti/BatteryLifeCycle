package IoT.Project.Modules.B_Transport.Models;

/**
 * @author Marco Savarese
 * @project IoT-BatteryLifeCycle
 * @created 24/02/2022 - 12:48
 */
public class VehicleDesctiptor {
    private String ID;
    private String Brand;
    private String Model;
    private String DriverId;

    //Constructors
    public VehicleDesctiptor() {
        ID = null;
        Brand = null;
        Model = null;
        DriverId = null;
    }
    public VehicleDesctiptor(String ID, String brand, String model, String driverId) {
        this.ID = ID;
        Brand = brand;
        Model = model;
        DriverId = driverId;
    }

    //Getters and Setters
    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBrand() {
        return Brand;
    }
    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getModel() {
        return Model;
    }
    public void setModel(String model) {
        Model = model;
    }

    public String getDriverId() {
        return DriverId;
    }
    public void setDriverId(String driverId) {
        DriverId = driverId;
    }

    //toString
    @Override
    public String toString(){
       final StringBuffer VehicleInfo = new StringBuffer("Vehicle:{");
       VehicleInfo.append("ID: '").append(ID).append('\'');
       VehicleInfo.append(", Brand: '").append(Brand).append('\'');
       VehicleInfo.append(", Model: '").append(Model).append('\'');
       VehicleInfo.append(", DriverID: '").append(DriverId).append('\'');
       VehicleInfo.append('}');
       return VehicleInfo.toString();
    }
}
