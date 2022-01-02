import {
  Button,
  makeStyles,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  TextField,
  Typography,
  withStyles
} from "@material-ui/core";
import React, {useState} from "react";
import {useHistory} from "react-router-dom";

const StyledTableCell = withStyles((theme) => ({
  head: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  body: {
    fontSize: 14,
  },
}))(TableCell);

const StyledTableRow = withStyles((theme) => ({
  root: {
    '&:nth-of-type(odd)': {
      backgroundColor: theme.palette.action.hover,
    },
  },
}))(TableRow);

function createData(name: string, calories: number, fat: number, carbs: number, protein: number) {
  return {name, calories, fat, carbs, protein};
}

const rows = [
  createData('Frozen yoghurt', 159, 6.0, 24, 4.0),
  createData('Ice cream sandwich', 237, 9.0, 37, 4.3),
  createData('Eclair', 262, 16.0, 24, 6.0),
  createData('Cupcake', 305, 3.7, 67, 4.3),
  createData('Gingerbread', 356, 16.0, 49, 3.9),
];


const useStyles = makeStyles((theme) => ({
  title: {
    marginBottom: theme.spacing(5)
  },
  button: {
    marginBottom: theme.spacing(3),
    width: '15%',
  },
  textField: {
    marginLeft: theme.spacing(3),
    marginRight: theme.spacing(3),
    marginBottom: theme.spacing(3),
    width: '50%',
  },
  contactNumber: {
    marginBottom: theme.spacing(3),
    width: '25%',
  },
  table: {
    marginTop: theme.spacing(3),
    marginLeft: theme.spacing(20),
    width: '80%',
  },
}));

const FeedbackDetails = () => {
  const classes = useStyles();
  const [tableState, setTableState] = useState(false);
  const history = useHistory();

  return (
      <div>
        <div>
          <Typography
              className={classes.title}
              variant="h3"
              component="h2"> Feedback Details Page
          </Typography>
        </div>

        <div>
          <TextField
              required
              id="name"
              className={classes.textField}
              label="Email"
              defaultValue=""
              variant="outlined"
          />
        </div>
        <div>
          <TextField
              required
              id="countryCode"
              className={classes.contactNumber}
              label="Country Code"
              defaultValue=""
              variant="outlined"
          />
          <TextField
              required
              id="number"
              className={classes.contactNumber}
              label="Number"
              defaultValue=""
              variant="outlined"
          />
        </div>
        <div>
          <label>
            <Button id="home" className={classes.button} variant="contained" color="primary"
                    component="span"
                    onClick={() => {
                      history.push('/');
                    }}>
              home
            </Button>
          </label>
        </div>
        <div>
          <label>
            <Button id="feedback-status-submit-button" className={classes.button}
                    variant="contained"
                    color="primary" component="span"
                    onClick={() => {
                      setTableState(true)
                    }}
            >
              Submit
            </Button>
          </label>
        </div>

        {tableState && (
            <Table
                className={classes.table}
                aria-label="customized table">
              <TableHead>
                <TableRow>
                  <StyledTableCell
                      align="center"> Dessert(100g serving)
                  </StyledTableCell>
                  <StyledTableCell align="center">Calories</StyledTableCell>
                  <StyledTableCell align="center">Fat&nbsp;(g)</StyledTableCell>
                  <StyledTableCell align="center">Carbs&nbsp;(g)</StyledTableCell>
                  <StyledTableCell align="center">Protein&nbsp;(g)</StyledTableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {rows.map((row) => (
                    <StyledTableRow key={row.name}>
                      <StyledTableCell align="center" component="th" scope="row">
                        {row.name}
                      </StyledTableCell>
                      <StyledTableCell align="center">{row.calories}</StyledTableCell>
                      <StyledTableCell align="center">{row.fat}</StyledTableCell>
                      <StyledTableCell align="center">{row.carbs}</StyledTableCell>
                      <StyledTableCell align="center">{row.protein}</StyledTableCell>
                    </StyledTableRow>
                ))}
              </TableBody>
            </Table>
        )
        }
      </div>
  );
};

export default FeedbackDetails;