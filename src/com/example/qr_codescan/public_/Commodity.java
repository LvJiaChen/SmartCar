package com.example.qr_codescan.public_;

public class Commodity {
	private int X;
	private int Y;
	private String trade_name;
	private String area;
	private double price;
	private String category;
	private String imagePath;
	
	
	public Commodity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Commodity(int x, int y, String trade_name, String area,
			double price, String category, String imagePath) {
		super();
		X = x;
		Y = y;
		this.trade_name = trade_name;
		this.area = area;
		this.price = price;
		this.category = category;
		this.imagePath = imagePath;
	}


	public int getX() {
		return X;
	}


	public void setX(int x) {
		X = x;
	}


	public int getY() {
		return Y;
	}


	public void setY(int y) {
		Y = y;
	}


	public String getTrade_name() {
		return trade_name;
	}


	public void setTrade_name(String trade_name) {
		this.trade_name = trade_name;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	@Override
	public String toString() {
		return "Commodity [X=" + X + ", Y=" + Y + ", trade_name=" + trade_name
				+ ", area=" + area + ", price=" + price + ", category="
				+ category + ", imagePath=" + imagePath + "]";
	}
	
	
}
