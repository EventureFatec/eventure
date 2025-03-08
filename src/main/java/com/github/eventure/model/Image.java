package com.github.eventure.model;

public class Image {
  
	private String path;
	private byte [] image;
	
	public void setPath(String path)
	{
		this.path = path;
	}
	public String getPath()
	{
		return this.path;
	}
	public void setImage(byte [] image)
	{
		this.image = image;
	}
	public byte [] getImage()
	{
		return this.image;
	}
}
