
import java.util.ArrayList;

public interface MyMap<Key,Value> {

    // adds or updates a key-value pair
    // If there is already a piar with new_key in the map, then update
    // the pair's value to new_value.
    // If there is not already a pair with new_key, then
    // add pair with new_key and new_value.
    public void put( Key new_key, Value new_value );

    // Returns true if the map contains a key-value pair with the given key
    public boolean containsKey( Key key );

    // Returns the value associated with the given key.
    // If that key is not in the map, then it returns null.
    public Value get( Key key );

    // Returns an array list of all the keys in the map.
    public ArrayList<Key> getKeys();

    // Returns the number of key-value pairs in the map.
    public int size();

    // return list of pairs with keys in an order that will be useful
    //       to reconstruct the map.
    public ArrayList<KeyValuePair<Key,Value>> getPairs();
    
    public static void main(String[] args) {
      //KeyValuePair<String,String> testSet = new KeyValuePair<String,String>();

    }
}
