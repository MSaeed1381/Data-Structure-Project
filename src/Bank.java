public class Bank {
    String name;
    int x, y, numberOfBranches;
    KDTree branches;
    public Bank(String name, int x, int y){
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
