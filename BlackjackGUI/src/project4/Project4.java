package project4;
/***************************************************************
Taylor Austin
* Project 4 
* BlackJack
* Chris Ferguson
* 4/29/14
* Blackjack dealers shuffle and deal cards for the play. Blackjack is a card game in
* which the goal is to accumulate cards with a higher count than that of the
* dealer but not higher than 21. Blackjack dealers must understand dealing 
* procedures to keep games secure and fair. If the Dealer or Player is 21 at the start they
* get blackjack. Tie goes to the Dealer as the start. 



***************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Collections;
import java.util.LinkedList;



public class Project4 extends JFrame implements ActionListener {

  private static int winxpos=0,winypos=0;      // place window here


  private JButton shuffleButton,exitButton, newButton, standButton;
  private CardList theDeck = null;
  private JPanel northPanel;
  private MyPanel centerPanel;
  private static JFrame myFrame = null;
  NewGame n = new NewGame();
  LinkedList list = new LinkedList();
  int xpos1 = 25, ypos1 = 245;
  int xpos2 = 25, ypos2 = 45;
  int DealerTotal=0;
  int PlayerTotal=0;
  Card [] DA;
  Card [] PA;
  Deck d = new Deck(52);
  
  Player player = new Player(52);
  Dealer dealer = new Dealer (52);
  Card [] dList= new Card[10];
  int playercount=2,dealercount=2;
  Boolean dA1=false,dA2=false,dA3=false,dA4=false,pA1=false,pA2=false,pA3=false,pA4=false;
  Boolean newg=false;
  Image BackCard,Background;
  Boolean stand=false,DealerBJ=false,PlayerBJ=false;

  ////////////              MAIN      ////////////////////////
  public static void main(String[] args) {
     Project4 tpo = new Project4();
  }

  ////////////            CONSTRUCTOR   /////////////////////
  public Project4 ()
  {
     
     // Card BackCard= new Card(53);  
     // Card FrontCard;
      myFrame = this;                 // need a static variable reference to a JFrame object
        northPanel = new JPanel();
        northPanel.setBackground(Color.white);
        shuffleButton = new JButton("New Game");
        northPanel.add(shuffleButton);
        shuffleButton.addActionListener(this);
        newButton = new JButton("Hit!");
        northPanel.add(newButton);
        newButton.addActionListener(this);
        standButton = new JButton("Stand!");
        northPanel.add(standButton);
        standButton.addActionListener(this);
        exitButton = new JButton("Exit");
        northPanel.add(exitButton);
        exitButton.addActionListener(this);
        getContentPane().add("North",northPanel);

        centerPanel = new MyPanel();
        getContentPane().add("Center",centerPanel);
        
        theDeck = new CardList(52);
        n.game();
        
        setSize(800,700);
        setLocation(winxpos,winypos);

        setVisible(true);
        
        
   }


  ////////////   BUTTON CLICKS ///////////////////////////
  public void actionPerformed(ActionEvent e) {

      if (e.getSource()== exitButton) {
        dispose(); System.exit(0);
      }
       if (e.getSource()== shuffleButton) {
        d=null;player=null;dealer=null;
        
        n.game();
        xpos1 = 25; ypos1 = 245;
        xpos2 = 25; ypos2 = 45;
        repaint();
      }
       if (e.getSource()== newButton && PlayerTotal<=21) {
        
           n.Hit();
        
        repaint();
      }
       if (e.getSource()== standButton && PlayerTotal<=21) {
            n.DealerHit();
            repaint();
      }
  }


// This routine will load an image into memory
//
public static Image load_picture(String fname)
{
        // Create a MediaTracker to inform us when the image has
        // been completely loaded.
        Image image;
        MediaTracker tracker = new MediaTracker(myFrame);


        // getImage() returns immediately.  The image is not
        // actually loaded until it is first used.  We use a
        // MediaTracker to make sure the image is loaded
        // before we try to display it.

        image = myFrame.getToolkit().getImage(fname);

        // Add the image to the MediaTracker so that we can wait
        // for it.
        tracker.addImage(image, 0);
        try { tracker.waitForID(0); }
        catch ( InterruptedException e) { System.err.println(e); }

        if (tracker.isErrorID(0)) { image=null;}
        return image;
}
// --------------   end of load_picture ---------------------------

class MyPanel extends JPanel {

 ////////////    PAINT   ////////////////////////////////
  public void paintComponent (Graphics g) {
    //
    //int xpos = 25, ypos = 5;
    BackCard = Project4.load_picture("images/gbCard52.gif");
    Background = Project4.load_picture("images/Background.jpg");
    Image PlayerWin=Project4.load_picture("images/PlayerWin.png");
    Image DealerWin=Project4.load_picture("images/DealerWin.png");
      if (player == null) return;
    //int x=1;
    g.drawImage(Background, 0,0, this);
    g.setFont(new Font("TimesRoman", Font.BOLD, 17));
    
    int x=0;int y=0;
    if(stand==true&&DealerTotal>=17&&DealerTotal<=20&&DealerTotal==PlayerTotal){

        g.drawString("PLAYER = "+PlayerTotal+"     PUSH", 25, 200);
            g.drawString("DEALER = "+DealerTotal+"     PUSH", 25, 15);
    }
   else if (stand==true){
        
        PA = player.DealerArray();
        PlayerTotal=0;
        int xyz =0;
        while(PA[xyz]!=null){
            g.drawImage(PA[xyz].getCardImage(), xpos1, ypos1, this);
            xpos1 += 80;
            PlayerTotal+=PA[xyz].CardValue();
            xyz++;
            if (xpos1 > 700) {
                xpos1 = 25; ypos1 += 105;
            }
        }
        DA = dealer.DealerArray();
        DealerTotal=0;
        int xyz1 =0;
        
        while(DA[xyz1]!=null){
            
            g.drawImage(DA[xyz1].getCardImage(), xpos2, ypos2, this);
            xpos2 += 80;
            DealerTotal+=DA[xyz1].CardValue();
            xyz1++;
        }
        
        if(DealerTotal>PlayerTotal){
            g.drawString("PLAYER = "+PlayerTotal+"     Dealer WINS", 25, 200);
            g.drawString("DEALER = "+DealerTotal+"     Dealer WINS", 25, 15);
        }
                    else if(DealerTotal>17&& DealerTotal==PlayerTotal){
            g.drawString("PLAYER = "+PlayerTotal+"     PUSH", 25, 200);
            g.drawString("DEALER = "+DealerTotal+"     PUSH", 25, 15);
            }
        else{
            int x1=0;
            int x2=0;
            
            while (DealerTotal<17){
                dealer.push(d.pop());
                dealer.pop();
                DA=dealer.DealerArray();
                DealerTotal=0;
                xpos2 = 25; ypos2 = 45;
                
                while(DA[x1]!=null){
                    g.drawImage(DA[x1].getCardImage(), xpos2, ypos2, this);
                    
                    xpos2 += 80;
                    DealerTotal+=DA[x1].CardValue();
                    x1++;
                    }
                x1=0;
                x2=0;
                    if(DealerTotal>17)
                    while(DA[x2]!=null) {
                    if(DA[x2].CardValue()==11){
                        DA[x2].SetCardValue();
                        DealerTotal-=10;
                        
                        break;
                    }
                    x2++;
                }
                
            }
            if (DealerTotal>PlayerTotal&&DealerTotal<=21){
                    g.drawString("DEALER = "+DealerTotal+"     Dealer WINS", 25, 15);
                    g.drawString("PLAYER = "+PlayerTotal+"     Dealer WINS", 25, 200);
            }
            else if(DealerTotal>=17&& DealerTotal==PlayerTotal){
            g.drawString("PLAYER = "+PlayerTotal+"     PUSH", 25, 200);
            g.drawString("DEALER = "+DealerTotal+"     PUSH", 25, 15);
            }
            else{
                g.drawString("DEALER = "+DealerTotal+"     Player WINS", 25, 15);
                    g.drawString("PLAYER = "+PlayerTotal+"     Player WINS", 25, 200);
            }
        }
    }
    else if(newg==true){

        player.pop();
        player.pop();
        PA=player.DealerArray();
        while(PA[x]!=null){
        g.drawImage(PA[x].getCardImage(), xpos1, ypos1, this);
        xpos1 += 80;
        PlayerTotal+=PA[x].CardValue();
        if (PlayerTotal==21)
            PlayerBJ=true;
        x++;
        }
        
   // g.drawImage(dList[1].getCardImage(), xpos1, ypos1, this);
    
        dealer.pop();
        dealer.pop();
        DA=dealer.DealerArray();
        while(DA[y]!=null){
        g.drawImage(DA[y].getCardImage(), xpos2, ypos2, this);
        xpos2 += 80;
        DealerTotal+=DA[y].CardValue();
        if (DealerTotal==21)
            DealerBJ=true;
        if(y==1){
            DealerTotal-=DA[y].CardValue();
        }
        y++;
        }
        g.drawImage(BackCard, 105, 45, this);
    
    newg=false;
    }
    else{
        
        player.pop();
        PA=player.DealerArray();
        PlayerTotal=0;
        int xyz =0;
        while(PA[xyz]!=null){
        g.drawImage(PA[xyz].getCardImage(), xpos1, ypos1, this);
        xpos1 += 80;
        PlayerTotal+=PA[xyz].CardValue();
        xyz++;
        
            if (xpos1 > 700) {
                xpos1 = 25; ypos1 += 105;
            }
        }

        
        
        
        
        int DAtemp;
        DA = dealer.DealerArray();
        DealerTotal=0;
        int xyz1 =0;
        while(DA[xyz1]!=null){
        g.drawImage(DA[xyz1].getCardImage(), xpos2, ypos2, this);
        xpos2 += 80;
        DealerTotal+=DA[xyz1].CardValue();
        if(xyz1==1){
            DealerTotal-=DA[xyz1].CardValue();
        }
        xyz1++;
        }

        g.drawImage(BackCard, 105, 45, this);
    
    }
    if (PlayerTotal>21&&stand==false){
        Boolean pass = true;
        int counterx=0;
        while (PA[counterx]!=null){
        if(PA[counterx].CardValue()==11){
            PA[counterx].SetCardValue();
            PlayerTotal-=10;
            pass=true;
            
            break;
        }
        else
            pass=false;
        counterx++;
    }
        int countertotal=0;
        PlayerTotal=0;
        DealerTotal=0;
        while (PA[countertotal]!=null){
            PlayerTotal+=PA[countertotal].CardValue();
            countertotal++;
        }
        countertotal=0;
        while (DA[countertotal]!=null){
            DealerTotal+=DA[countertotal].CardValue();
            countertotal++;
        }
        
        if (pass==false){
            g.drawImage(DA[1].getCardImage(), 105, 45, this);
            g.drawString("PLAYER = "+PlayerTotal +"     BUST!!!!   ---   YOU ARE OVER 21 ", 25, 200);
                    if (DealerTotal==22){
            DealerTotal-=10;
        }
            g.drawString("DEALER = "+DealerTotal+ "     Dealer WINS", 25, 15);
        }
        if (pass==true){
            g.drawString("PLAYER = "+PlayerTotal, 25, 200);
            g.drawString("DEALER = "+DealerTotal+" +  ?", 25, 15);
            g.drawImage(BackCard, 105, 45, this);
        }
    }
    
    else if (stand == false){
    g.drawString("DEALER = "+DealerTotal+" +  ?", 25, 15);
    g.drawString("PLAYER = "+PlayerTotal, 25, 200);
    }
    if(DealerBJ==true||PlayerBJ==true){
        xpos1 = 25; ypos1 = 245;
        xpos2 = 25; ypos2 = 45;
        
        int xyz1 =0;
        while(DA[xyz1]!=null){
        g.drawImage(DA[xyz1].getCardImage(), xpos2, ypos2, this);
        xpos2 += 80;xyz1++;
        }
        xyz1 =0;
        while(PA[xyz1]!=null){
        g.drawImage(PA[xyz1].getCardImage(), xpos1, ypos1, this);
        xpos1 += 80;
        xyz1++;}
        
        
        if(DealerBJ==true){
            g.drawImage(DealerWin, winxpos,winypos, this);
        }
        else{
            g.drawImage(PlayerWin, winxpos,winypos, this);
        }
    }
    /*
    while (x<=52) {
       Card current=d.pop();
       Image tempimage = current.getCardImage();
       g.drawImage(tempimage, xpos, ypos, this);
       // note: tempimage member variable must be set BEFORE paint is called
       xpos += 80;
       if (xpos > 700) {
          xpos = 25; ypos += 105;
       }
       
       x++;
    } //while
    */
    
    
  }
}
class NewGame {
    public void game(){
        PlayerBJ=false;
        DealerBJ=false;
        stand=false;
        Card [] dList= new Card[10];
         DealerTotal=0;
          PlayerTotal=0;
        d = new Deck(52);
        player= new Player(52);
        dealer= new Dealer(52);
        theDeck.shuffle();
        Card current = theDeck.getFirstCard();
        while (current!=null) {
            current.SetCardValueFalse();
            d.push(current);
            current = current.getNextCard();
        }
        
        player.push(d.pop());
        dealer.push(d.pop());
        player.push(d.pop());
        dealer.push(d.pop());
        newg=true;
    }
    public void Hit(){
        xpos1 = 25; ypos1 = 245;
        xpos2 = 25; ypos2 = 45;
        player.push(d.pop());
        repaint();
    }
    public void DealerHit(){
        xpos1 = 25; ypos1 = 245;
        xpos2 = 25; ypos2 = 45;
        stand=true;
    }
}
}    // End Of class Project4

