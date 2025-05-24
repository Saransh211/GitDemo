
public class tryQ {
	public static void main(String[] args) {
		int[] arr = {4,3,25,6,7,8,9,2,3,10};
		int count = 0;
		int maxx = 0;
		for(int i=0;i<arr.length-1;i++)
		{
			if(arr[i]+1==arr[i+1])
			{
				count++;
			}
			else
			{
				count = 0;
			}
			maxx = Math.max(maxx, count+1);
		}
		System.out.println(maxx);
	}
}
