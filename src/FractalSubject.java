import java.awt.*;
import java.util.ArrayList;

/**
 * Provides required methods for a FractalSubject class
 *
 * @author Raingsey Tevy
 * @version 2024-12-05
 */
public interface FractalSubject {

    /** Signals all registered observers */
    void notifyObservers();

    /**
     * Adds observer to subject
     *
     * @param obs observer to be added
     */
    void registerObserver(FractalObserver obs);

    /**
     * Removes observer from the subject
     *
     * @param obs observer to be removed
     */
    void unregisterObserver(FractalObserver obs);

    /**
     * Sets the values obtained from the gui
     *
     * @param sliderInfo    array of int values obtained from sliders
     * @param colorInfo     array of colors
     */
    void setOptions(int[] sliderInfo, Color[] colorInfo);

    /**
     * Retrieves an ArrayList of fractal elements
     *
     * @return an ArrayList of fractal elements
     */
    ArrayList<FractalElement> getFractalElements();
}
