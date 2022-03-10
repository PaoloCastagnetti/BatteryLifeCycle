package IoT.Project.DCPM.Models;
/**
 * @author Paolo Castagnetti, 267731@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 10/03/2022 - 10:09
 */
public class BatteryDescriptor {

    //Attributes
    private ExtractionDescriptor ED;
    private AssemblyDescriptor AD;
    private ProcessingDescriptor PD;
    private TransportDescriptor TD;

    //Constructor
    public BatteryDescriptor(){
        this.ED = new ExtractionDescriptor();
        this.AD = new AssemblyDescriptor();
        this.PD = new ProcessingDescriptor();
        this.TD = new TransportDescriptor();
    }
    public BatteryDescriptor(ExtractionDescriptor _ED, AssemblyDescriptor _AD, ProcessingDescriptor _PD, TransportDescriptor _TD){
        this.ED = new ExtractionDescriptor(_ED);
        this.AD = new AssemblyDescriptor(/*_AD*/);
        this.PD = new ProcessingDescriptor(/*_PD*/);
        this.TD = new TransportDescriptor(/*_TD*/);
    }

    //Getter & Setter
    public ExtractionDescriptor getED() {
        return ED;
    }
    public void setED(ExtractionDescriptor ED) {
        this.ED = ED;
    }
    public AssemblyDescriptor getAD() {
        return AD;
    }
    public void setAD(AssemblyDescriptor AD) {
        this.AD = AD;
    }
    public ProcessingDescriptor getPD() {
        return PD;
    }
    public void setPD(ProcessingDescriptor PD) {
        this.PD = PD;
    }
    public TransportDescriptor getTD() {
        return TD;
    }
    public void setTD(TransportDescriptor TD) {
        this.TD = TD;
    }

    //Methods
    @Override
    public String toString() {
        return "BatteryDescriptor{" +
                "ED=" + ED.toString() +
                ", AD=" + AD.toString() +
                ", PD=" + PD.toString() +
                ", TD=" + TD.toString() +
                '}';
    }
}
