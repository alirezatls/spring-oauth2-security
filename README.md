--------------------------------------------------------------
How to get a Access Token from AuthorizationServer?

POST http://localhost:8080/oauth/token

Content-Type: application/x-www-form-urlencoded
Authorization: Basic Y2xpZW50OnNlY3JldA==
username=jon&password=snow&grant_type=password
--------------------------------------------------------------

--------------------------------------------------------------
How to check token?

POST http://localhost:8080/oauth/check_token
Content-Type: application/x-www-form-urlencoded
token=b591770d-d290-426a-a9e5-a825be0ade2d
--------------------------------------------------------------
