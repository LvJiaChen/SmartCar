package com.example.qr_codescan.public_;

public class FavoratePojo {
	private int id;
	private String favorate_info;
	private double favorate_price;
	private double price;
	private String image;
	private int x;
	private int y;

	public FavoratePojo(int id, String favorate_info, double favorate_price,
			double price, String image, int x, int y) {
		super();
		this.id = id;
		this.favorate_info = favorate_info;
		this.favorate_price = favorate_price;
		this.price = price;
		this.image = image;
		this.x = x;
		this.y = y;
	}

	public FavoratePojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFavorate_info() {
		return favorate_info;
	}

	public void setFavorate_info(String favorate_info) {
		this.favorate_info = favorate_info;
	}

	public double getFavorate_price() {
		return favorate_price;
	}

	public void setFavorate_price(double favorate_price) {
		this.favorate_price = favorate_price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "FavoratePojo [id=" + id + ", favorate_info=" + favorate_info
				+ ", favorate_price=" + favorate_price + ", price=" + price
				+ ", image=" + image + ", x=" + x + ", y=" + y + "]";
	}

}
