import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Provides a graphical user interface to allow users to draw tree
 *
 * @author Raingsey Tevy
 * @version 2024-12-06
 */
public class FractalGui extends JFrame {

    /** the subject in an observer pattern */
    private final FractalSubject subj;
    /** int array of data from the sliders */
    private final int[] sliderInfo;
    /** array for color data */
    private final Color[] colorInfo;
    /** main panel of the GUI, which serves as the container for all graphical components */
    private final JPanel mainPanel; // only have global variables for if you cant pass it in OR too many methods use it


    /**
     * Constructor for Gui, creates and displays the graphical user interface
     *
     * @param subj instance that this GUI manipulates and updates based on user interactions
     */
    public FractalGui(FractalSubject subj) {
        this.subj = subj;

        sliderInfo = new int[6];
        colorInfo = new Color[2];
        sliderInfo[0] = 12;
        sliderInfo[1] = 70;
        sliderInfo[2] = 20;
        sliderInfo[3] = 30;
        sliderInfo[4] = 200;
        sliderInfo[5] = 15;
        colorInfo[0] = new Color(92, 64, 51);
        colorInfo[1] = new Color(58, 95, 11);

        setTitle("Fractal Settings");
        setSize(400,750);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel = new JPanel(null); // create panel; no layout manager; absolute positioning
        getContentPane().add(mainPanel); // glue it on to content pane to be our liaison

        JSlider recursionDepthSlider = makeSlider(0,"Recursion depth", 50, 4, 20, 12, 2, 1);
        JSlider childToParentSlider = makeSlider(1,"Child to parent ratio", 125, 40, 80, 70, 10, 5);
        JSlider leftChildAngleSlider = makeSlider(2,"Left child angle", 200, 0, 90, 20, 10, 5);
        JSlider rightChildAngleSlider = makeSlider(3,"Right child angle", 275, 0, 90, 30, 10, 5);
        JSlider trunkLengthSlider = makeSlider(4,"Trunk length", 350, 100, 400, 200, 100, 25);
        JSlider trunkWidthSlider = makeSlider(5,"Trunk width", 425, 10, 50, 15, 10, 5);
        makeColorButton(0,"Trunk Color...", 500, 150, 50);
        makeColorButton(1,"Leaf Color...", 565, 125, 50);

        JButton randomizeButton = new JButton("Randomize");
        randomizeButton.setBounds(50, 635, 100, 50);
        mainPanel.add(randomizeButton);

        randomizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                sliderInfo[0] = random.nextInt(16) + 4;
                sliderInfo[1] = random.nextInt(40) + 40;
                sliderInfo[2] = random.nextInt(90);
                sliderInfo[3] = random.nextInt(90);
                sliderInfo[4] = random.nextInt(300) + 100;
                sliderInfo[5] = random.nextInt(40) + 10;
                colorInfo[0] = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
                colorInfo[1] = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
                recursionDepthSlider.setValue(sliderInfo[0]);
                childToParentSlider.setValue(sliderInfo[1]);
                leftChildAngleSlider.setValue(sliderInfo[2]);
                rightChildAngleSlider.setValue(sliderInfo[3]);
                trunkLengthSlider.setValue(sliderInfo[4]);
                trunkWidthSlider.setValue(sliderInfo[5]);
                subj.setOptions(sliderInfo, colorInfo);
            }
        });

        setVisible(true);
    }

    /**
     * Helper method for making sliders
     *
     * @param index            index of the slider in the slider info array
     * @param title            label/title describing the slider
     * @param y                y coordinate of the slider on the panel
     * @param min              minimum value
     * @param max              maximum value
     * @param startingValue    beginning value of the slider
     * @param majorTickSpacing spacing between the bigger tick marks on the slider
     * @param minorTickSpacing spacing between the smaller tick marks on the slider
     * @return                 slider that was created
     */
    private JSlider makeSlider(int index, String title, int y,
                           int min, int max, int startingValue, int majorTickSpacing, int minorTickSpacing) {

        JLabel textLabel = new JLabel(title);
        textLabel.setBounds(50, y, 150, 20);
        mainPanel.add(textLabel);

        JSlider slider = new JSlider(JSlider.HORIZONTAL,min, max, startingValue);
        slider.setBounds(25,y+20,350,40);
        mainPanel.add(slider);

        slider.setMajorTickSpacing(majorTickSpacing);
        slider.setMinorTickSpacing(minorTickSpacing);

        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                sliderInfo[index] = slider.getValue();
                subj.setOptions(sliderInfo, colorInfo);
            }
        });

        return slider;
    }

    /**
     * Helper method for making color buttons
     *
     * @param index  index at which the color is stored
     * @param text   label/text indicating the where the color is used
     * @param y      y position of the button in the panel
     * @param width  width of the button
     * @param height height of the button
     */
    private void makeColorButton(int index, String text, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(50, y, width, height);
        mainPanel.add(button);

        // Create a panel to display the selected color
        JPanel colorPanel = new JPanel();
        colorPanel.setPreferredSize(new Dimension(100, 100));
        colorPanel.setBackground(Color.WHITE); // Default color
        mainPanel.add(colorPanel);

        // Add an ActionListener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the color chooser dialog
                Color selectedColor = JColorChooser.showDialog(mainPanel, "Pick a Color", colorPanel.getBackground());

                // If a color is selected, update the panel's background color
                if (selectedColor != null) {
                    colorInfo[index] = selectedColor;
                    subj.setOptions(sliderInfo, colorInfo);
                }
            }
        });
    }

}
