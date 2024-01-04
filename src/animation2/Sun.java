package animation2;

import Curve.Curve;
import Fill.Flood;
import Forms.OutlineShape;
import Forms.Polygon;
import utilities.MyPoint;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sun extends BufferedImage  {
    private Polygon polygon;
    private int sunAngle = 0;
    private Graphics graphics;

    public Sun(int width, int height, int imageType) {
        super(width, height, imageType);
        Curve curve = new Curve(this);
        polygon = new Polygon(this);
        polygon.addVert(new MyPoint(0,0));
        polygon.addVert(new MyPoint((width),0));
        polygon.addVert(new MyPoint(width,height));
        polygon.addVert(new MyPoint(0,height));
        curve.drawSun(new MyPoint(width/2,height/2),2, Color.ORANGE);
        OutlineShape outlineShape = new OutlineShape();
        outlineShape.changeColor(Color.YELLOW);
        Flood.apply(outlineShape.drawBasicCircle(width/2,height/2,width/4,this,graphics));
    }

    public Polygon getSun(){
        return polygon;
    }
    public void setGraphics(Graphics g){
        this.graphics = g;
        MyPoint[]points = polygon.getVerts();
        g.drawImage(this,points[0].getX(),points[0].getY(),null);
//        Transform.rotate(polygon, sunAngle);
    }

    public void setAngle(int angle){
        this.sunAngle = angle;
    }

    public int getAngle(){
        return sunAngle;
    }

}
