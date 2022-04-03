package IoT.Project.Modules.D_QrCodeGeneration.QrCodeMethod;

import IoT.Project.DCPM.Models.ExtractionDescriptor;
import IoT.Project.DCPM.Models.QrCodeDescriptor;
import IoT.Project.Modules.D_QrCodeGeneration.CoAP_Communications.CoapGetExtraction;
import IoT.Project.Modules.D_QrCodeGeneration.CoAP_Communications.CoapGetProcessing;
import IoT.Project.Modules.D_QrCodeGeneration.CoAP_Communications.CoapGetTransport;
import IoT.Project.Modules.D_QrCodeGeneration.CoAP_Communications.CoapPutQrCode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.File;
import java.io.IOException;

/**
 * @author Francesco Lasalvia, 271719@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 03/03/2022 - 10:09
 */

public class QrCodeGeneration {
    //dove vuoi mettere tutti i qrcode
    //private static String path="C:\\Users\\Marco\\Desktop\\Roba\\Università\\Terzo Anno\\IoT\\BatteryLifeCycle\\src\\main\\java\\IoT\\Project\\Modules\\D_QrCodeGeneration\\QrCodeImage";
    private static String path="C:\\Users\\lasal\\Desktop\\Unimore\\Iot\\Iot-code\\Prog-esame\\BatteryLifeCycle\\src\\main\\java\\IoT\\Project\\Modules\\D_QrCodeGeneration\\QrCodeImage";
    private static QrCodeDescriptor qrCodeDescriptor=new QrCodeDescriptor();

    public static void main(String[] args) {
        Gson gson = new Gson();
        //text sono le info che vuoi mettere nel QRcode
        String A_Extraction= CoapGetExtraction.getExtractionGson();
        ExtractionDescriptor extractionDescriptor = gson.fromJson(A_Extraction, ExtractionDescriptor.class);

        String B_Transport= CoapGetTransport.getTransportGson();
        String C_Processing= CoapGetProcessing.getProcessingGson();
        String final_payload=String.format("Extraction:\n%s\nTransport\n%s\nProcessing:\n%s\n",A_Extraction,B_Transport,C_Processing);
        try{

            //generate QrCode
            System.out.println("Generazione QR_Code");
            QRCodeWriter writer=new QRCodeWriter();
            BitMatrix bitMatrix=writer.encode(final_payload, BarcodeFormat.QR_CODE,150,150);
            //file generation:  usa path/nome del qr code associato al codice univoco che prenderò da batteria!
            File qrCode = new File(String.format("%s/QR_Code_%s.png",path,extractionDescriptor.getLoad_code()));
            qrCodeDescriptor.setID(extractionDescriptor.getLoad_code());
            qrCodeDescriptor.setTimestamp(System.currentTimeMillis());
            MatrixToImageWriter.writeToPath(bitMatrix,"PNG",qrCode.toPath());
            qrCodeDescriptor.setQrCode(qrCode);
            System.out.println("QR_Code Generato!!");
            System.out.println("QrCode inserito sul DCPM");
            //CoapPutQrCode.putQrCode(qrCodeDescriptor);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
