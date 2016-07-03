package com.example.picture_test_jsoup;

public class Picture {
	private String path;
	private String width;
	private String height;
	
	public Picture() {
		super();
	}

	public Picture(String path, String width, String height) {
		super();
		this.path = path;
		this.width = width;
		this.height = height;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

}
