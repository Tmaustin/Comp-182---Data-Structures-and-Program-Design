package project6;
// hash.java
// demonstrates hash table with linear probing
// to run this program: C:>java HashTableApp

import java.io.*;
////////////////////////////////////////////////////////////////

public class hash {

    class DataItem {                                // (could have more data)

        private String iData;               // data item (key)
//--------------------------------------------------------------

        public DataItem(String ii) // constructor
        {
            iData = ii;
        }
//--------------------------------------------------------------

        public String getKey() {
            return iData;
        }
//--------------------------------------------------------------
    }  // end class DataItem
////////////////////////////////////////////////////////////////

    class HashTable {

        private DataItem[] hashArray;    // array holds hash table
        private int arraySize;
        private DataItem nonItem;        // for deleted items
// -------------------------------------------------------------

        public DataItem[] getArray() {
            return hashArray;
        }

        public int hashFunc3(String key) {
            int hashVal = 0;
            for (int j = 0; j < key.length(); j++) // left to right
            {
                int letter = key.charAt(j) - 96; // get char code
                hashVal = (hashVal * 27 + letter) % arraySize; // mod
            }
            return hashVal; // no mod
        } // end hashFunc3()

        public HashTable(int size) // constructor
        {
            arraySize = size;
            hashArray = new DataItem[arraySize];
            nonItem = new DataItem("-1");   // deleted item key is -1
        }
// -------------------------------------------------------------

        public void displayTable() {
            System.out.print("Table: ");
            for (int j = 0; j < arraySize; j++) {
                if (hashArray[j] != null) {
                    System.out.print(hashArray[j].getKey() + " ");
                } else {
                    System.out.print("** ");
                }
            }
            System.out.println("");
        }
// -------------------------------------------------------------

        public int hashFunc(String key) {
            return hashFunc3(key);       // hash function
        }
// -------------------------------------------------------------

        public void insert(DataItem item) // insert a DataItem
        // (assumes table not full)
        {
            String key = item.getKey();      // extract key
            int hashVal = hashFunc3(key);  // hash the key
            // until empty cell or -1,
            while (hashArray[hashVal] != null
                    && hashArray[hashVal].getKey()!=("-1") ) {
                ++hashVal;                 // go to next cell
                hashVal %= arraySize;      // wraparound if necessary
            }
            hashArray[hashVal] = item;    // insert item
        }  // end insert()
// -------------------------------------------------------------

        public DataItem delete(String key) // delete a DataItem
        {
            int hashVal = hashFunc3(key);  // hash the key

            while (hashArray[hashVal] != null) // until empty cell,
            {                               // found the key?
                if (hashArray[hashVal].getKey().equals(key)) {
                    DataItem temp = hashArray[hashVal]; // save item
                    hashArray[hashVal] = nonItem;       // delete item
                    return temp;                        // return item
                }
                ++hashVal;                 // go to next cell
                hashVal %= arraySize;      // wraparound if necessary
            }
            return null;                  // can't find item
        }  // end delete()
// -------------------------------------------------------------

        public DataItem find(String key) // find item with key
        {
            int hashVal = hashFunc3(key);  // hash the key

            while (hashArray[hashVal] != null) // until empty cell,
            {                               // found the key?
                if (hashArray[hashVal].getKey().equals(key)) {
                    return hashArray[hashVal];   // yes, return item
                }
                ++hashVal;                 // go to next cell
                hashVal %= arraySize;      // wraparound if necessary
            }
            return null;                  // can't find item
        }
// -------------------------------------------------------------
    }  // end class HashTable
}
