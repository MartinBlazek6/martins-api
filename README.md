
# Martins Resume API

The Martins Resume API demonstrates my expertise in Spring Boot, RESTful API development, and backend data management. It offers efficient, database-backed storage and retrieval of resume-related information, including professional experience, education, personal details, and skills. Robust security and documentation are integral components, making it an ideal showcase of my skills in API development for real-world applications.
## API Reference

#### Get Martins resume in JSON

  ```http
  GET /api/forRecruiter/martinsResume
```


#### Get all resumes in JSON

  ```http
  GET /api/forRecruiter/martinsResume
```
#### Sign Up in to API
| Header       | Type    | Description                                  |
| :----------- | :------ | :------------------------------------------- |
| Authorization | Header  | **Required**. The header format should be "Bearer {token}", where `{token}` represents the authentication token for authorization. 





  ```http
  POST /api/registerNewUser
```

| Parameter         | Type     | Description                             |
| :---------------- | :------- | :-------------------------------------- |
| - (in request body) | JSON  | **Required**. JSON data containing the following fields:
  - `userName` (string): User's username.
  - `userFirstName` (string): User's first name.
  - `userLastName` (string): User's last name.
  - `userPassword` (string): User's password. 

#### Provide authentication and receive JWT

  ```http
  POST /api/authenticate
```

| Parameter         | Type     | Description                             |
| :---------------- | :------- | :-------------------------------------- |
| - (in request body) | JSON  | **Required**. JSON data containing the following fields:
  - `userName` (string): User's username.
  - `userPassword` (string): User's password. 


