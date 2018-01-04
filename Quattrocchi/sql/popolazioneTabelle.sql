-- Carattere di escape '\r\n', i null sono indicati con '\N', la data del sistema deve essere in formato yyyy-MM-dd per usare i csv con Excel.
-- luca mac //Users//luca//Desktop//ProgettoPW//sql//csv//
-- luca desktop D:\\Users\\Luca\\Desktop\\file\\unisa\\Lezioni Ingegneria del Software\\Quattrocchi.it\\Quattrocchi\\sql\\csv\\

use quattrocchiDB;
LOAD DATA LOCAL INFILE 'D:\\Users\\Luca\\Desktop\\file\\unisa\\Lezioni Ingegneria del Software\\Quattrocchi.it\\Quattrocchi\\sql\\csv\\Amministratore.csv'
	INTO TABLE Amministratore
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\r\n'
	();
LOAD DATA LOCAL INFILE 'D:\\Users\\Luca\\Desktop\\file\\unisa\\Lezioni Ingegneria del Software\\Quattrocchi.it\\Quattrocchi\\sql\\csv\\GestoreOrdini.csv'
	INTO TABLE GestoreOrdini
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\r\n'
	();
LOAD DATA LOCAL INFILE 'D:\\Users\\Luca\\Desktop\\file\\unisa\\Lezioni Ingegneria del Software\\Quattrocchi.it\\Quattrocchi\\sql\\csv\\Acquirente.csv'
	INTO TABLE Acquirente
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\r\n'
	();
LOAD DATA LOCAL INFILE 'D:\\Users\\Luca\\Desktop\\file\\unisa\\Lezioni Ingegneria del Software\\Quattrocchi.it\\Quattrocchi\\sql\\csv\\IndirizzoSpedizione.csv'
	INTO TABLE IndirizzoSpedizione
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\r\n'
	();
LOAD DATA LOCAL INFILE 'D:\\Users\\Luca\\Desktop\\file\\unisa\\Lezioni Ingegneria del Software\\Quattrocchi.it\\Quattrocchi\\sql\\csv\\CartaCredito.csv'
	INTO TABLE CartaCredito
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\r\n'
	();
LOAD DATA LOCAL INFILE 'D:\\Users\\Luca\\Desktop\\file\\unisa\\Lezioni Ingegneria del Software\\Quattrocchi.it\\Quattrocchi\\sql\\csv\\Ordine.csv'
	INTO TABLE Ordine
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\r\n'
	();
LOAD DATA LOCAL INFILE 'D:\\Users\\Luca\\Desktop\\file\\unisa\\Lezioni Ingegneria del Software\\Quattrocchi.it\\Quattrocchi\\sql\\csv\\ArticoloInOrder.csv'
	INTO TABLE ArticoloInOrder
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\r\n'
	();
LOAD DATA LOCAL INFILE 'D:\\Users\\Luca\\Desktop\\file\\unisa\\Lezioni Ingegneria del Software\\Quattrocchi.it\\Quattrocchi\\sql\\csv\\ArticoloInStock.csv'
	INTO TABLE ArticoloInStock
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\r\n'
	();
LOAD DATA LOCAL INFILE 'D:\\Users\\Luca\\Desktop\\file\\unisa\\Lezioni Ingegneria del Software\\Quattrocchi.it\\Quattrocchi\\sql\\csv\\ArticoloInCarrello.csv'
	INTO TABLE ArticoloInCarrello
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\r\n'
	();
