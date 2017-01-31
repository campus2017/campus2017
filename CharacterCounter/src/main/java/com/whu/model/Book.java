package com.whu.model;

public class Book {
	private int id;
	private String author;
	private String name;
	
	public Book(){}
	
	public Book(int id, String author, String name){
		super();
		this.id = id;
		this.author = author;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
