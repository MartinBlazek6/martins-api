
# Martins Resume API

The Martins Resume API demonstrates my expertise in Spring Boot, RESTful API development, and backend data management. It offers efficient, database-backed storage and retrieval of resume-related information, including professional experience, education, personal details, and skills. Robust security and documentation are integral components, making it an ideal showcase of my skills in API development for real-world applications.
## API Reference

---

#### Get Martins resume in JSON

  ```http
  GET /api/forRecruiter/martinsResume
```
---

#### Get all resumes in JSON

  ```http
  GET /api/forRecruiter/martinsResume
```
#### Sign Up in to API
| Header       | Type    | Description                                  |
| :----------- | :------ | :------------------------------------------- |
| Authorization | Header  | **Required**. The header format should be "Bearer {token}", where `{token}` represents the authentication token for authorization. 


---
#### Sign in as User

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

---

#### Sign in as Admin



| Parameter                  | Type     | Description                                                   |
|:---------------------------| :------- |:--------------------------------------------------------------|
| - (in request body)        | JSON  | **Required**. JSON data containing the following fields:      
| apiKey (in request header) | String  | **Required**. API key that authorizing you create an Amin user |
- `userName` (string): User's username.
- `userFirstName` (string): User's first name.
- `userLastName` (string): User's last name.
- `userPassword` (string): User's password.


---

#### Provide authentication and receive JWT

  ```http
  POST /api/authenticate
```

| Parameter         | Type     | Description                             |
| :---------------- | :------- | :-------------------------------------- |
| - (in request body) | JSON  | **Required**. JSON data containing the following fields:
  - `userName` (string): User's username.
  - `userPassword` (string): User's password. 

---

#### Save resume

  ```http
  POST /api/saveResume
```

| Parameter         | Type     | Description                             |
| :---------------- | :------- | :-------------------------------------- |
| - (in request body) | JSON  | **Required**. JSON data containing the following fields: |


**Request Body:**

- `resume` (object): Resume information.
    - `sourceCodeOfThisProject` (string): URL to the source code of this project.
    - `gdpr` (string): GDPR consent for personal data processing.
    - `profession` (string): Profession or job title.
- `personalDetails` (object): Personal details.
    - `name` (string): Name of the individual.
    - `email` (string): Email address.
    - `phoneNumber` (string): Phone number.
    - `location` (string): Location information.
- `employments` (array): List of employment history.
    - `projectName` (string): Name of the project or job.
    - `duration` (string): Duration of employment.
    - `position` (string): Job position or title.
    - `technologies` (string): List of technologies used.
- `educations` (array): List of educational history.
    - `description` (string): Description of the educational institution or program.
    - `duration` (string): Duration of education.
    - `name` (string): Name of the educational institution.
- `hardSkills` (array): List of hard skills.
    - `technology` (string): Technology or skill.
    - `level` (string, enum): Skill level (e.g., "BEGINNER," "INTERMEDIATE," "ADVANCED," "EXPERT").
- `softSkills` (array): List of soft skills.
    - `skill` (string): Soft skill.

**Request Body Example:**
```json
{
  "resume": {
    "sourceCodeOfThisProject": "https://github.com/MartinBlazek6/martins-api",
    "gdpr": "I consent to the processing of my personal data for the purpose of recruitment for the position to which I am applying.",
    "profession": "Back end developer",
  },
  "personalDetails": {
    "name": "Martin Blazek",
    "email": "martinblazek6@gmail.com",
    "phoneNumber": "0951013849",
    "location": "Martin (Slovakia)"
  },
  "employments": [
    {
      "projectName": "CHG healthcarete",
      "duration": "06/2022 - Present",
      "position": "Backend Developer",
      "technologies": "-Java 17 -PL SQL -JUnit 5 -Spring Boot 2.7 -VSC (GitHub) -SQL -Docker -Kafka -Vue.js"
    },
    {
      "projectName": "CHG healthcarete",
      "duration": "11/2022 - 06/2023",
      "position": "Software developer in test",
      "technologies": "-Serenity BDD -Java 17 -JUnit 5 -VSC (GitHub) -SQL -Docker -Kafka -JavaScript"
    },
    {
      "projectName": "Software Development Academy",
      "duration": "02/2022 - Present",
      "position": "Java Trainer",
      "technologies": "-Spring Boot 2.7 -Hibernate -Java 17 -REST -Mockito/MockMVC -SQL -CI/CD -Thymeleaf/React/SVELTE"
    },
    {
      "projectName": "Thermo Fisher scientific",
      "duration": "08/2021 - 11/2022",
      "position": "QA Automation Engineer",
      "technologies": "-RobotFramework -Python 2.7 -VSC (GitLab) -VMware"
    },
    {
      "projectName": "ComAp",
      "duration": "08/2021 - 11/2022",
      "position": "QA Automation Engineer",
      "technologies": "-TestComplete -Python 2.7 -VSC (GitLab)"
    }
  ],
  "educations": [
    {
      "description": "Faculty of Applied Informatics",
      "duration": "09/2017 - 06/2022",
      "name": "Tomas Bata University in Zlín, Zlín"
    },
    {
      "description": "Focused to Applied Informatics and Biology",
      "duration": "09/2007 - 06/2015",
      "name": "Mikulas Galanda Gymnasium"
    }
  ],
  "hardSkills": [
    {
      "technology": "Java",
      "level": "ADVANCED"
    },
    {
      "technology": "Typescript",
      "level": "INTERMEDIATE"
    },
    {
      "technology": "OOP",
      "level": "ADVANCED"
    },
    {
      "technology": "Spring Boot",
      "level": "ADVANCED"
    },
    {
      "technology": "REST",
      "level": "ADVANCED"
    },
    {
      "technology": "SQL",
      "level": "EXPERT"
    },
    {
      "technology": "PL SQL",
      "level": "INTERMEDIATE"
    },
    {
      "technology": "Docker",
      "level": "INTERMEDIATE"
    },
    {
      "technology": "React",
      "level": "INTERMEDIATE"
    },
    {
      "technology": "Vue",
      "level": "INTERMEDIATE"
    },
    {
      "technology": "Next",
      "level": "ADVANCED"
    },
    {
      "technology": "Hibernate",
      "level": "ADVANCED"
    },
    {
      "technology": "GIT",
      "level": "EXPERT"
    }
  ],
  "softSkills": [
    {
      "skill": "Can work under pressure"
    },
    {
      "skill": "Team player"
    },
    {
      "skill": "Pro active"
    }
  ]
}
```

---

#### Save Martins resume

  ```http
  POST /api/saveMartinsResume
```


| Parameter         | Type     | Description                             |
| :---------------- | :------- | :-------------------------------------- |
| - (in request body) | JSON  | JSON data containing the following fields. |
| Authorization (in request header) | String  | **Required**. A token or credentials for authentication. Include it in the request header. You must have the 'Admin' role. |


---