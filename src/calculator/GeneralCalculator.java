package calculator;

import exceptions.DataInvalidException;
import java.io.Serializable;
import validate.Validate;

/**
 * This class represents the abstract of a Calculator.
 * 
 * @author Lucas Lima Vieira
 */
public abstract class GeneralCalculator implements Serializable {
    
    private  String ipAddress;
    private String maskAddress;

    /**
     * Constructor of the class GeneralCalculator.
     * 
     * @param ipAddress - IP Address for calculation.
     * @param maskAddress - Mask Address for calculation.
     * @throws DataInvalidException if datas are invalid.
     */
    public GeneralCalculator(String ipAddress, String maskAddress) throws DataInvalidException {
        
        Validate.isIpValid(ipAddress);
        Validate.isMaskValid(maskAddress);
        
        this.ipAddress = ipAddress;
        this.maskAddress = maskAddress;
    }
    
    /**
     * This method return ip address.
     * 
     * @return ip address.
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * This method return mask address.
     * 
     * @return mask address.
     */
    public String getMaskAddress() {
        return maskAddress;
    }
    
    /**
     * This method is for calcualte the subnet IP.
     * 
     * @return subnet ip.
     */
    protected int subnetIDCalculate() {
        
        int interval = intervalCalculate();
                
        if (getOctetNumberIP() == getOctetNumberMask()) {
            return getOctetNumberIP();
            
        } else if (getOctetNumberIP() < interval) {
            return 0;
            
        } else if (getOctetNumberIP() == interval){
            return interval;
                        
        } else { /* else if (getOctetNumberIP() < getOctetNumberMask() || 
                             getOctetNumberIP() > getOctetNumberMask())*/
  
            int value = 0;
            while (getOctetNumberIP() >= value) {
                value += interval;
            }
            return value - interval;
        }
    }

    /**
     * This method is for calculate the broadcast address.
     * 
     * @return broadcast address.
     */
    protected int broadcastCalculate() {
        int interval = intervalCalculate();
       
        int value = interval;
        while (getOctetNumberIP() > value) {
            value *= 2;
        }
        
        if (getOctetNumberMask() > getOctetNumberIP() && getOctetNumberMask() < value) {
            return getOctetNumberMask() - 1;
        }
        
        return value - 1;
    }
    
    /**
     * This abstract method is for get the part 
     * of ip that represents the network ip.
     * 
     * @return network ip.
     */
    protected abstract String networkIp();
    
    /**
     * This abstract method is for calculate 
     * interval of hosts.
     * 
     * @return interval.
     */
    protected abstract int intervalCalculate();
    
    /**
     * This method return the respective octet number of ip address.
     * 
     * @return octet number ip.
     */
    protected abstract int getOctetNumberIP();
    
    /**
     * This method return the respective octet number of mask address.
     * 
     * @return octet number mask.
     */
    protected abstract int getOctetNumberMask();
    
}
