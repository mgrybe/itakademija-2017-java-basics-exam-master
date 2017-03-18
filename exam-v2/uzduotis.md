# Java Pagrindų Egzaminas

## Užduotis

Realizuoti programą taip, kad ji tenkintų testus apibrėžtus klasėje `lt.itakademija.exam.test.BaseTest`.

## Sprendimas ir pateikimas

1. Išpakavus archyvą viduje bus pateiktas .jar failas, kurį reikia panaudoti savo projekte
   bei javadoc kataloge esantis JavaDoc. Pateiktame jar faile yra automatiškai įtraukta
   JUnit biblioteka, todėl šios bibliotekos jums papildomai įsidėti į projektą nereikia.
2. Jūsų tikslas - išplėsti klasę `lt.itakademija.exam.test.BaseTest` ir įgyvendinti pateiktus abstrakčius
   metodus taip, kad šios klasės testai suveiktų sėkmingai.
3. Išsprendus užduotį supakuoti ją `VPavardernis_exam.zip`. Jei pateikiamas ir javadoc, tuomet: `VPavardernis_exam-javadoc.zip`.

## Vertinimas

Užduotis vertinama pagal kriterijus pateiktus žemiau. Praktinė užduotis sudaro 60% bendro kontrolinio įvertinimo.

### Vertinimo kriterijai

**Funkciniai reikalavimai (padengti testais)**

Programos atitikimas `lt.itakademija.exam.test.BaseTest` klasėje apibrėžtiems testams (5 taškai)

**Kodo kokybė**

|Kriterijus                                                                 |Taškai|
|:--------------------------------------------------------------------------|:---- |
|Aiškus ir tvarkingas kodas                                                 |0.25  |
|JavaDoc aprašytos klasės ir metodai                                        |0.25  |
|Bent du metodai padengti modulių (angl. unit) testais                      |0.25  |
|Programa išveda prasmingus pranešimus į programos eigos žurnalą (angl. log)|0.25  |
|                                                                  **Viso:**|1     |

**Papildomi balai:**

|Kriterijus                                           |Taškai|
|:----------------------------------------------------|:---- |
|Įvykių žurnalas išvedamas į atskirą failą            |0.3   |
|Daugiau nei 80% kodo padengta modulių testais        |0.4   |
|JavaDoc sugeneruotas ir pateiktas papildomame archyve|0.3   |
|                                            **Viso:**|1     |

## Papildoma informacija

Vadovaukitės iš testų gaunama informacija, bei javadoc aprašymu. 

Kiekvieną kartą paleidus testą, konsolėje pamatysite užduoties vykdymo statusą:

```
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ RESULTS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*
* Task: 'Truck manager responds with illegal argument exception when registering truck with zero capacity'   *
*    Grade: '0.1                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Truck manager responds with illegal argument exception when registering package with empty id'      *
*    Grade: '0.1                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Truck manager responds with illegal argument exception when registering truck with negative capacity'*
*    Grade: '0.1                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Truck manager responds with illegal argument exception when registering package with zero capacity' *
*    Grade: '0.1                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Truck manager responds with null pointer exception when registering truck with null id'             *
*    Grade: '0.1                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Truck manager responds with illegal argument exception when registering package with negative capacity'*
*    Grade: '0.1                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Truck manager responds with null pointer exception when registering package with null id'           *
*    Grade: '0.1                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Truck manager responds with illegal argument exception when registering truck with empty id'        *
*    Grade: '0.1                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Truck manager can return all packages assigned to truck'                                            *
*    Grade: '0.3                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Truck manager starts correctly'                                                                     *
*    Grade: '0.3                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Truck manager can return all registered trucks'                                                     *
*    Grade: '0.6                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Truck manager can return a total capacity of all registered trucks'                                 *
*    Grade: '0.6                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Truck manager can return truck by id'                                                               *
*    Grade: '0.6                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Truck manager can register trucks'                                                                  *
*    Grade: '0.6                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Truck manage can register packages'                                                                 *
*    Grade: '0.6                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Truck manager can assign package to truck'                                                          *
*    Grade: '0.6                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
*                                                                                                            *
* SCORE: 5.0, POSSIBLE GRADE: 10.00. TOTAL POINTS: 5.0                                                       *
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*
```


