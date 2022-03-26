package IoT.Project.Modules.B_Transport;

import IoT.Project.Modules.B_Transport.Process.VehicleDataConsumer;
import IoT.Project.Modules.B_Transport.Process.VehicleTrackingEmulator;


public class SimulationStageB {
        public static void main(String[] args){
            try {
                //DCPM.main(args);
                VehicleDataConsumer.main(args);
                Thread.sleep(2000);
                VehicleTrackingEmulator.main(args);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

}
