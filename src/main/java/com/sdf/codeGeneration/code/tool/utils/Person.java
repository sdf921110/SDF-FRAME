package com.sdf.codeGeneration.code.tool.utils;

public class Person {
	public Integer id;
	public String name;
	public boolean isMen;
	public Character ch;
	public Float float_;
	public Double double_;
	public Long long_;
	public Short short_;
	public Byte byte_;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsMen() {
		return isMen;
	}

	public void setIsMen(boolean isMen) {
		this.isMen = isMen;
	}

	public Character getCh() {
		return ch;
	}

	public void setCh(Character ch) {
		this.ch = ch;
	}

	public Float getFloat_() {
		return float_;
	}

	public void setFloat_(Float float_) {
		this.float_ = float_;
	}

	public Double getDouble_() {
		return double_;
	}

	public void setDouble_(Double double_) {
		this.double_ = double_;
	}

	public Long getLong_() {
		return long_;
	}

	public void setLong_(Long long_) {
		this.long_ = long_;
	}

	public Short getShort_() {
		return short_;
	}

	public void setShort_(Short short_) {
		this.short_ = short_;
	}

	public Byte getByte_() {
		return byte_;
	}

	public void setByte_(Byte byte_) {
		this.byte_ = byte_;
	}

	public Person(Integer id, String name, Boolean isMen, Character ch, Float float_, Double double_, Long long_,
			Short short_, Byte byte_) {
		super();
		this.id = id;
		this.name = name;
		this.isMen = isMen;
		this.ch = ch;
		this.float_ = float_;
		this.double_ = double_;
		this.long_ = long_;
		this.short_ = short_;
		this.byte_ = byte_;
	}

	public Person() {
		super();
	}
}
