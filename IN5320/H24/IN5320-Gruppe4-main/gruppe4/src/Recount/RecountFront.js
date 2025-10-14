import { useState } from "react";
import { Button, CircularLoader } from "@dhis2/ui";
import { Recount } from "./Recount";
import { useDataQuery } from "@dhis2/app-runtime";

const dataSetsQuery = (orgUnitId) => {
	return {
		organistaionUnit: {
			resource: "organisationUnits/" + orgUnitId,
			params: {
				fields: ["dataSets[id, name]"],
			},
		},
	};
};

function DataSetBoxes(props) {
	const [selectedDataSetList, setSelectedDataSet] = useState([false, null]);

	const handleClick = (dataSet) => {
		setSelectedDataSet([false, dataSet]);
		setTimeout(() => {
			setSelectedDataSet([true, dataSet]);
		}, 10);
	};

	return (
		<div>
			<div style={{ display: "flex", paddingBottom: "10px" }}>
				{props.dataSets.length === 0 ? (
					<h3>No datasets found for this organisation unit</h3>
				) : (
					props.dataSets.map((row) => (
						<div key={row.id} style={{ paddingRight: "20px" }}>
							<Button onClick={() => handleClick(row)}>
								{row.name}
							</Button>
						</div>
					))
				)}
			</div>
			{selectedDataSetList[0] && (
				<Recount
					orgUnitData={props.orgUnitData}
					dataSetData={[selectedDataSetList[1]]}
				/>
			)}
		</div>
	);
}

function ChooseDataSet(props) {
	const {
		loading: dataSetsLoading,
		error: dataSetsError,
		data: dataSetsData,
	} = useDataQuery(dataSetsQuery(props.orgUnitId));

	if (dataSetsError) {
		return <span>ERROR: {dataSetsError.message}</span>;
	}
	if (dataSetsLoading) {
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
	if (dataSetsData) {
		return (
			<DataSetBoxes
				dataSets={dataSetsData.organistaionUnit.dataSets}
				orgUnitData={props}
			/>
		);
	}
}

export function RecountFront(props) {
	return (
		<div>
			<header>
				<h1>Recount Resources</h1>
				<p>Choose a dataset to recount:</p>
			</header>
			<ChooseDataSet orgUnitId={props.orgUnit} />
		</div>
	);
}
