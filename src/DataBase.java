public class DataBase {
    Bank bankWithMostBranches = null;
    KDTree banks = new KDTree();
    TrieTree bankTrieTree = new TrieTree();
    TrieTree neighbourhoodsTrieTree = new TrieTree();
    BST banksBST = new BST();
    int numberOfAvailBanks;

    DataBase(){
        this.numberOfAvailBanks = 0;
        bankTrieTree.root = new TNode();
        neighbourhoodsTrieTree.root = new TNode();
    }
    public String addBank(String name, double x, double y){
        String answer;
        if (banks.isExistPoint(x, y)){
            answer = "there is a bank at this point :(";
        }else{
            Bank newBank = new Bank(name, x, y);
            bankTrieTree.add(newBank.name, newBank);
            Node node = new Node(newBank, x, y);
            banks._root = banks.insert(banks._root ,node, 0);
            answer = "bank " + newBank + " added to system :)";
        }
        return answer;
    }

    public String addBranch(String name, String branchName, double x, double y){
        String answer;
        if (banks.isExistPoint(x, y)){
            answer = "there is a bank at this point :(";
        }
        else {
            if (bankTrieTree.search(name) == null){
                answer = "the bank " + name + " doesn't exist :/";
            }else {
                Branch newBranch = new Branch(name, branchName, x, y);
                Node node = new Node(newBranch, x, y);
                Bank origin = (Bank) bankTrieTree.search(name).object;
                //--------
                Node n = new Node(origin, origin.x, origin.y);
                banksBST.delete(n);
                origin.numberOfBranches++;
                banksBST.insert(n);
                //--------
                KDTree branches = origin.branches;
                branches._root = branches.insert(branches._root ,node, 0);
                Node node1 = new Node(new Branch(name, branchName, x, y), x, y);
                banks._root = banks.insert(banks._root, node1, 0);
                answer = "the branch "+newBranch + " added to bank "+name + " branches :)";
            }
        }
        return answer;
    }
    public void getListOfBranches(String name){
        try {
            TNode tNode = bankTrieTree.search(name);
            if (tNode == null){
                System.out.println("bank '"+ name + "' doesn't exist!");
            }else{
                Bank origin = (Bank) tNode.object;
                if (!origin.branches.isEmpty()){
                    origin.branches.printTree(origin.branches._root);
                    System.out.println();
                }
                else{
                    System.out.println("bank " + name + " doesn't have any branch!");
                }
            }
        }catch (Exception e){
            System.out.println("something wrong! :(");
        }

    }
    String addNeighbourhood(String name, String[] coordinates){
    double minX = 1000000;
    double minY = 1000000;
    double maxX = -1000000;
    double maxY = -1000000;
        try {
            for (String coordinate:coordinates) {
                String[] co = coordinate.substring(1, coordinate.length()-1).split(",");
                double x = Double.parseDouble(co[0]);
                double y = Double.parseDouble(co[1]);
                if (x < minX){
                    minX = x;
                }
                if (x > maxX){
                    maxX = x;
                }
                if (y < minY){
                    minY = y;
                }
                if (y > maxY){
                    maxY = y;
                }
            }
            Neighbourhood neighbourhood = new Neighbourhood(name, minX, minY, maxX, maxY);
            neighbourhoodsTrieTree.add(neighbourhood.name, neighbourhood);
            return "neighbourhood " + neighbourhood + " added to system :)";
        }catch (Exception e){
            return "please follow the pattern :/";
        }
    }
    void listBanksNeighbourhood(String neighbourhood){
        try {
            TNode tNode = neighbourhoodsTrieTree.search(neighbourhood);
            if (tNode == null){
                System.out.println("Neighbourhood '" + neighbourhood + "' doesn't exist!");
            }else{
                Neighbourhood neighbour = (Neighbourhood) tNode.object;
                getBanksOfNeighbourhood(this.banks._root, neighbour, 0);
                if (neighbour.getNumberOfBanks() == 0){
                    System.out.println("There is no bank in this neighborhood!");
                }else{
                    System.out.println();
                }
            }
        }catch (Exception e){
            System.out.println("something went wrong!");
        }

    }

    void getBanksOfNeighbourhood(Node node, Neighbourhood neighbourhood, int step){
        if (node == null){
            return;
        }
        if (node.x >= neighbourhood.x1 && node.x <= neighbourhood.x2 && node.y >= neighbourhood.y1 && node.y <= neighbourhood.y2){
            System.out.print(node.object+" ");
            neighbourhood.increaseNumberOfBanks();
        }
        if (step % 2 == 0){
            if (node.x >= neighbourhood.x1 && node.x <= neighbourhood.x2){
                getBanksOfNeighbourhood(node.left, neighbourhood, step+1);
                getBanksOfNeighbourhood(node.right, neighbourhood, step+1);
            }else if(node.x < neighbourhood.x1){
                getBanksOfNeighbourhood(node.right, neighbourhood, step+1);
            }else {
                getBanksOfNeighbourhood(node.left, neighbourhood, step+1);
            }
        }else {
            if (node.y >= neighbourhood.y1 && node.y <= neighbourhood.y2){
                getBanksOfNeighbourhood(node.left, neighbourhood, step+1);
                getBanksOfNeighbourhood(node.right, neighbourhood, step+1);
            }else if(node.y < neighbourhood.y1){
                getBanksOfNeighbourhood(node.right, neighbourhood, step+1);
            }else {
                getBanksOfNeighbourhood(node.left, neighbourhood, step+1);
            }
        }
    }
    void getAvailableBanks(double x, double y, double r){
        try {
            getBanksOfNeighbourhood(this.banks._root, x, y, r, 0);
            if (this.numberOfAvailBanks != 0){
                this.numberOfAvailBanks = 0;
                System.out.println();
            }else{
                System.out.println("There is no bank in this neighborhood!");
            }
        }catch (Exception e){
            System.out.println("Something Went Wrong!");
        }
    }
    void getBanksOfNeighbourhood(Node node, double x, double y, double r, int step){
        if (node == null){
            return;
        }
        double distance = (node.x - x)*(node.x - x) + (node.y - y)*(node.y - y);
        if (distance <= r*r){
            System.out.print(node.object+" ");
            this.numberOfAvailBanks++;
        }
        if (step % 2 == 0){
            if (node.x >= x-r && node.x <= x+r){
                getBanksOfNeighbourhood(node.left, x, y, r,step+1);
                getBanksOfNeighbourhood(node.right, x, y, r, step+1);
            }else if(node.x < x-r){
                getBanksOfNeighbourhood(node.right, x, y, r, step+1);
            }else {
                getBanksOfNeighbourhood(node.left, x, y, r, step+1);
            }
        }else {
            if (node.y >= y-r && node.y <= y+r){
                getBanksOfNeighbourhood(node.left, x, y, r, step+1);
                getBanksOfNeighbourhood(node.right, x, y, r, step+1);
            }else if(node.y < y-r){
                getBanksOfNeighbourhood(node.right, x, y, r, step+1);
            }else {
                getBanksOfNeighbourhood(node.left, x, y, r, step+1);
            }
        }
    }
    String deleteBranch(double x, double y) {
        Node node = banks.search(banks._root, x, y, 0);
        String answer;
        if (node == null) {
            answer = "bank doesn't exist!";
        } else if (node.object instanceof Bank){
            answer = "this bank is not a branch!";
        } else {
            Branch branch = (Branch) node.object;
            String name = branch.bankName;
            Bank bank = (Bank) bankTrieTree.search(name).object;
            // remove from bank bst.
            Node n = new Node(bank , bank.x, bank.y);
            banksBST.delete(n);
            bank.numberOfBranches--;
            banksBST.insert(n);
            //______________
            KDTree branches = bank.branches;
            banks.deleteNode(banks._root, x, y, 0);
            if (branches.isExistPoint(x,y)) {
                branches.deleteNode(branches._root, x, y, 0);
                answer = "deleted successfully! :)";
            } else {
                answer = "bank " + bank + " doesn't have this branch!";
            }
        }
        return answer;
    }
    void restart(){
        this.bankWithMostBranches = null;
        this.banks = new KDTree();
        this.bankTrieTree = new TrieTree();
        this.neighbourhoodsTrieTree = new TrieTree();
        this.bankTrieTree.root = new TNode();
        this.neighbourhoodsTrieTree.root = new TNode();
    }
    void printPreOrderBanks(){
        if (banks._root == null){
            System.out.println("there aren't any bank in system!");
        }else{
            banks.printTree(banks._root);
            System.out.println();
        }

    }
    void printPreOrderBranches(String name){
        try {
            KDTree branches = ((Bank)bankTrieTree.search(name).object).branches;
            if (branches._root == null){
                System.out.println("there aren't any bank in system!");
            }else{
                branches.printTree(branches._root);
                System.out.println();
            }
        }catch (Exception e){
            System.out.println("this bank does not exist!");
        }

    }
    void getBankWithMostBranches(){
        try {
            System.out.println(banksBST.getMax().object);
        }catch (Exception e){
            System.out.println("something went wrong :(");
        }
    }
    void getNearestBank(double x, double y){
        try {
            getNearest(banks, x, y);
        }catch (Exception e){
            System.out.println("Something went Wrong!");
        }
    }
    void getNearestBranch(String name, double x, double y){
        try {
            TNode tNode = bankTrieTree.search(name);
            if (tNode == null){
                System.out.println("this bank doesn't exist!");
            }else {
                Bank bank = (Bank) tNode.object;
                KDTree branches = bank.branches;
                if (branches.isEmpty()){
                    System.out.println("this bank doesn't have any branch :/");
                }else {
                    getNearest(branches, x, y);
                }
            }
        }catch (Exception e){
            System.out.println("Something Went Wrong!");
        }
    }
    void getNearest(KDTree banks,double x, double y){
        Node test = new Node(new Bank("null", 10000,10000), 10000, 10000);
        Object object = banks.getNearest(banks._root, x, y, test, 0).object;
        if (object instanceof Bank){
            Bank bank = (Bank) object;
            if (bank.name.equals("null")){
                System.out.println("there isn't any bank!");
            }else {
                System.out.println(bank);
            }
        }else{
            Branch branch = (Branch) object;
            System.out.println(branch);
        }
    }


}
