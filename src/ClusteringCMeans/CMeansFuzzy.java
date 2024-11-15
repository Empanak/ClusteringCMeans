package ClusteringCMeans;

public abstract class CMeansFuzzy {
    private ResultsCMeansFuzzy resultsCMeansFuzzy;
    private PointsAndCentroidsTable auxTable;
    private float [][] auxDistances;
    private float [][] auxFuzzyMembership;
    private float [][] auxCentroids;
    private float [] auxCost;
    private float auxTotalCost;

    private void calculateDistance() {
        float[][] distances = new float[auxTable.getCentroidsQuantity()][auxTable.getPointsQuantity()];
        float[][] tempCentroids = auxTable.getCentroids();
        float[][] tempPoints = auxTable.getPoints();
        for (int i = 0; i < tempCentroids.length; i++) {
            for (int j = 0; j < tempPoints.length; j++) {
                distances[i][j] = (float) Math.sqrt(Math.pow((tempCentroids[i][0] - tempPoints[j][0]), 2) + Math.pow(tempCentroids[i][1] - tempPoints[j][1], 2));
                System.out.printf("%.4f    ", distances[i][j]);
            }
            System.out.println();
        }
        this.auxDistances = distances;
    }

    private void calculateMembership() {
        float [][] memberships = new float[auxTable.getCentroidsQuantity()][auxTable.getPointsQuantity()];
        for (int i = 0; i < auxTable.getCentroidsQuantity(); i++) {
            for (int j = 0; j < auxTable.getPointsQuantity(); j++) {
                float sum = 0;
                for(int k = 0; k < auxTable.getCentroidsQuantity(); k++){
                    sum += (float) Math.pow((auxDistances[i][j] / auxDistances[k][j]), (2 / (auxTable.getFuzzyParam() - 1)));
                }
                memberships[i][j] = 1 / sum;
                System.out.printf("%.4f    ", memberships[i][j]);
            }
            System.out.println();
        }
        this.auxFuzzyMembership = memberships;
    }

    private void calculateNewCentroids() {
        float [][] newCentroids = new float[auxTable.getCentroidsQuantity()][2];
        float [][] tempPoints = auxTable.getPoints();
        for (int i = 0; i < auxTable.getCentroidsQuantity(); i++) {
            float sumDen = 0;
            for (int j = 0; j < auxTable.getPointsQuantity(); j++) {
                float membresiaElevada = (float)Math.pow(auxFuzzyMembership[i][j], auxTable.getFuzzyParam());
                sumDen += membresiaElevada;
                newCentroids[i][0] += membresiaElevada * tempPoints[j][0];
                newCentroids[i][1] += membresiaElevada * tempPoints[j][1];
            }
            newCentroids[i][0] /= sumDen;
            newCentroids[i][1] /= sumDen;
            System.out.println("C" + (i+1) + ": (" + newCentroids[i][0] + ", " + newCentroids[i][1] + ")");
        }
        this.auxCentroids = newCentroids;
    }

    private void calculateCosts() {
        float [] cost = new float[auxTable.getCentroidsQuantity()];
        float totalCost = 0;
        for(int i = 0; i < auxTable.getCentroidsQuantity(); i++){
            cost[i] = 0;
            for(int j = 0; j < auxTable.getPointsQuantity(); j++){
                cost[i] += (float)(Math.pow(auxFuzzyMembership[i][j], auxTable.getFuzzyParam()) * Math.pow(auxDistances[i][j], 2));
            }
            System.out.println("j" + (i + 1) + " = " + cost[i]);
            totalCost += cost[i];
        }
        System.out.println("J = " + totalCost);
        this.auxCost = cost;
        this.auxTotalCost = totalCost;
    }

    public ResultsCMeansFuzzy getResultsCMeansFuzzy(PointsAndCentroidsTable auxTable) {
        this.auxTable = auxTable;
        resultsCMeansFuzzy = new ResultsCMeansFuzzy();

        calculateDistance();
        calculateMembership();
        calculateNewCentroids();
        calculateCosts();

        resultsCMeansFuzzy.setDistances(auxDistances);
        resultsCMeansFuzzy.setMemberships(auxFuzzyMembership);
        resultsCMeansFuzzy.setNewCentroids(auxCentroids);
        resultsCMeansFuzzy.setCost(auxCost);
        resultsCMeansFuzzy.setTotalCost(auxTotalCost);

        return resultsCMeansFuzzy;
    }
}
