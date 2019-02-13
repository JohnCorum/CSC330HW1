
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Tries {

    private static TrieNode root;
    private final static String testFile1 = "C:/Users/jfcorum/Desktop/alice.txt";

    public Tries() {
        root = new TrieNode();
    }

    class TrieNode {

        TrieNode[] arr;
        boolean isEnd;
        int frequency;
        char c;

        //Initialize the Trie with 26 initial nodes in array
        public TrieNode() {
            this.arr = new TrieNode[26];
        }

    }

    // adds a word to tree
    public void addWord(String word) {

        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (p.arr[index] == null) {
                TrieNode temp = new TrieNode();
                p.arr[index] = temp;
                p.arr[index].c = c;
                p = temp;
            } else if (p.arr[index] != null) {
                p = p.arr[index];
            }
        }
        p.isEnd = true;
        p.frequency++;

    }

    //sees if node exists in Trie
    public TrieNode searchNode(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (p.arr[index] != null) {
                p = p.arr[index];
            } else {
                return null;
            }
        }

        if (p == root) {
            return null;
        }

        return p;
    }

    public static Tries makeTrie(String fileName) throws Exception {
        File f = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(f));
        Tries retTrie = new Tries();

        String curLine;
        while ((curLine = br.readLine()) != null) {
            String[] words = curLine.split("\\s+");
            for (String word : words) {
                word = cleanWord(word);
                retTrie.addWord(word);
            }
        }
        return retTrie;
    }

    public static String cleanWord(String word) {
        String ret = "";
        for (int i = 0; i < word.length(); i++) {
            char cur = word.charAt(i);
            if ((cur >= 'A' && cur <= 'Z')) {
                cur -= 'A' - 'a';
                ret += cur;
            } else if ((cur >= 'a' && cur <= 'z')) {
                ret += cur;
            }
        }
        return ret;
    }

    //returns the number of times a word appears in the trie
    public int wordFrequency(String word) {
        TrieNode p = searchNode(word);
        System.out.println("Number of occurrences for " + word + ": ");
        return p.frequency;

    }

    public static void preorder(TrieNode p, String word) {
        // return false if Trie is empty
         
        if (p == null) {
            return;
        }

        
        
        for (int i = 0; i < 26; i++) {
            
            while(p.arr[i].frequency > 0){
                // if leaf node, print key
                if (p.arr[i] != null) {
                    System.out.print(p.arr[i].c);
                    word += p.arr[i].c;
                }
                preorder(p.arr[i], word);
                word ="";
            }
           
        }
        
    }
    
    public String kthWord(int index){
        preorder(root, "");
        return "";
    }

    public static void main(String[] args) throws Exception {
        Tries aliceTrie = makeTrie(testFile1);

        System.out.println(aliceTrie.wordFrequency("alice"));
        System.out.println(aliceTrie.kthWord(3));
    }
}
