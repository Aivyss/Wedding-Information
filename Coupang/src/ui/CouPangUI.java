package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import bucket.Bucket;
import bucket.BucketManage;
import logisticsWarehouse.OnePosition;
import logisticsWarehouse.Warehouse;
import vo.Product;
import vo.ProductManage;

public class CouPangUI {
	BucketManage bManage;
	ProductManage pManage;
	Warehouse warehouse;
	Scanner sc = new Scanner(System.in);

	/**
	 * UI생성자 오버로딩
	 * 
	 * @param localCode
	 */
	public CouPangUI(String localCode) {
		this.bManage = new BucketManage();
		this.pManage = new ProductManage();
		this.warehouse = new Warehouse(localCode);

		while (true) {
			try {
				printMenu();
				int selector = inputInteger();

				if (selector == 1) {
					InputProductInfoUI();
				} else if (selector == 2) {
					waitInBoundUI();
				} else if (selector == 3) {
					inBoundUI();
				} else if (selector == 4) {
					outBoundUI();
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
		System.out.println("1.물품 정보 입력");
		System.out.println("2.입고 대기 처리");
		System.out.println("3.입고 처리");
		System.out.println("4.출고 처리");
		System.out.print("메뉴 선택 > ");
	}

	/**
	 * 물품의 정보를 입력하는 메소드.
	 * 
	 * @throws InputMismatchException
	 */
	public void InputProductInfoUI() throws InputMismatchException {
		System.out.println("=====[물품 정보 입력]=====");
		List<Object> productInfo = new ArrayList<>();
		System.out.print("물품의 바코드를 입력하세요: ");
		productInfo.add(inputString());

		if (pManage.pMap.get(productInfo.get(0)) != null) {
			System.out.println("[에러] 이미 존재하는 정보입니다.");
		} else {
			System.out.print("물품의 이름을 입력하세요: ");
			productInfo.add(inputString());
			System.out.print("물품의 가격을 입력하세요: ");
			productInfo.add(inputInteger());
			Product vo = new Product(productInfo);
			pManage.pMap.put(vo.getBarCode(), vo);
		}
	}

	/**
	 * 입고대기를 수행하는 메소드. 입고시킬 수량을 사용자로부터 입력받고 입고를 수행한다.
	 */
	public void waitInBoundUI() {
		System.out.println("=====[입고 대기]=====");
		System.out.print("입고대기시킬 물품의 바코드를 찍으세요: ");
		String productCode = inputString();
		Product vo = pManage.searchProduct(productCode);

		if (vo != null) {
			System.out.print("입고 수량을 입력하세요: ");
			int count = inputInteger();
			if (vo.getCount() <= 0) {
				vo.setCount(count);
			} else {
				vo.setCount(vo.getCount() + count);
			}
		} else {
			System.out.println("[에러] 물품 정보가 입력되어 있지 않으니 물품 정보부터 입력하세요.");
		}
	}

	/**
	 * 입고 프로세스를 수행하는 메소드
	 */
	public void inBoundUI() {
		System.out.println("=====[입고 프로세스]=====");
		System.out.print("물품의 바코드를 입력하세요: ");
		String productCode = inputString();

		Product vo = pManage.pMap.get(productCode);
		if (vo != null && vo.getCount() != 0) {
			int maxCount = 15;
			int storageCount = pManage.pMap.get(productCode).getCount();

			System.out.print("원하는 입고 수량을 결정하세요(최대" + maxCount + "): ");
			int count = inputInteger();

			if (count > maxCount) {
				System.out.println("[에러] 최대 값을 초과했습니다.");
			} else {
				if (count < storageCount) {
					pManage.updateCount(productCode, count);
					vo.setCount(storageCount - count);
					pManage.pMap.put(productCode, vo);
					System.out.print("위치 바코드 값을 입력하세요: ");
					String placeCode = inputString();
					boolean flag = warehouse.inBound(placeCode, productCode, count);

					if (flag) {
						Product tmp = pManage.pMap.get(productCode);
						tmp.setCount(tmp.getCount() - count);
						pManage.pMap.put(productCode, tmp);
						System.out.println("입고처리가 되었습니다.");
					} else {
						System.out.println("[에러] 입고할 공간이 없습니다.");
					}
				} else {
					System.out.println("[에러] 입고 대기 양을 초과했습니다(최대양:" + pManage.pMap.get(productCode).getCount() + "개)입니다.");
				}
				
			}
		} else {
			System.out.println("[에러] 대기중인 물품이 없습니다.");
		}
	}

	public void outBoundUI() {
		List<String> productCodes = new ArrayList<>();
		List<Integer> counts = new ArrayList<>();

		while (true) {
			System.out.print("물품의 바코드를 입력하세요: ");
			productCodes.add(inputString());
			System.out.print("물품의 수량을 입력하세요: ");
			counts.add(inputInteger());
			System.out.print("더 입력하시겠습니까? (Y/N): ");
			boolean flag = inputBoolean();

			if (!flag) {
				break;
			}
		}

		Map<String, Integer> box = warehouse.outBound(productCodes, counts);
		for (String bucketCode : bManage.bMap.keySet()) {
			if (!bManage.bMap.get(bucketCode).lock) {
				Bucket tmp = new Bucket(bucketCode);
				tmp.box = box;
				bManage.bMap.put(bucketCode, tmp);
				break;
			}
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

	public boolean inputBoolean() {
		boolean flag;

		try {
			String str = sc.nextLine();

			if (str.equals("Y")) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (InputMismatchException e) {
			throw e;
		}

		return flag;
	}
}
