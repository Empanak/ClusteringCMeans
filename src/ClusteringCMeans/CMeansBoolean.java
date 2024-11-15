package ClusteringCMeans;

public abstract class CMeansBoolean {
    private PointsAndCentroidsTable auxTable;

    private float [][] auxDistances;
    private int [] auxCardinality;
    private boolean [][] auxBooleanMembership;
    private float [][] auxCentroids;
    private float [] auxCost;
    private float auxTotalCost;

    private void calculateDistance() {
        float [][] distances = new float[auxTable.getCentroidsQuantity()][auxTable.getPointsQuantity()];
        float [][] tempCentroids = auxTable.getCentroids();
        float [][] tempPoints = auxTable.getPoints();
        for(int i = 0; i < auxTable.getCentroidsQuantity(); i++) {
            for (int j = 0; j < auxTable.getPointsQuantity(); j++) {
                distances[i][j] = (float)Math.sqrt(Math.pow((tempCentroids[i][0] - tempPoints[j][0]), 2) + Math.pow(tempCentroids[i][1] - tempPoints[j][1], 2));
                System.out.printf("%.4f    ", distances[i][j]);
            }
            System.out.println();
        }
        this.auxDistances = distances;
    }

    private void calculateMembership() {
        int [] cardinality = new int[auxTable.getCentroidsQuantity()];
        boolean [][] booleanMembership = new boolean[auxTable.getCentroidsQuantity()][auxTable.getPointsQuantity()];
        for (int i = 0; i < auxTable.getPointsQuantity(); i++) {
            float min = Float.MAX_VALUE;
            int pos = -1;
            for (int j = 0; j < auxTable.getCentroidsQuantity(); j++) {
                if (auxDistances[j][i] < min) {
                    min = auxDistances[j][i];
                    pos = j;
                }
            }
            booleanMembership[pos][i] = true;
            cardinality[pos]++;
        }
        this.auxCardinality = cardinality;
        this.auxBooleanMembership = booleanMembership;
    }
    
    private void calculateNewCentroids() {
        float [][] newCentroids = new float[auxTable.getCentroidsQuantity()][2];
        float [][] tempPoints = auxTable.getPoints();
        for (int i = 0; i < auxTable.getCentroidsQuantity(); i++) {
            float card = auxCardinality[i] != 0 ? (float) 1 / auxCardinality[i] : (float)Math.pow(10, -7);
            for (int j = 0; j < auxTable.getPointsQuantity(); j++) {
                if (auxBooleanMembership[i][j]) {
                    newCentroids[i][0] += tempPoints[j][0];
                    newCentroids[i][1] += tempPoints[j][1];
                }
            }
            newCentroids[i][0] *= card;
            newCentroids[i][1] *= card;
            System.out.println("Centroide " + (i + 1) + ": (" + newCentroids[i][0] + ", " + newCentroids[i][1] + ")");
        }
        this.auxCentroids = newCentroids;        
    }
    
    private void calculateCosts() {
        float [] cost = new float[auxCentroids.length];
        float totalCost = 0;

        for(int i = 0; i < auxCentroids.length; i++){
            cost[i] = 0;
            for(int j = 0; j < auxTable.getPointsQuantity(); j++){
                if (auxBooleanMembership[i][j]) {
                    cost[i] += auxDistances[i][j];
                }
            }
            totalCost += cost[i];
            System.out.println("j" + (i + 1) + " = " + cost[i]);
        }
        System.out.println("J = " + totalCost);

        this.auxCost = cost;
        this.auxTotalCost = totalCost;
    }

    public ResultsCMeansBoolean getResultsCMeansBoolean(PointsAndCentroidsTable auxTable) {
        this.auxTable = auxTable;
        ResultsCMeansBoolean resultsCMeansBoolean = new ResultsCMeansBoolean();

        calculateDistance();
        calculateMembership();
        calculateNewCentroids();
        calculateCosts();

        resultsCMeansBoolean.setDistances(auxDistances);
        resultsCMeansBoolean.setCardinality(auxCardinality);
        resultsCMeansBoolean.setMembership(auxBooleanMembership);
        resultsCMeansBoolean.setNewCentroids(auxCentroids);
        resultsCMeansBoolean.setCost(auxCost);
        resultsCMeansBoolean.setTotalCost(auxTotalCost);

        return resultsCMeansBoolean;
    }

}
