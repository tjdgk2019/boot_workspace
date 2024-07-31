package com.kh.house.java.chap02;

import com.kh.house.java.chap02.strategy.DrinkStrategy;
import com.kh.house.java.chap02.strategy.MenuStrategy;

import lombok.val;

public class OrderApp {
	private MenuStrategy burger;
	private DrinkStrategy drink;
	
	public OrderApp(MenuStrategy burger, DrinkStrategy drink) {
		this.burger= burger;
		this.drink= drink;
	}
	public void setBurger(MenuStrategy burger) {
		this.burger= burger;
	}
	
	public void setDrink(DrinkStrategy drink) {
		this.drink=drink;
	}
	public void order() {
		burger.orderMenu();
		drink.orderDrink();
	}
}
