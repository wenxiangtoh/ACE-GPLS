import {
  Button,
  Table,
  TableBody,
  TableHead,
  TableRow,
  TextField,
  Typography
} from "@material-ui/core";
import React, {useState} from "react";
import {Link, useHistory} from "react-router-dom";
import axios from "axios";
import {POST_FEEDBACK_INFO_URL} from "../constants/apiUrl";
import {FeedbackInfoItem, FeedbackInfoRequest} from "../models/Feedback";
import {useForm} from "react-hook-form";
import {useMutation, useQueryClient} from "react-query";
import {
  StyledFeedbackTableCell,
  StyledTableCell,
  StyledTableRow,
  useStyles
} from "../constants/styles";

const FeedbackDetails = () => {
  const classes = useStyles();
  const history = useHistory();
  const queryClient = useQueryClient();

  const {register, errors, handleSubmit} = useForm({
    mode: "onSubmit",
    reValidateMode: "onSubmit"
  });

  const [feedbackDetails, setFeedbackDetails] = React.useState<FeedbackInfoItem[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [feedbackDetailsError, setFeedbackDetailsError] = useState("");

  const handleSubmitFeedbackDetails = (data: FeedbackInfoRequest) => {
    getFeedbackDetailsMutation.mutate(data);
  };

  const getFeedbackDetailsMutation = useMutation(POST_FEEDBACK_INFO_URL, (data: FeedbackInfoRequest) =>
      postGetFeedbackDetails(data));

  const postGetFeedbackDetails = async (data: FeedbackInfoRequest) => {
    const requestPayload = {
      "email": data.email,
      "contactNumber": {
        "countryCode": data.countryCode,
        "number": data.number
      },
    }

    await axios.post(POST_FEEDBACK_INFO_URL, requestPayload).then((res) => {
      queryClient.invalidateQueries(POST_FEEDBACK_INFO_URL);
      setFeedbackDetails(res.data);
      setIsLoading(false);
      setFeedbackDetailsError("")

      if (res.data.length === 0) {
        alert("There are no Feedback Records found!")
      }
    })
    .catch(error => {
      setFeedbackDetailsError("Please check your input again.")
      console.log(error.message);
    });
  }

  return (
      <form onSubmit={handleSubmit(handleSubmitFeedbackDetails)} autoComplete="off">
        <div>
          <div>
            <nav className={classes.nav}>
              <ul className={classes.navLink}>
                <Link className={classes.navStyle} to="/">
                  <li> Home</li>
                </Link>
                <Link className={classes.navStyle} to="/feedbacks">
                  <li> Submit Feedbacks</li>
                </Link>
              </ul>
            </nav>
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
                placeholder={"Email (Example: 123@gmail.com)"}
                variant="outlined"
                autoComplete="off"
                type="text"
                name="email"
                inputRef={register({
                  required: {
                    value: true,
                    message: 'Email cannot be empty!'
                  },
                  maxLength: {
                    value: 321,
                    message: "Email is too long"
                  }
                })}
                {...(errors.email && {error: true, helperText: errors.email.message})}
            />
          </div>
          <div>
            <TextField
                id="countryCode"
                className={classes.contactNumber}
                placeholder={"Country Code (Example: 65)"}
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
                  maxLength: {
                    value: 255,
                    message: "Number is too long"
                  }
                })}
                {...(errors.number && {error: true, helperText: errors.number.message})}
            />
          </div>
          <Typography className={classes.errorMessage}>
            {feedbackDetailsError}
          </Typography>
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
                        <StyledTableCell align="center" component="th" scope="row">
                          {index + 1}
                        </StyledTableCell>
                        <StyledTableCell align="center" component="th" scope="row">
                          {feedbackInfoItem.name}
                        </StyledTableCell>
                        <StyledTableCell
                            align="center">{feedbackInfoItem.email}</StyledTableCell>
                        <StyledTableCell
                            align="center">{
                          '+'.concat(feedbackInfoItem.contactNumber.countryCode.toString()).concat(" ").concat(feedbackInfoItem.contactNumber.number)}</StyledTableCell>
                        <StyledTableCell
                            align="center">{feedbackInfoItem.agency}</StyledTableCell>
                        <StyledFeedbackTableCell
                            align="center">{feedbackInfoItem.text}</StyledFeedbackTableCell>
                        <StyledTableCell
                            align="center">{feedbackInfoItem.status}</StyledTableCell>
                      </StyledTableRow>))}
                </TableBody>
              </Table>
          )}
        </div>
      </form>
  );
};

export default FeedbackDetails;