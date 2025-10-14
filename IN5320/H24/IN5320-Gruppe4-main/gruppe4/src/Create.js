import React from "react";
import {
	ReactFinalForm,
	InputFieldFF,
	Button,
	composeValidators,
	hasValue,
	integer,
	createNumberRange,
} from "@dhis2/ui";
import { useDataMutation, useAlert } from "@dhis2/app-runtime";
import "./components/MyForm.css";

const dataMutationQuery = {
	resource: "events",
	type: "create",
	data: ({
		orgUnit,
		computerLab,
		computerLabCondition,
		electricitySupply,
		electricitySupplyCondition,
		handwashingFacilities,
		handwashingFacilitiesCondition,
		totalNumberOfClassrooms,
		totalNumberOfClassroomsClean,
		playground,
		playgroundCondition,
		toiletsForTeachers,
	}) => ({
		events: [
			{
				program: "UxK2o06ScIe",
				programStage: "eJiBjm9Rl7E",
				orgUnit: orgUnit,
				eventDate: "2024-11-13",
				status: "COMPLETED",
				dataValues: [
					{ dataElement: "Nvp4hIbXrzF", value: computerLab },
					{ dataElement: "gzhjCMe7OyS", value: computerLabCondition },
					{ dataElement: "IKiSAA19Xvl", value: electricitySupply },
					{
						dataElement: "MH2eDd7qWxR",
						value: electricitySupplyCondition,
					},
					{
						dataElement: "n9KwS4rY2HC",
						value: handwashingFacilities,
					},
					{
						dataElement: "ie3bFiVatHT",
						value: handwashingFacilitiesCondition,
					},
					{
						dataElement: "ya5SyA5hej4",
						value: totalNumberOfClassrooms,
					},
					{
						dataElement: "XIgpDhDC4Ol",
						value: totalNumberOfClassroomsClean,
					},
					{ dataElement: "XThfmg6f2oC", value: playground },
					{ dataElement: "JzZfwXtdL6G", value: playgroundCondition },
					{ dataElement: "I13NTyLrHBm", value: toiletsForTeachers },
				],
			},
		],
	}),
};

const YesNoField = ({ name, label, children }) => (
	<ReactFinalForm.Field
		name={name}
		component={({ input }) => (
			<div style={{ marginBottom: "16px" }}>
				<label style={{ display: "block", marginBottom: "8px" }}>
					{label}
				</label>
				<div style={{ display: "flex", gap: "16px", padding: "8px 0" }}>
					<label
						style={{
							display: "flex",
							alignItems: "center",
							gap: "8px",
						}}
					>
						<input
							type="radio"
							name={name}
							value="true"
							checked={input.value === true}
							onChange={() => input.onChange(true)}
						/>
						Yes
					</label>
					<label
						style={{
							display: "flex",
							alignItems: "center",
							gap: "8px",
						}}
					>
						<input
							type="radio"
							name={name}
							value="false"
							checked={input.value === false}
							onChange={() => input.onChange(false)}
						/>
						No
					</label>
				</div>
				{input.value === true && (
					<div style={{ marginTop: "8px" }}>{children}</div>
				)}
			</div>
		)}
	/>
);

