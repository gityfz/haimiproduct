package sleftest;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {

/** 请完成下面这个函数，实现题目要求的功能 **/
 /** 当然，你也可以不按照这个模板来作答，完全按照自己的想法来 ^-^  **/
    static boolean resolve(int[] A) {
	int asize = A.length;
	int mark1= asize/4,mark2 =asize/2,mark3=asize*3/4;
	int fir = 0;
	int sec = 0;
	int thr =0;
	int forth = 0;
	boolean hs = false;
	for(int i=mark1;i<asize-5;i++){
	    for(int j = i+2;j<asize-3; j++){
		for(int h= j+2;h<asize-1;h++){
		    for(int a1 = 0; a1<i;a1++){
			fir=fir+a1;
		    }
		    for(int a2 = i+1; a2>i & a2<j ;a2++){
			sec=sec+a2;
		    }for(int a3 = j+1; a3>j & a3<h ;a3++){
			thr=thr+a3;
		    }for(int a4 = h+1; a4>h & a4<asize ;a4++){
			forth=forth+a4;
		    }
		    if(fir == sec & fir == thr & fir == forth){
			hs=true;
		    }
		    
		}
	    } 
	    
	}	
	return hs;
    }

    public static void main(String[] args){
        ArrayList<Integer> inputs = new ArrayList<Integer>();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while(line != null && !line.isEmpty()) {
            int value = Integer.parseInt(line.trim());
            if(value == 0) break;
            inputs.add(value);
            line = in.nextLine();
        }
        int[] A = new int[inputs.size()];
        for(int i=0; i<inputs.size(); i++) {
            A[i] = inputs.get(i).intValue();
        }
        Boolean res = resolve(A);

        System.out.println(String.valueOf(res));
    }
}
