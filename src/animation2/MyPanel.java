package animation2;

import Curve.Curve;
import Fill.Flood;
import Forms.OutlineShape;
import Forms.Polygon;
import Forms.Shape;
import transforms.Transform;
import utilities.MyPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MyPanel extends JPanel implements Runnable {
    private BufferedImage bufferedImage;
    private Graphics2D graphics;
    private final int WIDTH = 800;
    private final int HEIGHT = 800;
    private final Thread thread;
    private final Truck truck;
    private int timeOnSeconds = 0;
    private int timeOnRealSeconds = 0;
    private final Flower[]flowers;
    private MyPoint[]centers = {new MyPoint(75,550), new MyPoint(125,550)};
    private MyPoint[]centers2 = {new MyPoint(425,580), new MyPoint(475,580)};

    private MyPoint[]thingOnFloorTwo;



    private MyPoint[] thingOnFloor;
    public MyPanel(){
        this.bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        graphics = bufferedImage.createGraphics();
        thread = new Thread(this);
        truck = new Truck(310,100, BufferedImage.TYPE_INT_ARGB);
        truck.setGraphics(graphics);
        flowers = new Flower[10];
        flowers[0] = new Flower(75,75,BufferedImage.TYPE_INT_ARGB, 10, 450);
        flowers[1] = new Flower(75,75,BufferedImage.TYPE_INT_ARGB, 80, 470);
        flowers[2] = new Flower(75,75,BufferedImage.TYPE_INT_ARGB, 150, 440);
        flowers[3] = new Flower(75,75,BufferedImage.TYPE_INT_ARGB, 220, 480);

        flowers[4] = new Flower(75,75,BufferedImage.TYPE_INT_ARGB, 300, 580);
        flowers[5] = new Flower(75,75,BufferedImage.TYPE_INT_ARGB, 370, 550);
        flowers[6] = new Flower(75,75,BufferedImage.TYPE_INT_ARGB, 440, 530);
        flowers[7] = new Flower(75,75,BufferedImage.TYPE_INT_ARGB, 510, 570);

        flowers[8] = new Flower(75,75,BufferedImage.TYPE_INT_ARGB, 640, 410);
        flowers[9] = new Flower(75,75,BufferedImage.TYPE_INT_ARGB, 710, 450);

        Curve curve = new Curve(bufferedImage);
        thingOnFloor = curve.getPointsOfInfinite(50,new MyPoint(100,550));
        thingOnFloorTwo = curve.getPointsOfInfinite(50,new MyPoint(450,580));

    }

    public void startAnimation(){
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        OutlineShape outlineShape = new OutlineShape();
        if (timeOnRealSeconds < 23){
            Polygon landScape = new Polygon(bufferedImage);
            landScape.addVert(new MyPoint(0,400));
            landScape.addVert(new MyPoint(0,0));
            landScape.addVert(new MyPoint(800,0));
            landScape.addVert(new MyPoint(800,400));
            landScape.changeColor(new Color(183, 235, 239));
            Flood.apply(landScape.drawPolygon());

            //Buildings
            Building building = new Building(bufferedImage, new MyPoint(10,50));
            building.drawBuildingType1();

            Building building1 = new Building(bufferedImage, new MyPoint(210, 80));
            building1.scale(0.8, 0.8);
            building1.setColorOfBuilding(new Color(193, 131, 33));
            building1.setColorOfWindowBuilding(new Color(116, 79, 19));
            building1.drawBuildingType1();

            Building building2 = new Building(bufferedImage, new MyPoint(280, 90));
            building2.scale(1.1, 1.1);
            building2.setColorOfBuilding(new Color(227, 80, 64));
            building2.setColorOfWindowBuilding(new Color(151, 53, 43));
            building2.drawBuildingType1();

            Building building3 = new Building(bufferedImage, new MyPoint(480,50));
            building3.drawBuildingType1();

            Building building4 = new Building(bufferedImage, new MyPoint(930, 90));
            building4.scale(0.7, 0.7);
            building4.setColorOfBuilding(new Color(227, 80, 64));
            building4.setColorOfWindowBuilding(new Color(151, 53, 43));
            building4.drawBuildingType1();

            //Floor
            Polygon floor = new Polygon(bufferedImage);
            floor.changeColor(new Color(92, 165, 92));
            floor.addVert(new MyPoint(0,800));
            floor.addVert(new MyPoint(0,400));
            floor.addVert(new MyPoint(800,400));
            floor.addVert(new MyPoint(800,800));
            Flood.apply(floor.drawPolygon());
            for (int i = 0; i < 10; i++) {
                flowers[i].setGraphics(graphics);
            }

            //Street
            Polygon polygon = new Polygon(bufferedImage);
            polygon.changeColor(Color.gray);
            polygon.addVert(new MyPoint(0,400));
            polygon.addVert(new MyPoint(0,300));
            polygon.addVert(new MyPoint(800,300));
            polygon.addVert(new MyPoint(800,400));
            Flood.apply(polygon.drawPolygon());
            outlineShape.changeColor(Color.YELLOW);
            Flood.apply(outlineShape.drawRectangle(100, 350,40,10, bufferedImage, graphics));
            Flood.apply(outlineShape.drawRectangle(250, 350,40,10, bufferedImage, graphics));
            Flood.apply(outlineShape.drawRectangle(400, 350,40,10, bufferedImage, graphics));
            Flood.apply(outlineShape.drawRectangle(550, 350,40,10, bufferedImage, graphics));
            Flood.apply(outlineShape.drawRectangle(700, 350,40,10, bufferedImage, graphics));

            truck.setVertexOfTruck();
            truck.draw();
            graphics.setColor(Color.BLACK);
            graphics.drawString(String.valueOf(timeOnRealSeconds),10, 10);
        }
        if (timeOnRealSeconds == 23){
            truck.resetTruck();
            drawLandScapeTwo(outlineShape);
        }

        if (timeOnRealSeconds >= 24){
            drawLandScapeTwo(outlineShape);
            truck.setVertexOfTruck();
            truck.draw();
            graphics.setColor(Color.BLACK);
            graphics.drawString(String.valueOf(timeOnRealSeconds),10, 10);
        }
        resetBuffer(g);
    }


    private void resetBuffer(Graphics g) {
        g.drawImage(bufferedImage, 0, 0, null);
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                bufferedImage.setRGB(i,j,Color.WHITE.getRGB());
            }
        }
    }

    @Override
    public void run() {
        double FPS = 60;
        double drawInterval = 1_000_000_000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (thread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 100_000_000) {
                drawCount = 0;
                timer = 0;
                Transform.translate(truck.getPolygon(),5, 0);
                timeOnSeconds++;
                if (timeOnSeconds % 10 == 0){
                    timeOnRealSeconds++;
                }
            }
        }
    }
    public void drawLandScapeTwo(OutlineShape outlineShape){
        Polygon landScape2 = new Polygon(bufferedImage);
        landScape2.addVert(new MyPoint(0,400));
        landScape2.addVert(new MyPoint(0,0));
        landScape2.addVert(new MyPoint(800,0));
        landScape2.addVert(new MyPoint(800,400));
        landScape2.changeColor(new Color(183, 235, 239));
        Flood.apply(landScape2.drawPolygon());

        Sun sun = new Sun(100,150,BufferedImage.TYPE_INT_ARGB);
        sun.setGraphics(graphics);

        Color colorOfMontania = new Color(244,203,114);
        Curve curve = new Curve(bufferedImage);
        curve.setColor(Color.BLACK);
        drawMountain(curve, new MyPoint(50, 400), colorOfMontania);
        drawMountain(curve, new MyPoint(240,400), colorOfMontania);
        drawMountain2(curve, new MyPoint(400, 400), colorOfMontania);
        drawMountain2(curve, new MyPoint(500, 400), colorOfMontania);
        drawMountain(curve, new MyPoint(600,400), colorOfMontania);


        Polygon polygon = new Polygon(bufferedImage);
        polygon.changeColor(Color.gray);
        polygon.addVert(new MyPoint(0,500));
        polygon.addVert(new MyPoint(0,400));
        polygon.addVert(new MyPoint(800,400));
        polygon.addVert(new MyPoint(800,500));
        Flood.apply(polygon.drawPolygon());
        outlineShape.changeColor(Color.YELLOW);
        Flood.apply(outlineShape.drawRectangle(100, 450,40,10, bufferedImage, graphics));
        Flood.apply(outlineShape.drawRectangle(250, 450,40,10, bufferedImage, graphics));
        Flood.apply(outlineShape.drawRectangle(400, 450,40,10, bufferedImage, graphics));
        Flood.apply(outlineShape.drawRectangle(550, 450,40,10, bufferedImage, graphics));
        Flood.apply(outlineShape.drawRectangle(700, 450,40,10, bufferedImage, graphics));

        Polygon floor = new Polygon(bufferedImage);
        Color grassColor = new Color(92, 165, 92);
        floor.changeColor(grassColor);
        floor.addVert(new MyPoint(0,800));
        floor.addVert(new MyPoint(0,500));
        floor.addVert(new MyPoint(800,500));
        floor.addVert(new MyPoint(800,800));
        Flood.apply(floor.drawPolygon());

        curve.setColor(Color.BLACK);
        curve.drawParticleMovement(new MyPoint(200,50),3,200);
        curve.drawParticleMovement(new MyPoint(400,75),3,200);
        curve.drawParticleMovement(new MyPoint(400,150),3,200);

        Polygon thing = new Polygon(bufferedImage, thingOnFloor);
        thing.changeColor(Color.BLACK);
        thing.drawPolygon();
        thing.setVerts(thingOnFloor);

        Flood.apply(new Shape(new ArrayList<>(),centers[0].getX(),centers[0].getY(),
                bufferedImage,graphics,Color.RED,
                Shape.Figure.RECTANGLE),new int[]{0,grassColor.getRGB()});
        Flood.apply(new Shape(new ArrayList<>(),centers[1].getX(),centers[1].getY(),
                bufferedImage,graphics,Color.RED,
                Shape.Figure.RECTANGLE),new int[]{0,grassColor.getRGB()});

        Polygon thing2 = new Polygon(bufferedImage, thingOnFloorTwo);
        thing2.changeColor(Color.BLACK);
        thing2.drawPolygon();
        thing2.setVerts(thingOnFloorTwo);

        Flood.apply(new Shape(new ArrayList<>(),centers2[0].getX(),centers2[0].getY(),
                bufferedImage,graphics,Color.RED,
                Shape.Figure.RECTANGLE),new int[]{0,grassColor.getRGB()});
        Flood.apply(new Shape(new ArrayList<>(),centers2[1].getX(),centers2[1].getY(),
                bufferedImage,graphics,Color.RED,
                Shape.Figure.RECTANGLE),new int[]{0,grassColor.getRGB()});
    }
    private void drawMountain(Curve curve, MyPoint position, Color colorOfMontania){
        MyPoint[] montania = curve.getPointsOfCurve(100,new MyPoint(position.getX(),position.getY()),50,300);
        Polygon polygonOfMontania = new  Polygon(bufferedImage,montania);
        polygonOfMontania.changeColor(colorOfMontania);
        Flood.apply(polygonOfMontania.drawPolygon());

        MyPoint midPointOfMontania = polygonOfMontania.getMidPoint(montania);
        MyPoint[] cresta = curve.getPointsOfCurve(50, new MyPoint(
                midPointOfMontania.getX()-45, midPointOfMontania.getY()), 29,110);
        Polygon crestaOfMontania = new Polygon(bufferedImage,cresta);
        crestaOfMontania.changeColor(Color.WHITE);
        Flood.apply(crestaOfMontania.drawPolygon(), new int[]{0, colorOfMontania.getRGB()});
        drawFillTriangle(new MyPoint[]{
                new MyPoint(midPointOfMontania.getX()-23, midPointOfMontania.getY()+20),
                new MyPoint(midPointOfMontania.getX()-45,midPointOfMontania.getY()),
                new MyPoint(midPointOfMontania.getX(),midPointOfMontania.getY())},Color.WHITE, colorOfMontania);
        drawFillTriangle(new MyPoint[]{
                new MyPoint(midPointOfMontania.getX()+23, midPointOfMontania.getY()+20),
                new MyPoint(midPointOfMontania.getX(),midPointOfMontania.getY()),
                new MyPoint(midPointOfMontania.getX()+45,midPointOfMontania.getY())},Color.WHITE, colorOfMontania);
    }

    private void drawMountain2(Curve curve, MyPoint position, Color colorOfMontania){
        MyPoint[] montania = curve.getPointsOfCurve(100,new MyPoint(position.getX(),position.getY()),25,150);
        Polygon polygonOfMontania = new  Polygon(bufferedImage,montania);
        polygonOfMontania.changeColor(colorOfMontania);
        Flood.apply(polygonOfMontania.drawPolygon());

        MyPoint midPointOfMontania = polygonOfMontania.getMidPoint(montania);
        MyPoint[] cresta = curve.getPointsOfCurve(50, new MyPoint(
                midPointOfMontania.getX()-22, midPointOfMontania.getY()), 15,55);
        Polygon crestaOfMontania = new Polygon(bufferedImage,cresta);
        crestaOfMontania.changeColor(Color.WHITE);
        Flood.apply(crestaOfMontania.drawPolygon(), new int[]{0, colorOfMontania.getRGB()});
        drawFillTriangle(new MyPoint[]{
                new MyPoint(midPointOfMontania.getX()-12, midPointOfMontania.getY()+20),
                new MyPoint(midPointOfMontania.getX()-23,midPointOfMontania.getY()),
                new MyPoint(midPointOfMontania.getX(),midPointOfMontania.getY())},Color.WHITE, colorOfMontania);
        drawFillTriangle(new MyPoint[]{
                new MyPoint(midPointOfMontania.getX()+12, midPointOfMontania.getY()+20),
                new MyPoint(midPointOfMontania.getX(),midPointOfMontania.getY()),
                new MyPoint(midPointOfMontania.getX()+23,midPointOfMontania.getY())},Color.WHITE, colorOfMontania);
    }

    private void drawFillTriangle(MyPoint[] points, Color color, Color colorToCompare){
        int[] colors = new int[3];
        if (colorToCompare != null) {
            colors[1] = colorToCompare.getRGB();
            colors[2] = Color.gray.getRGB();
        }
        Polygon triangle = new Polygon(bufferedImage);
        triangle.changeColor(color);
        triangle.addVert(new MyPoint(points[0].getX(), points[0].getY()));
        triangle.addVert(new MyPoint(points[1].getX(), points[1].getY()));
        triangle.addVert(new MyPoint(points[2].getX(), points[2].getY()));
        Flood.apply(triangle.drawPolygon(), colors);
    }
}
