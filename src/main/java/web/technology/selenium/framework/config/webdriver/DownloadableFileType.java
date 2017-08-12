package web.technology.selenium.framework.config.webdriver;

enum DownloadableFileType {

	GZ("gz"),
	BZ2("bz2"),
	ZIP("zip"),
	TAR("tar"),
	EXE("exe");

	private final String downloadableFileFileExtension;

	DownloadableFileType(String downloadableFileFileExtension) {
		this.downloadableFileFileExtension = downloadableFileFileExtension;
	}
}
