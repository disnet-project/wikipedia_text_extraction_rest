package edu.ctb.upm.midas.model.xml;

import edu.ctb.upm.midas.constants.Constants;
import org.hibernate.validator.constraints.URL;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Created by gerardo on 4/4/17.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project ExtractionInformationWikipedia
 * @className XmlLink
 * @see
 */
public class XmlLink {

    private String consult;
    private Integer id;
    @Valid
    @NotNull(message = Constants.ERR_NO_PARAMETER)
    @Size(min = 11, message = Constants.ERR_EMPTY_PARAMETER)
    @URL
    private String url;
    //Es para controlar los enlaces que definitivamente no se les hara minado de texto
    private boolean relevant;


    public String getConsult() {
        return consult;
    }

    public void setConsult(String consult) {
        this.consult = consult;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isRelevant() {
        return relevant;
    }

    public void setRelevant(boolean relevant) {
        this.relevant = relevant;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof XmlLink)) return false;
        XmlLink link = (XmlLink) o;
        return Objects.equals(getUrl(), link.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl());
    }
}