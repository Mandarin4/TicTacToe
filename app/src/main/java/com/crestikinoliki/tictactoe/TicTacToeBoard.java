package com.crestikinoliki.tictactoe;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TicTacToeBoard extends View {

    private final int boardColor, XColor, OColor,winningLineColor;

    private final Paint paint = new Paint();

    private int cellSize = getWidth()/3;

    private final GameLogic game;

    private boolean winnerLine = false;


    public TicTacToeBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        game = new GameLogic();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.TicTacToeBoard,0,0);

        try {
           boardColor = a.getInteger(R.styleable.TicTacToeBoard_boardColor,0);
           XColor = a.getInteger(R.styleable.TicTacToeBoard_XColor,0);
           OColor = a.getInteger(R.styleable.TicTacToeBoard_OColor,0);
           winningLineColor = a.getInteger(R.styleable.TicTacToeBoard_winningLineColor,0);

        }finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int height){
        super.onMeasure(width,height);

        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());

        cellSize = dimension/3;

        setMeasuredDimension(dimension,dimension);
    }

    @Override
    protected void onDraw(Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        drawGameBoard(canvas);
        drawMarkers(canvas);

        if(winnerLine){
            paint.setColor(winningLineColor);
            drawWinningLine(canvas);
        }
    }

    private void drawMarkers(Canvas canvas) {
        for (int r=0; r<3;r++){
            for(int c=0;c<3;c++){
                if (game.getGameBoard()[r][c] != 0){
                    if (game.getGameBoard()[r][c] != 1){
                        drawO(canvas,r,c);
                    }else{
                        drawX(canvas,r,c);
                    }
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();

        if (action==MotionEvent.ACTION_DOWN){
            int row = (int) Math.ceil(y/cellSize);
            int col = (int) Math.ceil(x/cellSize);

            if(!winnerLine){
                if (game.updateGameBoard(row,col)){
                    invalidate();

                    if(game.winnerCheck()){
                        winnerLine=true;
                        invalidate();
                    }

                    if (game.getPlayer()%2==0){
                        game.setPlayer(game.getPlayer()-1);
                    }else {
                        game.setPlayer(game.getPlayer()+1);
                    }
                }
            }



            invalidate();
            return true;
        }
        return false;
    }

    private void drawGameBoard(Canvas canvas) {
        paint.setColor(boardColor);
        paint.setStrokeWidth(16);
        for(int c =0;c<4;c++){
            canvas.drawLine(cellSize*c,0, cellSize*c, canvas.getWidth(),paint);
        }
        for(int r =0;r<4;r++){
            canvas.drawLine(0,cellSize*r, canvas.getWidth(),cellSize*r ,paint);

        }
    }

    private void drawX(Canvas canvas, int row, int col){
        paint.setColor(XColor);

        canvas.drawLine((float)((col+1)*cellSize-cellSize*0.2),
                (float) (row*cellSize+cellSize*0.2),
                (float) (col*cellSize+cellSize*0.2),
                (float) ((row+1)*cellSize-cellSize*0.2),
                paint);
        canvas.drawLine((float)(col*cellSize+cellSize*0.2),
                (float)(row*cellSize+cellSize*0.2),
                (float)((col+1)*cellSize-cellSize*0.2),
                (float)((row+1)*cellSize-cellSize*0.2),
                paint);
    }

    private void drawO(Canvas canvas, int row, int col){
        paint.setColor(OColor);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawOval((float)(col*cellSize+cellSize*0.2),
                    (float)(row*cellSize+cellSize*0.2),
                    (float)((col*cellSize+cellSize)-cellSize*0.2),
                    (float)((row*cellSize+cellSize)-cellSize*0.2),
                    paint);
        }
    }

     public void resetGame(){
        game.resetGame();
        winnerLine = false;
     }

     public void setUpGame (Button playAgain, Button home, TextView playerDisplay, String[] names){
        game.setPlayAgain(playAgain);
        game.setHomebtn(home);
        game.setPlayerTurn(playerDisplay);
        game.setPlayerNames(names);

     }

     private void  drawHorizontallLine(Canvas canvas, int row,int col){
        canvas.drawLine(col, (float)row*cellSize+ cellSize/2,
                cellSize*3,(float)row*cellSize+cellSize/2,paint);
     }

    private void  drawVerticalLine (Canvas canvas, int row,int col){
        canvas.drawLine((float)col*cellSize+cellSize/2,row,
                (float)col*cellSize+cellSize/2,cellSize*3,paint);
    }

    private void  drawDiagonalLinePos(Canvas canvas){
        canvas.drawLine(0,cellSize*3,cellSize*3,0,paint);
    }

    private void  drawDiagonalLineNeg(Canvas canvas){
        canvas.drawLine(0,0,cellSize*3,cellSize*3,paint);
    }
    private void drawWinningLine(Canvas canvas){
        int row = game.getWinType()[0];
        int col = game.getWinType()[1];

        switch (game.getWinType()[2]){
            case 1:
                drawHorizontallLine(canvas,row,col);
                break;
            case 2:
                drawVerticalLine(canvas,row,col);
                break;
            case 3:
                drawDiagonalLineNeg(canvas);
                break;
            case 4:
                drawDiagonalLinePos(canvas);
                break;
            default:
                break;

        }
    }
}
