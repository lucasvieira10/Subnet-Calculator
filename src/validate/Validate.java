package validate;

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
     * @throws Exception if ip is not valid.
     */
    public static void isIpValid(String ipAddress) throws Exception {
        if (ipAddress == null || ipAddress.trim().isEmpty()) {
            throw new Exception("Invalid IP Address!");
        }
    }
    
    /**
     * This method see if mask address is valid.
     * 
     * @param maskAddress - mask address.
     * @throws Exception if mask is not valid.
     */
    public static void isMaskValid(String maskAddress) throws Exception {
        if (maskAddress == null || maskAddress.trim().isEmpty()) {
            throw new Exception("Invalid Maks Address!");
        }
    }
}
