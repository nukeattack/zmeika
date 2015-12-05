package com.poison.zmeika.game.controller.logic;

import com.badlogic.gdx.Gdx;
import com.poison.zmeika.engine.GameObject;
import com.poison.zmeika.engine.InputHelper;
import com.poison.zmeika.engine.messaging.GameEvent;
import com.poison.zmeika.engine.messaging.GameEventType;
import com.poison.zmeika.engine.messaging.MessagingManager;
import com.poison.zmeika.game.model.BoardModel;
import com.poison.zmeika.game.model.CellModel;

public class BoardController extends GameObject {
    private BoardModel boardModel;
    private GameObject rootObject;
    private int width = 50;
    private int height = 38;
    private float timePassed = 0.0f;
    private float stepPeriod = 0.1f;
    private int maxX = width - 1;
    private int maxY = height - 1;

    private boolean[][] removeBoard;
    private boolean[][] createBoard;
    private int[][] neighborsCount;

    public BoardController() {

    }

    public BoardModel getBoardModel() {
        return boardModel;
    }

    public void setRootObject(GameObject rootObject) {
        this.rootObject = rootObject;
    }

    public void init() {
        boardModel = new BoardModel(width, height);
        removeBoard = new boolean[width][height];
        createBoard = new boolean[width][height];
        neighborsCount = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                neighborsCount[i][j] = 0;
            }
        }
//        for(int i = 0; i < 30; i++){
//            createCell((int)(Math.random()*width), (int)(Math.random()*height));
//        }
        createCell(10, 5);
        createCell(10, 6);
        createCell(10, 7);
        createCell(9, 5);
        createCell(8, 6);

    }

    public boolean update(float dt) {
        if(super.update(dt)){
            if(Gdx.input.isTouched()){
                createCell((int)(InputHelper.mousePos.x / 16.0f), (int)(InputHelper.mousePos.y / 16.0f));
            }
            timePassed += dt;
            if (timePassed > stepPeriod) {
                while (timePassed > stepPeriod) {
                    rootObject.log("Step");
                    processStep();
                    timePassed = timePassed - stepPeriod;
                }
            }
            return true;
        }
        return false;
    }


    private void processStep() {
        clearBoards();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (boardModel.getCell(i, j) == null) {
                    if (neighborsCount[i][j] == 3) {
                        createBoard[i][j] = true;
                    }
                } else {
                    if (!(neighborsCount[i][j] == 3 || neighborsCount[i][j] == 2)) {
                        removeBoard[i][j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (removeBoard[i][j]) {
                    removeCell(i, j);
                }
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (createBoard[i][j]) {
                    createCell(i, j);
                }
            }
        }
    }

    private void clearBoards() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                removeBoard[i][j] = false;
                createBoard[i][j] = false;
            }
        }
    }

    private int getRealX(int x) {
        if(x < 0){
            return width + x;
        }
        if(x > maxX){
            return x - width;
        }
        return x;
    }

    private int getRealY(int y) {
        if(y < 0){
            return height + y;
        }
        if(y > maxY){
            return y - height;
        }
        return y;
    }


    private void updateNeighbors(int x, int y, boolean onCreate) {
        for (int i = x-1; i < x+2; i++) {
            for (int j = y-1; j < y+2; j++) {
                if (!(i == x && j == y)) {
                    if (onCreate) {
                        try{
                            neighborsCount[getRealX(i)][getRealY(j)]++;
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    } else {
                        neighborsCount[getRealX(i)][getRealY(j)]--;
                    }
                }
            }
        }
    }

    private void publishPost(GameEvent event){
        MessagingManager.instance().bus().post(event).now();
    }

    public CellModel createCell(int x, int y) {
        x = x > maxX ? maxX : x;
        y = y > maxY ? maxY : y;
        x = x < 0 ? 0 : x;
        y = y < 0 ? 0 : y;
        if (boardModel.getCell(x, y) == null) {
            x = getRealX(x);
            y = getRealY(y);
            CellModel cellModel = new CellModel(x, y);
            boardModel.addCell(cellModel);
            publishPost(new GameEvent(cellModel).forThe(GameEventType.OBJECT_CREATED));
            updateNeighbors(x, y, true);
            return cellModel;
        }

        return boardModel.getCell(x, y);
    }

    public void removeCell(int x, int y) {
        publishPost(new GameEvent(x,y).forThe(GameEventType.OBJECT_CREATED));
        boardModel.removeCell(x, y);
        updateNeighbors(x, y, false);
    }
}
