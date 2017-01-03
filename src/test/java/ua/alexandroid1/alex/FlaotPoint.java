package ua.alexandroid1.alex;

/**
 * Created by Oleksandr on 03.01.2017.
 */
class DoublePoint {
    private double dLat, dLon;

    public DoublePoint(double dLat, double dLon) {
        this.dLat = dLat;
        this.dLon = dLon;
    }

    public double getdLat() {
        return dLat;
    }

    public double getdLon() {
        return dLon;
    }

    public void setdLat(double dLat) {
        this.dLat = dLat;
    }

    public void setdLon(double dLon) {
        this.dLon = dLon;
    }

    public void increasedLat() {
        this.dLat = dLat + .00000001d;
    }

    public void increasedLon() {
        this.dLon = dLon + .00000001d;
    }
}
