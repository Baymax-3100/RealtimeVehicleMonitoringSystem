import RaViewComponent from "./ra-view-component";
import React from "react";
import {
    TableRow, TableCell, TableHead, TableSortLabel, Tooltip
} from '@material-ui/core';
import PropTypes from 'prop-types';


const rowsSample = [
    {
        id: 'name',
        numeric: false,
        disablePadding: false,
        label: 'Dessert (100g serving)',
        title: 'Dessert (100g serving)'
    },
];

export default class RaTableHeader extends RaViewComponent {

    sortableHandler = row => event => {
        this.props.clickForSort(row.id, row, event);
    };

    render() {
        const {order, orderBy, rows} = this.props;
        return (
            <TableHead>
                <TableRow>
                    {rows.map(row => {
                        return (
                            <TableCell
                                key={row.id}
                                numeric={row.numeric}
                                padding={row.disablePadding ? 'none' : 'default'}
                                sortDirection={orderBy === row.id ? order : false}>
                                <Tooltip
                                    title={row.title ? row.title : row.label}
                                    enterDelay={300}>
                                    <TableSortLabel
                                        active={orderBy === row.id}
                                        direction={order}
                                        onClick={this.sortableHandler(row)}>{row.label}</TableSortLabel>
                                </Tooltip>
                            </TableCell>
                        );
                    }, this)}
                    <TableCell numeric>Actions</TableCell>
                </TableRow>
            </TableHead>
        );
    }
}

RaTableHeader.propTypes = {
    order: PropTypes.string.isRequired,
    orderBy: PropTypes.string.isRequired,
    rows: PropTypes.array.isRequired,
    clickForSort: PropTypes.func.isRequired
};
