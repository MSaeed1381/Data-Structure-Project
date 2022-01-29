public class Branch {
    String bankName;
    String branchName;
    int x, y;
    Branch(String bankName, String branchName, int x, int y){
        this.bankName = bankName;
        this.branchName = branchName;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "BRANCH["+branchName+"("+x+","+y+")]";
    }
}
