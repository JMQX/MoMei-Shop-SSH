package com.momei.util;

import java.util.ArrayList;

public class Sort_deal {
	
	    //≤Â»Î≈≈–Ú
		public static int[] sort(ArrayList<Integer> list)
		{
			int arry[]=new int[list.size()];
			for(int i=0;i<list.size();i++)
			{
				arry[i]=list.get(i);
			}
			for(int i=0;i<arry.length;i++)
			{
				int insetVal=arry[i];
				int index=i-1;
				while(index>=0&&insetVal<arry[index])
				{
					arry[index+1]=arry[index];
					index--;
				}
				arry[index+1]=insetVal;
			}
			
			return arry;
			
		}

}
