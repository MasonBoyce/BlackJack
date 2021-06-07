  import java.util.ArrayList;
  import java.util.Scanner;

  public class Game{
    Deck deck;
    Player dealer;
    public Game(Deck deck){
      dealer = new Player(1000,"Dealer");
      this.deck = deck;
    }
    public Player getDealer(){
      return dealer;
    }
    public Player createPlayer(){
      Scanner scnr = new Scanner(System.in);
      System.out.println("Please enter your name");
      String name  = scnr.nextLine();
      System.out.println("Please enter the amount of  money that you would like to deposit");
      int money  =scnr.nextInt();
      Player player = new Player(money,name);
      return player;
    }

    public void deal(Player player, Player dealer){
      deck.shuffle();
      Card card1 = deck.getTopCard();
      Card card2 = deck.getTopCard();
      card2.toggleIsFaceUp();
      Card card3 = deck.getTopCard();
      Card card4 = deck.getTopCard();
      Hand hand1 = new Hand(card1,card3,deck);
      Hand hand2 = new Hand(card2,card4,deck);
      player.setHand(hand1);
      dealer.setHand(hand2);
    }

    public void inputBetAmount(Player player){
      Scanner scnr = new Scanner(System.in);
      System.out.println("Choose the amount you would like to place on your bet");
      int betAmount = scnr.nextInt();
      while(true){
      if (player.betAllowed(betAmount)){
        player.setBetAmount(betAmount);
        break;
      }
      else{
        System.out.println("Choose bet less than Money you have");
        betAmount = scnr.nextInt();
        break;
      }
    }
    }

    public int playerTakesTurn(Player player, int betAmount){
      Scanner scnr = new Scanner(System.in);
      Hand playerHand = player.getHand();



      String playerInput = "";
      while (!playerInput.equals("S") || !playerInput.equals("s")){
        System.out.println("Players cards:");
        playerHand.printHand();
        System.out.println();

        if (playerHand.blackJack()){
          System.out.println("******************");
          System.out.println("You got BlackJack");
          System.out.println("******************");
          player.winBlackJack();
          break;
        }
        System.out.print("Would you like to stay(S), hit(H)");
        if (playerHand.orginalHand()){
          System.out.print(", doubleDown(D)");
          if(playerHand.ableToSplit()){
            System.out.print(", Split(P)");
          }
        }

        System.out.println();
        playerInput = scnr.next();

        if (playerInput.equals("S") || playerInput.equals("s")){
          break;
        }
        else if (playerInput.equals("H") || playerInput.equals("h")){
          player.hit();
        }
        else if(!playerInput.equals("D") || !playerInput.equals("d")){
          //If doubleDown was called incorrectly
          if(playerHand.amountOfCards()>2){
            System.out.println("Can't double down once hit");
            continue;
          }
          if (!player.betAllowed(player.getBetAmount()*2)){
          System.out.println("Need more $ to doubleDown");
          continue;
        }
        else{
          player.doubleDown();
          playerHand.printHand();
          break;
        }
        }
        else if(!playerInput.equals("P") || !playerInput.equals("p")){
          //player.split();
        }
        else{
          System.out.println("Please Enter a letter that is shown above");
        }


        if (playerHand.checkBust()){
          System.out.println("Shucks you Busted");
          break;
        }


        if(playerHand.amountOfCards()==5){
          System.out.println("Won with 5 cards");
          player.winBet();
          break;
        }
      }
      if  (playerHand.checkBust()){
        return 0;
      }
      else{
        return playerHand.cardTotal();
      }
    }


    public int dealerTakesTurn(Player dealer){
        int total = 0;
        Hand dealerHand = dealer.getHand();
        dealerHand.toggleFirstCard();
        System.out.println("Dealer's Hand");
        dealerHand.printHand();
        while(true){
          if(dealerHand.cardTotal()<17){
            dealer.hit();
            System.out.println();
            dealerHand.printHand();
          }
          else if(dealerHand.hasAce() && dealerHand.cardTotal() == 17){
            dealer.hit();
            System.out.println();
            dealerHand.printHand();

          }
          else{
            total =  dealerHand.cardTotal();
            break;
          }
        }
        if  (dealerHand.checkBust()){
          return 0;
        }
        else{
          return dealerHand.cardTotal();
        }
    }



    public static void main(String arg[]){
      Deck deck = new Deck();
      Game game = new Game(deck);
      Scanner scnr = new Scanner(System.in);

      Player player = game.createPlayer();
      Player dealer = game.getDealer();
      String input = "";


      while(!input.equals("q")){

      game.deal(player, dealer);
      game.inputBetAmount(player);
      int betAmount = player.getBetAmount();
      System.out.println();

      System.out.println("dealerHand:");
      dealer.getHand().printHand();
      System.out.println();

      if (dealer.getHand().blackJack() && !player.getHand().blackJack()){
        System.out.println("Dealer Blackjack you lose");
        player.loseBet();
        System.out.println("Press q to quit or anything else to play another hand");
        input = scnr.next();
        continue;
      }
      else if (dealer.getHand().blackJack() && player.getHand().blackJack()){
        System.out.println("Tie. Dealer Blackjack and you have blackjack");
        player.loseBet();
        System.out.println("Press q to quit or anything else to play another hand");
        input = scnr.next();
        continue;
      }
      else{
      int playerTotal = game.playerTakesTurn(player, betAmount);

      if (player.getHand().checkBust()){
        player.loseBet();
        System.out.println("Press q to quit or anything else to play another hand");
        input = scnr.next();
        continue;
      }
      else if (player.getHand().blackJack()){
        System.out.println("Press q to quit or anything else to play another hand");
        input = scnr.next();
        continue;
      }
      //if player buste or blackjack dont do dealers turn
      int dealerTotal = game.dealerTakesTurn(dealer);

      if(playerTotal>dealerTotal){
        player.winBet();
      }
      else if(playerTotal<dealerTotal){
        player.loseBet();
      }
      else{
        System.out.println("Tied with dealer. You now have $"+player.getMoney());
      }
      System.out.println("\n");
      System.out.println("Press q to quit or anything else to play another hand");
      input = scnr.next();
      }
    }
    }
  }
