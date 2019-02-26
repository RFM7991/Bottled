package com.rk.bottled;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class TouchHandler {
    ArrayList<Point> points;
    Point startPoint, endPoint;
    Context context;
    android.graphics.Point screenDimensions;

    public TouchHandler() {
        points = new ArrayList<Point>();
        this.context = context;
    }

    public void clearPoints() {
        points = new ArrayList<Point>();
        startPoint = null;
        endPoint = null;
    }

    //state == 0 if began
    //state == 1 if ended
    //state == 2 if moved
    //add cancel later rob
    //return 0 if no swipe, 1 if up swipe, 2 if right swipe, 3 if down swipe, 4 if left swipe
    public int process(int state, float x, float y) {
        //beginning
        if (state == 0) {
            clearPoints();
            Point p = new Point(x, y);
            points.add(p);

            if (points.size() == 1) {
                startPoint = p;
            }
            //  Log.d("XXX", "BEGAN");
            return 0;
        }
        //end or outside
        if (state == 1 || state == 4) {
            Point p = new Point(x, y);
            points.add(p);
            endPoint = p;

            Point diffs = endPoint.getDifference(startPoint);

            boolean down = diffs.y < 0;
            boolean left = diffs.x > 0;

            float sX = Math.abs(diffs.x); //s = scalar
            float sY = Math.abs(diffs.y);

            Log.d("Touch Handler", "" + endPoint.toString());

            boolean swipeUp = false;
            boolean swipeDown = false;
            boolean swipeLeft = false;
            boolean swipeRight = false;

            float xDiff = diffs.x;
            float yDiff = diffs.y;


            // swipe Up
            if (sY > 3 * sX && sY > 200 && yDiff < 0) {
                return 1;
            }
            // swipe Right
            else if (sX > sY*3  && sX > 200 && xDiff > 0) {
                return 2;
            } else if (sY > sX * 3 && sY > 200 && yDiff > 0) { //down
                return 3;
            }
            // swipe Left
            else if (sX > sY*3  && sX > 200 && xDiff < 0) {
                return 4;
            }

            return 0;
        }
        return 0;
    }


    public void setScreenSize (android.graphics.Point p){
        this.screenDimensions = p;
    }
}
