//package edu.lmu.cs.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A server for a network multi-player tic tac toe game.  Modified and
 * extended from the class presented in Deitel and Deitel "Java How to
 * Program" book.  I made a bunch of enhancements and rewrote large sections
 * of the code.  The main change is instead of passing *data* between the
 * client and server, I made a TTTP (tic tac toe protocol) which is totally
 * plain text, so you can test the game with Telnet (always a good idea.)
 * The strings that are sent in TTTP are:
 *
 *  Client -> Server           Server -> Client
 *  ----------------           ----------------
 *  MOVE <n>  (0 <= n <= 8)    WELCOME <char>  (char in {X, O})
 *  QUIT                       VALID_MOVE
 *                             OTHER_PLAYER_MOVED <n>
 *                             VICTORY
 *                             DEFEAT
 *                             TIE
 *                             MESSAGE <text>
 *
 * A second change is that it allows an unlimited number of pairs of
 * players to play.
 */
public class TicTacToeServer {

    /**
     * Runs the application. Pairs up clients that connect.
     */
    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(8901);
        System.out.println("Tic Tac Toe Server is Running");
        try {
            while (true) {
                Game game = new Game();
                Game.Player playerX = game.new Player(listener.accept(), 'X');
                Game.Player playerO = game.new Player(listener.accept(), 'O');
                playerX.setOpponent(playerO);
                playerO.setOpponent(playerX);
                game.currentPlayer = playerX;
                playerX.start();
                playerO.start();
            }
        } finally {
            listener.close();
        }
    }
}

/**
 * A two-player game.
 */
class Game {

    /**
     * A board has nine squares.  Each square is either unowned or
     * it is owned by a player.  So we use a simple array of player
     * references.  If null, the corresponding square is unowned,
     * otherwise the array cell stores a reference to the player that
     * owns it.
     */
    private Player[] board = {
        null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null, null};

    /**
     * The current player.
     */
    Player currentPlayer;

