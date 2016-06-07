package calculator;

import exceptions.DataInvalidException;
import java.io.Serializable;

/**
 * Class that represents a Subnet Calculator for Network class A.
 * 
 * @author Lucas Lima Vieira
 * @version 1.0
 */
public class CalculatorA extends GeneralCalculator implements ICalculator, Serializable {

    /**
     * Constructor of the class CalculatorA.
     * 
     * @param ipAddress - IP Address for calculation.
     * @param maskAddress - Mask Address for calculation.
     * @throws DataInvalidException if datas are invalid.
     */
    public CalculatorA(String ipAddress, String maskAddress) throws DataInvalidException {
        super(ipAddress, maskAddress);
    }
    
    /**
     * This method is to calculation and get the host range.
     * 
     * @return the host range.
     */
    @Override
    public String hostRangeCalculate() {
       
        String firstIp = networkIp() + "." + super.subnetIDCalculate() + ".0.1";
        String lastIp = networkIp() + "." + super.broadcastCalculate() + ".255.254";

        return firstIp + " - " + lastIp;
    }

    /**
     * This method is for get the subnet IP. 
     * 
     * @return subnet ip.
     */
    @Override
    public String getSubnetID() {       
        return networkIp() + "." + super.subnetIDCalculate() + ".0.0" ;
    }
    
    /**
     * This method is for the get the broadcast.
     * 
     * @return broadcast.
     */
    @Override
    public String getBroadcast() {   
        return networkIp() + "." + super.broadcastCalculate()+ ".255.255";
    }
        
    /**
     * This method is for get the part of ip that 
     * represents the network ip (X).
     * 
     * @return network ip.
     */
    @Override
    protected String networkIp() {
        String[] ipSplit = getIpAddress().split("\\.");

        return ipSplit[0];
    }

    /**
     * This method is for calculate interval of hosts.
     * 
     * @return interval.
     */
    @Override
    protected int intervalCalculate() {

        String[] maskSplit = getMaskAddress().split("\\.");

        int interval = 256 - Integer.parseInt(maskSplit[1]);
        return interval;
    }
    
    /**
     * This method return the respective octet number of ip address.
     * 
     * @return octet number ip.
     */
    @Override
    protected int getOctetNumberIP() {
        String[] ipSplit = getIpAddress().split("\\.");
        
        return Integer.parseInt(ipSplit[1]);
    }
    
    /**
     * This method return the respective octet number of mask address.
     * 
     * @return octet number mask.
     */
    @Override
    protected int getOctetNumberMask() {
        String[] maskSplit = getMaskAddress().split("\\.");
        
        return Integer.parseInt(maskSplit[1]);
    }
}
