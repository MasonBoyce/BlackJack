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
  public boolean betAllowed(int bet){
    if (bet>money){
      System.out.println("Can not bet more than money you have.");
      return false;
    }
    else if (betAmount<0){
      System.out.println("Can not bet negative amount");
      return false;
    }
    else {
      return true;
    }
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
      System.out.println("You won. Your current balance is: $" + money);
  }
  public  void loseBet(){
    money -= betAmount;
      System.out.println("You lost. Your current balance is: $" + money);
  }
  public void winBlackJack(){
    money+= betAmount*1.5;
    System.out.println("You got BlackJack. Your current balance is: $" + money);
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
