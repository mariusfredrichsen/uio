import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Skrevet av Dag Langmyhr
// Refaktorert av Stein Gjessing: 
//  - Klasser løftet ut av metoder
//  - Modell uten avhengighet av GUI

// Hovedprogrammet
class TTTGUIS {
    public static void main (String[] arg) {
	    Kontroll kontroll = new Kontroll();
	    kontroll.startSpillet();
    }
}