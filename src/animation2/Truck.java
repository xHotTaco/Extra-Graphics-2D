package animation2;

import Fill.Flood;
import Fill.ScanLine;
import Forms.OutlineShape;
import Forms.Polygon;
import Lines.Line;
import utilities.MyPoint;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Truck extends BufferedImage {
    private Graphics graphics;
    private final Color colorOfTheBox = new Color(155, 168, 55);
    private final Color colorOfTheCabin = Color.BLUE;
    private final Color tiresColor = Color.BLACK;
    private Polygon polygon;
    private MyPoint position;
    private MyPoint defaultPosition;
    private int width;
    private int height;

    public Truck(int width, int height, int imageType) {
        super(width, height, imageType);
        polygon = new Polygon(this);
        this.width = width;
        this.height = height;
        defaultPosition = new MyPoint(-320, 340);
        position = new MyPoint(-320,270);
        polygon.addVert(new MyPoint(position.getX(),position.getY()));
        polygon.addVert(new MyPoint(position.getX()+width,position.getY()));
        polygon.addVert(new MyPoint(position.getX()+width,position.getY()+height));
        polygon.addVert(new MyPoint(position.getX(),position.getY()+height));
    }

    public void setGraphics(Graphics graphics){
        this.graphics = graphics;
    }
    public void draw(){
        MyPoint[]points = polygon.getVerts();
//        System.out.println(points[0].getX()+", "+points[0].getY());
        graphics.drawImage(this, points[0].getX(), points[0].getY(),null);
    }

    public void setVertexOfTruck(){
        Line line = new Line(Color.gray);
        //box one
        OutlineShape outlineShape = new OutlineShape();
        outlineShape.changeColor(colorOfTheBox);
        Flood.apply(outlineShape.drawRectangle(10, 0, 200, 80, this, graphics));

        //tires
        outlineShape.changeColor(tiresColor);
        Flood.apply(outlineShape.drawBasicCircle(30,85,15,this, graphics), new int[]{
                0, colorOfTheBox.getRGB()
        });
        Flood.apply(outlineShape.drawBasicCircle(60,85,15,this, graphics), new int[]{
                0, colorOfTheBox.getRGB()
        });

        Flood.apply(outlineShape.drawBasicCircle(160,85,15,this,graphics),new int[]{
                0, colorOfTheBox.getRGB()
        });
        Flood.apply(outlineShape.drawBasicCircle(190,85,15,this,graphics),new int[]{
                0,  colorOfTheBox.getRGB()
        });
        outlineShape.changeColor(Color.WHITE);
        outlineShape.drawBasicCircle(30,85,10,this, graphics);
        outlineShape.drawBasicCircle(60,85,10,this, graphics);
        outlineShape.drawBasicCircle(160,85,10,this, graphics);
        outlineShape.drawBasicCircle(190,85,10,this, graphics);
        line.drawDDALine(new MyPoint(10,80), new MyPoint(210,80), this, graphics);

        //union between the box and the cabin
        outlineShape.changeColor(Color.BLACK);
        Flood.apply(outlineShape.drawRectangle(210,70, 30,10,this, graphics));
        //cabin
        Polygon polygon = new Polygon(this);
        polygon.changeColor(colorOfTheCabin);
        polygon.addVert(new MyPoint(240,80));
        polygon.addVert(new MyPoint(240,20));
        polygon.addVert(new MyPoint(270,20));
        polygon.addVert(new MyPoint(275,40));
        polygon.addVert(new MyPoint(300,45));
        polygon.addVert(new MyPoint(300,80));
        Flood.apply(polygon.drawPolygon());

        outlineShape.changeColor(Color.BLACK);

        ScanLine.apply(outlineShape.drawRectangle(245, 25, 20, 15, this, graphics));
       //tire of the cabin
        outlineShape.changeColor(tiresColor);
        Flood.apply(outlineShape.drawBasicCircle(280,85,15,this,graphics),new int[]{
                0, colorOfTheCabin.getRGB()
        });
        outlineShape.changeColor(Color.WHITE);
        outlineShape.drawBasicCircle(280,85,10,this, graphics);
        line.drawDDALine(new MyPoint(240,80), new MyPoint(300, 80), this, graphics);
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void resetTruck(){
        position = defaultPosition;
        polygon.clearVertex();
        polygon.addVert(new MyPoint(defaultPosition.getX(),defaultPosition.getY()));
        polygon.addVert(new MyPoint(defaultPosition.getX()+width, defaultPosition.getY()));
        polygon.addVert(new MyPoint(defaultPosition.getX()+width,defaultPosition.getY()+height));
        polygon.addVert(new MyPoint(defaultPosition.getX(),defaultPosition.getY()+height));
    }
}
