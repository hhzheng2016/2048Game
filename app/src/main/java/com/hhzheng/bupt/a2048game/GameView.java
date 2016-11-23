package com.hhzheng.bupt.a2048game;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2016/11/21.
 */

public class GameView extends GridLayout {
    public GameView(Context context) {
        super(context);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGameView();
    }

    private void initGameView(){
        setColumnCount(4);
        setBackgroundColor(0xffbbada0);
        setOnTouchListener(new OnTouchListener() {

            private float startX,startY,offsetX,offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX=event.getX();
                        startY=event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX=event.getX()-startX;
                        offsetY=event.getY()-startY;
                        if(Math.abs(offsetX)>Math.abs(offsetY)){
                            if(offsetX<-5){
                                swipeLeft();
                            }else if(offsetX>5){
                                swipeRight();
                            }
                        }else{
                            if(offsetY<-5){
                                swipeUp();
                            }else if(offsetY>5){
                                swipeDown();
                            }
                        }

                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
       // System.out.println("w"+w+"h"+h);
        int cardWidth=(Math.min(w,h)-10)/4;
       // System.out.println(cardWidth);
        addCards(cardWidth,cardWidth);

        startGame();
    }

    private void addCards(int cardWidth,int cardHeight){
        Card c;

        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){
                c=new Card(getContext());
                c.setNum(0);
                addView(c,cardWidth,cardHeight);
                cardMap[x][y]=c;
            }
        }
    }

    private void addRandomNum(){
        emptyPoints.clear();

        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){
                //只有空点处可以添加数据，防止重复添加
                if(cardMap[x][y].getNum()<=0){
                    emptyPoints.add(new Point(x,y));
                }
            }
        }
        //随机移除一个点
        Point p=emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
        cardMap[p.x][p.y].setNum(Math.random()>0.1?2:4);
    }

    private void startGame(){
        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){
               cardMap[x][y].setNum(0);
            }
        }
        addRandomNum();
        addRandomNum();
    }

    private void swipeLeft(){}

    private void swipeRight(){}

    private void swipeUp(){}

    private void swipeDown(){}

    Card[][] cardMap=new Card[4][4];

    private List<Point> emptyPoints=new ArrayList<Point>();
}
