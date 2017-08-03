package web.technology.selenium.framework.config;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

public class FileDownloader {

	String downloadDirectory;

	public FileDownloader(File downloadedZipFileDirectory) throws Exception {
		this.downloadDirectory = localFilePath(downloadedZipFileDirectory);
	}

	private String localFilePath(File downloadDirectory) throws Exception {
		if (downloadDirectory.exists()) {
            if (downloadDirectory.isDirectory()) {
                return downloadDirectory.getAbsolutePath();
            } else {
                throw new Exception("'" + downloadDirectory.getAbsolutePath() + "' is not a directory!");
            }
        }

        if (downloadDirectory.mkdirs()) {
            return downloadDirectory.getAbsolutePath();
        } else {
            throw new Exception("Unable to create download directory!");
        }
	}

	public File attemptToDownload(URL fileLocation) throws IOException, URISyntaxException {
		String filename = FilenameUtils.getName(fileLocation.getFile());
        SocketConfig socketConfig = SocketConfig.custom().build();

        RequestConfig requestConfig = RequestConfig.custom().build();

        HttpClientBuilder httpClientBuilder = HttpClients.custom()
                .setDefaultSocketConfig(socketConfig)
                .setDefaultRequestConfig(requestConfig)
                .disableContentCompression();

        CloseableHttpClient httpClient = httpClientBuilder.build();

        CloseableHttpResponse fileDownloadResponse = httpClient.execute(new HttpGet(fileLocation.toURI()));

        HttpEntity remoteFileStream = fileDownloadResponse.getEntity();
        File fileToDownload = new File(downloadDirectory + File.separator + filename);
        try {
        	FileUtils.copyInputStreamToFile(remoteFileStream.getContent(), fileToDownload);
        } catch (IOException ex) {
            fileToDownload = null;
        } finally {
            fileDownloadResponse.close();
        }
        return fileToDownload;
	}


}
