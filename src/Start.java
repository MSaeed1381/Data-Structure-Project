import java.util.Scanner;
class Main{
    public static void main(String[] args) {
        Start start = new Start();
        start.runApp();
    }
}
class Start {
    DataBase dataBase = new DataBase();
    public void runApp() {
        Scanner input = new Scanner(System.in);
        System.out.println("welcome to bank system");
        System.out.println("type 'help' to see all commands");
        while (true){
            String request = input.nextLine();
            if (request.equals("help")){
                help();
            }else if(request.split(" ")[0].equals("addB")){
                addBank(request);
            }else if (request.split(" ")[0].equals("addBr")){
                addBranch(request);
            }else if (request.split(" ")[0].equals("listBrs")){
                getListOfBranches(request);
            }else if (request.split(" ")[0].equals("addN")){
                addNeighbourhood(request);
            }else if (request.split(" ")[0].equals("mostBrs")){
                getBankWithMostBranches();
            }else if (request.split(" ")[0].equals("listB")){
                listBanksNeighbourhood(request);
            }else if (request.split(" ")[0].equals("availB")){
                getAvailableBanks(request);
            }
            else if (request.equals("quit")){
                break;
            }
            System.out.println("---------------------------------");
        }
    }
    public void help(){
        System.out.println("0. quit");
        System.out.println("1. addB [name] [(x,y)]");
        System.out.println("2. addBr [name] [branchName] [(x,y)]");
        System.out.println("3. listBrs [name]");
        System.out.println("4. addN [name] [(x,y)] [(m,n)] [(z,t)] [(k,r)]");
        System.out.println("5. mostBrs");
        System.out.println("6. listB [name]");
        System.out.println("7. availB [(x,y)] r");
    }
    public void addBank(String request){
        String[] coordinates = request.split(" ")[2].substring(1, request.split(" ")[2].length()-1).split(",");
        boolean isExist = dataBase.addBank(request.split(" ")[1], Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
        if (isExist){
            //System.out.println("the bank is already existing :(");
        }else {
            System.out.println("the bank added :)");
        }
    }
    public void addBranch(String request){
        String[] coordinates = request.split(" ")[3].substring(1, request.split(" ")[3].length()-1).split(",");
        boolean isExist = dataBase.addBranch(request.split(" ")[1], request.split(" ")[2], Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
        if (isExist){
            //System.out.println("the bank is already existing :(");
        }else {
            System.out.println("the bank added :)");
        }
    }
    public void getListOfBranches(String request){
        dataBase.getListOfBranches(request.split(" ")[1]);
    }
    void addNeighbourhood(String request){
        String name = request.split(" ")[1];
        String[] coordinate = new String[4];
        coordinate[0] = request.split(" ")[2];
        coordinate[1] = request.split(" ")[3];
        coordinate[2] = request.split(" ")[4];
        coordinate[3] = request.split(" ")[5];
        dataBase.addNeighbourhood(name, coordinate);
    }
    void getBankWithMostBranches(){
        System.out.println(dataBase.bankWithMostBranches.name);
    }
    void listBanksNeighbourhood(String request){
        dataBase.listBanksNeighbourhood(request.split(" ")[1]);
    }
    void getAvailableBanks(String request){
        int r = Integer.parseInt(request.split(" ")[2]);
        String coordinate = request.split(" ")[1];
        coordinate = coordinate.substring(1, coordinate.length()-1);
        int x = Integer.parseInt(coordinate.split(",")[0]);
        int y = Integer.parseInt(coordinate.split(",")[1]);
        dataBase.getAvailableBanks(x, y, r);
    }
}

