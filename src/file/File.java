package file;

import calculator.Calculator;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class serves for read and write the object in hard disk.
 * 
 * @author Lucas Lima Vieira
 */
public class File {
    
    /**
     * This method write the object.
     * 
     * @param calculator - calculator.
     */
    public static void writeFile(Calculator calculator) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.dat"))) {
            oos.writeObject(calculator);
            
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method read the object.
     * 
     * @return - calculator.
     */
    public static Calculator readFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.dat"));
            
            Calculator calculator = (Calculator) (ois.readObject());
            return calculator;
            
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
