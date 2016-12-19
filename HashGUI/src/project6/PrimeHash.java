/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import project6.hash.DataItem;

public class PrimeHash extends JFrame implements ActionListener {

    private static int win_xpos = 0, win_ypos = 0;// place window here
    private static int win_xsize = 1200, win_ysize = 700;//  window size

// Private state variables.
    private Font boldfont = new Font("TimesRoman", Font.BOLD, 18);
    private Font plainfont = new Font("TimesRoman", Font.PLAIN, 12);

    private JButton hashbutton, exitbutton, numberinsert, numberdelete, numberfind, resetbutton, repaint;
    private JPanel northPanel, southPanel;
    private MyJPanel centerPanel;
    private JTextField hashsizefield, numberfield;
    private String thetext = "101", number;
    int counter = 0, x = 20, y = 30;

    hash st = new hash();
    hash.DataItem di;
    hash.HashTable hi;
    hash.DataItem[] hello;
    boolean numberfinder = false, redo = false;

////////////MAIN////////////////////////
    public static void main(String[] args) {
        PrimeHash tpo = new PrimeHash();

        tpo.addWindowListener(new WindowAdapter() {   // this exits the program when X box clicked
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

////////////CONSTRUCTOR/////////////////////
    public PrimeHash() {

        northPanel = new JPanel();
        northPanel.add(new Label("Enter a hashtable size: "));
        hashsizefield = new JTextField(thetext, 15);
        northPanel.add(hashsizefield);
        hashbutton = new JButton("CreateHash");
        northPanel.add(hashbutton);
        hashbutton.addActionListener(this);

        northPanel.add(new Label("Enter a name: "));
        numberfield = new JTextField(number, 15);
        northPanel.add(numberfield);
        numberinsert = new JButton("Insert");
        northPanel.add(numberinsert);
        numberinsert.addActionListener(this);
        numberdelete = new JButton("Delete");
        northPanel.add(numberdelete);
        numberdelete.addActionListener(this);
        numberfind = new JButton("Find");
        northPanel.add(numberfind);
        numberfind.addActionListener(this);
        resetbutton = new JButton("Reset");
        northPanel.add(resetbutton);
        resetbutton.addActionListener(this);
        exitbutton = new JButton("Exit");
        northPanel.add(exitbutton);
        exitbutton.addActionListener(this);

        getContentPane().add("North", northPanel);
        centerPanel = new MyJPanel();
        centerPanel.setPreferredSize(new Dimension(2000, win_ysize - 150));

        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.setPreferredSize(new Dimension(400, 400));

        getContentPane().add("Center", scrollPane);

        repaint = new JButton("REPAINT");
        repaint.addActionListener(this);
        getContentPane().add("South", repaint);

        String[] names = {"fred", "barney", "tom", "jerry", "larry", "moe", "curly", "betty", "wilma", "bart", "homer", "marge", "maggie", "lisa", "pebbles", "bambam", "smithers", "burns", "milhouse", "george", "astro", "dino", "mickey", "minnie", "pluto", "goofy", "donald", "huey", "louie", "dewey", "snowwhite", "happy", "doc", "grumpy", "sneezy", "dopey", "sleepy", "bambi", "belle", "gaston", "tarzan", "jane", "simba", "scar", "mufasa", "ariel", "flounder", "bugs", "daffy", "elmer", "foghorn", "chickenhawk", "roger", "jessica", "hank", "bobby", "peggy", "spot", "pongo", "perdy", "buzz", "potatohead", "woody", "chuckie", "tommy", "phil", "lil", "angelica", "dill", "spike", "pepe", "speedy", "yosemite", "sam", "tweety", "sylvester", "granny", "spiderman", "batman", "superman", "supergirl", "robin", "jimmy", "olsen", "thing", "flash", "silversurfer", "xmen", "pokemon", "joker", "wonderwoman"};
        int[] names1 = {45, 75, 56, 78, 32, 45, 72, 48, 97, 24, 45, 75, 56, 78, 32, 45, 72, 48, 97, 24, 45, 75, 56, 78, 32, 45, 72, 48, 97, 24, 45, 75, 56, 78, 32, 45, 72, 48, 97, 24};
        st = new hash();
        hi = st.new HashTable(Integer.parseInt(thetext));
        while (counter < names1.length) {
            di = st.new DataItem(names[counter]);
            hi.insert(di);
            counter++;
        }
        hello = hi.getArray();
        // need more init stuff? try here.
        setSize(win_xsize, win_ysize);
        setLocation(win_xpos, win_ypos);
        setVisible(true);
    }

////////////BUTTON CLICKS ///////////////////////////
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == repaint) {
            x = 20;
            y = 30;
            repaint();
        }
        if (e.getSource() == exitbutton) {
            dispose();
            System.exit(0);
        }
        if (e.getSource() == resetbutton) {
            st = null;
            di = null;
            hi = null;
            counter = 0;
            x = 20;
            y = 30;
            thetext = "101";
            st = new hash();
            hi = st.new HashTable(Integer.parseInt(thetext));
            repaint();
        }

        if (e.getSource() == hashbutton) {
            thetext = hashsizefield.getText();
            reset(hello, thetext);
            counter = 0;
            x = 20;
            y = 30;
            repaint();

        }
        if (e.getSource() == numberinsert) {
            number = numberfield.getText();
            if (number.equals("")) {
                redo = true;
            } else {
                di = st.new DataItem(number);
                hi.insert(di);
            }

            counter = 0;
            x = 20;
            y = 30;

            repaint();

        }
        if (e.getSource() == numberdelete) {
            number = numberfield.getText();
            counter = 0;
            x = 20;
            y = 30;
            di = st.new DataItem(number);
            System.out.println(hi.hashFunc3(di.getKey()));
            hi.delete(di.getKey());
            repaint();

        }
        if (e.getSource() == numberfind) {

            counter = 0;
            x = 20;
            y = 30;

            numberfinder = true;
            repaint();

        }
    } // end actionPerformed

    public void reset(hash.DataItem[] temp1, String text1) {
        counter = 0;
        st = new hash();
        hi = st.new HashTable(Integer.parseInt(text1));
        for (int y = 0; y < temp1.length; y++) {
            if (temp1[y] != null) {
                System.out.println(temp1[y].getKey());
                di = st.new DataItem(temp1[y].getKey());
                hi.insert(di);
            }
        }
        hello = hi.getArray();
    }

    class MyJPanel extends JPanel {

        ////////////    PAINT   ////////////////////////////////
        public void paintComponent(Graphics g) {

            g.setFont(plainfont);
            g.drawLine(500, 20, 500, win_ysize - 100);

            int test = 0, maincounter = 0;
            hello = hi.getArray();

            if (numberfinder == true) {
                numberfinder = false;
                number = numberfield.getText();
                di = st.new DataItem(number);
                hash.DataItem tempitem = hi.find(di.getKey());
                if (tempitem != null) {
                    g.drawString("NAME WAS FOUND", 620, 10);
                } else {
                    g.drawString("NAME WAS NOT FOUND", 620, 10);
                }
            }
            if (redo == true) {
                g.drawString("ENTER IN ANOTHER NAME", 620, 10);
            }
            while (test < hello.length) {

                if (hello[test] != null && hello[test].getKey() != "-1") {
                    y += 20;

                    if (y > win_ysize - 150) {
                        x += 500;
                        
                        y = 50;
                    }
                    if (x>900)
                    g.drawLine(1000, 20, 1000, win_ysize - 100);
                    if (x>1400)
                    g.drawLine(1500, 20, 1500, win_ysize - 100);
                    g.drawString("Hash Crash:  " + hello[test].getKey(), x, y);
                    g.drawString("should be at         " + hi.hashFunc(hello[test].getKey()), x + 200, y);
                    g.drawString("found at         " + (test + 1), x + 350, y);
                    maincounter++;
                }
                test++;
            }

            g.drawString("Hash Crash count is " + maincounter, 20, 30);
            redo = false;
        }
    } // End Of MyJPanel

}     // End Of PrimeHash
