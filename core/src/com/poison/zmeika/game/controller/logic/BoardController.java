package com.poison.zmeika.game.controller.logic;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.Gdx;
import com.poison.zmeika.engine.GameObject;
import com.poison.zmeika.engine.InputHelper;
import com.poison.zmeika.engine.TweenController;
import com.poison.zmeika.engine.geometry.Vec2fTweenAccessor;
import com.poison.zmeika.engine.messaging.EventPool;
import com.poison.zmeika.engine.messaging.GameEvent;
import com.poison.zmeika.engine.messaging.GameEventType;
import com.poison.zmeika.engine.messaging.MessagingManager;
import com.poison.zmeika.game.controller.utils.PoolController;
import com.poison.zmeika.game.model.life.BoardModel;
import com.poison.zmeika.game.model.life.CellModel;

public class BoardController extends GameObject {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 38;
    private static final float STEP_PERIOD = 0.1f;
    private static final int MIN_CELLS = 150;

    private BoardModel board;
    private GameObject rootObject;
    private float timePassed = 0.0f;
    private int maxX = WIDTH - 1;
    private int maxY = HEIGHT - 1;

    private boolean[][] removeBoard;
    private boolean[][] createBoard;
    private int[][] neighborsCount;

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
        neighborsCount = new int[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
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


        if(board.getCells().size() < MIN_CELLS){
            int size = MIN_CELLS - board.getCells().size();
            for(int i = 0; i < size; i++){
                createRandomCell();
            }
        }
    }

    private void lifeIteration(){
        for (int i = 0; i < board.width; i++) {
            for (int j = 0; j < board.height; j++) {
                if (board.getCell(i, j) == null) {
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
    }

    private void markLifeToLiveDeadToDeath(){
        for (int i = 0; i < board.width; i++) {
            for (int j = 0; j < board.height; j++) {
                if (removeBoard[i][j]) {
                    removeCell(i, j);
                }
            }
        }
        for (int i = 0; i < board.width; i++) {
            for (int j = 0; j < board.height; j++) {
                if (createBoard[i][j]) {
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

    private int getRealX(int x) {
        if(x < 0){
            return board.width + x;
        }
        if(x > maxX){
            return x - board.width;
        }
        return x;
    }

    private int getRealY(int y) {
        if(y < 0){
            return board.height + y;
        }
        if(y > maxY){
            return y - board.height;
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

    private void publishEvent(GameEvent event){
        MessagingManager.instance().publishEvent(event);
    }


    public CellModel createCell(int x, int y) {
        x = x > maxX ? maxX : x < 0 ? 0 : x;
        y = y > maxY ? maxY : y < 0 ? 0 : y;

        if (board.getCell(x, y) == null) {
            x = getRealX(x);
            y = getRealY(y);
            CellModel cell = PoolController.cellModels.get().position(x, y).opacity(0.0f, 0.0f);
            TweenController.start(Tween.to(cell.opacity, Vec2fTweenAccessor.POSITION_XY, STEP_PERIOD).target(1.0f, 1.0f));
            board.addCell(cell);
            publishEvent(EventPool.instance().getEvent().withData(cell).withType(GameEventType.OBJECT_CREATED));
            updateNeighbors(x, y, true);
            return cell;
        }

        return board.getCell(x, y);
    }

    public void removeCell(int x, int y) {
        CellModel model = board.getCell(x, y);
        publishEvent(EventPool.instance().getEvent().withData(PoolController.vec2f.get().set(model.screenPosition.x, model.screenPosition.y)).withType(GameEventType.OBJECT_DELETED));
        board.removeCell(x, y);
        updateNeighbors(x, y, false);
    }
}
