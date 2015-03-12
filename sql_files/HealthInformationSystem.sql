# Anshul Jain - 5716543
# Sean Prasertsit - 5718366

CREATE DATABASE  IF NOT EXISTS `HealthInformationSystem` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `HealthInformationSystem`;

create table Guardians (
	GuardianNo int not null,
	GivenName varchar(100),
	FamilyName varchar(100),
	Phone varchar(100),
	Address varchar(100),
	City varchar(100),
	State varchar(100),
	Zip int,
	primary key (GuardianNo)
);

create table InsuranceCompanies (
	PayerId int not null,
	Name varchar(100),
	Purpose char(100),
	PolicyType char(100),
	PolicyHolder varchar(100),
	primary key (PayerId)
);


create table Authors (
	AuthorId varchar(100) not null,
	AuthorTitle char(100),
	AuthorFirstName char(20),
	AuthorLastName char(20),
	primary key (AuthorId)
);


# Doesnt associate to a patient directly, expects to get patient through # patient visit ID
# Which hasnt been created yet as a table
create table LabTestReports (
	LabTestResultId int not null,
	PatientVisitId int not null, 
	LabTestPerformedDate varchar(100),
	LabTestType char(20),
	TestResultValue int,
	ReferenceRangeHigh varchar(100),
	ReferenceRangeLow varchar(100),
	primary key (LabTestResultId)
);

create table Patients (
	PatientId int not null,
	PatientRole int not null,
	GivenName char(20),
	FamilyName char(20),
	Suffix char(20),
	Gender char(10),
	Birthtime varchar(100),
	ProviderId varchar(100),
	xmlHealthCreation datetime not null,
	PayerId int not null,
	primary key (PatientId),
	foreign key (PatientRole)
		references Guardians(GuardianNo),
	foreign key (PayerId)
		references InsuranceCompanies(PayerId)
);

create table AuthorsPatients (
	PatientId int not null,
	AuthorId varchar(100) not null,
	ParticipatingRole char(50),
	primary key (PatientId, AuthorId),
	foreign key (PatientId)
		references Patients(PatientId),
	foreign key (AuthorId)
		references Authors(AuthorId)
);


create table FamilyHistory (
	id int not null,
	Relationship char(100),
	Age int not null,
	PatientId int not null,
	Diagnosis varchar(100),
	primary key (id),
	foreign key (PatientId)
		references Patients(PatientId)

);

create table Allergies (
	Substance char(20) not null,
	Status char(10) not null,
	Reaction char(40) not null,
	PatientId int not null,
	primary key (PatientId, Substance),
	foreign key (PatientId)
		references Patients(PatientId)

);

create table Plans (
	PatientId int not null,
	Activity varchar(100) not null,
	ActivityTime varchar(100) not null,
	CreatedOn varchar(100) not null,
	PlanId int not null,
	primary key (PlanId),
	foreign key (PatientId)
		references Patients(PatientId)
);
