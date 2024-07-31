package com.kh.house.java.chap02.brand;

import com.kh.house.java.chap02.store.HamburgerStore;
import com.kh.house.java.chap02.strategy.MenuStrategy;

public class Burgerking extends HamburgerStore implements MenuStrategy{
	public Burgerking(String menu) {
		super(menu);
	}
	
	@Override
	public void orderMenu() {
		System.out.println("버거킹에서"+ super.getMenu()+"을(를) 주문합니다.");
	}
}
