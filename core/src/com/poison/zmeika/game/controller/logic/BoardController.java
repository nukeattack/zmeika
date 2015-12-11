package com.poison.zmeika.game.controller.logic;

import com.badlogic.gdx.Gdx;
import com.poison.zmeika.engine.GameObject;
import com.poison.zmeika.engine.InputHelper;
import com.poison.zmeika.engine.messaging.EventPool;
import com.poison.zmeika.engine.messaging.GameEvent;
import com.poison.zmeika.engine.messaging.GameEventType;
import com.poison.zmeika.engine.messaging.MessagingManager;
import com.poison.zmeika.game.model.life.BoardModel;
import com.poison.zmeika.game.model.life.Cell;

public class BoardController extends GameObject {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 38;
    private static final float STEP_PERIOD = 0.5f;
    private static final int MIN_CELLS = 200;

    private BoardModel board;
    private GameObject rootObject;
    private float timePassed = 0.0f;

    private boolean[][] removeBoard;
    private boolean[][] createBoard;

    public BoardController() {

    }

    public BoardModel getBoard() {
        return board;
    }

    public void setRootObject(GameObject rootObject) {
        this.rootObject = rootObject;
    }

    public void init() {
        board = new BoardModel(WIDTH, HEIGHT);
        removeBoard = new boolean[WIDTH][HEIGHT];
        createBoard = new boolean[WIDTH][HEIGHT];

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
            if (timePassed > STEP_PERIOD) {
                while (timePassed > STEP_PERIOD) {
                    rootObject.log("Step");
                    processStep();
                    timePassed = timePassed - STEP_PERIOD;
                }
            }
            return true;
        }
        return false;
    }


    private void processStep() {
        clearBoards();
        lifeIteration();
        markLifeToLiveDeadToDeath();

        if(board.liveCellsCount < 100){
            for(int i = 0; i < 100; i++){
                createRandomCell();
            }
        }
    }

    private void lifeIteration(){
        for (int i = 0; i < board.width; i++) {
            for (int j = 0; j < board.height; j++) {
                if (!board.getCell(i, j).isAlive) {
                    if (board.neighborsCount[i][j] == 3) {
                        createBoard[i][j] = true;
                    }
                } else {
                    if (!(board.neighborsCount[i][j] == 3 || board.neighborsCount[i][j] == 2)) {
                        removeBoard[i][j] = true;
                    }
                }
            }
        }
    }

    private void markLifeToLiveDeadToDeath(){
        for (int i = 0; i < board.width; i++) {
            for (int j = 0; j < board.height; j++) {
                if (removeBoard[i][j] == true) {
                    removeCell(i, j);
                }
            }
        }
        for (int i = 0; i < board.width; i++) {
            for (int j = 0; j < board.height; j++) {
                if (createBoard[i][j] == true) {
                    createCell(i, j);
                }
            }
        }
    }

    private void createRandomCell(){
        int i = (int)(Math.random() * board.width);
        int j = (int)(Math.random() * board.height);
        createCell(i, j);
    }

    private void clearBoards() {
        for (int i = 0; i < board.width; i++) {
            for (int j = 0; j < board.height; j++) {
                removeBoard[i][j] = false;
                createBoard[i][j] = false;
            }
        }
    }

    private void publishEvent(GameEvent event){
        MessagingManager.instance().publishEvent(event);
    }

    public void createCell(int x, int y) {
        Cell cell = board.setAlive(x, y, true);
        publishEvent(EventPool.instance().getEvent().withData(cell).withType(GameEventType.OBJECT_CREATED));
    }

    public void removeCell(int x, int y) {
        Cell cell = board.setAlive(x, y, false);
        publishEvent(EventPool.instance().getEvent().withData(cell).withType(GameEventType.OBJECT_DELETED));
    }
}