/*****************************************************************
   Class Link, the base class for a link list of playing cards
   May be placed in a file named Link.java

******************************************************************/
class Link {
  protected Link next;

  public Link getNext() { return next; }
  public void setNext(Link newnext) { next = newnext; }

}  // end class Link

/*****************************************************************
   Class Card, the derived class each card is one object of type Card
   May be placed in a file named Card.java
******************************************************************/

class Card extends Link {
  private Image cardimage;
  private int value;
  private int counter;
  private Boolean AtoZero=false;
  public Card (int cardnum) {
      
    cardimage = Project4.load_picture("images/gbCard" + cardnum + ".gif");
    // code ASSUMES there is an images sub-dir in your project folder
    if (cardimage == null) {
      System.out.println("Error - image failed to load: images/gbCard" + cardnum + ".gif");
      System.exit(-1);
    }
    counter=cardnum;
    
  }
  public Card getNextCard() {
    return (Card)next;
  }
  public int getCardNum() {
    return counter;
  }
  public Image getCardImage() {
    return cardimage;
  }
  public void SetCardValue(){
      AtoZero=true;
  }
    public void SetCardValueFalse(){
      AtoZero=false;
  }
  public int CardValue(){
      int cardnum=counter;
      if (cardnum==0||cardnum==13||cardnum==26||cardnum==39){//A
          if(AtoZero==false)
          value=11;
          if(AtoZero==true)
          value=1;
      }

      else if(cardnum==1||cardnum==14||cardnum==27||cardnum==40)//2
          value=2;
      else if(cardnum==2||cardnum==15||cardnum==28||cardnum==41)//3
          value=3;
      else if(cardnum==3||cardnum==16||cardnum==29||cardnum==42)//4
          value=4;
      else if(cardnum==4||cardnum==17||cardnum==30||cardnum==43)//5
          value=5;
      else if(cardnum==5||cardnum==18||cardnum==31||cardnum==44)//6
          value=6;
      else if(cardnum==6||cardnum==19||cardnum==32||cardnum==45)//7
          value=7;
      else if(cardnum==7||cardnum==20||cardnum==33||cardnum==46)//8
          value=8;
      else if(cardnum==8||cardnum==21||cardnum==34||cardnum==47)//9
          value=9;
      else if(cardnum==9||cardnum==22||cardnum==35||cardnum==48)//10
          value=10;
      else if(cardnum==10||cardnum==23||cardnum==36||cardnum==49)//J
          value=10;
      else if(cardnum==11||cardnum==24||cardnum==37||cardnum==50)//Q
          value=10;
      else if(cardnum==12||cardnum==25||cardnum==38||cardnum==51)//K
          value=10;
      
      return value;
  }
}  //end class Card

