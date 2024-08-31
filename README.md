Base URL:https://opensource-demo.orangehrmlive.com/web/index.php/auth/login

Test cases
**********

1 Invalid Login
----------------
. Open the OrangeHRM login page.
. Enter invalid credentials.
. Click the Login button.
. Verify that an error message is displayed indicating invalid credentials.


2 Login Test Cases
-------------------
. Open the OrangeHRM login page.
. Enter valid credentials.
. Click the Login button.
. Verify successful login by checking the dashboard page title.

3  Add Employee Test case:
---------------------------
. Open the OrangeHRM login page.
. Navigate to the "PIM" menu and select "Add Employee".
. Fill in the required details(Eg. first name last name ..).
. Click the "save" button.
. Verify that the employee is successfully added by checking the employee list for "Personal Details".

4 Search Employee by Name Test Case:
------------------------------------
. Open the OrangeHRM login page.
. Navigate to the "PIM" menu and select "Employee list".
. Enter search criteria (Employee name).
. Click the Search button.
. Verify the record is found.
. Logout user.

5 Search Employee by ID Test Case:
------------------------------------
. Open the OrangeHRM login page.
. Navigate to the "PIM" menu and select "Employee list".
. Enter search criteria (Employee id).
. Click the Search button.
. Verify the record is found.
. Logout user.


