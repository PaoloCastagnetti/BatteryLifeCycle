package IoT.Project.DCPM.Models;
/**
 * @author Paolo Castagnetti, 267731@studenti.unimore.it - Marco Savarese, 271055@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 03/03/2022 - 10:09
 */
public class TransportDescriptor{
    //Attributes
    private long TTSS; //Transport Timestamp Start
    private long TTSE; //Transport Timestamp End
    private String VID; //Vehicle ID
    private String STL; //Start Location
    private String ENL; //Ending Location

    //Constructor
    public TransportDescriptor(){
        this.TTSS = 0;
        this.TTSE = 0;
        this.VID = "";
        this.STL = "";
        this.ENL = "";
    }
    public TransportDescriptor(TransportDescriptor TD){
        this.TTSS = TD.TTSS;
        this.TTSE = TD.TTSE;
        this.VID = TD.VID;
        this.STL = TD.STL;
        this.ENL = TD.ENL;
    }

    //Getters and Setters
    public long getTTSS() {
        return TTSS;
    }
    public void setTTSS(long TTSS) {
        this.TTSS = TTSS;
    }

    public long getTTSE() {
        return TTSE;
    }
    public void setTTSE(long TTSE) {
        this.TTSE = TTSE;
    }

    public String getVID() {
        return VID;
    }
    public void setVID(String VID) {
        this.VID = VID;
    }

    public String getSTL() {
        return STL;
    }
    public void setSTL(String STL) {
        this.STL = STL;
    }

    public String getENL() {
        return ENL;
    }
    public void setENL(String ENL) {
        this.ENL = ENL;
    }

    //toString

    @Override
    public String toString() {
        return "TransportDescriptor{" +
                "TTSS=" + TTSS +
                ", TTSE=" + TTSE +
                ", VID='" + VID + '\'' +
                ", STL='" + STL + '\'' +
                ", ENL='" + ENL + '\'' +
                '}';
    }
}
