/*
Alexa Brown
Lab 4
CSC 221
 */

import java.io.File;
import java.util.Scanner;
import java.util.*;
import java.io.FileNotFoundException;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) throws Exception {

        try{
            Integer.parseInt(args[1]); //is it an integer?
        }
        catch (Exception e) { //is not an integer
            System.out.println("Error.");
            System.out.println("Please enter an integer as the shingle size.");
            System.exit(0); //end the program
        }

        if (args.length == 0 || args.length > 2) {
            System.out.println("Please enter the name of the file and the number of characters to the command line.");
            System.out.println("For example: example.txt 4");
        }//ensuring that there are two arguments

        else {
            System.out.println("Number of command line arguments: " + args.length);
            System.out.println("File name is: " + "src/" + args[0]);
            //System.out.println("Number of characters that will be in each substring: " + args[1]);

        }

        int num = Integer.parseInt(args[1]); //gets size from command line
        KNode.setShingleSize(num);

        //creating File instance to reference text file in Java
        File text = new File("src/" + args[0]); //reading in file name from command line arguments

        HashMap<String, KNode> hashtable = new HashMap<String, KNode>(17,(float)0.75); //hashmap declaration
        String oldSub = ""; //to save the last three characters of each line

        try { //checks to see if the file exists
            Scanner scanner = new Scanner(text); //Scanner to read file
            while (scanner.hasNextLine()) { //continues through the entire text file
                String line = scanner.nextLine(); //get the line from the scanner
                line = line.replaceAll("[^a-zA-Z\\s]", " "); //clean up the line, getting rid of all upper case and numbers
                line = line.replaceAll("( ) +", " "); //rids of excess spaces
                line = line.toLowerCase(); //makes everything lowercase
                line = oldSub + line; //adds the last three of the previous line to this line

                for (int i = 0; i < line.length() - (num-1); i++) { //loop to get all the substrings in the line
                    if (i == (num-3)) {
                        oldSub = ""; //erases it after putting it with the new substring
                    }

                    String sub = line.substring(i, i + num); //gets the next substring

                    if (i == (line.length() - num)) { //saves the last characters of the string into its own substring to add to the next line
                        oldSub = line.substring(i + (num-3), i + num) + " "; //add a space for the 'next line'
                    }
                    KNode a = new KNode(sub);

                    if (hashtable.containsKey(sub)) {
                        a.incrementFrequency();
                        hashtable.replace(sub,a); //replace it in the hashtable so it has the correct frequency
                    }
                    else {
                        hashtable.put(sub, new KNode(sub)); //it is not in the table yet, add it in
                    }

                } //end of for loop
            } //end of NextLine while loop
        } //end of try

        catch (Exception ex){ //checking to ensure the file does exist
            System.out.println("The file doesn't exist.");
            System.exit(0); //end the program
        }

        System.out.println("Number of strings in table: " + hashtable.size());
        System.out.println("KNode size: " + KNode.getShingleSize());
        System.out.println();

        for (KNode a:hashtable.values()) { //cycle over the hashtable and use the print method
           // a.print(); //using KNode print method
            print(a);
        }//printing the hashtable

        //interaction with the user
        String input = "";
        while (!input.equals("q")) {
            System.out.println("Please type a " + num + "-byte input then press Enter or type 'q' to quit.");
            Scanner user = new Scanner(System.in);
            input = user.nextLine();
            assert input.length() == KNode.getShingleSize() : "Please enter an input that is " + KNode.getShingleSize() + " bytes long."; //checking that the input is n bytes long since all shingles are n bytes

            if (hashtable.containsKey(input)) {
                System.out.println("The frequency of " + input + " is " + hashtable.get(input).getFrequency());
            } else {
                System.out.println("Not in list.");
            }
            System.out.println();
        }//end of while

    } //end of psv main


   public static void print(KNode a){ //print method
        System.out.println(a.letters + "  " + a.Frequency);
        System.out.println();
    }

}//end of Main