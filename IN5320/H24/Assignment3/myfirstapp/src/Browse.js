import React from 'react'
import { useDataQuery } from '@dhis2/app-runtime'
import { CircularLoader, Table, TableHead, TableRow, TableCell, TableBody, TableRowHead, TableCellHead } from '@dhis2/ui'

const dataQuery = {
    dataSets: {
        resource: 'dataSets/aLpVgfXiz0f',
        params: {
            fields: [
                'name',
                'id',
                'dataSetElements[dataElement[id, displayName]',
            ],
        },
    },
    dataValueSets: {
        resource: 'dataValueSets',
        params: {
            orgUnit: 'KiheEgvUZ0i',
            dataSet: 'aLpVgfXiz0f',
            period: '2020',
        },
    },
}


function mergeData(data) {
  return data.dataSets.dataSetElements.map(d => {
      let matchedValue = data.dataValueSets.dataValues.find(dataValues => {
          if (dataValues.dataElement == d.dataElement.id) {
              return true
          }
      })

      return {
          displayName: d.dataElement.displayName,
          id: d.dataElement.id,
          value: matchedValue.value,
      }
  })
}

function displayData(dataQuery) {
  const { loading, error, data } = useDataQuery(dataQuery)

  if (error) {
    return <span>ERROR: {error.message}</span>
  }

  if (loading) {
    return <CircularLoader aria-label="Default Loader" />
  }

  if (data) {
    console.log(mergeData(data))
    return (
      <Table>
        <TableHead>
          <TableRowHead>
            <TableCellHead>Display Name</TableCellHead>
            <TableCellHead>Value</TableCellHead>
            <TableCellHead>ID</TableCellHead>
          </TableRowHead>
        </TableHead>
        <TableBody>
          {mergeData(data).map(dataValueSet => 
            <TableRow key={dataValueSet.id}>
              <TableCell>{dataValueSet.displayName}</TableCell>
              <TableCell>{dataValueSet.value}</TableCell>
              <TableCell>{dataValueSet.id}</TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>
    )
  }
}



export function Browse() {
    return <div>
        <h1>Browse</h1>
        {displayData(dataQuery)}
      </div>
}
