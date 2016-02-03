/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * @author admin
 */
public class Practica0 {

    /**
     * @param args the command line arguments
     */
    private static BufferedReader buffer;
    
    public static void main(String[] args) {
        int optselection=5;
        do{
        printMenu();
        try{
            buffer=new BufferedReader(new InputStreamReader(System.in));   
            optselection=Integer.parseInt(buffer.readLine());
        }catch(Exception a){

            System.out.println("Error de entrada. ");
        }
        switch(optselection){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:

                break;
        }
        
        }while(optselection!=0);
        
    }
     public static void printMenu(){
        System.out.println("\t\t***** Práctica 0 *****");
        System.out.println("\t 1. Ejercicio de números");
        System.out.println("\t 2. Ejercicio de cadenas");
        System.out.println("\t 3. Ejercicio de ficheros y arrays");
        System.out.println("\t 4. Ejercicio herencia y sobrecarga");
        System.out.println("\t 0. Salir");
     }
}
