import React, { useState } from "react";
import { CircularLoader, Transfer, Button } from "@dhis2/ui";
import { useDataQuery } from "@dhis2/app-runtime";
import { RequestModal } from "./RequestModal";
import classes from "./Request.module.css";

const neighbourResourceQuery = (orgUnit, orgUnits, schoolType) => {
	const dataIds = Object.keys(schoolType)
		.filter((key) => schoolType[key].selected)
		.map((key) => {
			switch (key) {
				case "p":
					return "FaAsIdQC3GM;ue3QIMOAC7G"; // hardcoded pairs of books and learners
				case "ls":
					return "j7FjvpR3C30;FwBwE2Gw30d";
				case "us":
					return "nTDHc2Jdipp;eZkEN060HSv";
				case "t":
					return "HlqFCACW3G7;wbyxtgvDs5N";
				default:
					return "";
			}
		})
		.join(";");

	return {
		resources: {
			resource:
				"analytics.json?" +
				`dimension=dx:${dataIds};Cm2qphUpUuS` +
				"&dimension=pe:2020" +
				`&dimension=ou:${orgUnit}`,
		},
		neighbourResources: {
			resource:
				"analytics.json?" +
				`dimension=dx:${dataIds};Cm2qphUpUuS` + // books + computer ids
				"&dimension=pe:2020" +
				`&dimension=ou:${orgUnits.join(";")}`,
		},
	};

	// orgUnits: {
	// 	resource: "organisationUnits",
	// 	params: {
	// 		filter: ["leaf:eq:true", "programs.id:eq:UxK2o06ScIe"],
	// 		fields: ["displayName", "id"],
	// 		paging: false,
	// 	},
	// },

	// dataElements: {
	// 	resource: "dataElements",
	// 	params: {
	// 		fields: ["id", "displayName"],
	// 		paging: false,
	// 	},
	// },
};

const convertData = (nrData) => {
	const { metaData, rows } = nrData.resources;
	const orgUnit = metaData.dimensions.ou[0];

	const mapRows = (rows, items) =>
		rows.map((row) => ({
			id: row[0],
			displayName: items[row[0]].name,
			value: row[3],
			period: row[1],
		}));

	return {
		requestedResources: metaData.dimensions.dx.map((dataId) => ({
			id: dataId,
			displayName: metaData.items[dataId].name,
		})),
		orgUnitResources: {
			id: orgUnit,
			displayName: metaData.items[orgUnit].name,
			resources: mapRows(rows, metaData.items),
		},
		neighbourResources: nrData.neighbourResources.rows.map((row) => ({
			id: row[2],
			displayName: nrData.neighbourResources.metaData.items[row[2]].name,
			resources: mapRows([row], metaData.items),
		})),
	};
};

export const NeighbourResources = (props) => {
	const [hide, setHide] = useState(true);

	const {
		loading: resourcesLoading,
		error: resourcesError,
		data: resourcesData,
	} = useDataQuery(
		neighbourResourceQuery(
			props.selectedOrgUnit,
			props.neighbours.map((orgUnit) => {
				return orgUnit.id;
			}),
			props.schoolType
		)
	);

	if (resourcesLoading) {
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

	if (resourcesError) {
		return <p>{resourcesError.message}</p>;
	}

	const mapIdToRequiredValue = (id) => {
		switch (id) {
			case "FaAsIdQC3GM": // primary books
				return props.learners["p"].number;
			case "j7FjvpR3C30": // lower secondary books
				return props.learners["ls"].number;
			case "nTDHc2Jdipp": // upper secondary books
				return props.learners["us"].number;
			case "HlqFCACW3G7": // tertiary books
				return props.learners["t"].number;
			default:
				return 0;
		}
	};

	const handleTransfer = (payload) => {
		props.setSelected(payload.selected);
	};

	if (resourcesData) {
		const convertedData = convertData(resourcesData);

		const renderOption = ({
			label,
			value,
			highlighted,
			selected,
			disabled,
			onClick,
			onDoubleClick,
			key,
		}) => (
			<p
				onClick={(event) =>
					!disabled ? onClick({ label, value }, event) : {}
				}
				onDoubleClick={(event) =>
					!disabled ? onDoubleClick({ label, value }, event) : {}
				}
				className={`${classes.transferOption} ${
					highlighted ? classes.highlighted : ""
				} ${disabled ? classes.disabled : ""}`}
			>
				<span>{label}</span>
				<span>
					{(
						convertedData.orgUnitResources.resources.find(
							(resource) => resource.id == value
						) || {}
					).value || 0}{" "}
					/ {mapIdToRequiredValue(key)}
				</span>
			</p>
		);

		const learnerIds = [
			"ue3QIMOAC7G",
			"FwBwE2Gw30d",
			"eZkEN060HSv",
			"wbyxtgvDs5N",
		];

		const options = [
			...convertedData.orgUnitResources.resources
				.filter((resource) => {
					return (
						!learnerIds.includes(resource.id) &&
						resource.value <= mapIdToRequiredValue(resource.id)
					);
				})
				.map((resource) => {
					return {
						key: resource.id,
						label: resource.displayName,
						value: resource.id,
					};
				}),
		];

		return (
			<div
				style={{ display: "flex", flexDirection: "column", gap: "8px" }}
			>
				{!hide && (
					<RequestModal
						setHide={setHide}
						neighbourResources={convertedData.neighbourResources}
						selected={props.selected}
						learners={props.learners}
						resources={convertedData.orgUnitResources}
					/>
				)}
				<Transfer
					options={options}
					selected={props.selected}
					onChange={handleTransfer}
					leftHeader={<h4>Selected School Resources</h4>}
					rightHeader={<h4>Selected Resources</h4>}
					renderOption={renderOption}
					height="350px"
					optionsWidth="400px"
					sourceEmptyPlaceholder={
						<p style={{ textAlign: "center" }}>
							No missing resources.
						</p>
					}
				/>
				<Button
					style={{ width: "200px", height: "36px", fontSize: "14px", margin: "8px"}}
					primary
					onClick={() => {
						setHide(false);
					}}
					disabled={options.length == 0}
				>
					Manage Request
				</Button>
			</div>
		);
	}
};
