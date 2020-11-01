package manage;

import java.util.ArrayList;
import java.util.List;

import vo.Human;

public class Rank {
	List<Human> male;
	List<Human> female;
	public int length;
	
	public Rank() {
		male = new ArrayList<>();
		female = new ArrayList<>();
		length = 2;
	}
	
	public void add(int index, Human vo) {
		if (index == 0) {
			male.add(vo);
		} else {
			female.add(vo);
		}
	}
	
	public Human get(int index, int listIndex) {
		Human result = null;
		
		if (index == 0) {
			result = male.get(listIndex);
		} else if (index == 1) {
			result = female.get(listIndex);
		}
		
		return result; 
	}
	
	public void set(int index, int listIndex, Human vo) {
		if (index == 0) {
			male.set(listIndex, vo);
		} else {
			female.set(listIndex, vo);
		}
	}
	
	public int size(int index) {
		int size = female.size();
		
		if (index == 0) {
			size = male.size();
		} 
		
		return size;
	}
}
