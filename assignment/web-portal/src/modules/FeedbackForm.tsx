import {Button, MenuItem, TextField, Typography} from "@material-ui/core";
import React, {useState} from "react";
import {Link} from "react-router-dom";
import axios from "axios";
import {GET_AGENCIES_URL, POST_FEEDBACKS_URL} from "../constants/apiUrl";
import {CreateFeedbackFormData, LookupListApiItem} from "../models/Feedback";
import {useForm} from "react-hook-form";
import {useMutation, useQuery, useQueryClient} from "react-query";
import {GET_AGENCY_KEY, POST_FEEDBACKS_KEY} from "../constants/apiKey";
import {useStyles} from "../constants/styles";

const FeedbackForm = () => {
  const classes = useStyles();
  const queryClient = useQueryClient();

  const {register, errors, handleSubmit, reset} = useForm({
    mode: "onSubmit",
    reValidateMode: "onSubmit"
  });

  const [agency, setAgency] = React.useState<LookupListApiItem>();
  const [createFeedbackError, setFeedbackError] = useState("");

  const handleProcessFeedback = (data: CreateFeedbackFormData) => {
    createFeedbackMutation.mutate(data);
  };

  const {
    isLoading: isAgencyDataLoading,
    data: agencyData,
    isFetching: isAgencyDataFetching
  } = useQuery(GET_AGENCY_KEY, () =>
      axios.get(
          GET_AGENCIES_URL
      ).then((res) => {
        const lookupListApiItem: LookupListApiItem = {
          "code": res.data[0].code,
          "description": res.data[0].description
        };
        setAgency(lookupListApiItem);
        return res.data;
      })
  );

  const handleAgencyChange = (event: any) => {
    const index = agencyData.map((item: { description: number; }) => item.description).indexOf(event.target.value);
    const lookupListApiItem: LookupListApiItem = {
      "code": agencyData[index].code,
      "description": event.target.value
    };
    setAgency(lookupListApiItem);
  };

  const createFeedbackMutation = useMutation(POST_FEEDBACKS_KEY, (data: CreateFeedbackFormData) =>
      postCreateFeedback(data));

  const postCreateFeedback = async (data: CreateFeedbackFormData) => {
    const requestPayload = {
      "name": data.name,
      "email": data.email,
      "contactNumber": {
        "countryCode": data?.countryCode,
        "number": data?.number
      },
      "agencyUuid": agency?.code,
      "text": data.feedback
    }
    await axios.post(POST_FEEDBACKS_URL, requestPayload).then(() => {
      queryClient.invalidateQueries(POST_FEEDBACKS_KEY);
      reset();
      setFeedbackError("");
      alert("Your feedback have been submitted. Do note that the processing would takes up to 5 minutes.");
    }).catch(error => {
      setFeedbackError("Please check your inputs again.");
      console.log(error.message)
    });
  }

  if (isAgencyDataLoading || isAgencyDataFetching) {
    return <div> Loading... </div>
  }
  return (
      <form onSubmit={handleSubmit(handleProcessFeedback)} autoComplete="off">
        <div>
          <nav className={classes.nav}>
            <ul className={classes.navLink}>
              <Link className={classes.navStyle} to="/">
                <li> Home</li>
              </Link>
              <Link className={classes.navStyle} to="/feedbacks/view-feedback-status">
                <li> Check Feedback Status</li>
              </Link>
            </ul>
          </nav>
          <Typography
              className={classes.title}
              variant="h3"
              component="h2">
            Feedback Submission Form
          </Typography>
          <div>
            <TextField
                id="name"
                className={classes.textField}
                label="Name"
                variant="outlined"
                autoComplete="new"
                type="text"
                name="name"
                inputRef={register({
                  required: {
                    value: true,
                    message: 'Name cannot be empty!'
                  },
                  maxLength: {
                    value: 255,
                    message: "Name is too long"
                  }
                })}
                {...(errors.name && {error: true, helperText: errors.name.message})}
            />
            <TextField
                id="email"
                className={classes.textField}
                placeholder={"Email (Example: 123@gmail.com)"}
                variant="outlined"
                autoComplete="new"
                name="email"
                type="text"
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
                  }
                })}
                {...(errors.countryCode && {
                  error: true,
                  helperText: errors.countryCode.message
                })}
            />
            <TextField
                id="number"
                className={classes.contactNumber}
                label="Number"
                variant="outlined"
                autoComplete="off"
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
            <TextField
                id="agency"
                className={classes.textField}
                select
                label="Agency"
                defaultValue={agencyData[0].description}
                onChange={handleAgencyChange}
                variant="outlined"
            >
              {agencyData.map((option: LookupListApiItem) => (
                  <MenuItem key={option.code} value={option.description}>
                    {option.description}
                  </MenuItem>
              ))}
            </TextField>
          </div>
          <div>
            <TextField
                id="feedback"
                className={classes.multiLine}
                placeholder={"Enter your feedback (limit to 2000 characters)"}
                multiline
                rows={6}
                variant="outlined"
                autoComplete="off"
                type="text"
                name="feedback"
                inputRef={register({
                  required: {
                    value: true,
                    message: 'Feedback cannot be empty!'
                  },
                  maxLength: {
                    value: 2000,
                    message: "Do keep the feedback within 2000 characters."
                  },
                })}
                {...(errors.feedback && {error: true, helperText: errors.feedback.message})}
            />
          </div>
          <Typography className={classes.errorMessage}>
            {createFeedbackError}
          </Typography>
          <div>
            <label>
              <Button
                  id="feedback-submit-button"
                  className={classes.button}
                  variant="contained"
                  color="primary"
                  type="submit"
              >
                Submit
              </Button>
            </label>
          </div>
        </div>
      </form>
  );
};

export default FeedbackForm;