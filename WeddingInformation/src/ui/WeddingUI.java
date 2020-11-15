package ui;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import manage.HumanInfo;
import vo.Female;
import vo.Human;
import vo.Male;

public class WeddingUI {
	
	
	// WeddingUI 객체
	private static final WeddingUI ui = new WeddingUI();
	// UI 클래스의 멤버변수
	private final Scanner sc;
	private final HumanInfo manage;
	private Human loggedIn;
	
	public static WeddingUI getInstance() {
		return ui;
	}

	/**
	 * UI 객체 생성자. ui기능의 전반을 담당한다.
	 */
	private WeddingUI() {
		sc = new Scanner(System.in);
		manage = HumanInfo.getInstance();
		this.loggedIn = null;
		while (true) {
			try {
				if (this.loggedIn == null) { // 로그인 전
					System.out.println("================================");
					menu1();
					int selector = inputInteger();
					System.out.println("================================");

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
					while (true) {
						System.out.println("================================");
						menu2();
						int selector = inputInteger();
						System.out.println("================================");

						if (selector == 1) {
							deposit();
						} else if (selector == 2) {
							match();
						} else if (selector == 3) {
							accept();
						} else if (selector == 4) {
							seeStatus();
						} else if (selector == 5) {
							showMyInfo();
						} else if (selector == 6) {
							initializeMatch();
						} else if (selector == 7) {
							removeAccount();
							
							if (loggedIn == null) { // 회원 탈퇴가 완료 되었으면 로그인 전단계로 이동하는 조건문
								break;
							}
						} else if (selector == 0) {
							backToFirst();
							break;
						} else {
							System.out.println("[에러] 잘못 입력하셨습니다.");
						}
					}
				}
			} catch (InputMismatchException e) {
				e = new InputMismatchException("[에러] 잘못 입력하셨습니다.");
				System.out.println(e.getMessage());
			}
		} // while end
	}

	/**
	 * 로그인 전의 메뉴화면을 보여주는 메소드
	 */
	private void menu1() {
		System.out.println("[회원가입/로그인]");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 프로그램 종료");
		System.out.print("메뉴선택> ");
	}

	/**
	 * 일반회원의 로그인 후 화면을 보여주는 메소드
	 */
	private void menu2() {
		System.out.println("[" + loggedIn.getName() + " 회원님 환영합니다.]");
		System.out.println("1. 캐시 충전하기");
		System.out.println("2. 상대 매칭하기");
		System.out.println("3. 상대 매칭 수락하기");
		System.out.println("4. 매칭 성사 현황");
		System.out.println("5. 나의 정보 현황 확인");
		System.out.println("6. 매칭 초기화");
		System.out.println("7. 회원 탈퇴");
		System.out.println("0. 로그인 전단계로 이동");
		System.out.print("메뉴선택> ");
	}

	/**
	 * 로그인을 수행하는 메소드
	 */
	private void signIn() {
		System.out.print("아이디를 입력하세요: ");
		String id = sc.nextLine();
		System.out.print("비밀번호를 입력하세요: ");
		String pw = sc.nextLine();
		this.loggedIn = manage.signIn(id, pw);

		if (this.loggedIn != null) {
			System.out.println("[알림] 로그인 되었습니다.");
		} else {
			System.out.println("[알림] 로그인에 실패했습니다.");
			if (manage.searchAccount(id) != null) {
				if (manage.searchAccount(id).getLockCount()>=3) {
					System.out.println("[알림] 3회 이상 실패하여 계정이 잠겼습니다.");
				}
			}
		}	
	}

