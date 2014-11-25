import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;

public class MarketPlaceSearchResult {
    private Meta meta;
    private List<App> objects;

    class Meta {
        @JSONField(name = "total_count")
        private Integer totalCount;
        private String next;

        public Integer getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }
    }

    static class App {
        /**
         * ‘hosted’, ‘packaged’, or ‘privileged’
         */
        @JSONField(name = "app_type")
        private String appType;
        private String author;
        private String[] categories;
        @JSONField(name = "current_version")
        private String currentVersion;
        @JSONField(name = "default_locale")
        private String defaultLocale;
        @JSONField(name = "device_types")
        private String[] deviceTypes;
        @JSONField(name = "is_packaged")
        private boolean packaged;
        @JSONField(name = "manifest_url")
        private String manifestUrl;
        @JSONField(name = "package_path")
        private String packagePath;
        @JSONField(name = "supported_locales")
        private String supportedLocales;
        private String id;
        private Map<String, String> name;
        @JSONField(name = "is_disabled")
        private boolean disabled;
        // One of free, premium, free-inapp, premium-inapp. If premium or
        // premium-inapp the app should be bought, check the price field to
        // determine if it can.
        @JSONField(name = "premium_type")
        private String premiumType;

        /**
         * 0 Incomplete 2 Pending approval 4 Fully Reviewed 5 Disabled by
         * Mozilla 11 Deleted 12 Rejected 13 Approved but waiting 15 Blocked
         */
        private int status;

        public boolean isUsable() {
            if (this.disabled) {
                return false;
            }

            if (this.status != 4) {
                return false;
            }
            return true;
        }

        public boolean isFree() {
            if (this.getPremiumType().equals("premium-inapp")) {
                return false;
            }
            if (this.getPremiumType().equals("premium")) {
                return false;
            }
            return true;
        }

        public String getAppType() {
            return appType;
        }

        public void setAppType(String appType) {
            this.appType = appType;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String[] getCategories() {
            return categories;
        }

        public void setCategories(String[] categories) {
            this.categories = categories;
        }

        public String getCurrentVersion() {
            return currentVersion;
        }

        public void setCurrentVersion(String currentVersion) {
            this.currentVersion = currentVersion;
        }

        public String getManifestUrl() {
            return manifestUrl;
        }

        public void setManifestUrl(String manifestUrl) {
            this.manifestUrl = manifestUrl;
        }

        public String getPackagePath() {
            return packagePath;
        }

        public void setPackagePath(String packagePath) {
            this.packagePath = packagePath;
        }

        public String[] getDeviceTypes() {
            return deviceTypes;
        }

        public void setDeviceTypes(String[] deviceTypes) {
            this.deviceTypes = deviceTypes;
        }

        public String getDefaultLocale() {
            return defaultLocale;
        }

        public void setDefaultLocale(String defaultLocale) {
            this.defaultLocale = defaultLocale;
        }

        public String getSupportedLocales() {
            return supportedLocales;
        }

        public void setSupportedLocales(String supportedLocales) {
            this.supportedLocales = supportedLocales;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isPackaged() {
            return packaged;
        }

        public void setPackaged(boolean packaged) {
            this.packaged = packaged;
        }

        public boolean isDisabled() {
            return disabled;
        }

        public void setDisabled(boolean disabled) {
            this.disabled = disabled;
        }

        public String getPremiumType() {
            return premiumType;
        }

        public void setPremiumType(String premiumType) {
            this.premiumType = premiumType;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Map<String, String> getName() {
            return name;
        }

        public void setName(Map<String, String> name) {
            this.name = name;
        }
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<App> getObjects() {
        return objects;
    }

    public void setObjects(List<App> objects) {
        this.objects = objects;
    }

}
