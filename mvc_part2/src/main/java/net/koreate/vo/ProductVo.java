package net.koreate.vo;

public class ProductVo {

	private String name;
	private int price;

	public ProductVo() {
		super();
	}

	public ProductVo(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "ProductVo [ name=" + this.name + " price = " + this.price + " ] ";
	}
}