	/**
	 * 회원가입을 수행하는 메소드.
	 */
	private void signUp() {
		Human vo = null;
		boolean sex;
		HashMap<String, Object> info = new HashMap<>();
		
		System.out.print("이용할 아이디 입력: ");
		String id = sc.nextLine();
		vo = manage.searchAccount(id);
		
		if (vo == null) {
			info.put("id", id);
			System.out.print("비밀번호 입력: ");
			info.put("password", sc.nextLine());
			System.out.print("이름 입력: ");
			info.put("name", sc.nextLine());
			System.out.print("성별 입력(M/F): ");
			sex = inputSex();
			info.put("sex", sex);
			System.out.print("나이 입력: ");
			info.put("age", inputInteger());
			System.out.print("키 입력: ");
			info.put("height", inputInteger());
			System.out.print("BMI 입력: ");
			info.put("bmi", inputDouble());
			System.out.print("최종대학 입력: ");
			info.put("latestEdu", sc.nextLine());
			System.out.print("최종학력 분류 1. 아이비리그, 2. 설대, 카이스트, 포공, 3. 연고성 해외대학, 4. 인서울,  5. 인경기,  6.지거국, 7. 지방대: ");
			info.put("latestEduIndex", inputInteger());
			System.out.print("연봉 입력: ");
			info.put("salary", inputInteger());

			boolean flag = false;
			if (sex) { // 성별이 남성인 경우
				System.out.print("탈모 여부(Y/N): ");
				info.put("taco", inputChoice());

				vo = new Male(info);
				flag = manage.addAccount(vo);
			} else { // 성별이 여성인 경우
				System.out.print("시술/성형 여부(1. 얼굴전체성형, 2.양악, 3.단순시술, 4.해당없음): ");
				info.put("surgery", inputInteger());

				vo = new Female(info);
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
	 * 캐시를 충전하는 메소드
	 */
	private void deposit() {
		System.out.print("본인 확인을 위한 비밀번호를 입력하세요: ");
		String pw = sc.nextLine();

		if (pw.equals(loggedIn.getPassword())) {
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
	private void match() {
		int gradeIndex = this.loggedIn.getGradeIndex();
		String grade = this.loggedIn.getGrade();
		int cash = this.loggedIn.getCash();

		if (loggedIn.getMatchLock()==1) {
			System.out.println("[에러] 매칭수락 대기중이거나 수락 여부를 판단할 대상이 있습니다.");
		} else {
			boolean choice = true;

			while (choice) {
				boolean flag = false;
				
				System.out.println("당신의 등급: " + grade + ", 충전금: " + cash + "원");
				System.out.println("[매칭등급 선택(회원님보다 높은 등급은 고를 수 없습니다.)]");
				System.out.println("6. 비브라늄 5. 다이아 4. 플래티넘 3. 골드 2. 실버 1. 브론즈 0. 언랭");
				System.out.print("매칭등급 선택> ");
				int selector = inputInteger();

				if (gradeIndex >= selector) { // 0 > 6
					Human another = manage.searchMatch(selector, this.loggedIn);

					if (another == null) {
						System.out.println("[에러] 등급에 인원이 없습니다.");
						break;
					}
					System.out.println(another.toString()); // 상대 정보 출력
					System.out.print("선택하시겠습니까(Y/N)> ");
					choice = !inputChoice();

					if (choice) {
						System.out.print("더 검색하시겠습니까(Y/N)> ");
						choice = inputChoice();
						
						if (choice) { // 깔끔한 뷰를 위해 추가한 조건문
							System.out.println("================================");
						}
					} else {
						flag = manage.match(loggedIn.getId(), another.getId());
						
						if (flag) {
							System.out.println("매칭신청을 보냈습니다.");
						} else {
							System.out.println("매칭에 실패했습니다.");
						}
					}
				} else {
					System.out.println("[에러] 선택할 수 없는 등급입니다.");
					break;
				}
			}
		}
	}

	/**
	 * 상대 매칭 확인 및 수락하는 메소드
	 */
	private void accept() {
		if (loggedIn.getInvited()==1 && loggedIn.getSuccess()!=1) {
			String matchedId = this.loggedIn.getMatchedId();
			Human vo = manage.searchAccount(matchedId); // 상대의 정보(객체)

			System.out.println("[매칭신청을 보낸 상대의 정보입니다]");
			System.out.println(vo.toString());
			System.out.print("매칭 수락(Y/N): ");
			boolean flag = inputChoice();

			flag = manage.accept(loggedIn.getId(), matchedId, flag);

			if (flag) {
				System.out.println("[알림] 매칭을 수락하였습니다.");
			} else {
				System.out.println("[알림] 매칭을 거부하였거나 상대가 돈이 없습니다.");
			}
		} else {
			System.out.println("[에러] 매칭 내역이 없습니다.");
		}
	}

	/**
	 * 매칭 성사 현황을 보여주는 메소드
	 */
	private void seeStatus() {
		if (loggedIn.getSuccess()==1) {
			Human vo = manage.searchAccount(loggedIn.getMatchedId());

			System.out.println("[알림] 매칭이 성사되었습니다.");
			System.out.println(vo.toString());
		} else {
			System.out.println("[알림] 매칭 성사된 내역이 없습니다.");
		}
	}
	
	/**
	 * 내 정보를 보여주는 메소드
	 */
	private void showMyInfo() {
		System.out.println("[내 정보 현황]");
		System.out.println(loggedIn.toString());
	}
	
	/**
	 * 매칭 정보를 초기화하는 메소드. 다시 매칭을 할 수 있도록 해준다.
	 */
	private void initializeMatch() {
		if (manage.initialize(loggedIn.getId())) {
			System.out.println("[알림] 매칭정보가 초기화 되었습니다. 새로운 만남을 찾으세요!");
		} else {
			System.out.println("[알림] 매칭정보가 없는 듯 합니다.");
		}
	}
	
	/**
	 * 회원 탈퇴를 진행하는 메소드
	 */
	private void removeAccount() {
		System.out.print("비밀번호 재입력> ");
		if(manage.removeAccount(loggedIn.getId(),sc.nextLine())) {
			System.out.println("[알림] 회원 탈퇴를 완료했습니다.");
			backToFirst();
		} else {
			System.out.println("[에러] 회원 탈퇴에 실패했습니다(비밀번호 불일치). ");
		}
	}
	
	private void backToFirst() {
		System.out.println("[알림] 로그인 전단계로 이동합니다.");
		this.loggedIn = null;
	}

	/**
	 * int 타입의 데이터를 넣기 위해서 사용하는 메소드 예외처리 기능을 포함하였다.
	 * 
	 * @return 정수형 반환
	 */
	private int inputInteger() {
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
	 * double 타입의 데이터를 넣기 위해서 사용하는 메소드 예외처리 기능을 포함하였다.
	 * 
	 * @return 실수형 반환
	 */
	private double inputDouble() {
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
	 * 성별을 넣기 위해 사용하는 메소드 String값을 boolean으로 변환. 사용자 편의성을 위해서 추가함.
	 * 
	 * @return 남성이면 true 여성이면 false
	 */
	private boolean inputSex() {
		String str;
		boolean sex;

		str = sc.nextLine();

		if (str.toUpperCase().equals("M")) {
			sex = true;
		} else if (str.toUpperCase().equals("F")) {
			sex = false;
		} else {
			throw new InputMismatchException();
		}

		return sex;
	}

	/**
	 * Y/N 선택
	 * 
	 * @return yes이면 true, no이면 false
	 */
	private boolean inputChoice() {
		String str;
		boolean choice;

		str = sc.nextLine();

		if (str.toUpperCase().equals("Y")) {
			choice = true;
		} else if (str.toUpperCase().equals("N")) {
			choice = false;
		} else {
			throw new InputMismatchException();
		}

		return choice;
	}
}
