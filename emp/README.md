# Software Architecture Design 2021 - Midterm Exam

## 9th March 2021

## Features

1. Employee List for admin only. List contains net salary calculated with the given formula.
2. Edit/Delete employees by admin.
3. Create user and employee by admin.
4. View own user profile.
5. Create/edit/delete addresses. Users cannot delete address if only one left.
6. Edit birthday by employee.
7. Normal users cannot access admin dashboard and create user page.
8. Most error pages are still plain trace pages. No fancy user error messages.

## Models

1. `User`
2. `Employee` - uses builder pattern (see create api). Has several transient values for `Monetary` base and net salaries.
3. `Role`
4. `Address`
5. `Level` (Enum) - has method to return bonus salary (in `MonetaryAmount`)

## Services

1. `AddressService` - only used for updating addresses by employee - there has to be a better way than the if-conditions here. They check if param from form view is empty.
2. `AdminService` - only used for updating employee by admin (similar to `AddressSerevice`)
3. `EmployeeService` - handles the calculation of net salary according to employee level. Bonus value is obtained from `Level` enum method.
4. `UserService` - handles saving of new users by the admin. On user persistence, employee persists also.

## Mock Users

1. **(Admin)** `username` - admin, `password` - password
2. **(User)** `username` - user, `password` - password
3. **(User)** `username` - user2, `password` - password
