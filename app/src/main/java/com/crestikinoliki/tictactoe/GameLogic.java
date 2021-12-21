package com.crestikinoliki.tictactoe;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameLogic {
    private int[][] gameBoard;

    private int player = 1;

    private Button playAgain, homebtn;
    private TextView playerTurn;

    private String[] playerNames = {"Игрок 1", "Игрок 2"};

    private int[] winType = {-1,-1,-1};

    GameLogic(){
        gameBoard = new int[3][3];
        for (int r=0; r<3;r++){
            for (int c =0;c<3; c++){
                gameBoard[r][c]=0;
            }
        }
    }

    public boolean updateGameBoard(int row, int col){
        if (gameBoard[row-1][col-1] == 0){
            gameBoard[row -1][col-1] = player;

            if (player ==1){
                playerTurn.setText(("Ходит " + playerNames[1]));
                playerTurn.setTextColor(Color.parseColor("#FFCC0000"));

            }else {
                playerTurn.setText(("Ходит " + playerNames[0]));
                playerTurn.setTextColor(Color.parseColor("#FF0099CC"));
            }

            return true;
        }else {
            return false;
        }
    }

    public boolean winnerCheck(){
        boolean isWinner = false;

        for (int r=0;r<3;r++){
            if (gameBoard[r][0] == gameBoard[r][1] && gameBoard[r][0] == gameBoard[r][2] && gameBoard[r][0]!=0){
                winType = new int[]{r,0,1};
                isWinner = true;
            }
        }

        for (int c=0;c<3;c++){
            if (gameBoard[0][c] == gameBoard[1][c] &&
                    gameBoard[0][c] == gameBoard[2][c] &&
                    gameBoard[0][c]!=0){
                winType = new int[]{0,c,2};
                isWinner = true;
            }
        }

        //negotive diagonal check (winType == 3)
        if (gameBoard[0][0] == gameBoard[1][1] &&
                gameBoard[0][0] == gameBoard[2][2] &&
                gameBoard[0][0]!=0){
            winType = new int[]{0,2,3};
            isWinner = true;
        }

        //positive diagonal check (winType == 4)
        if (gameBoard[2][0] == gameBoard[1][1] &&
                gameBoard[2][0] == gameBoard[0][2] &&
                gameBoard[2][0]!=0){
            winType = new int[]{0,2,4};
            isWinner = true;
        }

        int boardFilled = 0;
        for(int r=0; r<3;r++){
            for (int c =0; c<3;c++){
                if(gameBoard[r][c]!=0)
                    boardFilled++;
            }
        }

        if (isWinner){
            playAgain.setVisibility(View.VISIBLE);
            homebtn.setVisibility(View.VISIBLE);
            playerTurn.setText(("Победил " + playerNames[player-1] + " !!!"));
            playerTurn.setTextColor(Color.parseColor("#00FF00"));

            return true;
        } else if (boardFilled == 9){
            playAgain.setVisibility(View.VISIBLE);
            homebtn.setVisibility(View.VISIBLE);
            playerTurn.setText(("Ничья!!!"));
            playerTurn.setTextColor(Color.parseColor("#00FF00"));
            winType = new int[]{-1,-1,-1};
            return true;
        }else {
            return false;
        }

    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public void resetGame(){
        for (int r=0; r<3;r++){
            for (int c =0;c<3; c++){
                gameBoard[r][c]=0;
            }
        }

        player = 1;
        playAgain.setVisibility(View.GONE);
        homebtn.setVisibility(View.GONE);

        playerTurn.setText(("Ходит " + playerNames[0]));
        playerTurn.setTextColor(Color.parseColor("#FF0099CC"));
    }

    public void setPlayAgain(Button playAgain) {
        this.playAgain = playAgain;
    }

    public void setHomebtn(Button homebtn) {
        this.homebtn = homebtn;
    }

    public void setPlayerTurn(TextView playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setPlayerNames(String[] playerNames) {
        if (!playerNames[0].equals(" "))
        this.playerNames = playerNames;
    }

    public int[] getWinType() {
        return winType;
    }
}
