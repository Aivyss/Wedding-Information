package ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import manage.HumanInfo;
import vo.Female;
import vo.Human;
import vo.Male;

public class WeddingUI {
	Scanner sc;
	HumanInfo manage;
	Human loggedIn;
	
	public WeddingUI() {
		sc = new Scanner(System.in);
		manage = new HumanInfo();
		this.loggedIn = null;
		
		while (true) { // 로그인 전
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
					System.out.println("[에러] 잘못 입력하셨습니다.");
				}
			} else { // 로그인 후
				System.out.println("로그인 후 처리 부");
			}
		}
	}
	
	public void menu1() {
		System.out.println("[회원가입/로그인]");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 프로그램 종료");
		System.out.print("메뉴선택> ");
	}
	
	/**
	 * 로그인을 수행하는 메소드
	 */
	public void signIn() {
		System.out.print("아이디를 입력하세요: ");
		String id = inputString();
		System.out.print("비밀번호를 입력하세요: ");
		String pw = inputString();
		this.loggedIn = manage.signIn(id, pw);
		
		if (this.loggedIn != null) {
			System.out.println("[알림] 로그인 되었습니다.");
		}  else {
			System.out.println("[알림] 로그인에 실패했습니다.");
		}
	}
	
	/**
	 * 회원가입을 수행하는 메소드.
	 */
	public void signUp() {
		Human vo = null;
		ArrayList<Object> humanInfo = new ArrayList<>();

		System.out.print("이용할 아이디 입력: ");
		String id = inputString();
		
		vo = manage.humanMap.get(id);
		if (vo == null) {
			humanInfo.add(id);
			System.out.print("비밀번호 입력: ");
			humanInfo.add(inputString());
			System.out.print("이름 입력: ");
			humanInfo.add(inputString());
			System.out.println("성별 입력(M/F): ");
			boolean sex = inputSex();
			humanInfo.add(sex);
			System.out.println("나이 입력: ");
			humanInfo.add(inputInteger());
			System.out.println("BMI 입력: ");
			humanInfo.add(inputDouble());
			System.out.println("최종학력 입력: ");
			humanInfo.add(inputString());
			System.out.println("연봉 입력: ");
			humanInfo.add(inputInteger());
			
			boolean flag = false;
			if (sex) { // 성별이 남성인 경우
				vo = new Male(humanInfo);
				flag = manage.addAccount(vo);
			} else { // 성별이 여성인 경우
				System.out.print("시술/성형 여부(1. 얼굴전체성형, 2.양악, 3.단순시술: ");
				humanInfo.add(inputInteger());
				
				vo = new Female(humanInfo);
				flag = manage.addAccount(vo);
			}
			
			if (flag) {
				System.out.println("[알림] 회원 등록이 완료되었습니다.");
			} else {
				System.out.println("[알림] 회원 등록이 실패하였습니다.");
			}
		} else {
			System.out.println("[에러] 이미 있는 아이디 입니다.");
		}
	}
	
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
	
	public String inputString() {
		String str;
		try {
			 str = sc.nextLine();
		} catch (InputMismatchException e) {
			throw e;
		}
		
		return str;
	}
	
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
