/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytree;

/**
 *
 * @author Student-HSLH133
 */
class Node extends Object{
    Node leftChild;     // tree overhead, not 'real' information
    Node rightChild;   // tree overhead, not 'real' information
    int iData;           // the key!!! Is this tree overhead or real data ?
    double dData;            // some data, your data may differ
}
class Person extends Node {

}