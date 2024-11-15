package ClusteringCMeans;

public class ResultsCMeansBoolean {
    private float[][] distances;
    private int[] cardinality;
    private boolean[][] membership;
    private float[][] newCentroids;
    private float[] cost;
    private float totalCost;

    public ResultsCMeansBoolean(float[][] distances, int[] cardinality, boolean[][] membership, float[][] newCentroids, float[] cost) {
        this.distances = distances;
        this.cardinality = cardinality;
        this.membership = membership;
        this.newCentroids = newCentroids;
        this.cost = cost;
        this.totalCost = 0;
    }

    public ResultsCMeansBoolean() {
        this.distances = null;
        this.cardinality = null;
        this.membership = null;
        this.newCentroids = null;
        this.cost = null;
        this.totalCost = 0;
    }

    public float[][] getDistances() {
        return distances;
    }

    protected void setDistances(float[][] distances) {
        this.distances = distances;
    }

    public int[] getCardinality() {
        return cardinality;
    }

    protected void setCardinality(int[] cardinality) {
        this.cardinality = cardinality;
    }

    public boolean[][] getMembership() {
        return membership;
    }

    protected void setMembership(boolean[][] membership) {
        this.membership = membership;
    }

    public float[][] getNewCentroids() {
        return newCentroids;
    }

    protected void setNewCentroids(float[][] newCentroids) {
        this.newCentroids = newCentroids;
    }

    public float[] getCost() {
        return cost;
    }

    protected void setCost(float[] cost) {
        this.cost = cost;
    }

    public float getTotalCost() {
        return totalCost;
    }

    protected void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }
}