/**
 * Name : Nabeel Farooqui
 * PennKey : nabeelf
 * Recitation : 213
 * 
 * Execution: java Connect 4
 *
 * This program uses two objects, "discs" and a "game board" that stores discs,
 * and the PennDraw library to create a Connect 4 game, with the options of
 * 1 player and 2 player mode.
 */

public class Connect4 {
    public static void main(String[] args) {
        
        //create new gameboard
        GameBoard b = new GameBoard();
        
        /*
         //test disc
         Disc d = new Disc(0.5, 0.43);
         d.drawYellowDisc();
         d.drawRedDisc();
         */
        
        //set initial restart condition to true so it enters while loop
        boolean restart = true;
        
        //this loops the whole game over from start screen if restart is true
        while (restart) {
            
            //set to false so it doesn't re-loop when the game isn't yet over
            restart = false;
            
            //show the starting screen, with options for 1 and 2 player mode
            showStartScreen();
            
            //variable used to exit the start screen and enter 1/2 player mode
            int numPlayers = 0;
            
            //check if the mouse is clicked to see which mode is selected
            while (numPlayers == 0) {
                if (PennDraw.mousePressed()) {
                    
                    //catch extra clicks and do nothing, then get coordinates
                    while (PennDraw.mousePressed()) {
                        continue;
                    }
                    double x = PennDraw.mouseX();
                    double y = PennDraw.mouseY();
                    
                    //if area of 1/2 player box display clicked, set numPlayers
                    if (x >= 0.15 && x <= 0.45 && y >= 0.4 && y <= 0.6) {
                        numPlayers = 1;
                    }
                    
                    else if (x >= 0.65 && x <= 0.95 && y >= 0.4 && y <= 0.6) {
                        numPlayers = 2;
                    }
                }
            }
            
            //1 player mode with randomized computer opponent that's always red
            if (numPlayers == 1) {
                
                //clear start screen and draw gameboard
                PennDraw.clear();
                b.drawBoard();
                
                //randomly decide who goes first and display start message
                boolean isYellow = true;
                if (Math.random() > 0.5) {
                    isYellow = !isYellow;
                }
                if (isYellow) {
                    PennDraw.text(0.5, 0.95, "You Go First.");
                }
                else {
                    PennDraw.text(0.5, 0.95, "Computer Goes First. Click " +
                                  "anywhere to continue.");  
                }
                
                //declare variables for column and row indeces of disc
                int col;
                int row;
                
                //if board is not full, drop disc then check for winner
                while (!isFull(b) && !restart) {
                    
                    //if it's human's turn
                    if (isYellow) {
                        if (PennDraw.mousePressed()) {
                            double xCoord = PennDraw.mouseX();
                            
                            //only enter if x coordinate is on the board
                            if (xCoord >= 0.1 && xCoord <= 0.9) {
                                
                                //catch extra clicks and do nothing
                                while (PennDraw.mousePressed()) {
                                    continue;
                                }
                                
                                //get column from coordinate of click
                                col = coordToIndex(xCoord);
                                
                                //set row to -1 so it only enters if changed
                                row = -1;
                                
                                //execute drop function and set row to return
                                if (!isFull(b, col)) {
                                    row = b.dropAndGetRow(col, isYellow, 
                                                          isYellow);
                                    
                                    //alternate the boolean to change color
                                    isYellow = !isYellow;
                                    
                                    //check if someone won and display message
                                    if (row != -1) {
                                        if (didWin(b, col - 1, row)) {
                                            PennDraw.setPenColor(
                                                             PennDraw.WHITE);
                                            PennDraw.filledRectangle(0.5, 0.95, 
                                                                     0.6, 0.04);
                                            PennDraw.setPenColor(
                                                             PennDraw.BLACK);
                                            PennDraw.text(0.5, 0.95, "You" +
                                                          " win! Click " +
                                                          "anywhere to " +
                                                          "restart.");
                                            
                                            //restart game if mouse is pressed
                                            while (!restart) {
                                                if (PennDraw.mousePressed()) {
                                                    
                                                    //catch extra clicks
                                                    while (
                                                    PennDraw.mousePressed()) {
                                                        continue;
                                                    }
                                                    
                                                    PennDraw.clear();
                                                    reset(b);
                                                    restart = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    
                    //if it's computer's turn
                    else {
                        if (PennDraw.mousePressed()) {
                            while (PennDraw.mousePressed()) {
                                continue;
                            }
                            
                            //randomize computer's disc placement
                            col = (int) (Math.random() * 7 + 1);
                            row = -1;
                            
                            //drop if column isn't full
                            if (!isFull(b, col)) {
                                
                                //execute drop function and set row to return
                                row = b.dropAndGetRow(col, isYellow, isYellow);
                                
                                //alternate the boolean to change color
                                isYellow = !isYellow;
                                
                                //check if someone won and display message
                                if (row != -1) {
                                    if (didWin(b, col - 1, row)) {
                                        PennDraw.setPenColor(PennDraw.WHITE);
                                        PennDraw.filledRectangle(0.5, 0.95, 0.6,
                                                                 0.04);
                                        PennDraw.setPenColor(PennDraw.BLACK);
                                        PennDraw.text(0.5, 0.95, "Computer" +
                                                      " wins. Click " +
                                                      "anywhere to restart.");
                                        
                                        //restart if there's a winner
                                        while (!restart) {
                                            if (PennDraw.mousePressed()) {
                                                
                                                //catch extra clicks
                                                while (
                                                PennDraw.mousePressed()) {
                                                    continue;
                                                }
                                                
                                                PennDraw.clear();
                                                reset(b);
                                                restart = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                
                //display tie message if board is full
                if (isFull(b)) {
                    PennDraw.setPenColor(PennDraw.WHITE);
                    PennDraw.filledRectangle(0.5, 0.95, 0.6, 0.04);
                    PennDraw.setPenColor(PennDraw.BLACK);
                    PennDraw.text(0.5, 0.95, "It's a tie! Click anywhere to " +
                                  "restart.");
                }
                
                //if restart hasn't been called, set restart to true if clicked
                while (!restart) {
                    if (PennDraw.mousePressed()) {
                        
                        //catch extra clicks and do nothing
                        while (PennDraw.mousePressed()) {
                            continue;
                        }
                        
                        PennDraw.clear(); //clear the screen
                        reset(b); //reset the game board 2D array to empty
                        restart = true; //set to true so while loop will reloop
                    }
                }
            }
            
            //2 player mode
            else if (numPlayers == 2) {
                PennDraw.clear();
                b.drawBoard();
                
                /*
                 //tests
                 b.dropAndGetRow(0, true);
                 b.dropAndGetRow(1, false);
                 b.dropAndGetRow(7, true);
                 b.dropAndGetRow(1, true);
                 b.dropAndGetRow(2, true);
                 b.dropAndGetRow(8, true);
                 */
                
                //randomly decide who goes first and display start message
                boolean isYellow = true;
                if (Math.random() > 0.5) {
                    isYellow = !isYellow;
                }
                if (isYellow) {
                    
                    PennDraw.text(0.5, 0.95, "Yellow Goes First.");
                }
                else {
                    PennDraw.text(0.5, 0.95, "Red Goes First.");  
                }
                
                //declare variables for column and row indeces of disc
                int col;
                int row;
                
                //if board is not full, drop disc then check for winner
                while (!isFull(b) && !restart) {
                    if (PennDraw.mousePressed()) {
                        
                        double xCoord = PennDraw.mouseX();
                        if (xCoord >= 0.1 && xCoord <= 0.9) {
                            
                            //catch extra clicks and do nothing
                            while (PennDraw.mousePressed()) {
                                continue;
                            }
                            
                            /*
                             //tests
                             System.out.println(xCoord);
                             System.out.println(coordToIndex(xCoord));
                             */
                            
                            //get column from coordinate of click
                            col = coordToIndex(xCoord);
                            
                            //set row to -1 so it doesn't enter if not changed
                            row = -1;
                            
                            //execute drop function and set row to return
                            if (!isFull(b, col)) {
                                row = b.dropAndGetRow(col, isYellow);
                                
                                //alternate the boolean to change color
                                isYellow = !isYellow;
                                
                                //System.out.println("row" + row + "col" + col);
                                
                                //check if someone won and display message
                                if (row != -1) {
                                    if (didWin(b, col - 1, row)) {
                                        
                                        //check which color it is
                                        if (isYellow) {
                                            PennDraw.setPenColor(
                                                             PennDraw.WHITE);
                                            PennDraw.filledRectangle(0.5, 0.95, 
                                                                     0.4, 0.04);
                                            PennDraw.setPenColor(
                                                             PennDraw.BLACK);
                                            PennDraw.text(0.5, 0.95, "Red " +
                                                          "wins! Click " +
                                                          "anywhere to " +
                                                          "restart.");
                                            
                                            //restart if there's a winner
                                            while (!restart) {
                                                if (PennDraw.mousePressed()) {
                                                    
                                                    //catch extra clicks
                                                    while (
                                                    PennDraw.mousePressed()) {
                                                        continue;
                                                    }
                                                    
                                                    PennDraw.clear();
                                                    reset(b);
                                                    restart = true;
                                                }
                                            }
                                        }
                                        
                                        //if it's other color, display message
                                        else {
                                            PennDraw.setPenColor(
                                                             PennDraw.WHITE);
                                            PennDraw.filledRectangle(0.5, 0.95, 
                                                                     0.4, 0.04);
                                            PennDraw.setPenColor(
                                                             PennDraw.BLACK);
                                            PennDraw.text(0.5, 0.95, "Yellow " +
                                                          "wins! Click " +
                                                          "anywhere to " +
                                                          "restart.");
                                            
                                            //restart if there's a winner
                                            while (!restart) {
                                                if (PennDraw.mousePressed()) {
                                                    
                                                    //catch extra clicks
                                                    while (
                                                    PennDraw.mousePressed()) {
                                                        continue;
                                                    }
                                                    
                                                    PennDraw.clear();
                                                    reset(b);
                                                    restart = true;
                                                }
                                            }
                                        }
                                    }
                                } 
                            } 
                        }
                    }    
                }
                
                //display tie message if board is full
                if (isFull(b)) {
                    PennDraw.setPenColor(PennDraw.WHITE);
                    PennDraw.filledRectangle(0.5, 0.95, 0.4, 0.04);
                    PennDraw.setPenColor(PennDraw.BLACK);
                    PennDraw.text(0.5, 0.95, "It's a tie! Click anywhere to " +
                                  "restart.");
                }
                
                //if restart hasn't been called, set restart to true if clicked
                while (!restart) {
                    if (PennDraw.mousePressed()) {
                        
                        //catch extra clicks and do nothing
                        while (PennDraw.mousePressed()) {
                            continue;
                        }
                        
                        PennDraw.clear();
                        reset(b);
                        restart = true;
                    }
                }
            }
        }
    }
    
    /*
     * Description: Translates x-coordinate on the screen when mouse is clicked
     *              to a column on the game board using the math that was used
     *              to create the board itself
     * Input:  a double (the coordinate of the click)
     * Output: an int (the column)
     */
    public static int coordToIndex(double coord) {
        int index = (int) Math.ceil((coord - 0.1) * 70.0 / 8.0);
        return index;
    }
    
    /*
     * Description: Draws starting screen, with title of game and boxes for 
     *              1 and 2 player modes
     * Input:  none
     * Output: void, draws start screen as side effect
     */
    public static void showStartScreen() {
        PennDraw.setPenColor(0, 100, 255);
        PennDraw.filledRectangle(0.3, 0.5, 0.15, 0.1);
        PennDraw.filledRectangle(0.7, 0.5, 0.15, 0.1);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(0.5, 0.8, "Connect 4!");
        PennDraw.text(0.3, 0.5, "1-Player");
        PennDraw.text(0.7, 0.5, "2-Player");  
    }
    
    /*
     * Description: Checks to see if anyone won. Looks at four different cases, 
     *              only if the disc being checked fulfills case requirements.
     *              This method uses strategic iteration. First case is 
     *              horizontal, where the row of the recently inputted disc is
     *              checked for 4 in a row. The second case is diagonal with a 
     *              positive slope, where variables move diagonally down and to 
     *              the left to the bottom-most disc along the diagonal of the 
     *              disc we are checking for. These variables are used as
     *              indeces, and they iteratively move up the diagonal and check
     *              for 4 in a row. The same is done for the third case,
     *              diagonal with a negative slope. The final case is vertical,
     *              where the disc being checked is itreratively compared to the
     *              three (maximum) below it to see if there are 4 in a row.
     *              The program then returns a boolean showing whether or not
     *              there's a winner yet.
     * Input:  Object GameBoard, int column, and int row
     * Output: a boolean, true if someone won and false if not
     */
    public static boolean didWin(GameBoard b, int col, int row) {
        
        /*
         * horizontal case
         * entry conditions are if disc is next to a disc (on either side) of
         * the same color. conditional looks long because edge cases are 
         * accounted for
         */
        if ((col != 0 && b.board[row][col] != null && b.board[row][col - 1] != 
             null && b.board[row][col].isYellow == 
             b.board[row][col - 1].isYellow) || 
            (col != 6 && b.board[row][col] != null && 
             b.board[row][col + 1] != null && b.board[row][col].isYellow == 
             b.board[row][col + 1].isYellow)) {
            
            //System.out.println(row + " " + col);
            
            //count is number of links of contiguous same colored discs
            int count = 0;
            
            //iterate through row
            for (int i = 0; i < 6; i++) {
                
                //checks for a link of contiguous same colored discs
                if (b.board[row][i] != null && b.board[row][i + 1] != null && 
                    b.board[row][i].isYellow == b.board[row][i + 1].isYellow) {
                    
                    //System.out.println(row);
                    
                    //increase count
                    count++;
                    
                    //System.out.println(count);
                    
                    //only 3 links are needed for 4 in a row, return true if so
                    if (count == 3) {
                        return true;
                    }
                }
                
                //reset count if link does not exist at part of row looked at
                else {
                    count = 0;
                }
            }
        }
        
        /*
         * second case, positive diagonal, enter conditional if it is possible 
         * to move down it. then, move down it to bottomost disc in diagonal.
         * finally, iterate back up it to see if there are 4 in a row
         */
        if (row != 5 && col != 0) {
            int count = 0; 
            
            //r and c are temporary indeces that go up and down the diagonal
            int r = row;
            int c = col;
            while (b.board[r][c] != null && c != 0 && r != 5) {
                r++;
                c--;
            }
            
            //handle edge case of new disc being in the top right corner
            if (r == 0 && c == 6) {
                return false; //this case will always be false
            }
            
            //System.out.println("r " + r + ", c " + c);
            
            //iterate up diagonal chekcing for 4 in a row
            while (r != 0 && c != 6 && b.board[r][c] != null && 
                   b.board[r - 1][c + 1] != null) {
                r--;
                c++;
                if (b.board[row][col].isYellow == b.board[r][c].isYellow && 
                    b.board[r + 1][c - 1].isYellow == 
                    b.board[row][col].isYellow) {
                    count++;
                }
                //System.out.println("count " + count);
                if (count == 3) {
                    return true;
                }
            }
        }
        
        //negative diagonal, enter conditional if it is possible to move down it
        if (row != 5 && col != 6) {
            int count = 0;           
            int r = row;
            int c = col;
            while (b.board[r][c] != null && c != 6 && r != 5) {
                r++;
                c++;
            }
            
            //handle edge case of new disc being in the top left corner
            if (r == 0 && c == 0) {
                return false; 
            }
            
            //System.out.println("r " + r + ", c " + c);
            
            while (r != 0 && c != 0 && b.board[r][c] != null &&
                   b.board[r - 1][c - 1] != null) {
                r--;
                c--;
                if (b.board[row][col].isYellow == b.board[r][c].isYellow && 
                    b.board[r + 1][c + 1].isYellow == 
                    b.board[row][col].isYellow) {
                    count++;
                }
                
                //System.out.println("count " + count);
                
                if (count == 3) {
                    return true;
                }
            }
        }
        
        
        //last case, down, iterate down and check for 4 in a row
        if (row != 5 && b.board[row][col].isYellow == 
            b.board[row + 1][col].isYellow) {
            int count = 0;
            
            //only enter for loop if same colored discs are contiguous
            for (int i = row + 1; i < 6 && b.board[row][col].isYellow == 
                 b.board[i][col].isYellow; i++) {
                if (b.board[row][col].isYellow == b.board[i][col].isYellow) {
                    count++;
                }
                if (count == 3) {
                    return true;
                }
            }
            //System.out.println("vert");
        }
        return false; //if no case is satisfied, return false
    }
    
    /*
     * Description: checks to see if game board is full by checking to see
     *              if top row is full
     * Input:  object Gameboard
     * Output: a boolean (true if it's full)
     */
    public static boolean isFull(GameBoard b) {
        int count = 0;
        for (int i = 0; i < 7; i++) {
            if (b.board[0][i] != null) {
                count++;
            }
        }
        if (count == 7) {
            return true;
        }
        return false;
    }
    
    /*
     * Description: sees if a column if full by seeing if the top element of
     *              the column is full
     * Input:  object Gameboard, int column
     * Output: a boolean (true if it's full)
     */
    public static boolean isFull(GameBoard b, int col) {
        //System.out.println(col);
        if (b.board[0][col - 1] != null) {
            return true;
        }
        return false;
    }
    
    /*
     * Description: resets gameboard's 2d array and clears it
     * Input:  object Gameboard
     * Output: void, but changes gameboard as side effect
     */
    public static void reset(GameBoard b) {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                b.board[row][col] = null;
            }
        }
    }
}