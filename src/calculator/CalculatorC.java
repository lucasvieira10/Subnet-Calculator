package calculator;

import java.io.Serializable;
import validate.Validate;

/**
 * Class that represents a Subnet Calculator for Network class C.
 * 
 * @author Lucas Lima Vieira
 * @version 1.0
 */
public class CalculatorC implements ICalculator, Serializable {

    private  String ipAddress;
    private String maskAddress;

    /**
     * Constructor of the class CalculatorC.
     * 
     * @param ipAddress - IP Address for calculation.
     * @param maskAddress - Mask Address for calculation.
     * @throws Exception if datas are invalid.
     */
    public CalculatorC(String ipAddress, String maskAddress) throws Exception {
        
        Validate.isIpValid(ipAddress);
        Validate.isMaskValid(maskAddress);
        
        this.ipAddress = ipAddress;
        this.maskAddress = maskAddress;
    }
    
    /**
     * This method is to calculation and get the host range.
     * 
     * @return the host range.
     */
    @Override
    public String hostRangeCalculate() {

        String firstIp = networkIp() + (subnetIDCalculate() + 1);
        String lastIp = networkIp() + (broadcastCalculate() - 1);

        return firstIp + " - " + lastIp;
    }

    /**
     * This method is for get the subnet IP. 
     * 
     * @return subnet ip.
     */
    @Override
    public String getSubnetID() {
        return networkIp() + subnetIDCalculate();
    }
    
    /**
     * This method is for the get the broadcast.
     * 
     * @return broadcast.
     */
    @Override
    public String getBroadcast() {
        return networkIp() + broadcastCalculate();
    }
    
    /**
     * This method return ip address.
     * 
     * @return ip address.
     */
    @Override
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * This method return mask address.
     * 
     * @return mask address.
     */
    @Override
    public String getMaskAddress() {
        return maskAddress;
    }
    
    /**
     * This method is for calculate the subnet IP.
     * 
     * @return subnet ip.
     */
    private int subnetIDCalculate() {
        
        int interval = intervalCalculate();
                
        if (getLastNumberIP() == getLastNumberMask()) {
            return getLastNumberIP();
            
        } else if (getLastNumberIP() < interval) {
            return 0;
            
        } else if (getLastNumberIP() == interval){
            return interval;
            
        } else if (getLastNumberIP() < getLastNumberMask()) {
            int value = 0;
            while (getLastNumberIP() >= value) {
                value += interval;
            }
            return value - interval;
            
        } else { // else if (getLastNumberIP() > getLastNumberMask())
            
            return interval;
            /*
            int value = interval;
            while (getLastNumberIP() < value ) {
                value *= 2;
            }
            return value;*/
        }
    }

    /**
     * This method is for calculate the broadcast address.
     * 
     * @return broadcast address.
     */
    private int broadcastCalculate() {
        int interval = intervalCalculate();
       
        int value = interval;
        while (getLastNumberIP() > value) {
            value *= 2;
        }
        
        if (getLastNumberMask() > getLastNumberIP() && getLastNumberMask() < value) {
            return getLastNumberMask() - 1;
        }
        
        return value - 1;
    }

    /**
     * This method is for get the part of ip that 
     * represents the network ip (X.X.X).
     * 
     * @return network ip.
     */
    private String networkIp() {

        String[] ipSplit = ipAddress.split("\\.");

        String ip = "";
        int countNumbers = 1;

        while (ipSplit.length > countNumbers) {
            ip += ipSplit[countNumbers - 1] + ".";
            countNumbers++;
        }

        return ip;
    }

    /**
     * This method is for calculate interval of hosts.
     * 
     * @return interval.
     */
    private int intervalCalculate() {

        String[] maskSplit = maskAddress.split("\\.");

        int interval = 256 - Integer.parseInt(maskSplit[3]);
        return interval;
    }
    
    /**
     * This method return the last number of ip address.
     * 
     * @return last number ip.
     */
    private int getLastNumberIP() {
        String[] ipSplit = ipAddress.split("\\.");
        
        return Integer.parseInt(ipSplit[3]);
    }
    
    /**
     * This method return the last number of mask address.
     * 
     * @return last number mask.
     */
    private int getLastNumberMask() {
        String[] maskSplit = maskAddress.split("\\.");
        
        return Integer.parseInt(maskSplit[3]);
    }
}
