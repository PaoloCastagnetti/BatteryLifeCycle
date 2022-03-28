package IoT.Project.Modules.C_Processing.Server;

import IoT.Project.Modules.C_Processing.Client.CoapGetTransformResource;
import IoT.Project.Modules.C_Processing.Client.CoapGetTransportResource;
import IoT.Project.Modules.C_Processing.Resource.AssemblingResource;
import IoT.Project.Modules.C_Processing.Sensors.AssemblingSensor;
import IoT.Project.Modules.C_Processing.Sensors.TransformingSensor;
import org.eclipse.californium.core.CoapServer;


public class AssemblingCoapProcess extends CoapServer {

    public AssemblingCoapProcess(){
        super();
      this.add(new AssemblingResource("Assemble"));
    }

    public static void main(String[] args) {

        AssemblingCoapProcess server =new AssemblingCoapProcess();
        server.start();
        System.out.println("Coap serer started!, Available resouces: ");

        server.getRoot().getChildren().stream().forEach(resource -> {
            System.out.println(String.format("Resource %s, Uri %s , Observable: %b",resource.getName(), resource.getURI(), resource.isObservable()));
            if(!resource.getURI().equals("/.well-known")){
                resource.getChildren().stream().forEach(childResource -> {
                    System.out.println(String.format("Resource %s, Uri %s , Observable: %b", childResource.getName(), childResource.getURI(), childResource.isObservable()));
                });
            }
        });


        //devo fare la get sulla tranform, mi serve inizio,fine, luogo ,id
        TransformingSensor transformingSensor=new TransformingSensor();
        CoapGetTransformResource.getTransformComponent(transformingSensor);

        //abbiamo bisogno di tutte le altre info
        AssemblingSensor assemblingSensor=new AssemblingSensor();
        assemblingSensor.setCode(transformingSensor.getCode());
        assemblingSensor.setDeviceId(transformingSensor.getDeviceId());
        assemblingSensor.setI_timestamp_transforming(transformingSensor.getI_Timestamp());
        assemblingSensor.setF_Timestamp_assembling(transformingSensor.getF_Timestamp());

        //fase di assemblaggio setta solo i valori di inizio e fine timestamp
       //simulo assemblaggio
        assemblingSensor.update_assemble();

        //ora devo fare ina post al data collector dell'assembling sensor



    }




}

