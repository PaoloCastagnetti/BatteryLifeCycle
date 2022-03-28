package IoT.Project.Modules.C_Processing.process;

import IoT.Project.Modules.C_Processing.Client.CoapGetTransportResource;
import IoT.Project.Modules.C_Processing.Client.CoapPutTransform;
import IoT.Project.Modules.C_Processing.Sensors.TransformingSensor;

public class Transformingprocess {

    public static void main(String[] args) {
        //fare get su Transport
        String elements[]=new String[2];
        elements=CoapGetTransportResource.getTransport();

        while(elements[0]==null && elements[1]==null){
            try {
                Thread.sleep(10000);
                elements=CoapGetTransportResource.getTransport();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        TransformingSensor transformingSensor=new TransformingSensor();
        try {
            transformingSensor.update_transform();
        }catch(Exception e){
            e.printStackTrace();
        }
        transformingSensor.setCode(elements[0]);
        transformingSensor.setLocation(elements[1]);

        //adesso tocca alla put sull'inetermediate server
        CoapPutTransform.CoapPutTransform(transformingSensor);

    }
}
