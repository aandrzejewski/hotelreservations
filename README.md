# hotelreservations

This is a sample hotel rooms reservation/usage/income calculation service. It is built with Maven 3, Java 11 and Spring boot 2.6.3 All steps
below assume you have installed maven and java sdk in correct versions

# Building the app

To build the project, go to `hotel-reservations` directory and execute

```shell
mvn clean install
```

# Running the app

To run the project, go to `hotel-reservations` directory and execute

```shell
mvn spring-boot:run
```

This will build the app if necessary, and run it. Application will be available on port `8080`

# Using the application

To test the application you can use `curl` tool and the command line, or `postman` tool.

This project contains a postman collection which can be imported and contains sample requests to register potential payments from guests
using `register guest payments` request, and sample request to calculate room usage using `get calculations` request.

If you prefer to use `curl` you can find sample commands below.

Registration of guest payments:

```shell
curl --location --request PUT 'http://localhost:8080/reservations' \
--header 'Content-Type: application/json' \
--data-raw '{
    "potentialPayments":[23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
}'
```

Calculating room usage and profit:

```shell
curl --location --request GET 'http://localhost:8080/room-usage?premiumRooms=3&economyRooms=3'
```
