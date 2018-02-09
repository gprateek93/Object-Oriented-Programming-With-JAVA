import java.util.Scanner;
import java.util.Random;

public class BattleShips {
    public static void main(String[] args){
        greet();
        int [][] ocean = new int[10][10];
        displayMap(ocean);
        deployShipsUser(ocean);
        deployShipsCom(ocean);
        battleBegins(ocean);
    }
    public static void greet(){
        System.out.println("**** Welcome to battle ships game ****");
        System.out.println("\tRight now, the sea is empty");
    }
    public static void displayMap(int [][] ocean){
        displayColumns();
        for(int i = 0; i<10; i++){
            System.out.print("\n"+i+"| ");
            for(int j = 0; j<10; j++){
                if(ocean[i][j] == 1)
                    System.out.print("@ ");
                else if(ocean[i][j] == 3)
                    System.out.print("- ");
                else if(ocean[i][j] == 4 || ocean[i][j] == 7)
                    System.out.print("x ");
                else if(ocean[i][j] == 5 || ocean[i][j] == 8)
                    System.out.print("! ");
                else
                    System.out.print("  ");
            }
            System.out.print(" |"+i);
        }
        System.out.println();
        displayColumns();
    }
    public static void displayColumns(){
        for(int i = 0; i<10;i++){
            if(i==0)
                System.out.print("   "+i+" ");
            else if(i==9)
                System.out.print(i+"   ");
            else
                System.out.print(i+" ");
        }
    }
    public static void deployShipsUser(int [][] ocean){
        int i = 1 ;
        System.out.println("\nDeploy Your Ships");
        Scanner input = new Scanner(System.in);
        while(i<=5){
            System.out.print("Enter the x coordinate of your ship " +i+" :");
            int x = input.nextInt();
            System.out.print("Enter the y coordinate of your ship " +i+ " :");
            int y = input.nextInt();
            if(x<10 && y<10 && ocean[x][y] == 0){
                ocean[x][y] = 1;
                i++;
            }
            else{
                System.out.println("Error: You have entered an invalid location. The location either does not appear on the ocean or has been entered previously");
            }
        }
        displayMap(ocean);
    }
    public static void deployShipsCom(int [][] ocean){
        System.out.println("\nComputer is deploying its ships");
        Random rand = new Random();
        int i = 1;
        while(i<=5){
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            if(ocean[x][y] == 0){
                ocean[x][y] = 2;
                System.out.println("Computer's Ship no. "+i+" is deployed");
                i++;
            }
        }
    }
    public static void battleBegins(int [][] ocean) {
        System.out.println("\nLet the battle begin");
        int [] ships = {5,5};
        while (true) {
            playerTurn(ocean,ships);
            computerTurn(ocean,ships);
            displayMap(ocean);
            System.out.println("\nUser ships: "+ships[1]+"\tComputer ships "+ships[0]);
            if(ships[0]==0 || ships[1]==0){
                if(ships[0]==0)
                    System.out.println("You win");
                else
                    System.out.println("You lose");
                break;
            }
        }
    }
    public static void playerTurn(int [][] ocean,int [] ships){
        Scanner input = new Scanner(System.in);
        System.out.println("YOUR TURN");
        while(true) {
            System.out.print("Enter X coordinate");
            int x = input.nextInt();
            System.out.print("Enter the Y coordinate");
            int y = input.nextInt();
            if (x < 10 && y < 10 && (ocean[x][y] <3 || ocean[x][y]>5)) {
                if (ocean[x][y] == 0) {
                    ocean[x][y] = 3;
                    System.out.println("Sorry! You missed");
                }
                else if (ocean[x][y] == 1) {
                    ocean[x][y] = 4;
                    System.out.println("Oh no! You sunk your own ship :(");
                    ships[1]--;
                }
                else if (ocean[x][y] == 2) {
                    ocean[x][y] = 5;
                    System.out.println("Boom! You sunk the ship :)");
                    ships[0]--;
                }
                break;
            }
            else{
                System.out.println("Error. The guessed coordinates are either not present on the ocean or are already guessed previously. Hence make a new guess");
            }
        }
    }
    public static void computerTurn(int [][] ocean, int [] ships){
        System.out.println("Computer's turn");
        Random rand = new Random();
        while(true){
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            if(ocean[x][y]<6){
                if(ocean[x][y] == 0){
                    ocean[x][y] = 6;
                    System.out.println("Computer Missed");
                }
                else if(ocean[x][y] == 1){
                    ocean[x][y] = 7;
                    System.out.println("The computer sunk one of your ships :(");
                    ships[1]--;
                }
                else if(ocean[x][y] == 2){
                    ocean[x][y] = 8;
                    System.out.println("The computer sunk one of its own ships :)");
                    ships[0]--;
                }
                break;
            }
        }
    }
}
