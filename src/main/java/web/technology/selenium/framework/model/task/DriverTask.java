package web.technology.selenium.framework.model.task;

import web.technology.selenium.framework.config.webdriver.*;

import java.io.File;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

public class DriverTask implements Task {

    private static Logger LOG = Logger.getLogger(DriverTask.class.getName());

	private enum Status {
		OK, ERROR, RUNNING
	}

	private Status status;
	private String browser;
	
	public DriverTask(String browser) {
		this.browser = browser;
	}
	
	@Override
	public String getStatus() {
		return status.name();
	}

	@Override
	public String getName() {
		return "chDriver";
	}

	@Override
	public String getTime() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp.toString();
	}

	@Override
	public void run() {
    	Set<OperatingSystem> osTypeList =  OperatingSystem.getCurrentOperatingSystemAsAHashSet();
    	//Calculate system architecture
    	boolean thirtyTwoBitBinaries = false;
    	boolean sixtyFourBitBinaries = false;
    	boolean armBinaries = false;
    	
    	if (SystemArchitecture.getCurrentSystemArcitecture().equals(SystemArchitecture.ARCHITECTURE_64_BIT)) {
            sixtyFourBitBinaries = true;
        } else if (SystemArchitecture.getCurrentSystemArcitecture().equals(SystemArchitecture.ARCHITECTURE_ARM)) {
            armBinaries = true;
        } else {
            thirtyTwoBitBinaries = true;
        }
    	
    	DriverMap driverRepository;
    	InputStream xmlRepositoryMap = this.getClass().getResourceAsStream("/RepositoryMap.xml");
    	XMLParser parser = new XMLParser(xmlRepositoryMap, osTypeList, null, thirtyTwoBitBinaries, sixtyFourBitBinaries);
    	File binaries = new File("driver-binaries");
		File compressed = new File("compressed_files");
		binaries.mkdir();
		compressed.mkdir();
		try {
		DownloadHandler standaloneExecutableDownloader = new DownloadHandler(
                 binaries,
                 compressed,
                 FileRepository.buildDownloadableFileRepository(parser.getAllNodesInScope(), browser, thirtyTwoBitBinaries, sixtyFourBitBinaries, armBinaries),
                 true);
         driverRepository = standaloneExecutableDownloader.ensureStandaloneExecutableFilesExist();
         LOG.warning("Finish download binaries");
         Properties browserMapping = new Properties();
         browserMapping.load(this.getClass().getResourceAsStream("/browser-mapping.properties"));
         ArrayList<DriverContext> driverContextsForCurrentOperatingSystem = driverRepository.getDriverContextsForCurrentOperatingSystem();
         DriverDetails driverDetails = driverRepository.getDetailsForLatestVersionOfDriverContext(driverContextsForCurrentOperatingSystem.get(0));
         System.setProperty(browserMapping.getProperty(browser), driverDetails.extractedLocation);
         System.setProperty("browser", browser);
         LOG.warning("Set browser property to: " + browser);
         this.status = Status.OK;
		} catch (Exception e) {
			this.status = Status.ERROR;
            LOG.severe(e.getMessage());
		}

	}

}
