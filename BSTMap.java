import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Queue;

public class BSTMap<K,V> implements MyMap<K,V> {

    private TreeNode<K,V> root;
    private Comparator<K> comparator;
    private int treeSize;

    public BSTMap( Comparator<K> comparator ) {
      //constructor
        this.comparator = comparator;
        this.root = null;
    }

    public void put( K key, V value ) {
        // Call the TreeNode's put method. It will need the comparator passed in to it.
        if (this.root == null){
          TreeNode<K,V> newTree = new TreeNode<K,V>(key, value, null, null);
          this.root = newTree;
          this.treeSize++;
        } else{
          if (this.root.containsKey(key, this.comparator) == true) {
            this.root.put(key, value, this.comparator);
          }
          else{
            this.root.put(key, value, this.comparator);
            this.treeSize++;
          }
        }
    }

    // reset the tree to empty (possible memory loss here)
    public void clear() {
        this.root = null;
        this.treeSize = 0;
    }

    // return True if the tree is empty
    public boolean isEmpty() {
        return root == null;
    }

    // returns the size of the tree
    public int size() {
        return this.treeSize;
    }

    public V get(K key){
      //confusion :/
      if (this.root == null){
        return null;
      }else{
        return this.root.get(key, comparator);
      }
    }

    public int depth(){
      // int depth = (int)(Math.floor(Math.log10(this.treeSize+1) / Math.log10(2.0))) + 1;
      if (this.treeSize == 0) {
        return 0;
      }else{
        return this.root.depth(this.root);
      }
    }

    public ArrayList<KeyValuePair<K,V>> getPairs(){
      ArrayList<KeyValuePair<K,V>> pairup = new ArrayList<KeyValuePair<K,V>>();
      //for every key value pair, add to list
        if (this.root == null){
          return null;
        } else {
          this.root.getPairs(pairup);
        }
        return pairup;
        //check every node for it's key/val then go back to this

    }
    public ArrayList<K> getKeys(){
      return null;
      // ArrayList<K> keylist = new ArrayList<K>();
      // return keylist;
    }

    public boolean containsKey(K key){
      if (this.root == null) {
        return false;

      }else{
        return this.root.containsKey(key, this.comparator);
      }
      // for (TreeNode<K,V> tn : this){
      //   if (tn.getPair().getKey() == key){
      //     return true;
      //   }
      // }
      // return false;
    }

    public static void main( String[] args ) {
        System.out.println( "test code" );
        BSTMap<String,Integer> map = new BSTMap<String,Integer>(new AscendingStringComparator());
        map.put( "B", 2 );
        map.put( "A", 1);
        map.put( "D", 2 );
        map.put( "C", 2 );
        //map.printInOrder();
        System.out.println();
        System.out.println( "Has A: " + map.containsKey( "A" ) );
        System.out.println( "Has G: " + map.containsKey( "G" ) );
        map.put( "D", 3 );
        System.out.println( "D now has value " + map.get( "D" ) );
        System.out.println( "The tree has " + map.size() + " elements" );
        System.out.println( "The tree has " + map.depth() + " levels" );
        ArrayList<KeyValuePair<String,Integer>> pairs  = map.getPairs();
        System.out.println( "In useful order: " );
        System.out.println( pairs );
    }
}

//---------------------------------------

class TreeNode<Key,Value>{
    private KeyValuePair<Key,Value> pair;
    private TreeNode<Key,Value> left;
    private TreeNode<Key,Value> right;

    public TreeNode( Key this_key, Value this_val, TreeNode<Key,Value> l, TreeNode<Key,Value> r ) {
      //need to initialize the key value pair
      // this.pair.setKey(this_key);
      // this.pair.setValue(this_val);
      pair = new KeyValuePair(this_key, this_val);
      this.left = l;
      this.right = r;
    }

  // Methods to support insert, contains, printing, getPairs, etc.
    public Key getKey(){ return this.pair.getKey();}
    public Value getValue(){return this.pair.getValue();}
    public TreeNode<Key,Value> getLeft() { return this.left; }
		public void setLeft(TreeNode<Key,Value> newLeft) { this.left = newLeft; }
		public TreeNode<Key,Value> getRight() { return this.right; }
		public void setRight( TreeNode<Key,Value> newRight ) { this.right = newRight; }
		// public Value getContents() { return this.bucket; }
		// public void setContents(Value value) { this.bucket = value; }
    public ArrayList<Key> getKeys(){
      ArrayList<Key> keylist = new ArrayList<Key>();
      this.keyLister(keylist);
      return keylist;
    }

    public void keyLister(ArrayList<Key> keylist){
      keylist.add(this.getKey());
      if (this.left != null) {
        this.left.keyLister(keylist);
      }
      if (this.right != null) {
        this.right.keyLister(keylist);
      }
    }

    public ArrayList<KeyValuePair<Key,Value>> getPairs(ArrayList<KeyValuePair<Key,Value>> pairset){
      pairset.add(this.pair);
      if (this.left != null) {
        this.left.getPairs(pairset);
      }
      if (this.right != null){
        this.right.getPairs(pairset);
      }
      return pairset;
    }

    public void put(Key k, Value v, Comparator comp){
      //TreeNode putNode = new TreeNode();
      if (comp.compare(this.getKey(), k) == 0) {
        this.pair.setValue(v);
      }
      else if (comp.compare(this.getKey(), k) > 0) {
        if (this.left == null) {
          this.left = new TreeNode<Key,Value>(k,v,null,null);
        }else{
          this.left.put(k,v,comp);
        }
      }else{
        if (this.right == null) {
          this.right = new TreeNode<Key,Value>(k,v,null,null);
        }else{
          this.right.put(k,v,comp);
        }
      }
    }

    public Value get(Key k, Comparator<Key> comp){
      //null if key not found
      if (comp.compare(k, this.getKey()) == 0) {
        return this.getValue();
      }
      else if (comp.compare(k,this.getKey()) < 0) {
        if (this.getLeft() == null) {
          return null;
        }
        else{
          return this.getLeft().get(k,comp);
        }
      }
      else{
        if (this.getRight() == null) {
          return null;
        }
        else {
          return this.getRight().get(k,comp);
        }
      }
    }

    public boolean containsKey(Key k, Comparator<Key> comp){
      if (comp.compare(this.getKey(),k) == 0) {
        return true;
      }
      else if (comp.compare(this.getKey(),k) > 0) {
        if (this.getLeft() == null) {
          return false;
        }else{
          return this.getLeft().containsKey(k,comp);
        }
      }
      else{
        if (this.getRight() == null) {
          return false;
        }
        else{
          return this.getRight().containsKey(k,comp);
        }
      }
    }

    public int depth(TreeNode node){
      if (node == null) {
        return 0;
      }
      else{
        int leftDepth = depth(node.left);
        int rightDepth = depth(node.right);
        if (leftDepth > rightDepth) {
          return (leftDepth + 1);
        }
        else{
          return (rightDepth + 1);
        }
      }
    }

    public String toString(){
      String retValue = "Left Value: " + left + "Right Value: " + right;
      return retValue;
    }
    // public TreeNode<Key,Value> getNode(){
    //   return this;
    // }

} // end class TreeNode

class AscendingStringComparator implements Comparator<String> {
    //private this internalComparator; //???
    public int compare( String i1, String i2 ) {
        // returns negative number if i2 comes after i1 lexicographically
        return i1.compareTo(i2);
    }
}

//---------------------------------------
