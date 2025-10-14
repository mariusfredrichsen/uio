import React, { useState } from "react";
import { InputField, Button, Checkbox } from "@dhis2/ui";
import classes from "./Request.module.css";
import { Form as ReactFinalForm } from "react-final-form";

export const SelectSchoolType = (props) => {
	const [submitted, setSubmitted] = useState(false);

	const schoolTypes = [
		{ key: "p", name: "Primary" },
		{ key: "ls", name: "Lower Secondary" },
		{ key: "us", name: "Upper Secondary" },
		{ key: "t", name: "Tertiery" },
	];

	return (
		<ReactFinalForm
			onSubmit={() => {
				// check if all inputs are filled ifcorresponding checkbox is checked
				if (
					schoolTypes.every((type) => {
						const key = type.key;
						return (
							(props.schoolType[key].selected &&
								props.learners[key].number > 0) ||
							!props.schoolType[key].selected
						);
					})
				) {
					props.checkResources();
					props.hide();
				} else {
					setSubmitted(true);
					props.show();
				}
			}}
			initialValues={{
				school: props.orgUnit,
				learners: props.learners,
				schoolType: props.schoolType,
			}}
		>
			{({ handleSubmit }) => (
				<form
					onSubmit={handleSubmit}
					style={{
						display: "flex",
						flexDirection: "column",
						gap: "8px",
						width: "50%",
					}}
				>
					<h3 style={{ margin: "16px 0px 8px 0px" }}>
						School Type *
					</h3>
					<div
						style={{
							display: "flex",
							flexDirection: "column",
							gap: "16px",
						}}
					>
						{schoolTypes.map((type) => (
							<div key={type.key}>
								<Checkbox
									className={classes.checkbox}
									checked={
										props.schoolType[type.key].selected
									}
									label={`School contains ${type.name.toLowerCase()} learners`}
									onChange={() => {
										const isSelected =
											props.schoolType[type.key].selected;
										const newSchoolType = {
											...props.schoolType,
											[type.key]: {
												selected: !isSelected,
											},
										};
										setSubmitted(false);
										props.setSchoolType(newSchoolType);
									}}
								/>
								{props.schoolType[type.key].selected && (
									<InputField
										type="number"
										required
										placeholder={`Number of ${type.name} learners`}
										value={
											props.learners[type.key].number <= 0
												? ""
												: props.learners[
														type.key
												  ].number.toString()
										}
										min="0"
										onChange={(e) => {
											const value = e.value;
											if (value > 0) {
												const newLearners = {
													...props.learners,
													[type.key]: {
														number: parseInt(
															value,
															10
														),
													},
												};
												props.setLearners(newLearners);
											}
										}}
										error={
											submitted &&
											props.schoolType[type.key]
												.selected &&
											!(
												props.learners[type.key]
													.number > 0
											)
										}
										validationText={`${
											submitted &&
											props.schoolType[type.key]
												.selected &&
											!(
												props.learners[type.key]
													.number > 0
											)
												? "* please fill in this field"
												: ""
										}`}
									/>
								)}
							</div>
						))}
					</div>

					<Button
						style={{ width: "200px", height: "36px", fontSize: "14px",  margin: "8px" }}
						type="submit"
						primary
						disabled={
							!schoolTypes.some((type) => {
								return (
									props.learners[type.key].number > 0 &&
									props.schoolType[type.key].selected
								);
							})
						}
					>
						Check resources
					</Button>
				</form>
			)}
		</ReactFinalForm>
	);
};