    /**
     * Returns whether the current state of the board is such that one
     * of the players is a winner.
     */
    public boolean hasWinner() {
        return 
            (board[0] != null && board[0] == board[1] && board[0] == board[2] && board[0] == board[3] && board[0] == board[4])
          ||(board[10] != null && board[10] == board[11] && board[10] == board[12] && board[10] == board[13] && board[10] == board[14])
          ||(board[20] != null && board[20] == board[21] && board[20] == board[22] && board[20] == board[23] && board[20] == board[24])
          ||(board[30] != null && board[30] == board[31] && board[30] == board[32] && board[30] == board[33] && board[30] == board[34])
          ||(board[40] != null && board[40] == board[41] && board[40] == board[42] && board[40] == board[43] && board[40] == board[44])
          ||(board[50] != null && board[50] == board[51] && board[50] == board[52] && board[50] == board[53] && board[50] == board[54])
          ||(board[60] != null && board[60] == board[61] && board[60] == board[62] && board[60] == board[63] && board[60] == board[64])
          ||(board[70] != null && board[70] == board[71] && board[70] == board[72] && board[70] == board[73] && board[70] == board[74])
          ||(board[80] != null && board[80] == board[81] && board[80] == board[82] && board[80] == board[83] && board[80] == board[84])
          ||(board[90] != null && board[90] == board[91] && board[90] == board[92] && board[90] == board[93] && board[90] == board[94])
          ||(board[1] != null && board[1] == board[2] && board[1] == board[3] && board[1] == board[4] && board[1] == board[5])
          ||(board[11] != null && board[11] == board[12] && board[11] == board[13] && board[11] == board[14] && board[11] == board[15])
          ||(board[21] != null && board[21] == board[22] && board[21] == board[23] && board[21] == board[24] && board[21] == board[25])
          ||(board[31] != null && board[31] == board[32] && board[31] == board[33] && board[31] == board[34] && board[31] == board[35])
          ||(board[41] != null && board[41] == board[42] && board[41] == board[43] && board[41] == board[44] && board[41] == board[45])
          ||(board[51] != null && board[51] == board[52] && board[51] == board[53] && board[51] == board[54] && board[51] == board[55])
          ||(board[61] != null && board[61] == board[62] && board[61] == board[63] && board[61] == board[64] && board[61] == board[65])
          ||(board[71] != null && board[71] == board[72] && board[71] == board[73] && board[71] == board[74] && board[71] == board[75])
          ||(board[81] != null && board[81] == board[82] && board[81] == board[83] && board[81] == board[84] && board[81] == board[85])
          ||(board[91] != null && board[91] == board[92] && board[91] == board[93] && board[91] == board[94] && board[91] == board[95])
          ||(board[2] != null && board[2] == board[3] && board[2] == board[4] && board[2] == board[5] && board[2] == board[6])
          ||(board[12] != null && board[12] == board[13] && board[12] == board[14] && board[12] == board[15] && board[12] == board[16])
          ||(board[22] != null && board[22] == board[23] && board[22] == board[24] && board[22] == board[25] && board[22] == board[26])
          ||(board[32] != null && board[32] == board[33] && board[32] == board[34] && board[32] == board[35] && board[32] == board[36])
          ||(board[42] != null && board[42] == board[43] && board[42] == board[44] && board[42] == board[45] && board[42] == board[46])
          ||(board[52] != null && board[52] == board[53] && board[52] == board[54] && board[52] == board[55] && board[52] == board[56])
          ||(board[62] != null && board[62] == board[63] && board[62] == board[64] && board[62] == board[65] && board[62] == board[66])
          ||(board[72] != null && board[72] == board[73] && board[72] == board[74] && board[72] == board[75] && board[72] == board[76])
          ||(board[82] != null && board[82] == board[83] && board[82] == board[84] && board[82] == board[85] && board[82] == board[86])
          ||(board[92] != null && board[92] == board[93] && board[92] == board[94] && board[92] == board[95] && board[92] == board[96])
          ||(board[3] != null && board[3] == board[4] && board[3] == board[5] && board[3] == board[6] && board[3] == board[7])
          ||(board[13] != null && board[13] == board[14] && board[13] == board[15] && board[13] == board[16] && board[13] == board[17])
          ||(board[23] != null && board[23] == board[24] && board[23] == board[25] && board[23] == board[26] && board[23] == board[27])
          ||(board[33] != null && board[33] == board[34] && board[33] == board[35] && board[33] == board[36] && board[33] == board[37])
          ||(board[43] != null && board[43] == board[44] && board[43] == board[45] && board[43] == board[46] && board[43] == board[47])
          ||(board[53] != null && board[53] == board[54] && board[53] == board[55] && board[53] == board[56] && board[53] == board[57])
          ||(board[63] != null && board[63] == board[64] && board[63] == board[65] && board[63] == board[66] && board[63] == board[67])
          ||(board[73] != null && board[73] == board[74] && board[73] == board[75] && board[73] == board[76] && board[73] == board[77])
          ||(board[83] != null && board[83] == board[84] && board[83] == board[85] && board[83] == board[86] && board[83] == board[87])
          ||(board[93] != null && board[93] == board[94] && board[93] == board[95] && board[93] == board[96] && board[93] == board[97])
          ||(board[4] != null && board[4] == board[5] && board[4] == board[6] && board[4] == board[7] && board[4] == board[8])
          ||(board[14] != null && board[14] == board[15] && board[14] == board[16] && board[14] == board[17] && board[14] == board[18])
          ||(board[24] != null && board[24] == board[25] && board[24] == board[26] && board[24] == board[27] && board[24] == board[28])
          ||(board[34] != null && board[34] == board[35] && board[34] == board[36] && board[34] == board[37] && board[34] == board[38])
          ||(board[44] != null && board[44] == board[45] && board[44] == board[46] && board[44] == board[47] && board[44] == board[48])
          ||(board[54] != null && board[54] == board[55] && board[54] == board[56] && board[54] == board[57] && board[54] == board[58])
          ||(board[64] != null && board[64] == board[65] && board[64] == board[66] && board[64] == board[67] && board[64] == board[68])
          ||(board[74] != null && board[74] == board[75] && board[74] == board[76] && board[74] == board[77] && board[74] == board[78])
          ||(board[84] != null && board[84] == board[85] && board[84] == board[86] && board[84] == board[87] && board[84] == board[88])
          ||(board[94] != null && board[94] == board[95] && board[94] == board[96] && board[94] == board[97] && board[94] == board[98])
          ||(board[5] != null && board[5] == board[6] && board[5] == board[7] && board[5] == board[8] && board[5] == board[9])
          ||(board[15] != null && board[15] == board[16] && board[15] == board[17] && board[15] == board[18] && board[15] == board[19])
          ||(board[25] != null && board[25] == board[26] && board[25] == board[27] && board[25] == board[28] && board[25] == board[29])
          ||(board[35] != null && board[35] == board[36] && board[35] == board[37] && board[35] == board[38] && board[35] == board[39])
          ||(board[45] != null && board[45] == board[46] && board[45] == board[47] && board[45] == board[48] && board[45] == board[49])
          ||(board[55] != null && board[55] == board[56] && board[55] == board[57] && board[55] == board[58] && board[55] == board[59])
          ||(board[65] != null && board[65] == board[66] && board[65] == board[67] && board[65] == board[68] && board[65] == board[69])
          ||(board[75] != null && board[75] == board[76] && board[75] == board[77] && board[75] == board[78] && board[75] == board[79])
          ||(board[85] != null && board[85] == board[86] && board[85] == board[87] && board[85] == board[88] && board[85] == board[89])
          ||(board[95] != null && board[95] == board[96] && board[95] == board[97] && board[95] == board[98] && board[95] == board[99]);
    }