export function Create(props) {
	const orgUnitId = props.orgUnit;
	const [mutate, { loading, error }] = useDataMutation(dataMutationQuery);
	const { show: showCritical, hide: hideCritical } = useAlert(
		"An error occurred while submitting the form",
		{
			critical: true,
			duration: 15000,
		}
	);
	const { show: showSuccess, hide: hideSuccess } = useAlert(
		"Form submitted successfully",
		{
			success: true,
			duration: 15000,
		}
	);

	function onSubmit(formInput) {
		mutate({
			orgUnit: orgUnitId,
			computerLab: formInput.computerLab,
			computerLabCondition: formInput.computerLabCondition,
			electricitySupply: formInput.electricitySupply,
			electricitySupplyCondition: formInput.electricitySupplyCondition,
			handwashingFacilities: formInput.handwashingFacilities,
			handwashingFacilitiesCondition:
				formInput.handwashingFacilitiesCondition,
			totalNumberOfClassrooms: formInput.totalNumberOfClassrooms,
			totalNumberOfClassroomsClean:
				formInput.totalNumberOfClassroomsClean,
			playground: formInput.playground,
			playgroundCondition: formInput.playgroundCondition,
			toiletsForTeachers: formInput.toiletsForTeachers,
		}).then((result) => {
			if (result.status === "OK") {
				hideCritical();
				showSuccess();
			}
		});
		if (error) {
			console.error("Error during mutation:", error);
			hideSuccess();
			showCritical();
		}
	}

	return (
		<div>
            <header>
            <h1>Create Form</h1>
            <p>Fill out a form for a school inspection:</p>
            </header>
			<div className="form-container">
				<ReactFinalForm.Form
					onSubmit={onSubmit}
					subscription={{ values: true }}
				>
					{({ handleSubmit }) => (
						<form
							onSubmit={handleSubmit}
							autoComplete="off"
							className="form-layout"
						>
							<div style={{ marginBottom: "16px" }}>
								<ReactFinalForm.Field
									name="reportDate"
									component={InputFieldFF}
									label="Report date"
									type="date"
									validate={(value) => {
										if (!value)
											return "This field is required";
										return undefined;
									}}
									placeholder="Select a date"
								/>
							</div>
							<div>
								<YesNoField
									name="computerLab"
									label="Does the school have a computer lab?"
								>
									<ReactFinalForm.Field
										name="computerLabCondition"
										component={InputFieldFF}
										label="The computer lab is in good condition (1-5)"
										type="number"
										min="1"
										max="5"
										placeholder="Enter a number between 1 and 5"
										validate={composeValidators(
											hasValue,
											integer,
											createNumberRange(
												1,
												5,
												"number has to be between 1 and 5."
											)
										)}
									/>
								</YesNoField>

								<YesNoField
									name="electricitySupply"
									label="Does the school have electricity supply?"
								>
									<ReactFinalForm.Field
										name="electricitySupplyCondition"
										component={InputFieldFF}
										label="The electricity supply is in good condition (1-5)"
										type="number"
										min="1"
										max="5"
										placeholder="Enter a number between 1 and 5"
										validate={composeValidators(
											hasValue,
											integer,
											createNumberRange(
												1,
												5,
												"number has to be between 1 and 5."
											)
										)}
									/>
								</YesNoField>

								<YesNoField
									name="handwashingFacilities"
									label="Does the school have handwashing facilities?"
								>
									<ReactFinalForm.Field
										name="handwashingFacilitiesCondition"
										component={InputFieldFF}
										label="The handwashing facilities are in good condition (1-5)"
										type="number"
										min="1"
										max="5"
										placeholder="Enter a number between 1 and 5"
										validate={composeValidators(
											hasValue,
											integer,
											createNumberRange(
												1,
												5,
												"number has to be between 1 and 5."
											)
										)}
									/>
								</YesNoField>

								<YesNoField
									name="playground"
									label="Does the school have a playground?"
								>
									<ReactFinalForm.Field
										name="playgroundCondition"
										component={InputFieldFF}
										label="The playground is in good condition (1-5)"
										type="number"
										min="1"
										max="5"
										placeholder="Enter a number between 1 and 5"
										validate={composeValidators(
											hasValue,
											integer,
											createNumberRange(
												1,
												5,
												"number has to be between 1 and 5."
											)
										)}
									/>
								</YesNoField>

								<div style={{ marginBottom: "16px" }}>
									<ReactFinalForm.Field
										name="toiletsForTeachers"
										component={InputFieldFF}
										label="Total number of toilets for teachers"
										type="number"
										validate={composeValidators(
											integer,
											createNumberRange(
												0,
												99999999,
												"has to be a positive number."
											)
										)}
									/>
								</div>
								<div style={{ marginBottom: "16px" }}>
									<ReactFinalForm.Field
										name="totalNumberOfClassrooms"
										component={InputFieldFF}
										label="Total number of classrooms"
										type="number"
										validate={composeValidators(
											integer,
											createNumberRange(
												0,
												99999999,
												"has to be a positive number."
											)
										)}
									/>
								</div>
								<div style={{ marginBottom: "16px" }}>
									<ReactFinalForm.Field
										name="totalNumberOfClassroomsClean"
										component={InputFieldFF}
										label="Total number of classrooms in good condition"
										type="number"
										validate={composeValidators(
											integer,
											createNumberRange(
												0,
												99999999,
												"has to be a positive number."
											)
										)}
									/>
								</div>

								<Button
									type="submit"
									primary
									disabled={loading}
									style={{ marginBottom: "16px" }}
								>
									Submit
								</Button>
							</div>
						</form>
					)}
				</ReactFinalForm.Form>
			</div>
		</div>
	);
}
