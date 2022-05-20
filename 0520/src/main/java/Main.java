import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class MyData {
    private int strike;
    private int ball;
    private int out;

    public MyData(int strike, int ball, int out) {
        this.strike = strike;
        this.ball = ball;
        this.out = out;
    }

    public int getStrike() {
        return strike;
    }

    public void setStrike(int strike) {
        this.strike = strike;
    }

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }
}

abstract class Player{
    private String name;
    private String initNumber;
    private List<String> fightList = new LinkedList<String>();
    private List<MyData> resultList = new ArrayList<>();
    private Boolean isEnd;
    public Player(String name, String initNumber) {
        this.name = name;
        this.initNumber = initNumber;
        this.isEnd = false;
    }
    public abstract void play(String number) throws Exception;
    public void addFightList(String number) { fightList.add(number); }
    public void addResultList(int s, int b, int o) { resultList.add(new MyData(s, b, o)); }
    public Boolean getIsEnd() { return isEnd; }
    public void setIsEnd(Boolean isEnd) { this.isEnd = isEnd; }
    public String getInitNumber() { return initNumber; }
    public String getName() { return name; }
    public void printFightList() {
        // TODO
        System.out.println(this.getName());
        System.out.println("----------------------------------------");
        for (int i = 0; i < fightList.size(); i++) {
            MyData myData = resultList.get(i);
            System.out.println(i + 1 + " : " + fightList.get(i) + " : Strike : " + myData.getStrike()
                    + ", Ball : " + myData.getBall() + ", Out : " + myData.getOut());
        }
        System.out.println("----------------------------------------\n");

    }
    public static long countChar(String str, char ch) {
        return str.chars()
                .filter(c -> c == ch)
                .count();
    }
}
class PlayerATeam extends Player
{
    public PlayerATeam(String name, String number) {
        super(name, number);
    }

    @Override
    public void play(String number) throws Exception {
        int strike = 0;
        int ball = 0;
        int out = 0;

        // TODO
        if(number.length()>4){
            System.out.println("Invalid input(length 4). The opportunity passes to the next team.");
            return;
        }
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e){
            System.out.println("Invalid input(Only numeric). The opportunity passes to the next team.");
            return;
        }
        boolean isSame = false;
        for (int i = 0; i < number.length(); i++) {
            long cnt = countChar(number,number.charAt(i));
            if (cnt > 1) {
                isSame = true;
            }
        }
        if(isSame){
            System.out.println("Invalid input(Same number). The opportunity passes to the next team.");
            return;
        }
        String result = this.getInitNumber();
        this.addFightList(number);
        for (int i = 0; i < number.length(); i++) {
            char input = number.charAt(i);
            int inputIndex = result.indexOf(input);
            if(inputIndex == i) {
                strike++;
            } else if(inputIndex==-1) {
                out++;
            } else{
                ball++;
            }
        }

        this.addResultList(strike, ball, out);

        if(strike == 4) {
            System.out.println("Congratulation!");
            super.setIsEnd(true);
        } else {
            System.out.println("Strike : "+strike+", Ball :"+ball+", Out: "+out);
        }
    }
}

class PlayerBTeam extends Player
{
    public PlayerBTeam(String name, String number) {
        super(name, number);
    }
    @Override
    public void play(String number) throws Exception {
        int strike = 0;
        int ball = 0;
        int out = 0;

        // TODO
        if(number.length()>4){
            System.out.println("Invalid input(length 4). The opportunity passes to the next team.");
            return;
        }
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e){
            System.out.println("Invalid input(Only numeric). The opportunity passes to the next team.");
            return;
        }
        boolean isSame = false;
        for (int i = 0; i < number.length(); i++) {
            long cnt = countChar(number,number.charAt(i));
            if (cnt > 1) {
                isSame = true;
            }
        }
        if(isSame){
            System.out.println("Invalid input(Same number). The opportunity passes to the next team.");
            return;
        }
        this.addFightList(number);
        String result = this.getInitNumber();
        for (int i = 0; i < number.length(); i++) {
            char input = number.charAt(i);
            int inputIndex = result.indexOf(input);
            if(inputIndex == i) {
                strike++;
            } else if(inputIndex==-1) {
                out++;
            } else{
                ball++;
            }
        }

        this.addResultList(ball, strike, out);

        if(strike == 4) {
            System.out.println("Congratulation!");
            super.setIsEnd(true);
        } else {
            System.out.println("Strike : "+ball+", Ball :"+strike+", Out: "+out);
        }
    }
}

public class Main {

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Player playerArray[] = {new PlayerATeam("A Team","1234"), new PlayerBTeam("B Team","5678")};
        int checkPlayer = 0;

        while(true) {
            checkPlayer = 0;
            for(Player player : playerArray) {
                if (player.getIsEnd() == false) {
                    System.out.print("Please enter a 4 digit number (" + player.getName() + ") defence : ");
                    String number = scanner.nextLine();
                    try {
                        player.play(number);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    checkPlayer++;
                }
            }
            if(checkPlayer == playerArray.length) break;
        }

        for(Player player : playerArray) {
            player.printFightList();
        }
    }
}