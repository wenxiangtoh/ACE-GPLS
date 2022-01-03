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
import axios from "axios";
import {POST_FEEDBACK_INFO_URL} from "../constants/apiUrl";
import {FeedbackInfoItem, FeedbackInfoRequest} from "../models/Feedback";
import {useForm} from "react-hook-form";

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

  const {register, errors, handleSubmit} = useForm({
    mode: "onSubmit",
    reValidateMode: "onSubmit"
  });

  const [feedbackDetails, setFeedbackDetails] = React.useState<FeedbackInfoItem[]>([]);
  const [isLoading, setIsLoading] = useState(true);

  const handleSubmitFeedbackDetails = (data: FeedbackInfoRequest) => {
    postGetFeedbackDetails(data);
  };

  const postGetFeedbackDetails = async (data: FeedbackInfoRequest) => {
    await axios({
      method: 'POST',
      url: POST_FEEDBACK_INFO_URL,
      headers: {
        'Content-Type': 'application/json;charset=UTF-8',
        'Access-Control-Allow-Origin': '*',
      },
      data: {
        "email": data.email,
        "contactNumber": {
          "countryCode": data.countryCode,
          "number": data.number
        },
      },
    }).then((res) => {
      setFeedbackDetails(res.data);
      setIsLoading(false);
    })
    .catch(error => {
      console.log(error);
    });
  }

  return (
      <form onSubmit={handleSubmit(handleSubmitFeedbackDetails)} autoComplete="off">
        <div>
          <div>
            <Typography
                className={classes.title}
                variant="h3"
                component="h2">
              Feedback Details Page
            </Typography>
          </div>
          <div>
            <TextField
                id="email"
                className={classes.textField}
                label="Email"
                variant="outlined"
                autoComplete="off"
                type="text"
                name="email"
                inputRef={register({
                  required: {
                    value: true,
                    message: 'Email cannot be empty!'
                  },
                })}
                {...(errors.email && {error: true, helperText: errors.email.message})}
            />
          </div>
          <div>
            <TextField
                id="countryCode"
                className={classes.contactNumber}
                label="Country Code"
                variant="outlined"
                autoComplete="on"
                type="number"
                name="countryCode"
                inputRef={register({
                  required: {
                    value: true,
                    message: 'Country Code cannot be empty!'
                  },
                })}
                {...(errors.countryCode && {error: true, helperText: errors.countryCode.message})}
            />
            <TextField
                id="number"
                className={classes.contactNumber}
                label="Number"
                variant="outlined"
                autoComplete="new"
                type="text"
                name="number"
                inputRef={register({
                  required: {
                    value: true,
                    message: 'Number cannot be empty!'
                  },
                })}
                {...(errors.number && {error: true, helperText: errors.number.message})}
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
              <Button
                  id="feedback-status-submit-button"
                  className={classes.button}
                  variant="contained"
                  color="primary"
                  type="submit"
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
                          {index + 1}
                        </StyledTableCell>
                        <StyledTableCell align="left" component="th" scope="row">
                          {feedbackInfoItem.name}
                        </StyledTableCell>
                        <StyledTableCell
                            align="left">{feedbackInfoItem.email}</StyledTableCell>
                        <StyledTableCell
                            align="left">{
                          '+'.concat(feedbackInfoItem.contactNumber.countryCode.toString()).concat(" ").concat(feedbackInfoItem.contactNumber.number)}</StyledTableCell>
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