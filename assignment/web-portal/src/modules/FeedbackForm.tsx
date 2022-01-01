import {Button, makeStyles, TextField, Typography} from "@material-ui/core";

const useStyles = makeStyles((theme) => ({
  title: {
    marginBottom: theme.spacing(5)
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
  multiLine: {
    marginBottom: theme.spacing(3),
    width: '50%'
  }
}));

const FeedbackForm = () => {
  const classes = useStyles();

  return (
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
              defaultValue=""
              variant="outlined"
          />
          <TextField
              required
              id="email"
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
          <TextField
              id="agency"
              className={classes.textField}
              select
              label="Agency"
              value={""}
              // onChange={handleChange}
              variant="outlined"
          />
        </div>
        <div>
          <TextField
              id="feedback"
              className={classes.multiLine}
              placeholder={"Enter your feedback..."}
              multiline
              rows={6}
              defaultValue=""
              variant="outlined"
          />
        </div>
        <label>
          <Button variant="contained" color="primary" component="span">
            Submit
          </Button>
        </label>
      </div>
  );
};

export default FeedbackForm;