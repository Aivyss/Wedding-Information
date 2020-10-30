package ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import manage.HumanInfo;
import vo.Female;
import vo.Human;
import vo.Male;

public class WeddingUI {
	// UI Ŭ������ �������
	Scanner sc;
	HumanInfo manage;
	Human loggedIn;
	
	/**
	 * UI ��ü ������.
	 * ui����� ������ ����Ѵ�.
	 */
	public WeddingUI() {
		sc = new Scanner(System.in);
		manage = new HumanInfo();
		this.loggedIn = null;
		
		while (true) { // �α��� ��
			if (this.loggedIn == null) {
				menu1();
				int selector = inputInteger();
				
				if (selector == 1) {
					signIn();
				} else if (selector == 2) {
					signUp();
				} else if (selector == 3) {
					break;
				} else {
					System.out.println("[����] �߸� �Է��ϼ̽��ϴ�.");
				}
			} else { // �α��� ��
				System.out.println("�α��� �� ó�� ��");
			}
		}
	}
	
	/**
	 * �α��� ���� �޴�ȭ���� �����ִ� �޼ҵ�
	 */
	public void menu1() {
		System.out.println("[ȸ������/�α���]");
		System.out.println("1. �α���");
		System.out.println("2. ȸ������");
		System.out.println("3. ���α׷� ����");
		System.out.print("�޴�����> ");
	}
	
	/**
	 * �α����� �����ϴ� �޼ҵ�
	 */
	public void signIn() {
		System.out.print("���̵� �Է��ϼ���: ");
		String id = inputString();
		System.out.print("��й�ȣ�� �Է��ϼ���: ");
		String pw = inputString();
		this.loggedIn = manage.signIn(id, pw);
		
		if (this.loggedIn != null) {
			System.out.println("[�˸�] �α��� �Ǿ����ϴ�.");
		}  else {
			System.out.println("[�˸�] �α��ο� �����߽��ϴ�.");
		}
	}
	
	/**
	 * ȸ�������� �����ϴ� �޼ҵ�.
	 */
	public void signUp() {
		Human vo = null;
		ArrayList<Object> humanInfo = new ArrayList<>();

		System.out.print("�̿��� ���̵� �Է�: ");
		String id = inputString();
		
		vo = manage.humanMap.get(id);
		if (vo == null) {
			humanInfo.add(id);
			System.out.print("��й�ȣ �Է�: ");
			humanInfo.add(inputString());
			System.out.print("�̸� �Է�: ");
			humanInfo.add(inputString());
			System.out.println("���� �Է�(M/F): ");
			boolean sex = inputSex();
			humanInfo.add(sex);
			System.out.println("���� �Է�: ");
			humanInfo.add(inputInteger());
			System.out.println("BMI �Է�: ");
			humanInfo.add(inputDouble());
			System.out.println("�����з� �Է�: ");
			humanInfo.add(inputString());
			System.out.println("���� �Է�: ");
			humanInfo.add(inputInteger());
			
			boolean flag = false;
			if (sex) { // ������ ������ ���
				vo = new Male(humanInfo);
				flag = manage.addAccount(vo);
			} else { // ������ ������ ���
				System.out.print("�ü�/���� ����(1. ����ü����, 2.���, 3.�ܼ��ü�: ");
				humanInfo.add(inputInteger());
				
				vo = new Female(humanInfo);
				flag = manage.addAccount(vo);
			}
			
			if (flag) {
				System.out.println("[�˸�] ȸ�� ����� �Ϸ�Ǿ����ϴ�.");
			} else {
				System.out.println("[�˸�] ȸ�� ����� �����Ͽ����ϴ�.");
			}
		} else {
			System.out.println("[����] �̹� �ִ� ���̵� �Դϴ�.");
		}
	}
	
	/**
	 * int Ÿ���� �����͸� �ֱ� ���ؼ� ����ϴ� �޼ҵ�
	 * ����ó�� ����� �����Ͽ���.
	 * @return ������ ��ȯ
	 */
	public int inputInteger() {
		int output;
		
		try {
			output = sc.nextInt();
			sc.nextLine();
		} catch(InputMismatchException e) {
			sc.nextLine();
			throw e;
		}
		
		return output;
	}
	/**
	 * double Ÿ���� �����͸� �ֱ� ���ؼ� ����ϴ� �޼ҵ�
	 * ����ó�� ����� �����Ͽ���.
	 * @return �Ǽ��� ��ȯ
	 */
	public double inputDouble() {
		double output;
		
		try {
			output = sc.nextDouble();
			sc.nextLine();
		} catch(InputMismatchException e) {
			sc.nextLine();
			throw e;
		}
		
		return output;
	}
	
	/**
	 * String Ÿ���� �����͸� �ֱ� ���ؼ� ����ϴ� �޼ҵ�
	 * ����ó�� ����� �����Ͽ���.
	 * @return ���ڿ�
	 */
	public String inputString() {
		String str;
		try {
			 str = sc.nextLine();
		} catch (InputMismatchException e) {
			throw e;
		}
		
		return str;
	}
	
	/**
	 * ������ �ֱ� ���� ����ϴ� �޼ҵ� String���� boolean���� ��ȯ.
	 * ����� ���Ǽ��� ���ؼ� �߰���.
	 * @return �����̸� true �����̸� false
	 */
	public boolean inputSex() {
		String str;
		boolean sex;
		
		str = sc.nextLine();
		
		if (str.equals("M")) {
			sex = true;
		} else if (str.equals("F")) {
			sex = false;
		} else {
			InputMismatchException e = new InputMismatchException();
			throw e;
		}
		
		return sex;
	}
}
