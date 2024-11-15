/*
 * Empanakkkkk
 * IA
 * CMeans
 * 5°4
 */
package ClusteringCMeans;

public class PointsAndCentroidsTable {
    private int pointsQuantity, centroidsQuantity;
    private float[][] points, centroids;
    private float fuzzyParam;
    private float maxX = Float.MIN_VALUE, maxY = Float.MIN_VALUE;

    public PointsAndCentroidsTable(int pointsQuantity, int centroidsQuantity) {
        this.pointsQuantity = pointsQuantity;
        this.centroidsQuantity = centroidsQuantity;
        this.points = new float[pointsQuantity][2];
        this.centroids = new float[centroidsQuantity][2];
        this.fuzzyParam = -1;
    }

    public int getPointsQuantity() {
        return pointsQuantity;
    }

    public int getCentroidsQuantity() {
        return centroidsQuantity;
    }

    public float[][] getPoints() {
        return points;
    }

    public void setPoints(float[][] points) {
        if(points[0].length != 2){
            throw new IllegalArgumentException("The array must have 2 columns");
        }
        pointsQuantity = points.length;
        this.points = points;
        for(int i = 0; i < points.length; i++){
            if(points[i][0] > maxX){
                maxX = points[i][0];
            }
            if(points[i][1] > maxY){
                maxY = points[i][1];
            }
        }
    }

    public float getMaxX() {
        return maxX;
    }

    public float getMaxY() {
        return maxY;
    }

    public float[][] getCentroids() {
        return centroids;
    }

    public void setCentroids(float[][] centroids) {
        if(centroids[0].length != 2){
            throw new IllegalArgumentException("The array must have 2 columns");
        }
        centroidsQuantity = centroids.length;
        this.centroids = centroids;
        for(int i = 0; i < centroids.length; i++){
            if(centroids[i][0] > maxX){
                maxX = centroids[i][0];
            }
            if(centroids[i][1] > maxY){
                maxY = centroids[i][1];
            }
        }

    }

    public void setPointsQuantity(int pointsQuantity) {
        float[][] aux = new float[pointsQuantity][2];
        System.arraycopy(this.points, 0, aux, 0, pointsQuantity);
        this.points = aux;
        this.pointsQuantity = pointsQuantity;
    }

    public void setCentroidsQuantity(int centroidsQuantity) {
        float[][] aux = new float[centroidsQuantity][2];
        System.arraycopy(this.centroids, 0, aux, 0, centroidsQuantity);
        this.centroids = aux;
        this.centroidsQuantity = centroidsQuantity;
    }

    public float getFuzzyParam() {
        return fuzzyParam;
    }

    public void setFuzzyParam(float fuzzyParam) {
        this.fuzzyParam = fuzzyParam;
    }

    public boolean validation(){
        if(points == null || centroids == null || pointsQuantity == 0 || centroidsQuantity == 0 || fuzzyParam == -1){
            return false;
        }
        if(points.length != pointsQuantity || centroids.length != centroidsQuantity){
            return false;
        }
        if(points[0].length != 2 || centroids[0].length != 2){
            return false;
        }
        return true;
    }
}
