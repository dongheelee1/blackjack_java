import java.util.Scanner; 
public class Player{

	private int totalMoney; 
	private Scanner input; 
	private Boolean validBet; 

	public Player()
	{
		this.totalMoney = 0; 
		input = new Scanner(System.in);
	}

	public int buyIn()
	{
		System.out.println("To play the game, you must buy-in for at least $100. What is your buy-in amount?");
		int amount = input.nextInt(); 
		if(amount<100)
		{
			System.out.println("Invalid amount!");
			buyIn();
		}
		else{
			totalMoney += amount; 
		}
		return totalMoney;  
	}

	public Boolean makeBet(int bet)
	{
		validBet = true;
		System.out.println(bet);
		System.out.println(totalMoney);
		if(bet>totalMoney || bet<10 || bet>1000)
		{
			if(bet>totalMoney)
			{
				System.out.println("Invalid amount! You gon get broke if you lose!"); 
			}
			if(bet<10 || bet>1000)
			{
				System.out.println(bet);
				System.out.println("Bet must be between 10 and 1000 dollars!"); 
			}
			validBet = false; 
		}
		return validBet; 
	}
	
}