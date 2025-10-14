import React from 'react'
import {
    ReactFinalForm,
    InputFieldFF,
    Button,
    SingleSelectFieldFF,
    hasValue,
    number,
    composeValidators,
} from '@dhis2/ui'

import { useDataMutation } from '@dhis2/app-runtime'

const dataMutationQuery = { 
    resource: 'events',
    type: 'create',
    data: ({ value, dataElement, period, orgUnit }) => ({
        events: [
            {
            program: "UxK2o06ScIe",
            programStage: "eJiBjm9Rl7E",
            orgUnit: orgUnit,
            eventDate: "2024-11-13",
            status: "COMPLETED",
            dataValues: [
                {
                    dataElement: "ya5SyA5hej4",
                    value: value
                },
            ],
            }   
        ]
    }),
}
/*{

  "events": [

    {

      "program": "program_uid",

      "programStage": "program_stage_uid",

      "orgUnit": "org_unit_uid",

      "eventDate": "2024-10-14",

      "status": "COMPLETED",

      "dataValues": [

        {

          "dataElement": "data_element_uid",

          "value": "some_value"

        }

      ]

    }

  ]

}*/

export function Insert(props) {
    const [mutate, { loading, error }] = useDataMutation(dataMutationQuery)

    function onSubmit(formInput) {
        console.log(formInput)
        mutate({
            value: formInput.value,
            dataElement: formInput.dataElement,
            period: '2024',
            orgUnit: 'ws9bNzMJD4Y',
        })
    }

    return (
        <div>
            <ReactFinalForm.Form onSubmit={onSubmit}>
                {({ handleSubmit }) => (
                    <form onSubmit={handleSubmit} autoComplete="off">
                        <ReactFinalForm.Field
                            component={SingleSelectFieldFF}
                            name="dataElement"
                            label="Select field"
                            initialValue="ya5SyA5hej4"
                            options={[
                                {
                                    label: 'CHK number of classrooms',
                                    value: 'ya5SyA5hej4',
                                },
                                {
                                    label: 'Population of women of child bearing age (WRA)',
                                    value: 'vg6pdjObxsm',
                                },
                                {
                                    label: 'Total population < 5 years  ',
                                    value: 'DTtCy7Nx5jH',
                                },
                                {
                                    label: 'Expected pregnancies',
                                    value: 'h0xKKjijTdI',
                                },
                                {
                                    label: 'Total population < 1 year   ',
                                    value: 'DTVRnCGamkV',
                                },
                            ]}
                        />
                        <ReactFinalForm.Field
                            name="value"
                            label="Value"
                            component={InputFieldFF}
                            validate={composeValidators(hasValue, number)}
                        />
                        <Button type="submit" primary>
                            Submit
                        </Button>
                    </form>
                )}
            </ReactFinalForm.Form>
        </div>
    )
}
