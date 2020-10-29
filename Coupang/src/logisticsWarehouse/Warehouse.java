package logisticsWarehouse;

public class Warehouse {
	private String localCode;
	private final int FLOOR = 3;
	private final int POSITION = 150;
	private OnePosition[][] warehouse = new OnePosition[FLOOR][POSITION];
	
	/**
	 * 기본 생성자
	 */
	public Warehouse() {
		
	}
	/**
	 * 특수 생성자
	 * @param localCode
	 */
	public Warehouse(String localCode) {
		this.localCode = localCode;
		String placeBarCode = "";
		
		for (int i=1; i<=FLOOR; i++) {
			for (int j=1; j<=POSITION; j++) {
				if (j>=100) {
					placeBarCode = localCode + i + j;
				} else if (j>=10) {
					placeBarCode = localCode + i + "0" + j;
				} else {
					placeBarCode = localCode + i + "00" + j;
				}
				
				warehouse[i][j] = new OnePosition(placeBarCode);
			}
		}
	}
	
	public void display() {
		int count = 0;

		for (int i=0; i<FLOOR; i++) {
			System.out.println(i + "층 현황");
			for (int j=0; j<POSITION; j++) {
				if (count < 10) {
					System.out.print(warehouse[i][j].getStorageCount() + "\t");
					count++;
				} else {
					System.out.println();
					count = 0;
				}
			}
		}
	} // display method end
}
