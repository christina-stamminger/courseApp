# CourseApp API
The CourseApp is a RESTful API for a course booking system, including user management, managing courses and bookings of users.

# Description
Built in Spring Boot, the CourseApp allows users to perform CRUD operations on users and courses. Users can also book and cancel bookings for different courses. Courses have a limit on the maximum number of users they can accommodate.
An in-memory database (Apache derby) is used for storing user data, course data and booking data in respectively a table.
They are mapped using a oneToMany relation (one user can book many courses, one course can have many users) and manyToOne relation (many bookings belong to one user or many bookings belong to one course).

# Functionalities
## User Management
Users can be created by sending a POST request to the respective endpoint.
Users can be retrieved by id or all by sending a GET request to the respective endpoint.
Users can be updated by sending a PUT request to the respective endpoint.
Users can also be deleted by sending a DELETE request to the respective endpoint.

## Course Management
Courses can be created by sending a POST request to the respective endpoint (see table).
Courses can be retrieved by id or all by sending a GET request to the respective endpoint.
Courses can be updated by sending a PUT request to the respective endpoint.
Only available courses (free user places) can be retrieved by sending a GET to the respective endpoint.
Courses can also be deleted by sending a DELETE request to the respective endpoint.

## Booking Management
Bookings can be created by sending a POST request to the respective endpoint.
Bookings can be retrieved by id or all be sending a GET request to the respective endpoint.
Bookings can be deleted by sending a DELETE request to the respective endpoint.
(see table for all endpoints)

# Installation /Dependencies
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Apache Derby
- Apache Commons Lang3
- Hibernate Validator

# Endpoints

## User
| Method | Endpoint       | Description       |
|--------|----------------|-------------------|
| POST   | /api/user      | Create a new user |
| GET    | /api/user/     | Get all users     |
| GET    | /api/user/{id} | Get user by ID    |
| PUT    | /api/user      | Update user by ID |
| DELETE | /api/user/{id} | Delete user by ID |


## Course
| Method | Endpoint              | Description                          |
|--------|-----------------------|--------------------------------------|
| POST   | /api/course/create    | Create a new course                  |
| GET    | /api/course/all       | Get all courses                      |
| GET    | /api/course/available | Get all courses with free user place |
| GET    | /api/course/{id}      | Get course by ID                     |
| PUT    | /api/course           | Update course                        |
| DELETE | /api/course/{id}      | Delete course by ID                  |


## Booking
| Method | Endpoint          | Description          |
|--------|-------------------|----------------------|
| POST   | /api/booking      | Create a new booking |
| GET    | /api/booking/     | Get all bookings     |
| GET    | /api/booking/{id} | Get booking by ID    |
| DELETE | /api/booking/{id} | Delete booking by ID |