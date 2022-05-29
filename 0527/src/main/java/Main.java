import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Calculator
{
    public Calculator() {}
    // Sum of A n B
    public int intersection(int[] A, int[] B)
    {
        int result = 0;
        // todo
        List<Integer> listA = Arrays.stream(A).boxed().collect(Collectors.toList());
        List<Integer> listB = Arrays.stream(B).boxed().collect(Collectors.toList());
        listA.retainAll(listB);
        for (int num:listA) {
            result += num;
        }
        return result;
    }

    // Sum of A - B
    public int differenceOfSet(int[] A, int[] B)
    {
        int result = 0;
        // todo
        List<Integer> listA = Arrays.stream(A).boxed().collect(Collectors.toList());
        List<Integer> listB = Arrays.stream(B).boxed().collect(Collectors.toList());
        listA.removeAll(listB);
        for (int num:listA) {
            result += num;
        }
        return result;
    }
}

class Minesweeper
{
    public static int MAP_X = 10;
    public static int MAP_Y = 10;
    private int[][] map;
    private int numOfpick;
    public Minesweeper(int[][] map) { this.map = map; this.numOfpick = 0;}

    public int pick(int x, int y)
    {
        numOfpick++;
        int numOfMine = 0;
        // todo
        if(map[x][y] == 0){
            //입력
            map[x][y]=2;
            //상
            int testX = x+1;
            int testY = y;
            if(isValid(testX, testY)){
                if(map[testX][testY] == 1){
                    numOfMine++;
                } else {
                    map[testX][testY] = 2;
                }
            }
            //하
            testX = x-1;
            testY = y;
            if(isValid(testX, testY)){
                if(map[testX][testY] == 1){
                    numOfMine++;
                } else {
                    map[testX][testY] = 2;
                }
            }
            //좌
            testX = x;
            testY = y-1;
            if(isValid(testX, testY)){
                if(map[testX][testY] == 1){
                    numOfMine++;
                } else {
                    map[testX][testY] = 2;
                }
            }
            //우
            testX = x;
            testY = y+1;
            if(isValid(testX, testY)){
                if(map[testX][testY] == 1){
                    numOfMine++;
                } else {
                    map[testX][testY] = 2;
                }
            }
            //대각선 좌하
            testX = x-1;
            testY = y-1;
            if(isValid(testX, testY)){
                if(map[testX][testY] == 1){
                    numOfMine++;
                } else {
                    map[testX][testY] = 2;
                }
            }
            //대각선 좌상
            testX = x-1;
            testY = y+1;
            if(isValid(testX, testY)){
                if(map[testX][testY] == 1){
                    numOfMine++;
                } else {
                    map[testX][testY] = 2;
                }
            }
            //대각선 우하
            testX = x+1;
            testY = y-1;
            if(isValid(testX, testY)){
                if(map[testX][testY] == 1){
                    numOfMine++;
                } else {
                    map[testX][testY] = 2;
                }
            }
            //대각선 우상
            testX = x+1;
            testY = y+1;
            if(isValid(testX, testY)){
                if(map[testX][testY] == 1){
                    numOfMine++;
                } else {
                    map[testX][testY] = 2;
                }
            }
        } else {
            numOfMine = -1;
        }
        return numOfMine;
    }

    public boolean isValid(int x,int y) {
        if(x>=map.length || x < 0 || y>=map.length || y < 0)
            return false;
        else
            return true;
    }

    public int getNumOfpick()
    {
        return numOfpick;
    }

    public boolean checkMap()
    {
        // todo
        for(int i = 0; i < MAP_X; i++) {
            for(int j = 0; j < MAP_Y; j++) {
                if(map[i][j]==0)
                    return false;
            }
        }
        return true;
    }

    public void printMap() {
        for(int i = 0; i < MAP_X; i++) {
            for(int j = 0; j < MAP_Y; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}

public class Main {

    public static void main(String[] args)
    {
        Calculator calculator = new Calculator();
        System.out.println("intersection = "+ calculator.intersection(new int[]{1,2,4,11,6,7,5,14,19,16},new int[]{2,9,8,4,11,19,15,12}));
        System.out.println("differenceOfSet = "+ calculator.differenceOfSet(new int[]{1,2,4,11,6,7,5,14,19,16},new int[]{2,9,8,4,11,19,15,12}));

        int[][] map = {
                {0,0,1,0,0,0,0,1,0,0},
                {0,0,0,0,0,1,0,1,0,0},
                {0,0,1,0,0,0,0,0,0,1},
                {0,1,0,0,1,0,0,0,0,0},
                {1,0,0,0,1,0,0,1,0,0},
                {0,1,0,1,0,0,0,0,0,0},
                {0,0,1,0,1,0,0,1,0,0},
                {0,1,0,0,0,0,1,0,0,1},
                {1,0,1,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,1,0,0,0}
        };

        Minesweeper minesweeper = new Minesweeper(map);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Minesweeper start!!!");
        System.out.println("----------------------------------------------------");
        minesweeper.printMap();
        while(minesweeper.checkMap() == false) {
            System.out.print("x(0~9) : ");
            int x = scanner.nextInt();
            System.out.print("y(0~9) : ");
            int y = scanner.nextInt();

            if(x >= minesweeper.MAP_X || y >= minesweeper.MAP_Y || x < 0 || y < 0) break;

            int numOfMine = minesweeper.pick(Minesweeper.MAP_Y - y -1, x);
            if(numOfMine == -1) {
                System.out.println("Mine has exploded!!!");
                break;
            } else {
                System.out.println("There's a mine around : "+numOfMine);
                minesweeper.printMap();
            }
        }

        System.out.println("----------------------------------------------------");
        System.out.println("Number of Attempts : " + minesweeper.getNumOfpick());
        System.out.println("Minesweeper end!!!");
    }
}