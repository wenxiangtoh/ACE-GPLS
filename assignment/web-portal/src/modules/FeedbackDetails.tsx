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
import React, {FormEvent, useState} from "react";
import {useHistory} from "react-router-dom";
import axios from "axios";
import {POST_FEEDBACK_INFO_URL} from "../constants/apiUrl";
import {FeedbackInfoItem} from "../models/Feedback";

const useStyles = makeStyles((theme) => ({
  title: {
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
    width: '50%',
  },
  contactNumber: {
    marginBottom: theme.spacing(3),
    width: '25%',
  },
  table: {
    marginTop: theme.spacing(3),
    marginLeft: theme.spacing(15),
    width: '80%',
  },
}));

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

const FeedbackDetails = () => {
  const classes = useStyles();
  const history = useHistory();

  const [email, setEmail] = useState("");
  const [countryCode, setCountryCode] = useState("");
  const [number, setNumber] = useState("");
  const [feedbackDetails, setFeedbackDetails] = React.useState<FeedbackInfoItem[]>([]);
  const [isLoading, setIsLoading] = useState(true);

  const handleEmailChange = () => (
      e: React.FormEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setEmail(e.currentTarget.value);
  };

  const handleCountryCodeChange = () => (
      e: React.FormEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setCountryCode(e.currentTarget.value);
  };

  const handleNumberChange = () => (
      e: React.FormEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setNumber(e.currentTarget.value);
  };

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
  };

  const postGetFeedbackDetails = async () => {
    await axios({
      method: 'POST',
      url: POST_FEEDBACK_INFO_URL,
      headers: {
        'Content-Type': 'application/json;charset=UTF-8',
        'Access-Control-Allow-Origin': '*',
      },
      data: {
        "email": email,
        "contactNumber": {
          "countryCode": countryCode,
          "number": number
        },
      },
    }).then((res) => {
      if (feedbackDetails !== undefined) {
        setFeedbackDetails(res.data);
        setIsLoading(false);
        if (feedbackDetails.length === 0) {
          alert("No feedback Records Found!");
        }
      }
    })
    .catch(error => {
      console.log(error);
    });
  }

  return (
      <form onSubmit={handleSubmit} autoComplete="off">
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
                onChange={handleEmailChange()}
                autoComplete="new"
                type="text"
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
                onChange={handleCountryCodeChange()}
                autoComplete="on"
                type="text"
            />
            <TextField
                required
                id="number"
                className={classes.contactNumber}
                label="Number"
                defaultValue=""
                variant="outlined"
                onChange={handleNumberChange()}
                autoComplete="off"
                type="text"
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
                        postGetFeedbackDetails()
                      }}
              >
                Submit
              </Button>
            </label>
          </div>
          {!isLoading && feedbackDetails.length > 0 && (
              <Table
                  className={classes.table}
                  aria-label="customized table">
                <TableHead>
                  <TableRow>
                    <StyledTableCell align="center">S/N&nbsp;</StyledTableCell>
                    <StyledTableCell align="center">Name&nbsp;</StyledTableCell>
                    <StyledTableCell align="center">Email&nbsp;</StyledTableCell>
                    <StyledTableCell align="center">Contact Number&nbsp;</StyledTableCell>
                    <StyledTableCell align="center">Agency&nbsp;</StyledTableCell>
                    <StyledTableCell align="center">Feedback&nbsp;</StyledTableCell>
                    <StyledTableCell align="center">Status&nbsp;</StyledTableCell>
                  </TableRow>
                </TableHead>

                <TableBody>{
                  feedbackDetails.map((feedbackInfoItem, index) => (
                      <StyledTableRow key={index}>
                        <StyledTableCell align="left" component="th" scope="row">
                          {index}
                        </StyledTableCell>
                        <StyledTableCell align="left" component="th" scope="row">
                          {feedbackInfoItem.name}
                        </StyledTableCell>
                        <StyledTableCell
                            align="left">{feedbackInfoItem.email}</StyledTableCell>
                        <StyledTableCell
                            align="left">{feedbackInfoItem.contactNumber.countryCode + " " + feedbackInfoItem.contactNumber.number}</StyledTableCell>
                        <StyledTableCell
                            align="left">{feedbackInfoItem.agency}</StyledTableCell>
                        <StyledTableCell
                            align="left">{feedbackInfoItem.text}</StyledTableCell>
                        <StyledTableCell
                            align="left">{feedbackInfoItem.status}</StyledTableCell>
                      </StyledTableRow>))}
                </TableBody>
              </Table>
          )}
        </div>
      </form>
  );
};

export default FeedbackDetails;