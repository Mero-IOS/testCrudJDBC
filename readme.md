# Intradoc

## consegna

Sviluppare un programma che si occupa di gestire il database Intradoc e la parte grafica (CLI) che ne permette l'utilizzo.
Tutti i dati dovranno essere letti e scritti su un database (vedi sotto) di conseguenza si richiede l'uso del JDBC template per gestire le operazioni CRUD.
I modelli entity del database andranno sviluppati tenendo conto di tutte le relazioni tra le tabelle, relazioni che vanno implementate solo a livello Java e non a livello di SQL.
Il programma non dovrà mai bloccarsi, tutti i possibili errori dell'utente dovranno essere gestiti in modo tale da non compromettere **MAI** l'usabilità del programma, l'unico caso in cui il programma termina è quando l'utente decide di terminarlo volontariamente.

All'avvio del programma si dovrà interrogare l'utente su quale delle seguenti funzioni vorrà eseguire:

1) "ricerca valore": eseguire una ricerca in base all'input preso dall'utente (la ricerca deve essere effettuata solo sulle seguenti tabelle: clienti, professionisti e lingue)
2) "aggiungi elemento": chiedere all'utente quale elemento aggiungere, i relativi dati e successivamente inserirlo in DB
3) "rimuovi elemento": funziona come "aggiungi elemento" ma dovrà eliminare dal DB (in questo caso bisogna rispettare le foreign key)
4) "modifica elemento": funziona come "aggiungi elemento" ma dovrà modificare l'elemento del DB

Per finire il programma andrà testato attraverso JUnit 5, in particolare si richiede l'implementazione di almeno:

- 5 unit test
- 3 integration test
- 2 E2E test

## database

Per gestire il database si lascia libertà sul DBMS da utilizzare scegliendo tra:

- MYSQL
- H2

Qui di seguito verrà spiegato logicamente come è strutturato il database, questo però non obbliga il programmatore a implementarlo come di seguito in quanto si possono fare ragionamenti differenti basta che in fase di code review vengano motivati.

Legenda:

- '->': indica una possibile foreign key

### clienti

1) id
2) ragione_sociale

### commessa

1) id
2) cliente -> clienti.id
3) sub_descrizione
4) stato -> tbl_stato_commessa.id

### commessa_attivita

1) id
2) id_commessa -> commessa.id
3) lingua_da -> lingua.id
4) lingua_a -> lingua.id
5) data_ora_riconsegna
6) tipo_attivita -> tbl_tipologia_tariffa_s3.id

### tbl_lingue_s1

1) id
2) descrizione

### professionisti

1) id
2) nome
3) congome

### clienti_professionisti_preferiti

1) id
2) id_cliente -> clienti.id
3) id_professionista -> professionisti.id

### tbl_stato_commessa

1) id
2) descrizione
3) colore
4) font_color

### tbl_tipologia_tariffa_s3

1) id
2) descrizione

### professionisti_lingue_attive

1) id
2) id_professionista -> professionisti.id
3) lingua -> lingua.id
