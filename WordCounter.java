import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

public class WordCounter{
//
//WordCounter needs to:
//generate a BSTMap from a text document. To do so, it will read in the text,
//separate it into words. For each word, it should check to see if the word is a
//key in the BSTMap. If it isn't, then add the Key-Value pair to the dictionary
//with the word as the key and 1 as the value. If it is, then increment the
//value associated with that word.

//write a file containing the values of the dictionary, with the total number of words on the first line, and each word and its count on the subsequent lines (one pair per line). We will refer to a file formatted like this as a word count file.

//read a word count file, recreating the BSTMap.

//Implement accessor methods for the dictionary.
//
  private BSTMap<String, Integer> wordMap;
  private BSTMap<String, Integer> reconWordMap;
  private int wordCount;

  public WordCounter(){ //constructor class, create wordmap, holder for it's reconstruction, wordcount Integer
    wordMap = new BSTMap<String,Integer>( new AscendingStringComparator());
    reconWordMap = new BSTMap<String,Integer>( new AscendingStringComparator());
    this.wordCount = 0;
  }

    public int getTotalWordCount(){ //return wordCount variable
      return this.wordCount;
    }

    public double getFrequency( String word ){ //return value associated with words
      if ((double)wordMap.get(word) != 0) {
        return ((double)wordMap.get(word)/(double)this.wordCount);
      }
      return 0.0;
    }

    public int getCount( String word ){   //needs to return the value of the word
      return this.wordMap.get(word);
    }

    public BSTMap<String,Integer> getWordMap(){ //get the word map
      return this.wordMap;
    }

    public BSTMap<String,Integer> getReconWordMap(){ //get the reconstructed map
      return this.reconWordMap;
    }

  public void loadFromOriginalFile(String filename){
  // split line into words. Any character other than a letter (a-z and A-Z) or a single quote
    // is considered a non-word character and is used to split words.
    // You may want to include numbers in the list of characters that are consider
    // parts of words.
    //create new BufferedReader based on a Fileread of the filename
    try{
      BufferedReader loader = new BufferedReader(new FileReader(filename));
      //make a line that puts the read strings into one
      String line = loader.readLine();
      while(line != null){
        String[] parse = line.split("[^a-zA-Z0-9']");// Regular expressions + numbers!
        for (int i = 0; i < parse.length; i++) {
            String word = parse[i].trim().toLowerCase();
            // Write code to update the map
            if (word.isEmpty() == false) {  //if the string is not empty
              Integer timesFound = this.wordMap.get(word); //timesFound is for when there are duplicate iterations of the same word
              if (timesFound == null) { //if the string in the wordMap is null
                this.wordMap.put(word,1); //put the word as they key, and set the value to 1
              }else{
                this.wordMap.put(word, timesFound + 1); //else, put the key
              }
              this.wordCount++;
            }
          }
          line = loader.readLine();
      }
    }
      catch (IOException e) {
        System.out.println(e);
      }

  }

  public void writeWordCountFile( String filename ){
    try{
    FileWriter fw = new FileWriter(filename); //Create a FileWriter
    fw.write("Total_Word_Count: " + this.getTotalWordCount() + "\n"); //Write at the top the total word count
    //for every pair in the list, write the pair as key : value and then close the file
    ArrayList<KeyValuePair<String, Integer>> writePairs = wordMap.getPairs();
    //old inefficient code due to the getpairs method being called multiple times
    for (KeyValuePair<String,Integer> kayvee : writePairs) {
      fw.write(kayvee.getKey() + " : " + kayvee.getValue() + "\n");
    }
    // for (int i = 0; i < wordMap.getPairs().size(); i++ ) {
    //   fw.write(wordMap.getPairs().get(i).getKey() + " : " + wordMap.getPairs().get(i).getValue() + "\n");
    // }
    fw.close();
    }
    catch(IOException e){
      System.out.println(e);
    }
  }

  public void readWordCountFile (String filename){ //throws IOException{
    try{
      int newWords = 0;
      BufferedReader loader = new BufferedReader(new FileReader(filename));
      String line = loader.readLine();

      while (line != null) { //will not run if there isn't anything in the line
        String[] parse = line.split("[^a-zA-Z0-9_\t']");// Regular expressions, numbers
        for (int i = 0; i < parse.length; i++) { //for the strings in the line
          if (parse[0].equals("Total_Word_Count")) {
            //
          }
          else{
            String word = parse[i].trim().toLowerCase(); // word parsed for i, trimmed, set to lowercase
            if (word.isEmpty() == false) { //if it is empt
              reconWordMap.put(parse[0], Integer.parseInt(parse[3]));
              newWords++;
            }
          }
        }
        line = loader.readLine();
      }
    }
    catch(IOException e){
      System.out.println("e");
    }
  }

