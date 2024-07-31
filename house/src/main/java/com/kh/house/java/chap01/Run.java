package com.kh.house.java.chap01;

import com.kh.house.java.brand.Burgerking;
import com.kh.house.java.brand.Macdonaldo;

public class Run {
	
	public static void main(String[] args) {
		Burgerking bk = new Burgerking();
		
		bk.orderLongChicken();
		bk.orderToCoke();
		
		Macdonaldo mc = new Macdonaldo();
		mc.orderBigMac();
		mc.orderCoke();
		
	}
}
