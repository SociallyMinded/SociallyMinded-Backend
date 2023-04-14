# SociallyMinded-Backend

This repository contains the backend codes for SociallyMinded

## Backend Tech Stack
- Java EE 8
- MySQL 8.0.31 and Java Persistence API (JPA)


## Database Design
Link to diagram : https://drive.google.com/file/d/1z9gSA7jFjqo1jwGhS31tJ3rkOcxK41Ls/view?usp=sharing

![Untitled Diagram drawio-3](https://user-images.githubusercontent.com/97529863/231953801-f22a8a8f-9b6e-4284-87c7-abe3d2154a1a.png)

## Software Architecture & Design
Component Based Architecture
  - Entity Classes represent tables in our data model (mapped to their respective tables in the MySQL database using JPA)
  - Stateless Session Beans contain business methods to query, create, update and delete their respective entities
  - Stateless Session Beans contain interfaces to expose their business methods to the web service 
  - Jakarta RESTful Web Services (JAX-RS) expose session bean business methods to the frontend (via GET, PUT, POST, DELETE methods)
 
REST APIs 
- Take reference to data input templates under `model` folder
- Implemented in the respective REST files for each entity under `service` folder

Bean Validation
  - Enforced at the backend with the help of Java's ValidatorFactory API
