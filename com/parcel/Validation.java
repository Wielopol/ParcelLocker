package com.parcel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {



    public boolean valName(String name) {
        return !name.isBlank();
    }

    public boolean valId(String id) {
        Pattern pAddress = Pattern.compile("^[A-Z]{3}[0-9]{2}$");
        Matcher m = pAddress.matcher(id);

        return m.find();
    }

    public boolean valSize(String size) {
        Pattern pSize1 = Pattern.compile("^([0-9]+.?[0-9]?)[xX]([0-9]+.?[0-9]?)[xX]([0-9]+.?[0-9]?)$");
        Pattern pSize2 = Pattern.compile("^([0-9]+.?[0-9]?)[xX]([0-9]+.?[0-9]?)$");
        Matcher m = pSize1.matcher(size);
        Matcher n = pSize2.matcher(size);

        return m.find() || n.find();
    }

    public boolean valAddress(String address) {
        Pattern pAddress = Pattern.compile("^([A-Za-z]+\\.?\s[0-9]?[0-9]?[0-9]?\s?([A-Za-z]+\s?[A-Za-z]+-?\s?[A-Za-z]+)\s([0-9]+[A-Za-z]?)),\s?([A-Za-z]+),\s?([0-9]{2}-?[0-9]{3})$");
        Matcher m = pAddress.matcher(address);

        return m.find();
    }

    public boolean valUUid(String id) {
        Pattern pUUid = Pattern.compile("^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$");
        Matcher m = pUUid.matcher(id);

        return m.find();
    }
}
