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
                getListOfBranches(request.split(" ")[1]);
            }else if (request.split(" ")[0].equals("addN")){
                addNeighbourhood(request);
            }else if (request.split(" ")[0].equals("mostBrs")){
                getBankWithMostBranches();
            }
            else if (request.equals("quit")){
                break;
            }

        }
    }
    public void help(){
        System.out.println("0. quit");
        System.out.println("1. addB [name] [(x,y)]");
        System.out.println("2. addBr [name] [branchName] [(x,y)]");
        System.out.println("3. listBrs [name]");
        System.out.println("4. addN [name] [(x,y)] [(m,n)] [(z,t)] [(k,r)]");
        System.out.println("5. mostBrs");
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
    public void getListOfBranches(String name){
        dataBase.getListOfBranches(name);
    }
    void addNeighbourhood(String request){
        String name = request.split(" ")[1];
        String[] coordinate = request.split(" ");
        dataBase.addNeighbourhood(name, coordinate);
    }
    void getBankWithMostBranches(){
        System.out.println(dataBase.bankWithMostBranches.name);
    }
}

