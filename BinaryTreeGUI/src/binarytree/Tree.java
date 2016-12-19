/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytree;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

/**
 *
 * @author Student-HSLH133
 */
public class Tree {
    int counter=0;
    private Node root;   // the root Node of the tree
        public void displayTree(Graphics g, Node localTree, int x, int y, int level){
           Node temp=localTree;
           ////////////// X & Y Values ///////////////
           
           int MaxNodes=(int)Math.pow(2, counter);
           int NodePerLevel=(int)Math.pow(2, level);
           int MaxSpace=MaxNodes*20;
           int SpaceBetween = MaxSpace/NodePerLevel;
           
           y+=40;
           if(temp.leftChild!=null)
               displayTree(g,temp.leftChild,x-SpaceBetween/2, y,level+1);
           if(temp.rightChild!=null)
               displayTree(g,temp.rightChild,x+SpaceBetween/2, y,level+1);
           ////////////// Colors and Shapes //////////////
           
           g.setColor(Color.BLACK);
           if(temp.leftChild!=null)
                g.drawLine(x+6, y-40, x-SpaceBetween/2+15, y-10);
           if(temp.rightChild!=null)
                g.drawLine(x+6, y-40, x+SpaceBetween/2-2, y-10);
           y-=40;
           
           
           g.setColor(ColorTree(level));
           g.fillOval(x-3, y-15, 20, 20);
           g.setColor(Color.BLACK);
           g.drawString(Integer.toString(temp.iData), x, y);

       }
        
        public int Spacer(int level){
            int x=0;
            int total=0;
            while(x<level){
                total=level*2;
                x++;
            }
            return total;
            
        }
        public Color ColorTree(int level){

            if(level==1)
                return Color.WHITE;
            else if(level==2)
                return Color.GREEN;
            else if(level==3)
                return Color.CYAN;
            else if(level==4)
                return Color.ORANGE;
            else if(level==5)
                return Color.YELLOW;
            else if(level==6)
                return Color.PINK;
            else if(level==7)
                return Color.MAGENTA;
            else{
                /*
                Random rand = new Random();
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                */
                
                return Color.WHITE;//getHSBColor(r, g, b);
            }
            
        }
        
      
       
      

       
       public Node getRoot(){
           return root;
       }
       

        public Node find (int key) {        
            Node current = root;               // start at root
            while(current.iData != key)        // while no match,
               {
               if(key < current.iData)         // go left?
                  current = current.leftChild;
               else                            // or go right?
                  current = current.rightChild;
               if(current == null)             // if no child,
                  return null;                 // did not find it
               }
            return current;
        }
    public void insert(int id, double dd)
          {
          Node newNode = new Node();    // make new node
          newNode.iData = id;           // insert data
          newNode.dData = dd;
          if(root==null)                // no node in root
             root = newNode;
          else                          // root occupied
             {
             Node current = root;       // start at root
             Node parent;
             int levelcounter=1;
             while(true)                // (exits internally)
                {
                parent = current;
                if(id < current.iData)  // go left?
                   {
                   current = current.leftChild;
                   if(current == null)  // if end of the line,
                      {                 // insert on left
                      parent.leftChild = newNode;
                      return;
                      }
                   }  // end if go left
                else                    // or go right?
                   {
                   current = current.rightChild;
                   if(current == null)  // if end of the line
                      {                 // insert on right
                      parent.rightChild = newNode;
                      return;
                      }
                   }  // end else go right
                levelcounter++;
                if(levelcounter>counter)
                    counter++;
                }  // end while
             }  // end else not root
          }  // end insert() 
   public boolean delete(int key) // delete node with given key
      {                           // (assumes non-empty list)
      Node current = root;
      Node parent = root;
      boolean isLeftChild = true;

      while(current.iData != key)        // search for node
         {
         parent = current;
         if(key < current.iData)         // go left?
            {
            isLeftChild = true;
            current = current.leftChild;
            }
         else                            // or go right?
            {
            isLeftChild = false;
            current = current.rightChild;
            }
         if(current == null)             // end of the line,
            return false;                // didn't find it
         }  // end while
      // found node to delete

      // if no children, simply delete it
      if(current.leftChild==null&& current.rightChild==null)
      {
          if(current == root)
              root = null;
          else if(isLeftChild)
              parent.leftChild = null;
          else 
              parent.rightChild = null;
      }



      // if no right child, replace with left subtree
      //put your code here

      else if(current.rightChild==null)
          if(current == root)
              root = current.leftChild;
          else if (isLeftChild)
              parent.leftChild = current.leftChild;
          else
              parent.rightChild = current.leftChild;


      // if no left child, replace with right subtree
      //put your code here

      else if(current.leftChild==null)
          if(current == root)
              root = current.rightChild;
          else if(isLeftChild)
          parent.leftChild=current.rightChild;
          else
              parent.rightChild = current.rightChild;


      else  // two children, so replace with in order successor
         //put your code here
      {
          Node successor = getSuccessor(current);

          // if connect parent of current to successor instead
          if(current == root)
              root = successor;
          else if (isLeftChild)
              parent.leftChild = successor;
          else
              parent.rightChild = successor;
          // connect successor to current's left child
          successor.leftChild = current.leftChild;
      }



      // (successor cannot have a left child)
      return true;                                // success
      }  // end delete()
      private Node getSuccessor(Node theNode)
      {
      Node successorParent = theNode;
      Node successor = theNode;
      Node current = theNode.rightChild;   // go to right child
      while(current != null)               // until no more
         {                                 // left children,
         successorParent = successor;
         successor = current;
         current = current.leftChild;      // go to left child
         }
                                           // if successor not
      if(successor != theNode.rightChild)  // right child,
         {                                 // make connections
         successorParent.leftChild = successor.rightChild;
         successor.rightChild = theNode.rightChild;
         }
      return successor;
      }
    public int returnlevels(){
        return counter;
    }
   private void displayTree( Node t )
        {
            if( t != null )
            {
                displayTree( t.leftChild );
                System.out.println( t.iData );//was .element
                displayTree( t.rightChild );
            }
        }
  public void displayTree( )
        {
            if( isEmpty( ) )
                System.out.println( "Empty tree" );
            else
                displayTree( root );
        }
  public boolean isEmpty( )
        {
            return root == null;
        }
}