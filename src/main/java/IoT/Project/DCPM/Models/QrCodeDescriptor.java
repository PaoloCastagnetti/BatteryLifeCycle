package IoT.Project.DCPM.Models;

import java.io.File;

/**
 * @author Francesco Lasalvia, 271719@studenti.unimore.it
 * @project IoT-BatteryLifeCycle
 * @created 31/03/2022 - 18:54
 */

public class QrCodeDescriptor {
    private File qrCode;
    private String ID;
    private long timestamp;

    public QrCodeDescriptor() {
    }

    public File getQrCode() {
        return qrCode;
    }
    public void setQrCode(File qrCode) {
        this.qrCode = qrCode;
    }

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
