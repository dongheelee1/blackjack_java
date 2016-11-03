import java.util.Scanner; 

public class Game{
	private Scanner input; 
	private Deck playerCards; 
	private Deck dealerCards; 
	private Deck originalDeck; 
	private Player player; 
	private int totalMoney; 
	private int playerInput; 
	private int bet; 
	private boolean validBet; 
	private int playerCardsAmount;
	private int dealerCardsAmount;  
	private boolean playerLose; 
	private boolean tie; 
	private boolean dealerBust; 
	private boolean playerBust; 

	public Game()
	{

		input = new Scanner(System.in);
		this.playerCards = new Deck();
		this.dealerCards = new Deck();
		this.originalDeck = new Deck();
		this.player = new Player(); 
		this.totalMoney = 0;
		playerLose = false; 
		tie = false; 
	}

	public void play()
	{

		//create + shuffle the original deck 
		originalDeck.populateDeck();
		originalDeck.shuffle();
		//to play the game, player must buy-in for at least $100.00.
		totalMoney = player.buyIn(); 
		while(totalMoney != 0){
			//first, place a bet 
			System.out.println("You currently have " + totalMoney + " dollars. Place a bet amount."); 
			bet = input.nextInt(); 
			validBet = player.makeBet(bet); //check to see if bet made is a valid amount

			if(validBet)
			{
				System.out.println("Awesome. We are ready to start the Blackjack death match!!!!\n");

				//deal cards out to player and dealer
				playerCards.drawCard(originalDeck); 
				playerCards.drawCard(originalDeck); 
				dealerCards.drawCard(originalDeck); 
				dealerCards.drawCard(originalDeck);

				playerCardsAmount = playerCards.getCardsAmount(); 
				dealerCardsAmount = dealerCards.getCardsAmount();  

				while(playerBust == false)
				{
					//show players cards
					System.out.println("Your hand: " + playerCards.toString()); 
					System.out.println("You - cards amount: " + playerCardsAmount + "\n"); 
					System.out.println("Dealer's hand: \n" + dealerCards.retrieve(dealerCards.getSize()-1).toString()+"\nhidden card\n"); 
					System.out.println("Would you like to stand [1] or hit [2]?"); 

					playerInput = input.nextInt(); 
					if(playerInput == 1) //player stands, playerBust = false
					{
						break; 
					}
					else//player hits
					{
						//shuffle deck if original deck size is smaller than 12
						if(originalDeck.getSize() < 12)
						{
							originalDeck.shuffle(); 
						}

						playerCards.drawCard(originalDeck);//draw topmost card
						playerCardsAmount=playerCards.getCardsAmount();//get all of your cards amoutn 
						System.out.println("You drew a " + playerCards.retrieve(playerCards.getSize()-1).toString()); 
						System.out.println("You - cards amount: " + playerCardsAmount + "\n"); 
						if(playerCardsAmount>21) //YOU BUST
						{
							playerLose = true; 
							playerBust = true;  
							break; 
						}
					}
				}

				System.out.println("\n"+"Revealing: Dealer's hand: " + dealerCards.toString()); 
				System.out.println("Dealer - cards amount: " + dealerCardsAmount); 


				// --------------------Dealer's turn!!!--------------------------------------

				if(playerBust == false)
				{
					if(dealerCardsAmount > 21) //DEALER BUSTS; dealer's cards > 21
					{
						playerLose = false;
						dealerBust = true; 
					}
					else if(dealerCardsAmount > playerCardsAmount) //NONE BUSTS; dealer's cards < 21 but > player cards 
					{
						playerLose = true; 
						//playerBust == false

					}
					else if(dealerCardsAmount < playerCardsAmount)
					{
						while(dealerCardsAmount < 17){
							System.out.println("Hitting..."); 
							if(originalDeck.getSize() < 12)
							{
								originalDeck.shuffle(); 
							}
							dealerCards.drawCard(originalDeck); //draw topmost card 
							dealerCardsAmount = dealerCards.getCardsAmount(); 
							System.out.println("Dealer draws " + dealerCards.retrieve(dealerCards.getSize()-1).toString()); 
						}
						//check on the dealer 

						System.out.println("Dealer - new hand: " + dealerCards.toString()); 
						System.out.println("Dealer - cards amount: " + dealerCardsAmount);  
						System.out.println(dealerCardsAmount);

						if(dealerCardsAmount > 21) //dealer busts
						{
							playerLose = false;
							dealerBust = true; 

						}
						else if(dealerCardsAmount < playerCardsAmount) //player wins, dealer loses
						{
							playerLose = false; 
							dealerBust = false; 
						}
						else if(dealerCardsAmount > playerCardsAmount) //player loses, dealer wins
						{
							playerLose = true; 
							dealerBust = false; 
						}	
						else //neither lose/win
						{
							tie = true;
						}
					}
					else if(dealerCardsAmount == playerCardsAmount)
					{	
						tie = true;  
					}

				}//closes out the if 

				if(tie)
				{
					System.out.println("It's a push! You neither win/lose money!"); 
				}
				else if(playerLose==true && playerBust==true)
				{
					totalMoney -= bet;
					System.out.println("Busted. You lose! Dealer Wins!");
				}
				else if(playerLose==true && playerBust==false)
				{
					totalMoney -= bet;
					System.out.println("Dealer got a higher card total than you. You lose! Dealer Wins!");
				}
				else if(playerLose==false && dealerBust==true) 
				{
					System.out.println(bet*1.5); 
					totalMoney += bet*1.5;
					System.out.println("Dealer busted. You win. Dealer loses!"); 
				}
				else if(playerLose==false && dealerBust==false)
				{
					System.out.println(bet*1.5); 
					totalMoney += bet*1.5;
					System.out.println("You got a higher card total than the Dealer. You win! Dealer loses!"); 
				}
				playerBust = false;
				tie = false;

				playerCards.moveCards(originalDeck);
				dealerCards.moveCards(originalDeck); 

			}//closes out the if valid bet

		}//closes out the while

	}//closes out the play
}