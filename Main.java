//Aiden Tsang
//Lab 3

import java.util.Scanner;
import java.util.Random;

public class Main {

    enum MenuOptions { INVALID, BATTLE, QUIT }

    public static class Creature {
        public static final int DEFAULT_MIN = 0;
        public static final String DEFAULT_STR = "n/a";

        private int strength = DEFAULT_MIN;
        private int health = DEFAULT_MIN;
        private String name = DEFAULT_STR;
        private String type = DEFAULT_STR;

        public Creature() {
            setCreature(DEFAULT_STR, DEFAULT_STR, DEFAULT_MIN, DEFAULT_MIN);
        }

        public Creature(String newName, String newType, int newHealth, int newStrength) {
            setCreature(newName, newType, newHealth, newStrength);
        }

        public void setCreature(String newName, String newType, int newHealth, int newStrength) {
            name = newName;
            type = newType;

            if (newHealth >= 0) {
                health = newHealth;
            } else {
                health = 0;
            }

            if (newStrength >= 0) {
                strength = newStrength;
            } else {
                strength = 0;
            }
        }

        public int getHealth() {
            int result = health;
            return result;
        }

        public int getStrength() {
            int result = strength;
            return result;
        }

        public String getNameOnly() {
            String result = name;
            return result;
        }

        public String getTypeOnly() {
            String result = type;
            return result;
        }

        public String getNameAndType() {
            String result = name + " the " + type;
            return result;
        }

        public int getDamage() {
            Random rand = new Random();
            int calculatedDamage = 0;

            if (strength > 0) {
                calculatedDamage = rand.nextInt(strength) + 1;
            }

            return calculatedDamage;
        }

