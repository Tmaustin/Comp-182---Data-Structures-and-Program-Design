/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.Scanner;

/**
 * Taylor Austin
 * Chris Ferguson
 * Project 1 : 3.4.14
 * This Project is a Appointment planner. This program adds and deletes appointments through a series of menus.
 * Planner will give you a menu and you can select add which then asks you to input a month, day, hour, minute, and message.
 * Then it will send it to a sort method that sorts the array based on month day and time. 
 */
public class Planner {
    Planner (){
    Appointment set1= new Appointment();
    Appointment set2= new Appointment();
    Appointment set3= new Appointment();
    Appointment set4= new Appointment();
    set1.setMonth("Mar");   set1.setDay(4);     set1.setHour(17);   set1.setMinute(30);     set1.setMessage("Quiz 1");
    set2.setMonth("Apr");   set2.setDay(1);     set2.setHour(17);   set2.setMinute(30);     set2.setMessage("Midterm");
    set3.setMonth("May");   set3.setDay(6);     set3.setHour(17);   set3.setMinute(30);     set3.setMessage("Quiz 2");
    set4.setMonth("Jun");   set4.setDay(3);     set4.setHour(17);   set4.setMinute(30);     set4.setMessage("Final");
    array1 [0]=set1; 
    array1 [1]=set2;
    array1 [2]=set3;
    array1 [3]=set4;
    }
    int tester=0;
    private Appointment [] array1 = new Appointment[20];
    private Appointment [] arraysingle = new Appointment[1];

    public static void main(String [ ] args){
        Planner plan=new Planner();
        plan.run();
    }
    public void run() {
        System.out.println("1) Add Appointment\n2) Delete Appointment\n3) List Appointment\n4) Exit\n");
        Scanner scan= new Scanner (System.in);
        int input=scan.nextInt();
        System.out.println("\n");
        if(input==1){
            addAppointment();
        }
        if(input==2){
            deleteAppointment();
        }
        if(input==3){
            listAppointment();
        }
        if(input==4){
            System.exit(0);
        }
        if(input==5){
            Appointment set5= new Appointment();
    set5.setMonth("Jan");   set5.setDay(14);     set5.setHour(12);   set5.setMinute(21);     set5.setMessage("Hello");
            insertAppointment(set5);
            run();
        }

        else{
            System.out.println("Value not recognized! EXIT");
            System.exit(0);
        }
        
    }
    public boolean compareAppointment (Appointment A1, Appointment A2) {
        int test1= getMonthNumber(A1.getMonth());
        int test2= getMonthNumber(A2.getMonth());
        if (test1<test2)
            return true;
        else if (test1>test2)
            return false;
        else if (test1==test2){
            if (A1.getDay()<A2.getDay())
            return true;
            else if (A1.getDay()>A2.getDay())
            return false;
            else
                if ((A1.getHour()<A2.getHour()))
                    return true;
                else if ((A1.getHour()>A2.getHour()))
                    return false;
                else{
                    if ((A1.getMinute()<A2.getMinute()))
                    return true;
                    else if ((A1.getMinute()>A2.getMinute()))
                    return false;
                    else
                        return false;
                }
        }
        else
            return false;
            
        }
    private int getMonthNumber(String test){
        String test1= test.toUpperCase();
        if (test1.equals("JAN"))
            return 1;
        else if (test1.equals("FEB"))
            return 2;
        else if (test1.equals("MAR"))
            return 3;
        else if (test1.equals("APR"))
            return 4;
        else if (test1.equals("MAY"))
            return 5;
        else if (test1.equals("JUN"))
            return 6;
        else if (test1.equals("JUL"))
            return 7;
        else if (test1.equals("AUG"))
            return 8;
        else if (test1.equals("SEP"))
            return 9;
        else if (test1.equals("OCT"))
            return 10;
        else if (test1.equals("NOV"))
            return 11;
        else
            return 12;
    }
    public void insertAppointment (Appointment A1){
         // places A1 in the proper (sorted) slot of the array
        int x=0;
        int counter=0;
        while (array1[counter]!=null)
            counter++;
        while (x < array1.length){
            if (array1[x]==null){
                array1[x]=A1;
                break;
            }
            else if(this.compareAppointment(A1, array1[x])==true){
                arraysingle[0]=array1[x];
                array1[x]=A1;
                break;
            }
            else
                x++;  
        }   
        if(arraysingle[0]!=null){
            while (counter > x){
                array1[counter]=array1[counter-1];
         counter--;
        }
            array1[x+1]=arraysingle[0];
            arraysingle[0]=null;
            
    }
    }

        
    
    public void listAppointment (){
       int x=0;
        while (array1 [x]!=null){
            System.out.println(array1[x].toString());
            x++;
        }
        run();
    }
    public void deleteAppointment () {
    int x=0;
    int y=1;
    Scanner scan=new Scanner(System.in);
        while (array1 [x]!=null){
            System.out.println(y+") " + array1[x].toString());
            x++;
            y++;
        }
        System.out.println("Enter in the Appointment number you want removed: ");
        int input=scan.nextInt();
        if (input >=1&&input <array1.length){
            array1[input-1]=null;
            while (array1[input]!=null){
                array1[input-1]=array1[input];
                input++;
            }
            array1[input-1]=null;
        }
        else{
        System.out.println("Exit! Wrong input");
        }
        
        run();
    }

    void addAppointment (){
        Appointment temp= new Appointment();
        temp.inputAppointment();
        this.insertAppointment(temp);
        run();
    }
}
