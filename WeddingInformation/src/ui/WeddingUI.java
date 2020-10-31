package ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import manage.HumanInfo;
import vo.Female;
import vo.Human;
import vo.Male;

public class WeddingUI {
	// UI 클래스의 멤버변수
	Scanner sc;
	HumanInfo manage;
	Human loggedIn;
	
	/**
	 * UI 객체 생성자.
	 * ui기능의 전반을 담당한다.
	 */
	public WeddingUI() {
		sc = new Scanner(System.in);
		manage = new HumanInfo();
		this.loggedIn = null;
		
		while (true) { 
			if (this.loggedIn == null) { // 로그인 전
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
				if (loggedIn.getId().equals("admin")) { // 관리자로 접속한 경우
					while(true) {
						adminMenu();
						int selector = inputInteger();
						
						if (selector == 1) {
							giveGrade();
						} else if (selector == 0) {
							System.out.println("[알림] 전단계로 이동합니다");
							this.loggedIn = null;
							break;
						} else {
							System.out.println("[에러] 잘못 입력하셨습니다.");
						}	
					}
				} else { // 일반 회원으로 접속한 경우
					while (true) {
						menu2();
						int selector = inputInteger();
						
						if (selector == 1) {
							deposit();
						} else if (selector == 2) {
							match();
						} else if (selector == 3) {
							accept();
						} else if (selector == 0) {
							System.out.println("[알림] 전단계로 이동합니다.");
							this.loggedIn = null;
							break;
						} else {
							System.out.println("[에러] 잘못 입력하셨습니다.");
						}
					}
				}
			}
		} // while end
	}
	
	/**
	 * 로그인 전의 메뉴화면을 보여주는 메소드
	 */
	public void menu1() {
		System.out.println("==========================");
		System.out.println("[회원가입/로그인]");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 프로그램 종료");
		System.out.print("메뉴선택> ");
	}
	
	/**
	 * 일반회원의 로그인 후 화면을 보여주는 메소드
	 */
	public void menu2() {
		System.out.println("==========================");
		System.out.println("[" + loggedIn.getName() + "회원님 환영합니다.]");
		System.out.println("1. 캐시 충전하기");
		System.out.println("2. 상대 매칭하기");
		System.out.println("3. 상대 매칭 수락하기");
		System.out.println("0. 로그인 전단계로 이동");
		System.out.print("메뉴선택> ");
	}
	
	/**
	 * admin의 로그인 후 화면을 보여주는 메소드
	 */
	public void adminMenu() {
		System.out.println("==========================");
		System.out.println("[admin 관리 페이지]");
		System.out.println("1. 회원 정보 수정 및 등급부여");
		System.out.println("0. 로그인 전단계로 이동");
		System.out.print("메뉴선택> ");
	}
	
	/**
	 * 로그인을 수행하는 메소드
	 */
	public void signIn() {
		System.out.println("==========================");
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
		
		System.out.println("==========================");
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
				System.out.println("탈모 여부(Y/N)");
				humanInfo.add(inputChoice());
				
				vo = new Male(humanInfo);
				flag = manage.addAccount(vo);
			} else { // 성별이 여성인 경우
				System.out.print("시술/성형 여부(1. 얼굴전체성형, 2.양악, 3.단순시술, 4.해당없음: ");
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
	
	/**
	 * 관리자가 회원의 정보 추가 및 등급을 부여하는 메소드
	 */
	public void giveGrade() {
		System.out.println("==========================");
		System.out.print("수정하려는 회원 아이디: ");
		Human vo = manage.searchAccount(inputString());
		
		if (vo != null) {
			if (vo.isSex()) { // 남성 정보를 수정하는 프로세스
				
			} else { // 여성 정보를 수정하는 프로세스
				Female voF = (Female) vo;
				
				manage.giveFemaleScore(voF);
				manage.humanMap.put(voF.getId(), voF);
			}
			
			manage.giveGrade();
			System.out.println("[알림] 정보에 대한 처리가 완료 되었습니다.");
		} else {
			System.out.println("[에러] 일치하는 회원 정보가 없습니다.");
		}
	}
	
	/**
	 * 캐시를 충전하는 메소드
	 */
	public void deposit() {
		System.out.print("본인 확인을 위한 비밀번호를 입력하세요: ");
		String pw = inputString();
		
		if(pw.equals(loggedIn.getPassword())) {
			System.out.print("충전할 금액을 입력: ");
			int cash = inputInteger();
			
			manage.deposit(loggedIn, pw, cash);
		} else {
			System.out.println("[에러] 비밀번호가 틀렸습니다.");
		}
	}
	
	/**
	 * 상대를 매칭하는 메소드
	 */
	public void match() {
		int level = this.loggedIn.getLevel();
		String grade = this.loggedIn.getGrade()[level];
		boolean sex = this.loggedIn.isSex();
		
		if(loggedIn.isLock()) {
			System.out.println("[에러] 매칭수락 대기중이거나 수락 여부를 판단할 대상이 있습니다.");
		} else {
			System.out.println("===========================");
			System.out.println("당신의 등급은 " + grade + "입니다.");
			System.out.print("당신이 매칭을 원하는 등급을 고르세요: ");
			
			for (int i=0; i<=level; i++) {
				if (i <= level) {
					System.out.print(Integer.toString(i+1)+ ". " + this.loggedIn.getGrade()[i]);
				}
			}
			
			boolean choice = true;
			while(choice) {
				boolean flag = false;
				System.out.println(); // 뷰를 위한 처리
				System.out.println("매칭 선택> ");
				int selector = inputInteger();
				selector -= 1;
				
				Human another = manage.searchMatch(selector, sex);
				System.out.print("선택하시겠습니까(Y/N): ");
				choice = !inputChoice();
				
				if (choice) {
					System.out.print("더 검색하시겠습니까(Y/N): ");
					choice = inputChoice();
				} else {
					flag = manage.match(loggedIn, another);
				}
				
				if (flag) {
					System.out.println("매칭신청을 보냈습니다.");
				} else {
					System.out.println("매칭에 실패했습니다.");
				}
			}
		}
	}
	
	/**
	 * 상대 매칭 확인 및 수락하는 메소드
	 */
	public void accept() {
			
	}
	
	/**
	 * int 타입의 데이터를 넣기 위해서 사용하는 메소드
	 * 예외처리 기능을 포함하였다.
	 * @return 정수형 반환
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
	 * double 타입의 데이터를 넣기 위해서 사용하는 메소드
	 * 예외처리 기능을 포함하였다.
	 * @return 실수형 반환
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
	 * String 타입의 데이터를 넣기 위해서 사용하는 메소드
	 * 예외처리 기능을 포함하였다.
	 * @return 문자열
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
	 * 성별을 넣기 위해 사용하는 메소드 String값을 boolean으로 변환.
	 * 사용자 편의성을 위해서 추가함.
	 * @return 남성이면 true 여성이면 false
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
	 * Y/N 선택
	 * @return yes이면 true, no이면 false
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
