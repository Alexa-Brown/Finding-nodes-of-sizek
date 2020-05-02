/*
Alexa Brown
Lab 4
CSC 221
*/

public class KNode {

        String letters;
        int Frequency;
        static int shingleSize;

        KNode(String x) { //constructor
            letters = x;
            Frequency = 1;
        }

        public static int getShingleSize(){
            return shingleSize;
        }

        public static void setShingleSize(int size){
            shingleSize = size;
        }

        public String getString() {
            return this.letters;
        }

        public int getFrequency() {
            return this.Frequency;
        }

        public void incrementFrequency() { //add one to frequency
            Frequency++;
        }

   /* public void print(){
        System.out.println(letters + "  " + Frequency);
        System.out.println();
    }*/


    }

