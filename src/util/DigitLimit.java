package util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * This class serves for delimit the characters number in a JTextField.
 * 
 * @author Lucas Lima Vieira
 */
public class DigitLimit extends PlainDocument {
    
    private int maxNumber;

    /**
     * Contructor of the class DigitLimit. 
     * 
     * @param maxNumber 
     */
    public DigitLimit(int maxNumber) {
        super();
        this.maxNumber = maxNumber;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str == null || getLength() == maxNumber) {
            return;
        }
        
        int total = getLength() + str.length();
        
        if (total <= maxNumber) {
            super.insertString(offs, str, a);
            return;
        }
        
        String newString = str.substring(0, getLength() - maxNumber);
        super.insertString(offs, newString, a);
    }
}
