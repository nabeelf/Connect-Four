/**
 * Name : Nabeel Farooqui
 * PennKey : nabeelf
 * Recitation : 213
 * 
 * Execution: java Disc
 *
 * This object is a disc with coordinates, a radius, and a boolean. It can draw
 * the disc (a circle) using PennDrqw, with the color depending on the boolean.
 * The disc is used by the GameBoard and Connect4 classes.
 */
public class Disc {
    private double radius;
    private double x;
    private double y;
    public boolean isYellow;
    
    //constructor
    public Disc(double x, double y) {
        radius = 0.05;
        this.x = x;
        this.y = y;
    }
    
    /*
     * Description: draws yellow disc and sets its boolean to true
     * Input: none
     * Output: void, draws disc
     */
    public void drawYellowDisc() {
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.filledRectangle(0.5, 0.95, 0.6, 0.04);
        PennDraw.setPenColor(PennDraw.YELLOW);
        PennDraw.filledCircle(x, y, radius);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.circle(x, y, radius);
        PennDraw.text(0.5, 0.95, "Red's Turn");
        isYellow = true;
    }
    
    /*
     * Description: same as last, but 1P version. drawn texts are different.
     * Input: none
     * Output: void, draws disc
     */
    public void drawYellowDisc(boolean yourTurn) {
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.filledRectangle(0.5, 0.95, 0.6, 0.04);
        PennDraw.setPenColor(PennDraw.YELLOW);
        PennDraw.filledCircle(x, y, radius);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.circle(x, y, radius);
        if (!yourTurn) {
            PennDraw.text(0.5, 0.95, "Your Turn");
        }
        else {
            PennDraw.text(0.5, 0.95, "Computer's Turn. Click anywhere " +
                          "to continue. ");
        }
        isYellow = true;
    }
    
    /*
     * Description: draws red disc and sets its boolean to false
     * Input: none
     * Output: void, draws disc
     */
    public void drawRedDisc() {
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.filledRectangle(0.5, 0.95, 0.6, 0.04);
        PennDraw.setPenColor(PennDraw.RED);
        PennDraw.filledCircle(x, y, radius);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.circle(x, y, radius);
        PennDraw.text(0.5, 0.95, "Yellow's Turn");
        isYellow = false;
    } 
    
    /*
     * Description: same as last, but 1P version. drawn texts are different.
     * Input: none
     * Output: void, draws disc
     */
    public void drawRedDisc(boolean yourTurn) {
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.filledRectangle(0.5, 0.95, 0.6, 0.04);
        PennDraw.setPenColor(PennDraw.RED);
        PennDraw.filledCircle(x, y, radius);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.circle(x, y, radius);
        if (!yourTurn) {
            PennDraw.text(0.5, 0.95, "Your Turn");
        }
        else {
            PennDraw.text(0.5, 0.95, "Computer's Turn. Click anywhere " +
                          "to continue. ");
        }
        isYellow = false;
    } 
}    