public class Neighbourhood {
    String name;
    double x1, y1; // left down point
    double x2, y2; // right up point
    int numberOfBanks;

    public Neighbourhood(String name, double x1, double y1, double x2, double y2) {
        this.numberOfBanks = 0;
        this.name = name;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    void increaseNumberOfBanks(){
        this.numberOfBanks++;
    }

    public int getNumberOfBanks() {
        return numberOfBanks;
    }

    @Override
    public String toString() {
        return "NEIGHBOURHOOD["+name + "(" + x1+","+y1+")"+ "(" + x2+","+y2+")]";
    }
}
