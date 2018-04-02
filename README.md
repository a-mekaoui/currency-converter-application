# Currency converter application
[![Circle CI](https://circleci.com/gh/a-mekaoui/currency-converter-application.svg?style=svg&circle-token=4aef8bd23970e13ff5dee6f4a843a898985619ef)](https://circleci.com/gh/a-mekaoui/currency-converter-application)
This application essentially allows to convert some currencies.
The conversion can be done in two ways: either by querying the latest conversion rate or by choosing a date so as to have an overview of the conversion to an earlier date.

The technologies used to develop the application are as following:
- Java 8
- apache maven (3.5.2)
- JSR-303 (Bean Validation)
- Spring data JPA
- Spring boot 2
- Spring security
- Thymeleaf & bootstrap
- Spring actuator (Micrometer)
- Tests
-- Mockito
-- MockMVC
-- HTMLUnit
-- Cucumber for acceptance tests
- git
- Maven 3
- H2 database engine
- liquibase (database versioning)
- Spring cache

For continuous integration [Circle ci](https://circleci.com/gh/a-mekaoui/challenge) is used and you can find a live demo of the currency converter application [here](https://currency-converter-appli.herokuapp.com)

# Getting started

Clone or download source code
There are many ways to run the currency converter application. Following are some of them:

- Make an mvn clean install
Navigate to the target folder and type the following: 
```java -jar currency-converter-application-0.0.1-SNAPSHOT.jar```

- Or from the source folder, type the following:
```mvn spring-boot:run``` 

Once the previous steps followed:

 1. on your browser, enter the following link: [currency converter application](http://localhost:8080)
you will be in that case redirected to the following [localhost:8080/login](http://localhost:8080/login)
 2. you need to register to be able to use the currency converter application, in that case, click on the **Sign up** button
 3. enter the required fields (those are mandatory)
 4. once registered, log in with your **Email address** and the previously chosen **password**
 5. enter the amount you want to convert, your actual currency, the target currency you wish to convert to. Leaving the historical date will convert the desired amount and your actual currency using the latest exchange rate. If you want to have an overview about an historical exchange rate, fill the historical date. Finally click on the **Convert Button**
 6. Once you did a successful conversion, it will be added to your history and will be displayed next time you log in to the currency converter application. (that table will retain only the 10 latest conversions that you made)
In order to see some metrics (you need to be logged in), navigate to [http://localhost:8080/actuator/metrics](http://localhost:8080/actuator/metrics). 

Here are some of them:

- "currency.controller.converter.login.failure", "currency.controller.converter.login.success", "currency.converter.registration.success", "currency.converter.registration.failure"
- For example, to have an overview about "currency.controller.converter.login.failure", navigate to [http://localhost:8080/actuator/metrics/currency.controller.converter.login.success](http://localhost:8080/actuator/metrics/currency.controller.converter.login.success)

# Some tips during development
In order to access the h2-console from your browser:

- Alter the SecurityConfiguration.java in **configure** method and add the following ```.and().authorizeRequests().antMatchers("/h2-console/**").permitAll()``` and add at the end ```httpSecurity.headers().frameOptions().disable();```
- in application.properties, add ```spring.h2.console.enabled=true```

If you need to run the embedded tomcat server with another port than 8080, add the following in the application.properties ```server.port=9090``` or whatever port you want.

For sake of ease during development, add the following to the liquibase yml file  (db.changelog-master.yaml) and pay attention to the indentation. The password is testtest: 
```yaml
- changeSet:  
 id: 3  
    author: amekaoui  
    changes:  
  - insert:  
 tableName: USER_CREDENTIAL  
          columns:  
  - column:  
 name: PASSWORD  
                value: $2a$10$fx3DJpvpCo2QtrNMv5Rkp.H9FCToWzKDUgyCsy1Ugi9AmmM/U4doS  
            - column:  
 name: EMAIL  
                value: test@test.com  
            - column:  
 name: AUTHORITY  
                value: ROLE_USER  
            - column:  
 name: BIRTH_DATE  
                value: "1980-01-01"  
  - column:  
 name: STREET  
                value: test  
            - column:  
 name: ZIP_CODE  
                value: test  
            - column:  
 name: CITY  
                value: test  
            - column:  
 name: COUNTRY  
                value: BELGIUM 
```


Finally, for information, this application queries [openexchangerates.org](openexchangerates.org). In case you want to use the currency conversion application source code, just go to the dashboard, click on the left menu item [Subscription Plan](https://openexchangerates.org/account/subscription) and choose Developer then click on Change Plan. In that case you will be able to query with different base currencies for some time. After that time, your plan will be downgraded but you can reuse the same subscription plan by doing what explained before.

Enjoy the currency converter application and feel free to leave comments :-)
