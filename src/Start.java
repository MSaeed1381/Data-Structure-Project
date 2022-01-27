import java.util.Scanner;
class Main{
    public static void main(String[] args) {
        Start start = new Start();
        start.runApp();
    }
}
class Start {
    String error = "you don't follow the pattern\ntype command 'help'";
    DataBase dataBase = new DataBase();
    public void runApp() {
        Scanner input = new Scanner(System.in);
        welcome();
        System.out.println("type 'help' to see all commands");
        while (true){
            String request = input.nextLine();
            switch (request.split(" ")[0]){
                case "help":help();break;
                case "addB":addBank(request);break;
                case "addBr":addBranch(request);break;
                case "listBrs":getListOfBranches(request);break;
                case "addN": addNeighbourhood(request);break;
                case "mostBrs":getBankWithMostBranches();break;
                case "listB":listBanksNeighbourhood(request);break;
                case "availB":getAvailableBanks(request);break;
                case "delBr":deleteBranch(request);break;
                case "quit": return;
                default: error();
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
        System.out.println("8. delBr [name of bank] [(x,y)]");
    }
    public void welcome(){
        System.out.println(" ____       _      _   _   _  __  ___   _   _    ____      ____   __   __  ____    _____   _____   __  __ \n" +
                "| __ )     / \\    | \\ | | | |/ / |_ _| | \\ | |  / ___|    / ___|  \\ \\ / / / ___|  |_   _| | ____| |  \\/  |\n" +
                "|  _ \\    / _ \\   |  \\| | | ' /   | |  |  \\| | | |  _     \\___ \\   \\ V /  \\___ \\    | |   |  _|   | |\\/| |\n" +
                "| |_) |  / ___ \\  | |\\  | | . \\   | |  | |\\  | | |_| |     ___) |   | |    ___) |   | |   | |___  | |  | |\n" +
                "|____/  /_/   \\_\\ |_| \\_| |_|\\_\\ |___| |_| \\_|  \\____|    |____/    |_|   |____/    |_|   |_____| |_|  |_|\n");
    }
    public void error(){
        System.out.println("this command doesn't exist. please type 'help' to see all commands");
    }
    public void addBank(String request){
        try {
            String[] coordinates = request.split(" ")[2].substring(1, request.split(" ")[2].length()-1).split(",");
            dataBase.addBank(request.split(" ")[1], Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
        }catch (Exception e){
            System.out.println(error);
        }
    }
    public void addBranch(String request){
        try {
            String[] coordinates = request.split(" ")[3].substring(1, request.split(" ")[3].length()-1).split(",");
            dataBase.addBranch(request.split(" ")[1], request.split(" ")[2], Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
        }catch (Exception e){
            System.out.println("");
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
            System.out.println(error);
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

}

