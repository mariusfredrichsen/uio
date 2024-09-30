import React, { useState } from 'react'
import { useDataQuery } from '@dhis2/app-runtime'
import { Menu, MenuItem, Table, TableHead, TableRow, TableCell, TableBody, TableRowHead, TableCellHead } from '@dhis2/ui'

export function Datasets() {
    const [dataSet, setDataSet] = useState(null)
    
    const request = {
        request0: {
        resource: "/dataSets",
        params: {
            fields: "id,displayName,created",
            paging: "false"
        }
        }
    }


    const DataSetTable = (dataset) => {
        if (dataset == null) {
            return
        }

        return (
            <div style={{width: 'min-content'}}>

            <Table>
                <TableHead>
                    <TableRowHead>
                        <TableCellHead>Display Name</TableCellHead>
                        <TableCellHead>ID</TableCellHead>
                        <TableCellHead>Created</TableCellHead>
                    </TableRowHead>
                </TableHead>
                <TableBody>
                    <TableRow>
                        <TableCell>{dataset.displayName}</TableCell>
                        <TableCell>{dataset.id}</TableCell>
                        <TableCell>{dataset.created}</TableCell>
                    </TableRow>
                </TableBody>
            </Table>
            </div>
        )
    }
    
    const sendRequest = () => {
        const { loading, error, data } = useDataQuery(request)
        if (error) {
            return <span>ERROR: {error.message}</span>
        }

        if (loading) {
            return <span>Loading...</span>
        }

        if (data) {
            console.log("API response:",data.request0.dataSets)
            //To-do: return a component using the data response 
            
            return <Menu>
                    {data.request0.dataSets.map(x => 
                        <MenuItem 
                            key={x.id} 
                            label={x.displayName} 
                            onClick={() => setDataSet(x)}
                        />
                    )}
                </Menu>            
        }
    }   


    return (
        <div>
            <h1>Datasets</h1>
            <div style={{display: 'flex'}}>
                {sendRequest()}
                {DataSetTable(dataSet)}
            </div>
        </div>
    )
}