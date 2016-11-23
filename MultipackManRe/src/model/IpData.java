/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Jhon Edison
 */
class IpData {
    private int numberOfConettion ;
    private String ip;

    public IpData( String ip) {
        this.numberOfConettion = 1;
        this.ip = ip;
    }
   
    public boolean existsHere(String ipp){
        if(this.ip.equals(ipp)) {
            return true;
        }
        return false;
    }
    
    public void compareHere(){
        numberOfConettion++;
    }

    public int getNumberOfConettion() {
        return numberOfConettion;
    }

    public void setNumberOfConettion(int numberOfConettion) {
        this.numberOfConettion = numberOfConettion;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
}
