package web.technology.selenium.framework.config;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FilenameUtils;

public class DownloadHandler {

	private final DriverMap filesToDownload;
	private final File rootStandaloneServerDirectory;
	private final File downloadedZipFileDirectory;
	private boolean onlyGetLatestVersions = true;
	private FileDownloader fileDownloader;

	public DownloadHandler(File rootStandaloneServerDirectory, File downloadedZipFileDirectory, DriverMap filesToDownload, boolean onlyGetLatestVersions) throws Exception {
		this.rootStandaloneServerDirectory = rootStandaloneServerDirectory;
		this.downloadedZipFileDirectory = downloadedZipFileDirectory;
		this.filesToDownload = filesToDownload;
		this.onlyGetLatestVersions = onlyGetLatestVersions;

		fileDownloader = new FileDownloader(downloadedZipFileDirectory);
	}

	public DriverMap ensureStandaloneExecutableFilesExist() throws Exception {
		for (final DriverContext driverContext : filesToDownload.getKeys()) {
			if (onlyGetLatestVersions) {
				DriverDetails driverDetails = filesToDownload.getDetailsForLatestVersionOfDriverContext(driverContext);
				downloadAndExtractExecutableFiles(driverContext, driverDetails);
			} else {
				for (String version : filesToDownload.getAvailableVersionsForDriverContext(driverContext)) {
					DriverDetails driverDetails = filesToDownload.getDetailsForVersionOfDriverContext(driverContext, version);
					downloadAndExtractExecutableFiles(driverContext, driverDetails);
				}
			}
		}

		return filesToDownload;
	}

	private void downloadAndExtractExecutableFiles(DriverContext driverContext, DriverDetails driverDetails) throws Exception {
		String localZipFileAbsolutePath = this.downloadedZipFileDirectory + File.separator + FilenameUtils.getName(driverDetails.fileLocation.getFile());
		File localZipFile = new File(localZipFileAbsolutePath);
		boolean fileNeedsToBeDownloaded = true;

		if (fileNeedsToBeDownloaded) {
			localZipFile = downloadFile(driverDetails);
		}

		String extractedFileLocation = this.rootStandaloneServerDirectory.getAbsolutePath() + File.separator + driverContext.buildExtractionPathFromDriverContext();
		FileExtractor fileExtractor = new FileExtractor(true);
		driverDetails.extractedLocation = fileExtractor.extractFileFromArchive(localZipFile, extractedFileLocation, driverContext.getBinaryTypeForContext());

	}

	private File downloadFile(DriverDetails driverDetails) throws Exception {

		URL remoteFileLocation = driverDetails.fileLocation;

		final String filename = FilenameUtils.getName(remoteFileLocation.getFile());
		File downloadedFile = fileDownloader.attemptToDownload(remoteFileLocation);
		if (null != downloadedFile) {
			return downloadedFile;

		}

		throw new Exception("Unable to successfully download '" + filename + "'!");
	}

}