/*****************************************************************
   Class CardList, A Linked list of playing cards
   May be placed in a file named CardList.java

   Note : This class can be used to create a 'hand' of cards
   Just Create another CardList object, and delete cards from
   'theDeck' and insert the into the new CardList object

******************************************************************/

class CardList {
  private Card firstcard = null;
  private int numcards=0;

  public  CardList(int num) {
    numcards = num;   //set numcards in the deck
    for (int i = 0; i < num; i++) {  // load the cards
      Card temp = new Card(i);
      if (firstcard != null) {
        temp.setNext(firstcard);
      }
      firstcard = temp;
    }
  }

  public Card getFirstCard() {
      return firstcard;
  }

  public Card deleteCard(int cardnum) {
    Card target, targetprevious;

    if (cardnum > numcards)
      return null;   // not enough cards to delete that one
    else
      numcards--;

    target = firstcard;
    targetprevious = null;
    while (cardnum-- > 0) {
        targetprevious = target;
        target = target.getNextCard();
        if (target == null) return null;  // error, card not found
    }
    if (targetprevious != null)
      targetprevious.setNext(target.getNextCard());
    else
      firstcard =  target.getNextCard();
    return target;
  }

  public void insertCard(Card target) {
    numcards++;
    if (firstcard != null)
      target.setNext(firstcard);
    else
      target.setNext(null);
    firstcard =  target;
  }

