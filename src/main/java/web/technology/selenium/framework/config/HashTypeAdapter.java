package web.technology.selenium.framework.config;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class HashTypeAdapter extends XmlAdapter<String, HashType> {

    @Override
    public HashType unmarshal(String str) throws Exception {
        return HashType.valueOf(str.toUpperCase());
    }

    @Override
    public String marshal(HashType hashType) throws Exception {
        return hashType.toString().toLowerCase();
    }
}
