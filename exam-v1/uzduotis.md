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
* Task: 'Project tracker responds with illegal argument exception when creating project with empty args'     *
*    Grade: '0.1                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Project tracker responds with null pointer exception when creating project with null args'          *
*    Grade: '0.1                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Project tracker responds with null pointer exception when creating issue with null args'            *
*    Grade: '0.1                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Project tracker responds with illegal argument exception when creating issue with empty args'       *
*    Grade: '0.1                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Project tracker starts correctly'                                                                   *
*    Grade: '0.3                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Project tracker can return all created projects'                                                    *
*    Grade: '0.3                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Project tracker can return project by id'                                                           *
*    Grade: '0.6                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Project tracker allows to retrieve issues in the project'                                           *
*    Grade: '0.6                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Project tracker can create projects'                                                                *
*    Grade: '0.6                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Project tracker allows to create issues in the project'                                             *
*    Grade: '0.6                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Project tracker allows to create issues in the project by project id'                               *
*    Grade: '0.7                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Project tracker allows to retrieve issues in the project by project id'                             *
*    Grade: '0.9                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
*                                                                                                            *
* SCORE: 5.0, POSSIBLE GRADE: 10.00. TOTAL POINTS: 5.0                                                        *
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*
```


