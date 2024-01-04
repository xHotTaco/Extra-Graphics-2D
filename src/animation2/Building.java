package animation2;

import Fill.Flood;
import Forms.OutlineShape;
import Forms.Polygon;
import transforms.Transform;
import utilities.MyPoint;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Building {
    private final MyPoint position;
    private final Graphics graphics;
    private final OutlineShape outlineShape;
    private final BufferedImage bufferedImage;
    private MyPoint windowSize;
    private Color colorOfBuilding;
    private Color colorOfWindowBuilding;
    private Polygon building;
    private int minusX = 15;
    private int minuxY = 10;
    private int spaceBetweenWindows = 30;
    private MyPoint[][] windows;
    private int widthOfWindow = 20;
    private int heightOfWindow = 20;
    private Polygon door;
    private int widthOfRoofOne = 140;
    private int heightOfRoofOne = 10;
    private int widthOfRoofTwo = 120;
    private int heightOfRoofTwo = 20;
    private int widthOfRoofThree = 100;
    private int heightOfRoofThree = 10;
    private int spaceBetweenRoofOne = 10;
    private int spaceXBetweenRoofTwo = 10;
    private int spaceYBetweenRoofTwo = 30;

    private int spaceXBetweenRoofThree = 20;
    private int spaceYBetweenRoofThree = 40;

    public Building(BufferedImage bufferedImage, MyPoint position){
        this.position = position;
        this.bufferedImage = bufferedImage;
        graphics = bufferedImage.createGraphics();
        windows = new MyPoint[5][4];
        door = new Polygon(bufferedImage);
        outlineShape = new OutlineShape();
        windowSize = new MyPoint(20,20);
        building = new Polygon(bufferedImage);
        building.addVert(new MyPoint(position.getX(), position.getY()+200));
        building.addVert(position);
        building.addVert(new MyPoint(position.getX()+140, position.getY()));
        building.addVert(new MyPoint(position.getX()+140, position.getY()+200));
        door.setVerts(new MyPoint[]{
                new MyPoint(position.getX()+55, position.getY()+200),
                new MyPoint(position.getX()+55,position.getY()+160),
                new MyPoint(position.getX()+95,position.getY()+160),
                new MyPoint(position.getX()+95, position.getY()+200)
        });
    }

    public void setColorOfBuilding(Color color){
        this.colorOfBuilding = color;
    }

    public void setColorOfWindowBuilding(Color color){
        this.colorOfWindowBuilding = color;
    }

    public void drawBuildingType1(){
        Color building1Color = colorOfBuilding == null ? new Color(153, 204, 161): colorOfBuilding;
        building.changeColor(building1Color);
        Flood.apply(building.drawPolygon());

        Color building1WindowColor = colorOfWindowBuilding == null ? new Color(72, 119, 103): colorOfWindowBuilding;
        door.changeColor(building1WindowColor);
        outlineShape.changeColor(building1WindowColor);
        //Roof
        Flood.apply(outlineShape.drawRectangle(position.getX() + spaceXBetweenRoofThree,
                position.getY() - spaceYBetweenRoofThree, widthOfRoofThree, heightOfRoofThree, bufferedImage,graphics));

        Flood.apply(outlineShape.drawRectangle(position.getX(),position.getY() - spaceBetweenRoofOne,
                widthOfRoofOne, heightOfRoofOne, bufferedImage, graphics));
        outlineShape.changeColor(building1Color);
        Flood.apply(outlineShape.drawRectangle(position.getX()+spaceXBetweenRoofTwo,position.getY()-spaceYBetweenRoofTwo,
                widthOfRoofTwo, heightOfRoofTwo,bufferedImage, graphics));

        //All Floors
        outlineShape.changeColor(building1WindowColor);
        Flood.apply(outlineShape.drawRectangle(position.getX() + minusX,position.getY() + minuxY,
                widthOfWindow, heightOfWindow,bufferedImage, graphics));
        Flood.apply(outlineShape.drawRectangle((position.getX() + minusX) + spaceBetweenWindows,
                position.getY() + minuxY, widthOfWindow, heightOfWindow,bufferedImage, graphics));
        Flood.apply(outlineShape.drawRectangle((position.getX() + minusX) + spaceBetweenWindows * 2,
                position.getY() + minuxY, widthOfWindow, heightOfWindow,bufferedImage, graphics));
        Flood.apply(outlineShape.drawRectangle((position.getX() + minusX) + spaceBetweenWindows * 3,
                position.getY() + minuxY, widthOfWindow, heightOfWindow, bufferedImage, graphics));

        Flood.apply(outlineShape.drawRectangle(position.getX() + minusX,
                (position.getY() + minuxY) + spaceBetweenWindows, widthOfWindow, heightOfWindow,bufferedImage, graphics));
        Flood.apply(outlineShape.drawRectangle((position.getX() + minusX) + spaceBetweenWindows,
                (position.getY() + minuxY) + spaceBetweenWindows, widthOfWindow, heightOfWindow,bufferedImage, graphics));
        Flood.apply(outlineShape.drawRectangle((position.getX() + minusX) + spaceBetweenWindows * 2,
                (position.getY() + minuxY) + spaceBetweenWindows, widthOfWindow, heightOfWindow,bufferedImage, graphics));
        Flood.apply(outlineShape.drawRectangle((position.getX() + minusX) + spaceBetweenWindows * 3,
                (position.getY() + minuxY) + spaceBetweenWindows, widthOfWindow, heightOfWindow, bufferedImage, graphics));

        Flood.apply(outlineShape.drawRectangle(position.getX() + minusX,
                (position.getY() + minuxY) + spaceBetweenWindows * 2, widthOfWindow, heightOfWindow,bufferedImage, graphics));
        Flood.apply(outlineShape.drawRectangle((position.getX() + minusX) + spaceBetweenWindows,
                (position.getY() + minuxY) + spaceBetweenWindows * 2, widthOfWindow, heightOfWindow,bufferedImage, graphics));
        Flood.apply(outlineShape.drawRectangle((position.getX() + minusX) + spaceBetweenWindows * 2,
                (position.getY() + minuxY) + spaceBetweenWindows * 2, widthOfWindow, heightOfWindow,bufferedImage, graphics));
        Flood.apply(outlineShape.drawRectangle((position.getX() + minusX) + spaceBetweenWindows * 3,
                (position.getY() + minuxY) + spaceBetweenWindows * 2, widthOfWindow, heightOfWindow, bufferedImage, graphics));

        Flood.apply(outlineShape.drawRectangle(position.getX() + minusX,
                (position.getY() + minuxY) + spaceBetweenWindows * 3, widthOfWindow, heightOfWindow,bufferedImage, graphics));
        Flood.apply(outlineShape.drawRectangle((position.getX() + minusX) + spaceBetweenWindows,
                (position.getY() + minuxY) + spaceBetweenWindows * 3, widthOfWindow, heightOfWindow,bufferedImage, graphics));
        Flood.apply(outlineShape.drawRectangle((position.getX() + minusX) + spaceBetweenWindows * 2,
                (position.getY() + minuxY) + spaceBetweenWindows * 3, widthOfWindow, heightOfWindow,bufferedImage, graphics));
        Flood.apply(outlineShape.drawRectangle((position.getX() + minusX) + spaceBetweenWindows * 3,
                (position.getY() + minuxY) + spaceBetweenWindows * 3, widthOfWindow, heightOfWindow, bufferedImage, graphics));

        Flood.apply(outlineShape.drawRectangle(position.getX() + minusX,
                (position.getY() + minuxY) + spaceBetweenWindows * 4, widthOfWindow, heightOfWindow,bufferedImage, graphics));
        Flood.apply(outlineShape.drawRectangle((position.getX() + minusX) + spaceBetweenWindows,
                (position.getY() + minuxY) + spaceBetweenWindows * 4, widthOfWindow, heightOfWindow,bufferedImage, graphics));
        Flood.apply(outlineShape.drawRectangle((position.getX() + minusX) + spaceBetweenWindows * 2,
                (position.getY() + minuxY) + spaceBetweenWindows * 4, widthOfWindow, heightOfWindow,bufferedImage, graphics));
        Flood.apply(outlineShape.drawRectangle((position.getX() + minusX) + spaceBetweenWindows * 3,
                (position.getY() + minuxY) + spaceBetweenWindows * 4, widthOfWindow, heightOfWindow, bufferedImage, graphics));
        //Door
        Flood.apply(door.drawPolygon());
    }

    public Polygon getBuilding() {
        return building;
    }

    public void scale(double sx, double sy){
        Transform.scaleWithMatrix(getBuilding(), sx, sy);
        Polygon polygon = new Polygon(bufferedImage);
        polygon.addVert(new MyPoint(minusX, minuxY));
        polygon.addVert(new MyPoint(spaceBetweenWindows,0));
        polygon.addVert(new MyPoint(widthOfWindow, heightOfWindow));
        polygon.addVert(new MyPoint(widthOfRoofOne, heightOfRoofOne));
        polygon.addVert(new MyPoint(widthOfRoofTwo, heightOfRoofTwo));
        polygon.addVert(new MyPoint(0,spaceBetweenRoofOne));
        polygon.addVert(new MyPoint(spaceXBetweenRoofTwo, spaceYBetweenRoofTwo));
        polygon.addVert(new MyPoint(spaceXBetweenRoofThree, spaceYBetweenRoofThree));
        polygon.addVert(new MyPoint(widthOfRoofThree, heightOfRoofThree));
        Transform.scaleWithMatrix(polygon, sx, sy);
        Transform.scaleWithMatrix(door, sx, sy);
        minusX = polygon.getVerts()[0].getX();
        minuxY = polygon.getVerts()[0].getY();
        spaceBetweenWindows = polygon.getVerts()[1].getX();
        widthOfWindow = polygon.getVerts()[2].getX();
        heightOfWindow = polygon.getVerts()[2].getY();
        widthOfRoofOne = polygon.getVerts()[3].getX();
        heightOfRoofOne = polygon.getVerts()[3].getY();
        widthOfRoofTwo = polygon.getVerts()[4].getX();
        heightOfRoofTwo = polygon.getVerts()[4].getY();

        spaceBetweenRoofOne = polygon.getVerts()[5].getY();
        spaceXBetweenRoofTwo = polygon.getVerts()[6].getX();
        spaceYBetweenRoofTwo = polygon.getVerts()[6].getY();

        spaceXBetweenRoofThree = polygon.getVerts()[7].getX();
        spaceYBetweenRoofThree = polygon.getVerts()[7].getY();

        widthOfRoofThree = polygon.getVerts()[8].getX();
        heightOfRoofThree = polygon.getVerts()[8].getY();
    }
}
