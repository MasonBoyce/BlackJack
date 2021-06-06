public class Player{
  private int money;
  private Hand hand;
  private int betAmount;
  public Player(){
    this.money= 0;

  }
  public Player(int money, String name){
    this.money = money;
    this.hand = null;
    betAmount = 0;
  }
  public void setHand(Hand hand){
    this.hand = hand;
  }
  public void setBetAmount(int bet){
    betAmount = bet;
  }
  public int getBetAmount(){
    return betAmount;
  }
  public void hit(){
    hand.addCard();
  }
  public void doubleDown(){
      if(hand.orginalHand()){
      betAmount *= 2;
      hand.addCard();
    }
    else{
      System.out.println("You can only double down with orginal hands");
    }
  }
  public void split(){
      //Hand newHand1 = new Hand()
  }
  public void winBet(){
    money += betAmount;
    System.out.println("You now have $"+money);
  }
  public  void loseBet(){
    money -= betAmount;
    System.out.println("You now have $"+money);
  }
  public void winBlackJack(){
    money+= betAmount*1.5;
  }
  public void  printBet(){
    System.out.println("Bet Amount " +betAmount);
  }
  public Hand getHand(){
    return hand;
  }
  public  int getMoney(){
    return money;
  }
}
