// Sito Arystotenesa
// https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
public class Sito{
	private int max;
	private int[] pierwsze;

	public Sito(int n){
		this.max = n;
		this.pierwsze = sito(n);
	}

	private int[] sito(int n){

		boolean a[] = new boolean[n+1];
		int count = 0;
		
		for(int i = 0; i <= n; i++)
			a[i] = true;

		for(int i = 2; i < n; i++){
			if(a[i]){
				for(int j = i*i; j <= n; j += i){
					if(a[j] != false)
						count++;
					a[j] = false;
				}
			}
		}

		int ilePierwszych = (n + 1) - count - 2; 

		int p[] = new int[ilePierwszych];

		int j = 2;

		for(int i = 0; i < ilePierwszych; i++){
			while(a[j] != true)
				j++;
			p[i] = j;
			j++;
		}

		return p;
	}

	public String toString(){

		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < pierwsze.length; i++)
			sb.append(this.pierwsze[i]+" ");

		return "Liczby pierwsze z przedziału 2 do "+this.max+" to: "+sb.toString();

	}
}
