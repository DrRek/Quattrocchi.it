use quattrocchiDB;
LOAD DATA LOCAL INFILE 'C:\\Users\\luigi\\OneDrive\\Documenti\\GitHub\\Quattrocchi.it\\Quattrocchi\\sql\\csv\\Amministratore.csv'
	INTO TABLE Amministratore
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\r\n'
	();
LOAD DATA LOCAL INFILE 'C:\\Users\\luigi\\OneDrive\\Documenti\\GitHub\\Quattrocchi.it\\Quattrocchi\\sql\\csv\\GestoreOrdini.csv'
	INTO TABLE GestoreOrdini
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\r\n'
	();
LOAD DATA LOCAL INFILE 'C:\\Users\\luigi\\OneDrive\\Documenti\\GitHub\\Quattrocchi.it\\Quattrocchi\\sql\\csv\\Acquirente.csv'
	INTO TABLE Acquirente
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\r\n'
	();
LOAD DATA LOCAL INFILE 'C:\\Users\\luigi\\OneDrive\\Documenti\\GitHub\\Quattrocchi.it\\Quattrocchi\\sql\\csv\\IndirizzoSpedizione.csv'
	INTO TABLE IndirizzoSpedizione
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\r\n'
	();
LOAD DATA LOCAL INFILE 'C:\\Users\\luigi\\OneDrive\\Documenti\\GitHub\\Quattrocchi.it\\Quattrocchi\\sql\\csv\\CartaCredito.csv'
	INTO TABLE CartaCredito
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\r\n'
	();
LOAD DATA LOCAL INFILE 'C:\\Users\\luigi\\OneDrive\\Documenti\\GitHub\\Quattrocchi.it\\Quattrocchi\\sql\\csv\\Ordine.csv'
	INTO TABLE Ordine
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\r\n'
	();
LOAD DATA LOCAL INFILE 'C:\\Users\\luigi\\OneDrive\\Documenti\\GitHub\\Quattrocchi.it\\Quattrocchi\\sql\\csv\\ArticoloInOrder.csv'
	INTO TABLE ArticoloInOrder
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\r\n'
	();
LOAD DATA LOCAL INFILE 'C:\\Users\\luigi\\OneDrive\\Documenti\\GitHub\\Quattrocchi.it\\Quattrocchi\\sql\\csv\\ArticoloInStock.csv'
	INTO TABLE ArticoloInStock
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\r\n'
	();
LOAD DATA LOCAL INFILE 'C:\\Users\\luigi\\OneDrive\\Documenti\\GitHub\\Quattrocchi.it\\Quattrocchi\\sql\\csv\\ArticoloInCarrello.csv'
	INTO TABLE ArticoloInCarrello
		FIELDS TERMINATED BY ';' LINES TERMINATED BY '\r\n'
	();
