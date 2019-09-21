package edu.ctb.upm.midas.model.document_structure;

public class Information {

    private BasicInfo basicInfo;
    private PageProtectionInfo pageProtectionInfo;
    private EditHistoryInfo editHistoryInfo;

    public Information() {
    }

    public Information(BasicInfo basicInfo, PageProtectionInfo pageProtectionInfo, EditHistoryInfo editHistoryInfo) {
        this.basicInfo = basicInfo;
        this.pageProtectionInfo = pageProtectionInfo;
        this.editHistoryInfo = editHistoryInfo;
    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public PageProtectionInfo getPageProtectionInfo() {
        return pageProtectionInfo;
    }

    public void setPageProtectionInfo(PageProtectionInfo pageProtectionInfo) {
        this.pageProtectionInfo = pageProtectionInfo;
    }

    public EditHistoryInfo getEditHistoryInfo() {
        return editHistoryInfo;
    }

    public void setEditHistoryInfo(EditHistoryInfo editHistoryInfo) {
        this.editHistoryInfo = editHistoryInfo;
    }
}
