import {Typography} from "@material-ui/core";
import React from "react";
import {Link} from "react-router-dom";
import {useStyles} from "../constants/styles";

const Navigation = () => {
  const classes = useStyles();

  return (
      <div>
        <nav className={classes.nav}>
          <ul className={classes.navLink}>
            <Link className={classes.navStyle} to="/feedbacks">
              <li> Submit Feedbacks</li>
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
          Welcome to the Portal!
        </Typography>
      </div>
  );
};

export default Navigation;