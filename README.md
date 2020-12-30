# Reusable-Component-Repository-Backend
Reusable Component Repository Backend, exposing a RESTful api for accessing and managing a component repository.
Tech : Spring Boot, GCP, Spring Security

## Built With

* [Spring Boot](https://spring.io/guides/gs/spring-boot/) - The framework used for RESTful api
* [Spring Security](https://spring.io/projects/spring-security) - The framework used for security
* [Maven](https://maven.apache.org/) - Dependency Management
* [GCP Cloud SQL](https://cloud.google.com/sql) - Database Management

## RESTful API
The backend exposes a restful api for managing the cloud repository.
The backend api is protected with authentication by token using spring security and JWT.
The backend api's main resource are components which can be fetched using a get api and posted to database using post api.

The Api provided is listed below :

• Add User: /register/adduser

Method : post with new user details

![Add User](/Api/registerUser.JPG?raw=true "Add User")

• Authenticate User: /authenticate

Method : post with username and password in body

![Authenticate User](/Api/authenticate.JPG?raw=true "Authenticate User")

• Upload Component:

public: /api/rcl/upload/component/public

Method : post , with component details in form data, requires
valid JWT in header

![Upload Component Publicly](/Api/uploadPublic.JPG?raw=true "Upload Component Publicly")

private: /api/rcl/upload/component/private

Method : post with component details in form data, requires
valid JWT in header

![Upload Component Privately](/Api/uploadPrivate.JPG?raw=true "Upload Component Privately")

• Get All Components : /api/rcl/download/component/pub-
lic/all

Method : get

public: /api/rcl/download/component/public

Method : get, requires a valid JWT responds with all public
components posted by this User

![Get Public Component](/Api/getpub.JPG?raw=true "Get Public Components")

private: /api/rcl/downpload/component/private

Method : get, requires valid JWT in header responds with all
public components posted by this User

![Get Private Components](/Api/getprivate.JPG?raw=true "Get Private Components")

• Search Apis :

Facet Search and Attribute integrated search :

Public Component : /api/rcl/download/component/public/-
facet

Method : get method, with details of component to be searched
with facets in form data

![Search Public Component by Facets](/Api/getPubFac.JPG?raw=true "Search Public Component by Facets")

Private by user : /api/rcl/download/component/private/facet

Method : get method, with details of component to be searched
with facets in form data

![Search Private Component by Facets](/Api/getPriFac.JPG?raw=true "Search Private Component by Facets")

• Attribute Search :

Public Component : /api/rcl/download/component/public/at-
tribute

Method : get method, with details of component to be searched
with attributes in form data

![Get Public Component by Attributes](/Api/getPubAtt.JPG?raw=true "Get Public Component by Attributes")

Private By User : /api/rcl/download/component/private/at-
tribute

Method : get method, with details of component to be searched
with attributes in form data, requires valid JWT in header

![Get Private Component by Attributes](/Api/getPriAtt.JPG?raw=true "Get Private Component by Attributes")
