import java.util.ArrayList;

public class DataBase {
    KDTree banks = new KDTree();
    ArrayList<Bank> bankArrayList = new ArrayList<>();
    boolean addBank(String name, int x, int y){
        Bank newBank = new Bank(name, x, y);
        bankArrayList.add(newBank); //TODO
        Node node = new Node(newBank);
        banks._root = banks.insert(banks._root ,node, 0);
        banks.printTree(banks._root);
        System.out.println();
        return true;
    }
    boolean addBranch(String name, String branchName, int x, int y){
        Bank newBranch = new Bank(name, branchName, x, y);
        Node node = new Node(newBranch);
        Bank origin = null;
        for (Bank b:bankArrayList) {
            if (name.equals(b.name)){
                origin = b;
                break;
            }
        }
        KDTree branches = origin.branches;
        branches._root = branches.insert(branches._root ,node, 0);
        branches.printTree(branches._root);
        System.out.println();
        return true;
    }
}
