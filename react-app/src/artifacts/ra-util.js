import {Fade, LinearProgress} from "@material-ui/core";
import React from "react";


export const RaUtil = {

    showLoader: (state) => {
        return (
            state ? (<Fade in={state}>
                <LinearProgress color="primary"/>
            </Fade>) : ""
        );
    }


};