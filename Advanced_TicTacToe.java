import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Tayyab
 */
public class Advanced_TicTacToe extends javax.swing.JFrame implements ActionListener {

    /**
     *
     */
    public javax.swing.JButton button[][] = new javax.swing.JButton[3][3]; // Array of Buttons

    private String currentPlayer = "X";
    private JFrame frame;

    /**
     * Creates new form Advanced_TicTacToe
     */
    public Advanced_TicTacToe() {
        initComponents();
        setSize(600, 500);
        setLocationRelativeTo(null);
        initializeButtons();
    }

    private void initializeButtons() {
        // Create button field and place it in the window
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                button[i][j] = new javax.swing.JButton(i + "/" + j);
                button[i][j].setText(null);
                button[i][j].setFont(new java.awt.Font("Segoe UI", 1, 48));
                button[i][j].addActionListener(this);
            }
        }

        // Add buttons to the panel jPanel3.

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                jPanel3.add(button[i][j]);
            }
        }
    }

    // This method will check if 3 given strings are equal to X
    public boolean equalsX(String a, String b, String c) {
        if (a == "X" && b == "X" && c == "X") {
            return true;
        }
        return false;
    }

    // This method will check if 3 given strings are equal to O
    public boolean equalsO(String a, String b, String c) {
        if (a == "O" && b == "O" && c == "O") {
            return true;
        }
        return false;
    }

    // This method will reset the Game as the name suggests
    public void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                button[i][j].setText(null);
                button[i][j].setEnabled(true);
            }
        }
    }

    // Tree bases algorithm that searches for the best path
    public int minimax(boolean isMaximizing) {
        if (isWinning() == 1 || isWinning() == -1 || isWinning() == 0) {
            int temp = isWinning();
            return temp;
        }
        if (isMaximizing == true) {
            int maxScore = -10;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (button[i][j].getText() == null) {
                        button[i][j].setText("X");
                        int score = minimax(false);
                        button[i][j].setText(null);
                        maxScore = Math.max(score, maxScore);
                    }
                }
            }
            return maxScore;
        } else {
            int minScore = 10;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (button[i][j].getText() == null) {
                        button[i][j].setText("O");
                        int score = minimax(true);
                        button[i][j].setText(null);
                        minScore = Math.min(score, minScore);
                    }
                }
            }
            return minScore;

        }
    }

    public void cpuMove() {

        int bestScore = 10;
        int besti = 0;// Row
        int bestj = 0;// Column
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (button[i][j].getText() == null) {
                    button[i][j].setText("O");
                    int score = minimax(true);
                    button[i][j].setText(null);
                    if (score < bestScore) {
                        bestScore = score;
                        besti = i;
                        bestj = j;
                    }
                }
            }
        }
        button[besti][bestj].setText("O");
        button[besti][bestj].setForeground(Color.BLUE);
        button[besti][bestj].setEnabled(false);
        if (isWinning() == 1) {
            if (JOptionPane.showConfirmDialog(frame, "Player X wins! Want to replay the game?", "Tic Tac Toe",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }
        } else if (isWinning() == -1) {
            if (JOptionPane.showConfirmDialog(frame, "Player O wins! Want to replay the game?", "Tic Tac Toe",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }

        } else if (isWinning() == 0) {
            if (JOptionPane.showConfirmDialog(frame, "Draw! Want to replay the game?", "Tic Tac Toe",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }

        }

    }

    public int isWinning() {

        // Checking for Rows
        for (int i = 0; i < 3; i++) {
            if (equalsX(button[i][0].getText(), button[i][1].getText(), button[i][2].getText())) {
                return 1;
            }
            if (equalsO(button[i][0].getText(), button[i][1].getText(), button[i][2].getText())) {
                return -1;
            }
        }

        // Checking for Columns
        for (int j = 0; j < 3; j++) {
            if (equalsX(button[0][j].getText(), button[1][j].getText(), button[2][j].getText())) {
                return 1;
            }
            if (equalsO(button[0][j].getText(), button[1][j].getText(), button[2][j].getText())) {
                return -1;
            }
        }

        // Checking for Diagonals
        if (equalsX(button[0][0].getText(), button[1][1].getText(), button[2][2].getText())) {
            return 1;
        }
        if (equalsO(button[0][0].getText(), button[1][1].getText(), button[2][2].getText())) {
            return -1;
        }

        if (equalsX(button[0][2].getText(), button[1][1].getText(), button[2][0].getText())) {
            return 1;
        }
        if (equalsO(button[0][2].getText(), button[1][1].getText(), button[2][0].getText())) {
            return -1;
        }

        // Checking if board is filled
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (button[i][j].getText() == null) {
                    return -2;
                }
            }
        }

        // Returning 0 as Draw becuase if X and O didn't win and the board is empty then
        // it is a draw
        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (e.getSource() == button[i][j]) {
                    // the pressed button will perform the following code:
                    button[i][j].setText(currentPlayer);
                    button[i][j].setForeground(Color.RED);
                    button[i][j].setEnabled(false);
                    if (isWinning() == 1) {
                        if (JOptionPane.showConfirmDialog(frame, "Player X wins! Want to replay the game?",
                                "Tic Tac Toe",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                            resetGame();
                        } else {
                            System.exit(0);
                        }
                        return;
                    } else if (isWinning() == -1) {
                        if (JOptionPane.showConfirmDialog(frame, "Player O wins! Want to replay the game?",
                                "Tic Tac Toe",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                            resetGame();
                        } else {
                            System.exit(0);
                        }
                        return;
                    } else if (isWinning() == 0) {
                        if (JOptionPane.showConfirmDialog(frame, "Draw! Want to replay the game?", "Tic Tac Toe",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                            resetGame();
                        } else {
                            System.exit(0);
                        }
                        return;
                    }

                    cpuMove();

                }
            } // inner loop ends
        } // outer loop ends
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel2.setBackground(new java.awt.Color(255, 204, 204));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tic Tac Toe");
        jPanel2.add(jLabel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setLayout(new java.awt.GridLayout(3, 3, 2, 2));
        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Advanced_TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Advanced_TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Advanced_TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Advanced_TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Advanced_TicTacToe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
