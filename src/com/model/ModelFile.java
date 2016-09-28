package com.model;

import java.io.Serializable;

public class ModelFile implements Serializable{
	private static final long serialVersionUID = 1L;
	private byte[] byteFile;
	private String namaFile;
	private String contentType;

	public ModelFile(){}

	public ModelFile(byte[] byteFile, String namaFile, String contentType) {
		this.byteFile = byteFile;
		this.namaFile = namaFile;
		this.contentType = contentType;
	}

	public byte[] getByteFile() {
		return byteFile;
	}

	public void setByteFile(byte[] byteFile) {
		this.byteFile = byteFile;
	}

	public String getNamaFile() {
		return namaFile;
	}

	public void setNamaFile(String namaFile) {
		this.namaFile = namaFile;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	
	
}