  public static void main(String[] args) { //the world's worst code block, calling it now
    WordCounter testCounter = new WordCounter();
    //2008
    System.out.println("Starting 2008");
    long begin = System.currentTimeMillis();
    testCounter.loadFromOriginalFile("reddit_comments_2008.txt");
    System.out.println("Hi There");
    testCounter.writeWordCountFile("counts_reddit_comments_2008.txt");
    long end = System.currentTimeMillis();
    long dt = end - begin;
    System.out.println("Total time taken for 2008 = " + dt);

    testCounter.getWordMap().clear();
    testCounter.getReconWordMap().clear();
    System.out.println("Clearing!");
    // //2009
    System.out.println("Starting 2009");
    long begin2 = System.currentTimeMillis();
    testCounter.loadFromOriginalFile("reddit_comments_2009.txt");
    testCounter.writeWordCountFile("counts_reddit_comments_2009.txt");
    long end2 = System.currentTimeMillis();
    long dt2 = end2 - begin2;
    System.out.println("Total time taken for 2009 = " + dt2);

    testCounter.getWordMap().clear();
    testCounter.getReconWordMap().clear();
    System.out.println("Clearing!");
    // //2010
    System.out.println("Starting 2010");
    long begin3 = System.currentTimeMillis();
    testCounter.loadFromOriginalFile("reddit_comments_2010.txt");
    testCounter.writeWordCountFile("counts_reddit_comments_2010.txt");
    long end3 = System.currentTimeMillis();
    long dt3 = end3 - begin3;
    System.out.println("Total time taken for 2010 = " + dt3);
    //
    testCounter.getWordMap().clear();
    testCounter.getReconWordMap().clear();
    System.out.println("Clearing!");
    // //2011
    System.out.println("Starting 2011");
    long begin4 = System.currentTimeMillis();
    testCounter.loadFromOriginalFile("reddit_comments_2011.txt");
    testCounter.writeWordCountFile("counts_reddit_comments_2011.txt");
    long end4 = System.currentTimeMillis();
    long dt4 = end4 - begin4;
    System.out.println("Total time taken for 2011 = " + dt4);

    testCounter.getWordMap().clear();
    testCounter.getReconWordMap().clear();
    System.out.println("Clearing!");
    // //2012
    System.out.println("Starting 2012");
    long begin5 = System.currentTimeMillis();
    testCounter.loadFromOriginalFile("reddit_comments_2012.txt");
    testCounter.writeWordCountFile("counts_reddit_comments_2012.txt");
    long end5 = System.currentTimeMillis();
    long dt5 = end5 - begin5;
    System.out.println("Total time taken for 2012 = " + dt5);
    //
    testCounter.getWordMap().clear();
    testCounter.getReconWordMap().clear();
    System.out.println("Clearing!");
    // //2013
    System.out.println("Starting 2013");
    long begin6 = System.currentTimeMillis();
    testCounter.loadFromOriginalFile("reddit_comments_2013.txt");
    testCounter.writeWordCountFile("counts_reddit_comments_2013.txt");
    long end6 = System.currentTimeMillis();
    long dt6 = end6 - begin6;
    System.out.println("Total time taken for 2013 = " + dt6);

    testCounter.getWordMap().clear();
    testCounter.getReconWordMap().clear();
    System.out.println("Clearing!");
    // //2014
    System.out.println("Starting 2014");
    long begin7 = System.currentTimeMillis();
    testCounter.loadFromOriginalFile("reddit_comments_2014.txt");
    testCounter.writeWordCountFile("counts_reddit_comments_2014.txt");
    long end7 = System.currentTimeMillis();
    long dt7 = end7 - begin7;
    System.out.println("Total time taken for 2014 = " + dt7);
    //
    testCounter.getWordMap().clear();
    testCounter.getReconWordMap().clear();
    System.out.println("Clearing!");
    // //2015
    System.out.println("Starting 2015");
    long begin8 = System.currentTimeMillis();
    testCounter.loadFromOriginalFile("reddit_comments_2015.txt");
    testCounter.writeWordCountFile("counts_reddit_comments_2015.txt");
    long end8 = System.currentTimeMillis();
    long dt8 = end8 - begin8;
    System.out.println("Total time taken for 2015 = " + dt8);

  }
}
