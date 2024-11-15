package planoCart;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CartesianPlane extends JPanel {
    private final int POINTSIZE = 6;
    private List<ColoredPoint> points;
    private int maxDistanceX, maxDistanceY, minDistanceX, minDistanceY;
    private int quantityPoints = 30, maxCoordValue;

    public CartesianPlane(){
        setBorder(BorderFactory.createBevelBorder(0));
        this.points = new ArrayList<>();
    }

    public void addPoint(ColoredPoint point){
        //quantityPoints = getQuantityPoints(maxX, maxY);
        this.points.add(point);
        repaint();
    }

    public int getQuantityPoints(double a, double b){
        double max = Math.max(a,b);

        int addXSpaces = String.valueOf((int)max).length();

        String spaces = "1"+"0".repeat(addXSpaces);
        addXSpaces = (Integer.parseInt(spaces)/10 + (int)max)*2;

        return addXSpaces;
    }

    @Override
    public void paintComponent(Graphics g){
        points.sort(new Comparator<ColoredPoint>() {
            @Override
            public int compare(ColoredPoint o1, ColoredPoint o2) {
                if(o1.y < o2.y) return -1;
                return o1.x < o2.x ? -1 : 1;
            }
        });
        float[] max = new float[2];
        if(!points.isEmpty()) {
            max[0] = Math.max(Math.abs(points.get(0).x), Math.abs(points.get(points.size() - 1).x));
            max[1] = Math.max(Math.abs(points.get(0).y), Math.abs(points.get(points.size() - 1).y));
            quantityPoints = getQuantityPoints(max[0], max[1]);
        }
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        double addCoord = 1;
        boolean isAlterated = false;
        if(quantityPoints>30) {
            addCoord = (double) (quantityPoints / 2) / 15;
            isAlterated = true;
            quantityPoints = 30;
        }
        int bound = (width / (width /quantityPoints)), space = width / quantityPoints;
        double valueCoordinate = 0;
        g2d.setColor(Color.BLACK);
        //ZeroPoint
        g2d.fillOval((width/2)-(POINTSIZE/2), (height/2)-(POINTSIZE/2), POINTSIZE, POINTSIZE);
        //Right Side
        for(int i=0; i<bound; i++){
            maxCoordValue = Math.max(maxCoordValue, (int)valueCoordinate);
            //System.out.println("Pintandoooo");
            g2d.fillOval((width/2) - POINTSIZE/2 + space, (height/2) - (POINTSIZE/2), POINTSIZE, POINTSIZE);
            g2d.fillOval((width/2) - (POINTSIZE/2), (height/2) - (POINTSIZE/2) + space, POINTSIZE, POINTSIZE);

            String coordStr = String.format("%.0f", valueCoordinate+addCoord);

            g2d.drawString(coordStr, (width/2) - (POINTSIZE/2) + space, (height/2) + (POINTSIZE*3));
            g2d.drawString('-' + coordStr, (width/2) - (POINTSIZE*3), (height/2) + (POINTSIZE/2) + space);

            if(isAlterated){
                valueCoordinate+=addCoord;
            }else
                valueCoordinate++;

            space += width / quantityPoints;
        }
        //Left Side
        space = width / quantityPoints;
        valueCoordinate = 0;
        for(int i=bound; i>0; i--){
            maxCoordValue = Math.max(maxCoordValue, (int)valueCoordinate);
            g2d.fillOval((width/2) - (POINTSIZE/2) - space, (height/2) - (POINTSIZE/2), POINTSIZE, POINTSIZE);
            g2d.fillOval((width/2) - (POINTSIZE/2), (height/2) - (POINTSIZE/2) - space, POINTSIZE, POINTSIZE);

            String coordStr = String.format("%.0f", valueCoordinate+addCoord);

            g2d.drawString('-' + coordStr, (width/2) - (POINTSIZE/2) - space, (height/2) + (POINTSIZE*3));
            g2d.drawString(coordStr, (width/2) - (POINTSIZE*3), (height/2) + (POINTSIZE/2) - space);

            if(isAlterated){
                valueCoordinate+=addCoord;
            }else
                valueCoordinate++;

            space += width / quantityPoints;
        }

        g2d.drawLine(0, height/2, width, height/2);
        g2d.drawLine(width/2, 0, width/2, height);

        for(int i = 0; i < points.size(); i++){
            System.out.println("Pintando punto: " + i + " x: " + points.get(i).x + " y: " + points.get(i).y);
            ColoredPoint point = points.get(i);
            g2d.setColor(point.color);
            System.out.println("MAX COORD VALUE -> " + maxCoordValue);
            System.out.println("QUANTITY POINTS -> " + quantityPoints);
            System.out.println("SPACE -> " + space);
            int pointX = (int) Math.round((double) width / 2 + (point.x * ((double) (width / (quantityPoints)))));
            int pointY = (int) Math.round((double) height / 2 - (point.y * ((double) (height / (quantityPoints)))));
            g2d.fillOval(pointX - 3, pointY - 3, 6, 6);
        }

    }

}
