package web.technology.selenium.framework.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;

import org.apache.commons.io.FilenameUtils;

public class CompressedFile {

	private File compressedFile;
	private String decompressedFilename;
	private DownloadableFileType filetype = null;

	public CompressedFile(File downloadedCompressedFile) throws Exception {
		filetype = DownloadableFileType.valueOf(FilenameUtils.getExtension(compressedFile.getName()).toUpperCase());
		if (filetype != DownloadableFileType.GZ && filetype != DownloadableFileType.BZ2) {
			throw new Exception(compressedFile.getName() + " is an archive, not a known compressed file type");
		}
		this.compressedFile = compressedFile;
		decompressedFilename = FilenameUtils.getBaseName(compressedFile.getName());
	}

	 DownloadableFileType getArchiveType() {
	        try {
	            return DownloadableFileType.valueOf(FilenameUtils.getExtension(decompressedFilename).toUpperCase());
	        } catch (IllegalArgumentException e) {
	            return null;
	        }
	    }

	public InputStream getInputStream() throws FileNotFoundException, IOException {
		switch (filetype) {
		case GZ:
			return new GzipCompressorInputStream(new FileInputStream(compressedFile));
		case BZ2:

			return new BZip2CompressorInputStream(new FileInputStream(compressedFile));
		}
		return null;
	}

	String getDecompressedFilename() {
        return decompressedFilename;
    }

}
