/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica0;

/**
 *
 * @author nicomda
 */
public class CuentaBancaria {
    private String client_name;
    private double saldo;
    public void CuentaBancaria(double saldo){
        this.saldo=saldo;
    }
    public void CuentaBancaria(){
        this.saldo=0;
    }
    
    public String getName(){
        return this.client_name;
    }
    
    public void setName(String name){
        this.client_name=name;
    }
    
    public void updateSaldo(double saldo){
        this.saldo=saldo;
    }
    
    public double getSaldo(){
        return this.saldo;
    }
}
