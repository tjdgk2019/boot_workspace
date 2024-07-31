package com.kh.house.java.chap02;

import com.kh.house.java.chap02.brand.Burgerking;
import com.kh.house.java.chap02.brand.Macdonaldo;
import com.kh.house.java.chap02.drink.Beer;

public class Run {
	public static void main(String[] args) {
		OrderApp oa = new OrderApp(new Burgerking("와퍼"),new Beer());
		oa.order();
		
		System.out.println("주문을 변경합니다.");
		oa.setBurger(new Macdonaldo("빅맥"));
		oa.order();
	}
}
