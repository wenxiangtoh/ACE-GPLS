import {Button, makeStyles, MenuItem, TextField, Typography} from "@material-ui/core";
import React, {FormEvent, useEffect, useState} from "react";
import {useHistory} from "react-router-dom";
import axios from "axios";
import {GET_AGENCIES_URL, POST_FEEDBACKS_URL} from "../constants/apiUrl";
import {ListItem, LookupListApiItem} from "../models/Feedback";

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

  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [countryCode, setCountryCode] = useState("");
  const [number, setNumber] = useState("");
  const [agencyList, setAgencyList] = React.useState<ListItem[]>([]);
  const [agency, setAgency] = React.useState<LookupListApiItem>();
  const [feedback, setFeedback] = useState("");
  const [isLoading, setIsLoading] = useState(true);

  const handleNameChange = () => (
      e: React.FormEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setName(e.currentTarget.value);
  };

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

  const handleAgencyChange = (event: any) => {
    const index = agencyList.map(item => item.value).indexOf(event.target.value);
    const lookupListApiItem: LookupListApiItem = {
      "code": agencyList[index].key,
      "description": event.target.value
    };
    setAgency(lookupListApiItem);
  };

  const handleFeedbackChange = () => (
      e: React.FormEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setFeedback(e.currentTarget.value);
  };

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
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

  const postCreateFeedback = async () => {
    await axios({
      method: 'POST',
      url: POST_FEEDBACKS_URL,
      headers: {
        'Content-Type': 'application/json;charset=UTF-8',
        'Access-Control-Allow-Origin': '*',
      },
      data: {
        "name": name,
        "email": email,
        "contactNumber": {
          "countryCode": countryCode,
          "number": number
        },
        "agencyUuid": agency?.code,
        "text": feedback
      },
    }).then((res) => {
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
      <form onSubmit={handleSubmit} autoComplete="off">
        <div>
          <Typography className={classes.title} variant="h3" component="h2">
            Feedback Submission Form
          </Typography>
          <div>
            <TextField
                required
                id="name"
                className={classes.textField}
                label="Name"
                value={name}
                variant="outlined"
                onChange={handleNameChange()}
                autoComplete="new"
                type="text"
            />
            <TextField
                required
                id="email"
                className={classes.textField}
                label="Email"
                value={email}
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
                value={countryCode}
                onChange={handleCountryCodeChange()}
                variant="outlined"
                autoComplete="on"
                type="text"
            />
            <TextField
                required
                id="number"
                className={classes.contactNumber}
                label="Number"
                value={number}
                variant="outlined"
                onChange={handleNumberChange()}
                autoComplete="off"
                type="text"
            />
            <TextField
                id="agency"
                className={classes.textField}
                select
                label="Agency"
                value={agency?.description}
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
                defaultValue={feedback}
                variant="outlined"
                onChange={handleFeedbackChange()}
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
              <Button
                  id="feedback-submit-button"
                  className={classes.button}
                  variant="contained"
                  color="primary"
                  type={"submit"}
                  onClick={() => {
                    postCreateFeedback()
                  }}
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