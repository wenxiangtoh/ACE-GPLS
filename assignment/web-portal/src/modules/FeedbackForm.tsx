import {Button, makeStyles, MenuItem, TextField, Typography} from "@material-ui/core";
import React, {useEffect} from "react";
import {useHistory} from "react-router-dom";
import axios from "axios";
import {GET_AGENCIES_URL, POST_FEEDBACKS_URL} from "../constants/apiUrl";
import {CreateFeedbackFormData, LookupListApiItem} from "../models/Feedback";
import {useForm} from "react-hook-form";
import {useMutation, useQuery, useQueryClient} from "react-query";
import {GET_AGENCY_KEY, POST_FEEDBACKS_KEY} from "../constants/apiKey";

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
  }
}));

const FeedbackForm = () => {
  const classes = useStyles();
  const history = useHistory();
  const queryClient = useQueryClient();

  const {register, errors, handleSubmit, reset} = useForm({
    mode: "onSubmit",
    reValidateMode: "onSubmit"
  });

  const [agency, setAgency] = React.useState<LookupListApiItem>();

  const handleProcessFeedback = (data: CreateFeedbackFormData) => {
    createFeedbackMutation.mutate(data);
  };

  const {
    isLoading: isAgencyDataLoading,
    data: agencyData,
    isFetching: isAgencyDataFetching
  } = useQuery(GET_AGENCY_KEY, () =>
      fetch(
          GET_AGENCIES_URL
      ).then((res) => res.json())
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
    await axios.post(POST_FEEDBACKS_URL, requestPayload).then((res) => {
      queryClient.invalidateQueries(POST_FEEDBACKS_KEY);
      reset();
      alert("Your feedback have been submitted. Do note that the processing would takes up to 5 minutes.")
    }).catch(error => {
      console.log(error);
    });
  }

  useEffect(() => {
    if (!isAgencyDataLoading) {
      const lookupListApiItem: LookupListApiItem = {
        "code": agencyData[0].code,
        "description": agencyData[0].description
      };
      setAgency(lookupListApiItem);
    }
  }, []);

  if (isAgencyDataLoading || isAgencyDataFetching) {
    return <div> Loading... </div>
  }
  return (
      <form onSubmit={handleSubmit(handleProcessFeedback)} autoComplete="off">
        <div>
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
                })}
                {...(errors.name && {error: true, helperText: errors.name.message})}
            />
            <TextField
                id="email"
                className={classes.textField}
                label="Email"
                variant="outlined"
                autoComplete="new"
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
                placeholder={"Enter your feedback..."}
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
                })}
                {...(errors.feedback && {error: true, helperText: errors.feedback.message})}
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