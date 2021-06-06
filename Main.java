//import package.Deck;
class Main{
  public static void main(String[] args){
    Deck deck = new Deck();
    deck.shuffle();
    Card card1 = deck.getTopCard();
    Card card2 = deck.getTopCard();
    //deck.printDeck();
    Hand hand = new Hand(card1,card2,deck);
    Player player = new Player(100,hand,"Mason");
    hand.printHand();
    System.out.println("Toal Value" + hand.cardTotal());
    //hand.addCard();
    hand.printHand();
    System.out.println("Total Value" + hand.cardTotal());
    player.setBetAmount(10);
    player.doubleDown();
    hand.printHand();
    System.out.println(hand.cardTotal());
    if (!hand.checkBust()){
      player.winBet();
    }
    else{
      player.loseBet();
    }
    System.out.println(player.getMoney());




  }
}
