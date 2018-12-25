import React from 'react'
import { withStyles } from '@material-ui/core/styles';
import RaViewComponent from "../artifacts/ra-view-component";


const styles = theme => ({
    root: {
        color:'blue'
    }
});

class DashboardView extends RaViewComponent {
    appRender () {
        const { classes } = this.props;
        return (
            <div
                style={{
                    width: "800px",
                    height: "500px"
                }}
            >
            </div>
        );
    }
}
export default withStyles(styles)(DashboardView);