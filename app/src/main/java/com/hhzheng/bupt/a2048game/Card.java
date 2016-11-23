package com.hhzheng.bupt.a2048game;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by hasee on 2016/11/21.
 */

public class Card extends FrameLayout {
    private int num;
    public Card(Context context) {
        super(context);

        label=new TextView(getContext());
        label.setTextSize(32);
        label.setBackgroundColor(0x33ffffff);
        label.setGravity(Gravity.CENTER);

        //填充满整个父级容器  -1,-1
        LayoutParams lp=new LayoutParams(-1,-1);
        lp.setMargins(10,10,0,0);
        addView(label,lp);

        setNum(0);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        if(num<=0){
            label.setText("");
        }else {
            //Andoid的setText接受int参数代表的是字符串资源的id
            label.setText(num + "");
        }
    }

    public boolean equals(Card o){
        return getNum()==o.getNum();
    }
    private TextView label;
}
