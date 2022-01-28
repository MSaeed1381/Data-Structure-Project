import java.util.Scanner;
class Main{
    public static void main(String[] args) {
        Start start = new Start();
        start.runApp();
    }
}
class Start {
    private final String ERROR = "you don't follow the pattern\ntype command 'help' to see all correct commands";
    private final DataBase dataBase;
    String[] commands;
    Start(){
        dataBase = new DataBase();
        commands = new String[100];
        commands[0] = "restart";
    }

    int commandCounter = 1;
    public void runApp() {
        Scanner input = new Scanner(System.in);
        welcome();
        System.out.println("type 'help' to see commands");
        while (true){
            String request = input.nextLine();
            commands[commandCounter] = request;
            switch (request.split(" ")[0]){
                case "help":help();break;
                case "addB": System.out.println(addBank(request));break;
                case "addBr": System.out.println(addBranch(request));break;
                case "listBrs":getListOfBranches(request);break;
                case "addN": addNeighbourhood(request);break;
                case "mostBrs":getBankWithMostBranches();break;
                case "listB":listBanksNeighbourhood(request);break;
                case "availB":getAvailableBanks(request);break;
                case "delBr":deleteBranch(request);break;
                case "undo":undo(request);break;
                case "printPreB":printPreOrderBanks();break;
                case "printPreBr":printPreOrderBranch(request);break;
                case "quit": return;
                default: error();
            }
            commandCounter++;
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
        System.out.println("8. delBr [name of bank] [(x,y)]");
        System.out.println("9. undo [p]  -> (p = 0 restart the system)");
        System.out.println("10. printPreB");
        System.out.println("11. printPreBr [name]");
    }
    public void welcome(){
        System.out.println("""
                 ____       _      _   _   _  __  ___   _   _    ____      ____   __   __  ____    _____   _____   __  __\s
                | __ )     / \\    | \\ | | | |/ / |_ _| | \\ | |  / ___|    / ___|  \\ \\ / / / ___|  |_   _| | ____| |  \\/  |
                |  _ \\    / _ \\   |  \\| | | ' /   | |  |  \\| | | |  _     \\___ \\   \\ V /  \\___ \\    | |   |  _|   | |\\/| |
                | |_) |  / ___ \\  | |\\  | | . \\   | |  | |\\  | | |_| |     ___) |   | |    ___) |   | |   | |___  | |  | |
                |____/  /_/   \\_\\ |_| \\_| |_|\\_\\ |___| |_| \\_|  \\____|    |____/    |_|   |____/    |_|   |_____| |_|  |_|
                """);
    }
    public void error(){
        System.out.println("this command doesn't exist. please type 'help' to see all commands");
    }
    public String addBank(String request){
        try {
            String[] coordinates = request.split(" ")[2].substring(1, request.split(" ")[2].length()-1).split(",");
            return dataBase.addBank(request.split(" ")[1], Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
        }catch (Exception e){
            return ERROR;
        }
    }
    public String addBranch(String request){
        try {
            String[] coordinates = request.split(" ")[3].substring(1, request.split(" ")[3].length()-1).split(",");
            return dataBase.addBranch(request.split(" ")[1], request.split(" ")[2], Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
        }catch (Exception e){
            return ERROR;
        }
    }
    public void getListOfBranches(String request){
        dataBase.getListOfBranches(request.split(" ")[1]);
    }
    void addNeighbourhood(String request){
        try {
            String name = request.split(" ")[1];
            String[] coordinate = new String[4];
            coordinate[0] = request.split(" ")[2];
            coordinate[1] = request.split(" ")[3];
            coordinate[2] = request.split(" ")[4];
            coordinate[3] = request.split(" ")[5];
            dataBase.addNeighbourhood(name, coordinate);
        }catch (Exception e){
            System.out.println(ERROR);
        }
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
    void deleteBranch(String request){
        String name = request.split(" ")[1];
        String coordinates = request.split(" ")[2];
        String[] coordinate = coordinates.substring(1, coordinates.length()-1).split(",");
        int x = Integer.parseInt(coordinate[0]);
        int y = Integer.parseInt(coordinate[1]);
        dataBase.deleteBranch(name, x, y);
    }
    void undo(String request){
        int p = Integer.parseInt(request.split(" ")[1]);
        for (int i = 0; i <= p; i++) {
            String command = this.commands[i];
            switch (command.split(" ")[0]){
                case "addB":addBank(command);break;
                case "addBr":addBranch(command);break;
                case "addN":addNeighbourhood(command);break;
                case "delBr":deleteBranch(command);break;
                case "undo": undo(command);break;
                case "restart": dataBase.restart();break;
                default:break;
            }
        }
    }
    void printPreOrderBanks(){
        dataBase.printPreOrderBanks();
    }
    void printPreOrderBranch(String request){
        dataBase.printPreOrderBranches(request.split(" ")[1]);
    }


}

