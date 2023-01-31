Feature: Bundle tracking feature

@BT_001
Scenario: Successful connection to the NLFTK server and tracking of bundle in the server

When User login into db using URL username and password
Then User should be successfully logged in into the server
When User Enters and Runs the query
Then User should get the latest bundleId with the count and BundleRefId
And User pastes the BundleRefId in another query and executes it
Then User will get all the files uploaded towards the bundle

@BT_002
Scenario: Checking the bundle in FPD Monitor

When User enters the url 
When User selects Dlvry Bundle ID and gives the BundleId
And Click on search
Then Check if the status is Successful