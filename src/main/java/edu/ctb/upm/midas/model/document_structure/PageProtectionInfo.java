package edu.ctb.upm.midas.model.document_structure;

public class PageProtectionInfo {

    private String edit;
    private String move;
    private Link viewProtectLogUrl;//"View the protection log for this page."


    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }

    public Link getViewProtectLogUrl() {
        return viewProtectLogUrl;
    }

    public void setViewProtectLogUrl(Link viewProtectLogUrl) {
        this.viewProtectLogUrl = viewProtectLogUrl;
    }
}
