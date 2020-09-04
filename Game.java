import javax.swing.*;
import java.util.Random;
public class Game extends Square {

    private int boardWidth;
    private int boardHeight;
    int mineCounter;
    int randNumx;
    int randNumy;
    private Random random;
    private Square[][] gameGrid;
    //default game with no options selected

    public Game(){
        //gameGrid=new Square[10][10];
        //random=new Random();
       // generateGameBoard(10,10,10);
       // System.out.println("default constructr");
    }

    //Game Board with different options selected
    public Game(int boardHeight, int boardWidth){
        this.boardWidth=boardWidth;
        this.boardHeight=boardHeight;
        gameGrid=new Square[boardHeight][boardWidth];
        random=new Random();
        generateGameBoard(boardHeight,boardWidth,10);
    }

    public void generateGameBoard(int height, int width, int mineCounter){
        //Generate the board

        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                gameGrid[y][x]=new Square();
            }
        }
        //Place mines
        this.mineCounter=mineCounter;
        while(mineCounter>0){
            randNumy=random.nextInt(boardHeight);
            randNumx=random.nextInt(boardWidth);

            if(gameGrid[randNumy][randNumx].getSpaceType()!=9){
                System.out.println("randx: "+randNumx+" randY: "+randNumy);
                gameGrid[randNumy][randNumx].setSpaceType(9);
                increaseSpaceType(randNumy,randNumx);
                mineCounter--;
            }

        }
        //Generate Numbers around
    }

    public void increaseSpaceType(int y,int x){
        //top left
        if(y==0&&x==0){
            gameGrid[y+1][x].aroundBomb();
            gameGrid[y][x+1].aroundBomb();
            gameGrid[y+1][x+1].aroundBomb();
        }
        //top right
        else if(y==0&&x==boardWidth-1){
            gameGrid[y][x-1].aroundBomb();
            gameGrid[y+1][x].aroundBomb();
            gameGrid[y+1][x-1].aroundBomb();
        }
        //bottom left
        else if(y==boardHeight-1 && x==0){
            gameGrid[y][x+1].aroundBomb();
            gameGrid[y-1][x].aroundBomb();
            gameGrid[y-1][x+1].aroundBomb();
        }
        //bottom right
        else if(y==boardHeight-1 && x==boardWidth-1){
            gameGrid[y][x-1].aroundBomb();
            gameGrid[y-1][x].aroundBomb();
            gameGrid[y-1][x-1].aroundBomb();
        }
        //top edge
        else if(y==0){
            gameGrid[y][x+1].aroundBomb();
            gameGrid[y][x-1].aroundBomb();
            gameGrid[y+1][x].aroundBomb();
            gameGrid[y+1][x-1].aroundBomb();
            gameGrid[y+1][x+1].aroundBomb();
        }
        //bottom edge
        else if(y==boardHeight-1){
            gameGrid[y][x+1].aroundBomb();
            gameGrid[y][x-1].aroundBomb();
            gameGrid[y-1][x].aroundBomb();
            gameGrid[y-1][x-1].aroundBomb();
            gameGrid[y-1][x+1].aroundBomb();
        }
        //left edge
        else if(x==0){
            gameGrid[y-1][x].aroundBomb();
            gameGrid[y+1][x].aroundBomb();
            gameGrid[y+1][x+1].aroundBomb();
            gameGrid[y-1][x+1].aroundBomb();
            gameGrid[y][x+1].aroundBomb();
        }
        //right edge
        else if(x==boardWidth-1){
            gameGrid[y-1][x].aroundBomb();
            gameGrid[y+1][x].aroundBomb();
            gameGrid[y+1][x-1].aroundBomb();
            gameGrid[y-1][x-1].aroundBomb();
            gameGrid[y][x-1].aroundBomb();
        }
        else{
            gameGrid[y][x-1].aroundBomb();
            gameGrid[y-1][x-1].aroundBomb();
            gameGrid[y-1][x].aroundBomb();
            gameGrid[y-1][x+1].aroundBomb();
            gameGrid[y][x+1].aroundBomb();
            gameGrid[y+1][x+1].aroundBomb();
            gameGrid[y+1][x].aroundBomb();
            gameGrid[y+1][x-1].aroundBomb();
            
        }

    }

    public Square getSquare(int width, int height){
        return gameGrid[height][width];
    }
    public void bombClick(){

    }


    public void revealSquares(int y,int x){
        //Make a check if its flagged, then if bomb then bombClick()
        //in if statement, add || isFlaged
        if(gameGrid[y][x].isHidden() || gameGrid[y][x].hasFlag()==true) {
            System.out.println("I am revealing at x: " + x + " y: " + y);
            gameGrid[y][x].setHidden(false);
            //if !flagged, else can reveal bomb and lose
            if (gameGrid[y][x].getSpaceType() != 9 && gameGrid[y][x].getSpaceType() != 0) {

                gameGrid[y][x].setHidden(false);
                return;
            } else if (gameGrid[y][x].getSpaceType() == 0) {
                //top left

                if (y == 0 && x == 0) {

                    revealSquares(y + 1, x);
                    revealSquares(y, x + 1);
                    revealSquares(y + 1, x + 1);

                }
                //top right
                else if (y == 0 && x == boardWidth - 1) {

                    revealSquares(y, x - 1);
                    revealSquares(y + 1, x);
                    revealSquares(y + 1, x - 1);
                }
                //bottom left
                else if (y == boardHeight - 1 && x == 0) {

                    revealSquares(y, x + 1);
                    revealSquares(y - 1, x);
                    revealSquares(y - 1, x + 1);
                }
                //bottom right
                else if (y == boardHeight - 1 && x == boardWidth - 1) {

                    revealSquares(y, x - 1);
                    revealSquares(y - 1, x);
                    revealSquares(y - 1, x - 1);

                }
                //top edge
                else if (y == 0) {

                    revealSquares(y, x + 1);
                    revealSquares(y, x - 1);
                    revealSquares(y + 1, x);
                    revealSquares(y + 1, x - 1);
                    revealSquares(y + 1, x + 1);

                }
                //bottom edge
                else if (y == boardHeight - 1) {

                    revealSquares(y, x + 1);
                    revealSquares(y, x - 1);
                    revealSquares(y - 1, x);
                    revealSquares(y - 1, x - 1);
                    revealSquares(y - 1, x + 1);
                }
                //left edge
                else if (x == 0) {

                    revealSquares(y - 1, x);
                    revealSquares(y + 1, x);
                    revealSquares(y + 1, x + 1);
                    revealSquares(y - 1, x + 1);
                    revealSquares(y, x + 1);

                }
                //right edge
                else if (x == boardWidth - 1) {

                    revealSquares(y - 1, x);
                    revealSquares(y + 1, x);
                    revealSquares(y + 1, x - 1);
                    revealSquares(y - 1, x - 1);
                    revealSquares(y, x - 1);
                } else {

                    revealSquares(y, x - 1);
                    revealSquares(y - 1, x - 1);
                    revealSquares(y - 1, x);
                    revealSquares(y - 1, x + 1);
                    revealSquares(y, x + 1);
                    revealSquares(y + 1, x + 1);
                    revealSquares(y + 1, x);
                    revealSquares(y + 1, x - 1);


                }

            }
        }
        else{
            return;
        }

    }



}
