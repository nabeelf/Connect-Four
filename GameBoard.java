/**
 * Name : Nabeel Farooqui
 * PennKey : nabeelf
 * Recitation : 213
 * 
 * Execution: java GameBoard
 *
 * This object holds disc objects for the Connect4 class. It also draws the 
 * board using PennDraw and drops discs into it.
 */
public class GameBoard {
    public Disc[][] board; //2D Disc array used to model gameboard
    
    //constructor
    public GameBoard() {
        board = new Disc[6][7];
    }
    
    /*
     * Description: draws empty board with PennDraw
     * Input:  none
     * Output: void, draws board as side effect
     */
    public void drawBoard() {
        
        //draw columns (lines with a for loop)
        for (double i = 0.1; i <= 0.9; i += 8.0 / 60.0) {
            PennDraw.setPenColor(PennDraw.BLACK);
            PennDraw.line(0.1, i, 0.9, i);
        }
        
        //draw rows
        for (double i = 0.1; i <= 0.901; i += 8.0 / 70.0) {
            PennDraw.setPenColor(PennDraw.BLACK);
            PennDraw.line(i, 0.1, i, 0.9);
        }    
    }
    
    /*
     * Description: converts column to x coordinate of where to draw disc
     * Input:  int column
     * Output: double specifying x coordinate
     */
    public double colToXCoord(int col) {
        double coord = 0.045 + (8.0 / 70.0) * col;
        return coord;
    }
    
    /*
     * Description: converts row to y coordinate of where to draw disc
     * Input:  int row
     * Output: double specifying y coordinate
     */
    public double rowToYCoord(int row) {
        double coord = 0.82 - (8.0 / 60.0) * row;
        return coord;
    }
    
    /*
     * Description: drops disc into lowest possible row (highest indexed row) 
     *              that is still empty in the specified column and returns
     *              the row, draws disc on screen and adds it to 2D array
     * Input:  int column, boolean isYellow (this controls the color of disc)
     * Output: int row
     */
    public int dropAndGetRow(int col, boolean isYellow) {
        
        //System.out.println("reached");
        int row = 0;
        
        //loop through each row, starting from bottom
        for (row = 5; row >= 0; row--) {
            
            //System.out.println("for loop");
            
            //only execute drop if column is in range and space is null
            if (col > 0 && col < 8 && board[row][col - 1] == null) {
                //System.out.println(row + " " + col);
                
                //insert and draw yellow or red loop depending on input
                if (isYellow) {
                    Disc i = new Disc(colToXCoord(col), rowToYCoord(row));
                    
                    //need to subtract 1 so column #'s translate to indeces
                    board[row][col - 1] = i;
                    
                    //System.out.println(i.row);
                    
                    i.drawYellowDisc();
                    break; //break loop once disc is drawn
                }
                
                if (!isYellow) {
                    Disc i = new Disc(colToXCoord(col), rowToYCoord(row));
                    board[row][col - 1] = i;
                    
                    //System.out.println(i.row);
                    
                    i.drawRedDisc();
                    break;
                }
            }
        }
        return row;
    }   
    
    /*
     * Description: this method is the 1-player version of the overloaded 
     *              method. the method acts the same as the last one but
     *              the boolean "yourTurn" is used to draw different messages
     *              when drawing the disc object
     * Input:  int column, boolean isYellow, boolean yourTurn
     * Output: int row
     */
    public int dropAndGetRow(int col, boolean isYellow, boolean yourTurn) {
        
        //System.out.println("reached");
        
        int row = 0;
        
        //loop through each row, starting from bottom
        for (row = 5; row >= 0; row--) {
            
            //System.out.println("for loop");
            
            //only execute drop if column is in range and space is null
            if (col > 0 && col < 8 && board[row][col - 1] == null) {
                
                //System.out.println(row + " " + col);
                
                //insert and draw yellow or red loop depending on input
                if (isYellow) {
                    Disc i = new Disc(colToXCoord(col), rowToYCoord(row));
                    board[row][col - 1] = i;
                    
                    //System.out.println(i.row);
                    
                    i.drawYellowDisc(yourTurn); //only change from last method
                    break;
                }
                if (!isYellow) {
                    Disc i = new Disc(colToXCoord(col), rowToYCoord(row));
                    board[row][col - 1] = i;
                    
                    //System.out.println(i.row);
                    
                    i.drawRedDisc(yourTurn); //only change from last method
                    break;
                }
            }
        }
        return row;
    }  
}