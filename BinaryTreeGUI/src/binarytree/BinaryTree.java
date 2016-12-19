/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
 

public class BinaryTree extends JFrame implements ActionListener {

private static int winxpos=0,winypos=0;// place window here

// Private state variables.

private Font boldfont = new Font ("TimesRoman",Font.BOLD,18);
private Font plainfont = new Font ("TimesRoman",Font.PLAIN,12);

private JButton insertbutton,exitbutton,deletebutton;
private JTextField infield;
private JPanel northPanel;
private MyPanel centerPanel;
private static final int WINWIDTH = 1200;
private static final int WINHEIGHT = 500;

private Tree theTree = new Tree();

 
////////////NODE////////////////////////


////////////TREE////////////////////////




        
////////////MAIN////////////////////////

public static void main(String[] args) {
        BinaryTree tpo = new BinaryTree();

        tpo.addWindowListener(new WindowAdapter() {   // this exits the program when X box clicked
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
}
 
 

////////////CONSTRUCTOR/////////////////////

public BinaryTree ()

{
       northPanel = new JPanel();
       northPanel.add(new Label("Enter a number to insert: "));
       infield = new JTextField("",20);
       northPanel.add(infield);
       insertbutton = new JButton("Insert");
       northPanel.add(insertbutton);
       insertbutton.addActionListener(this);
       deletebutton = new JButton("Delete");
       northPanel.add(deletebutton);
       deletebutton.addActionListener(this);
       exitbutton = new JButton("Exit");
       northPanel.add(exitbutton);
       exitbutton.addActionListener(this);
       getContentPane().add("North",northPanel);

       
       centerPanel = new MyPanel();
       centerPanel.setLayout(new BorderLayout());
       JScrollPane scroller;
       
       scroller= new JScrollPane(centerPanel,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       
       getContentPane().add("Center",scroller);
       



      theTree.insert(50, 1.5);
      theTree.insert(25, 1.2);
      theTree.insert(75, 1.7);
      theTree.insert(69, 1.5);
      theTree.insert(12, 1.5);
      theTree.insert(37, 1.2);
      theTree.insert(43, 1.7);
      theTree.insert(30, 1.5);
      theTree.insert(33, 1.2);
      theTree.insert(87, 1.7);
      theTree.insert(93, 1.5);
      theTree.insert(97, 1.5);
      theTree.displayTree();
      setSize(WINWIDTH,WINHEIGHT);
      setLocation(winxpos,winypos);
      setVisible(true);

}
 

////////////BUTTON CLICKS ///////////////////////////

public void actionPerformed(ActionEvent e) {

          if (e.getSource()== exitbutton) {
         dispose(); System.exit(0);

         }
 
 

         if (e.getSource()== insertbutton) {
                
                theTree.insert(Integer.parseInt(infield.getText()),2.1);
                
                repaint();
         }
         if (e.getSource()== deletebutton) {
                
                theTree.delete(Integer.parseInt(infield.getText()));
                
                repaint();
         }
 

}
 

class MyPanel extends JPanel {

 ////////////    PAINT   ////////////////////////////////
  public void paintComponent (Graphics g) {
         
         g.setFont(plainfont);
         g.drawString("I am paint",20,30);

         theTree.displayTree(g,theTree.getRoot(),WINWIDTH/2,40, 1);
  }
}
 

}     // End Of BinaryTree
  