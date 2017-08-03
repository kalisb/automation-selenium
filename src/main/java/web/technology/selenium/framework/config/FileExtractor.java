package web.technology.selenium.framework.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import org.apache.commons.compress.archivers.zip.ZipFile;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class FileExtractor {

	private final boolean overwriteFilesThatExist;

	public FileExtractor(boolean overwriteFilesThatExist) {
		this.overwriteFilesThatExist = overwriteFilesThatExist;
	}

	public String extractFileFromArchive(File downloadedCompressedFile, String extractedToFilePath,
			BinaryType possibleFilenames) throws Exception {
		DownloadableFileType fileType = DownloadableFileType.valueOf(FilenameUtils.getExtension(downloadedCompressedFile.getName()).toUpperCase());
		switch (fileType) {
		case GZ:
		case BZ2:
			CompressedFile compressedFile = new CompressedFile(downloadedCompressedFile);
			if (null != compressedFile.getArchiveType() && compressedFile.getArchiveType().equals(DownloadableFileType.TAR)) {
				return untarFile(compressedFile.getInputStream(), extractedToFilePath, possibleFilenames);
			} else {
				return copyFileToDisk(compressedFile.getInputStream(), extractedToFilePath, compressedFile.getDecompressedFilename());
			}
		case ZIP:
			return unzipFile(downloadedCompressedFile, extractedToFilePath, possibleFilenames);
		case EXE:
			if(possibleFilenames.getBinaryFilenames().contains(downloadedCompressedFile.getName())) {
				return copyFileToDisk(new FileInputStream(downloadedCompressedFile), extractedToFilePath, downloadedCompressedFile.getName());
			}
		default:
			throw new IllegalArgumentException("." + fileType + " is an unsupported archive type");
		}
	}
	
	 String unzipFile(File downloadedCompressedFile, String extractedToFilePath, BinaryType possibleFilenames) throws Exception {
	        ArrayList<String> filenamesWeAreSearchingFor = possibleFilenames.getBinaryFilenames();
	        ZipFile zip = new ZipFile(downloadedCompressedFile);
	        try {
	            Enumeration<ZipArchiveEntry> zipFile = zip.getEntries();
	            while (zipFile.hasMoreElements()) {
	                ZipArchiveEntry zipFileEntry = zipFile.nextElement();
	                for (String aFilenameWeAreSearchingFor : filenamesWeAreSearchingFor) {
	                    if (zipFileEntry.getName().endsWith(aFilenameWeAreSearchingFor)) {
	                        return copyFileToDisk(zip.getInputStream(zipFileEntry), extractedToFilePath, aFilenameWeAreSearchingFor);
	                    }
	                }
	            }
	        } finally {
	            zip.close();
	        }

	        throw new Exception("Unable to find any expected files for " + possibleFilenames.getBinaryTypeAsString());
	    }

	private String untarFile(InputStream compressedFileInputStream, String extractedToFilePath, BinaryType possibleFilenames) throws Exception {
		ArchiveEntry currentFile;
        ArrayList<String> filenamesWeAreSearchingFor = possibleFilenames.getBinaryFilenames();
        ArchiveInputStream archiveInputStream = new TarArchiveInputStream(compressedFileInputStream);
        try {
            while ((currentFile = archiveInputStream.getNextEntry()) != null) {
                for (String aFilenameWeAreSearchingFor : filenamesWeAreSearchingFor) {
                    if (currentFile.getName().endsWith(aFilenameWeAreSearchingFor)) {
                        return copyFileToDisk(archiveInputStream, extractedToFilePath, aFilenameWeAreSearchingFor);
                    }
                }
            }
        } finally {
            compressedFileInputStream.close();
        }

        throw new Exception("Unable to find any expected filed for " + possibleFilenames.getBinaryTypeAsString());
  
	}

	private String copyFileToDisk(InputStream inputStream, String pathToExtractTo,
			String filename) throws IOException {
		 if (!overwriteFilesThatExist) {
	            File[] existingFiles = new File(pathToExtractTo).listFiles();
	            if (null != existingFiles && existingFiles.length > 0) {
	                for (File existingFile : existingFiles) {
	                    String existingFilename = existingFile.getName();
	                    if (existingFilename.equals(filename)) {
	                        return existingFile.getAbsolutePath();
	                    }
	                }
	            }
	        }

	        File outputFile = new File(pathToExtractTo, filename);
	        try {
	            if (!outputFile.exists() && !outputFile.getParentFile().mkdirs() && !outputFile.createNewFile()) {
	                throw new IOException("Unable to create " + outputFile.getAbsolutePath());
	            }
	            FileUtils.copyInputStreamToFile(inputStream, outputFile);
	        } finally {
	            inputStream.close();
	        }

	        return outputFile.getAbsolutePath();
	}

}
