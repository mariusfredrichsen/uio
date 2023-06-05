

//Random ting :)
import java.util.Random;
//monitor?
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
//Fil og lese av terminal
import java.util.Scanner;
import java.io.File;
//GUI
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
//Exceptions
import java.lang.Exception;
//Iterator
import java.util.Iterator;

class MinExceptions extends Exception {
    MinExceptions(String etEllerAnnet) {
        super("Tilbkae meling og evt " + etEllerAnnet + " ting du kan legge til :)");
    }
}


class Node {
    Node neste = null;
    int data = 0;
}
Node hode = new Node();

@Override
public void leggTil(E x) {
    if (hode == null) {
        hode = new Node(x);
    } else if (hode.data.compareTo(x) > 0) {
        Node tempHolder = hode;
        hode = new Node(x);
        hode.neste = tempHolder;
    } else {
        Node nesteLenke = hode;
        
        while (nesteLenke != null) {
            if (nesteLenke.neste == null) {
                super.leggTil(x);
                break;
            } else {
                if (nesteLenke.neste.data.compareTo(x) >= 0) {
                    Node nesteLenkeHolder = nesteLenke.neste; 
                    Node nyNode = new Node(x);
                    nyNode.neste = nesteLenkeHolder;
                    nesteLenke.neste = nyNode;
                    break;
                } else {
                    nesteLenke = nesteLenke.neste;
                }
            }
        }
    }
}