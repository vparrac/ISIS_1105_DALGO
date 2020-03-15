package uniandes.algorithms.coinchange;

public class GreedyCoinChangeAlgorithm implements CoinChangeAlgorithm {
	
	@Override
	public int[] calculateOptimalChange(int totalValue, int[] denominations) {		
		int value = totalValue;
		int[] coinChange= new int[denominations.length];		
		for (int i = denominations.length-1; i >=0; i--) {	
			int greaterDenomination = denominations[i];			
			int times = value/greaterDenomination;			
			coinChange[i]=(times<0)?0:times;			
			value = (value==0)?0:(value - times*greaterDenomination);		
		}		
		return coinChange;
	}
}
	