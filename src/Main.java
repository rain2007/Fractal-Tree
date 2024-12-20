/**
 * Main class used to run the program
 *
 * @author Raingsey Tevy
 * @version 2024-12-06
 */
public class Main {

    /**
     * Constructor for main to avoid XDocLint
     */
    public Main() {
        //does nothing...
    }

    /**
     * Method used to run the program
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        FractalSubject subj = new FractalGenerator();
        new FractalGui(subj);
        new FractalDrawing(subj);
    }

}