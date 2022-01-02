import {AppBar, makeStyles, Tab, Tabs, Typography} from "@material-ui/core";
import React from "react";
import {useHistory} from "react-router-dom";

const useStyles = makeStyles((theme) => ({
  title: {
    marginTop: theme.spacing(3),
    marginBottom: theme.spacing(5)
  }
}));

function a11yProps(index: any) {
  return {
    id: `scrollable-auto-tab-${index}`,
    'aria-controls': `scrollable-auto-tabpanel-${index}`,
  };
}

const Navigation = () => {
  const classes = useStyles();
  const [value, setValue] = React.useState(0);
  const history = useHistory();

  const handleChange = (event: any, newValue: any) => {
    setValue(newValue);

    if (newValue == 0) {
      history.push('/feedbacks');
    } else if (newValue == 1) {
      history.push('/feedbacks/view-feedback-status');
    }
  };

  return (
      <div>
        <AppBar position="static" color="default">
          <Tabs
              value={value}
              onChange={handleChange}
              indicatorColor="primary"
              textColor="primary"
              variant="standard"
              scrollButtons="auto"
              aria-label="scrollable auto tabs example"
          >
            <Tab label="Submit Feedbacks" {...a11yProps(0)} />
            <Tab label="Feedback Status" {...a11yProps(1)} />
          </Tabs>
        </AppBar>
        <Typography
            className={classes.title}
            variant="h3"
            component="h2">
          Welcome to the Feedback Processing Portal!
        </Typography>
      </div>
  );
};

export default Navigation;