import {makeStyles, TableCell, TableRow, withStyles} from '@material-ui/core';

export const useStyles = makeStyles((theme) => ({
  nav: {
    display: 'flex',
    justifyContent: 'flex-end',
    backgroundColor: 'royalblue',
    color: 'white'
  },
  navStyle: {
    color: 'white',
    textDecoration: 'none',
    textTransform: 'uppercase'
  },
  navLink: {
    width: '25%',
    display: 'flex',
    justifyContent: 'space-around',
    alignItems: 'center',
    listStyle: 'none',
    color: 'black'
  },
  title: {
    marginTop: theme.spacing(1),
    marginBottom: theme.spacing(3)
  },
  button: {
    marginBottom: theme.spacing(3),
    width: '15%',
  },
  textField: {
    marginLeft: theme.spacing(3),
    marginRight: theme.spacing(3),
    marginBottom: theme.spacing(3),
    textAlign: "left",
    width: '50%',
  },
  contactNumber: {
    marginBottom: theme.spacing(3),
    width: '25%',
  },
  multiLine: {
    marginBottom: theme.spacing(3),
    width: '50%'
  },
  paragraph: {
    marginBottom: theme.spacing(3)
  },
  errorMessage: {
    marginTop: theme.spacing(-3),
    marginBottom: theme.spacing(3),
    color: "red"
  },
  table: {
    marginTop: theme.spacing(3),
    marginLeft: theme.spacing(15),
    width: '80%',
  },
}));

export const StyledTableCell = withStyles((theme) => ({
  head: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  body: {
    fontSize: 14,
  },
}))(TableCell);

export const StyledTableRow = withStyles((theme) => ({
  root: {
    '&:nth-of-type(odd)': {
      backgroundColor: theme.palette.action.hover,
    },
  },
}))(TableRow);