import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;


public class FileChoosing{
    File file;
    String fileName, path;
    File[] files;
    FileChoosing(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Audio Files*","wav");
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(null);

        if(returnValue == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();

            if(selectedFile.isDirectory()){
                files = selectedFile.listFiles(((dir, name) -> name.toLowerCase().endsWith(".wav")));
                if (files != null && files.length > 0){
                    file = files[0];
                    path = file.getAbsolutePath();
                    fileName = file.getName();
                } else {
                    System.out.println("Klasörde geçerli dosya yok!");
                }
            }else {
                file = selectedFile;
                path = selectedFile.getAbsolutePath();
                fileName = selectedFile.getName();
            }
        }else {
            System.out.println("Dosya veya klasör seçilmedi!");
        }
    }

}