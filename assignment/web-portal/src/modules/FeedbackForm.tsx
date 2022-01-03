import {Button, makeStyles, MenuItem, TextField, Typography} from "@material-ui/core";
import React, {useEffect, useState} from "react";
import {useHistory} from "react-router-dom";
import axios from "axios";
import {GET_AGENCIES_URL, POST_FEEDBACKS_URL} from "../constants/apiUrl";
import {CreateFeedbackFormData, ListItem, LookupListApiItem} from "../models/Feedback";
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

  const {register, errors, handleSubmit, reset} = useForm({
    mode: "onSubmit",
    reValidateMode: "onSubmit"
  });

  const [agencyList, setAgencyList] = React.useState<ListItem[]>([]);
  const [agency, setAgency] = React.useState<LookupListApiItem>();
  const [isLoading, setIsLoading] = useState(true);

  const handleAgencyChange = (event: any) => {
    const index = agencyList.map(item => item.value).indexOf(event.target.value);
    const lookupListApiItem: LookupListApiItem = {
      "code": agencyList[index].key,
      "description": event.target.value
    };
    setAgency(lookupListApiItem);
  };

  const handleProcessFeedback = (data: CreateFeedbackFormData) => {
    postCreateFeedback(data);
  };

  const getAgencyList = async () => {
    await axios({
      method: 'GET',
      url: GET_AGENCIES_URL,
      headers: {
        'Content-Type': 'application/json;charset=UTF-8',
        'Access-Control-Allow-Origin': '*',
      },
    }).then((res) => {
      const agencyDropdownList: LookupListApiItem[] = res.data;
      const agencyDropdownListMenuItem = [
        ...agencyDropdownList.map(({code, description}) => ({
          children: code,
          key: code,
          value: description,
        })),
      ];
      setAgencyList(agencyDropdownListMenuItem);
      setIsLoading(false);
      return agencyDropdownListMenuItem;
    })
    .catch(error => {
      console.log(error);
    });
  };

  const postCreateFeedback = async (data: CreateFeedbackFormData) => {
    await axios({
      method: 'POST',
      url: POST_FEEDBACKS_URL,
      headers: {
        'Content-Type': 'application/json;charset=UTF-8',
        'Access-Control-Allow-Origin': '*',
      },
      data: {
        "name": data.name,
        "email": data.email,
        "contactNumber": {
          "countryCode": data?.countryCode,
          "number": data?.number
        },
        "agencyUuid": agency?.code,
        "text": data.feedback
      },
    }).then((res) => {
      reset();
      setAgency(undefined)
      alert("Your feedback have been submitted. Do note that the processing would takes up to 5 minutes.")
    })
    .catch(error => {
      console.log(error);
    });
  }

  useEffect(() => {
    getAgencyList();
  }, []);

  if (isLoading) {
    return <div>loading</div>
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
                {...(errors.countryCode && {error: true, helperText: errors.countryCode.message})}
            />
            <TextField
                id="number"
                className={classes.contactNumber}
                label="Number"
                // value={number}
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
                value={agency !== undefined ? agency.description : ""}
                onChange={handleAgencyChange}
                variant="outlined"
            >
              {agencyList.map((option) => (
                  <MenuItem key={option.key} value={option.value}>
                    {option.value}
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