        public String to_String() {
            String outputStr = String.format("%-30s %15d %15d", getNameAndType(), health, strength);
            return outputStr;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Creature player1 = new Creature();
        Creature player2 = new Creature();

        MenuOptions userChoice = MenuOptions.INVALID;
        boolean keepRunning = true;

        while (keepRunning) {
            printMenu();

            int numericChoice = 0;

            if (input.hasNextInt()) {
                numericChoice = input.nextInt();
                input.nextLine();
            } else {
                input.nextLine();
                numericChoice = 0;
            }

            if (numericChoice >= MenuOptions.BATTLE.ordinal() && numericChoice <= MenuOptions.QUIT.ordinal()) {
                userChoice = MenuOptions.values()[numericChoice];
            } else {
                userChoice = MenuOptions.INVALID;
            }

            switch (userChoice) {
                case BATTLE:
                    executeBattleSetup(player1, player2, input);
                    break;
                case QUIT:
                    System.out.println("Exiting battle application. Goodbye!");
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid menu selection. Please try again.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("\n=== CREATURE BATTLE ARENA ===");
        System.out.println("1. Battle Two Creatures");
        System.out.println("2. Quit");
        System.out.print("Enter choice: ");
    }

    public static void executeBattleSetup(Creature c1, Creature c2, Scanner input) {
        Random rand = new Random();

        System.out.print("Enter Name for Creature 1: ");
        String name1 = input.nextLine();
        System.out.print("Enter Type for Creature 1: ");
        String type1 = input.nextLine();

        System.out.print("Enter Name for Creature 2: ");
        String name2 = input.nextLine();
        System.out.print("Enter Type for Creature 2: ");
        String type2 = input.nextLine();

        int health1 = rand.nextInt(121) + 60;
        int strength1 = rand.nextInt(141) + 50;
        int health2 = rand.nextInt(121) + 60;
        int strength2 = rand.nextInt(141) + 50;

        c1.setCreature(name1, type1, health1, strength1);
        c2.setCreature(name2, type2, health2, strength2);

        System.out.println("\n--- Initial Competitor Stats ---");
        printStatsTable(c1, c2);

        runCombatSimulation(c1, c2);

        System.out.println("\n--- Final Competitor Stats ---");
        printStatsTable(c1, c2);

        c1.setCreature("n/a", "n/a", Creature.DEFAULT_MIN, Creature.DEFAULT_MIN);
        c2.setCreature("n/a", "n/a", Creature.DEFAULT_MIN, Creature.DEFAULT_MIN);
    }

    public static void runCombatSimulation(Creature c1, Creature c2) {
        Random rand = new Random();
        int roundNumber = 1;
        boolean battleActive = true;

        Creature attacker = c1;
        Creature defender = c2;

        if (rand.nextInt(2) == 1) {
            attacker = c2;
            defender = c1;
        }

        System.out.println("\n================================= BATTLE LOG ==================================");
        System.out.println(String.format("%-6s %-25s %-8s %-25s %-15s", "Round", "Attacker", "Damage", "Defender", "Def. Health"));
        System.out.println("-------------------------------------------------------------------------------");

        while (battleActive) {
            int damageDealt = attacker.getDamage();
            int healthRemaining = defender.getHealth() - damageDealt;

            if (healthRemaining < 0) {
                healthRemaining = 0;
            }

            defender.setCreature(defender.getNameOnly(), defender.getTypeOnly(), healthRemaining, defender.getStrength());

            System.out.println(String.format("%-6d %-25s %8d %-25s %15d",
                    roundNumber, attacker.getNameAndType(), damageDealt, defender.getNameAndType(), healthRemaining));

            if (defender.getHealth() == 0) {
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println(String.format("\n>> %s defeated %s in %d rounds!",
                        attacker.getNameAndType(), defender.getNameAndType(), roundNumber));
                battleActive = false;
            } else {
                Creature temp = attacker;
                attacker = defender;
                defender = temp;
                roundNumber = roundNumber + 1;
            }
        }
        System.out.println("===============================================================================");
    }

    public static void printStatsTable(Creature c1, Creature c2) {
        System.out.println("-----------------------------------------------------------------");
        System.out.println(String.format("%-30s %15s %15s", "Creature Info", "Health", "Strength"));
        System.out.println("-----------------------------------------------------------------");
        System.out.println(c1.to_String());
        System.out.println(c2.to_String());
        System.out.println("-----------------------------------------------------------------");
    }
}

/*Output
/Library/Java/JavaVirtualMachines/jdk-26.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=56362 -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /Users/aidentsang/IdeaProjects/CS213_L3_AT/out/production/CS213_L3_AT Main

=== CREATURE BATTLE ARENA ===
1. Battle Two Creatures
2. Quit
Enter choice: 1
Enter Name for Creature 1: Aiden
Enter Type for Creature 1: Elf
Enter Name for Creature 2: Ragnar
Enter Type for Creature 2: Wolf

--- Initial Competitor Stats ---
-----------------------------------------------------------------
Creature Info                           Health        Strength
-----------------------------------------------------------------
Aiden the Elf                               87              73
Ragnar the Wolf                             63             109
-----------------------------------------------------------------

================================= BATTLE LOG ==================================
Round  Attacker                  Damage   Defender                  Def. Health
-------------------------------------------------------------------------------
1      Aiden the Elf                   71 Ragnar the Wolf                         0
-------------------------------------------------------------------------------

>> Aiden the Elf defeated Ragnar the Wolf in 1 rounds!
===============================================================================

--- Final Competitor Stats ---
-----------------------------------------------------------------
Creature Info                           Health        Strength
-----------------------------------------------------------------
Aiden the Elf                               87              73
Ragnar the Wolf                              0             109
-----------------------------------------------------------------

=== CREATURE BATTLE ARENA ===
1. Battle Two Creatures
2. Quit
Enter choice: 3
Invalid menu selection. Please try again.

=== CREATURE BATTLE ARENA ===
1. Battle Two Creatures
2. Quit
Enter choice: d
Invalid menu selection. Please try again.

=== CREATURE BATTLE ARENA ===
1. Battle Two Creatures
2. Quit
Enter choice: !
Invalid menu selection. Please try again.

=== CREATURE BATTLE ARENA ===
1. Battle Two Creatures
2. Quit
Enter choice: 1
Enter Name for Creature 1: 3241
Enter Type for Creature 1: dfsa
Enter Name for Creature 2: 1324
Enter Type for Creature 2: !3

--- Initial Competitor Stats ---
-----------------------------------------------------------------
Creature Info                           Health        Strength
-----------------------------------------------------------------
3241 the dfsa                               64             126
1324 the !3                                124             122
-----------------------------------------------------------------

================================= BATTLE LOG ==================================
Round  Attacker                  Damage   Defender                  Def. Health
-------------------------------------------------------------------------------
1      1324 the !3                     50 3241 the dfsa                          14
2      3241 the dfsa                   96 1324 the !3                            28
3      1324 the !3                     12 3241 the dfsa                           2
4      3241 the dfsa                   68 1324 the !3                             0
-------------------------------------------------------------------------------

>> 3241 the dfsa defeated 1324 the !3 in 4 rounds!
===============================================================================

--- Final Competitor Stats ---
-----------------------------------------------------------------
Creature Info                           Health        Strength
-----------------------------------------------------------------
3241 the dfsa                                2             126
1324 the !3                                  0             122
-----------------------------------------------------------------

=== CREATURE BATTLE ARENA ===
1. Battle Two Creatures
2. Quit
Enter choice: 2
Exiting battle application. Goodbye!

Process finished with exit code 0
* */