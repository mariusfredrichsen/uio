
# Using analytics
BASE_URL/api/analytics.json?dimension=dx:kIjrYORLGSx&dimension=pe:2020&dimension=ou:IT3x07NwCxd

### The dimensions:
- ou = OrganisationUnit ID
- pe = Period, 2020 (the whole year) or 202001 (january 2020)
- dx = DataElement ID

### How to find OrganisationUnit ID
- BASE_URL/api/programs/UxK2o06ScIe/organisationUnits
- this uri gives a list of organisation units inside of the school inspection program

### How to find DataElement ID
- inside of `BASE_URL/api/organisationUnits/IT3x07NwCxd` there should be a `dataSet` value containing a list of dataSet IDs the school is "connected" to
- pick one of these dataSet IDs and find the dataElement IDs with: `BASE_URL/api/dataSets/Abp17dyKIx0`
    - for example: kIjrYORLGSx <-- used in

