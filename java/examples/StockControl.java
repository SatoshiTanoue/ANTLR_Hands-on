import java.util.ArrayList;

public class StockControl {
	private static ArrayList<Item> ItemList;
	private int ItemID;

	public StockControl() {
		ItemList = new ArrayList<Item>();
		ItemID = 0;
	}

	public void set(String name, int weight, int price) {
		Item item = new Item(ItemID, name, weight, price);
		ItemList.add(item);
		ItemID++;

		System.out.println("在庫" + name + "を登録しました");

		return;
	}

	public void addStock(String name, int weight) {
		Item item = null;
		item = searchItem(name);

		if (item == null) {
			System.out.println("該当する在庫が存在しません。");
			return;
		}

		item.add(weight);
		System.out.println(name + "を" + weight + "kg追加しました。");
	}

	public Item searchItem(String SearchName) {
		int i = 0;
		Item item = null;
		String name = "";
		Item result = null;

		while (i < ItemList.size()) {
			item = ItemList.get(i);
			name = item.getName();

			if (name.equals(SearchName)) {
				result = item;
			}
			i++;
		}

		return result;
	}

	public void searchInfo(int id) {
		Item item;
		try {
			item = ItemList.get(id);
			item.print();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("該当する在庫が存在しません。");
		}
		return;
	}

	public void calculateUnitPrice() {
		Item item;
		int weight, totalPrice = 0, unitPrice;
		String name;
		try {
			for (int i = 0; i < ItemList.size(); i++) {
				item = ItemList.get(i);
				name = item.getName();
				weight = item.getWeight();
				totalPrice = item.getTotalPrice();
				unitPrice = totalPrice / weight;
				System.out.println(name + ":" + unitPrice);
			}
		} catch (ArithmeticException e) {
			System.out.println("在庫なし");

		}
		return;
	}

}