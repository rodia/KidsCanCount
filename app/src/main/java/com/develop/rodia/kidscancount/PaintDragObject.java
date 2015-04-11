package com.develop.rodia.kidscancount;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

/**
 * This class manage the process to game.
 *
 * @version 0.1
 */
public class PaintDragObject extends View {
    private DragObject[] objs = new DragObject[5];
    private int objectID = 0;
    private MotionEvent event;

    /**
     *
     * @param context
     */
    public PaintDragObject(Context context) {
        super(context);
        setFocusable(true);
        Point point1 = new Point();
        point1.x = 50;
        point1.y = 20;
        Point point2 = new Point();
        point2.x = 100;
        point2.y = 20;
        Point point3 = new Point();
        point3.x = 150;
        point3.y = 20;
        Point point4 = new Point();
        point4.x = 200;
        point4.y = 20;
        Point point5 = new Point();
        point5.x = 250;
        point5.y = 20;
        objs[0] = new DragObject(context, R.drawable.cat, point1);
        objs[1] = new DragObject(context, R.drawable.cat, point2);
        objs[2] = new DragObject(context, R.drawable.cat, point3);
        objs[3] = new DragObject(context, R.drawable.cat, point4);
        objs[4] = new DragObject(context, R.drawable.cat, point5);
    }

    /**
     * Draw the each object
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        for (DragObject obj : objs) {
            canvas.drawBitmap(obj.getBitmap(), obj.getX(), obj.getY(), null);
        }
    }

    /**
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.event = event;
        int eventaction = event.getAction();
        int X = (int)event.getX();
        int Y = (int)event.getY();
        switch (eventaction ) {
            case MotionEvent.ACTION_DOWN:
                objectID = 0;
                for (DragObject obj : objs) {
                    int centerX = obj.getX() + 25;
                    int centerY = obj.getY() + 25;
                    double radCircle = Math.sqrt( (double) (((centerX-X)*(centerX-X)) + (centerY-Y)*(centerY-Y)));
                    if (radCircle < 23){
                        objectID = obj.getID();
                        break;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (objectID > 0) {
                    objs[objectID-1].setX(X - 25);
                    objs[objectID-1].setY(Y - 25);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }
}
