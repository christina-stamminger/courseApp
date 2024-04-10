# CourseApp API
The CourseApp is a RESTful API for a course booking system, including user management, managing courses and bookings of users.

# Description
Written in Spring Boot users as well as courses can be created, get, updated and deleted.
Users can book different courses and again cancel them.
A course can only have a maximum number of users.
An in-memory database (derby) is used for storing user data, course data and booking data in respectively a table.
They are mapped using a oneToMany relation (one user can book many courses, one course can have many users) and manyToOne relation (many bookings belong to one user or many bookings belong to one course).

# Functionalities

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