import { useState, useEffect } from "react";
import {
	CircularLoader,
	Table,
	TableBody,
	TableCell,
	TableCellHead,
	TableHead,
	TableRow,
	TableRowHead,
	Input,
} from "@dhis2/ui";

import { useDataQuery } from "@dhis2/app-runtime";

const orgUnitsQuery = (orgUnitId, dataSetId) => {
	return {
		dataValueSets: {
			resource: "dataValueSets",
			params: {
				orgUnit: orgUnitId,
				dataSet: dataSetId,
				lastUpdated: "2020-01-01T12:00:00.000",
			},
		},
		dataSets: {
			resource: "dataSets/" + dataSetId,

			params: {
				fields: "dataSetElements[dataElement[id,displayName]]",
			},
		},
	};
};

function mergeData(data) {
	return data.dataSets.dataSetElements.map((d) => {
		let matchedValue = data.dataValueSets.dataValues.find((dataValues) => {
			if (dataValues.dataElement == d.dataElement.id) {
				return true;
			}
		});

		return {
			displayName: d.dataElement.displayName,
			id: d.dataElement.id,
			value: matchedValue ? matchedValue.value : null,
		};
	});
}

const RecentResources = (props) => {
	const {
		loading: resourcesLoading,
		error: resourcesError,
		data: resourcesData,
	} = useDataQuery(orgUnitsQuery(props.orgUnitId, props.dataSetId));

	if (resourcesError) {
		return (
			<h5>
				User does not have persmission to view this organisation unit.
			</h5>
		);
	}

	if (resourcesLoading) {
		return (
			<div
				style={{
					display: "flex",
					justifyContent: "center",
					width: "100%",
				}}
			>
				<CircularLoader large />
			</div>
		);
	}

	if (resourcesData) {
		return (
			<ResourceTable
				mergedData={mergeData(resourcesData)}
				dataSetName={props.dataSetName}
			/>
		);
	}
};

const ResourceTable = ({ mergedData, dataSetName }) => {
	const dataArray = mergedData;

	return (
		<div>
			<h3>{dataSetName}</h3>
			<Table>
				<TableHead>
					<TableRowHead>
						<TableCellHead>Display Name</TableCellHead>
						<TableCellHead>Previous Value</TableCellHead>
						<TableCellHead>Current Value</TableCellHead>
						<TableCellHead>Status</TableCellHead>
					</TableRowHead>
				</TableHead>
				<TableBody>
					{dataArray
						.sort((a, b) =>
							a.displayName.localeCompare(b.displayName)
						)
						.map((row) => {
							const [inputValue, setInputState] = useState(
								parseInt(row.value == null ? 0 : row.value)
							);
							const [statusText, setStatusText] = useState("a");

							useEffect(() => {
								setStatusText(() => {
									if (inputValue < row.value) {
										return "Shortage detected";
									} else if (inputValue > row.value) {
										return "Increase in resource";
									}
									return "Condition is stable";
								});
							}, [inputValue]);

							return (
								<TableRow key={row.id}>
									<TableCell>{row.displayName}</TableCell>
									<TableCell>
										{row.value ?? "No data"}
									</TableCell>
									<div style={{ display: "flex" }}>
										<Input
											type="number"
											onChange={(event) => {
												const parsedValue = parseInt(
													event.value
												);
												setInputState(
													isNaN(parsedValue)
														? 0
														: parsedValue
												);
											}}
											value={inputValue}
										/>
									</div>
									<TableCell>{statusText}</TableCell>
								</TableRow>
							);
						})}
				</TableBody>
			</Table>
		</div>
	);
};

export function Recount(props) {
	return (
		<div>
			<RecentResources
				orgUnitId={props.orgUnitData.orgUnitId}
				orgUnitName={props.orgUnitData.orgUnitName}
				dataSetId={props.dataSetData[0].id}
				dataSetName={props.dataSetData[0].name}
			/>
		</div>
	);
}
