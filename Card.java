public class Card{
  private String suit;
  private String value;
  private boolean isFaceUp;
  private int numValue;

  public Card(String suit, String value,int numValue){
    this.suit  = suit;
    this.value = value;
    this.isFaceUp = true;
    this.numValue = numValue;
  }
  public String getSuit(){
    return suit;
  }
  public String getValue(){
    return value;
  }
  public void toggleIsFaceUp(){
    isFaceUp = !isFaceUp;
  }
  public void  setNumValue(int newNumValue){
    numValue = newNumValue;
  }
  @Override
  public String toString(){
    if(!isFaceUp){
      return "FACE DOWN";
    }
    else{
      return ("The " +value+ " of " + suit);
    }
  }
  public int getNumValue(){
      return numValue;
  }
}
