package ui;

import logisticsWarehouse.Warehouse;

public class CouPangUI {
	Warehouse wh;

	public CouPangUI(String localCode) {
		this.wh = new Warehouse(localCode);
	}
}
