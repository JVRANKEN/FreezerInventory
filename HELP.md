# Getting Started
### Setup
- Create a Firebase account
- Go to project setting > Firebase Admin SDK
  - Generate new private key
  - Place generated private key in resourgit ces folder (see: serviceAccountKey.json)
  - Copy the code from Firebase to the Application startup method
- Create a restcontroller
  - Create
  - Read
  - Update
  - Delete
- Create a service & serviceImpl
- On Firebase, create Firestore Database (this is defined in the final var in serviceImpl)

### Backend testing
- For the tests of the backend, we use swagger
  - http://localhost:8080/swagger-ui/index.html

### Firebase data
- For importing/exporting data through FireBase, billing needs to be setup
- To avoid this, use:
  - Export: GET http://localhost:8080/freezer/internaltesting/search/exportfirebase
  - Import: GET http://localhost:8080/freezer/internaltesting/search/importfirebase

### Setup frontend structure:
- https://www.baeldung.com/spring-boot-angular-web
- 