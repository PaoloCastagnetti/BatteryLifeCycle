package IoT.Project.Modules.D_QrCodeGeneration.QrCodeMethod;

import IoT.Project.Modules.D_QrCodeGeneration.GetDCPM.CoapGetExtraction;
import IoT.Project.Modules.D_QrCodeGeneration.GetDCPM.CoapGetProcessing;
import IoT.Project.Modules.D_QrCodeGeneration.GetDCPM.CoapGetTransport;
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
    private static String path="C:\\Users\\Marco\\Desktop\\Roba\\Università\\Terzo Anno\\IoT\\BatteryLifeCycle\\src\\main\\java\\IoT\\Project\\Modules\\D_QrCodeGeneration\\QrCodeImage";

    public static void main(String[] args) {
        //text sono le info che vuoi mettere nel QRcode
        String A_Extraction= CoapGetExtraction.getExtractionGson();
        String B_Transport= CoapGetTransport.getTransportGson();
        String C_Processing= CoapGetProcessing.getProcessingGson();
        String final_payload=String.format("Extraction:\n%s\nTransport\n%s\nProcessing:\n%s\n",A_Extraction,B_Transport,C_Processing);
        try{
            //generate QrCode
            System.out.println("Generazione QR_Code");
            generateQrCode(final_payload,"codice");
            System.out.println("QR_Code Generato!!");
    } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void generateQrCode(String text,String codebattery) throws WriterException, IOException{
        QRCodeWriter writer=new QRCodeWriter();
        BitMatrix bitMatrix=writer.encode(text, BarcodeFormat.QR_CODE,300,300);
        //file generation:  usa path/nome del qr code associato al codice univoco che prenderò da batteria!
        File file=new File(String.format("%s/QR_Code_%s.png",path,codebattery));
        MatrixToImageWriter.writeToPath(bitMatrix,"PNG",file.toPath());
    }

}
