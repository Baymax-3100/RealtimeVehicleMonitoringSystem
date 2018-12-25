import LoginView from './../views/login-view';
import DashboardView from './../views/dashboard-view';
import NotFoundView from './../views/not-found-view';
import React, { Component } from 'react'
import {BrowserRouter, Route, Switch} from "react-router-dom";
import UserMainView, {UserOtherUrls} from "../views/user/user-main-view";
import PublicLayout from "../views/layouts/public-layout";
import PrivateLayout from "../views/layouts/private-layout";
import Location from '@material-ui/icons/LocationOn';
import AssignmentInd from '@material-ui/icons/SubdirectoryArrowLeftTwoTone';
import Stat from '@material-ui/icons/Timelapse';
import commute from '@material-ui/icons/Commute';
import Notification from '@material-ui/icons/NotificationImportant';



const PublicLayoutViews = [
    {
        path: "/login",
        component: LoginView,
        isActive: true,
    }
];

const PrivateLayoutViews = [
    {
        path: "/",
        name: "Dashboard",
        icon: Location,
        component: DashboardView,
        isLeftNav: false,
        isActive: true,
    },
    {
        path: "/dashboard",
        name: "Dashboard",
        icon: Location,
        component: DashboardView,
        isLeftNav: true,
        isActive: true,
    },
    {
        path: "/user",
        name: "User",
        icon:commute,
        component: UserMainView,
        isLeftNav: true,
        isActive: true,
        routes: UserOtherUrls
    },
    {
        path: "/experiment",
        name: "Vehicle Details",
        icon:Stat,
        component: UserMainView,
        isLeftNav: true,
        isActive: true,
    },
    {
        path: "/component-demo",
        name: "Notification",
        icon:Notification,
        component: UserMainView,
        isLeftNav: true,
        isActive: true,
    },
    {
        icon: AssignmentInd,
        path: "/login",
        name: "Logout",
        isLeftNav: true,
        isActive: true,
    }
];

const nestedRoutes = nestedRoutes =>{
    if (nestedRoutes.routes) {
       return nestedRoutes.routes.map((route, key) => {
            const {component, path, isActive} = route;
            if (isActive) {
                return (
                    <Route
                        exact
                        path={path}
                        key={key}
                        render={(route) => <PrivateLayout component={component} route={route}/>}/>
                )
            }
        })
    }
};

export default class UrlMapping extends Component {
    render() {
        return (
            <BrowserRouter>
                <Switch>
                    {PublicLayoutViews.map((route, key) => {
                        const {component, path, isActive} = route;
                        if (isActive) {
                            return (
                                <Route
                                    exact
                                    path={path}
                                    key={key}
                                    render={(route) => <PublicLayout component={component} route={route}/>}
                                />
                            )
                        }
                    })}
                    {PrivateLayoutViews.map((route, key) => {
                        const {component, path, isActive} = route;
                        if (isActive) {
                            return (
                                <Route
                                    exact
                                    path={path}
                                    key={key}
                                    render={(route) => <PrivateLayout component={component} route={route}/>}/>
                            )
                        }
                    })}
                    {PrivateLayoutViews.map((route, key) => nestedRoutes(route))}
                    <Route component={NotFoundView}/>
                </Switch>
            </BrowserRouter>
        );
    }
}


export {
    PrivateLayoutViews
};