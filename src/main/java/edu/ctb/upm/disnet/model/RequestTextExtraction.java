package edu.ctb.upm.disnet.model;

import edu.ctb.upm.disnet.constants.Constants;
import edu.ctb.upm.disnet.model.xml.XmlLink;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class RequestTextExtraction {

    @Valid
    @NotNull(message = Constants.ERR_NO_PARAMETER)
    @Size(min = 1, message = Constants.ERR_EMPTY_PARAMETER)
    private List<XmlLink> wikipediaLinks;

    public List<XmlLink> getWikipediaLinks() {
        return wikipediaLinks;
    }

    public void setWikipediaLinks(List<XmlLink> wikipediaLinks) {
        this.wikipediaLinks = wikipediaLinks;
    }
}
