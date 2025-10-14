import React, { useState } from "react";
import {
	Modal,
	ModalTitle,
	ModalContent,
	ModalActions,
	ButtonStrip,
	Button,
	InputField,
	Table,
	TableBody,
	TableCell,
	TableCellHead,
	TableRow,
	TableRowHead,
} from "@dhis2/ui";

export const RequestModal = (props) => {
	const [showConfirm, setShowConfirm] = useState(false);

	const mappings = [
		{ learnerId: "ue3QIMOAC7G", bookId: "FaAsIdQC3GM" },
		{ learnerId: "FwBwE2Gw30d", bookId: "j7FjvpR3C30" },
		{ learnerId: "eZkEN060HSv", bookId: "nTDHc2Jdipp" },
		{ learnerId: "wbyxtgvDs5N", bookId: "HlqFCACW3G7" },
	];

	const learnerIds = mappings.map((mapping) => mapping.learnerId);
	const bookIds = mappings.map((mapping) => mapping.bookId);


	const bookIdToLearnerId = mappings.reduce((acc, { learnerId, bookId }) => {
		acc[bookId] = learnerId;
		return acc;
	}, {});

	const combinedData = props.neighbourResources.reduce((result, orgUnit) => {
		const {
			id: orgUnitId,
			displayName: orgUnitDisplayName,
			resources,
		} = orgUnit;
		const resource = resources[0];
		const { id, displayName, value, period } = resource;

		if (!learnerIds.includes(id)) {
			if (bookIds.includes(id)) {
				const learnerResource = props.neighbourResources.find(
					({ id: orgId, resources: res }) =>
						orgId === orgUnitId &&
						res[0].id === bookIdToLearnerId[id]
				).resources[0];

				const percentage =
					((value - learnerResource.value) / learnerResource.value) *
					100;

				result.push({
					orgUnitId,
					orgUnitDisplayName,
					resourceId: id,
					resourceDisplayName: displayName,
					resourceValue: value,
					learnerId: learnerResource.id,
					learnerDisplayName: learnerResource.displayName,
					learnerValue: learnerResource.value,
					period,
					percentage,
					leftOver: value - learnerResource.value,
				});
			} else {
				result.push({
					orgUnitId,
					orgUnitDisplayName,
					resourceId: id,
					resourceDisplayName: displayName,
					resourceValue: value,
					period,
					percentage: 0,
					leftOver: resource.value,
				});
			}
		}
		return result;
	}, []);


	function changeLeftover(learnerKey, value, leftover) {
		const updatedLeftover = { ...leftover };
		updatedLeftover[learnerKey] = {
			number: leftover[learnerKey].number - value,
		};
		return updatedLeftover;
	}

	let leftover = props.learners;
	props.resources.resources.forEach((resource) => {
		if (bookIds.includes(resource.id)) {
			const learnerId = bookIdToLearnerId[resource.id];
			const learnerKey = getLearnerKey(learnerId);
			const value = resource.value;
			leftover = changeLeftover(learnerKey, value, leftover);
		}
	});

	// Function to map learnerId to learnerKey
	function getLearnerKey(learnerId) {
		const learnerTypeMap = {
			ue3QIMOAC7G: "p",
			FwBwE2Gw30d: "ls",
			eZkEN060HSv: "us",
			wbyxtgvDs5N: "t",
		};
		return learnerTypeMap[learnerId];
	}

	const [inputValues, setInputValues] = useState(() => {
		let initialValues = {};
		combinedData
			.filter((item) => item.percentage >= 0)
			.sort((a, b) => b.percentage - a.percentage)
			.forEach((item) => {
				let value = 0;
				const resourceId = item.resourceId;
				if (bookIds.includes(resourceId)) {
					const learnerKey = getLearnerKey(
						bookIdToLearnerId[resourceId]
					);
					const requiredBooks = leftover[learnerKey].number;

					value =
						requiredBooks > item.leftOver
							? item.leftOver
							: requiredBooks;

					leftover = changeLeftover(learnerKey, value, leftover);
				}

				initialValues[`${item.orgUnitId}-${item.resourceId}`] = value;
			});
		return initialValues;
	});


    return (
        <div
            style={{
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                height: "100vh",
            }}
        >
            {showConfirm && (
                <Modal hide={!showConfirm} small>
                    <ModalTitle>Do you want to confirm request?</ModalTitle>
                    <ModalActions>
                        <ButtonStrip center>
                            <Button onClick={() => setShowConfirm(false)} secondary>
                                Cancel
                            </Button>
                            <Button
                                onClick={() => {
                                    props.setHide(true);
                                    setShowConfirm(false);
                                    /*
                                        POST REQUEST HERE
                                        UNCLEAR WHAT TO DO
                                    */
                                }}
                                primary
                            >
                                Confirm
                            </Button>
                        </ButtonStrip>
                    </ModalActions>
                </Modal>
            )}
            <Modal hide={props.hide} large>
                <header>
                    <ModalTitle>Request Manager</ModalTitle>
                    <p>
                        Select an amount of resources from neighbouring schools
                        (There a preselected values)
                    </p>
                </header>
                <ModalContent>
                    <div
                        style={{
                            display: "flex",
                            flexDirection: "row",
                            gap: "8px",
                        }}
                    >
                        <div style={{ flex: 6 }}>
                            <Table>
                                <TableRowHead>
                                    <TableCellHead>School</TableCellHead>
                                    <TableCellHead>Resource</TableCellHead>
                                    <TableCellHead>
                                        Requested Amount
                                    </TableCellHead>
                                </TableRowHead>
                                <TableBody>
                                    {combinedData
                                        .filter((item) => item.percentage >= 0)
                                        .sort(
                                            (a, b) =>
                                                b.percentage - a.percentage
                                        )
                                        .map((item) => {
                                            let value = 0;
                                            const resourceId = item.resourceId;
                                            if (bookIds.includes(resourceId)) {
                                                const bookId = resourceId;
                                                const learnerTypeMap = {
                                                    ue3QIMOAC7G: "p",
                                                    FwBwE2Gw30d: "ls",
                                                    eZkEN060HSv: "us",
                                                    wbyxtgvDs5N: "t",
                                                };
                                                const learnerId =
                                                    bookIdToLearnerId[bookId];
                                                const learnerKey =
                                                    learnerTypeMap[learnerId];
                                                const requiredBooks =
                                                    leftover[learnerKey].number;

                                                value =
                                                    requiredBooks >
                                                    item.leftOver
                                                        ? item.leftOver
                                                        : requiredBooks;

                                                leftover = changeLeftover(
                                                    learnerKey,
                                                    value,
                                                    leftover
                                                );
                                            }

                                            return (
                                                <TableRow
                                                    key={`${item.orgUnitId}-${item.resourceId}`}
                                                >
                                                    <TableCell>
                                                        {
                                                            item.orgUnitDisplayName
                                                        }
                                                    </TableCell>
                                                    <TableCell>
                                                        {
                                                            item.resourceDisplayName
                                                        }
                                                    </TableCell>
                                                    <TableCell>
                                                        <InputField
                                                            name={`${item.orgUnitId}-${item.resourceId}`}
                                                            type="number"
                                                            value={
                                                                inputValues[
                                                                    `${item.orgUnitId}-${item.resourceId}`
                                                                ]
                                                            }
                                                            onChange={(v) => {
                                                                setInputValues({
                                                                    ...inputValues,
                                                                    [`${item.orgUnitId}-${item.resourceId}`]:
                                                                        v.value,
                                                                });
                                                            }}
                                                            min="0"
                                                            max={item.leftOver}
                                                        />
                                                    </TableCell>
                                                </TableRow>
                                            );
                                        })}
                                </TableBody>
                            </Table>
                        </div>
                        <div style={{ flex: 4 }}>
                            <Table>
                                <TableRowHead>
                                    <TableCellHead>
                                        Requested Items
                                    </TableCellHead>
                                    <TableCellHead>Amount</TableCellHead>
                                </TableRowHead>
                                <TableBody>
                                    {Object.entries(inputValues)
                                        .filter(([key, value]) => value > 0)
                                        .map(([key, value]) => {
                                            const [orgUnitId, resourceId] =
                                                key.split("-");
                                            const item = combinedData.find(
                                                (data) =>
                                                    data.orgUnitId ===
                                                        orgUnitId &&
                                                    data.resourceId ===
                                                        resourceId
                                            );
                                            const displayName = item
                                                ? `${item.orgUnitDisplayName} - ${item.resourceDisplayName}`
                                                : key;
                                            return (
                                                <TableRow key={key}>
                                                    <TableCell>
                                                        {displayName}
                                                    </TableCell>
                                                    <TableCell>
                                                        {value}
                                                    </TableCell>
                                                </TableRow>
                                            );
                                        })}
                                </TableBody>
                            </Table>
                        </div>
                    </div>
                </ModalContent>
                <ModalActions>
                    <ButtonStrip end>
                        <Button onClick={() => props.setHide(true)} secondary>
                            Cancel
                        </Button>
                        <Button
                            onClick={() => {
                                setShowConfirm(true);
                            }}
                            primary
                        >
                            Send Request
                        </Button>
                    </ButtonStrip>
                </ModalActions>
            </Modal>
        </div>
    );
};
