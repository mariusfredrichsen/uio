import {
  ReactFinalForm,
  InputFieldFF,
  SingleSelectFieldFF,
  SwitchFieldFF,
  composeValidators,
  createEqualTo,
  email,
  hasValue,
  Button,
  integer,
  string,
} from '@dhis2/ui'
import React from 'react'
import './MyForm.css' 


const alertValues = (values) => {
    console.log("amount of chairs per student: " + values.chairs)
    console.log("amount of teachers per student: " + values.teachers)
    console.log("amount of books per student: " + values.books)
    console.log(values)

    const formattedValuesString = JSON.stringify(values, null, 2)
    alert(formattedValuesString)

    // FROM GEMINI
    const baseUrl = 'https://your-dhis2-instance';
    const username = 'in5320';
    const password = 'P1@tform';

    const dataMutationQuery = {
        resource: 'events',
        type: 'create',
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

    const eventData = {
        program: "UxK2o06ScIe",
        orgUnit: "oXTcmBQ3JjJ",
        programStage: "eJiBjm9Rl7E",
        eventDate: "2024-11-11",
        dataValues: [
            {
            dataElement: "dataElementId1",
            value: "value1"
            },
            // ... other data values
        ]
    };
    
    const auth = 'Basic ' + btoa(`${username}:${password}`);

    fetch(`${baseUrl}/api/tracker/events`, {
    method: 'POST',
    headers: {
        'Authorization': auth,
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(eventData)
    })
    .then(response => {
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    return response.json();
    })
    .then(data => {
    console.log('Event created successfully:', data);
    })
    .catch(error => {
    console.error('Error creating event:', error);  

    });
}


const validators = [];
for (const field in ["string1", "string2", "string3"]) {
  validators.push(createEqualTo(field));
}


export const MyForm = () => (
  <div className={styles.container}>
      <h1>Form</h1>

      <ReactFinalForm.Form onSubmit={alertValues}>
          {({ handleSubmit }) => (
              <form onSubmit={handleSubmit}>
                  <div className={styles.row}>
                      <ReactFinalForm.Field
                          name="title"
                          label="Title"
                          component={SingleSelectFieldFF}
                          className={styles.title}
                          initialValue="inspector"
                          options={[
                              { label: 'School Inspector', value: 'inspector' },
                              { label: 'Teacher', value: 'teacher' },
                              { label: 'Other', value: 'other' },
                          ]}
                      />
                  </div>

                  <div className={styles.row}>
                      <ReactFinalForm.Field
                          required
                          name="school"
                          label="School name"
                          component={InputFieldFF}
                          className={styles.email}
                          validate={composeValidators(
                            hasValue,
                        )}
                      />
                  </div>

                  <div className={styles.row}>
                      <ReactFinalForm.Field
                          required
                          name="chairs"
                          label="Amount of chairs per student"
                          component={InputFieldFF}
                          initialValue="0"
                          className={styles.surname}
                          validate={composeValidators(hasValue, integer)}
                      />

                      <ReactFinalForm.Field
                          required
                          name="books"
                          label="Amount of books per student"
                          component={InputFieldFF}
                          initialValue="0"
                          className={styles.firstname}
                          validate={composeValidators(hasValue, integer)}
                      />

                      <ReactFinalForm.Field
                          required
                          name="teachers"
                          label="Amount of teachers per subject"
                          component={InputFieldFF}
                          initialValue="0"
                          className={styles.firstname}
                          validate={composeValidators(hasValue, integer)}
                      />
                  </div>

                  <div className={styles.row}>
                      <ReactFinalForm.Field
                          type="checkbox"
                          name="newsletter"
                          label="I want to receive the newsletter"
                          component={SwitchFieldFF}
                          className={styles.newsletters}
                          initialValue={false}
                      />
                  </div>

                  <div className={styles.row}>
                      <Button type="submit" primary>
                          Submit form
                      </Button>
                  </div>
              </form>
          )}
      </ReactFinalForm.Form>
  </div>
)