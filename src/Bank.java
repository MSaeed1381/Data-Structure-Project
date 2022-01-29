public class Bank {
    String name;
    double x, y, numberOfBranches;
    KDTree branches;
    public Bank(String name, double x, double y){
        this.name = name;
        this.x = x;
        this.y = y;
        this.numberOfBranches = 0;
        branches = new KDTree();
    }

    @Override
    public String toString() {
        return "BANK["+this.name+"(" +this.x+","+this.y + ")]";
    }
}
