package uniandes.algorithms.coinchange;

public class RecursiveCoinChangeAlgorithm implements CoinChangeAlgorithm {
	
	int[] optimalChange;
	int[][] rememberk;
	
	@Override
	public int[] calculateOptimalChange(int totalValue, int[] denominations) 
	{
		rememberk = new int[denominations.length][totalValue+1];
		calculateOptimalChangeNumbers (denominations.length-1, totalValue, denominations);
		rollBack(totalValue, denominations);
		return optimalChange;
	}
	
	private int calculateOptimalChangeNumbers (int i, int j, int[] denominations)
	{
		if(i == 0 & j != 0) 
		{
			rememberk[i][j]=j;	
			return j;
		} else if(j == 0) 
		{
			rememberk[i][j]=0;	
			return 0;
		} else {
			int minValue = (int) Double.POSITIVE_INFINITY;
			int maxValueOfk = j/denominations[i];					
			for (int k = 0; k <= maxValueOfk; k++) 
			{
				int aux = calculateOptimalChangeNumbers(i-1, j-k*denominations[i], denominations) + k;
				if(aux <= minValue) 
				{
					minValue = aux;
					rememberk[i][j] = k;
				}
			}	
			return minValue;
		}									
	}
	
	public void rollBack( int totalValue, int[] denominations) {		
		optimalChange=new int[rememberk.length];
		int valorTotal=totalValue;
		
		for (int i = rememberk[0].length-1; i >= 0 & valorTotal!= 0; i--) 
		{
			for (int j = rememberk.length-1; j >= 0 & valorTotal!= 0; j--) 
			{
				int aux2 =rememberk[j][i]; 
				
				if(aux2 != 0) 
				{
					optimalChange[j]=aux2;					
					i= valorTotal-aux2*denominations[j]+1;
					valorTotal=valorTotal-aux2*denominations[j];
					j= rememberk.length-1;
					break;
				}
			}
		}		
	}	
}