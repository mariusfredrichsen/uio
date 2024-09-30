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
import { useDataMutation } from "@dhis2/app-runtime";

const dataMutationQuery = {
  resource: 'dataValueSets',
  type: 'create',
  dataSet: 'aLpVgfXiz0f',
  data: ({ value, dataElement, period, orgUnit }) => ({
      dataValues: [
          {
              dataElement: dataElement,
              period: period,
              orgUnit: orgUnit,
              value: value,
          },
      ],
  }),
}


export function Insert(props) {
    const [mutate, { loading, error }] = useDataMutation(dataMutationQuery)

    function onSubmit(formInput) {
        mutate({
            value: formInput.value,
            dataElement: formInput.dataElement,
            period: '2020',
            orgUnit: 'KiheEgvUZ0i',
        })
    }

    return (
      <div>
        <h1>Insert</h1>
          <ReactFinalForm.Form onSubmit={onSubmit}>
              {({ handleSubmit }) => (
                  <form onSubmit={handleSubmit} autoComplete="off">
                      <ReactFinalForm.Field
                          component={SingleSelectFieldFF}
                          name="dataElement"
                          label="Select field"
                          initialValue="WUg3MYWQ7pt"
                          options={[
                              {
                                  label: 'Total Population',
                                  value: 'WUg3MYWQ7pt',
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
