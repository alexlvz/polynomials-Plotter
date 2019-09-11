package com.example.alexl.polynoms;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GraphView extends View
{
    private float firstPointY, SecondPointY, firstPointX, secondPointX;
    private Polynomial poly;
    public GraphView(Context context, AttributeSet attrs)
    {
        super(context,attrs);
    }

    protected void onDraw(Canvas c)
    {
        super.onDraw(c);
        Paint p = new Paint();
        float yCenter=getHeight()/2;
        float xCenter=getWidth()/2;
        float xSpot=0,y=0,num=-9; //for drawing the coordinate system
        float xInterval=(getWidth()/20);
        float yInterval=getHeight()/20;
        float xForEvaluation=-20; // the point to draw they y of (for the graph)
        final double INTERVAL_ZIZE=0.1;
        final float CANVAS_HIGHT=getHeight();
        final float CANVAS_WIDTH=getWidth();


        //============================================================//
        p.setTextSize(CANVAS_WIDTH/25);
        p.setColor(Color.BLUE);
        p.setStrokeWidth(2);
        for(int i=0;i<300;i++) //draw the X and Y axis
        {
            c.drawLine(0,y,CANVAS_WIDTH,y,p);
            y+=CANVAS_HIGHT/20;
            c.drawLine(xSpot,0,xSpot,CANVAS_HIGHT,p);
            xSpot+=CANVAS_WIDTH/20+0.25;
        }
        //===========================================================//
        xSpot=CANVAS_WIDTH/20;
        p.setStrokeWidth(3);
        p.setColor(Color.BLACK);
        for(int i=0;i<=18;i++) //draw the numbers on X axis
        {
            if(num!=0) {
                if(num<0) //NEGATIVE NUMBERS
                    c.drawText("" + (int)num, xSpot-35, yCenter + 35, p);
                else
                    c.drawText("" + (int)num, xSpot+10, yCenter + 35, p);
            }
            num++;
            xSpot+=CANVAS_WIDTH/20;
        }
        //========================================================//

        //=====================X Y AXIS =========================//
        p.setColor(Color.BLACK);
        p.setStrokeWidth(7);
        c.drawLine(0,yCenter,CANVAS_WIDTH,yCenter,p);
        c.drawLine(xCenter,0,xCenter,CANVAS_WIDTH,p);
        //======================================================//
        p.setColor(Color.BLUE);
        p.setStrokeWidth(7);
        for(int i=-200;i<=200;i++) //DRAW THE GRAPH BY TAKING Y POINTS FOR GIVEN X'S.
        {
            if(this.poly!=null)
            {
                firstPointX = xCenter+xForEvaluation*xInterval;
                secondPointX = (float) (xCenter+(xForEvaluation+INTERVAL_ZIZE)*xInterval);
                firstPointY = (float) (yCenter - poly.evaluate(xForEvaluation)*yInterval);
                SecondPointY = (float) (yCenter - poly.evaluate(xForEvaluation+INTERVAL_ZIZE)*yInterval);

                c.drawLine(firstPointX,firstPointY,secondPointX,SecondPointY, p);

                xForEvaluation+=INTERVAL_ZIZE;
            }
        }
    }
    public void drawGraph(Polynomial p)
    {
        this.poly=p; //get the polynom that will be drawn.
        invalidate(); //call onDraw
    }
}
