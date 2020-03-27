package Screen;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author phamq
 */
public class Reversi {

    private int rows;
    private int cols;
    private Cell[][] board;
    private int numberOfMoves;

    public Reversi(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        newGame();
    }

    public Reversi(Reversi obj) {
        int x, y, z;
        this.rows = this.cols = obj.getBoard().length;
        board = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            board[i] = new Cell[cols];
            for (int j = 0; j < cols; j++) {
                x = obj.getBoard()[i][j].getX();
                y = obj.getBoard()[i][j].getY();
                z = obj.getBoard()[i][j].getValue();
                
                board[i][j] = new Cell(x, y, z);
            }
        }
    }
    
    

    public Cell[][] getBoard() {
        return board;
    }

    public void newGame() {
        board = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            board[i] = new Cell[cols];
            for (int j = 0; j < cols; j++) {
                if ((i == rows / 2 - 1 && j == rows / 2 - 1) || (i == rows / 2 && j == rows / 2)) {
                    board[i][j] = new Cell(j, i, 1);
                } else if ((i == rows / 2 - 1 && j == rows / 2) || (i == rows / 2 && j == rows / 2 - 1)) {
                    board[i][j] = new Cell(j, i, 2);
                } else if ((i == rows / 2 - 2 && j == rows / 2) || (i == rows / 2 - 1 && j == rows / 2 + 1)
                        || (i == rows / 2 && j == rows / 2 - 2) || (i == rows / 2 + 1 && j == rows / 2 - 1)) {
                    board[i][j] = new Cell(j, i, 3);
                } else {
                    board[i][j] = new Cell(j, i, 0);
                }

            }
        }
    }

    public int move(int xCor, int yCor, boolean isChange, int newValue, int oldValue) {
        int cont, st1 = 0, st2 = 0, status = -1;

        //check from top to bot
        if ((xCor + 1 < rows) && board[xCor + 1][yCor].getValue() == oldValue) {
            cont = xCor;

            while ((cont < rows) && (st2 != -1) && (st1 != 2)) {
                cont++;
                if (cont < rows) {
                    if (board[cont][yCor].getValue() == oldValue) {
                        st1 = 1;
                    } else if (board[cont][yCor].getValue() == newValue) {
                        st1 = 2;
                    } else {
                        st2 = -1;
                    }
                }
            }

            if (st1 == 2) {
                numberOfMoves += cont - xCor - 1;
                if (isChange) {
                    for (int i = xCor; i < cont; i++) {
                        board[i][yCor].setValue(newValue);
                    }
                    status = 0;
                }
            }

            st1 = 0;
            st2 = 0;
        }

        //check from bot to top
        if ((xCor - 1 >= 0) && board[xCor - 1][yCor].getValue() == oldValue) {
            cont = xCor;

            while ((cont >= 0) && (st2 != -1) && (st1 != 2)) {
                cont--;
                if (cont >= 0) {
                    if (board[cont][yCor].getValue() == oldValue) {
                        st1 = 1;
                    } else if (board[cont][yCor].getValue() == newValue) {
                        st1 = 2;
                    } else {
                        st2 = -1;
                    }
                }
            }

            if (st1 == 2) {
                numberOfMoves += xCor - cont - 1;
                if (isChange) {
                    for (int i = xCor; i > cont; i--) {
                        board[i][yCor].setValue(newValue);
                    }
                    status = 0;
                }
            }

            st1 = 0;
            st2 = 0;
        }

        //check from left to right
        if ((yCor + 1 < rows) && board[xCor][yCor + 1].getValue() == oldValue) {
            cont = yCor;

            while ((cont < rows) && (st2 != -1) && (st1 != 2)) {
                cont++;
                if (cont < rows) {
                    if (board[xCor][cont].getValue() == oldValue) {
                        st1 = 1;
                    } else if (board[xCor][cont].getValue() == newValue) {
                        st1 = 2;
                    } else {
                        st2 = -1;
                    }
                }
            }

            if (st1 == 2) {
                numberOfMoves += cont - yCor - 1;
                if (isChange) {
                    for (int i = yCor; i < cont; i++) {
                        board[xCor][i].setValue(newValue);
                    }
                    status = 0;
                }
            }

            st1 = 0;
            st2 = 0;
        }

        //check from right to left
        if ((yCor - 1 > 0) && board[xCor][yCor - 1].getValue() == oldValue) {
            cont = yCor;

            while ((cont >= 0) && (st2 != -1) && (st1 != 2)) {
                cont--;
                if (cont >= 0) {
                    if (board[xCor][cont].getValue() == oldValue) {
                        st1 = 1;
                    } else if (board[xCor][cont].getValue() == newValue) {
                        st1 = 2;
                    } else {
                        st2 = -1;
                    }
                }
            }

            if (st1 == 2) {
                numberOfMoves += yCor - cont - 1;
                if (isChange) {
                    for (int i = yCor; i > cont; i--) {
                        board[xCor][i].setValue(newValue);
                    }
                    status = 0;
                }
            }

            st1 = 0;
            st2 = 0;
        }

        //check from top left to bot right
        if ((xCor + 1 < rows) && (yCor + 1 < rows) && (board[xCor + 1][yCor + 1].getValue() == oldValue)) {
            int x = xCor;
            int y = yCor;

            while (x < rows && y < rows && st2 != -1 && st1 != 2) {
                x++;
                y++;
                if (x < rows && y < rows) {
                    if (board[x][y].getValue() == oldValue) {
                        st1 = 1;
                    } else if (board[x][y].getValue() == newValue) {
                        st1 = 2;
                    } else {
                        st2 = -1;
                    }
                }
            }

            if (st1 == 2) {
                numberOfMoves += x - xCor - 1;
                if (isChange) {
                    while (x >= xCor && y >= yCor) {
                        x--;
                        y--;
                        if(x >= xCor && y >= yCor) board[x][y].setValue(newValue);
                    }
                    status = 0;
                }
            }

            st1 = 0;
            st2 = 0;
        }

        //check from bot right to top left
        if ((xCor - 1 >= 0) && (yCor - 1 >= 0) && (board[xCor - 1][yCor - 1].getValue() == oldValue)) {
            int x = xCor;
            int y = yCor;

            while (x >= 0 && y >= 0 && st2 != -1 && st1 != 2) {
                x--;
                y--;
                if (x >= 0 && y >= 0) {
                    if (board[x][y].getValue() == oldValue) {
                        st1 = 1;
                    } else if (board[x][y].getValue() == newValue) {
                        st1 = 2;
                    } else {
                        st2 = -1;
                    }
                }
            }
            if (st1 == 2) {
                numberOfMoves += xCor - x - 1;
                if (isChange) {
                    while (x <= xCor && y <= yCor) {
                        x++;
                        y++;
                        if(x <= xCor && y <= yCor) board[x][y].setValue(newValue);
                    }
                    status = 0;
                }
            }

            st1 = 0;
            st2 = 0;
        }

        //check from top right to bot left
        if ((xCor + 1 < rows) && (yCor - 1 >= 0) && (board[xCor + 1][yCor - 1].getValue() == oldValue)) {
            int x = xCor;
            int y = yCor;

            while (x < rows && y >= 0 && st2 != -1 && st1 != 2) {
                x++;
                y--;
                if (x < rows && y >= 0) {
                    if (board[x][y].getValue() == oldValue) {
                        st1 = 1;
                    } else if (board[x][y].getValue() == newValue) {
                        st1 = 2;
                    } else {
                        st2 = -1;
                    }
                }
            }

            if (st1 == 2) {
                numberOfMoves += x - xCor - 1;
                if (isChange) {
                    while (x >= xCor && y <= yCor) {
                        x--;
                        y++;
                        if(x >= xCor && y <= yCor) board[x][y].setValue(newValue);
                    }
                    status = 0;
                }
            }

            st1 = 0;
            st2 = 0;
        }

        //check from bot left to top right
        if ((xCor - 1 >= 0) && (yCor + 1 < rows) && (board[xCor - 1][yCor + 1].getValue() == oldValue)) {
            int x = xCor;
            int y = yCor;

            while (x >= 0 && y < rows && st2 != -1 && st1 != 2) {
                x--;
                y++;
                if (x >= 0 && y < rows) {
                    if (board[x][y].getValue() == oldValue) {
                        st1 = 1;
                    } else if (board[x][y].getValue() == newValue) {
                        st1 = 2;
                    } else {
                        st2 = -1;
                    }
                }
            }

            if (st1 == 2) {
                numberOfMoves += xCor - x - 1;
                if (isChange) {
                    while (x <= xCor && y >= yCor) {
                        x++;
                        y--;
                        if(x <= xCor && y >= yCor) board[x][y].setValue(newValue);
                    }
                    status = 0;
                }
            }

            st1 = 0;
            st2 = 0;
        }

        return status;
    }
    
    //computer play
    public int computer() {
        boolean change = false;
        int max = 0;
        int xmax = 0, ymax = 0;
        
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(board[i][j].getValue() == 3) {
                    this.numberOfMoves = 0;
                    move(i, j, change, 2, 1);
                    if(this.numberOfMoves > max) {
                        max = this.numberOfMoves;
                        xmax = i;
                        ymax = j;
                    }
                }
            }
        }
        
        if (max == 0) return -1;
        
        change = true;
        move(xmax, ymax, change, 2, 1);
        return 0;
    }

    //player1 play
    public int player1(int xCor, int yCor) {

        int status;
        boolean change = false;
        this.numberOfMoves = 0;

        move(xCor, yCor, change, 1, 2);

        change = true;
        status = move(xCor, yCor, change, 1, 2);

        return status;
    }

    //player2 play
    public int player2(int xCor, int yCor) {

        int status;
        boolean change = false;
        this.numberOfMoves = 0;

        move(xCor, yCor, change, 2, 1);

        change = true;
        status = move(xCor, yCor, change, 2, 1);

        return status;
    }

    //find suggest for user 1
    public void findSuggest1() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if (board[i][j].getValue() == 3) {
                    this.numberOfMoves = 0;
                    move(i, j, false, 1, 2);
                    if (this.numberOfMoves == 0) {
                        board[i][j].setValue(0);
                    }
                } else if (board[i][j].getValue() == 0){
                    this.numberOfMoves = 0;
                    move(i, j, false, 1, 2);
                    if (this.numberOfMoves > 0) {
                        board[i][j].setValue(3);
                    }
                }
            }
        }
    }

    //find suggest for user 2
    public void findSuggest2() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if (board[i][j].getValue() == 3) {
                    this.numberOfMoves = 0;
                    move(i, j, false, 2, 1);
                    if (this.numberOfMoves == 0) {
                        board[i][j].setValue(0);
                    }
                } else if (board[i][j].getValue() == 0){
                    this.numberOfMoves = 0;
                    move(i, j, false, 2, 1);
                    if (this.numberOfMoves > 0) {
                        board[i][j].setValue(3);
                    }
                }
            }
        }
    }
    
    //control value
    public void controlElement(int arr[]) {
        int black = 0;
        int white = 0;
        int suggest = 0;
        int point = 0;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < rows; j++) {
                int item = board[i][j].getValue();
                if(item == 0) point++;
                else if(item == 1) black++;
                else if(item == 2) white++;
                else if(item == 3) suggest++;
            }
        }
        
        arr[0] = point; arr[1] = black; arr[2] = white; arr[3] = suggest;
    }
    
    //end game or not
    public int endOfGame() {
        int[] arr = new int[4];
        
        controlElement(arr);
        int point = arr[0], black = arr[1], white = arr[2], suggest = arr[3];
        if(suggest == 0) {
            if(black > white) return 0;
            else if(white > black) return 1;
            else return 2;
        }
        
        return -1;
    }
}
