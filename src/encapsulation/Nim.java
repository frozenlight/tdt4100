package encapsulation;

public class Nim {
	public int number;
	private int[] piles;
	
	public void removePieces(int number, int targetPile) {
		if (isGameOver()) {
			throw new IllegalStateException ("The game has finished");
		}
		if (number <= 0) {
			throw new IllegalArgumentException ("Cannot remove less than one piece");
		}
		if (piles[targetPile] < number) {
			throw new IllegalArgumentException ("Number of pieces left has to be grates than those you try to remove");
		}
		if (isValidMove(number,targetPile)) {
			piles[targetPile] -= number;
		}
	}
	boolean isValidMove(int number, int targetPile) {
		if (number >= 1 && piles[targetPile] >= number && (! isGameOver())) {
			return true;
		}
		else {
			return false;
		}
	}
	boolean isGameOver() {
		if (piles[0] == 0 || piles[1] == 0 || piles[2] == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	int getPile(int targetPile) {
		return piles[targetPile];
	}
	public String toString() {
		String currentGame = "The piles 0, 1 and 2 contain" + getPile(0) + getPile(1) + getPile(2) + " respectivly";
		return currentGame;
	}
	public Nim(int pileSize) {
		piles = new int[]{pileSize,pileSize,pileSize};
	}
	public Nim() {
		piles = new int[]{12,12,12};
	}
}
