export const RaUrlUtil = {

    redirectTo: (url) => {
        window.location = url;
    },

    isMatchPathname: (url) => {
       return window.location.pathname === url;
    }


};