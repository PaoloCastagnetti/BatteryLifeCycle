package IoT.Project.DCPM.Resources;

import org.eclipse.californium.core.CoapResource;

/**
 * @author Paolo Castagnetti, 267731@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 10/03/2022 - 10:09
 */
public class BatteryResource extends CoapResource {
    public BatteryResource(String name) {
        super(name);
    }
}
