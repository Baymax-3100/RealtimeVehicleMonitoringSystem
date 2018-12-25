import RaViewComponent from "../../artifacts/ra-view-component";
import React from "react";
import {
    Button, TextField, FormControl, InputLabel, Radio,
    Select,MenuItem, FormHelperText, Checkbox, FormGroup, FormLabel,RadioGroup,
    Card, CardContent, CardActions, CardHeader, Grid, withStyles
} from '@material-ui/core'
import {ApiURL} from "../../app/api-url";



const styles = theme => ({
    root: {
        width: '100%',
        marginTop: theme.spacing.unit * 3,
    },
    table: {
        minWidth: 1020,
    },
    tableWrapper: {
        overflowX: 'auto',
    },
    mainActionArea : {
        display: "flex",
        justifyContent: "space-between",
        padding: "8px",
    },
    marginToLeft : {
        marginLeft: theme.spacing.unit,
    },
    displayInline : {
        display: "inline",
    },
});


class UserCreateUpdateView extends RaViewComponent {

    constructor(props) {
        super(props);
        this.state = {
            edit: false,
            formData: {},
            formError: {},
            formEditData: {},
        };
    }


    componentDidMount() {
        this.showFlashMessage();
        let id = this.getValueFromParams("id");
        if (id) {
            this.setState({edit: true})
            this.showFlashMessage();
            this.getToApi("api/v1/user/details?propertyName=id&propertyValue=" + id, response => {
                this.setState({formData: response.data.response})
            });
        }
    }

    formSubmitHandler = event => {
        event.preventDefault();
        let formData = this.state.formData;
        let url = ApiURL.UserCreate;
        let successMessage = "Successfully Created!!";

        let id = this.getValueFromParams("id");
        if (id){
            url = ApiURL.UserUpdate;
            successMessage = "Successfully Updated!!";
            formData = RaGsConditionMaker.equal(formData, "id", Number(id))
        }
        this.postJsonToApi(url, formData,
            success => {
                this.processFormResponse(success.data, "/user", successMessage);
            }
        )
    };



    appRender() {
        const registrationForm = (
            <form onSubmit={this.formSubmitHandler}>
                <Card>
                    <CardHeader title={this.state.edit ? "Update User" : "Create User"}/>
                    <CardContent>
                        <Grid container spacing={8}>
                            <Grid item xs={6}><TextField label="First name" {...this.onChangeTextFieldProcessor("firstName")} fullWidth/></Grid>
                            <Grid item xs={6}><TextField label="Last name"  {...this.onChangeTextFieldProcessor("lastName")} fullWidth/></Grid>
                            <Grid item xs={6}><TextField label="Email" type="email" {...this.onChangeTextFieldProcessor("email")} fullWidth/></Grid>
                            {!this.state.edit? <Grid item xs={6}><TextField label="Password" type="password"{...this.onChangeTextFieldProcessor("password")} fullWidth/></Grid> : ''}
                        </Grid>
                    </CardContent>
                    <CardActions>
                        <Grid container spacing={8} justify="flex-end">
                            <Grid item xs={1}><Button color="primary" type="submit" fullWidth variant="raised" children={this.state.edit ? "Update" : "Save"}/></Grid>
                            <Grid item xs={1}><Button color="primary" onClick={event =>{this.goToUrl("/user", event)}} fullWidth variant="raised" children="Cancel"/></Grid>
                        </Grid>
                    </CardActions>
                </Card>
            </form>
        );
        return registrationForm;
    }
}

export default withStyles(styles)(UserCreateUpdateView);