import java.util.ArrayList;

public class Deck
{
	private ArrayList<Card> deck;
	private ArrayList<Card> shuffledDeck;
	private String[] suits = {"Hearts", "Spades", "Clubs", "Diamonds"};
	private String[] values = {"A", "2", "3",  "4",  "5",  "6",  "7",  "8",  "9",  "10", "J", "Q", "K"};

	public Deck(){
		this.deck = new ArrayList<Card>();
	}

	public void populateDeck()
	{

		for(int i=0; i<suits.length; i++)
		{
			for(int j=0; j<values.length; j++)
			{
				//add a new card to deck 
				this.deck.add(new Card(suits[i], values[j]));
			}
		}
	}

	//default 
	public String toString()
	{
		String cards = "";

		for(Card card:this.deck)
		{
			cards += "\n"+ card.toString();
		}

		return cards;
	}

	public void shuffle()
	{	
		shuffledDeck = new ArrayList<Card>();
		while(this.deck.size() != 0)
		{	
			int randIdx = (int)(Math.random()*this.deck.size());	
			shuffledDeck.add(this.deck.get(randIdx));
			this.deck.remove(randIdx);
		}
		this.deck = shuffledDeck;
	}

	public void drawCard(Deck originalDeck)
	{
		//draw a card from top of the playing/original deck 
		this.deck.add(originalDeck.retrieve(0)); 
		//remove card from teh original deck 
		originalDeck.removeCard(0); 
	}

	public Card retrieve(int idx)
	{
		//retrieve 
		Card card = this.deck.get(idx);
		return card; 
	}

	public void removeCard(int idx)
	{
		this.deck.remove(idx); 
	}

	public void addCard(Card card)
	{
		this.deck.add(card);
	}

	public int getSize()
	{
		return this.deck.size(); 
	}

	public int getCardsAmount()
	{
		int total = 0; 

		int countOfAces = 0; 

		for(Card card:this.deck) //goes through each card in the specified deck 
		{
			String value = card.getValue(); 
			
			if(value == "A")
			{
				countOfAces++; 
			}
			if(value == "2")
			{
				total+=2; 
			} 
			if(value == "3")
			{
				total+=3; 
			}
			if(value == "4")
			{
				total+=4; 
			} 
			if(value == "5")
			{
				total+=5; 
			}
			if(value == "6")
			{
				total+=6; 
			}
			if(value == "7")
			{
				total+=7; 
			}
			if(value == "8")
			{	
				total+=8; 
			}
			if(value == "9")
			{
				total+=9; 
			}
			if(value == "10")
			{
				total+=10; 
			}
			if(value == "J" || value == "Q" || value == "K")
			{
				total+=10; 
			}
		}

		while(countOfAces>0)
		{
			if(total<=10) 
			{
				total += 11;
			}else{
				total += 1; 
			}
			countOfAces--; 
		}

		return total; 
	}

	public void moveCards(Deck originalDeck)
	{
		while(this.deck.size() != 0)
		{
			originalDeck.addCard(this.deck.get(this.deck.size()-1)); 
			this.deck.remove(this.deck.size()-1); 
		}
	}

}