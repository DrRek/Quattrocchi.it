-- Carattere di escape '\r\n', i null sono indicati con '\N', la data del sistema deve essere in formato yyyy-MM-dd per usare i csv con Excel.
-- luca mac //Users//luca//Desktop//ProgettoPW//sql//csv//
-- luca desktop C:\\Users\\Luca\\Desktop\\ProgettoPW\\sql\\csv\\

use quattrocchiDB;
LOAD DATA LOCAL INFILE '//Users//luca//Desktop//ProgettoPW//sql//csv//Amministratore.csv'
	INTO TABLE Amministratore
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'
	();
LOAD DATA LOCAL INFILE '//Users//luca//Desktop//ProgettoPW//sql//csv//GestoreOrdini.csv'
	INTO TABLE GestoreOrdini
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'
	();
LOAD DATA LOCAL INFILE '//Users//luca//Desktop//ProgettoPW//sql//csv//IndirizzoSpedizione.csv'
	INTO TABLE IndirizzoSpedizione
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'
	();
LOAD DATA LOCAL INFILE '//Users//luca//Desktop//ProgettoPW//sql//csv//CartaCredito.csv'
	INTO TABLE CartaCredito
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'
	();
LOAD DATA LOCAL INFILE '//Users//luca//Desktop//ProgettoPW//sql//csv//Ordine.csv'
	INTO TABLE Ordine
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'
	();
LOAD DATA LOCAL INFILE '//Users//luca//Desktop//ProgettoPW//sql//csv//ArticoloInOrder.csv'
	INTO TABLE ArticoloInOrder
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'
	();
LOAD DATA LOCAL INFILE '//Users//luca//Desktop//ProgettoPW//sql//csv//ArticoloInStock.csv'
	INTO TABLE ArticoloInStock
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'
	();
LOAD DATA LOCAL INFILE '//Users//luca//Desktop//ProgettoPW//sql//csv//ArticoloInCarello.csv'
	INTO TABLE ArticoloInCarello
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'
	();
