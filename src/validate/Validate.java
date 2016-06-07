package validate;

import exceptions.DataInvalidException;
import exceptions.NetworkClassInvalidException;

/**
 * This class serves for the verification and validation of the 
 * data in my program.
 * 
 * @author Lucas Lima Vieira
 */
public class Validate {
    
    /**
     * This method see if ip address is valid.
     * 
     * @param ipAddress - ip address.
     * @throws DataInvalidException if ip is not valid.
     */
    public static void isIpValid(String ipAddress) throws DataInvalidException {
        if (ipAddress == null || ipAddress.trim().isEmpty()) {
            throw new DataInvalidException("Invalid IP Address!");
        }
        
        String[] ipSplit = ipAddress.split("\\.");
        
        for (String octet : ipSplit) {
            if (Integer.parseInt(octet) > 255) {
                throw new DataInvalidException("Invalid IP Address!");
            }
        }
    }
    
    /**
     * This method see if mask address is valid.
     * 
     * @param maskAddress - mask address.
     * @throws DataInvalidException if mask is not valid.
     */
    public static void isMaskValid(String maskAddress) throws DataInvalidException {
        if (maskAddress == null || maskAddress.trim().isEmpty()) {
            throw new DataInvalidException("Invalid Maks Address!");
        }
        
        String[] maskSplit = maskAddress.split("\\.");
        
        for (String octet : maskSplit) {
            if (Integer.parseInt(octet) > 255) {
                throw new DataInvalidException("Invalid Mask Address!");
            }
        }
    }
    
    public static void isAClassValid(String maskAddress) throws NetworkClassInvalidException {
        String[] maskSplit = maskAddress.split("\\.");
        
        // maskSplit[1] is variable.
        if (!("255".equals(maskSplit[0])) || !(Integer.parseInt(maskSplit[1]) < 255) ||
                !("0".equals(maskSplit[2])) || !("0".equals(maskSplit[3]))) {
            
            throw new NetworkClassInvalidException("Invalid A network class!");
        }
    }
    
   public static void isBClassValid(String maskAddress) throws NetworkClassInvalidException {
        String[] maskSplit = maskAddress.split("\\.");
        
        // maskSplit[2] is variable.
        if (!("255".equals(maskSplit[0])) || !("255".equals(maskSplit[1])) || 
                !(Integer.parseInt(maskSplit[2]) < 255) ||!("0".equals(maskSplit[3]))) {
            
            throw new NetworkClassInvalidException("Invalid B network class!");
        }
    }
   
   public static void isCClassValid(String maskAddress) throws NetworkClassInvalidException {
        String[] maskSplit = maskAddress.split("\\.");
        
        // maskSplit[3] is variable.
        if (!("255".equals(maskSplit[0])) || !("255".equals(maskSplit[1])) || 
                !("255".equals(maskSplit[2]))) {
            
            throw new NetworkClassInvalidException("Invalid C network class!");
        }
    }
}
