package calculator;

import java.io.Serializable;
import validate.Validate;

/**
 * Class that represents a Subnet Calculator.
 * 
 * @author Lucas Lima Vieira
 * @version 1.0
 */
public class Calculator implements Serializable {

    private final int LEN_BINARY = 8;
    private  String ipAddress;
    private String maskAddress;

    /**
     * Constructor of the class Claculator.
     * 
     * @param ipAddress - IP Address for calculation.
     * @param maskAddress - Mask Address for calculation.
     * @throws Exception if datas are invalid.
     */
    public Calculator(String ipAddress, String maskAddress) throws Exception {
        
        Validate.isIpValid(ipAddress);
        Validate.isMaskValid(maskAddress);
        
        this.ipAddress = ipAddress;
        this.maskAddress = maskAddress;
    }
    
    /**
     * This method is to calculation the host range.
     * 
     * @return the host range.
     */
    public String hostRangeCalculate() {

        String firstIp = networkIp() + (254 - numberOfHosts() + 1);
        String lastIp = networkIp() + (254);

        return firstIp + " - " + lastIp;
    }

    /**
     * This method is for get the subet IP.
     * 
     * @return subnet ip.
     */
    public String getSubnetID() {
        return networkIp() + (254 - numberOfHosts());
    }

    /**
     * This method is for get the broadcast address.
     * 
     * @return broadcast address.
     */
    public String getBroadcast() {
        return networkIp() + (255);
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
     * This method is for calculate the hosts numbers possibles.
     * 
     * @return hosts numbers.
     */
    private int numberOfHosts() {

        String[] maskSplit = maskAddress.split("\\.");

        // here is calculated the number in binary of amount hosts.
        String binaryHost = intToBinary(maskSplit[3]);

        int hosts = 0;
        for (char number : binaryHost.toCharArray()) {
            if (number == '0') {
                hosts++;
            }
        }

        int totalOfHosts = (int) (Math.pow(2, hosts) - 2);

        return totalOfHosts;
    }

    /**
     * This method have function of convert the int to binary number.
     * 
     * @param value - int number.
     * @return binary number.
     */
    private String intToBinary(String value) {

        int intValue = Integer.parseInt(value);
        String binaryValue = Integer.toBinaryString(intValue);

        while (binaryValue.length() < LEN_BINARY) {
            binaryValue = "0" + binaryValue;
        }

        return binaryValue;
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
}