    /**
     * Returns whether there are no more empty squares.
     */
    public boolean boardFilledUp() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Called by the player threads when a player tries to make a
     * move.  This method checks to see if the move is legal: that
     * is, the player requesting the move must be the current player
     * and the square in which she is trying to move must not already
     * be occupied.  If the move is legal the game state is updated
     * (the square is set and the next player becomes current) and
     * the other player is notified of the move so it can update its
     * client.
     */
    public synchronized boolean legalMove(int location, Player player) {
        if (player == currentPlayer && board[location] == null) {
            board[location] = currentPlayer;
            currentPlayer = currentPlayer.opponent;
            currentPlayer.otherPlayerMoved(location);
            return true;
        }
        return false;
    }

    /**
     * The class for the helper threads in this multithreaded server
     * application.  A Player is identified by a character mark
     * which is either 'X' or 'O'.  For communication with the
     * client the player has a socket with its input and output
     * streams.  Since only text is being communicated we use a
     * reader and a writer.
     */
    class Player extends Thread {
        char mark;
        Player opponent;
        Socket socket;
        BufferedReader input;
        PrintWriter output;

        /**
         * Constructs a handler thread for a given socket and mark
         * initializes the stream fields, displays the first two
         * welcoming messages.
         */
        public Player(Socket socket, char mark) {
            this.socket = socket;
            this.mark = mark;
            try {
                input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);
                output.println("WELCOME " + mark);
                output.println("MESSAGE Waiting for opponent to connect");
            } catch (IOException e) {
                System.out.println("Player died: " + e);
            }
        }

        /**
         * Accepts notification of who the opponent is.
         */
        public void setOpponent(Player opponent) {
            this.opponent = opponent;
        }

        /**
         * Handles the otherPlayerMoved message.
         */
        public void otherPlayerMoved(int location) {
            output.println("OPPONENT_MOVED " + location);
            output.println(
                hasWinner() ? "DEFEAT" : boardFilledUp() ? "TIE" : "");
        }

        /**
         * The run method of this thread.
         */
        public void run() {
            try {
                // The thread is only started after everyone connects.
                output.println("MESSAGE All players connected");

                // Tell the first player that it is her turn.
                if (mark == 'X') {
                    output.println("MESSAGE Your move");
                }

                // Repeatedly get commands from the client and process them.
                while (true) {
                    String command = input.readLine();
                    if (command.startsWith("MOVE")) {
                        int location = Integer.parseInt(command.substring(5));
                        if (legalMove(location, this)) {
                            output.println("VALID_MOVE");
                            output.println(hasWinner() ? "VICTORY"
                                         : boardFilledUp() ? "TIE"
                                         : "");
                        } else {
                            output.println("MESSAGE ?");
                        }
                    } else if (command.startsWith("QUIT")) {
                        return;
                    }
                }
            } catch (IOException e) {
                System.out.println("Player died: " + e);
            } finally {
                try {socket.close();} catch (IOException e) {}
            }
        }
    }
}