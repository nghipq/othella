/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screen;

import database.DBManagement;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author phamq
 */
public class PnBoard extends javax.swing.JPanel {

    private int rows;
    private int cols;
    private Color bgcolor;
    public Reversi board;
    public ArrayList<Reversi> BoardGame = new ArrayList<>();
    private int mode;
    private int n = 0;
    private boolean click = false;

    private Image black = null;
    private Image[] blackFlip = new Image[4];

    private Image white = null;
    private Image[] whiteFlip = new Image[4];

    private Image suggest = null;

    private static JButton[] cells;

    private int Player1Score = 2;
    private int Player2Score = 2;

    private int turn = 0;

    private String player1Name;
    private String player2Name;

    /**
     * Creates new form Pnboard
     */
    public PnBoard(int rows, int cols, Color bgcolor, int mode) {
        initComponents();
        init();

        this.rows = rows;
        this.cols = cols;
        this.bgcolor = bgcolor;
        this.mode = mode;
        board = new Reversi(rows, cols);
        BoardGame.add(new Reversi(board));

        Pnboard.setLayout(new GridLayout(rows, cols));
        cells = new JButton[rows * cols];

        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[k] = new JButton();
                cells[k].setSize(50, 50);
                cells[k].setBackground(bgcolor);
                cells[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cells[k].addActionListener(new Action());
                int item = board.getBoard()[i][j].getValue();
                if (item == 1) {
                    cells[k].setIcon(new ImageIcon(black));
                } else if (item == 2) {
                    cells[k].setIcon(new ImageIcon(white));
                } else if (item == 3) {
                    cells[k].setIcon(new ImageIcon(suggest));
                }

                Pnboard.add(cells[k]);
                k++;
            }
        }

