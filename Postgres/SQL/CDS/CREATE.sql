CREATE TABLE person ( 
	key	INTEGER, 
	name VARCHAR(75), 
	geburtsdatum DATE,
	PRIMARY KEY (key)
);

CREATE TABLE segler ( 
	key	INTEGER REFERENCES person ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY (key)
);

CREATE TABLE trainer ( 
	key	INTEGER REFERENCES person ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY (key)
);

CREATE TABLE boot ( 
	id	INTEGER,
	name VARCHAR(75),
	personen INTEGER,
	tiefgang NUMERIC,
	PRIMARY KEY (id)
);

CREATE TABLE Tourenboot ( 
	id INTEGER REFERENCES boot ON DELETE CASCADE ON UPDATE CASCADE,
	bootsklasse	VARCHAR(50),
	PRIMARY KEY (id)
);

CREATE TABLE Sportboot ( 
	id INTEGER REFERENCES boot ON DELETE CASCADE ON UPDATE CASCADE,
	segelflaeche NUMERIC,
	PRIMARY KEY (id)
);

CREATE TABLE mannschaft (
        name VARCHAR(75),
        aklasse VARCHAR(50),
        key INTEGER REFERENCES trainer ON DELETE CASCADE ON UPDATE CASCADE,
		PRIMARY KEY (name)
);

CREATE TABLE regatta ( 
	name VARCHAR(75), 
	jahr INTEGER,
	land VARCHAR(50), 
	PRIMARY KEY (name, jahr)
);

CREATE TABLE wettfahrt (
	name VARCHAR(75),
	jahr INTEGER, 
	datum DATE,
	laenge INTEGER,
	PRIMARY KEY (datum, name, jahr),
	FOREIGN KEY (name, jahr) REFERENCES Regatta (name, jahr) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE bildet (
	key INTEGER REFERENCES segler,
	name VARCHAR(75) REFERENCES mannschaft ON DELETE CASCADE ON UPDATE CASCADE, 
	PRIMARY KEY (key, name)
);

CREATE TABLE zugewiesen (
	id INTEGER REFERENCES boot, 
	name VARCHAR(75) REFERENCES mannschaft ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY (id, name)
);
	
CREATE TABLE nimmt_teil (
	mname VARCHAR(75) 	REFERENCES mannschaft (name),
	rname VARCHAR(75),
	rjahr INTEGER,
	sportboot INTEGER REFERENCES Sportboot (id),
	startnr INTEGER,
	PRIMARY KEY (mname, rname, rjahr, sportboot),
	FOREIGN KEY (rname, rjahr) REFERENCES Regatta (name, jahr) ON DELETE CASCADE ON UPDATE CASCADE
);
	
CREATE TABLE erzielt (
	mname VARCHAR(75) 	REFERENCES mannschaft (name),
	wname VARCHAR(75),
	wjahr INTEGER,
	wdatum DATE,
	punkte INTEGER,
	PRIMARY KEY (mname, wname, wjahr, wdatum),
	FOREIGN KEY (wname, wjahr, wdatum) references wettfahrt(name, jahr, datum) ON DELETE CASCADE ON UPDATE CASCADE
);	