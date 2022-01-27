public class DataBase {
    Bank bankWithMostBranches = null;
    KDTree banks = new KDTree();
    TrieTree bankTrieTree = new TrieTree();
    TrieTree neighbourhoodsTrieTree = new TrieTree();

    DataBase(){
        bankTrieTree.root = new TNode();
        neighbourhoodsTrieTree.root = new TNode();
    }
    boolean addBank(String name, int x, int y){
        Bank newBank = new Bank(name, x, y);
        bankTrieTree.add(newBank.name, newBank); //TODO
        Node node = new Node(newBank, x, y);
        banks._root = banks.insert(banks._root ,node, 0);
        banks.printTree(banks._root);
        System.out.println();
        return true;
    }

    boolean addBranch(String name, String branchName, int x, int y){
        Branch newBranch = new Branch(name, branchName, x, y);
        Node node = new Node(newBranch, x, y);
        Bank origin = (Bank) bankTrieTree.search(name).object;
        origin.numberOfBranches++;
        KDTree branches = origin.branches;
        branches._root = branches.insert(branches._root ,node, 0);
        //banks._root = banks.insert(banks._root , node, 0);
        banks.printTree(banks._root); // TODO
        System.out.println("***");
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
        Bank origin = (Bank) bankTrieTree.search(name).object;
        origin.branches.printTree(origin.branches._root);
    }
    void addNeighbourhood(String name, String[] coordinates){
    int minX = 100000;
    int minY = 100000;
    int maxX = -100000;
    int maxY = -100000;
        for (String coordinate:coordinates) {
            String[] co = coordinate.substring(1, coordinate.length()-1).split(",");
            int x = Integer.parseInt(co[0]);
            int y = Integer.parseInt(co[1]);
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
    }
    void listBanksNeighbourhood(String neighbourhood){
        Neighbourhood neighbour = (Neighbourhood) neighbourhoodsTrieTree.search(neighbourhood).object;
        getBanksOfNeighbourhood(this.banks._root, neighbour, 0);
    }

    void getBanksOfNeighbourhood(Node node, Neighbourhood neighbourhood, int step){
        if (node == null){
            return;
        }
        if (node.object instanceof Bank){
            getBanksOfNeighbourhood(((Bank) node.object).branches._root, neighbourhood, 0);
        }
        if (node.x >= neighbourhood.x1 && node.x <= neighbourhood.x2 && node.y >= neighbourhood.y1 && node.y <= neighbourhood.y2){
            System.out.print(node.object+" ");
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
    void getAvailableBanks(int x, int y, int r){
        getBanksOfNeighbourhood(this.banks._root, x, y, r, 0);
    }
    void getBanksOfNeighbourhood(Node node, int x, int y, int r, int step){
        if (node == null){
            return;
        }
        if (node.object instanceof Bank){
            getBanksOfNeighbourhood(((Bank) node.object).branches._root, x, y, r,0);
        }
        double distance = (node.x - x)*(node.x - x) + (node.y - y)*(node.y - y);
        if (distance <= r*r){
            System.out.print(node.object+" ");
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
    void deleteBranch(String name, int x, int y){
        Bank bank = (Bank) bankTrieTree.search(name).object;
        if (bank == null){
            System.out.println("there is not a bank with name " + name);
        }else{
            KDTree branches = bank.branches;
            if (branches.isExistPoint(x, y)){
                branches._root = branches.deleteNode(branches._root, x, y, 0);
                System.out.println("deleted successfully! :)");
            }else{
                System.out.println("bank " + name + " has not this branch!");
            }
             branches.printTree(branches._root);
        }

    }

}
