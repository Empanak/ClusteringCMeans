package ClusteringCMeans;

public class ResultsCMeansFuzzy {
    private float[][] distances;
    private float[][] memberships;
    private float[][] newCentroids;
    private float[]cost;
    private float totalCost;

    public ResultsCMeansFuzzy(float[][] distances, float[][] memberships, float[][] newCentroids, float[] cost, float totalCost) {
        this.distances = distances;
        this.memberships = memberships;
        this.newCentroids = newCentroids;
        this.cost = cost;
        this.totalCost = totalCost;
    }

    public ResultsCMeansFuzzy() {
        this.distances = null;
        this.memberships = null;
        this.newCentroids = null;
        this.cost = null;
        this.totalCost = 0;
    }

    public float[][] getDistances() {
        return distances;
    }

    public float[][] getMemberships() {
        return memberships;
    }

    public float[][] getNewCentroids() {
        return newCentroids;
    }

    public float[] getCost() {
        return cost;
    }

    protected void setDistances(float[][] distances) {
        this.distances = distances;
    }

    protected void setMemberships(float[][] memberships) {
        this.memberships = memberships;
    }

    protected void setNewCentroids(float[][] newCentroids) {
        this.newCentroids = newCentroids;
    }

    protected void setCost(float[] cost) {
        this.cost = cost;
    }

    protected void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public float getTotalCost() {
        return totalCost;
    }
}
