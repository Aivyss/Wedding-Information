package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import manage.HumanInfo;

public class WeddingUI {
	Scanner sc;
	HumanInfo manage;
	
	public WeddingUI() {
		sc = new Scanner(System.in);
		manage = new HumanInfo();
	}
	
	public void signIn() {
		System.out.print("���̵� �Է��ϼ���: ");
		String id = inputString();
		System.out.print("��й�ȣ�� �Է��ϼ���: ");
		String pw = inputString();
	}
	
	public String inputString() {
		String str;
		try {
			 str = sc.nextLine();
		} catch (InputMismatchException e) {
			throw e;
		}
		
		return str;
	}
}
