import java.util.ArrayList;

public class DataBase {
    Bank bankWithMostBranches = null;
    KDTree banks = new KDTree();
    ArrayList<Bank> bankArrayList = new ArrayList<>();
    ArrayList<Neighbourhood> neighbourhoods = new ArrayList<>();
    Bank search(String name){
        for (Bank b:bankArrayList) {
            if (name.equals(b.name)){
                return b;
            }
        }
        return null;
    }
    boolean addBank(String name, int x, int y){
        Bank newBank = new Bank(name, x, y);
        bankArrayList.add(newBank); //TODO
        Node node = new Node(newBank, x, y);
        banks._root = banks.insert(banks._root ,node, 0);
        banks.printTree(banks._root);
        System.out.println();
        return true;
    }

    boolean addBranch(String name, String branchName, int x, int y){
        Branch newBranch = new Branch(name, branchName, x, y);
        Node node = new Node(newBranch, x, y);
        Bank origin = search(name);
        origin.numberOfBranches++;
        KDTree branches = origin.branches;
        branches._root = branches.insert(branches._root ,node, 0);
        branches.printTree(branches._root);
        System.out.println();
        if (bankWithMostBranches == null){
            bankWithMostBranches = origin;
        }else{
            if (origin.numberOfBranches > bankWithMostBranches.numberOfBranches){
                bankWithMostBranches = origin;
            }
        }
        return true;
    }
    public void getListOfBranches(String name){
        Bank origin = search(name);
        origin.branches.printTree(origin.branches._root);
    }
    void addNeighbourhood(String name, String[] coordinates){
    int minX = 100000;
    int minY = 100000;
    int maxX = -100000;
    int maxY = -100000;
        for (String coordinate:coordinates) {
            //coordinate = coordinate.split(1,)
        }
    }
}
