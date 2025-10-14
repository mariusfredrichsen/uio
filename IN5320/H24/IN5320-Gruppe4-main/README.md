# IN5320-Gruppe4


## The Apps Functionality

  #### 1. Creating a form
Upon choosing a school, the user will be greeted with the option to fill out a form. By submitting the form, the application stores and sends data through a post request to /events. The user is able to fill out the form containing broad questions about the school facilities and their condition. - This is done by mutating the data from the input fields and sending them to api/events throguh a post request.

  #### 2. Recount of previously recorded data
This feature lets the user view the schools' currently stored datasets and their previously recorded values within each dataset. The user is able to switly recapture and view new against previous recorded data and are informed about a decrease or increase in resources tied to each value. <br><br> The datasets that are shown are implemented to be the datasets that are tied to the organisation unit's dataValues set upon a get request. The program then initiates a get request from dataValueSets/dataSet, where dataSet is the current chosen dataset. Ideally we would want to submit the data to replace the previous recorded data, but struggeled finding the way to post request this.

  #### 3. Request resources from nearby schools
This feature allows the user to identify nearby schools with surplus resources. By calculating resource ratios and surpluses, the app provides an overview of potential donors. School managers can then initiate resource requests, specifying the type and quantity of resources needed.<br><br>
Here it would be ideal to also have some more information about the schools like what type of school it is (Primary, Lower Secondary, etc.). This is so its easier to know what type of resources they need and dont need to manually search for this. We cant also just request everything just incase they dont have any data on it, so it must be secured by doing it manually.<br><br>
There should also be a easier way to filter on a specific orgUnits neighbours (parents children) and if their in the school inspection program.<br><br>
One last thing to add is that its hard to use the useDataQuery with dhis analytic api since it has a weird format where you need to write dimension=dx:...&dimension=ou:...&dimension=pe:... manually rather than dimension: ["dx:...", "ou:...", "pe:..."].
