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
	 * UI ��ü ������. ui����� ������ ����Ѵ�.
	 */
	public WeddingUI() {
		sc = new Scanner(System.in);
		manage = new HumanInfo();
		this.loggedIn = null;

		while (true) {
			if (this.loggedIn == null) { // �α��� ��
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
				while (true) {
					menu2();
					int selector = inputInteger();

					if (selector == 1) {
						deposit();
					} else if (selector == 2) {
						match();
					} else if (selector == 3) {
						accept();
					} else if (selector == 4) {
						seeStatus();
					} else if (selector == 0) {
						System.out.println("[�˸�] ���ܰ�� �̵��մϴ�.");
						this.loggedIn = null;
						break;
					} else {
						System.out.println("[����] �߸� �Է��ϼ̽��ϴ�.");
					}
				}
			}
		} // while end
	}

	/**
	 * �α��� ���� �޴�ȭ���� �����ִ� �޼ҵ�
	 */
	public void menu1() {
		System.out.println("==========================");
		System.out.println("[ȸ������/�α���]");
		System.out.println("1. �α���");
		System.out.println("2. ȸ������");
		System.out.println("3. ���α׷� ����");
		System.out.print("�޴�����> ");
	}

	/**
	 * �Ϲ�ȸ���� �α��� �� ȭ���� �����ִ� �޼ҵ�
	 */
	public void menu2() {
		System.out.println("==========================");
		System.out.println("[" + loggedIn.getName() + "ȸ���� ȯ���մϴ�.]");
		System.out.println("1. ĳ�� �����ϱ�");
		System.out.println("2. ��� ��Ī�ϱ�");
		System.out.println("3. ��� ��Ī �����ϱ�");
		System.out.println("4. ��Ī ���� ��Ȳ");
		System.out.println("0. �α��� ���ܰ�� �̵�");
		System.out.print("�޴�����> ");
	}

	/**
	 * �α����� �����ϴ� �޼ҵ�
	 */
	public void signIn() {
		System.out.println("==========================");
		System.out.print("���̵� �Է��ϼ���: ");
		String id = inputString();
		System.out.print("��й�ȣ�� �Է��ϼ���: ");
		String pw = inputString();
		this.loggedIn = manage.signIn(id, pw);

		if (this.loggedIn != null) {
			System.out.println("[�˸�] �α��� �Ǿ����ϴ�.");
		} else {
			System.out.println("[�˸�] �α��ο� �����߽��ϴ�.");
		}
	}

	/**
	 * ȸ�������� �����ϴ� �޼ҵ�.
	 */
	public void signUp() {
		Human vo = null;
		ArrayList<Object> humanInfo = new ArrayList<>();

		System.out.println("==========================");
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
			System.out.println("Ű �Է�: ");
			humanInfo.add(inputInteger());
			System.out.println("BMI �Է�: ");
			humanInfo.add(inputDouble());
			System.out.println("�������� �Է�: ");
			humanInfo.add(inputString());
			System.out.println("�����з� �з� 1. ���̺񸮱�, 2. ����, ī�̽�Ʈ, ����, 3. ���� �ؿܴ���, 4. �μ���,  5. �ΰ��,  6.���ű�, 7. �����");
			humanInfo.add(inputInteger());
			System.out.println("���� �Է�: ");
			humanInfo.add(inputInteger());

			boolean flag = false;
			if (sex) { // ������ ������ ���
				System.out.println("Ż�� ����(Y/N)");
				humanInfo.add(inputChoice());

				vo = new Male(humanInfo);
				flag = manage.addAccount(vo);
			} else { // ������ ������ ���
				System.out.print("�ü�/���� ����(1. ����ü����, 2.���, 3.�ܼ��ü�, 4.�ش����: ");
				humanInfo.add(inputInteger());

				vo = new Female(humanInfo);
				flag = manage.addAccount(vo);
			}

			if (flag) {
				System.out.println("[�˸�] ȸ�� ����� �Ϸ�Ǿ����ϴ�.");
				manage.giveScore(vo);
			} else {
				System.out.println("[�˸�] ȸ�� ����� �����Ͽ����ϴ�.");
			}
		} else {
			System.out.println("[����] �̹� �ִ� ���̵� �Դϴ�.");
		}
	}

	/**
	 * ĳ�ø� �����ϴ� �޼ҵ�
	 */
	public void deposit() {
		System.out.print("���� Ȯ���� ���� ��й�ȣ�� �Է��ϼ���: ");
		String pw = inputString();

		if (pw.equals(loggedIn.getPassword())) {
			System.out.print("������ �ݾ��� �Է�: ");
			int cash = inputInteger();

			manage.deposit(loggedIn, pw, cash);
		} else {
			System.out.println("[����] ��й�ȣ�� Ʋ�Ƚ��ϴ�.");
		}
	}

	/**
	 * ��븦 ��Ī�ϴ� �޼ҵ�
	 */
	public void match() {
		int level = this.loggedIn.getLevel();
		String grade = this.loggedIn.getGrade();
		boolean sex = this.loggedIn.isSex();

		if (loggedIn.isLock()) {
			System.out.println("[����] ��Ī���� ������̰ų� ���� ���θ� �Ǵ��� ����� �ֽ��ϴ�.");
		} else {
			System.out.println("===========================");
			System.out.println(loggedIn.toString());
			System.out.println("����� ����� " + grade + "�Դϴ�.");
			System.out.println("6. ���� 5. ���̾� 4. �÷�Ƽ�� 3. ��� 2. �ǹ� 1. ����� 0. ��");
			System.out.print("����� ��Ī�� ���ϴ� ����� ������: ");

			boolean choice = true;
			while (choice) {
				boolean flag = false;
				System.out.println(); // �並 ���� ó��
				System.out.print("��Ī ����> ");
				int selector = inputInteger();

				if (level >= selector) { // 0 > 6
					Human another = manage.searchMatch(selector, sex);

					if (another == null) {
						System.out.println("[����] ��޿� �ο��� �����ϴ�.");
						break;
					}
					System.out.println(another.toString()); // ��� ���� ���
					System.out.print("�����Ͻðڽ��ϱ�(Y/N): ");
					choice = !inputChoice();

					if (choice) {
						System.out.print("�� �˻��Ͻðڽ��ϱ�(Y/N): ");
						choice = inputChoice();
					} else {
						flag = manage.match(loggedIn, another);
						if (flag) {
							System.out.println("��Ī��û�� ���½��ϴ�.");
						} else {
							System.out.println("��Ī�� �����߽��ϴ�.");
						}
					}
				} else {
					System.out.println("[����] ������ �� ���� ����Դϴ�.");
					break;
				}
			}
		}
	}

	/**
	 * ��� ��Ī Ȯ�� �� �����ϴ� �޼ҵ�
	 */
	public void accept() {
		String matchedId = this.loggedIn.getMatchedId();
		Human vo = manage.searchAccount(matchedId); // ����� ����(��ü)

		System.out.println("=====================");
		System.out.println("[��Ī��û�� ���� ����� �����Դϴ�]");
		System.out.println(vo.toString());
		System.out.print("��Ī ����(Y/N): ");
		boolean flag = inputChoice();

		flag = manage.accept(loggedIn.getId(), matchedId, flag);

		if (flag) {
			System.out.println("[�˸�] ��Ī�� �����Ͽ����ϴ�.");
		} else {
			System.out.println("[�˸�] ��Ī�� �ź��Ͽ����ϴ�.");
		}
	}

	/**
	 * ��Ī ���� ��Ȳ�� �����ִ� �޼ҵ�
	 */
	public void seeStatus() {
		if (loggedIn.isSuccess()) {
			Human vo = manage.searchAccount(loggedIn.getMatchedId());

			System.out.println("[�˸�] ��Ī�� ����Ǿ����ϴ�.");
			System.out.println("===============================");
			System.out.println(vo.toString());
		} else {
			System.out.println("[�˸�] ��Ī ����� ������ �����ϴ�.");
		}
	}

	/**
	 * int Ÿ���� �����͸� �ֱ� ���ؼ� ����ϴ� �޼ҵ� ����ó�� ����� �����Ͽ���.
	 * 
	 * @return ������ ��ȯ
	 */
	public int inputInteger() {
		int output;

		try {
			output = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			sc.nextLine();
			throw e;
		}

		return output;
	}

	/**
	 * double Ÿ���� �����͸� �ֱ� ���ؼ� ����ϴ� �޼ҵ� ����ó�� ����� �����Ͽ���.
	 * 
	 * @return �Ǽ��� ��ȯ
	 */
	public double inputDouble() {
		double output;

		try {
			output = sc.nextDouble();
			sc.nextLine();
		} catch (InputMismatchException e) {
			sc.nextLine();
			throw e;
		}

		return output;
	}

	/**
	 * String Ÿ���� �����͸� �ֱ� ���ؼ� ����ϴ� �޼ҵ� ����ó�� ����� �����Ͽ���.
	 * 
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
	 * ������ �ֱ� ���� ����ϴ� �޼ҵ� String���� boolean���� ��ȯ. ����� ���Ǽ��� ���ؼ� �߰���.
	 * 
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

	/**
	 * Y/N ����
	 * 
	 * @return yes�̸� true, no�̸� false
	 */
	public boolean inputChoice() {
		String str;
		boolean choice;

		str = sc.nextLine();

		if (str.equals("Y")) {
			choice = true;
		} else if (str.equals("N")) {
			choice = false;
		} else {
			InputMismatchException e = new InputMismatchException();
			throw e;
		}

		return choice;
	}
}
