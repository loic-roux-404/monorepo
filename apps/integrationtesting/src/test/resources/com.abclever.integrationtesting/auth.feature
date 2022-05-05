Feature: auth

  Background: Accounting Data
    Given non registered users
      | id | name      | password   | picture | email        | emailVerified | birthdate  | updatedAt  | createdAt  | familyName | gender |
      | 1  | Christian | 10/12/1976 | ""      | toto@foo.com | toto@foo.com  | 2007-01-01 | 2022-02-11 | 2022-02-11 | Jojo       | 0      |
      | 2  | Toto      | 10/12/1976 | ""      | tata@foo.com | tata@foo.com  | 2000-09-01 | 2022-02-11 | 2022-02-11 | Jaja       | 1      |
      | 3  | Max       | 10/12/1996 | ""      | titi@fii.com | titi@fii.com  | 2005-03-27 | 2022-02-11 | 2022-02-11 | Jiji       | 1      |
      | 4  | Lando     | 10/12/2005 | ""      | jose@faa.com | jose@faa.com  | 1992-02-11 | 2022-02-11 | 2022-02-11 | Dupont     | 0      |

  @auth
  Scenario: auth service
    #When login with user 1
    Given start auth test env
    Then app auth on route / status is 200
