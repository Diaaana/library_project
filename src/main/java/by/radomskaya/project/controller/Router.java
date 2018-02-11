package by.radomskaya.project.controller;

import static by.radomskaya.project.constant.JspPage.START_PAGE;

public class Router {

    public enum RouteType {
        FORWARD, REDIRECT;
    }

    private String pagePath;
    private RouteType route = RouteType.FORWARD;

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        if (pagePath == null) {
            this.pagePath = START_PAGE;
        }
        this.pagePath = pagePath;
    }

    public RouteType getRoute() {
        return route;
    }

    public void setRoute(RouteType route) {
        if (route == null) {
            this.route = RouteType.FORWARD;
        }
        this.route = route;
    }
}
