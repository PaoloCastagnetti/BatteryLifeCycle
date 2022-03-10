package IoT.Project.Modules.A_Extraction.Models;

public class Cities {
    private final String[] CITIES = {"Mantova", "Modena", "Reggio Emilia", "Parma", "Milano"};

    public String[] getCITIES() {
        return CITIES;
    }
    public String getCITY(int rnd) {
        return CITIES[rnd];
    }
}
