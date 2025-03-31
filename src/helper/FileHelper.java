package helper;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileHelper {
    public static File createExcelFile(JInternalFrame parent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(parent);
        File f = fileChooser.getSelectedFile();
        if (f == null)
            return null;
        String filePath = f.toString();
        if (!filePath.endsWith(".xlsx")) {
            filePath += ".xlsx";
            f = new File(filePath);
        }
        return f;
    }
    
    public static void writeToExcelFile(File file, XSSFWorkbook workbook) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();
    }
    
    public static void openFile(File file) throws IOException {
        Desktop.getDesktop().open(file);
    }
}
