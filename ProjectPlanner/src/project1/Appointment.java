/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

/**
 *
 * @author Student-HSLH133
 */
public class Appointment {
    private String month;
    private int day;
    private int hour;
    private int minute;
    private String message;
    /*public static void main(String [ ] args){
    Appointment apptn= new Appointment();
    apptn.inputAppointment();
    System.out.println(apptn.toString());
    }
    */
    public Appointment(){
        month="Jan";
        day=1;
        hour=12;
        minute=00;
        message="Lunch with Chris";
    }
    public Appointment(String AssignMonth, int AssignDay,int AssignHour, int AssignMinute, String AssignMessage){
        month = UserInput.testString(AssignMonth,3,3);
        day = UserInput.testInt(AssignDay,1,31);
        hour = UserInput.testInt(AssignHour,0,24);
        minute = UserInput.testInt(AssignMinute,0,59);
        message = UserInput.testString(AssignMessage,0,30);
    }

    public void setMonth(String AssignMonth){
        month = UserInput.testString(AssignMonth,3,3);
    }
    public String getMonth(){
        return month;
    }
    public void setMessage(String AssignMessage){
        message = UserInput.testString(AssignMessage,0,30);
        
    }
    public String getMessage(){
        return message;
    }
    public void setDay(int AssignDay){
        day = UserInput.testInt(AssignDay,1,31);
        
    }
    public int getDay(){
        return day;
    }
    public void setHour(int AssignHour){
        hour = UserInput.testInt(AssignHour,0,24);
        
    }
    public int getHour(){
        return hour;
    }
    public void setMinute(int AssignMinute){
        minute = UserInput.testInt(AssignMinute,0,59);
        
    }
    public int getMinute(){
        return minute;
    }
    public  String toString()
    {
        String minute1="00";
        String hour1="00";
        if (minute >0 && minute<10)
            minute1 = "0"+minute;
        else if(minute == 0 ||minute == 00)
            minute1 ="00";
        else
            minute1=Integer.toString(minute);
        if (hour >0 && hour<10)
            hour1 = "0"+hour;
        else if(hour == 0)
            hour1 ="00";
        else
            hour1=Integer.toString(hour);
            
    return month+" "+ day + ", "+hour1+":"+minute1+" "+message;
    }
    public void inputAppointment(){
        UserInput user = new UserInput();
        System.out.println("Hello welcome to your Appointment setter!");
        System.out.println("Input the Month (3 Characters only - EX: Jan)");
        month = user.getString(3,3);
        System.out.println("Input the Day");
        day = user.getInt(1,31);
        System.out.println("Input the Hour");
        hour = user.getInt(0,24);
        System.out.println("Input the Minute");
        minute = user.getInt(0,59);
        System.out.println("Input your Reminder (0-30 Characters only)");
        message = user.getString(0,30);
        
    }
}


