package edu.ctb.upm.midas.model.document_structure;

public class EditHistoryInfo {

    private Link pageCreator;
    private Link pageCreatorTalk;
    private Link pageCreatorContribs;
    private Link dateOfPageCreation;

    private Link latestEditor;
    private Link latestEditorTalk;
    private Link latestEditorContribs;

    private Link dateOfLatestEdit;

    private Integer totalNumberOfEdits;
    private Integer recentNumberOfEdits;//"Recent number of edits (within past 30 days)"
    private Integer recentNumberOfDistAuth;//"Recent number of distinct authors"


    public Link getPageCreator() {
        return pageCreator;
    }

    public void setPageCreator(Link pageCreator) {
        this.pageCreator = pageCreator;
    }

    public Link getPageCreatorTalk() {
        return pageCreatorTalk;
    }

    public void setPageCreatorTalk(Link pageCreatorTalk) {
        this.pageCreatorTalk = pageCreatorTalk;
    }

    public Link getPageCreatorContribs() {
        return pageCreatorContribs;
    }

    public void setPageCreatorContribs(Link pageCreatorContribs) {
        this.pageCreatorContribs = pageCreatorContribs;
    }

    public Link getDateOfPageCreation() {
        return dateOfPageCreation;
    }

    public void setDateOfPageCreation(Link dateOfPageCreation) {
        this.dateOfPageCreation = dateOfPageCreation;
    }

    public Link getLatestEditor() {
        return latestEditor;
    }

    public void setLatestEditor(Link latestEditor) {
        this.latestEditor = latestEditor;
    }

    public Link getLatestEditorTalk() {
        return latestEditorTalk;
    }

    public void setLatestEditorTalk(Link latestEditorTalk) {
        this.latestEditorTalk = latestEditorTalk;
    }

    public Link getLatestEditorContribs() {
        return latestEditorContribs;
    }

    public void setLatestEditorContribs(Link latestEditorContribs) {
        this.latestEditorContribs = latestEditorContribs;
    }

    public Link getDateOfLatestEdit() {
        return dateOfLatestEdit;
    }

    public void setDateOfLatestEdit(Link dateOfLatestEdit) {
        this.dateOfLatestEdit = dateOfLatestEdit;
    }

    public Integer getTotalNumberOfEdits() {
        return totalNumberOfEdits;
    }

    public void setTotalNumberOfEdits(Integer totalNumberOfEdits) {
        this.totalNumberOfEdits = totalNumberOfEdits;
    }

    public Integer getRecentNumberOfEdits() {
        return recentNumberOfEdits;
    }

    public void setRecentNumberOfEdits(Integer recentNumberOfEdits) {
        this.recentNumberOfEdits = recentNumberOfEdits;
    }

    public Integer getRecentNumberOfDistAuth() {
        return recentNumberOfDistAuth;
    }

    public void setRecentNumberOfDistAuth(Integer recentNumberOfDistAuth) {
        this.recentNumberOfDistAuth = recentNumberOfDistAuth;
    }
}
