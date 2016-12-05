public class KeyValuePair<Key,Value>{
  private Key key;
  private Value val;

  public KeyValuePair( Key k, Value v ){
    this.key = k;
    this.val = v;
  }
  public Key getKey(){
    return this.key;
  }
  public Key setKey( Key k ){
    this.key = k;
    return this.key;
  }
  public Value getValue(){
    return this.val;
  }
  public Value setValue( Value v ){
    this.val = v;
    return this.val;
  }
  public String toString(){
    String a = "";
    a += "Key: " + this.key + "\n";
    a += "Value: " + this.val;
    return a;
  }
}
