package com.example.demo.model;

public class TreeNode {
private int id;
private String  name;
private String level;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getLevel() {
	return level;
}
public void setLevel(String level) {
	this.level = level;
}
public TreeNode(int id, String name, String level) {
	super();
	this.id = id;
	this.name = name;
	this.level = level;
}
@Override
public String toString() {
	return "TreeNode [id=" + id + ", name=" + name + ", level=" + level + "]";
}

}
