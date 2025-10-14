import React from "react";
import classes from "./App.module.css";
import { useState } from "react";

import { Navigation } from "./Navigation";
import { Request } from "./Request/Request";
import { RecountFront } from "./Recount/RecountFront";
import { Create } from "./Create";
import { ChooseOrgUnit } from "./ChooseOrgUnit";

function MyApp() {
	const [activePage, setActivePage] = useState("ChooseOrgUnit");
	const [chosenOrgUnit, setChosenOrgUnit] = useState(null);

	function activePageHandler(page) {
		setActivePage(page);
	}
	if (chosenOrgUnit == null) {
		return (
			<ChooseOrgUnit
				setChosenOrgUnit={setChosenOrgUnit}
				setActivePage={setActivePage}
			/>
		);
	} else {
		return (
			<div className={classes.container}>
				<div className={classes.left}>
					<div style={{ display: "flex" }}>
						<Navigation
							activePage={activePage}
							activePageHandler={activePageHandler}
							orgUnit={chosenOrgUnit.name}
							setChosenOrgUnit={setChosenOrgUnit}
						/>
					</div>
				</div>
				<div className={classes.right}>
					{activePage === "Recount" && (
						<RecountFront orgUnit={chosenOrgUnit.id} />
					)}
					{activePage === "Create" && (
						<Create orgUnit={chosenOrgUnit.id} />
					)}
					{activePage === "Request" && (
						<Request orgUnit={chosenOrgUnit.id} />
					)}
				</div>
			</div>
		);
	}
}

export default MyApp;
