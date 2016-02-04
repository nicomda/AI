/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author nicomda
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
                ej1();
                break;
            case 2:
                ej2();
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
    public static void ej1() {
        System.out.println("Introduzca la operación que desea realizar con 2 operandos(+,-,*,/)");
        String operation=null;
        String op_regex="([0-9]*)([\\*\\+-\\/])([0-9]*)";
        float a,b,result;
        Boolean valid_op = false;
        do {
            try {
                operation = buffer.readLine();
                System.out.println(operation);
                if (operation.matches(op_regex)) {
                    valid_op = true;
                }
            } catch (IOException ex) {
                System.out.println("Introduzca un operando válido(+,-,*,/) entre los 2 números");
            }
        } while (!valid_op);
        Pattern p= Pattern.compile(op_regex);
        Matcher m= p.matcher(operation);
        if(m.find()){
        a=Float.parseFloat(m.group(1));
        b=Float.parseFloat(m.group(3));
        switch(m.group(2)){
            case"*":
                result=a*b;
                break;
            case"+":
                result=a+b;
                break;
            case"-":
                result=a-b;
                break;
            default:
                result=a/b;
                break;
        }
                
        System.out.println("El resultado es: "+ result);
        }
        else{System.out.println("Error de entrada");}
        
        

    }
    public static void ej2() {
        System.out.println("Introduce una frase para contar sus palabras.");
        String sentence="";
        int counter=0;
        String regex_space="(\\s)";
        try {
            sentence=buffer.readLine();
        } catch (IOException ex) {
            System.out.println("Error en la entrada de datos");
        }
        Pattern p=Pattern.compile(regex_space);
        Matcher m= p.matcher(sentence);
        while(m.find()){
        counter++;
        }
        System.out.println("La frase tiene " + (counter+1) + " palabras");
    }
}
