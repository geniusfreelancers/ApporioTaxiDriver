package com.apporio.demotaxiappdriver.models;

/**
 * Created by lenovo-pc on 5/3/2017.
 */

public class ConfigModelResponse {


    /**
     * result : 1
     * response : {"user_config":{"map_theme":1,"base_url":"http://apporioinfolabs.com/apporiotaxi/api/","image_domain":"http://apporioinfolabs.com/apporiotaxi/","login_base_url":"http://apporioinfolabs.com/"},"driver_config":{"map_theme":0,"base_url":"http://apporioinfolabs.com/apporiotaxi/api/","image_domain":"http://apporioinfolabs.com/apporiotaxi/"}}
     */

    private int result;
    private ResponseBean response;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * user_config : {"map_theme":1,"base_url":"http://apporioinfolabs.com/apporiotaxi/api/","image_domain":"http://apporioinfolabs.com/apporiotaxi/","login_base_url":"http://apporioinfolabs.com/"}
         * driver_config : {"map_theme":0,"base_url":"http://apporioinfolabs.com/apporiotaxi/api/","image_domain":"http://apporioinfolabs.com/apporiotaxi/"}
         */

        private UserConfigBean user_config;
        private DriverConfigBean driver_config;

        public UserConfigBean getUser_config() {
            return user_config;
        }

        public void setUser_config(UserConfigBean user_config) {
            this.user_config = user_config;
        }

        public DriverConfigBean getDriver_config() {
            return driver_config;
        }

        public void setDriver_config(DriverConfigBean driver_config) {
            this.driver_config = driver_config;
        }

        public static class UserConfigBean {
            /**
             * map_theme : 1
             * base_url : http://apporioinfolabs.com/apporiotaxi/api/
             * image_domain : http://apporioinfolabs.com/apporiotaxi/
             * login_base_url : http://apporioinfolabs.com/
             */

            private int map_theme;
            private String base_url;
            private String image_domain;
            private String login_base_url;

            public int getMap_theme() {
                return map_theme;
            }

            public void setMap_theme(int map_theme) {
                this.map_theme = map_theme;
            }

            public String getBase_url() {
                return base_url;
            }

            public void setBase_url(String base_url) {
                this.base_url = base_url;
            }

            public String getImage_domain() {
                return image_domain;
            }

            public void setImage_domain(String image_domain) {
                this.image_domain = image_domain;
            }

            public String getLogin_base_url() {
                return login_base_url;
            }

            public void setLogin_base_url(String login_base_url) {
                this.login_base_url = login_base_url;
            }
        }

        public static class DriverConfigBean {
            /**
             * map_theme : 0
             * base_url : http://apporioinfolabs.com/apporiotaxi/api/
             * image_domain : http://apporioinfolabs.com/apporiotaxi/
             */

            private int map_theme;
            private String base_url;
            private String image_domain;

            public int getMap_theme() {
                return map_theme;
            }

            public void setMap_theme(int map_theme) {
                this.map_theme = map_theme;
            }

            public String getBase_url() {
                return base_url;
            }

            public void setBase_url(String base_url) {
                this.base_url = base_url;
            }

            public String getImage_domain() {
                return image_domain;
            }

            public void setImage_domain(String image_domain) {
                this.image_domain = image_domain;
            }
        }
    }
}
