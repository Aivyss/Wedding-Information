package main;

import java.util.ArrayList;
import java.util.HashMap;

import ui.WeddingUI;
import vo.Human;

public class Main {

	public static void main(String[] args) {
		// new WeddingUI();
		ArrayList<Human> list = new ArrayList<>();
		HashMap<String, Human> map = new HashMap<>();
		
		list.add(new Human());
		map.put("1", list.get(0));
		list.get(0).setLock(true);
		
		
		System.out.println(list.get(0).isLock());
		System.out.println(map.get("1").isSex());
	}

}
