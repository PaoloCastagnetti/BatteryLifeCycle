package IoT.Project.Modules.C_Processing.process;

import IoT.Project.Modules.C_Processing.Client.CoapGetTransportResource;
import IoT.Project.Modules.C_Processing.Client.CoapPutTransform;
import IoT.Project.Modules.C_Processing.Sensors.TransformingSensor;

public class Transformingprocess {

    public static void main(String[] args) {
        //fare get su Transport
        String[] elements=CoapGetTransportResource.getTransportObs();

        if (elements[0] != null && elements[1]!= null){
            TransformingSensor transformingSensor=new TransformingSensor();
            try {
                transformingSensor.update_transform();
            }catch(Exception e){
                e.printStackTrace();
            }
            transformingSensor.setCode(elements[0]);
            transformingSensor.setLocation(elements[1]);

        //adesso tocca alla put sull'inetermediate server
        System.out.println("Sending information to Assembling SEnsor...");
        CoapPutTransform.CoapPutTransform(transformingSensor);
        System.out.println("Assembling sensor got everithing!");

        }
    }
}
