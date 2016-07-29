package com.smluv82.file2compare.domain;

public class Compare {
	private int fileNo;
	private String fileName;
	private String fullPathFileName;
	private String fileHash;
	private String compareResult;

	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFullPathFileName() {
		return fullPathFileName;
	}
	public void setFullPathFileName(String fullPathFileName) {
		this.fullPathFileName = fullPathFileName;
	}
	public String getFileHash() {
		return fileHash;
	}
	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}
	public String getCompareResult() {
		return compareResult;
	}
	public void setCompareResult(String compareResult) {
		this.compareResult = compareResult;
	}

	@Override
	public String toString() {
		return "Compare [fileNo=" + fileNo + ", fileName=" + fileName + ", fullPathFileName=" + fullPathFileName
				+ ", fileHash=" + fileHash + ", compareResult=" + compareResult + "]";
	}
}
