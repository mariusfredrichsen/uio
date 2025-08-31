import React from "react";
import classes from "./App.module.css";
import { useState } from "react";

import { Browse } from "./Browse";
import { Insert } from "./Insert";
import { Navigation } from "./Navigation";
import { Datasets } from './Datasets';

function MyApp() {
  const [activePage, setActivePage] = useState("Browse");

  function activePageHandler(page) {
    setActivePage(page);
  }

  return (
    <div className={classes.container}>
      <div className={classes.left}>
        <Navigation
          activePage={activePage}
          activePageHandler={activePageHandler}
        />
      </div>
      <div className={classes.right}>
        {activePage === "Browse" && <Browse />}
        {activePage === "Insert" && <Insert />}
        {activePage === "Datasets" && <Datasets />}
      </div>
    </div>
  );
}

export default MyApp;
