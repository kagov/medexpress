# Med express 

## Requirements
- Java 17

The project uses Gradle wrapper
- Gradle version : 8.11.1

## Running the application

```
$ ./gradlew bootRun
```

## OpenAPI url
`http://localhost:8080/swagger-ui/index.html`

## Running tests
```
$ ./gradlew test
```

## Assumptions
- The consultation is currently linear and the next question is not decided based on the response to the previous question.

## Additional notes
- I have tried to adhere to SOLID principles by keeping the interfaces and separate and extendable
- Currently only one condition is covered, but it can be extended by proving an implementation for `EligibilityService`
- I have implemented some unit and integration testing, but they can be improved upon by using mocks and covering more invalid scenarios
- I have implemented basic validation to validate some input fields but this is not complete and needs to be improved
- I have not implemented any logging or exception handling mechanisms

## Curl commands

### Get questions
```
curl -X 'GET' \
  'http://localhost:8080/api/consultation/1/questions' \
  -H 'accept: */*'
```

### Submit answers
```
curl -X 'POST' \
  'http://localhost:8080/api/consultation/1/answers' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '[
  {
    "questionId": 1,
    "answer": "yes"
  },
{
    "questionId": 2,
    "answer": "yes"
  },
{
    "questionId": 3,
    "answer": "None"
  }
]'
```
