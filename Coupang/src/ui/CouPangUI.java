package ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import bucket.Bucket;
import bucket.BucketManage;
import logisticsWarehouse.Warehouse;
import vo.Product;
import vo.ProductManage;

public class CouPangUI {
	Warehouse wh;
	BucketManage bManage;
	ProductManage pManage;
	Scanner sc = new Scanner(System.in);

	/**
	 * UI생성자 오버로딩
	 * 
	 * @param localCode
	 */
	public CouPangUI(String localCode) {
		this.wh = new Warehouse(localCode);
		this.bManage = new BucketManage();
		this.pManage = new ProductManage();

		while (true) {
			try {
				printMenu();
				int selector = inputInteger();

				if (selector == 1) {
					InputProductInfo();
				} else if (selector == 2) {
					moveToBucket();
				}
			} catch (InputMismatchException e) {
				e = new InputMismatchException("잘못 입력하셨습니다.");
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * 주-메뉴부를 출력하는 메소드
	 */
	public void printMenu() {
		System.out.println("1.물품 입고 대기처리");
		System.out.println("2.토트로 물품 옮기기(입고과정1)");
		System.out.print("메뉴 선택 > ");
	}

	/**
	 * 입고과정 1을 수행하는 메소드.
	 */
	public void moveToBucket() {
		System.out.print("입고할 물품의 바코드를 찍으세요 : ");
		String productBarCode = inputString();

		if (pManage.pMap.get(productBarCode) != null) {
			int productCount = pManage.pMap.get(productBarCode);
			int count = 0;

			if (productCount == 0) {
				System.out.println("입고 대기수량이 0입니다.");
			} else {
				System.out.print("넣을 물품의 갯수를 입력하시오.");
				count = inputInteger();

				if (count > productCount) {
					System.out.println("입고대기중인 수량보다 많습니다.");
				} else {
					int index = 0;
					for (Bucket<String> bucket : bManage.getbList()) {
						if (!bucket.lock) {
							bucket.add(productBarCode, count);
							bManage.bList.set(index, bucket); // 토트에 담음
							if (productCount - count == 0) { // 입고대기 수량 조정
								pManage.pMap.remove(productBarCode);
							} else {
								pManage.pMap.put(productBarCode, productCount - count);
							}
							System.out.println("토트에 물건을 옮겼습니다.");
							System.out.println("토트 바코드 번호: " + bucket.getBucketBarCode());

							break;
						}

						index++;
					}
				}
			}

		} else {
			System.out.println("해당 물품이 없습니다.");
		}
	}

	/**
	 * 입고과정 2를 진행하는 메소드
	 */
	public void bucketToWarehouse() {
		Bucket<String> select = null;
		System.out.print("토트 바코드를 찍으세요: ");
		String bucketCode = inputString();
		
		// 매니저 객체로부터 토트 탐색
		int index = 0;
		for (Bucket<String> bucket : bManage.bList) {
			if (bucketCode.equals(bucket.getBucketBarCode())){
				select = bucket;
				break;
			}
			
			index++;
		}
		if (select != null) {
			if (select.lock) {
				System.out.print("입고할 위치 바코드를 찍으세요 : ");
				String placeCode = inputString();
				bManage.bList.set(index, select);
				// 이부분 완료 해야함.
			} else {
				System.out.println("토트가 비어 있습니다.");
			}
		} else {
			System.out.println("바코드를 다시 찍으세요.");
		}
	}

	public void InputProductInfo() {
		List<Object> productInfo = new ArrayList<>();

		System.out.print("제품 바코드를 찍으세요: ");
		productInfo.add(inputString());
		System.out.print("제품명 입력: ");
		productInfo.add(inputString());
		System.out.print("가격 입력: ");
		productInfo.add(inputInteger());
		System.out.print("입고할 제품의 갯수: ");
		int productCount = inputInteger();

		Product product = new Product(productInfo);
		boolean flag = pManage.addProduct(product, productCount);

		if (flag) {
			System.out.println("대기열에 추가되었습니다.");
		} else {
			System.out.println("이미 추가된 대기열의 수량을 늘렸습니다.");
		}
	}

	public int inputInteger() {
		int input = 0;

		try {
			input = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			sc.nextLine();
			throw e;
		}

		return input;
	}

	public String inputString() {
		String input = null;

		try {
			input = sc.nextLine();
		} catch (InputMismatchException e) {
			throw e;
		}

		return input;
	}

	public void printWaitInBound() {

	}
}
