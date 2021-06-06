import java.util.ArrayList;

public class Hand{
  private ArrayList<Card> hand;
  private Deck deck;

  public Hand(Card card1,Card card2,Deck deck){
    hand = new ArrayList<Card>();
    hand.add(card1);
    hand.add(card2);
    this.deck = deck;
  }
  public void addCard(){
    Card newCard = deck.getTopCard();
    hand.add(newCard);
  }
  public boolean hasAce(){
    for (Card card: hand){
      if (card.getValue().equals("Ace")){
        return  true;
      }
    }
    return  false;
  }
  public int amountOfCards(){
    int numCards = 0;
    for(Card card: hand){
      numCards +=1;
    }
    return  numCards;
  }
  public int cardTotal(){
    int total = 0;
    for (Card card: hand){
      total += card.getNumValue();
    }
    if (total>21){
      for (Card card: hand){
        if (card.getValue().equals("Ace")){
          card.setNumValue(1);
          total -=10;
        }
      }
    }
    return total;
  }
  public void toggleFirstCard(){
    hand.get(0).toggleIsFaceUp();
  }
  public void printHand(){
    for (int i=0; i<hand.size();i++){
      System.out.println("Hand:" + hand.get(i));
    }
  }
  public boolean orginalHand(){
    if (hand.size()==2){
      return true;
    }
    else{
      return false;
    }
  }
  public boolean checkBust(){
    //Check if need tto switch value of Ace to 1
    if (cardTotal()>21){
      return true;
    }
    else{
      return false;
    }
  }
  public boolean blackJack(){
    if (orginalHand()&& cardTotal()==21){
      return true;
    }
    else{
      return false;
    }
  }
  public boolean ableToSplit(){
    if (orginalHand() && hand.get(0).getNumValue() == (hand.get(1).getNumValue())){
      return true;
    }
    else{
      return false;
    }
  }
}
