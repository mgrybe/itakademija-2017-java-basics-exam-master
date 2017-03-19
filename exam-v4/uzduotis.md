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

Užduotis vertinama pagal kriterijus pateiktus žemiau.

### Vertinimo kriterijai

**Funkciniai reikalavimai (padengti testais)**

Programos atitikimas `lt.itakademija.exam.test.BaseTest` klasėje apibrėžtiems testams (9 balai)

**Kodo kokybė**

|Kriterijus                                                                 |Balai |
|:--------------------------------------------------------------------------|:---- |
|Aiškus ir tvarkingas kodas                                                 |0.25  |
|JavaDoc aprašytos klasės ir metodai                                        |0.25  |
|Bent du metodai padengti modulių (angl. unit) testais                      |0.25  |
|Programa išveda prasmingus pranešimus į programos eigos žurnalą (angl. log)|0.25  |
|                                                                  **Viso:**|1     |

**Papildomi balai:**

|Kriterijus                                           |Balai |
|:----------------------------------------------------|:---- |
|Įvykių žurnalas išvedamas į atskirą failą            |0.3   |
|Daugiau nei 80% kodo padengta modulių testais        |0.4   |
|JavaDoc sugeneruotas ir pateiktas papildomame archyve|0.3   |
|                                            **Viso:**|1     |

## Papildoma informacija

Vadovaukitės iš testų gaunama informacija, bei javadoc aprašymu.

Kiekvieną kartą paleidus testą, konsolėje pamatysite užduoties įvykdymo būseną:

```
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ RESULTS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*
* Task: 'Throws account create exception if non existent customer passed'                                    *
*    Grade: '0.25                                                                                            *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Throws insufficient funds exception if debit account lacks money'                                   *
*    Grade: '0.25                                                                                            *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Creates currency converter'                                                                         *
*    Grade: '0.25                                                                                            *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Throws customer create exception if existing person code provided'                                  *
*    Grade: '0.25                                                                                            *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Throws null pointer exception if creating account with null args'                                   *
*    Grade: '0.25                                                                                            *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Throws null pointer exception if creating customer with null args'                                  *
*    Grade: '0.25                                                                                            *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Throws currency conversion exception if unable to convert currency pair'                            *
*    Grade: '0.25                                                                                            *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Creates bank correctly'                                                                             *
*    Grade: '0.5                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Converts currency correctly'                                                                        *
*    Grade: '0.75                                                                                            *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Creates account'                                                                                    *
*    Grade: '1.0                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Creates customers correctly'                                                                        *
*    Grade: '1.0                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Creates payment correctly'                                                                          *
*    Grade: '2.0                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
* Task: 'Counts total balance in euros correctly'                                                            *
*    Grade: '2.0                                                                                             *
*    Status: PASSED                                                                                          *
*                                                                                                            *
*                                                                                                            *
* SCORE: 9.00/9.00                                                                                           *
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*
```


