package edu.ctb.upm.midas.model.document_structure;

import java.util.List;

public class BasicInfo {

    private String displayTitle;
    private String defaultSortKey;
    private Integer pageLengthInBytes;
    private String pageId;
    private String pageContentLanguage;
    private String pageContentModel;
    private String indexingByRobots;
    private Integer numberOfPageWatchers;
    private Integer numberOfPageWatchersWVRE;//"Number of page watchers who visited recent edits"
    private Link numberOfRedirectsTTP;//"Number of redirects to this page"
    private List<Link> redirectList;
    private String countedAsAContentPage;
    private Link wikidataItemId;
    private String localDescription;
    private String centralDescription;
    private Link pageImage;
    private Integer pageViewsInTP30D;//"Page views in the past 30 days"


    public String getDisplayTitle() {
        return displayTitle;
    }

    public void setDisplayTitle(String displayTitle) {
        this.displayTitle = displayTitle;
    }

    public String getDefaultSortKey() {
        return defaultSortKey;
    }

    public void setDefaultSortKey(String defaultSortKey) {
        this.defaultSortKey = defaultSortKey;
    }

    public Integer getPageLengthInBytes() {
        return pageLengthInBytes;
    }

    public void setPageLengthInBytes(Integer pageLengthInBytes) {
        this.pageLengthInBytes = pageLengthInBytes;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getPageContentLanguage() {
        return pageContentLanguage;
    }

    public void setPageContentLanguage(String pageContentLanguage) {
        this.pageContentLanguage = pageContentLanguage;
    }

    public String getPageContentModel() {
        return pageContentModel;
    }

    public void setPageContentModel(String pageContentModel) {
        this.pageContentModel = pageContentModel;
    }

    public String getIndexingByRobots() {
        return indexingByRobots;
    }

    public void setIndexingByRobots(String indexingByRobots) {
        this.indexingByRobots = indexingByRobots;
    }

    public Integer getNumberOfPageWatchers() {
        return numberOfPageWatchers;
    }

    public void setNumberOfPageWatchers(Integer numberOfPageWatchers) {
        this.numberOfPageWatchers = numberOfPageWatchers;
    }

    public Integer getNumberOfPageWatchersWVRE() {
        return numberOfPageWatchersWVRE;
    }

    public void setNumberOfPageWatchersWVRE(Integer numberOfPageWatchersWVRE) {
        this.numberOfPageWatchersWVRE = numberOfPageWatchersWVRE;
    }

    public Link getNumberOfRedirectsTTP() {
        return numberOfRedirectsTTP;
    }

    public void setNumberOfRedirectsTTP(Link numberOfRedirectsTTP) {
        this.numberOfRedirectsTTP = numberOfRedirectsTTP;
    }

    public List<Link> getRedirectList() {
        return redirectList;
    }

    public void setRedirectList(List<Link> redirectList) {
        this.redirectList = redirectList;
    }

    public String getCountedAsAContentPage() {
        return countedAsAContentPage;
    }

    public void setCountedAsAContentPage(String countedAsAContentPage) {
        this.countedAsAContentPage = countedAsAContentPage;
    }

    public Link getWikidataItemId() {
        return wikidataItemId;
    }

    public void setWikidataItemId(Link wikidataItemId) {
        this.wikidataItemId = wikidataItemId;
    }

    public String getLocalDescription() {
        return localDescription;
    }

    public void setLocalDescription(String localDescription) {
        this.localDescription = localDescription;
    }

    public String getCentralDescription() {
        return centralDescription;
    }

    public void setCentralDescription(String centralDescription) {
        this.centralDescription = centralDescription;
    }

    public Link getPageImage() {
        return pageImage;
    }

    public void setPageImage(Link pageImage) {
        this.pageImage = pageImage;
    }

    public Integer getPageViewsInTP30D() {
        return pageViewsInTP30D;
    }

    public void setPageViewsInTP30D(Integer pageViewsInTP30D) {
        this.pageViewsInTP30D = pageViewsInTP30D;
    }
}
