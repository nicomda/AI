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
public class CuentaAhorros extends CuentaBancaria {
    private float interest;
    CuentaAhorros(double Saldo, float interest){
        this.updateSaldo(Saldo);
        this.interest=interest;
    }
    CuentaAhorros(){
        this.interest=0;
    };

    /**
     * @return the interest
     */
    public float getInterest() {
        return interest;
    }

    /**
     * @param interest the interest to set
     */
    public void setInterest(float interest) {
        this.interest = interest;
    }
    
}
