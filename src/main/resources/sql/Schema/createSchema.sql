CREATE TABLE if NOT EXISTS users(
  user_id int IDENTITY (1,1) PRIMARY KEY,
  parent_id int null,
  reminder_id int null,
  ticket_id int null,
  constraint user_parent_fk FOREIGN KEY(parent_id) REFERENCES parents(parent_id) ON DELETE CASCADE,
  constraint user_reminder_fk FOREIGN KEY(reminder_id) REFERENCES reminders(reminder_id) ON DELETE CASCADE,
  constraint user_tiket_fk FOREIGN KEY(ticket_id) REFERENCES tickets(ticket_id) ON DELETE CASCADE,
  userName nvarchar(50) NOT NULL,
  password nvarchar(50) NOT NULL,
  firstName nvarchar(25) NOT NULL,
  lastName nvarchar(25) NOT NULL,
  email nvarchar(50) NOT NULL,
  belt nvarchar(10) NULL,
  stripes NUMERIC(1,0) NULL,
  role nvarchar(25) NOT NULL,
  tel int NULL,
  bornDate DATE NOT NULL,
  enabled BIT NOT NULL
);
CREATE UNIQUE INDEX ix_username on users(username, lastname);

create table IF NOT EXISTS authorities (
  authorities_id int IDENTITY(1,1) NOT NULL,
  constraint fk_authorities_users foreign key(user_id) references users(user_id) on delete cascade,
  username nvarchar(50) NOT NULL,
  authority nvarchar(50) NOT NULL
);
create unique index ix_auth_username on authorities(username,authority);

CREATE TABLE  IF NOT EXISTS attendance(
  attendace_id int IDENTITY (1,1) PRIMARY KEY,
  user_id int not null,
  training_id int not null,
  constraint att_train_fk FOREIGN KEY(training_id) REFERENCES trainings(training_id) ON DELETE CASCADE,
  constraint att_user_fk FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS parents(
  parent_id int IDENTITY (1,1) PRIMARY KEY,
  firstName nvarchar(25) NOT NULL,
  lastName nvarchar(25) NOT NULL,
  email nvarchar(50) NOT NULL,
  bornDate DATE NULL,
  agreement bit NOT NULL,
  tel int NOT NULL
);
CREATE UNIQUE INDEX ix_parents ON parents(lastname);

CREATE TABLE IF NOT EXISTS reminders(
  reminder_id int IDENTITY (1,1) PRIMARY KEY,
  reminderCount int NOT NULL,
  startingDate DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS tickets(
  ticket_id int IDENTITY (1,1) PRIMARY KEY,
  entry int NOT NULL,
  endingDate DATE NULL,
  startingDate DATE NULL,
  isTimeTicket BIT NOT NULL
);

CREATE TABLE IF NOT EXISTS trainings(
  training_id int IDENTITY (1,1) PRIMARY KEY,
  title nvarchar(50) NOT NULL,
  beginning DATE NOT NULL,
  ending DATE NOT NULL,
  trainingLevel int NULL,
  coach nvarchar(50) null,
  capacity int NULL,
  note nvarchar(500) NULL
);
CREATE UNIQUE INDEX ix_trainig ON trainings(title);

CREATE TABLE IF NOT EXISTS test(
  test_id int IDENTITY (1,1) PRIMARY KEY,
  entry int NOT NULL
);