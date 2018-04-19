

/*************************************************************************
 * Name: 
 * NetID: 
 * Precept: 
 *
 * Description: Uses a Markov chian to create a statistical model of input text.
 *************************************************************************/

public class MarkovModel {
    // global constant for ASCII
    private static final int ASCII = 128;
    // For each k-gram (string), the first symbol table states how many times 
    // it appears in the text (an integer).
    private ST<String, Integer> symbol = new ST<String, Integer>();
    // For each k-gram (string), the second symbol table states how many times
    // each ASCII character succeeds the k-gram in the text in an int array.
    private ST<String, int[]> array = new ST<String, int[]>();
    private int order;               // order of model
    
    // creates a Markov model of order k for the specified text
     public MarkovModel(String text, int k) {
         order = k;
         
         StringBuilder stringBuffer = new StringBuilder();
           String kgrams = ""; 
   int number = 0;
    String substring = "";
    
   // Fills the buffer with the first k characters of the text, where k is
    // 'Window' that looks at k number of text at a time.
    if (order < text.length()) {
    for (int i = 0; i < order - 1; i++) {
      stringBuffer.append(text.charAt(i));
    }
    }
    text += stringBuffer.toString();
    
    for (int i = 0; i < text.length() - 1; i++) {
         kgrams = text.substring(i, i + order);
         symbol.put(kgrams, number);
         if (symbol.contains(kgrams)) {
                int j = symbol.get(kgrams);
                j++;
                symbol.put(kgrams, j);
            }
    }
    /*
    for (int i = 0; i < text.length() - 1; i++) {
          
         substring = text.substring(i, i + order);
                 if (symbol.contains(substring)) {
                     number++;
                 }
                 symbol.put(kgrams, number);
    }  */

    int[] asciiArray = new int[ASCII];
       array.put(kgrams, asciiArray);
    
    char[] hello = new char[order];
    char next = 0;
    for (int i = 0; i < text.length() - order; i++) {
     
     // 'Window' that looks at k number of text at a time.
     for (int j = 0; j < order; j++) {
      hello[j] = text.charAt(i + j);
      
      // Single char immediately after the 'window'
      next = text.charAt(i + j + 1); 
     }

     String entry = String.valueOf(hello);
     
     // Storing the value in HashMap
     if (array.contains(entry)) {
      // If already present, we increment the value of frequency of next char by one
      asciiArray = array.get(kgrams);
      asciiArray[(int) next]++;
     } 
     else {
      // If not, we initalize a new Integer array and set the frequency of next char
      // by one
      array.put(entry, asciiArray);
     }
    } 
   }
        

    // returns the order k of this Markov model
    public int order() {
        return order;
    }


    public String toString() {
         StringBuilder content = new StringBuilder();
         for (String key : array.keys()) {
             content.append(key);
             // result += key + ": ";
             content.append(": ");
             // get the character frequency array
             int[] frequency = array.get(key);
             // for each non-zero entry, append the character and the frequency
             for (int i = 0; i < array.size(); i++) {
                 // result = String.valueOf(frequency[i]);
                 char c = (char) frequency[i];
                 content.append(c);
                 content.append(freq(key, c));
             // result += freq(key, key.charAt(i));
             }
             content.append("\n");
             // append a newline character
             // result += "\n";
             
             // sb.append(freq(kgram, c) + " " + key + "\n");
         }
         return content.toString();
         // return result.toString();
     }
     
     

    // returns the number of times the specified kgram appears in the text
    public int freq(String kgram) {
        // Error check
  if (kgram.length() != order) {
   throw new IllegalArgumentException("Length of string kgram must be equal to order.");
  }

  // Retrieve the array of the specified character sequence in string form
  if (symbol.get(kgram) == 0) {
  return 0;
  }

  // If the hashmap retrieves nothing, means the specified string does not
  // appear in the text
      return symbol.get(kgram);
      }

    // returns the number of times the character c follows the specified
    // kgram in the text
     public int freq(String kgram, char c) {
         // For error
         if (kgram.length() != order) {
             throw new IllegalArgumentException
                 ("Length of kgram must equal order.");
         }
     // Retrieves the array of the specified character sequence in string form
   int[] asciiArray = array.get(kgram);

   // If the hashmap retrieves nothing, means the specified string does not
   // appear in the text
   for (int i = 0; i < asciiArray.length; i++) {
   if (asciiArray[i] == 0) {
    return 0;
   }
   }
   return asciiArray[c];
  
     }

    // returns a random character that follows the specified kgram in the text,
    // chosen with weight proportional to the number of times that character
    // follows the specified kgram in the text
    public char random(String kgram) {
        int[] charValue = array.get(kgram);
        int index = 0;
        
        index = StdRandom.discrete(charValue);
        if (!array.contains(kgram)) 
                    throw new IllegalArgumentException("Wrong substring!!!");
        return (char) index;      
    }

    // tests this class by directly calling all instance methods
    public static void main(String[] args) {
        String text1 = "banana";
        MarkovModel model1 = new MarkovModel(text1, 2);
        StdOut.println(model1);

        String text2 = "gagggagaggcgagaaa";
        MarkovModel model2 = new MarkovModel(text2, 2);
        StdOut.println(model2);
    }
} 