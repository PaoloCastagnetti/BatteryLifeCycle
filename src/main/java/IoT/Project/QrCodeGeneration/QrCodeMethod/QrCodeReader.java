package IoT.Project.QrCodeGeneration.QrCodeMethod;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * @author Francesco Lasalvia, 271719@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 03/03/2022 - 10:09
 */

public class QrCodeReader {
    //path è dove la funziona trova i QR_code da leggere
    private static String path="C:\\Users\\lasal\\Desktop\\Unimore\\Iot\\Iot-code\\Prog-esame\\BatteryLifeCycle\\src\\main\\java\\IoT\\Project\\QrCodeGeneration\\QrCodeImage\\";

    public static void main(String[] args) throws ChecksumException, NotFoundException, IOException, WriterException, FormatException {
        String codebattery="codice";
        File file=new File(String.format("%s/QR_Code_%s.png",path,codebattery));
        System.out.println("IL contenuto del QrCode e' il seguente: \n");
        System.out.println(readQrCode(file,codebattery));
        System.out.println("\n");
        System.out.println("Done");
    }

    private static String readQrCode(File qrcodefile,String codebattery) throws WriterException, IOException, NotFoundException, ChecksumException, FormatException {
        QRCodeReader qrCodeReader=new QRCodeReader();
        Result decode=qrCodeReader.decode(new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(qrcodefile)))));
        return decode.getText();
    }
}
