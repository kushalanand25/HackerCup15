package kone.hackerrank.vmware;


class Solution{
    public static void main(String[] args){
        System.out.println(isPalindrome("abcba"));
        System.out.println(isPalindrome("abcbaa"));
        System.out.println(coins(97));
    }
    
    public static boolean isPalindrome(String str)
    {
    char[] array    = str.toCharArray();
    int len 		= array.length;
    boolean isPalin	= true;
    for(int i = 0; i < str.length() /2 ; i++ ){
        if(array[i] != array[len - i-1]){
            isPalin = false;
            break;
        }
    }
    return isPalin;
    }
    
    public static int coins(int denomination){
    	int coins	= 0;
    	int[] values = new int[]{25,10,5,1};
    	int left = denomination;
    	int currentVal;
    	int idx = 0;
    	
    	while(left > 0){
    		currentVal	= left/ values[idx];
    		left = left - (currentVal * values[idx]);
    		coins += currentVal;
    		idx++;
    	}
    	return coins;
    }
}