  public void shuffle() {
    for ( int i = 0; i < 300; i++) {
      int rand = (int)(Math.random() * 100) % numcards;
      Card temp = deleteCard(rand);
      
      if (temp != null) insertCard(temp);
    }  // end for loop
  }   // end shuffle

  
}    // end class CardList
class Deck 
        { 
        private int maxSize;        // size of stack array 
        private Card[] stackArray; 
        private int top;            // top of stack 
     //-------------------------------------------------------------- 
        public Deck(int s)         // constructor 
           { 
           maxSize = s;             // set array size 
           stackArray = new Card[maxSize];  // create array 
           top = -1;                // no items yet 
           } 
     //-------------------------------------------------------------- 
        public void push(Card j)  // put item on top of stack 
           { 
           stackArray[++top] = j;     // increment top, insert item 
           } 
     //-------------------------------------------------------------- 
        public Card pop()         // take item from top of stack 
           { 
            
           return stackArray[top--];  // access item, decrement top 
           } 
       //-------------------------------------------------------------- 
        public boolean isEmpty()    // true if stack is empty 
           { 
           return (top == -1);  // = =  returns true or false 
           } 
     //-------------------------------------------------------------- 
    }  // end class StackX


class Dealer 
        { 
        private int maxSize;        // size of stack array 
        private Card[] stackArray; 
        private int top;            // top of stack 
        public int Dcounter=0;
        Card[] DArray = new Card[52];
     //-------------------------------------------------------------- 
        public Dealer(int s)         // constructor 
           { 
           maxSize = s;             // set array size 
           stackArray = new Card[maxSize];  // create array 
           top = -1;                // no items yet 
           } 
     //-------------------------------------------------------------- 
        public void push(Card j)  // put item on top of stack 
           { 
           stackArray[++top] = j;     // increment top, insert item 
           } 
     //-------------------------------------------------------------- 
        public Card pop()         // take item from top of stack 
           { 
            DArray[Dcounter]= stackArray[top--];
            Dcounter++;
           return DArray[Dcounter];  // access item, decrement top 
           } 
        public Card [] DealerArray(){
            return DArray;
        }
       //-------------------------------------------------------------- 

        public boolean isEmpty()    // true if stack is empty 
           { 
           return (top == -1);  // = =  returns true or false 
           } 
     //-------------------------------------------------------------- 
    }  // end class StackX



class Player
        { 
        private int maxSize;        // size of stack array 
        private Card[] stackArray; 
        private int top;            // top of stack 
        public int Pcounter=0;
        Card[] PArray = new Card[52];
     //-------------------------------------------------------------- 
        public Player(int s)         // constructor 
           { 
           maxSize = s;             // set array size 
           stackArray = new Card[maxSize];  // create array 
           top = -1;                // no items yet 
           } 
     //-------------------------------------------------------------- 
        public void push(Card j)  // put item on top of stack 
           { 
           stackArray[++top] = j;     // increment top, insert item 
           } 
     //-------------------------------------------------------------- 
        public Card pop()         // take item from top of stack 
           { 
            PArray[Pcounter]= stackArray[top--];
            Pcounter++;
           return PArray[Pcounter];  // access item, decrement top 
           } 
        public Card [] DealerArray(){
            return PArray;
        }
       //-------------------------------------------------------------- 
        public boolean isEmpty()    // true if stack is empty 
           { 
           return (top == -1);  // = =  returns true or false 
           } 
     //-------------------------------------------------------------- 
    }  // end class StackX