import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Provides a graphical user interface for rendering and displaying fractal drawings.
 *
 * @author Raingsey Tevy
 * @version 2024-12-06
 */
public class FractalDrawing extends JFrame implements FractalObserver {

    /** the subject in an observer pattern */
    private final FractalSubject subj;
    /** ArrayList containing elements to make the fractals/branches */
    private ArrayList<FractalElement> fractalElementsArrayList;

    /**
     * Constructor for fractal drawing
     *
     * @param subj the subject in an observer design model
     */
    public FractalDrawing(FractalSubject subj) {
        this.subj = subj;
        subj.registerObserver(this); //register me as an observer of your stuff; not in observer list not getting any updates

        setTitle("Fractal Drawing");
        setSize(750, 750);

        setResizable(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new DrawArea();
        getContentPane().add(mainPanel);

        int[] sliderInfo = new int[6];
        sliderInfo[0] = 12;
        sliderInfo[1] = 70;
        sliderInfo[2] = 20;
        sliderInfo[3] = 30;
        sliderInfo[4] = 200;
        sliderInfo[5] = 15;
        Color[] colorInfo = new Color[2];
        colorInfo[0] = new Color(92, 64, 51);
        colorInfo[1] = new Color(58, 95, 11);

        subj.setOptions(sliderInfo, colorInfo);

        setVisible(true);
    }

    /**
     * Method that is called when the state of the fractal model changes.
     */
    @Override
    public void update() {
        fractalElementsArrayList = subj.getFractalElements();
        repaint();
    }

    /**
     * Panel for drawing fractal elements.
     */
    private class DrawArea extends JPanel {

        /**
         * Constructor for DrawArea
         */
        public DrawArea() {
            //creates a JPanel inside a JPanel
        }

        /**
         * Draws the fractal elements on the panel.
         *
         * @param g the graphics context
         */
        @Override
        protected void paintComponent(Graphics g) {
            setBackground(new Color(0,0,0));
            super.paintComponent(g);
            for (FractalElement element : fractalElementsArrayList) {
                element.draw(g);
            }
        }
    }

}
