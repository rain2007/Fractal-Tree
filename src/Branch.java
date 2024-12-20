import java.awt.*;

/**
 * A record used to create Branch Objects
 *
 * @author Raingsey Tevy
 * @version 2024-12-05
 *
 * @param x1    x coordinate for the first point
 * @param y1    y coordinate for the first point
 * @param x2    x coordinate for the second point
 * @param y2    y coordinate for the second point
 * @param width thickness of the branch
 * @param red   red color component
 * @param green green color component
 * @param blue  blue color component
 */
public record Branch(int x1, int y1, int x2, int y2, float width, int red, int green, int blue) implements FractalElement {

    /**
     * Draws a round-ended Branch
     *
     * @param g Graphics object used to render the branch
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setColor(new Color(red, green, blue));
        g2.drawLine(x1, y1, x2, y2);
    }

}
