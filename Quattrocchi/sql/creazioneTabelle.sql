drop schema quattrocchiDB;
create schema quattrocchiDB;
use quattrocchiDB;

create user if not exists
	progetto@localhost identified by 'pw';
	
grant all on quattrocchiDB.* to progetto@localhost;


create table Amministratore(
	Username varchar(50) primary key,
	Pwd varchar(15) not null,
	Nome varchar(30) not null,
	Cognome varchar(30) not null,
	Email varchar(40) not null unique
);

create table GestoreOrdini(
	Username varchar(50) primary key,
	Pwd varchar(15) not null,
	Nome varchar(30) not null,
	Cognome varchar(30) not null,
	Email varchar(40) not null unique,
	Matricola varchar(10) not null unique
);

create table Acquirente(
	Username varchar(50) primary key,
	Pwd varchar(15) not null,
	Nome varchar(30) not null,
	Cognome varchar(30) not null,
	Email varchar(40) not null unique,
	DataNascita date not null
);

create table IndirizzoSpedizione(
	Id integer(10) primary key,
	Stato varchar(30) not null,
	Provincia char(2) not null, -- non è var è char
	CAP integer(5) not null,
	Indirizzo varchar(40) not null,
	NumeroCivico integer(5) not null, -- 10 è decisamente troppo
	Acquirente varchar(50),
	foreign key (Acquirente) references Acquirente(Username) on delete set null on update cascade
);

create table CartaCredito(
	IdCarta	integer(10) primary key,
	NumeroCC integer(16) not null,
	Intestatario varchar(40) not null,
	Circuito varchar(20) not null,
	DataScadenza date not null,
	CvcCvv integer(3) not null,
	Acquirente varchar(50),
	foreign key (Acquirente) references Acquirente(Username) on delete set null on update cascade
);

create table Ordine(
	Codice integer(10) primary key,
   	DataEsecuzione date not null,
	Prezzo decimal(8,2) not null, -- a che serve sto double che bastano due cifre dopo la virgola
	IndirizzoSpedizione integer(10) not null,
	CartaCredito integer(10) not null,
	Acquirente varchar(16) not null,
	StatoOrdine varchar(30) not null,
	DataConsegna date,
	NumeroTracking varchar(50),
	Correriere varchar(40),
	foreign key (IndirizzoSpedizione) references IndirizzoSpedizione(Id),
	foreign key (CartaCredito) references CartaCredito(IdCarta),
	foreign key (Acquirente) references Acquirente(Username)
);

create table ArticoloInOrder(
	Codice varchar(10) primary key,
	Modello varchar(50) not null,
	Marca varchar(20) not null, -- che cazz sta scritt nell'er
	Img1 varchar(60) not null,
	Img2 varchar(60) not null,
	Img3 varchar(60) not null,
	Descrzione varchar(255) not null,
	Prezzo decimal(6,2) not null, -- sto prezzo double non s è mai sentito
	Quantit� integer(3) not null,-- fare l'esagerato con le dimensioni non ti fa onore
	Ordine integer(10) not null,
	foreign key (Ordine) references Ordine(Codice)
);

create table ArticoloInStock(
	Codice varchar(10) primary key,
	Modello varchar(50) not null,
	Marca varchar(10) not null, -- che cazz sta scritt nell'er
	Img1 varchar(60) not null, -- il path delle immagini può essere parecchio lungo, 10 non è abbastanza
	Img2 varchar(60) not null,
	Img3 varchar(60) not null,
	Descrzione varchar(255) not null,
	Prezzo decimal(6,2) not null, -- sto prezzo double non s è mai sentito
	Disponibilità integer(3) not null-- fare l'esagerato con le dimensioni non ti fa onore
);

-- non ho la minima idea di cosa ci sia scritto nell'er per quanto riguarda il carrello, questo è quello che voleva il prof da quello che ho capito
-- lo so che è l'ultima cosa e vuoi andare di fretta, ma guardatelo con attenzione e discutiamone
create table ArticoloInCarello(
	Acquirente varchar(50) not null,
	ArticoloInStock varchar(10) not null,
	Quantit� integer(3) not null,
	foreign key (Acquirente) references Acquirente(Username),
	foreign key (ArticoloInStock) references ArticoloInStock(Codice),
	primary key(Acquirente, ArticoloInStock)
);