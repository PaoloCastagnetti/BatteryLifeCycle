package IoT.Project.Modules.D_QrCodeGeneration.QrCodeMethod.LoadingBar;

import javax.swing.*;

public class ProgressBar extends JFrame {

    public  JProgressBar jb;

    public ProgressBar(int min, int max){
        this.setResizable(false);
        jb=new JProgressBar(min,max);
        jb.setBounds(0,0,200,50);  //Posizionamento e grandezza della barra
        setSize(300,120);  		//Grandezza finestra.
        jb.setValue(0);  //Imposta il valore di partenza
        jb.setStringPainted(true);

        add(jb);
        setLayout(null);
    }

    public static void updateProgressBar(ProgressBar progressBar, int value){
        progressBar.paint(progressBar.getGraphics());
        progressBar.jb.setValue(value);
    }
}
