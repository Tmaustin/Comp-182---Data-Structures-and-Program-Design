/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Taylor Austin
 * CS 182
 * 3-20-14
 * This Project takes a string and compares it to a Linear/Binary search.
 * It counts the number of comparisons and and compares to to the other searches.
 * Then takes the Average and finds the number or searches needed to pass the overhead
 * given by Binary Search
 * This is a crap way to do this but I ran out of patience
 */
public class Project2 {

    /**
     * @param args the command line arguments
     */
    static int overhead=0;
    public static void main(String[] args) {
        Project2 pro = new Project2();
        double a=2.2;
        
        String[] names = { "fred" , "barney", "tom", "jerry", "larry", "moe", "curly", "betty" , "wilma", "bart", "homer", "marge", "maggie", "lisa", "pebbles" , "bambam", "smithers", "burns", "milhouse", "george", "astro", "dino" , "mickey", "minnie", "pluto", "goofy", "donald", "huey", "louie" , "dewey", "snowwhite", "happy", "doc", "grumpy", "sneezy", "dopey" , "sleepy", "bambi", "belle", "gaston", "tarzan", "jane", "simba" , "scar", "mufasa", "ariel", "flounder", "bugs", "daffy", "elmer" , "foghorn", "chickenhawk", "roger", "jessica", "hank", "bobby", "peggy" , "spot", "pongo", "perdy", "buzz", "potatohead", "woody","chuckie" , "tommy", "phil", "lil", "angelica", "dill", "spike", "pepe" , "speedy", "yosemite", "sam", "tweety", "sylvester", "granny", "spiderman" , "batman", "superman", "supergirl", "robin", "catwoman","penguin", "thing" , "flash", "silversurfer", "xmen", "pokemon", "joker", "wonderwoman" };
        String[] sortednames = new String[names.length];
        pro.copyArray(names, sortednames);
        //Arrays.sort(sortednames);
        pro.SortArray(sortednames);
        System.out.println(overhead);
        int counter=0;
        int counter1=0;
        int LinearCount=0;
        int BinaryCount=0;
        int saveAVG=0;
        int TotalsaveAVG=0;
        int totalcount=0;
        Scanner scan = new Scanner (System.in);
        System.out.println("1) Linear/Binary Search Comparison\n2) Exit");
            String select =scan.nextLine();
            //pro.PrintArray(sortednames);
        while (true){
            
             int searches=0;
            
            
            if(select.equals("1")){
                System.out.println("\nEnter a name to search for (Type 'exit' to Quit):");
                String SearchName = scan.nextLine();
                if(SearchName.equals("exit"))
                    break;
                int total= pro.linearSearch(names,SearchName);
                counter +=total;
                LinearCount++;
                System.out.println("Linear search: "+ total +" comparisons, average "+counter/LinearCount+" comparisons per linear search ");
                //BINARY
                int total1= pro.binarySearch(sortednames,SearchName);
                counter1 += total1;
                totalcount+=(total-total1);
                BinaryCount++;
                saveAVG+=((counter/LinearCount)-(counter1/BinaryCount));
                TotalsaveAVG=saveAVG/BinaryCount;
                int NumSearch=(overhead-totalcount)/TotalsaveAVG;
                System.out.println("Binary search: "+ total1 +" comparisons, average "+counter1/BinaryCount+" comparisons per linear search ");
                
                System.out.println("(TOTAL DIFFERENCE: "+totalcount+")Binary search currently 'saves' "+TotalsaveAVG+" comparisons on average per search ");
                if (NumSearch <=0)
                    NumSearch=0;
                System.out.println("It will take "+ NumSearch +" searches before binary sort overhead of "+overhead+" comparisons is crossed.");
            }
            else{
                break;
            }
            
            
        }
    }
public int binarySearch(String[]Sarray,String searchKey) 
      { 
      int nElems=Sarray.length;
      int lowerBound = 0; 
      int upperBound = nElems-1; 
      int curIn;
      int x=0;
      while(true) 
         { 
         curIn = (lowerBound + upperBound ) / 2; 
         x++;
         if(Sarray[curIn].equalsIgnoreCase(searchKey)) 
            return x;              // found it 
         else if(lowerBound > upperBound) 
            return x;             // can not find it 
         else                          // divide range 
            { 
            if(Sarray[curIn].compareTo(searchKey)<0){ 
               lowerBound = curIn + 1; // it is in upper half 
               
            }
            else {
               upperBound = curIn - 1; // it is in lower half 
               
            }
            }  // end else divide range 
         }  // end while 
      }
    void copyArray ( String [ ] source, String [ ] target ){
        for(int x=0;x<source.length;x++)
            target[x]=source[x];
    }
    public int linearSearch(String[] namesArray, String searchName) {
            int numOfComparisons = 0;
            for (int i = 0; i < namesArray.length; i++) {
                numOfComparisons++;
                if (namesArray[i].equals(searchName)) {
                    break;
                }
            }
            return numOfComparisons;
    }
    public void PrintArray(String [ ] sortarray ){
       int x=0;
       
        while (x<sortarray.length){
            System.out.print(sortarray[x]+", ");
            x++;
        }
    }
    String [] SortArray ( String [ ] sortarray )
    {    
    for(int j=0; j<sortarray.length;j++)
    {
         for (int i=j+1 ; i<sortarray.length; i++)
         {
             if(sortarray[i].compareTo(sortarray[j])<0)
             {
                 String temp= sortarray[j];
                 sortarray[j]= sortarray[i]; 
                 sortarray[i]=temp;
                 overhead++;   

             }
         }
    }
    return sortarray;
    }
}
