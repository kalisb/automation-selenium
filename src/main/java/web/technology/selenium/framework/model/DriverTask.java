package web.technology.selenium.framework.model;

import java.io.File;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Set;

import web.technology.selenium.framework.config.DownloadHandler;
import web.technology.selenium.framework.config.DriverMap;
import web.technology.selenium.framework.config.FileRepository;
import web.technology.selenium.framework.config.OperatingSystem;
import web.technology.selenium.framework.config.SystemArchitecture;
import web.technology.selenium.framework.config.XMLParser;

public class DriverTask implements Task {

	private enum Status {
		OK, ERROR
	}

	private Status status;

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
                 FileRepository.buildDownloadableFileRepository(parser.getAllNodesInScope(), thirtyTwoBitBinaries, sixtyFourBitBinaries, armBinaries),
                 true);
         driverRepository = standaloneExecutableDownloader.ensureStandaloneExecutableFilesExist();
         // TO DO: Set appropriate system property according to selected browser. 
         //System.setProperty(key, value);
         this.status = Status.OK;
		} catch (Exception e) {
			this.status = Status.ERROR;
		}

	}

}
