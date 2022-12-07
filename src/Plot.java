/**
 * @author Vu Hoang Thuy Duong
 * No.etudiant : 21110221
 * Groupe : 7
 * 
 * 
 * 
 * =====================
 * Information de class 
 * =====================
 * Type : public class Plot extends JPanel
 * Obj : realiser un graphe des animaux affectes lors de la Maree noire
 */

/* Import required classes and packages */
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

/* Extends JPanel class */
public class Plot extends JPanel {

    /* variables definition */
    int[] data;
    int marg = 60;

    /* Constructeur */
    public Plot(int[] data) {
        this.data = data;
    }

    /* Methods */
    protected void paintComponent(Graphics grf) {

        /* Create instance of the Graphics to use its methods */
        super.paintComponent(grf);
        Graphics2D graph = (Graphics2D)grf;

        /* Sets the value of a single preference for the rendering algorithms */
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        /* Get width and height */
        int width = getWidth();
        int height = getHeight();

        /* Draw graph */
        graph.draw(new Line2D.Double(marg, marg, marg, height-marg));
        graph.draw(new Line2D.Double(marg, height-marg, width-marg, height-marg));

        /* Find value of x and scale to plot points */
        double x = (double)(width-2*marg) / (data.length-1);
        double scale = (double)(height-2*marg) / getMax();

        /* Set color for points */
        //graph.setPaint(Color.RED);

        /* Set points to the graph */
        for (int i=0;i<data.length;i++) {
            double x1 = marg + i*x;
            double y1 = height-marg-scale*data[i];
            graph.fill(new Ellipse2D.Double(x1-2,y1-2,4,4));
            graph.setPaint(Color.RED);
        }
    }

    /* getMax to find maximum value */
    private int getMax() {
        int max = -Integer.MAX_VALUE;
        for (int i=0;i<data.length;i++) {
            if (data[i]>max) 
                max = data[i];
        }
        return max;
    }
}