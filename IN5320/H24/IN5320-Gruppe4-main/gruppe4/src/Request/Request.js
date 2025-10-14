import React, { useState, useEffect } from "react";
import { useDataQuery } from "@dhis2/app-runtime";
import { SelectSchoolType } from "./SelectSchoolType";
import { NeighbourResources } from "./NeighbourResources";
import { useAlert } from "@dhis2/app-runtime";

const neighbourQuery = {
	neighbourUnits: {
		resource: "organisationUnits",
		id: ({ id }) => id,
		params: {
			fields: ["parent[children[displayName,id,programs, leaf]]"],
		},
	},
};

const filterNeighbours = (neighbourData) => {
	if (neighbourData) {
		return neighbourData.neighbourUnits.parent.children
			.filter(
				(orgUnit) =>
					orgUnit.programs.some(
						(program) => program.id === "UxK2o06ScIe"
					) && orgUnit.leaf
			)
			.map((orgUnit) => {
				return {
					id: orgUnit.id,
					displayName: orgUnit.displayName,
				};
			});
	}

	return [];
};

export function Request(props) {
	const { show, hide } = useAlert("Please fill in for all checked points", {
		duration: 10000,
	});

	const [learners, setLearners] = useState({
		p: {
			number: 0,
		},
		ls: {
			number: 0,
		},
		us: {
			number: 0,
		},
		t: {
			number: 0,
		},
	});

	const [schoolType, setSchoolType] = useState({
		p: {
			selected: false,
			name: "Primary",
		},
		ls: {
			selected: false,
			name: "Lower Secondary",
		},
		us: {
			selected: false,
			name: "Upper Secondary",
		},
		t: {
			selected: false,
			name: "Tertiary",
		},
	});

	// Selected resources
	const [selected, setSelected] = useState();

	// Resources
	const [isCheckingResources, setIsCheckingResources] = useState(false);

	function checkResources() {
		setIsCheckingResources(true);
	}

	/*
	NEIGHBOURS
	*/

	const [neighbours, setNeighbours] = useState([]);

	const {
		loading: neighboursLoading,
		error: neighboursError,
		data: neighboursData,
		refetch: neighboursRefetch,
	} = useDataQuery(neighbourQuery, {
		variables: {
			id: props.orgUnit,
		},
	});

	useEffect(() => {
		if (props.orgUnit) {
			neighboursRefetch({
				id: props.orgUnit,
			}).then((value) => {
				const neighboursId = filterNeighbours(value);
				setNeighbours(neighboursId);
			});
		}
	}, [props.orgUnit]);

	return (
		<div style={{ width: "100%", height: "100%" }}>
			<header>
				<h1>Request Resources</h1>
				<p>
					Select school type and amount of learners per type:
				</p>
			</header>

			{isCheckingResources ? (
				neighbours.length > 0 ? (
					<NeighbourResources
						selectedOrgUnit={props.orgUnit}
						neighbours={neighbours}
						selected={selected}
						setSelected={setSelected}
						learners={learners}
						schoolType={schoolType}
					/>
				) : (
					<p>No neighbours found.</p>
				)
			) : (
				<SelectSchoolType
					selectedOrgUnit={props.orgUnit}
					learners={learners}
					setLearners={setLearners}
					schoolType={schoolType}
					setSchoolType={setSchoolType}
					checkResources={checkResources}
					show={show}
					hide={hide}
				/>
			)}
		</div>
	);
}
