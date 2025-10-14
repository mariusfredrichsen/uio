import React, { useState } from "react";
import { useDataQuery } from "@dhis2/app-runtime";
import { Form as ReactFinalForm, Field } from "react-final-form";
import { SingleSelectOption, SingleSelectField } from "@dhis2/ui";
import { CircularLoader, Button } from "@dhis2/ui";

const orgUnitsQuery = {
	orgUnits: {
		resource: "organisationUnits/",
		params: {
			paging: false,
			filter: ["programs.id:eq:UxK2o06ScIe", "level:eq:5"],
			fields: ["name", "id"],
		},
	},
};

export function ChooseOrgUnit(props) {
	const {
		loading: orgUnitsLoading,
		error: orgUnitsError,
		data: orgUnitsData,
	} = useDataQuery(orgUnitsQuery);

	if (orgUnitsError) {
		return <span>ERROR: {orgUnitsError.message}</span>;
	}
	if (orgUnitsLoading) {
		return (
			<div
				style={{
					display: "flex",
					justifyContent: "center",
					width: "100%",
				}}
			>
				<CircularLoader />
			</div>
		);
	}
	if (orgUnitsData) {
		return (
			<OrgUnitField
				orgUnitsData={orgUnitsData}
				setChosenOrgUnit={props.setChosenOrgUnit}
				setActivePage={props.setActivePage}
			/>
		);
	}
}

function OrgUnitField(props) {
	const [selectedOrgUnit, setSelectedOrgUnit] = useState(null);
	const orgUnitsData = props.orgUnitsData;

	return (
		<div
			style={{
				display: "flex",
				justifyContent: "center",
				alignItems: "center",
				height: "50vh",
			}}
		>
			<ReactFinalForm
				onSubmit={() => {
					props.setChosenOrgUnit(
						orgUnitsData.orgUnits.organisationUnits.find(
							(orgUnit) => {
								return orgUnit.id == selectedOrgUnit;
							}
						)
					); // SET SELECTED ORG UNIT ID
					props.setActivePage("Create"); // GO TO CREATE SITE
				}}
				initialValues={{
					school: selectedOrgUnit,
					name: "nothing",
				}}
			>
				{({ handleSubmit }) => (
					<form
						onSubmit={handleSubmit}
						style={{
							display: "flex",
							flexDirection: "column",
							gap: "10px",
							width: "50%",
						}}
					>
						<Field name="school">
							{({ input }) => (
								<SingleSelectField
									{...input}
									label="Selected Organisation Unit"
									required
									filterable
									selected={input.value}
									onChange={({ selected }) => {
										setSelectedOrgUnit(selected);
									}}
								>
									{orgUnitsData.orgUnits.organisationUnits.map(
										(orgUnit) => (
											<SingleSelectOption
												key={orgUnit.id}
												label={orgUnit.name}
												value={orgUnit.id}
											/>
										)
									)}
								</SingleSelectField>
							)}
						</Field>
						<Button
							type="submit"
							primary
							disabled={
								props.learners == 0 ||
								props.selectedOrgUnit == ""
							}
						>
							Select
						</Button>
					</form>
				)}
			</ReactFinalForm>
		</div>
	);
}
