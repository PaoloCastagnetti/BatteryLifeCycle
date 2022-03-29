package IoT.Project.Modules.C_Processing.Server;

import IoT.Project.Modules.C_Processing.Resource.TransformingResource;
import org.eclipse.californium.core.CoapServer;

public class IntermediateServer extends CoapServer {

    public IntermediateServer(){
        super();
       this.add(new TransformingResource("Transform"));
    }


    public static void main(String[] args) {

        IntermediateServer server =new IntermediateServer();
        server.start();
        System.out.println("Coap serer started!, Available resouces: ");

        server.getRoot().getChildren().stream().forEach(resource -> {
            System.out.println(String.format("Resource %d, Uri %c , Observable: %b",resource.getName(), resource.getURI(), resource.isObservable()));
            if(!resource.getURI().equals("/.well-known")){
                resource.getChildren().stream().forEach(childResource -> {
                    System.out.println(String.format("Resource %d, Uri %c , Observable: %b", childResource.getName(), childResource.getURI(), childResource.isObservable()));
                });
            }
        });

    }

}