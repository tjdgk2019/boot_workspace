package com.kh.house.java.chap02.drink;

import com.kh.house.java.chap02.strategy.DrinkStrategy;

public class Coke implements DrinkStrategy{
	@Override
	public void orderDrink() {
		System.out.println("콜라를 주문합니다.");
	}
}
