package project3;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class Project3 extends JFrame implements ActionListener {
String oldRoom;
private static int xpos=0,ypos=0;// place window at this position
private static int xsize=700,ysize=500;// set window to this size

// Private state variables.
int counter=0;


private JPanel northPanel,centerPanel;
private JButton pushButton,popButton,dumpButton,exitButton;
private JTextField colorField;
private JTextField codeField;
private JTextArea outputArea;


////////////MAIN////////////////////////

public static void main(String[] args) {
        Project3 tpo = new Project3();
}

////////////CONSTRUCTOR/////////////////////
       StackCode stackcode= new StackCode(15);
       StackColor stackcolor= new StackColor(15);
       int alive=0;
       Boolean keyboard=false;
public Project3 ()
{

       addScreenComponents();   // put the stuff on the screen

       // Exit when the window is closed. i.e. when top right X box pressed
       addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

       setSize(xsize,ysize);
       setLocation(xpos,ypos);
       setVisible(true);

}

public void addScreenComponents()  {
       northPanel = new JPanel();
       northPanel.add(new JLabel("Enter A Color: "));
       colorField = new JTextField("",15);
       northPanel.add(colorField);
       northPanel.add(new JLabel("And A Code: "));
       codeField = new JTextField("",5);
       northPanel.add(codeField);

       pushButton = new JButton("Push");
       northPanel.add(pushButton);
       pushButton.addActionListener(this);
       popButton = new JButton("Pop");
       northPanel.add(popButton);
       popButton.addActionListener(this);
       dumpButton = new JButton("Dump");
       northPanel.add(dumpButton);
       dumpButton.addActionListener(this);
       exitButton = new JButton("Exit");
       northPanel.add(exitButton);
       exitButton.addActionListener(this);

       getContentPane().add("North",northPanel);

       centerPanel = new JPanel();
       outputArea = new JTextArea("Who Dares Enter.... The Temple of Gloom!",20,60);
       centerPanel.add(outputArea);
       getContentPane().add(centerPanel,"Center");

}
////////////BUTTON CLICKS ///////////////////////////

public void actionPerformed(ActionEvent e) {

         if (e.getSource()== exitButton) {
            dispose(); System.exit(0);
         }

         if (e.getSource()== popButton) {
                String newcolor = colorField.getText();
                outputArea.setText("Pop returning to " + newcolor);
                // add code to pop color off the stack, check that the color/code matches and change to that color room
         }

         if (e.getSource()== pushButton) {
                Room roomGet=new Room();
                String newcolor = colorField.getText();
                String newcode = codeField.getText();
                outputArea.setText("Push entering " + newcolor);
                colorField.setText("");
                codeField.setText("");
                if (counter==0&& newcolor.equals("green")){
                    stackcolor.push(newcolor);
                    stackcode.push(newcode);
                }
                else if (keyboard==true){
                    //insert
                }
                else if(counter>0 && roomGet.TRUEROOM(oldRoom,newcolor)==true){
                    stackcolor.push(newcolor);
                    stackcode.push(newcode);
                }
                else if (roomGet.TRUEROOM(oldRoom,newcolor)==false){
                    alive=1;
                }
                else if(counter==0||alive==1){
                        
                        outputArea.setText("DDDDD        EEEEEEEE     AAAAAAAA     DDDDD   \n"+
                                           "DD    DD       EE                     AAA     AAA     DD    DD  \n"+
                                           "DD     DD      EE                     AA          AA     DD     DD \n"+
                                           "DD      DD     EEEEEEEE     AA   AA  AA     DD      DD\n"+
                                           "DD      DD     EEEEEEEE     AAAA AAAA     DD      DD\n"+
                                           "DD     DD      EE                     AAA     AAA     DD     DD \n"+
                                           "DD    DD       EE                     AA          AA     DD    DD  \n"+
                                           "DDDDD        EEEEEEEE     AA          AA     DDDDD   \n"
                                           );
                        
                        pushButton.removeActionListener(this);
                        dumpButton.removeActionListener(this);
                        popButton.removeActionListener(this);
                        colorField.setText(null);
                        codeField.setText(null);
                        colorField.removeActionListener(this);
                        colorField.removeActionListener(this);
                  }
                //old room
                oldRoom=newcolor;
                System.out.println(oldRoom);
                counter++;
                 // add code to push color/code ON the stack and change to that color room
         }

         if (e.getSource()== dumpButton) {
             //System.out.println("1) "+colorField.getText() +"2)"+codeField.getText());
                System.out.println("Stack Contents Dump: ");
                
                String [] test1= stackcode.returnArray();
                String [] test2= stackcolor.returnArray();
                for(int x=0; x < test1.length;x++){
                    if(test1[x]==null)
                        break;
                    System.out.println(test2[x]+" - "+test1[x]);
                    
                }
                 // add code to print contents of Stack to the CONSOLE
         }
         
}
     class StackCode 
        { 
        private int maxSize;        // size of stack array 
        private String[] stackArraycode;
        private int top;            // top of stack 
     //-------------------------------------------------------------- 
        public StackCode(int s)         // constructor 
           { 
           maxSize = s;             // set array size 
           stackArraycode = new String[maxSize];  // create array 

           top = -1;                // no items yet 
           } 
     //-------------------------------------------------------------- 
        public void push(String j)  // put item on top of stack 
           { 
            top++;
           stackArraycode[top] = j;// increment top, insert item 
           } 
     //-------------------------------------------------------------- 
        public String popCode()         // take item from top of stack 
           { 
           return stackArraycode[top--];
           // access item, decrement top 
           }
        
       //-------------------------------------------------------------- 
        public boolean isEmpty()    // true if stack is empty 
           { 
           return (top == -1);  // = =  returns true or false 
           } 
        
     //-------------------------------------------------------------- 
        public String [] returnArray(){
            return stackArraycode;
        }
        public int returnValue(){
            return top;
        }
     }
     class StackColor 
        { 
        private int maxSize;        // size of stack array 
        
        private String[] stackArraycolor;
        private int top;            // top of stack 
     //-------------------------------------------------------------- 
        public StackColor(int s)         // constructor 
           { 
           maxSize = s;             // set array size 
             // create array 
           stackArraycolor = new String[maxSize];
           top = -1;                // no items yet 
           } 
     //-------------------------------------------------------------- 
        public void push(String k)  // put item on top of stack 
           { 
            top++;
           stackArraycolor[top] = k;// increment top, insert item 
           } 
     //-------------------------------------------------------------- 

        public String popColor()         // take item from top of stack 
           { 
           return stackArraycolor[top--];
           // access item, decrement top 
           }
        
       //-------------------------------------------------------------- 
        public boolean isEmpty()    // true if stack is empty 
           { 
           return (top == -1);  // = =  returns true or false 
           } 
     //-------------------------------------------------------------- 
        public String [] returnArray(){
            return stackArraycolor;
        }
        public int returnValue(){
            return top;
        }
     }
     class Room 
        { 
         public void RoomSelect(){
             
         }
         public boolean TRUEROOM(String current, String next){
             if (current.equals("green")){
                 if(next.equals("brown"))
                     return true;
                 else if(next.equals("pink"))
                     return true;
                 else if(next.equals("blue"))
                     return true;
                 else
                     return false;
             }
             else if (current.equals("pink")){
                 if(next.equals("green"))
                     return true;
                 else if(next.equals("brown"))
                     return true;
                 else if(next.equals("blue"))
                     return true;
                 else
                     return false;
             }
             else if (current.equals("brown")){
                 if(next.equals("green"))
                     return true;
                 else if(next.equals("pink"))
                     return true;
                 else if(next.equals("red"))
                     return true;
                 else
                     return false;
             }
             else if (current.equals("blue")){
                 if(next.equals("green"))
                     return true;
                 else if(next.equals("pink"))
                     return true;
                 else if(next.equals("yellow"))
                     return true;
                 else
                     return false;
             }
             else if (current.equals("red")){
                 if(next.equals("brown"))
                     return true;
                 else if(next.equals("yellow"))
                     return true;
                 else
                     return false;
             }
             else if (current.equals("yellow")){
                 if(next.equals("red"))
                     return true;
                 else if(next.equals("gold")){
                     keyboard=true;
                     System.out.println("YOU GOT THE KEYBOARD");
                     return true;
                 }
                 else if(next.equals("blue"))
                     return true;
                 else
                     return false;
             }
             else if (current.equals("gold")){
                 if(next.equals("yellow"))
                     return true;
                 else
                     return false;
             }
             else
             return false;
         }
     }
}     // End Of Project3