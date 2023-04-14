# SociallyMinded-Backend

This repository contains the backend codes for SociallyMinded

## Backend Tech Stack
- Java EE 8
- MySQL 8.0.31 and Java Persistence API (JPA)


## Database Design
Link to diagram : https://drive.google.com/file/d/1z9gSA7jFjqo1jwGhS31tJ3rkOcxK41Ls/view?usp=sharing

![Untitled Diagram drawio-3](https://user-images.githubusercontent.com/97529863/231953801-f22a8a8f-9b6e-4284-87c7-abe3d2154a1a.png)

## Software Architecture & Design
### Component Based Architecture
The backend is divided into components, each representing a subsytem of our application, with session beans created to implement required business methods 
  - Entity Classes are mapped as tables to the MySQL database using JPA
    - Customer
    - SocialEnterprise
    - OrderRecord
    - Product
    - Review
  - Singleton Session Bean contains a datainit session bean, that persists dummy data to the database upon deployment (to aid in the purposes of the demo)
  - Stateless Session Beans contain business methods to query, create, update and delete their respective entities
    - CustomerSessionBean
    - SocialEnterpriseSessionBean
    - OrderRecordSessionBean
    - ProductSessionBean
    - ReviewSessionBean
  - Local interfaces of Stateless Session Beans expose business methods to the web service 
    - CustomerSessionBeanLocal
    - SocialEnterpriseSessionBeanLocal
    - OrderRecordSessionBeanLocal
    - ProductSessionBeanLocal
    - ReviewSessionBeanLocal
  - Jakarta RESTful Web Services (JAX-RS) expose session bean business methods to the frontend (via GET, PUT, POST, DELETE methods)
 
Bean Validation
  - Enforced at the backend with the help of Java's ValidatorFactory API

## REST API Reference
These are some of the basic REST APIs implemented at the web service layer, to interact with the frontend clients 

Common API Path : http://localhost:8080/SociallyMinded-war/webresources/ [insert subsequent api path]

### Customer

#### GET

`/entity.customer` 
- Returns all customers

`/entity.customer/{id}` 
- Returns customer by ID

`/entity.customer/findCustomerByUsername/{username}` 
- Returns customer by username of customer

`/entity.customer/count` 
- Returns total number of entries in Customer table

#### POST

`/entity.customer` 
- Create a new customer

#### PUT

`/entity.customer/{id}` 
- Update customer details

### Social Enterprise

#### GET
`/entity.socialenterprise`
- Returns all social enterprises

`/entity.socialenterprise/{id}`
- Returns social enterprise by id 

`/entity.socialenterprise/findSocialEnterpriseByName/{enterprise_name}`
- Returns social enterprise by enterprise name

`/entity.socialenterprise/count`
- Returns total number of entries in Social Enterprise Table

#### POST

`/entity.socialenterprise`
- Create a new Social Enterprise

#### PUT

`/entity.socialenterprise/{id}`
- Update social enterprise details

### Product

#### GET

`/entity.product`
- Returns all products

`/entity.product/{id}`
- Returns product by ID

`/entity.product/findProductByName/{product_name}`
- Returns product by name

`/entity.product/findProductsByEnterpriseId/{enterpriseId}`
- Returns products sold by a social enterprise using enterprise ID

`/entity.product/findProductByEnterpriseName/{enterprisename}`
- Returns products sold by a social enterprise using enterprise name

`/entity.product/count`
- Returns total number of entries in Product table

#### POST

`/entity.product`
- Create a new product
- Request JSON params 
  * product : product entity
  * socialEnterpriseId : social enterprise ID

#### PUT
`/entity.product/{id}`
- Update product details
- Request JSON params 
  * product : product entity
  * socialEnterpriseId : social enterprise ID

#### DELETE
`/entity.product/{id}`
- Delete a product by ID

### OrderRecord

#### GET

`/entity.orderrecord`
- Returns all order records

`/entity.orderrecord/{id}`
- Returns order record by id

`/entity.orderrecord/findOrderRecordsByCustomerId/{customerId}`
- Returns order records of a customer using customer ID

`/entity.orderrecord/findOrderRecordsByProductId/{productId}`
- Returns order records of a product using product ID

- `/entity.orderrecord/count`
- Returns total number of entries in Order Records table

#### POST
`/entity.orderrecord`
- Create a new order record
- Request JSON params 
  * record : record entity
  * productId : product ID
  * customerId : customer ID

#### PUT
`/entity.orderrecord/{id}`
- Update order record details
- Request JSON params 
  * record : record entity
  * productId : product ID
  * customerId : customer ID

#### DELETE
`/entity.orderrecord/{id}`
- Delete an order record by ID

### Review

#### GET

`/entity.review`
- Returns all reviews

`/entity.review/{id}`
- Returns review by ID

`/entity.review/findReviewsByProductId/{productId}`
- Returns all reviews of a product using product ID

`/entity.review/findReviewsByCustomerId/{customerId}`
- Returns all reviews made by a customer using customer ID

`/entity.review/count`
- Returns total number of entries in Review table

#### POST

`/entity.review`
- Create a new review
- Request JSON params 
  * review : review entity
  * productId : product ID
  * customerId : customer ID

#### PUT

`/entity.review/{id}`
- Update details of a review
- Request JSON params 
  * review : review entity
  * productId : product ID
  * customerId : customer ID

#### DELETE

`/entity.review/{id}`
- Delete a review by ID
