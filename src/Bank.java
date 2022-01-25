public class Bank {
    String name;
    String branchName;
    int x, y, numberOfBranches;
    KDTree branches;
    public Bank(String name, int x, int y){
        this.name = name;
        this.branchName = name;
        this.x = x;
        this.y = y;
        this.numberOfBranches = 0;
        branches = new KDTree();
    }
    public Bank(String originName, String name, int x, int y){
        this.name = originName;
        this.branchName = name;
        this.x = x;
        this.y = y;
        this.numberOfBranches = 0;
    }

    @Override
    public String toString() {
        return this.branchName+"(" +this.x+", "+this.y + ")";
    }
}