        JLBPlayer1.setText("Player 1: " + this.Player1Score);
        if (mode == 1) {
            JLBPlayer2.setText("Player 2: " + this.Player2Score);
        } else {
            JLBPlayer2.setText("Computer: " + this.Player2Score);
        }
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }

    public void saveDB(String name) {
        try {
            DBManagement conn = new DBManagement();
            Statement st;
            ResultSet rs;
            String sql = "";
            
            sql = "select * from `player` where `name` = '" + name + "'";
            st = conn.getConn().createStatement();
            rs = st.executeQuery(sql);
            if(rs.next()) {
                String point = String.format("%d", Integer.parseInt(rs.getString("point")) + 1);
                sql = "update `player` set `point` = '" + point + "'";
                st.execute(sql);
            } else {
                sql = "insert into `player`(`name`, `point`) values ('" + name + "', '1')";
                st.execute(sql);
            }
            conn.closeConnection();
            
        } catch (SQLException ex) {
            Logger.getLogger(PnBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadBoard(int n) {
        int k = 0;
        Reversi oldBoard = BoardGame.get(BoardGame.size() - 1);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                int item = board.getBoard()[i][j].getValue();
                int oldItem = oldBoard.getBoard()[i][j].getValue();

                if (item == 1) {
                    if (oldItem == 2) {
                        cells[k].setIcon(new ImageIcon(whiteFlip[n]));
                    } else {
                        cells[k].setIcon(new ImageIcon(black));
                    }
                } else if (item == 2) {
                    if (oldItem == 1) {
                        cells[k].setIcon(new ImageIcon(blackFlip[n]));
                    } else {
                        cells[k].setIcon(new ImageIcon(white));
                    }
                } else if (item == 3) {
                    cells[k].setIcon(new ImageIcon(suggest));
                } else {
                    cells[k].setIcon(null);
                }
                k++;
            }
        }

        JLBPlayer1.setText("Player 1: " + this.Player1Score);
        if (mode == 1) {
            JLBPlayer2.setText("Player 2: " + this.Player2Score);
        } else {
            JLBPlayer2.setText("Computer: " + this.Player2Score);
        }
        
        if(turn % 2 == 0) {
            ImgTurn.setIcon(new ImageIcon(black));
        } else {
            ImgTurn.setIcon(new ImageIcon(white));
        }
    }

    public void play() {
        player1Name = JOptionPane.showInputDialog(this, "Input player 1 name: ", "Player1").toString();
        System.out.println(player1Name);

        if (mode == 1) {
            player2Name = JOptionPane.showInputDialog(this, "Input player 2 name: ", "Player2").toString();
        }
    }

    public void init() {

        try {
            black = whiteFlip[3] = ImageIO.read(getClass().getResource("/img/den.png"));
        } catch (Exception e) {
        };

        try {
            blackFlip[0] = ImageIO.read(getClass().getResource("/img/den2.png"));
        } catch (Exception e) {
        };

        try {
            blackFlip[1] = ImageIO.read(getClass().getResource("/img/den3.png"));
        } catch (Exception e) {
        };

        try {
            blackFlip[2] = ImageIO.read(getClass().getResource("/img/den4.png"));
        } catch (Exception e) {
        };

        try {
            white = blackFlip[3] = ImageIO.read(getClass().getResource("/img/trang.png"));
        } catch (Exception e) {
        };

        try {
            whiteFlip[0] = ImageIO.read(getClass().getResource("/img/trang2.png"));
        } catch (Exception e) {
        };

        try {
            whiteFlip[1] = ImageIO.read(getClass().getResource("/img/trang3.png"));
        } catch (Exception e) {
        };

        try {
            whiteFlip[2] = ImageIO.read(getClass().getResource("/img/trang4.png"));
        } catch (Exception e) {
        };

        try {
            suggest = ImageIO.read(getClass().getResource("/img/bong.png"));
        } catch (Exception e) {
        };
    }

    class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 64; i++) {
                if (e.getSource() == cells[i]) {
                    int isEnd;
                    int xCor = i / 8;
                    int yCor = i % 8;
                    int ct = -100;

                    if (board.getBoard()[xCor][yCor].getValue() == 3) {

                        if (turn % 2 == 0) {
                            ct = board.player1(xCor, yCor);
                            board.findSuggest2();
                            if (ct == 0) {
                                turn++;
                            }
                            if (mode == 0) {
                                ct = board.computer();
                                board.findSuggest1();
                                if (ct == 0) {
                                    turn++;
                                }
                            }

                        } else {
                            ct = board.player2(xCor, yCor);
                            board.findSuggest1();
                            isEnd = board.endOfGame();
                            if (ct == 0) {
                                turn++;
                            }
                        }
                        click = true;
                    }

                    int[] arr = new int[4];
                    board.controlElement(arr);
                    Player1Score = arr[1];
                    Player2Score = arr[2];
                    JLBPlayer1.setText("Player 1: " + Player1Score);
                    if (mode == 1) {
                        JLBPlayer2.setText("Player 2: " + Player2Score);
                    } else {
                        JLBPlayer2.setText("Computer: " + Player2Score);
                    }

                    isEnd = board.endOfGame();
                    if (isEnd != -1) {
                        String result;
                        if (isEnd == 0) {
                            result = "Player 1 win!";
                            System.out.println(player1Name);
                            saveDB(player1Name);
                        } else if (isEnd == 1) {
                            if(mode == 1) {
                                System.out.println(player2Name);
                                saveDB(player2Name);
                            }
                            result = "Player 2 win!";
                        } else {
                            result = "DRAW";
                        }

                        int choice = JOptionPane.showConfirmDialog(Pnboard, result, "Do you want to play a new game?", JOptionPane.YES_NO_OPTION);
                        if (choice == 0) {
                            board.newGame();
                            Player1Score = 2;
                            Player2Score = 2;
                            loadBoard(3);
                        }
                    }
                }
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PnBorder = new javax.swing.JPanel();
        PnX = new javax.swing.JPanel();
        LBA = new javax.swing.JLabel();
        LBB = new javax.swing.JLabel();
        LBC = new javax.swing.JLabel();
        LBD = new javax.swing.JLabel();
        LBE = new javax.swing.JLabel();
        LBF = new javax.swing.JLabel();
        LBG = new javax.swing.JLabel();
        LBH = new javax.swing.JLabel();
        PnY = new javax.swing.JPanel();
        LB1 = new javax.swing.JLabel();
        LB2 = new javax.swing.JLabel();
        LB3 = new javax.swing.JLabel();
        LB4 = new javax.swing.JLabel();
        LB5 = new javax.swing.JLabel();
        LB6 = new javax.swing.JLabel();
        LB7 = new javax.swing.JLabel();
        LB8 = new javax.swing.JLabel();
        Pnboard = new javax.swing.JPanel();
        background = new javax.swing.JLabel();
        PnScore = new javax.swing.JPanel();
        LBTurn = new javax.swing.JLabel();
        ImgTurn = new javax.swing.JLabel();
        JLBPlayer1 = new javax.swing.JLabel();
        Img1 = new javax.swing.JLabel();
        JLBPlayer2 = new javax.swing.JLabel();
        Img2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        PnBorder.setBackground(new java.awt.Color(153, 51, 0));
        PnBorder.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(153, 51, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(102, 102, 102)));
        PnBorder.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PnBorder.setPreferredSize(new java.awt.Dimension(350, 350));
        PnBorder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PnX.setOpaque(false);
        PnX.setLayout(new java.awt.GridLayout(1, 8));

        LBA.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        LBA.setForeground(new java.awt.Color(255, 255, 255));
        LBA.setText("A");
        PnX.add(LBA);

        LBB.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        LBB.setForeground(new java.awt.Color(255, 255, 255));
        LBB.setText("B");
        PnX.add(LBB);

        LBC.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        LBC.setForeground(new java.awt.Color(255, 255, 255));
        LBC.setText("C");
        PnX.add(LBC);

        LBD.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        LBD.setForeground(new java.awt.Color(255, 255, 255));
        LBD.setText("D");
        PnX.add(LBD);

        LBE.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        LBE.setForeground(new java.awt.Color(255, 255, 255));
        LBE.setText("E");
        PnX.add(LBE);

        LBF.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        LBF.setForeground(new java.awt.Color(255, 255, 255));
        LBF.setText("F");
        PnX.add(LBF);

        LBG.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        LBG.setForeground(new java.awt.Color(255, 255, 255));
        LBG.setText("G");
        PnX.add(LBG);

        LBH.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        LBH.setForeground(new java.awt.Color(255, 255, 255));
        LBH.setText("H");
        PnX.add(LBH);

        PnBorder.add(PnX, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 290, 40));

        PnY.setOpaque(false);
        PnY.setLayout(new java.awt.GridLayout(8, 1));

        LB1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        LB1.setForeground(new java.awt.Color(255, 255, 255));
        LB1.setText("1");
        PnY.add(LB1);

        LB2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        LB2.setForeground(new java.awt.Color(255, 255, 255));
        LB2.setText("2");
        PnY.add(LB2);

        LB3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        LB3.setForeground(new java.awt.Color(255, 255, 255));
        LB3.setText("3");
        PnY.add(LB3);

        LB4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        LB4.setForeground(new java.awt.Color(255, 255, 255));
        LB4.setText("4");
        PnY.add(LB4);

        LB5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        LB5.setForeground(new java.awt.Color(255, 255, 255));
        LB5.setText("5");
        PnY.add(LB5);

        LB6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        LB6.setForeground(new java.awt.Color(255, 255, 255));
        LB6.setText("6");
        PnY.add(LB6);

        LB7.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        LB7.setForeground(new java.awt.Color(255, 255, 255));
        LB7.setText("7");
        PnY.add(LB7);

        LB8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        LB8.setForeground(new java.awt.Color(255, 255, 255));
        LB8.setText("8");
        PnY.add(LB8);

        PnBorder.add(PnY, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 40, 280));

        Pnboard.setPreferredSize(new java.awt.Dimension(280, 280));

        javax.swing.GroupLayout PnboardLayout = new javax.swing.GroupLayout(Pnboard);
        Pnboard.setLayout(PnboardLayout);
        PnboardLayout.setHorizontalGroup(
            PnboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        PnboardLayout.setVerticalGroup(
            PnboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );

        PnBorder.add(Pnboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 36, -1, -1));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/wood.jpg"))); // NOI18N
        PnBorder.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 350));

        PnScore.setBackground(new java.awt.Color(255, 255, 255));

        LBTurn.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        LBTurn.setForeground(new java.awt.Color(0, 102, 51));
        LBTurn.setText("Turn");

        ImgTurn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/den.png"))); // NOI18N

        JLBPlayer1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        JLBPlayer1.setText("Player 1:");

        Img1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/den.png"))); // NOI18N

        JLBPlayer2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        JLBPlayer2.setText("Player 2:");

        Img2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/trang.png"))); // NOI18N

        javax.swing.GroupLayout PnScoreLayout = new javax.swing.GroupLayout(PnScore);
        PnScore.setLayout(PnScoreLayout);
        PnScoreLayout.setHorizontalGroup(
            PnScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnScoreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LBTurn)
                    .addGroup(PnScoreLayout.createSequentialGroup()
                        .addComponent(Img1)
                        .addGap(3, 3, 3)
                        .addComponent(JLBPlayer1))
                    .addGroup(PnScoreLayout.createSequentialGroup()
                        .addComponent(Img2)
                        .addGap(3, 3, 3)
                        .addComponent(JLBPlayer2))
                    .addComponent(ImgTurn))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        PnScoreLayout.setVerticalGroup(
            PnScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnScoreLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(LBTurn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ImgTurn)
                .addGap(18, 18, 18)
                .addGroup(PnScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JLBPlayer1)
                    .addComponent(Img1))
                .addGap(18, 18, 18)
                .addGroup(PnScoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JLBPlayer2)
                    .addComponent(Img2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PnBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnScore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PnScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PnBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Img1;
    private javax.swing.JLabel Img2;
    private javax.swing.JLabel ImgTurn;
    private javax.swing.JLabel JLBPlayer1;
    private javax.swing.JLabel JLBPlayer2;
    private javax.swing.JLabel LB1;
    private javax.swing.JLabel LB2;
    private javax.swing.JLabel LB3;
    private javax.swing.JLabel LB4;
    private javax.swing.JLabel LB5;
    private javax.swing.JLabel LB6;
    private javax.swing.JLabel LB7;
    private javax.swing.JLabel LB8;
    private javax.swing.JLabel LBA;
    private javax.swing.JLabel LBB;
    private javax.swing.JLabel LBC;
    private javax.swing.JLabel LBD;
    private javax.swing.JLabel LBE;
    private javax.swing.JLabel LBF;
    private javax.swing.JLabel LBG;
    private javax.swing.JLabel LBH;
    private javax.swing.JLabel LBTurn;
    private javax.swing.JPanel PnBorder;
    private javax.swing.JPanel PnScore;
    private javax.swing.JPanel PnX;
    private javax.swing.JPanel PnY;
    private javax.swing.JPanel Pnboard;
    private javax.swing.JLabel background;
    // End of variables declaration//GEN-END:variables
}
