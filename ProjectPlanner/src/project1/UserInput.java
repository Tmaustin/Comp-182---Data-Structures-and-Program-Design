/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Student-HSLH133
 */
public class UserInput {


  public static int getInt()  {
    Scanner scan = new Scanner (System.in);
    int input = scan.nextInt();
    return input;

  }



public static int getInt(int min, int max) {
    
    System.out.println("Please input an Integer"+ 
            "(The minimum length is "+min+" and max length is "+max);
    int input = getInt();
    while(input < min || input > max){
         System.out.println("Please input a Integer"+ 
                " REMEMBER! between "+min+" and "+max);
         input = getInt();
    }
    return input;
}
public static int testInt(int test,int min, int max) {
    int input = test;
    while(input < min || input > max){
         System.out.println("Please input a Char"+ 
                " REMEMBER! between "+min+" and "+max);
         input = getInt();
    }
    return input;
}




  public static char getChar() {
      Scanner scan = new Scanner (System.in);
    char input = scan.next().charAt(0);

    return input;

  }
  
  
public static char getChar(char min, char max)  {      // min char 'A', max char 'Z'
    
    System.out.println("Please input a char (single character)"+ 
            "(The minimum length is "+min+" and max length is "+max);
    char input = getChar();
    while(input < min || input > max){
         System.out.println("Please input a Char"+ 
                " REMEMBER! between "+min+" and "+max);
         input = getChar();
    }
    return input;
}
public static char getChar(char test,char min, char max)  {      // min char 'A', max char 'Z'
    char input = test;
    while(input < min || input > max){
         System.out.println("Please input a Char"+ 
                " REMEMBER! between "+min+" and "+max);
         input = getChar();
    }
    return input;
}


public static double getDouble() {
    Scanner scan = new Scanner (System.in);
    double input = scan.nextDouble();
    return input;

  }
public static double getDouble(double min, double max) {
   
    System.out.println("Please input a Double"+ 
            "(The minimum length is "+min+" and max length is "+max);
    double input = getDouble();
    while(input < min || input > max){
         System.out.println("Please input a Double"+ 
                " REMEMBER! between "+min+" and "+max);
         input = getDouble();
    }
    return input;
        
}
public static double testDouble(double test,double min, double max) {

    double input = test;
    while(input < min || input > max){
         System.out.println("Please input a Double"+ 
                " REMEMBER! between "+min+" and "+max);
         input = getDouble();
    }
    return input;
        
}
  public static String getString()  {
      Scanner scan = new Scanner (System.in);
    String input = scan.nextLine();
    return input;
  
  }

public static String getString(int min, int max) {// min and max length
    
    System.out.println("Please input a string:" + 
            "(The minimum length is "+min+" and max length is "+max);
    String input = getString();
    while(input.length() < min || input.length() > max){
         System.out.println("Please input a String"+ 
                " REMEMBER! between "+min+" and "+max+" characters");
         input = getString();
    }
    return input;
}
public static String testString(String test,int min, int max) {// min and max length
    String input = test;
    while(input.length() < min || input.length() > max){
         System.out.println("Please input a String"+ 
                " REMEMBER! between "+min+" and "+max+" characters");
         input = getString();
    }
    return input;
}

}



