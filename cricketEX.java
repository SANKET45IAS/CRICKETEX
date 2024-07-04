import java.util.*;
class CricException extends Exception{
    CricException(String Message){
        super(Message);
        
    }
}
class RULES {
    static int toss(int call) {
        Random random_number = new Random();
        int Computer_toss = random_number.nextInt(2);
        return Computer_toss == call ? 1 : 0;
    }
 
}

public class cricketEX extends RULES {
    public static void main(String[] args) {
        Scanner ha = new Scanner(System.in);

        int user_batting;
        int user_bowling;
        Random random_number = new Random();

        int user_wicket = 0;
        int system_wicket = 0;
        System.out.println("Ladies and Gentlemen, a great match between the user and the system. Let's see who will win the match");
        System.out.println("How many overs do you want to play?");
        int overs = ha.nextInt();
        System.out.println("Total 5 wickets in each team");
        System.out.println("It's toss time. What is your call?");
        System.out.print("Head (0), Tail (1): ");
        int call = ha.nextInt();

        int systemScore = 0;
        int userScore = 0;
        if (toss(call) == 1) {
            System.out.println("Congratulations, you won the toss");
            System.out.println("What do you want to choose, batting or bowling first? [(1): bat or (2): bowl]");
            int option = ha.nextInt();

            if (option == 1) {
                System.out.println("You chose batting first (System will bowl to you)");
                System.out.println("Enter 1: One run, 2: Two runs, 3: Three runs, 4: Four runs, 5: Five runs, 6: Six runs");

                userScore = playInnings(ha, overs, true);
                System.out.println("Total score: " + userScore);
                System.out.println("2nd innings start in a few sec, now it's your turn to bowl and defend your score (Target): " + (userScore + 1));
                systemScore = playInnings(ha, overs, false);
            } else if (option == 2) {
                System.out.println("You chose bowling first (System will bat first...)");
                System.out.println("Enter 1: One run, 2: Two runs, 3: Three runs, 4: Four runs, 5: Five runs, 6: Six runs");

                systemScore = playInnings(ha, overs, false);
                System.out.println("Total score: " + systemScore);
                System.out.println("2nd innings start in a few sec, now it's your turn to bat and chase the score (Target): " + (systemScore + 1));
                userScore = playInnings(ha, overs, true);
            }
        } else {
            System.out.println("Sorry, you lost the toss");
            int computer_choose = random_number.nextInt(2);
            if (computer_choose == 0) {
                System.out.println("The computer won the toss and chose to bat first");
                System.out.println("Enter 1: One run, 2: Two runs, 3: Three runs, 4: Four runs, 5: Five runs, 6: Six runs");

                systemScore = playInnings(ha, overs, false);
                System.out.println("Total score: " + systemScore);
                System.out.println("2nd innings start in a few sec, now it's your turn to bat and chase the score (Target): " + (systemScore + 1));
                userScore = playInnings(ha, overs, true);
            } else {
                System.out.println("The computer won the toss and chose to bowl first");
                System.out.println("Enter 1: One run, 2: Two runs, 3: Three runs, 4: Four runs, 5: Five runs, 6: Six runs");

                userScore = playInnings(ha, overs, true);
                System.out.println("Total score: " + userScore);
                System.out.println("2nd innings start in a few sec, now it's your turn to bowl and defend your score (Target): " + (userScore + 1));
                systemScore = playInnings(ha, overs, false);
            }
        }

        if (userScore > systemScore) {
            System.out.println("Congratulations, you won the game by " + (userScore - systemScore) + " runs");
        } else if (userScore < systemScore) {
            System.out.println("You lost the game by " + (5 - system_wicket) + " wickets, but nice try. Best of luck for the next game");
        } else {
            System.out.println("It's a tie! Both you and the system scored " + userScore + " runs. Well played!");
        }
    }
    private static int playInnings(Scanner ha, int overs, boolean isUserBatting) {
        int score = 0;
        int wickets = 0;
        Random random_number = new Random();

        for (int i = 1; i <= overs; i++) {
            for (int j = 1; j <= 6; j++) {
                if (isUserBatting) {
                    System.out.print("Bat: ");
                    int user_batting = ha.nextInt();
                    int computer_bowling = random_number.nextInt(6) + 1;
                    if (user_batting != computer_bowling) {
                        System.out.println("You hit: " + user_batting + " runs, System chose: " + computer_bowling);
                        score += user_batting;
                    } else {
                        wickets++;
                        System.out.println("Oops, you're out! You: " + user_batting + ", System chose: " + computer_bowling);
                        if (wickets == 5) break;
                    }
                } else {
                    System.out.print("Ball: ");
                    int user_bowling = ha.nextInt();
                    int computer_batting = random_number.nextInt(6) + 1;
                    if (user_bowling != computer_batting) {
                        System.out.println("System hit: " + computer_batting + " runs, You chose: " + user_bowling);
                        score += computer_batting;
                    } else {
                        wickets++;
                        System.out.println("Yes, you got it! System wicket gone. You: " + user_bowling + ", System chose: " + computer_batting);
                        if (wickets == 5) break;
                    }
                }
                if (wickets == 5) break;
            }
            System.out.println("After completing " + i + " overs, the score is " + score + " runs with the loss of " + wickets + " wickets");
        }

        return score;
    }
   
}
