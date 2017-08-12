package web.technology.selenium.framework.config.webdriver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.net.URL;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DriverDetails {

    @XmlElement(name = "filelocation")
    public URL fileLocation;

    @XmlElement()
    public String hash;

    @XmlElement(name = "hashtype")
    @XmlJavaTypeAdapter(HashTypeAdapter.class)
    public HashType hashType;

    public String extractedLocation;

    @Override
    public int hashCode() {
        int result = fileLocation != null ? fileLocation.hashCode() : 0;
        result = 31 * result + (hash != null ? hash.hashCode() : 0);
        result = 31 * result + (hashType != null ? hashType.hashCode() : 0);
        return result;
    }
}
