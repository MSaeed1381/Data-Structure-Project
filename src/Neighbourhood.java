public class Neighbourhood {
    String name;
    int x1, y1; // left down point
    int x2, y2; // right up point
    int numberOfBanks;

    public Neighbourhood(String name, int x1, int y1, int x2, int y2) {
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
