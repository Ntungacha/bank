package pvibank;

public class Card {

	private long cardNumber;
	private String cardType;
	private int pin;
	private int cardID;

	public Card(long cardNumber, String cardType, int pin) {
		super();
		this.cardNumber = cardNumber;
		this.cardType = cardType;
		this.pin = pin;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Card() {

	}

	public int getCardID() {
		return cardID;
	}

	public void setCardID(int cardID) {
		this.cardID = cardID;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String toString() {
		return "\nCardNumber=" + cardNumber + "\nPin=" + pin + "\ncard Type=" + cardType;
	}

}
