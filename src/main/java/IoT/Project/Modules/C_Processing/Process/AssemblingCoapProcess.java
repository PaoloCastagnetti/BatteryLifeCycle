package IoT.Project.Modules.C_Processing.Process;

import IoT.Project.Modules.C_Processing.Client.CoapGetTransformResource;
import IoT.Project.Modules.C_Processing.Client.CoapPutAssembleStage;
import IoT.Project.Modules.C_Processing.Sensors.AssemblingSensor;
import IoT.Project.Modules.C_Processing.Sensors.TransformingSensor;

/**
 * @author Francesco Lasalvia, 271719@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 03/03/2022 - 10:09
 */

public class AssemblingCoapProcess {

//    public AssemblingCoapProcess(){
//        super();
//      this.add(new AssemblingResource("Assemble"));
//    }

    public static void main(String[] args) {

//        AssemblingCoapProcess server =new AssemblingCoapProcess();
//        server.start();
//        System.out.println("Coap serer started!, Available resouces: ");
//
//        server.getRoot().getChildren().stream().forEach(resource -> {
//            System.out.println(String.format("Resource %s, Uri %s , Observable: %b",resource.getName(), resource.getURI(), resource.isObservable()));
//            if(!resource.getURI().equals("/.well-known")){
//                resource.getChildren().stream().forEach(childResource -> {
//                    System.out.println(String.format("Resource %s, Uri %s , Observable: %b", childResource.getName(), childResource.getURI(), childResource.isObservable()));
//                });
//            }
//        });


        //devo fare la get sulla tranform, mi serve inizio,fine, luogo ,id
        AssemblingSensor assemblingSensor=new AssemblingSensor();
        TransformingSensor transformingSensor = CoapGetTransformResource.getTransformComponent();


        //abbiamo bisogno di tutte le altre info
        System.out.println("Passing the precious information to: Assembling sensor");
        assemblingSensor.setCode(transformingSensor.getCode());
        assemblingSensor.setI_timestamp_transforming(transformingSensor.getI_Timestamp());
        assemblingSensor.setF_timestap_transforming(transformingSensor.getF_Timestamp());
        assemblingSensor.setLocation(transformingSensor.getLocation());
        System.out.println("Assembling sensor has all the information!");

        //fase di assemblaggio setta solo i valori di inizio e fine timestamp
        //simulo assemblaggio
        try {
            assemblingSensor.update_assemble();
        } catch (InterruptedException e) {
            System.out.println("Assembling update went wrong...");
            e.printStackTrace();
        }
        //il messaggio di fine sta nell'update

        //ora devo fare una put al data collector dell'assembling sensor
        System.out.println("Sending all data to DCPM...");
        CoapPutAssembleStage.CoapPutAssemble(assemblingSensor);
    }




}

