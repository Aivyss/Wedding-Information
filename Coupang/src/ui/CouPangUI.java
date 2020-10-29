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
	
	public void printMenu() {
		System.out.println("1.물품 입고 대기처리");
		System.out.println("2.토트로 물품 옮기기(입고과정1)");
		System.out.print("메뉴 선택 > ");
	}
	
	public void moveToBucket() {
		List<Bucket<String>> BucketList = bManage.getManage();
		int index = 0;
		boolean flag = true;
		
		if (flag) {
			System.out.print("넣을 물건의 바코드를 찍으세요 : ");
			String productBarCode = inputString();
			Product vo = pManage.pManage.get(productBarCode);
			
			if (vo != null) {
				int count = 0;
				while (true) {
					System.out.print("넣을 물품의 갯수를 입력하시오.");
					int count = inputInteger();					
				}
				for (Bucket<String> bucket : BucketList) {
					if (bucket.storageCount == 0) {
						int restCount = bucket.STORAGE-bucket.storageCount;
						
						
					}
				}
				if (count > vo.count) {
					System.out.println("넣을 수 있는 최대 물건의 갯수를 초과했습니다.");
				} else {
					vo.count -= count;

					
				}
			}
		} else {
			System.out.println("토트가 부족합니다 잠시 휴식하세요.");
		}
		
	}
	
	public void bucketToWarehouse() {
		
	}
	
	public void InputProductInfo() {
		List<Object> productInfo = new ArrayList<>();
		
		System.out.print("제품 바코드를 찍으세요: ");
		productInfo.add(inputString());
		System.out.print("제품명 입력: ");
		productInfo.add(inputString());
		System.out.print("가격 입력: ");
		productInfo.add(inputInteger());
		System.out.print("입고 대기중인 제품의 갯수: ");
		productInfo.add(inputInteger());
		
		Product vo = new Product(productInfo);
		boolean flag = pManage.addProduct(vo);
		
		if (flag) {
			System.out.println("대기열에 추가되었습니다.");
		} else {
			System.out.println("이미 추가된 대기열에 수량을 늘렸습니다.");
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
